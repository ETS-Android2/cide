package com.github.pomaretta.tictactoe.board;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.gridlayout.widget.GridLayout;

import com.github.pomaretta.tictactoe.ai.AdvancedAI;
import com.github.pomaretta.tictactoe.R;
import com.github.pomaretta.tictactoe.ai.RandomAI;
import com.github.pomaretta.tictactoe.views.GameActivity;

public class GameBoard {

    private final GridLayout gameLayout;
    private final Difficulty difficulty;
    private final GameActivity activity;

    private final MediaPlayer pressPlayer;
    private final MediaPlayer winPlayer;
    private final MediaPlayer losePlayer;
    private final MediaPlayer tiePlayer;

    private BoardPiece[] boardPieces;
    private BoardAI boardAI;
    private boolean turn;

    private final boolean playerTurn;
    private final Integer[][] winningCombinations = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
    };

    public GameBoard(GridLayout layout, Difficulty difficulty, boolean playerTurn, GameActivity activity) {
        this.gameLayout = layout;
        this.difficulty = difficulty;
        this.playerTurn = playerTurn;
        this.activity = activity;

        this.pressPlayer = MediaPlayer.create(this.activity, R.raw.clicky);
        this.winPlayer = MediaPlayer.create(this.activity, R.raw.winner);
        this.losePlayer = MediaPlayer.create(this.activity, R.raw.lose);
        this.tiePlayer = MediaPlayer.create(this.activity, R.raw.tie);
    }

    public void start() {

        // Vaciar el grid layout.
        this.gameLayout.removeAllViews();
        this.boardPieces = new BoardPiece[9];

        // Empieza el player
        this.turn = this.playerTurn;

        // Rellenamos el gridLayout con botones.
        this.populateBoard(3,3);

        // Creamos la AI según la dificultad.
        switch (this.difficulty) {
            case HARD:
                this.boardAI = new AdvancedAI(this, true, this.playerTurn);
                break;
            case MEDIUM:
                this.boardAI = new AdvancedAI(this, false, this.playerTurn);
                break;
            default:
                this.boardAI = new RandomAI(this);
                break;
        }

    }

    /**
     * @return Devuelve verdadero si algún jugador ha ganado, o ningún jugador
     * es ganador pero no quedan piezas restantes.
     */
    public boolean isGameOver() {
        return checkWinner() == -1 && !piecesLeft() || checkWinner() != -1;
    }

    /**
     * @return Devuelve el turno actual, verdadero o falso según cual sea el jugador
     * dirá si es su turno o no.
     */
    public boolean isTurn() {
        return turn;
    }

    /**
     * @return Devuelve si quedan piezas restantes, piezas no clickadas.
     */
    public boolean piecesLeft() {
        for (BoardPiece piece : this.boardPieces) {
            if (!piece.isClicked()) return true;
        }
        return false;
    }

    /**
     * Comprobamos varias reglas del juego, si ya ha acabado,
     * si el botón está clickado, o si no es el turno del jugador
     * que intenta clickar.
     *
     * @param piece la pieza que se quiere clickar.
     * @param source el origen del click (jugador o máquina)
     * @throws Exception Si el juego ha acabado, si el botón ha sido clickado o si no es el turno del jugador.
     */
    private void checkRules(BoardPiece piece, boolean source) throws Exception {
        // Si el juego ya ha terminado.
        if (isGameOver()) throw new Exception("Game is over");
        // Si el botón ya está pulsado
        if (piece.isClicked()) throw new Exception("Button already clicked.");
        // Si no es el turno del jugador que quiere pulsarlo.
        if (isTurn() != source) throw new Exception("Is not your turn.");
    }

    /**
     * Ejecuta el jugador máquina, busca la posición y ejecuta el click.
     *
     * @throws Exception Si alguna regla del juego es incumplida.
     */
    private void runAI() throws Exception {
        int position = this.getBoardAI().search();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            try {
                this.fillButton(this.getBoardPieces()[position], !this.playerTurn);
            } catch (Exception e) {
                // Ignorar
            }
        }, 1500);
    }

    /**
     * Es la acción centraliza del click del jugador, permite clickar un botón,
     * ejecutar las acciones visuales y auditivas y dar por clickado a la pieza.
     *
     * @param piece la pieza que se quiere clickar.
     * @param source el origen del click (jugador o máquina)
     * @throws Exception Si alguna regla es incumplida.s
     */
    public void fillButton(BoardPiece piece, boolean source) throws Exception {

        this.checkRules(piece, source);

        // Establecemos el fondo del botón.
        piece.getButton().setBackgroundResource(
                isTurn()
                ? R.drawable.game_x
                : R.drawable.game_o
        );

        // Iniciamos el efecto sonido.
        pressPlayer.start();
        // Registramos como pulsado el botón.
        piece.click(source);

        // Cambiamos el orden de turno.
        this.turn = !this.turn;
        this.checkGame();

        // Run AI si el source es el jugador.
        if (isTurn() != playerTurn && !this.isGameOver()) runAI();
    }

    /**
     * Ejecuta el sonido correspondiente al resultado del juego.
     *
     * @param winner el ganador (1, 0) si es empate -1.
     * @param playerFlag indica quien es el jugador humano.
     */
    private void playEndgameSound(int winner, int playerFlag) {
        if (winner == -1) {
            this.tiePlayer.start();
            return;
        }
        if (winner != playerFlag) {
            this.losePlayer.start();
            return;
        }
        this.winPlayer.start();
    }

    /**
     * Acción a ejecutar despues de un click, que permite comprobar el estado del juego
     * para saber si alguien ha ganado, empatado y ejecutar las acciones pertinentes.
     */
    private void checkGame() {

        // Miramos si la jugada afecta en que un jugador gana la partida.
        int winner = checkWinner();
        int playerFlag = this.playerTurn ? 1 : 0;

        // Si no gana nadie y quedan piezas se sigue el juego y se da la pista.
        if (winner == -1 && piecesLeft()) {
            putHint(this.turn);
            return;
        }

        this.activity.getRestartButton().setEnabled(false);

        /*
         * En caso de que un jugador o que no queden piezas por jugar
         * se harán una serie de reglas para dar por terminado el juego
         * y ofrecer controles para reiniciar la partida y mostrar el ganador
         * o si es empate.
         */

        // Se establecen los visuales para ofrecer un nuevo juego.
        this.activity.getTurnHint().setText("Reiniciar juego");
        // Se muestra el ganador o si es empate.
        this.activity.getStartGameText().setText(
                String.format(
                        "%s",
                        winner == -1 ? "Empate" : winner == playerFlag ? "Gana el jugador" : "Gana la máquina"
                )
        );
        // Se muestra el logo de la pieza del ganador.
        this.activity.getStartGameImage().setBackgroundResource(
                winner == -1 ? R.drawable.game_xo : winner == 1 ? R.drawable.game_x : R.drawable.game_o
        );

        // Suena el sonido correspondiente a la jugada.
        this.playEndgameSound(winner, playerFlag);

        // Se obtiene la fila ganadora.
        Integer[] row = getWinnerRow(this.boardPieces, winner);

        // Si es nula significa que ha sido empate y se ejecutan las acciones sin resaltar la fila.
        if (row == null) {
            this.activity.getPlaceholderLayout().setVisibility(View.VISIBLE);
            this.gameLayout.removeAllViews();
            this.activity.getRestartButton().setEnabled(true);
            return;
        }

        // Se ponen a verde los botones ganadores.
        for (int col : row) {
            this.boardPieces[col].getButton().setBackgroundColor(
                    Color.argb(60,0,255,0)
            );
        }

        // Se retrasa la salida de los visuales para restablecer la partida.
        Handler handler = new Handler();
        handler.postDelayed(() -> {

            this.activity.getPlaceholderLayout().setVisibility(View.VISIBLE);
            this.gameLayout.removeAllViews();

            this.activity.getRestartButton().setEnabled(true);

        }, 2500);

    }

    /**
     * Permite dar la pista necesaria para el jugador, indicando si es su turno o no.
     * @param turn el turno actual.
     */
    private void putHint(boolean turn) {
        this.activity.getTurnHint().setText(String.format(
                "Turno %s",
                turn == this.playerTurn ? "del Jugador" : "de la Máquina"
        ));
    }

    /**
     * @return Devuelve si el jugador 1 es ganador, o si el jugador 0 es ganador, sino devuelve
     * -1, indicando empate.
     */
    public int checkWinner() {
        if (isWinner(this.boardPieces, 1)) return 1;
        if (isWinner(this.boardPieces,0)) return 0;
        return -1;
    }

    /**
     *
     * Permite saber si alguna de las combinaciones concuerda con el
     * jugador que se quiere saber si gana.
     *
     * @param board el estado del tablero a la hora de mirar las combinaciones.
     * @param player el jugador a buscar, 0 es la máquina, 1 es el jugador humano.
     * @return Se alguna de las combinaciones sale correcta.
     */
    public boolean isWinner(BoardPiece[] board, int player) {
        for (Integer[] combination : this.winningCombinations) {
            if (board[combination[0]].isClickedBy() == player && board[combination[1]].isClickedBy() == player && board[combination[2]].isClickedBy() == player) return true;
        }
        return false;
    }

    /**
     * Obtiene la fila ganadora.
     *
     * @param board el tablero actual.
     * @param player el jugador a comprobar.
     * @return si se da el caso de combinación ganadora devuelve las 3 posiciones de la combinación.
     */
    public Integer[] getWinnerRow(BoardPiece[] board, int player) {
        for (Integer[] combination : this.winningCombinations) {
            if (board[combination[0]].isClickedBy() == player && board[combination[1]].isClickedBy() == player && board[combination[2]].isClickedBy() == player) return combination;
        }
        return null;
    }

    public BoardPiece[] getBoardPieces() {
        return boardPieces.clone();
    }

    public BoardAI getBoardAI() {
        return boardAI;
    }

    /**
     *
     * Rellena el gridLayout con el número de botones necesario
     * (3x3), se asigna que controles tienen esos botones y como interactua con el juego.
     *
     * @param rows el número de filas.
     * @param columns el número de columnas.
     */
    private void populateBoard(int rows, int columns) {
        for (int i = 0; i < (rows * columns); i++) {

            Button button = new Button(this.gameLayout.getContext());

            // El tamaño de los botones
            button.setLayoutParams(new ViewGroup.LayoutParams(200,200));

            // Se crea el objeto BoardPiece.
            BoardPiece piece = new BoardPiece(button);

            // Se establece la funcionalidad del botón.
            piece.getButton().setOnClickListener(ev -> {
                try {
                    if (isTurn() != this.playerTurn) throw new Exception("No es tu turno");
                    fillButton(piece,this.playerTurn);
                } catch (Exception e) {
                    // Si no es su turno no ocurre nada.
                    e.printStackTrace();
                }
            });

            this.boardPieces[i] = piece;
            this.gameLayout.addView(piece.getButton());
        }
    }

}

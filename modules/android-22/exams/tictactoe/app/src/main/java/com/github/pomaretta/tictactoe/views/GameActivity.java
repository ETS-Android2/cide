package com.github.pomaretta.tictactoe.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.pomaretta.tictactoe.R;
import com.github.pomaretta.tictactoe.board.BoardAI;
import com.github.pomaretta.tictactoe.board.BoardPiece;
import com.github.pomaretta.tictactoe.board.Difficulty;
import com.github.pomaretta.tictactoe.board.GameBoard;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private final String[] DIFFICULTIES = {
        "Hard",
        "Medium",
        "Easy"
    };

    private Spinner difficultySelector;
    private TextView turnHint;

    private ConstraintLayout placeholderLayout;
    private TextView startGameText;
    private ImageView startGameImage;

    private GridLayout gameLayout;
    private Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        this.difficultySelector = findViewById(R.id.difficultySelector);
        this.turnHint = findViewById(R.id.turnHint);

        this.placeholderLayout = findViewById(R.id.placeholderLayout);
        this.startGameText = findViewById(R.id.startGameText);
        this.startGameImage = findViewById(R.id.startGameImage);

        this.gameLayout = findViewById(R.id.gameGrid);
        this.restartButton = findViewById(R.id.restartButton);

        // Populate dropdown.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, DIFFICULTIES
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.difficultySelector.setAdapter(adapter);
        this.difficultySelector.setSelection(DIFFICULTIES.length - 1);

        // Start placeholder
        this.startGameImage.setOnClickListener(ev -> startGame());

        // Restart
        this.restartButton.setOnClickListener(ev -> startGame());

    }

    private void startGame() {
        this.placeholderLayout.setVisibility(View.INVISIBLE);
        GameBoard board = new GameBoard(
            this.gameLayout,
            Difficulty.valueOf(this.DIFFICULTIES[this.difficultySelector.getSelectedItemPosition()].toUpperCase()),
            true,
            this
        );
        board.start();
    }

    public TextView getTurnHint() {
        return turnHint;
    }

    public TextView getStartGameText() {
        return this.startGameText;
    }

    public ImageView getStartGameImage() {
        return this.startGameImage;
    }

    public ConstraintLayout getPlaceholderLayout() {
        return placeholderLayout;
    }

    public Button getRestartButton() {
        return restartButton;
    }
}
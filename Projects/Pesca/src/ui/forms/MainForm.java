package ui.forms;

import ui.FormManager;
import ui.NotificationWindow;
import ui.PescaForm;

import javax.swing.*;

public class MainForm extends PescaForm {

    private JButton altaDeUsuarioButton;
    private JButton bajaDeUsuarioButton;
    private JButton pescarButton;
    private JButton estadisticasButton;

    private JTextField altaDeUsuarioField;
    private JTextField bajaDeUsuarioField;
    private JTextField pescaField;
    private JTextField estadisticasField;

    private ButtonGroup pesqueras;

    private JRadioButton mediterraniaButton;
    private JRadioButton floridaButton;
    private JRadioButton pacificoButton;
    private JRadioButton indicoButton;

    public MainForm(FormManager manager) {
        super(manager);
    }

    @Override
    public void run() {

        this.root.setTitle("Menú | Pesca by Carlos Pomares");

        this.root.setBounds(100, 100, 506, 329);
        this.root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.root.getContentPane().setLayout(null);

        altaDeUsuarioButton = new JButton("Alta de usuario");
        altaDeUsuarioButton.setBounds(26, 21, 112, 38);
        this.root.getContentPane().add(altaDeUsuarioButton);

        bajaDeUsuarioButton = new JButton("Baja de usuario");
        bajaDeUsuarioButton.setBounds(26, 83, 112, 38);
        this.root.getContentPane().add(bajaDeUsuarioButton);

        pescarButton = new JButton("Pescar");
        pescarButton.setBounds(26, 148, 112, 38);
        this.root.getContentPane().add(pescarButton);

        estadisticasButton = new JButton("Estadísticas");
        estadisticasButton.setBounds(26, 227, 112, 38);
        this.root.getContentPane().add(estadisticasButton);

        altaDeUsuarioField = new JTextField();
        altaDeUsuarioField.setBounds(170, 21, 294, 38);
        this.root.getContentPane().add(altaDeUsuarioField);
        altaDeUsuarioField.setColumns(10);

        bajaDeUsuarioField = new JTextField();
        bajaDeUsuarioField.setColumns(10);
        bajaDeUsuarioField.setBounds(170, 83, 294, 38);
        this.root.getContentPane().add(bajaDeUsuarioField);

        pescaField = new JTextField();
        pescaField.setColumns(10);
        pescaField.setBounds(170, 148, 294, 38);
        this.root.getContentPane().add(pescaField);

        estadisticasField = new JTextField();
        estadisticasField.setColumns(10);
        estadisticasField.setBounds(170, 227, 294, 38);
        this.root.getContentPane().add(estadisticasField);

        this.pesqueras = new ButtonGroup();

        mediterraniaButton = new JRadioButton("Mediterrania");
        pesqueras.add(mediterraniaButton);
        mediterraniaButton.setBounds(175, 193, 85, 23);
        mediterraniaButton.setSelected(true);
        this.root.getContentPane().add(mediterraniaButton);

        floridaButton = new JRadioButton("Florida");
        pesqueras.add(floridaButton);
        floridaButton.setBounds(262, 193, 66, 23);
        this.root.getContentPane().add(floridaButton);

        pacificoButton = new JRadioButton("Pacífico");
        pesqueras.add(pacificoButton);
        pacificoButton.setBounds(330, 193, 66, 23);
        this.root.getContentPane().add(pacificoButton);

        indicoButton = new JRadioButton("Índico");
        pesqueras.add(indicoButton);
        indicoButton.setBounds(398, 193, 66, 23);
        this.root.getContentPane().add(indicoButton);

        altaDeUsuarioButton.addActionListener(e -> {
            try {
                registerUser();
            } catch (Exception exception){
                NotificationWindow.run(exception.getMessage(),"Error:");
            }
            this.altaDeUsuarioField.setText("");
        });

        bajaDeUsuarioButton.addActionListener(e -> {
            try {
                deleteUser();
            } catch (Exception exception){
                NotificationWindow.run(exception.getMessage(),"Error:");
            }
            this.bajaDeUsuarioField.setText("");
        });

        estadisticasButton.addActionListener(e -> {
            try {
                showStatistics();
            } catch (Exception exception){
                NotificationWindow.run(exception.getMessage(),"Error:");
            }
            this.estadisticasField.setText("");
        });

        pescarButton.addActionListener(e -> {
            try {
                registerNewAction();
            } catch (Exception exception){
                NotificationWindow.run(exception.getMessage(),"Error:");
            }
            this.pescaField.setText("");
        });

    }

    private void registerUser() throws Exception {

        if(this.altaDeUsuarioField.getText().equals("")){
            throw new Exception("Alta de usuario vacia.");
        }

        this.manager.getPescaUI().getApi().registerUser(this.altaDeUsuarioField.getText());
        NotificationWindow.run("Usuario creado satisfactoriamente.","Información:");

    }

    private void deleteUser() throws Exception {

        if(this.bajaDeUsuarioField.getText().equals("")){
            throw new Exception("Alta de usuario vacia.");
        }

        this.manager.getPescaUI().getApi().deleteUser(this.bajaDeUsuarioField.getText());
        NotificationWindow.run("Usuario eliminado satisfactoriamente.","Información:");

    }

    private void showStatistics() throws Exception {

        StatisticsForm form;

        if(this.estadisticasField.getText().equals("")){
            form = new StatisticsForm(this.manager,this.manager.getPescaUI().getApi().getStatistics(),"Global");
        } else {
            form = new StatisticsForm(this.manager,this.manager.getPescaUI().getApi().getStatistics(this.estadisticasField.getText()),this.estadisticasField.getText());
        }

        this.manager.changeForm(form);
    }

    private void registerNewAction() throws Exception {

        if(this.pescaField.getText().equals("")){
            throw new Exception("Usuario para pescar vacio.");
        }

        this.manager.getPescaUI().getApi().registerNewAction(
                this.pescaField.getText()
                ,this.manager.getPescaUI().getApi().getFish(getClass().getResource(parseBoat()).getFile())
        );

        NotificationWindow.run("Pesca realizada satisfactoriamente.","Información:");

    }

    private String parseBoat() {
        if(floridaButton.isSelected()){
            return "/data/florida.txt";
        } else if(pacificoButton.isSelected()){
            return "/data/pacifico.txt";
        } else if(indicoButton.isSelected()){
            return "/data/indico.txt";
        } else {
            return "/data/mediterrania.txt";
        }
    }

}
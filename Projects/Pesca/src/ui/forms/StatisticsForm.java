package ui.forms;

import common.specification.StatisticResult;
import ui.FormManager;
import ui.NotificationWindow;
import ui.PescaForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StatisticsForm extends PescaForm {

    private final StatisticResult result;
    private final String user;

    private JTextField maximoPesoField;
    private JTextField minimoPesoField;
    private JTextField mediaPesoField;
    private JTextField medianaPesoField;

    private JList<String> fishSizesList;
    private JList<String> fishCatchesList;

    private DefaultListModel<String> fishSizesModel;
    private DefaultListModel<String> fishCatchesModel;

    public StatisticsForm(FormManager manager, StatisticResult statistic, String user) {
        super(manager);
        this.result = statistic;
        this.user = user;
    }

    @Override
    public void run() {

        this.root.setTitle(String.format("%s Statistics | Pesca by Carlos Pomares",this.user));

        this.root.setBounds(100, 100, 622, 375);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Estadísticas");
        lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(10, 11, 119, 33);
        root.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Máximo peso registrado:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(10, 51, 166, 24);
        root.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Mínimo peso registrado:");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1.setBounds(10, 83, 166, 24);
        root.getContentPane().add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Media de peso registrado:");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1_1.setBounds(10, 118, 166, 24);
        root.getContentPane().add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("Mediana de peso registrado:");
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1_1_1.setBounds(10, 153, 191, 24);
        root.getContentPane().add(lblNewLabel_1_1_1_1);

        maximoPesoField = new JTextField();
        maximoPesoField.setBounds(220, 51, 68, 24);
        root.getContentPane().add(maximoPesoField);
        maximoPesoField.setColumns(10);

        minimoPesoField = new JTextField();
        minimoPesoField.setColumns(10);
        minimoPesoField.setBounds(220, 83, 68, 24);
        root.getContentPane().add(minimoPesoField);

        mediaPesoField = new JTextField();
        mediaPesoField.setColumns(10);
        mediaPesoField.setBounds(220, 118, 68, 24);
        root.getContentPane().add(mediaPesoField);

        medianaPesoField = new JTextField();
        medianaPesoField.setColumns(10);
        medianaPesoField.setBounds(220, 153, 68, 24);
        root.getContentPane().add(medianaPesoField);

        fishSizesList = new JList<String>();
        fishSizesList.setBounds(10, 221, 276, 104);
        root.getContentPane().add(fishSizesList);

        fishCatchesList = new JList<String>();
        fishCatchesList.setBounds(316, 221, 276, 104);
        root.getContentPane().add(fishCatchesList);

        JLabel lblNewLabel_2 = new JLabel("Máximo peso por pescado capturado:");
        lblNewLabel_2.setBounds(10, 196, 278, 14);
        root.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_2_1 = new JLabel("Máximo capturas por pescado:");
        lblNewLabel_2_1.setBounds(314, 196, 278, 14);
        root.getContentPane().add(lblNewLabel_2_1);

        JButton exitButton = new JButton("Volver al menu");
        exitButton.setBounds(467, 14, 125, 23);
        root.getContentPane().add(exitButton);

        JLabel lblNewLabel_3 = new JLabel(this.user);
        lblNewLabel_3.setBounds(120, 18, 208, 14);
        root.getContentPane().add(lblNewLabel_3);

        fishSizesModel = new DefaultListModel<String>();
        this.fishSizesList.setModel(fishSizesModel);

        fishCatchesModel = new DefaultListModel<String>();
        this.fishCatchesList.setModel(fishCatchesModel);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToMenu();
            }
        });

        updateFields();

    }

    /**
     * Updates all fields with the StatisticsResult data.
     */
    private void updateFields(){

        this.maximoPesoField.setText(String.format("%.2f",this.result.getMax()));
        this.minimoPesoField.setText(String.format("%.2f",this.result.getMin()));
        this.mediaPesoField.setText(String.format("%.2f",this.result.getAverage()));
        this.medianaPesoField.setText(String.format("%.2f",this.result.getMean()));

        StatisticResult s = (StatisticResult) this.result;

        HashMap<String,Float> sizes = new HashMap<>();
        HashMap<String,Float> catches = new HashMap<>();


        try {
            s.getStatisticsFromFile(sizes,"tmp","sizes.txt",'#',2,0,1);
            s.getStatisticsFromFile(catches,"tmp","catches.txt",'#',2,0,1);
        } catch (IOException e){
            NotificationWindow.run(e.getMessage(),"Error");
        }

        // Uses HashMaps to populate the JList.
        updateList(fishSizesModel,sizes,"KG");
        updateList(fishCatchesModel,catches,"veces");

    }

    /**
     *
     * Updates the list with the data and can show an specified unit.
     *
     * @param list the list to update.
     * @param data the data to add in the list.
     * @param unit the unit of the data.
     */
    private void updateList(DefaultListModel<String> list, HashMap<String,Float> data, String unit){
        list.clear();
        for(Map.Entry<String,Float> entry : data.entrySet()){
            String d = String.format("%s, %.2f %s",entry.getKey(), entry.getValue(),unit);
            list.addElement(d);
        }
    }

    /**
     * Allows to return to the main menu.
     */
    private void goToMenu(){
        this.manager.changeForm(this.manager.getPescaUI().getForms().get("main"));
    }

}

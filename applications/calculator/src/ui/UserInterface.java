package ui;

/*

    Project     Programming21
    Package     ui    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-23

    DESCRIPTION
    
*/

import calculator.Calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Carlos Pomares
 */

public class UserInterface {

    private JFrame frame;
    private JTextArea numberField;

    private String arg1 = "", arg2 = "";
    private String operator = "";
    private String result = "";
    private boolean argOrder = true;
    private boolean arg1Dot = false, arg2Dot = false;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserInterface window = new UserInterface();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public UserInterface() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 335, 441);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 159, 230, 232);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JButton number1 = new JButton("1");
        number1.setBounds(0, 0, 70, 50);
        panel.add(number1);
        number1.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JButton number2 = new JButton("2");
        number2.setBounds(80, 0, 70, 50);
        panel.add(number2);
        number2.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JButton number3 = new JButton("3");
        number3.setBounds(160, 0, 70, 50);
        panel.add(number3);
        number3.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JButton number4 = new JButton("4");
        number4.setBounds(0, 61, 70, 50);
        panel.add(number4);
        number4.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JButton number5 = new JButton("5");
        number5.setBounds(80, 61, 70, 50);
        panel.add(number5);
        number5.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JButton number6 = new JButton("6");
        number6.setBounds(160, 61, 70, 50);
        panel.add(number6);
        number6.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JButton number7 = new JButton("7");
        number7.setBounds(0, 122, 70, 50);
        panel.add(number7);
        number7.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JButton number8 = new JButton("8");
        number8.setBounds(80, 122, 70, 50);
        panel.add(number8);
        number8.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JButton number9 = new JButton("9");
        number9.setBounds(160, 122, 70, 50);
        panel.add(number9);
        number9.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JButton number0 = new JButton("0");
        number0.setBounds(80, 182, 70, 50);
        panel.add(number0);
        number0.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JButton dotButton = new JButton(".");
        dotButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dotButton.setBounds(160, 183, 70, 50);
        panel.add(dotButton);

        JButton clearButton = new JButton("C");
        clearButton.setBackground(Color.RED);
        clearButton.setBounds(10, 111, 70, 37);
        frame.getContentPane().add(clearButton);

        JButton btnFutureUpdates = new JButton("Future Updates");
        btnFutureUpdates.setBounds(90, 111, 150, 37);
        frame.getContentPane().add(btnFutureUpdates);

        JButton additionButton = new JButton("+");
        additionButton.setBackground(Color.GREEN);
        additionButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        additionButton.setBounds(250, 155, 59, 37);
        frame.getContentPane().add(additionButton);

        JButton subtractButton = new JButton("-");
        subtractButton.setBackground(Color.MAGENTA);
        subtractButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        subtractButton.setBounds(250, 203, 59, 37);
        frame.getContentPane().add(subtractButton);

        JButton multiplyButton = new JButton("*");
        multiplyButton.setBackground(Color.CYAN);
        multiplyButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        multiplyButton.setBounds(250, 257, 59, 37);
        frame.getContentPane().add(multiplyButton);

        JButton divisionButton = new JButton("/");
        divisionButton.setBackground(Color.BLUE);
        divisionButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        divisionButton.setBounds(250, 305, 59, 37);
        frame.getContentPane().add(divisionButton);

        JButton equalButton = new JButton("=");
        equalButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        equalButton.setBackground(Color.YELLOW);
        equalButton.setBounds(250, 353, 59, 38);
        frame.getContentPane().add(equalButton);

        numberField = new JTextArea();
        numberField.setLineWrap(true);
        numberField.setFont(new Font("Monospaced", Font.PLAIN, 20));
        numberField.setEditable(false);
        numberField.setBounds(10, 11, 299, 89);
        frame.getContentPane().add(numberField);

        // CLEAR

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearProperties();
            }
        });

        // CALCULATOR NUMBERS

        number0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(getOrder()) {
                    setArg1(concatenate(getArg1(),"0"));
                } else {
                    setArg2(concatenate(getArg2(),"0"));
                }
            }
        });

        number1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(getOrder()) {
                    setArg1(concatenate(getArg1(),"1"));
                } else {
                    setArg2(concatenate(getArg2(),"1"));
                }
            }
        });

        number2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(getOrder()) {
                    setArg1(concatenate(getArg1(),"2"));
                } else {
                    setArg2(concatenate(getArg2(),"2"));
                }
            }
        });


        number3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(getOrder()) {
                    setArg1(concatenate(getArg1(),"3"));
                } else {
                    setArg2(concatenate(getArg2(),"3"));
                }
            }
        });

        number4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(getOrder()) {
                    setArg1(concatenate(getArg1(),"4"));
                } else {
                    setArg2(concatenate(getArg2(),"4"));
                }
            }
        });

        number5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(getOrder()) {
                    setArg1(concatenate(getArg1(),"5"));
                } else {
                    setArg2(concatenate(getArg2(),"5"));
                }
            }
        });

        number6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(getOrder()) {
                    setArg1(concatenate(getArg1(),"6"));
                } else {
                    setArg2(concatenate(getArg2(),"6"));
                }
            }
        });

        number7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(getOrder()) {
                    setArg1(concatenate(getArg1(),"7"));
                } else {
                    setArg2(concatenate(getArg2(),"7"));
                }
            }
        });

        number8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(getOrder()) {
                    setArg1(concatenate(getArg1(),"8"));
                } else {
                    setArg2(concatenate(getArg2(),"8"));
                }
            }
        });

        number9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(getOrder()) {
                    setArg1(concatenate(getArg1(),"9"));
                } else {
                    setArg2(concatenate(getArg2(),"9"));
                }
            }
        });

        // DOT BUTTON

        dotButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDot();
            }
        });

        // OPERATORS

        additionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setOperator("+");
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setOperator("-");
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setOperator("*");
            }
        });

        divisionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setOperator("/");
            }
        });

        // SUBMIT

        equalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

    }

    private String concatenate(String a, String b) {
        return String.format("%s%s", a,b);
    }

    private void setDot() {
        if(getOrder()) {
            setArgDot(true);
            this.arg1 = dotize(this.getArg1());
        } else {
            setArg2Dot(true);
            this.arg2 = dotize(this.getArg2());
        }
    }

    private String dotize(String arg) {
        return String.format("%s.", arg);
    }

    private boolean getOrder() {
        return this.argOrder;
    }

    private void setOrder(boolean order) {
        this.argOrder = order;
    }

    private String getArg1() {
        return this.arg1;
    }

    private String getArg2() {
        return this.arg2;
    }

    private String getOperator() {
        return this.operator;
    }

    private void setArg1(String arg) {
        this.arg1 = arg;
        updatePanel();
    }

    private void setArg2(String arg) {
        this.arg2 = arg;
        updatePanel();
    }

    private void setArgDot(boolean flag) {
        this.arg1Dot = flag;
    }

    private void setArg2Dot(boolean flag) {
        this.arg1Dot = flag;
    }

    private void setOperator(String operator) {
        this.operator = operator;
        setOrder(false);
        updatePanel();
    }

    private void calculate() {

        double arg1 = Double.parseDouble(this.getArg1());
        double arg2 = Double.parseDouble(this.getArg2());

        if(this.getOperator().equals("+")) {
            this.result = String.valueOf(Calculator.sum(arg1,arg2));
        }

        if(this.getOperator().equals("-")) {
            this.result = String.valueOf(Calculator.subtract(arg1,arg2));
        }

        if(this.getOperator().equals("*")) {
            this.result = String.valueOf(Calculator.multiply(arg1, arg2));
        }

        if(this.getOperator().equals("/")) {
            this.result = String.valueOf(Calculator.division(arg1, arg2));
        }

        if(this.result != null && !this.result.equals("")) {
            this.numberField.setText(this.result);
        }

    }

    private void updatePanel() {
        if(!this.argOrder) {
            this.numberField.setText(String.format("%s %s %s",getArg1(),getOperator(),getArg2()));
        } else {
            this.numberField.setText(getArg1());
        }
    }

    private void clearProperties() {
        this.arg1 = "";
        this.arg2 = "";
        this.result = "";
        this.argOrder = true;
        this.arg1Dot = false;
        this.arg2Dot = false;
        this.numberField.setText("");
    }

}

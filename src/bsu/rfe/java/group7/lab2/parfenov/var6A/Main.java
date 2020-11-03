package bsu.rfe.java.group7.lab2.parfenov.var6A;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Main extends JFrame {
    //размеры окна приложения
    private static final int WIDTH = 500;
    private static final int HEIGHT = 340;

    public double SUM = 0.0;

    // Текстовые поля для считывания значений переменных,
// как компоненты, совместно используемые в различных методах
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;

    // Текстовое поле для отображения результата,
// как компонент, совместно используемый в различных методах
    private JTextField textFieldResult;

    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();

    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;

    // Формула №1 для рассчёта
    public Double calculate1(Double x, Double y, Double z) {
        if (x == 0) {
            JOptionPane.showMessageDialog(Main.this,
                    "X не может равняться 0", " " +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }

        if (z == -1) {
            JOptionPane.showMessageDialog(Main.this,
                    "Z не может равняться -1", " " +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        return Math.pow((Math.cos(Math.pow(Math.E, y)) + Math.pow(Math.E, y * y) + Math.sqrt(1/x)), 0.25) /
                Math.pow((Math.cos(Math.PI * z * z * z) + Math.log((1 + z) * (1 + z))), Math.sin(y));
    }
    // Формула №2 для рассчёта
    public Double calculate2(Double x, Double y, Double z) {
        if (x == -1) {
            JOptionPane.showMessageDialog(Main.this,
                    "X не может равняться -1", "" +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        return (1 + Math.pow(x, z) + Math.log(y * y)) * (1 - Math.sin(y * z)) / Math.sqrt(x * x * x + 1);
    }

    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Main.this.formulaId = formulaId;

            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    // Конструктор класса
    public Main() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();

// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        hboxFormulaType.add(Box.createHorizontalGlue()); //добавление "клея"
//Создание радио-кнопок 1,2
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
//Установить выделенной 1-ую кнопку
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
//Задать рамку для коробки с помощью класса BorderFactory
      //  hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
// Создать область с полями ввода для X, Y, Z
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
       // hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(7));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(7));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(7));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(7));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(7));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());
// Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        //hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

// Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {

                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonSum = new JButton("M+");
        buttonSum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());

                    if (formulaId==1)
                        SUM += calculate1(x, y, z);
                    else
                        SUM += calculate2(x, y, z);
                    textFieldResult.setText(Double.toString(SUM));
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonClearSum = new JButton("MC");
        buttonClearSum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                SUM=0.0;
                textFieldResult.setText("0");
            }});
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(15));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalStrut(15));
        hboxButtons.add(buttonSum);
        hboxButtons.add(Box.createHorizontalStrut(15));
        hboxButtons.add(buttonClearSum);
        hboxButtons.add(Box.createHorizontalGlue());
        //hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

// Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Main frame = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
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
            JOptionPane.showMessageDialog(MainFrame.this,
                    "X не может равняться 0", " " +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }

        if (z == -1) {
            JOptionPane.showMessageDialog(MainFrame.this,
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
            JOptionPane.showMessageDialog(MainFrame.this,
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
                MainFrame.this.formulaId = formulaId;

            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }


    public static void main(String[] args) {
	// write your code here
    }
}

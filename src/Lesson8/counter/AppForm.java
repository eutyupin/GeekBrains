package Lesson8.counter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppForm extends JFrame {
   private final int COUNTER_INITIAL_VALUE = 0;
   private int counterValue;
   private Font font = new Font("Arial", Font.BOLD,36);
   private Font font2 = new Font("Arial", Font.BOLD,18);
   private Font font3 = new Font("Arial", Font.BOLD,14);
   private JLabel label = new JLabel();
   private JButton decrementButton = new JButton("<");
   private JButton incrementButton = new JButton(">");
   private JButton resetButton = new JButton("Reset");
   private JTextField stepField = new JTextField("1",2);

    public AppForm(){
        setTitle("Counter");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(350,150);
        setResizable(false);
        setVisible(true);
        setAlwaysOnTop(true);
        formFilling();
    }

    private void formFilling() {
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label,BorderLayout.CENTER);
        label.setText(String.valueOf(COUNTER_INITIAL_VALUE));
        counterValue = COUNTER_INITIAL_VALUE;

        decrementButton.setFont(font);
        add(decrementButton,BorderLayout.WEST);
        decrementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counterValue -= Integer.parseInt(stepField.getText());
                label.setText(String.valueOf(counterValue));
            }
        });

        incrementButton.setFont(font);
        add(incrementButton,BorderLayout.EAST);
        incrementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counterValue += Integer.parseInt(stepField.getText());
                label.setText(String.valueOf(counterValue));
            }
        });
        resetButton.setFont(font2);
        add(resetButton,BorderLayout.SOUTH);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counterValue = COUNTER_INITIAL_VALUE;
                label.setText(String.valueOf(counterValue));
            }
        });
        add(stepField, BorderLayout.NORTH);
        stepField.setHorizontalAlignment(SwingConstants.CENTER);

        stepField.setFont(font3);
    }
}

package com.branden;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by badams on 4/7/16.
 */
public class CCValidator extends JFrame{
    private JTextField creditCardNumberTextField;
    private JButton validateButton;
    private JLabel validMessageLabel;
    private JButton quitButton;
    private JPanel rootPanel;

    CCValidator(){
        super("Credit Card Validator");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cNumber = creditCardNumberTextField.getText();
                Boolean valid = isVisaCreditCardNumberValid(cNumber);
                if ( valid ){
                    validMessageLabel.setText("This is a valid credit card number");
                } else {
                    validMessageLabel.setText("This is not a valid credit card number");
                }
                pack();

            }

        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    private static boolean isVisaCreditCardNumberValid(String cc) {

        if (!cc.startsWith("4") || (cc.length() != 16)){
            System.out.println("Doesnt start with 4 or length wrong");
            return false;
        }

        int sum = 0;

        for (int i = 0; i < 16 ; i++ ) {
            int thisDigit = Integer.parseInt((cc.substring(i, i+1)));
            if (i % 2 == 1) {
                sum = sum + thisDigit;
            } else {
                int doubled = thisDigit * 2;
                if (doubled > 9 ) {
                    int toAdd = 1 + (doubled % 10);
                    sum = sum + toAdd;
                } else {
                    sum = sum + (thisDigit * 2);
                }
            }
        }

        if (sum % 10 == 0) {
            return true;
        }

        return false;

    }
}

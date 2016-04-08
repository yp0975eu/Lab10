package com.branden;

import com.sun.tools.javah.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PetSurvey extends JFrame{
    private JPanel rootPanel;
    private JCheckBox dogCheckBox;
    private JCheckBox catCheckBox;
    private JCheckBox fishCheckBox;
    private JButton submitButton;
    private JLabel surveyResultsLabel;
    private JButton quitButton;
    private Boolean hasDog = false;
    private Boolean hasCat = false;
    private Boolean hasFish= false;

    protected PetSurvey(){
        super("Pet Survey");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        fishCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                hasFish = fishCheckBox.isSelected();
            }
        });
        catCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                hasCat = catCheckBox.isSelected();
            }
        });
        dogCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                hasDog = dogCheckBox.isSelected();
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String dog = (hasDog) ? "a dog" : "no dogs";
                String cat = (hasCat) ? "a cat" : "no cat";
                String fish = (hasFish) ? "a fish" : "no fish";
                String surveyResults = "User has a" + dog +" and "+ cat + " and "+ fish;
                surveyResultsLabel.setText(surveyResults);
                // System.out.println("Dogs");
                pack();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quit = JOptionPane.showConfirmDialog(PetSurvey.this, "Are you sure you want to quit?","Quit", JOptionPane.OK_CANCEL_OPTION);

                if ( quit == JOptionPane.OK_OPTION ) System.exit(0);}
        });
    }
}
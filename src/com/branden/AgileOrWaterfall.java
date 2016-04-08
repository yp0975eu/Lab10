package com.branden;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by badams on 4/7/16.
 */
public class AgileOrWaterfall  extends  JFrame{
    int numProgrammers = 0;
    Boolean[]requirements = new Boolean[5];
    private JPanel rootPanel;
    private JTextField numberOfProgrammersTextField;
    private JLabel recommendationLabel;
    private JButton recommendButton;
    private JButton quitButton;
    private JTextField projectNameTextField;
    private JRadioButton deadlinesYesRadioButton;
    private JRadioButton deadlinesNoRadioButton;
    private JRadioButton programmersExperiencedYesRadioButton;
    private JRadioButton programmersExperiencedNoRadioButton;
    private JRadioButton programmersSameLocationYesRadioButton;
    private JRadioButton programmersSameLocationNoRadioButton;
    private JRadioButton stringentQualityYesRadioButton;
    private JRadioButton stringentQualityNoRadioButton;
    private JRadioButton workingModelsYesRadioButton;
    private JRadioButton workingModelsNoRadioButton;
    private JLabel numberFormatErrorLabel;
    Boolean error = false;
    AgileOrWaterfall() {
        super("Agile or Waterfall");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        recommendButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // get name of project
                String projectName = projectNameTextField.getText();

                // get number of programmers
                // clear error and recommendation text from previous entry
                numberFormatErrorLabel.setText("");

                try {
                    numProgrammers = Integer.parseInt( numberOfProgrammersTextField.getText() );
                    error = false;
                } catch (Exception ex){
                    error = true;
                    numberFormatErrorLabel.setText("You must enter a valid integer");

                }

                // get radio buttons and get requirements booleans
                requirements[0] = deadlinesYesRadioButton.isSelected();
                requirements[1] = programmersExperiencedYesRadioButton.isSelected();
                requirements[2] = programmersSameLocationYesRadioButton.isSelected();
                requirements[3] = stringentQualityYesRadioButton.isSelected();
                requirements[4] = workingModelsYesRadioButton.isSelected();


                // if there is an error then don't set recommendation label
                if ( error ){
                    recommendationLabel.setText("");
                }else {
                    String recommendation = agileOrWaterfall( numProgrammers,requirements);
                    recommendationLabel.setText(String.format("Project %s should use %s", projectName, recommendation));
                }
                // reset window size
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

    static String agileOrWaterfall( int numProgrammers, Boolean[] requirements ){
        String method;
        int agileVote = 0;
        int waterfallVote = 0;

        // if there is a large ( more than 4 ) team and they don't have experience then use waterfall
        if (  numProgrammers > 4 ){
            waterfallVote++;
        } else{
            agileVote++;
        }

        // if the team is on a firm deadline you can use waterfall
        if (  requirements[0] ){
            waterfallVote++;
        } else {
            agileVote++;
        }
        // if they don't have experience then you can budget for experts and specialists w ith waterfall
        if( requirements[1] ){
            waterfallVote++;
        } else {
            agileVote++;
        }
        // if not in same location then agile can be hard
        // From:  Slide 33, Java Programming - Week 3 - Methods and Program Design Process - Spring 2016
        if( requirements[2] ){
            agileVote++;
        } else {
            waterfallVote++;
        }
        // if there are stringent quality requirement then use waterfall
        if (  requirements[3] ){
            waterfallVote++;
        } else {
            agileVote++;
        }
        // if there are working models required  then use agile
        if (  requirements[4] ){
            agileVote++;
        } else {
            waterfallVote++;
        }


        if ( agileVote == waterfallVote){
            method = "either Agile or Waterfall";
        } else if ( agileVote > waterfallVote ){
            method = "Agile";
        } else {
            method = "Waterfall";
        }
        return method;
    }
}

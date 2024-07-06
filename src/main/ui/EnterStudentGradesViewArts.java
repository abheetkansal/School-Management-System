package ui;

import model.School;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the window to enter grades for arts students in the Indian High School by the professor
public class EnterStudentGradesViewArts extends JPanel {

    private static final String JSON_STORE = "./data/school.json";
    private School school;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: constructs an object with school and id as fields
    public EnterStudentGradesViewArts(School s1, int i) {

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        school = s1;

        JFrame frame = new JFrame("Students Grade Register");
        frameSetter(frame);

        JPanel panel = new JPanel();

        frame.setVisible(true);

        panel.setLayout(new GridLayout(8, 2));
        panel.setBackground(new Color(229, 98, 46));

        panel.setBorder(new EmptyBorder(new Insets(100, 50, 25, 50)));

        JLabel mathGrade = new JLabel("Enter Math Grade: ");
        JTextField mathField = gradeSetter(panel, mathGrade);

        JLabel hisGrade = new JLabel("Enter History Grade: ");
        JTextField hisField = gradeSetter(panel, hisGrade);

        JLabel civicsGrade = new JLabel("Enter Civics Grade: ");
        JTextField civicsField = gradeSetter(panel, civicsGrade);

        JLabel engGrade = new JLabel("Enter English Grade: ");
        JTextField engField = gradeSetter(panel, engGrade);

        JLabel geoGrade = new JLabel("Enter Geography Grade: ");
        JTextField geoField = gradeSetter(panel, geoGrade);

        JButton addGrades = new JButton("Add Grades");
        JButton exitButton = new JButton("Exit Button");


        setNActions(s1, i, frame, panel, mathField, hisField, civicsField, engField, geoField, addGrades, exitButton);

    }

    // EFFECTS: sets the action and reduces size
    public void setNActions(School s1, int i, JFrame frame, JPanel panel, JTextField mathField,
                            JTextField hisField, JTextField civicsField, JTextField engField, JTextField geoField,
                            JButton addGrades, JButton exitButton) {
        setterAndAlign(frame, panel, addGrades, exitButton);

        action(s1, i, frame, mathField, hisField, civicsField, engField, geoField, addGrades, exitButton);
    }


    // EFFECTS: specificaton of buttons
    public void action(School s1, int i, JFrame frame, JTextField mathField, JTextField hisField,
                       JTextField civicsField, JTextField engField, JTextField geoField, JButton addGrades,
                       JButton exitButton) {
        addGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s1.getGrades().get(i).setMath(Integer.parseInt(mathField.getText()));
                s1.getGrades().get(i).setMath(Integer.parseInt(hisField.getText()));
                s1.getGrades().get(i).setMath(Integer.parseInt(civicsField.getText()));
                s1.getGrades().get(i).setMath(Integer.parseInt(engField.getText()));
                s1.getGrades().get(i).setMath(Integer.parseInt(geoField.getText()));
                saveSchool();
            }
        });


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new StartScreen();
            }
        });
    }

    // EFFECTS: sets panel and aligns buttons
    public void setterAndAlign(JFrame frame, JPanel panel, JButton addGrades, JButton exitButton) {
        addGrades.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(addGrades);
        panel.add(exitButton);

        frame.add(panel);
    }

    // EFFECTS: sets the grades input
    public JTextField gradeSetter(JPanel panel, JLabel mathGrade) {
        JTextField mathField = new JTextField();
        panel.add(mathGrade);
        panel.add(mathField);
        return mathField;
    }

    // EFFECTS: sets the frame
    public void frameSetter(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 2000);
        frame.setBackground(Color.ORANGE);
    }

    // EFFECTS: saves the workroom to file
    private void saveSchool() {
        try {
            jsonWriter.open();
            jsonWriter.write(school);
            jsonWriter.close();
            System.out.println("Saved Indian High School data to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadSchool() {
        try {
            school = jsonReader.read();
            System.out.println("Loaded Indian High School data from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}



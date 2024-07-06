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

// Represents the window to enter grades for commerce students in the Indian High School by the professor
public class EnterStudentGradesViewScience extends JPanel {

    private static final String JSON_STORE = "./data/school.json";
    private School school;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs an object with school and id as fields
    public EnterStudentGradesViewScience(School s1, int i) {

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        school = s1;

        JFrame frame = new JFrame("Students Grade Register");
        frameSetter(frame);

        JPanel panel = new JPanel();


        panel.setLayout(new GridLayout(8, 2));
        panel.setBackground(new Color(229, 98, 46));

        panel.setBorder(new EmptyBorder(new Insets(100, 50, 25, 50)));

        JLabel mathGrade = new JLabel("Enter Math Grade: ");
        JTextField mathField = new JTextField();

        JLabel chemGrade = new JLabel("Enter Chemistry Grade: ");
        JTextField chemField = new JTextField();

        JLabel phyGrade = new JLabel("Enter Physics Grade: ");
        JTextField phyField = new JTextField();

        JLabel engGrade = new JLabel("Enter English Grade: ");
        JTextField engField = new JTextField();

        JLabel csGrade = new JLabel("Enter Computer Science Grade: ");
        JTextField csField = new JTextField();


        JButton addGrades = new JButton("Add Grades");
        JButton exitButton = new JButton("Exit Button");

        reducer(s1, i, frame, panel, mathField, chemGrade, chemField, phyGrade, phyField, engGrade, engField, csGrade,
                csField, addGrades, exitButton);

    }

    // EFFECTS: used to reduce length of code and is multi functional
    public void reducer(School s1, int i, JFrame frame, JPanel panel, JTextField mathField, JLabel chemGrade,
                        JTextField chemField, JLabel phyGrade, JTextField phyField, JLabel engGrade,
                        JTextField engField, JLabel csGrade, JTextField csField, JButton addGrades,
                        JButton exitButton) {
        gradeAdd(panel, csGrade, csField);
        gradeAdd(panel, phyGrade, phyField);
        gradeAdd(panel, phyGrade, phyField);
        gradeAdd(panel, engGrade, engField);
        gradeAdd(panel, chemGrade, chemField);
        addAndAlign(frame, panel, addGrades, exitButton);

        action(s1, i, frame, mathField, chemField, phyField, engField, csField, addGrades, exitButton);
    }

    // EFFECTS: houses the buttons and their actions when used
    public void action(School s1, int i, JFrame frame, JTextField mathField, JTextField chemField, JTextField phyField,
                       JTextField engField, JTextField csField, JButton addGrades, JButton exitButton) {
        addGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s1.getGrades().get(i).setMath(Integer.parseInt(mathField.getText()));
                s1.getGrades().get(i).setMath(Integer.parseInt(chemField.getText()));
                s1.getGrades().get(i).setMath(Integer.parseInt(phyField.getText()));
                s1.getGrades().get(i).setMath(Integer.parseInt(engField.getText()));
                s1.getGrades().get(i).setMath(Integer.parseInt(csField.getText()));
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

    // EFFECTS: aligns the buttons and adds them
    public void addAndAlign(JFrame frame, JPanel panel, JButton addGrades, JButton exitButton) {
        addGrades.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(addGrades);
        panel.add(exitButton);

        frame.add(panel);
    }

    // EFFECTS: sets properties for a frame
    public void frameSetter(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 2000);
        frame.setBackground(Color.ORANGE);
        frame.setVisible(true);
    }

    // EFFECTS: adds particular grades
    public void gradeAdd(JPanel panel, JLabel csGrade, JTextField csField) {
        panel.add(csGrade);
        panel.add(csField);
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

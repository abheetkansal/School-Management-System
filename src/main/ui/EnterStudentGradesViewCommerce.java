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
public class EnterStudentGradesViewCommerce extends JPanel {

    private static final String JSON_STORE = "./data/school.json";
    private School school;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: constructs an object with school and id as fields
    public EnterStudentGradesViewCommerce(School s1, int i) {

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

        JLabel accGrade = new JLabel("Enter Accountancy Grade: ");
        JTextField accField = new JTextField();

        JLabel bstGrade = new JLabel("Enter Business Studies Grade: ");
        JTextField bstField = new JTextField();

        JLabel engGrade = new JLabel("Enter English Grade: ");
        JTextField engField = new JTextField();

        JLabel ecoGrade = new JLabel("Enter Economics Grade: ");
        JTextField ecoField = new JTextField();


        JButton addGrades = new JButton("Add Grades");
        JButton exitButton = new JButton("Exit Button");

        everyAdds(s1, i, frame, panel, mathGrade, mathField, accGrade, accField, bstGrade, bstField, engGrade, engField,
                ecoGrade, ecoField, addGrades, exitButton);

    }

    // EFFECTS: adds grades for a particular subject
    public void everyAdds(School s1, int i, JFrame frame, JPanel panel, JLabel mathGrade, JTextField mathField,
                          JLabel accGrade, JTextField accField, JLabel bstGrade, JTextField bstField, JLabel engGrade,
                          JTextField engField, JLabel ecoGrade, JTextField ecoField, JButton addGrades,
                          JButton exitButton) {
        gradeAdder(panel, ecoGrade, ecoField);
        gradeAdder(panel, mathGrade, mathField);
        gradeAdder(panel, engGrade, engField);
        gradeAdder(panel, accGrade, accField);
        gradeAdder(panel, bstGrade, bstField);
        panelAlign(frame, panel, addGrades, exitButton);

        action(s1, i, frame, mathField, accField, bstField, engField, ecoField, addGrades, exitButton);
    }

    // EFFECTS: sets various properties of frame
    public void frameSetter(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 2000);
        frame.setBackground(Color.ORANGE);
        frame.setVisible(true);
    }

    // EFFECTS: adds grades to the particular subject
    public void gradeAdder(JPanel panel, JLabel ecoGrade, JTextField ecoField) {
        panel.add(ecoGrade);
        panel.add(ecoField);
    }

    // EFFECTS: aligns button and adds them to panel
    public void panelAlign(JFrame frame, JPanel panel, JButton addGrades, JButton exitButton) {
        addGrades.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(addGrades);
        panel.add(exitButton);

        frame.add(panel);
    }

    // EFFECTS: actions performed on use of various buttons
    public void action(School s1, int i, JFrame frame, JTextField mathField,
                       JTextField accField, JTextField bstField, JTextField engField, JTextField ecoField,
                       JButton addGrades, JButton exitButton) {
        addGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s1.getGrades().get(i).setMath(Integer.parseInt(mathField.getText()));
                s1.getGrades().get(i).setMath(Integer.parseInt(accField.getText()));
                s1.getGrades().get(i).setMath(Integer.parseInt(bstField.getText()));
                s1.getGrades().get(i).setMath(Integer.parseInt(engField.getText()));
                s1.getGrades().get(i).setMath(Integer.parseInt(ecoField.getText()));
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



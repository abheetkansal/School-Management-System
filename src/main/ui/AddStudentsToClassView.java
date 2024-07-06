package ui;

import model.School;
import model.Student;
import model.Transcript;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the window to add students to the Indian High School
public class AddStudentsToClassView extends JPanel {

    private static final String JSON_STORE = "./data/school.json";
    private School school;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: constructs an object with school field
    public AddStudentsToClassView(School s1) {

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        school = s1;
        JFrame frame = new JFrame("Add Students To Class");
        frameSetter(frame);

        JPanel panel = new JPanel();


        panel.setLayout(new GridLayout(6, 2));
        panel.setBackground(new Color(229, 98, 46));

        panel.setBorder(new EmptyBorder(new Insets(100, 50, 25, 50)));

        JLabel studID = new JLabel("Enter Student ID: ");
        JTextField studIDField = new JTextField();
        panelAdder(panel, studID, studIDField);


        JLabel name = new JLabel("Enter Student Name: ");
        JTextField nameField = new JTextField();

        JLabel studFaculty = new JLabel("Enter Student Faculty: ");

        String[] destinationChoices = {"-- Faculty -- ", "Science", "Commerce", "Arts"};
        JComboBox<String> studFacultyBox = new JComboBox<String>(destinationChoices);


        JButton addStudent = new JButton("Add Student");
        JButton exitButton = new JButton("Exit Button");

        setsAndGets(s1, frame, panel, studID, name, nameField, studFaculty, studFacultyBox, addStudent, exitButton);
    }

    // EFFECTS: setters and getters for appropriate fields
    public void setsAndGets(School s1, JFrame frame, JPanel panel, JLabel studID,
                            JLabel name, JTextField nameField, JLabel studFaculty, JComboBox<String> studFacultyBox,
                            JButton addStudent, JButton exitButton) {
        panelAndAlign(frame, panel, addStudent, exitButton);
        frame.setVisible(true);
        actionButtonsClass(s1, frame, studID, name, studFacultyBox, addStudent, exitButton);
        panelAdder(panel, name, nameField);
        facultySetter(panel, studFaculty);
        comboSetter(panel, studFacultyBox);
    }

    // EFFECTS: sets the required combo option for setting the field
    public void comboSetter(JPanel panel, JComboBox<String> studFacultyBox) {
        studFacultyBox.setVisible(true);
        panel.add(studFacultyBox);
    }

    // EFFECTS: adds the Panel and Aligns the buttons accordingly
    public void panelAndAlign(JFrame frame, JPanel panel, JButton addStudent, JButton exitButton) {
        addStudent.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(addStudent);
        panel.add(exitButton);

        frame.add(panel);
    }

    // EFFECTS: sets the faculty
    public void facultySetter(JPanel panel, JLabel studFaculty) {
        studFaculty.setVisible(true);
        panel.add(studFaculty);
    }

    // EFFECTS: actions on various buttons specifications
    public void actionButtonsClass(School s1, JFrame frame, JLabel studID, JLabel name,
                                   JComboBox<String> studFacultyBox, JButton addStudent, JButton exitButton) {
        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String faculty = (String) studFacultyBox.getSelectedItem();
                s1.addStudent(new Student(Integer.parseInt(studID.getText()), name.getText(), faculty));
                s1.getGrades().add(new Transcript((Integer.parseInt(studID.getText()))));

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

    // EFFECTS: adds appropriate fields to panel
    public void panelAdder(JPanel panel, JLabel studID, JTextField studIDField) {
        panel.add(studID);
        panel.add(studIDField);
    }

    // EFFECTS: sets frame properties
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

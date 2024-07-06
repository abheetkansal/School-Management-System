package ui;

import model.Professor;
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

// Represents the window to add professors to the Indian High School by the principal
public class AddProfessorView extends JPanel {

    private static final String JSON_STORE = "./data/school.json";
    private School school;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs an object with the school field
    public AddProfessorView(School s1) {

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        school = s1;
        JFrame frame = new JFrame("Add Professor To School");
        frameSetter(frame);

        JPanel panel = new JPanel();

        frame.setVisible(true);

        panelSetter(panel);

        JLabel profID = new JLabel("Enter Professor ID: ");
        JTextField profIDField = new JTextField();
        profIDAdder(panel, profID, profIDField);

        JLabel name = new JLabel("Enter Professor Name: ");
        JTextField nameField = new JTextField();
        profIDAdder(panel, name, nameField);
        JLabel profSalaryLabel = new JLabel("Enter Professor Total Salary: ");
        JTextField profSalary = new JTextField();
        profIDAdder(panel, profSalaryLabel, profSalary);
        JButton addProfessorButton = new JButton("Add Professor");
        JButton exitButton = new JButton("Exit Button");
        adder(panel, addProfessorButton, exitButton);
        frame.add(panel);
        actionButtonArea(s1, frame, profID, name, profSalary, addProfessorButton, exitButton);
    }

    // EFFECTS: sets format properties of a frame
    public void frameSetter(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 2000);
        frame.setBackground(Color.ORANGE);
    }

    // EFFECTS: sets panel properties like border, layout, background
    public void panelSetter(JPanel panel) {
        panel.setLayout(new GridLayout(6, 2));
        panel.setBackground(new Color(229, 98, 46));

        panel.setBorder(new EmptyBorder(new Insets(100, 50, 25, 50)));
    }


    // EFFECTS: adds id of professor to panel
    public void profIDAdder(JPanel panel, JLabel profID, JTextField profIDField) {
        panel.add(profID);
        panel.add(profIDField);
    }


    // EFFECTS: adds buttons to panel
    public void adder(JPanel panel, JButton addProfessorButton, JButton exitButton) {
        addProfessorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(addProfessorButton);
        panel.add(exitButton);
    }

    // EFFECTS: buttons actions are performed in appropriate areas
    public void actionButtonArea(School s1, JFrame frame, JLabel profID, JLabel name, JTextField profSalary,
                                 JButton addProfessorButton, JButton exitButton) {
        addProfessorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s1.addProfessor(new Professor(Integer.parseInt(profID.getText()), name.getText(),
                        Integer.parseInt(profSalary.getText())));
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

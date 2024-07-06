package ui;


import exception.NoBalanceException;
import model.School;
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

// Represents the window with a list of actions undertaken by the professors of Indian High School
public class ProfessorView extends JPanel {

    private static final String JSON_STORE = "./data/school.json";
    private School school;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: constructs an object with school as its fields
    public ProfessorView(School s1) {

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        school = s1;
        JFrame frame = new JFrame("Professor View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setBackground(Color.YELLOW);

        JPanel panel = new JPanel();

        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

        panel.setLayout(boxlayout);
        panel.setBackground(new Color(229, 98, 46));

        panel.setBorder(new EmptyBorder(new Insets(100, 50, 100, 50)));

        JLabel txt = new JLabel(("Select operation you want to perform"));
        JButton addStudentsToClassButton = new JButton("Add Students to Class");
        JButton viewTotalSalaryButton = new JButton("View Total Salary");
        JButton collectSalaryButton = new JButton("Collect Salary");
        JButton enterStudentGradesButton = new JButton("Enter Student Grades");
        JButton exitButton = new JButton("Exit");


        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        fastMaker(s1, frame, panel, txt, addStudentsToClassButton, viewTotalSalaryButton, collectSalaryButton,
                enterStudentGradesButton, exitButton);
    }

    // EFFECTS: makes the programme method short
    public void fastMaker(School s1, JFrame frame, JPanel panel, JLabel txt, JButton addStudentsToClassButton,
                          JButton viewTotalSalaryButton, JButton collectSalaryButton, JButton enterStudentGradesButton,
                          JButton exitButton) {
        alignSet(txt, addStudentsToClassButton, viewTotalSalaryButton, collectSalaryButton, enterStudentGradesButton,
                exitButton);

        panAdder(panel, txt, addStudentsToClassButton, viewTotalSalaryButton, collectSalaryButton,
                enterStudentGradesButton, exitButton);

        action(s1, frame, addStudentsToClassButton, viewTotalSalaryButton, collectSalaryButton,
                enterStudentGradesButton, exitButton);
    }

    // EFFECTS: adds properties for the current panel
    public void panAdder(JPanel panel, JLabel txt, JButton addStudentsToClassButton, JButton viewTotalSalaryButton,
                         JButton collectSalaryButton, JButton enterStudentGradesButton, JButton exitButton) {
        panel.add(txt);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(addStudentsToClassButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(viewTotalSalaryButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(collectSalaryButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(enterStudentGradesButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(exitButton);
    }

    // EFFECTS: sets the alignments for buttons on screen
    public void alignSet(JLabel txt, JButton addStudentsToClassButton, JButton viewTotalSalaryButton,
                         JButton collectSalaryButton, JButton enterStudentGradesButton, JButton exitButton) {
        txt.setAlignmentX(CENTER_ALIGNMENT);
        addStudentsToClassButton.setAlignmentX(CENTER_ALIGNMENT);
        viewTotalSalaryButton.setAlignmentX(CENTER_ALIGNMENT);
        collectSalaryButton.setAlignmentX(CENTER_ALIGNMENT);
        enterStudentGradesButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
    }

    // EFFECTS: actions performed when certain buttons are pressed in the mainframe
    public void action(School s1, JFrame frame, JButton addStudentsToClassButton, JButton viewTotalSalaryButton,
                       JButton collectSalaryButton, JButton enterStudentGradesButton, JButton exitButton) {
        studentAddButtonAre(s1, frame, addStudentsToClassButton);

        viewTotalSalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Your current salary is: "
                        + s1.getProfessors().get(s1.getMemory()).getTotalSalary());
            }
        });

        collectSalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, salarySender(s1.getMemory()));
            }
        });

        gradeEntry(s1, frame, enterStudentGradesButton);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                s1.setMemory(0);
                saveSchool();
            }
        });
    }

    // EFFECTS: specification for the add student button
    public void studentAddButtonAre(School s1, JFrame frame, JButton addStudentsToClassButton) {
        addStudentsToClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                new AddStudentsToClassView(s1);
            }
        });
    }

    // EFFECTS: enters the grade for student of that particular faculty
    public void gradeEntry(School s1, JFrame frame, JButton enterStudentGradesButton) {
        enterStudentGradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                int sid = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Student ID"));
                for (Transcript t : s1.getGrades()) {
                    if (t.getStudentID() == sid) {
                        for (int k = 0; k < s1.getStudents().size(); k++) {
                            if (s1.getStudents().get(k).getStudentID() == sid) {
                                if (s1.getStudents().get(k).getFaculty().equals("Science")) {
                                    new EnterStudentGradesViewScience(s1, sid);
                                } else if (s1.getStudents()
                                        .get(k).getFaculty().equals("Commerce")) {
                                    new EnterStudentGradesViewCommerce(s1, sid);
                                } else {
                                    new EnterStudentGradesViewArts(s1, sid);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    //EFFECTS: sends the current salary of professor from the school's funds to professor
    public String salarySender(int i) {
        school.getProfessors().get(i)
                .sendSalary(school.getProfessors().get(i).getTotalSalary());
        try {
            school.reduceMoneySpent(school.getProfessors().get(i).getTotalSalary());
        } catch (NoBalanceException e) {
            JOptionPane.showMessageDialog(null, "No Balance");
        }
        return ("Salary Sent");
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

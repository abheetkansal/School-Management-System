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

// Represents the window with a list of actions undertaken by the students of Indian High School
public class StudentView extends JPanel {

    private static final String JSON_STORE = "./data/school.json";
    private School school;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    public (School s1) {

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        school = s1;
        JFrame frame = new JFrame("Student View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setBackground(Color.YELLOW);

        JPanel panel = new JPanel();

        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

        panel.setLayout(boxlayout);
        panel.setBackground(new Color(229, 98, 46));

        panel.setBorder(new EmptyBorder(new Insets(100, 50, 100, 50)));

        JLabel txt = new JLabel(("Select operation you want to perform"));
        JButton payTuitionButton = new JButton("Pay Tuition");
        JButton viewTotalTuitionButton = new JButton("View Total Tuition");
        JButton viewTranscriptButton = new JButton("View Transcript");
        JButton calculateMyAverageButton = new JButton("Calculate my Average");
        JButton exitButton = new JButton("Exit");

        reducer(s1, frame, panel, txt, payTuitionButton, viewTotalTuitionButton, viewTranscriptButton,
                calculateMyAverageButton, exitButton);
    }

    public void reducer(School s1, JFrame frame, JPanel panel, JLabel txt, JButton payTuitionButton,
                        JButton viewTotalTuitionButton, JButton viewTranscriptButton, JButton calculateMyAverageButton,
                        JButton exitButton) {
        aligner(txt, payTuitionButton, viewTotalTuitionButton, viewTranscriptButton, calculateMyAverageButton,
                exitButton);

        paneAndFrameSetter(frame, panel, txt, payTuitionButton, viewTotalTuitionButton, viewTranscriptButton,
                calculateMyAverageButton, exitButton);

        action(s1, frame, payTuitionButton, viewTotalTuitionButton, viewTranscriptButton, calculateMyAverageButton,
                exitButton);
    }

    public void paneAndFrameSetter(JFrame frame, JPanel panel, JLabel txt, JButton payTuitionButton, JButton
            viewTotalTuitionButton, JButton viewTranscriptButton, JButton calculateMyAverageButton,
                                   JButton exitButton) {
        panel.add(txt);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(payTuitionButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(viewTotalTuitionButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(viewTranscriptButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(calculateMyAverageButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(exitButton);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void aligner(JLabel txt, JButton payTuitionButton, JButton viewTotalTuitionButton,
                        JButton viewTranscriptButton, JButton calculateMyAverageButton, JButton exitButton) {
        txt.setAlignmentX(CENTER_ALIGNMENT);
        payTuitionButton.setAlignmentX(CENTER_ALIGNMENT);
        viewTotalTuitionButton.setAlignmentX(CENTER_ALIGNMENT);
        viewTranscriptButton.setAlignmentX(CENTER_ALIGNMENT);
        calculateMyAverageButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
    }

    public void action(School s1, JFrame frame, JButton payTuitionButton, JButton viewTotalTuitionButton,
                       JButton viewTranscriptButton, JButton calculateMyAverageButton, JButton exitButton) {
        payTuitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int payAmount = Integer.parseInt(JOptionPane.showInputDialog(frame,
                        "Enter the amount yo want to pay "));
                JOptionPane.showMessageDialog(frame, tuitionPayer(s1.getMemory(), payAmount));

            }
        });

        viewTotalTuitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, tuitionViewer(s1.getMemory()));
            }
        });

        transcriptAndCalculate(s1, frame, viewTranscriptButton, calculateMyAverageButton);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                s1.setMemory(0);
                saveSchool();
            }
        });
    }

    public void transcriptAndCalculate(School s1, JFrame frame, JButton viewTranscriptButton,
                                       JButton calculateMyAverageButton) {
        viewTranscriptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < s1.getStudents().size(); i++) {
                    if (s1.getStudents().get(i).getStudentID() == s1.getMemory()) {
                        JOptionPane.showMessageDialog(frame, transcriptViewer(i));
                    }
                }
            }
        });

        calculateMyAverageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < s1.getStudents().size(); i++) {
                    if (s1.getStudents().get(i).getStudentID() == s1.getMemory()) {
                        JOptionPane.showMessageDialog(frame, averageViewer(i));
                    }
                }
            }
        });
    }

    //EFFECTS: views the average grade of student in a particular faculty
    public String averageViewer(int i) {
        return ("Your current average: " + school.getGrades().get(i)
                .getAverage());
    }

    //EFFECTS: views the total tuition fee of student
    public String tuitionViewer(int i) {
        return ("Your Total Tuition Fee is " + school.getStudents().get(i).getTotalTuition());
    }

    //EFFECTS: shows the complete student transcript based on his faculty
    public String transcriptViewer(int i) {
        if (school.getStudents().get(i).getFaculty().equals("Science")) {
            return (printScienceTranscript(i));
        } else if (school.getStudents().get(i).getFaculty().equals("Commerce")) {
            return (printCommerceTranscript(i));
        } else {
            return (printArtsTranscript(i));
        }
    }

    //EFFECTS: prints the transcript of students in Commerce
    public String printCommerceTranscript(int i) {
        return ("The student transcript is as follows \n"
                + "Math " + school.getGrades().get(i).getMathGrade() + "\n"
                + "Business Studies " + school.getGrades().get(i).getBusinessStudiesGrade() + "\n"
                + "Accountancy " + school.getGrades().get(i).getAccountancyGrade() + "\n"
                + "English " + school.getGrades().get(i).getEnglishGrade() + "\n"
                + "Economics " + school.getGrades().get(i).getEconomicsGrade());
    }

    //EFFECTS: prints the transcript of students in Science
    public String printScienceTranscript(int i) {
        return ("The student transcript is as follows \n"
                + "Math " + school.getGrades().get(i).getMathGrade() + "\n"
                + "Physics " + school.getGrades().get(i).getPhysicsGrade() + "\n"
                + "Chemistry " + school.getGrades().get(i).getChemistryGrade() + "\n"
                + "English " + school.getGrades().get(i).getEnglishGrade() + "\n"
                + "Computer Science " + school.getGrades().get(i).getComputerScienceGrade());
    }

    //EFFECTS: prints the transcript of students in Arts
    public String printArtsTranscript(int i) {
        return ("The student transcript is as follows \n"
                + "Math " + school.getGrades().get(i).getMathGrade() + "\n"
                + "History " + school.getGrades().get(i).getHistoryGrade() + "\n"
                + "Civics " + school.getGrades().get(i).getCivicsGrade() + "\n"
                + "English " + school.getGrades().get(i).getEnglishGrade() + "\n"
                + "Geography " + school.getGrades().get(i).getGeographyGrade());
    }

    //EFFECTS: pays the tuition fee of student and adds to school funds the money received
    public String tuitionPayer(int i, int tuition) {
        school.getStudents().get(i).payTuition(tuition);
        school.addMoneyReceived(tuition);
        return ("Your remaining tuition fee is : " + school.getStudents().get(i)
                .getPendingTuition());
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

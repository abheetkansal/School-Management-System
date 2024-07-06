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

// Represents the window with a list of actions undertaken by the principal of Indian High School
public class PrincipalView extends JPanel {

    private static final String JSON_STORE = "./data/school.json";
    private School school;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;


    // EFFECTS: constructs an object with school as its field
    public PrincipalView(School s1) {

        school = s1;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        JFrame frame = new JFrame("Principal View");


        JPanel panel = new JPanel();

        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

        frameAndPanelLayout(frame, panel, boxlayout);
        panel.setBackground(new Color(229, 98, 46));

        panel.setBorder(new EmptyBorder(new Insets(100, 50, 100, 50)));

        JLabel txt = new JLabel(("Select operation you want to perform"));
        JButton loadDataButton = new JButton("Load Data");
        JButton saveDataButton = new JButton("Save Data");
        JButton addStudentButton = new JButton("Add Student");
        JButton addProfessorButton = new JButton("Add Professor");
        JButton viewStudentTranscriptButton = new JButton("View Student Transcript");
        JButton viewSchoolBankBalanceButton = new JButton("View School's Bank Balance");
        JButton viewSchoolSpendingsButton = new JButton("View School's Spendings");
        JButton viewStudentList = new JButton("View Student List");
        JButton viewProfessorList = new JButton("View Professor List");
        JButton exitButton = new JButton("Exit");


        shortener(s1, frame, panel, txt, loadDataButton, saveDataButton, addStudentButton, addProfessorButton,
                viewStudentTranscriptButton, viewSchoolBankBalanceButton, viewSchoolSpendingsButton, viewStudentList,
                viewProfessorList, exitButton);
    }

    // EFFECTS: shortens the code length with multi functional use
    public void shortener(School s1, JFrame frame, JPanel panel, JLabel txt, JButton loadDataButton,
                          JButton saveDataButton, JButton addStudentButton, JButton addProfessorButton,
                          JButton viewStudentTranscriptButton, JButton viewSchoolBankBalanceButton,
                          JButton viewSchoolSpendingsButton, JButton viewStudentList, JButton viewProfessorList,
                          JButton exitButton) {
        panelFrameAdder(frame, panel, exitButton);

        reducer(s1, frame, panel, txt, loadDataButton, saveDataButton, addStudentButton, addProfessorButton,
                viewStudentTranscriptButton, viewSchoolBankBalanceButton, viewSchoolSpendingsButton, viewStudentList,
                viewProfessorList, exitButton);
    }

    // EFFECTS: sets frame and panel layout
    public void frameAndPanelLayout(JFrame frame, JPanel panel, BoxLayout boxlayout) {
        frameSetter(frame);
        panel.setLayout(boxlayout);
    }

    // EFFECTS: sets formatting for frame
    public void frameSetter(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setBackground(Color.YELLOW);
    }

    // EFFECTS: reduces the size of method
    public void reducer(School s1, JFrame frame, JPanel panel, JLabel txt, JButton loadDataButton,
                        JButton saveDataButton, JButton addStudentButton, JButton addProfessorButton,
                        JButton viewStudentTranscriptButton, JButton viewSchoolBankBalanceButton,
                        JButton viewSchoolSpendingsButton, JButton viewStudentList, JButton viewProfessorList,
                        JButton exitButton) {
        aligner(txt, loadDataButton, saveDataButton, addStudentButton, addProfessorButton, viewStudentTranscriptButton,
                viewSchoolBankBalanceButton, viewSchoolSpendingsButton, viewStudentList, viewProfessorList, exitButton);

        panelAdder(panel, txt, loadDataButton, saveDataButton, addStudentButton, addProfessorButton,
                viewStudentTranscriptButton, viewSchoolBankBalanceButton, viewSchoolSpendingsButton, viewStudentList,
                viewProfessorList);

        action(s1, frame, loadDataButton, saveDataButton, addStudentButton, addProfessorButton,
                viewStudentTranscriptButton, viewSchoolBankBalanceButton, viewSchoolSpendingsButton, viewStudentList,
                viewProfessorList, exitButton);
    }

    // EFFECTS: adds buttons to panel and sets properties for frame
    public void panelFrameAdder(JFrame frame, JPanel panel, JButton exitButton) {
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(exitButton);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: houses the actions on press off the buttons on screen
    public void action(School s1, JFrame frame, JButton loadDataButton, JButton saveDataButton,
                       JButton addStudentButton, JButton addProfessorButton, JButton viewStudentTranscriptButton,
                       JButton viewSchoolBankBalanceButton, JButton viewSchoolSpendingsButton, JButton viewStudentList,
                       JButton viewProfessorList, JButton exitButton) {
        fileButtons(frame, loadDataButton, saveDataButton);

        addItems(s1, frame, addStudentButton, addProfessorButton);

        listViewers(s1, frame, viewStudentList, viewProfessorList);

        viewStudentTranscriptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sid = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Student ID"));
                for (int i = 0; i < s1.getStudents().size(); i++) {
                    if (s1.getStudents().get(i).getStudentID() == sid) {
                        JOptionPane.showMessageDialog(frame, transcriptViewer(i));
                    }
                }
            }
        });

        bankSchoolData(s1, frame, viewSchoolBankBalanceButton, viewSchoolSpendingsButton);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                s1.setMemory(0);
            }
        });
    }

    // EFFECTS: views the data about school's bank account on the particular button pressed
    public void bankSchoolData(School s1, JFrame frame, JButton viewSchoolBankBalanceButton,
                               JButton viewSchoolSpendingsButton) {
        viewSchoolBankBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "The school's current bank balance: " + s1.getBalance());
            }
        });

        viewSchoolSpendingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "The school's current spendings: " + s1.getSpendings());
            }
        });
    }

    // EFFECTS: view the student and professor list on the press of required button
    public void listViewers(School s1, JFrame frame, JButton viewStudentList, JButton viewProfessorList) {
        viewProfessorList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new ViewProfessorList(s1);
            }
        });

        viewStudentList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new ViewStudentList(s1);
            }
        });
    }

    // EFFECTS: adds items to the list of students and professors when button is pressed
    public void addItems(School s1, JFrame frame, JButton addStudentButton, JButton addProfessorButton) {
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new AddStudentsToClassView(s1);
            }
        });

        addProfessorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new AddProfessorView(s1);
            }
        });
    }

    // EFFECTS: actions performed based on the file
    public void fileButtons(JFrame frame, JButton loadDataButton, JButton saveDataButton) {
        loadDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSchool();
                JOptionPane.showMessageDialog(frame, "Data Loaded Successfully");
            }
        });

        saveDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSchool();
                JOptionPane.showMessageDialog(frame, "Data Saved Successfully");
            }
        });
    }

    // EFFECTS: adds required field to the panel
    public void panelAdder(JPanel panel, JLabel txt, JButton loadDataButton, JButton saveDataButton,
                           JButton addStudentButton, JButton addProfessorButton, JButton viewStudentTranscriptButton,
                           JButton viewSchoolBankBalanceButton, JButton viewSchoolSpendingsButton,
                           JButton viewStudentList, JButton viewProfessorList) {
        panel.add(txt);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(loadDataButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(saveDataButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(addStudentButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(addProfessorButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(viewStudentTranscriptButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(viewSchoolBankBalanceButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(viewSchoolSpendingsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(viewStudentList);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(viewProfessorList);
    }

    // EFFECTS: aligns the button on the screen appropriately
    public void aligner(JLabel txt, JButton loadDataButton, JButton saveDataButton, JButton addStudentButton,
                        JButton addProfessorButton, JButton viewStudentTranscriptButton,
                        JButton viewSchoolBankBalanceButton, JButton viewSchoolSpendingsButton, JButton viewStudentList,
                        JButton viewProfessorList, JButton exitButton) {
        txt.setAlignmentX(CENTER_ALIGNMENT);
        loadDataButton.setAlignmentX(CENTER_ALIGNMENT);
        saveDataButton.setAlignmentX(CENTER_ALIGNMENT);
        addStudentButton.setAlignmentX(CENTER_ALIGNMENT);
        addProfessorButton.setAlignmentX(CENTER_ALIGNMENT);
        viewStudentTranscriptButton.setAlignmentX(CENTER_ALIGNMENT);
        viewSchoolBankBalanceButton.setAlignmentX(CENTER_ALIGNMENT);
        viewSchoolSpendingsButton.setAlignmentX(CENTER_ALIGNMENT);
        viewStudentList.setAlignmentX(CENTER_ALIGNMENT);
        viewProfessorList.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
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

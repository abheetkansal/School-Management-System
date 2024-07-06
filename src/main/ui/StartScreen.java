package ui;


import model.School;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//EFFECTS: represents a window to select the type of user and its designation in the Indian High School Database
// Management System
public class StartScreen extends JPanel {

    private static final String JSON_STORE = "./data/school.json";
    private School s1;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs an object for startScreen
    public StartScreen() {

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        coolSoundGen("./data/bell.wav");

        s1 = new School(new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());
        loadSchool();
        JFrame starter = new JFrame("Indian High School");
        starterSet(starter);

        JPanel panel = new JPanel();

        JLabel motto = new JLabel(new ImageIcon("./data/motto.png"));
        motto.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(motto);

        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

        panel.setLayout(boxlayout);
        panel.setBackground(new Color(229, 98, 46));

        panel.setBorder(new EmptyBorder(new Insets(100, 50, 100, 50)));

        JLabel txt = new JLabel(("You are ..."));
        JButton studentButton = new JButton("Student");
        JButton professorButton = new JButton("Professor");
        JButton principalButton = new JButton("Principal");
        JButton exitButton = new JButton("Exit");

        reducer(starter, panel, txt, studentButton, professorButton, principalButton, exitButton);
    }

    // EFFECTS: used to reduce the lines of code
    public void reducer(JFrame starter, JPanel panel, JLabel txt, JButton studentButton, JButton professorButton,
                        JButton principalButton, JButton exitButton) {
        alignSetPanelAdder(starter, panel, txt, studentButton, professorButton, principalButton, exitButton);

        action(starter, studentButton, professorButton, principalButton, exitButton);

        saveSchool();
    }

    // EFFECTS: sets the required fields for frame
    public void starterSet(JFrame starter) {
        starter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        starter.setMinimumSize(new Dimension(300, 300));
        starter.setBackground(Color.YELLOW);
    }

    // EFFECTS: creates sounds on the press of required button
    public void coolSoundGen(String s) {
        File bellSoundFile = new File(s);
        AudioInputStream a = null;
        try {
            a = AudioSystem.getAudioInputStream(bellSoundFile.toURI().toURL());
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clip coolSound = null;
        try {
            coolSound = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            coolSound.open(a);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        coolSound.start();
    }

    // EFFECTS: performs the actions when certain buttons are pressed
    public void action(JFrame starter, JButton studentButton, JButton professorButton, JButton principalButton,
                       JButton exitButton) {
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coolSoundGen("./data/child.wav");

                starter.setVisible(false);
                new StudentLoginView(s1);
            }
        });

        profButton(starter, professorButton);

        principalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starter.setVisible(false);
                new PrincipalLoginView(s1);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // EFFECTS: performs the actions when professor is selected
    public void profButton(JFrame starter, JButton professorButton) {
        professorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starter.setVisible(false);
                new ProfessorLoginView(s1);
            }
        });
    }

    // EFFECTS: aligns the buttons and adds them to panel
    public void alignSetPanelAdder(JFrame starter, JPanel panel, JLabel txt, JButton studentButton,
                                   JButton professorButton, JButton principalButton, JButton exitButton) {
        txt.setAlignmentX(RIGHT_ALIGNMENT);
        studentButton.setAlignmentX(CENTER_ALIGNMENT);
        professorButton.setAlignmentX(CENTER_ALIGNMENT);
        principalButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(txt);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(studentButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(professorButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(principalButton);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        panel.add(exitButton);

        starter.add(panel);
        starter.pack();
        starter.setVisible(true);
    }

    // EFFECTS: saves the workroom to file
    private void saveSchool() {
        try {
            jsonWriter.open();
            jsonWriter.write(s1);
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
            s1 = jsonReader.read();
            System.out.println("Loaded Indian High School data from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

package ui;

import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the window to validate the students of Indian High School
public class StudentLoginView {

    private School school;

    // EFFECTS: constructs an object with school as its fields
    public StudentLoginView(School s1) {

        school = s1;
        JFrame frame = new JFrame("Student Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setBackground(Color.ORANGE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.setBackground(new Color(229, 98, 46));

        panel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JLabel label = new JLabel("Enter Student ID:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton login = new JButton("Login");
        JButton exit = new JButton("Exit");

        panel.add(login);
        panel.add(exit);

        frame.add(panel);
        frame.setVisible(true);

        action(s1, frame, passwordField, login, exit);
    }

    // EFFECTS: sets all the buttons and their actions
    public void action(School s1, JFrame frame, JPasswordField passwordField, JButton login, JButton exit) {
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idPass = Integer.parseInt(new String(passwordField.getPassword()));
                boolean log = false;
                for (Student s : school.getStudents()) {
                    if (s.getStudentID() == idPass) {
                        log = true;
                    }
                }
                    if (log) {
                    frame.dispose();
                    JOptionPane.showMessageDialog(null, "Logged In");
                    s1.setMemory(idPass);
                    new StudentView(s1);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials");
                }
            }
        });

        exitActionSet(frame, exit);
    }

    // EFFECTS: houses the action performed when action is performed
    public void exitActionSet(JFrame frame, JButton exit) {
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new StartScreen();
            }
        });
    }
}

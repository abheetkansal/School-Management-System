package ui;

import model.Professor;
import model.School;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the window to validate the professors of Indian High School
public class ProfessorLoginView {

    private School school;

    // EFFECTS: constructs an object with school and id as fields
    public ProfessorLoginView(School s1) {

        school = s1;
        JFrame frame = new JFrame("Professor Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setBackground(Color.ORANGE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.setBackground(new Color(229, 98, 46));

        panel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JLabel label = new JLabel("Enter Professor ID:");
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

    // EFFECTS: houses actions for various buttons when pressed
    public void action(School s1, JFrame frame, JPasswordField passwordField, JButton login, JButton exit) {
        actionOne(s1, frame, passwordField, login);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new StartScreen();
            }
        });
    }

    // EFFECTS: sets the specification for the first button
    public void actionOne(School s1, JFrame frame, JPasswordField passwordField, JButton login) {
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idPass = Integer.parseInt(new String(passwordField.getPassword()));
                boolean log = false;
                for (Professor p : school.getProfessors()) {
                    if (p.getProfessorID() == idPass) {
                        log = true;
                    }
                }
                if (log) {
                    frame.dispose();
                    JOptionPane.showMessageDialog(null, "Logged In");
                    new ProfessorView(s1);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials");
                }
            }
        });
    }
}

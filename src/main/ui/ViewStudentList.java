package ui;


import model.School;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//EFFECTS: represents a window to display all professors working in the Indian High School Database Management System
public class ViewStudentList extends JPanel {

    private final School school;


    // EFFECTS: constructs an object with school as its field
    public ViewStudentList(School s1) {

        school = s1;
        JFrame frame = new JFrame("List Of Students");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setBackground(Color.YELLOW);


        JPanel panel = new JPanel();

        JTable table = new JTable(s1.getStudents().size() + 1, 5);

        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

        panel.setLayout(boxlayout);
        panel.setBackground(new Color(229, 98, 46));
        panel.add(table);
        panel.setBorder(new EmptyBorder(new Insets(100, 50, 100, 50)));

        frame.setVisible(true);
        frame.add(panel);

        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(exitButton);

        tableHeadSet(table);

        for (int i = 0; i < s1.getStudents().size(); i++) {
            tableFields(s1, table, i);
        }

        action(frame, exitButton);
    }

    // EFFECTS: adds fields in columns of the table
    public void tableFields(School s1, JTable table, int i) {
        table.setValueAt(s1.getStudents().get(i).getStudentName(), i + 1, 0);
        table.setValueAt(s1.getStudents().get(i).getFaculty(), i + 1, 1);
        table.setValueAt(s1.getStudents().get(i).getStudentID(), i + 1, 2);
        table.setValueAt(s1.getStudents().get(i).getPendingTuition(), i + 1, 3);
        table.setValueAt(s1.getStudents().get(i).getTotalTuition(), i + 1, 4);
    }

    // EFFECTS: creates button and their action
    public void action(JFrame frame, JButton exitButton) {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new StartScreen();
            }
        });
    }

    // EFFECTS: sets the header for the table
    public void tableHeadSet(JTable table) {
        table.setValueAt("Student Name", 0, 0);
        table.setValueAt("Student Faculty", 0, 1);
        table.setValueAt("Student ID", 0, 2);
        table.setValueAt("Pending Tuition", 0, 3);
        table.setValueAt("Total Tuition", 0, 4);
    }

}



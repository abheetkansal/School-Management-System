
package ui;


import model.School;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//EFFECTS: represents a window to display all professors working in the Indian High School Database Management System
public class ViewProfessorList extends JPanel {

    private School school;

    // EFFECTS: constructs an object with school as its field
    public ViewProfessorList(School s1) {

        school = s1;
        JFrame frame = new JFrame("List Of Professors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setBackground(Color.YELLOW);


        JPanel panel = new JPanel();

        JTable table = new JTable(s1.getProfessors().size() + 1, 3);

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

        headSet(table);

        for (int i = 0; i < s1.getProfessors().size(); i++) {
            tableFields(s1, table, i);
        }

        action(frame, exitButton);
    }

    // EFFECTS: sets the headings in the table
    public void headSet(JTable table) {
        table.setValueAt("Professor Name", 0, 0);
        table.setValueAt("Professor ID", 0, 1);
        table.setValueAt("Total Salary", 0, 2);
    }

    // EFFECTS: sets the required fields used in the table
    public void tableFields(School s1, JTable table, int i) {
        table.setValueAt(s1.getProfessors().get(i).getProfessorName(), i + 1, 0);
        table.setValueAt(s1.getProfessors().get(i).getProfessorID(), i + 1, 1);
        table.setValueAt(s1.getProfessors().get(i).getTotalSalary(), i + 1, 2);
    }

    // EFFECTS: sets the buttons for the window and its actions
    public void action(JFrame frame, JButton exitButton) {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new StartScreen();
            }
        });
    }
}

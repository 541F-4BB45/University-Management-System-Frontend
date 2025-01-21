package studentView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class HomeFrame extends WorkingFrame implements ActionListener {

    private JTextField dob_field;
    private JTextField gender_field;
    private JTextField address_field;
    private JButton editing_button;
    private boolean editing = false;
    private JPanel coursesPanel;

    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 7);
    private static final Color BUTTON_BACKGROUND = Color.LIGHT_GRAY;
    private static final Color BUTTON_FOREGROUND = Color.BLACK;

    public HomeFrame(Student student) throws SQLException 
{
        this.student = student;
        initialiseFrame("Home");
        getContentPane().setBackground(new Color(0, 51, 51));
        initialiseMenuBar();
        setPageHeadings();
        addEditingButton("Edit", 180, 490, 100, 30);
        setLocationRelativeTo(null);
        setVisible(true);
try
{
    bootUp();
setAttributes();
coursesPanel = addCoursesPanel(10, 330, 450, 140); // Adjust dimensions as needed
}
catch (IllegalStateException ex)
{
ex.printStackTrace();
JOptionPane.showMessageDialog(null, ex.getMessage());
}
        
    }

    private void setPageHeadings() {
        addMainHeader("Student Information", 150, 10, 200, 30);
        addSubHeader("Personal Details", 180, 50, 200, 20);
        addProfilePhoto(new ImageIcon("path/to/profile/photo.jpg"), 10, 80, 100, 100); // Replace with actual path
    }

    private void addEditingButton(String text, int x, int y, int width, int height) {
        editing_button = new JButton(text);
        editing_button.setBounds(x, y, width, height);
        editing_button.setFocusable(false);
        editing_button.setFont(BUTTON_FONT);
        editing_button.setForeground(BUTTON_FOREGROUND);
        editing_button.setBackground(BUTTON_BACKGROUND);
        editing_button.addActionListener(this);
        add(editing_button);
    }

    private void setAttributes() {
        addLabel("Student ID: " + student.getStudentID(), 10, 100, 200, 20);
        addLabel("Student Name: " + student.getStudentName(), 10, 120, 200, 20);
        addLabel("Program Name: " + student.getProgramName(), 10, 140, 200, 20);
        addLabel("Department ID: " + student.getDepartmentID(), 10, 160, 200, 20);
        addLabel("Semester ID: " + student.getSemesterID(), 10, 180, 200, 20);
        addLabel("E-Mail: " + student.getEmail(), 10, 200, 300, 20);

        addLabel("Gender:", 10, 230, 200, 20);
        gender_field = addTextField("", 120, 230, 200, 20);

        addLabel("Address:", 10, 260, 200, 20);
        address_field = addTextField("", 120, 260, 200, 20);

        addLabel("Date of Birth:", 10, 290, 200, 20);
        dob_field = addTextField("", 120, 290, 200, 20);
    }

    private JPanel addCoursesPanel(int x, int y, int width, int height) {
        JPanel coursesPanel = new JPanel(new GridLayout(3, 4, 20, 20));
        coursesPanel.setBounds(x, y, width, height);
        coursesPanel.setBackground(Color.LIGHT_GRAY);

        System.out.println(courses);

        for (Course course : courses) {
            JButton button = new JButton(course.getCourseName());
            button.setFont(BUTTON_FONT);
            button.setForeground(BUTTON_FOREGROUND);
            button.setBackground(BUTTON_BACKGROUND);

            button.addActionListener(
                    (e) -> new CourseFrame(student, course)
            );

            coursesPanel.add(button);
        }

        add(coursesPanel);
        return coursesPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
    if (e.getSource() == home_item)
    {
        try {
            new HomeFrame(student);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }
    else if (e.getSource() == dashboard_item)
    {
        new DashboardFrame(student).setVisible(true); 
        dispose();
    }
    else if(e.getSource() == editing_button){
        setEditableFields();
        editing = !editing;
    }
    else if (e.getSource() == exit_item)
    {
        dispose();
    }
    }
    private void setEditableFields() {
        boolean state = editing;

        dob_field.setEditable(state);
        gender_field.setEditable(state);
        address_field.setEditable(state);
    }

}
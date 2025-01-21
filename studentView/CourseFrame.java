package studentView;

import javax.swing.*;

import java.awt.Color;
//import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.Random;

public class CourseFrame extends WorkingFrame implements ActionListener 
{
private Course course;

public CourseFrame(Student student, Course course) 
{
try
{
this.course = course;
this.student = student;
bootUp();
assessments = initialiseAssessmentsList(course);
initialiseFrame("Course Details");
initialiseMenuBar();
setAttributes();
setPageHeadings();
getContentPane().setBackground(new Color(0, 51, 51));
setAssessmentHistory(10, 200, 780, 30); //bounds
System.out.println(courses);

setVisible(true);
}
catch (SQLException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
} 
catch (IllegalStateException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection not initialized: " + ex.getMessage());
}
}

private void setData(Course course) 
{
this.course = course;
}

private void setAttributes() 
{
addLabel("Course ID: " + course.getCourseID(), 10, 80, 300, 30); //bounds

addLabel("Course Name: " + course.getCourseName(), 10, 110, 300, 30); //bounds

addLabel("Department Name: " + course.getDepartmentID(), 10, 140, 300, 30); //bounds

addLabel("Semester Number: " + course.getSemesterNo(), 10, 170, 300, 30); //bounds

addLabel("Instructor Name: " + professor.getProfessorName(), 10, 260, 300, 30); //bounds
}

private void setPageHeadings() 
{
addMainHeader("Course Details", 200, 10, 780, 40); // bounds

addSubHeader("Course Information", 10, 50, 780, 30); // bounds

}

private void setAssessmentHistory(int x, int y, int width, int height) 
{
int vertical_increment = 30; // set this

for (Assessment assessment : assessments) 
{
addLabel(assessment.getAssessmentName() + ": " + CGPAToGrade(record.getCGPA()), x, y, width, height);

y += vertical_increment;
}
}

private String CGPAToGrade(double CGPA) 
{

if (CGPA >= 3.5) 
{
return generateGrade("good");
} 
else if (CGPA < 2.0) 
{
return generateGrade("bad");
} 
else 
{
return generateGrade("mid");
}
}

private String generateGrade(String assessment) 
{
Random random = new Random();
double smallest_chance = 0.1, high_chance = 0.5, decent_chance = 0.3;
double random_number = random.nextDouble();

switch (assessment) 
{
case "good": 
{
if (random_number < smallest_chance) 
{
return "C";
} 
else if (random_number < smallest_chance + decent_chance) 
{
return "A";
} 
else if (random_number < smallest_chance + decent_chance + high_chance) 
{
return "B+";
} 
else 
{
return "B";
}
}

case "mid": 
{

if (random_number < smallest_chance) 
{
return "D";
} 
else if (random_number < smallest_chance + decent_chance) 
{
return "C";
} 
else if (random_number < smallest_chance + decent_chance + high_chance) 
{
return "C+";
} 
else 
{
return "A";
}
}

case "bad": 
{

        if (random_number < smallest_chance) 
        {
                return "B+";
        } 
        else if (random_number < smallest_chance + decent_chance) 
        {
                return "C+";
        } 
        else if (random_number < smallest_chance + decent_chance + high_chance) 
        {
                return "D";
        } 
        else 
        {
                return "C";
        }
}

default: 
        {
                return "F";
        }
}


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
    else if (e.getSource() == exit_item)
    {
        dispose();
    }
    }
}
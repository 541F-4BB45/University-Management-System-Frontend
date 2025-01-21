package studentView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import university.management.system.Conn;

public abstract class WorkingFrame extends JFrame implements ActionListener 
{
protected Student student;
protected ArrayList<Course> courses;
protected Semester semester;
protected AcademicRecord record;
protected Department department;
protected Professor professor;
protected ArrayList<Assessment> assessments;
protected static ArrayList<Club> clubs;


protected JMenuItem home_item , dashboard_item , exit_item;
protected JMenuBar menu_bar;
protected JMenu home_menu , exit_menu;
protected JMenu professors_menu;
protected JMenu dashboard_menu;

protected static final Color BACKGROUND_COLOR = Color.WHITE; // Example color
protected static final Color FOREGROUND_COLOR = Color.WHITE; // Example color
protected static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 12); // Example font
protected static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 16); // Example font
protected static final Font SUBHEADER_FONT = new Font("Arial", Font.ITALIC, 14); // Example font
protected static final Dimension FRAME_DIMENSION = new Dimension(800, 600); // Example dimension

protected void bootUp() throws SQLException, IllegalStateException 
{
try 
{
    department = initialiseDepartment(student);
    semester = initialiseSemester(student);
    record = initialiseAcademicRecord(student);
    courses = initialiseCoursesList(student);
    professor = initialiseProfessor(student);
clubs = initialiseClubsList();
} 
catch (SQLException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            
throw ex;
} 
catch (IllegalStateException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection not initialized: " + ex.getMessage());
            
throw ex;
}
}

protected void initialiseMenuBar() 
{
home_menu = new JMenu("Home");

dashboard_menu = new JMenu("Dashboard");
home_item = new JMenuItem("Home");
home_item.addActionListener(this);
home_menu.add(home_item);

dashboard_item = new JMenuItem("Dashboard");
dashboard_item.addActionListener(this);
dashboard_menu.add(dashboard_item);

exit_item = new JMenuItem("Exit");
exit_item.addActionListener(this);
exit_menu = new JMenu("Exit");
exit_menu.add(exit_item);


menu_bar = new JMenuBar();
menu_bar.add(home_menu);
menu_bar.add(dashboard_menu);
menu_bar.add(exit_menu);

setJMenuBar(menu_bar);
}

protected void initialiseFrame(String Text  ) 
{
setTitle(Text);
setLayout(null);    
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setSize(new Dimension(500, 600));
setResizable(false);
setLocationRelativeTo(null);
}

protected void addLabel(String text, int x, int y, int width, int height) 
{
JLabel label = new JLabel(text);
label.setBounds(x, y, width, height);
label.setFont(LABEL_FONT);     
label.setForeground(FOREGROUND_COLOR);      
label.setBackground(BACKGROUND_COLOR);
        
add(label);
}

protected JTextField addTextField(String text, int x, int y, int width, int height) 
{
JTextField field = new JTextField(10);
field.setBounds(x, y, width, height);    
field.setForeground(FOREGROUND_COLOR);   
field.setBackground(BACKGROUND_COLOR);
field.setText(text);
field.setEditable(false);
field.setFocusable(false);
add(field);

return field;
}

protected void addProfilePhoto(ImageIcon profile_photo, int x, int y, int width, int height) 
{
JLabel label = new JLabel();
label.setIcon(profile_photo);
label.setBounds(x, y, width, height);

add(label);
}

protected void addMainHeader(String text, int x, int y, int width, int height) 
{
JLabel header = new JLabel(text);
header.setBounds(x, y, width, height);      header.setForeground(FOREGROUND_COLOR);     header.setBackground(BACKGROUND_COLOR);
header.setFont(HEADER_FONT);
        
add(header);
}

protected void addSubHeader(String text, int x, int y, int width, int height) 
{
JLabel label = new JLabel(text);
label.setBounds(x, y, width, height);     label.setForeground(FOREGROUND_COLOR);     label.setBackground(BACKGROUND_COLOR);
label.setFont(SUBHEADER_FONT);
        
add(label);
}

protected void addPanelLabel(JPanel panel, String text, int x, int y, int width, int height) 
{
JLabel label = new JLabel(text);
label.setBounds(x, y, width, height);
label.setFont(LABEL_FONT);      label.setForeground(FOREGROUND_COLOR);    label.setBackground(BACKGROUND_COLOR);
        
panel.add(label);
}

protected void initialisePanel(JPanel panel, int x, int y, int width, int height) 
{
panel.setLayout(null);
panel.setBounds(x, y, width, height);
}

protected void addPanelTextField(JPanel panel, String text, int x, int y, int width, int height) 
{
JTextField field = new JTextField(10);      
field.setBounds(x, y, width, height);     field.setForeground(FOREGROUND_COLOR);     field.setBackground(BACKGROUND_COLOR);
field.setText(text);
field.setEditable(false);
field.setFocusable(false);

panel.add(field);
}

protected void addPanelProfilePhoto(JPanel panel, ImageIcon profile_photo, int x, int y, int width, int height) 
{
JLabel label = new JLabel();
label.setIcon(profile_photo);
label.setBounds(x, y, width, height);

panel.add(label);
}

protected void addPanelMainHeader(JPanel panel, String text, int x, int y, int width, int height) {
JLabel header = new JLabel(text);
header.setBounds(x, y, width, height);      header.setForeground(FOREGROUND_COLOR);      header.setBackground(BACKGROUND_COLOR);
header.setFont(HEADER_FONT);
        
panel.add(header);
}

protected void addPanelSubHeader(JPanel panel, String text, int x, int y, int width, int height) 
{
JLabel label = new JLabel(text);
label.setBounds(x, y, width, height);     label.setForeground(FOREGROUND_COLOR);      label.setBackground(BACKGROUND_COLOR);
label.setFont(SUBHEADER_FONT);
        
panel.add(label);
}

protected void addPanelAnonymousField(JPanel panel, String text, int x, int y, int width, int height) {
JTextField field = new JTextField(10);
field.setBounds(x, y, width, height);      field.setForeground(FOREGROUND_COLOR);     field.setBackground(BACKGROUND_COLOR);
field.setText(text);
field.setEditable(false);
field.setFocusable(false);
        
panel.add(field);
}

protected void initialiseStudent(Student student) 
{
this.student = student;
}

protected static Semester initialiseSemester(Student student) throws SQLException, IllegalStateException 
{
Semester semester = new Semester();
        
String query = "SELECT * FROM Semester WHERE Semester.Semester_ID = ?";

        
try 
{
ResultSet result_set = getResultSet(query, student.getSemesterID());
            
if (result_set.next()) 
{
    semester.setSemesterID(result_set.getString("Semester_ID"));             
    semester.setSemesterName(result_set.getString("Semester_Name"));              
    semester.setSemesterNo(result_set.getInt("Semester_No"));             
    semester.setStartDate(result_set.getString("Start_Date"));       
    semester.setEndDate(result_set.getString("End_Date"));

} 
else 
{
throw new SQLException("No semester found!");
}
} 
catch (SQLException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            
throw ex;
} 
catch (IllegalStateException ex) 
{
ex.printStackTrace();

JOptionPane.showMessageDialog(null, "Connection not initialized: " + ex.getMessage());
            
throw ex;
}
        
return semester;
}

protected static AcademicRecord initialiseAcademicRecord(Student student) throws SQLException, IllegalStateException 
{
AcademicRecord record = new AcademicRecord();
        
String query = "SELECT AcademicRecord.Grade_ID, AcademicRecord.Student_ID, AcademicRecord.CGPA FROM AcademicRecord INNER JOIN Student ON Student.Student_ID = AcademicRecord.Student_ID WHERE AcademicRecord.Student_ID = ?";

     
try 
{
ResultSet result_set = getResultSet(query, student.getStudentID());

if (result_set.next()) 
{
    record.setStudentID(result_set.getString("Student_ID"));             record.setGradeID(result_set.getString("Grade_ID"));              record.setCGPA(result_set.getDouble("CGPA"));

} 
else 
{
throw new SQLException("No academic record found!");
}

} 
catch (SQLException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            
throw ex;
} 
catch (IllegalStateException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection not initialized: " + ex.getMessage());
            
throw ex;
}
        
return record;
}

protected static ArrayList<Course> initialiseCoursesList(Student student) throws SQLException, IllegalStateException 
{
ArrayList<Course> courses = new ArrayList<>();
        
String query = "SELECT Course.Course_ID, Course.Course_Name, Course.Department_ID, Course.Semester_No FROM Course INNER JOIN Department ON Course.Department_ID = Department.Department_ID WHERE Course.Department_ID = ?";
        
try 
{
ResultSet result_set = getResultSet(query, student.getDepartmentID());
            
while (result_set.next()) 
{
Course course = new Course();
course.setCourseID(result_set.getString("Course_ID"));              course.setCourseName(result_set.getString("Course_Name"));             course.setDepartmentID(result_set.getString("Department_ID"));             course.setSemesterNo(result_set.getInt("Semester_No"));

courses.add(course);
}

if (courses.isEmpty()) 
{
throw new SQLException("No such courses found!");
}

} 
catch (SQLException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            
throw ex;
} 
catch (IllegalStateException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection not initialized: " + ex.getMessage());
            
throw ex;
}

return courses;
}

protected static Department initialiseDepartment(Student student) throws SQLException 
{
Department department = new Department();
        
String query = "SELECT * FROM Department WHERE Department.Department_ID = ?";

try 
{
ResultSet result_set = getResultSet(query, student.getDepartmentID());

if (result_set.next()) 
{
    department.setDepartmentID(result_set.getString("Department_ID")); department.setDepartmentName(result_set.getString("Department_Name"));

} 
else 
{
throw new SQLException("No department found!");
}

}
catch (SQLException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            
throw ex;
} 
catch (IllegalStateException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection not initialized: " + ex.getMessage());
            
throw ex;
}

return department;
}

protected static Professor initialiseProfessor(Student student) throws SQLException, IllegalStateException 
{
Professor professor = new Professor();
       
String query = "SELECT Professor.Professor_ID, Professor.Professor_Name, Professor.Email, Professor.Department_ID FROM Professor INNER JOIN Student ON Student.Department_ID = Professor.Department_ID WHERE Professor.Department_ID = ?";
       
try 
{
ResultSet result_set = getResultSet(query, student.getDepartmentID());
            
if (result_set.next()) 
{
    professor.setProfessorID(result_set.getString("Professor_ID"));
    professor.setProfessorName(result_set.getString("Professor_Name"));
    professor.setDepartmentID(result_set.getString("Department_ID"));
    professor.setEmail(result_set.getString("Email"));

} 
else 
{
throw new SQLException("No professor found for the given department ID");
}

} 
catch (SQLException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            
throw ex;
} 
catch (IllegalStateException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection not initialized: " + ex.getMessage());
            
throw ex;
}
        
return professor;
}

protected static ArrayList<Assessment> initialiseAssessmentsList(Course course) throws SQLException, IllegalStateException 
{
ArrayList<Assessment> assessments = new ArrayList<>();
        
String query = "SELECT Assessment.Assessment_ID, Assessment.Assessment_Type, Assessment.Course_ID FROM Assessment INNER JOIN Course ON Assessment.Course_ID = Course.Course_ID WHERE Assessment.Course_ID = ?";
        
try 
{
ResultSet result_set = getResultSet(query, course.getCourseID());
            
while (result_set.next()) 
{
Assessment assessment = new Assessment();
                assessment.setAssessmentID(result_set.getString("Assessment_ID"));             
                assessment.setAssessmentName(result_set.getString("Assessment_Type"));            
                assessment.setCourseID(result_set.getString("Course_ID"));
                
assessments.add(assessment);
}

if (assessments.isEmpty()) 
{
throw new SQLException("No assessments found!");
}

} 
catch (SQLException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            
throw ex;
} 
catch (IllegalStateException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection not initialized: " + ex.getMessage());
            
throw ex;
}
        
return assessments;
}

protected static ArrayList<Club> initialiseClubsList() throws SQLException, IllegalStateException 
{
ArrayList<Club> clubs = new ArrayList<>();
        
String query = "SELECT * FROM Club";
        
try 
{
PreparedStatement prepared_statement = Conn.connection.prepareStatement(query);
            
ResultSet result_set = prepared_statement.executeQuery();
            
while (result_set.next()) 
{
Club club = new Club();
                club.setClubID(result_set.getString("Club_ID"));              
                club.setClubName(result_set.getString("Club_Name"));
                
clubs.add(club);
}

if (clubs.isEmpty()) 
{              
throw new SQLException("No clubs found!");
}

} 
catch (SQLException ex) 
{          
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            
throw ex;
} 
catch (IllegalStateException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection not initialized: " + ex.getMessage());

throw ex;
}
        
return clubs;
}

protected static ResultSet getResultSet(String query, String setting_string) throws SQLException, IllegalStateException 
{
if (Conn.connection == null) 
{
throw new IllegalStateException("Connection is not initialized");
}

try 
{
PreparedStatement prepared_statement = Conn.connection.prepareStatement(query);
            
prepared_statement.setString(1, setting_string);

return prepared_statement.executeQuery();
} 
catch (SQLException ex) 
{
ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());

throw ex;
}
}

protected void DepartmentRoomLink(ArrayList<String> room_no_list) 
{
for (int i = 1; i <= 9; i++) 
{
room_no_list.add("R000" + i);
}
room_no_list.add("R0010");
}



@Override
public void actionPerformed(ActionEvent e)
{
if (e.getSource() == home_item)
{
try {
    new HomeFrame(student);
} catch (SQLException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
}
dispose();
}
else if (e.getSource() == dashboard_item)
{
new DashboardFrame(student);
dispose();
}
else if (e.getSource() == exit_item)
{
    dispose();
}
}
}
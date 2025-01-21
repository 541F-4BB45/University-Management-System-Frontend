package studentView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import university.management.system.Conn;
import university.management.system.Login;

public class StudentViewLogin extends JFrame implements ActionListener {
    JTextField TextFieldName;
    JPasswordField PasswordField;
    JButton LoginButton, ExitButton, AdminLogin;
    JFrame frame;

    public StudentViewLogin() {
        // Create the main frame
        frame = new JFrame("Student Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 350); // Set the frame size
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setLayout(null); // Use absolute positioning
        frame.getContentPane().setBackground(new Color(0, 51, 51)); // Set the background color of the content pane


        // Add a label for "Login Here"
        JLabel loginHereLabel = new JLabel("Login Here");
        loginHereLabel.setBounds(250, 20, 200, 30); // Set position and size
        loginHereLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        loginHereLabel.setBackground(new Color(0, 51, 51)); // Set text color
        loginHereLabel.setForeground(Color.WHITE);
        frame.add(loginHereLabel);

        // Adding Label for "Username"
        JLabel email = new JLabel("Email");
        email.setBounds(40, 60, 100, 40);
        email.setFont(new Font("Arial", Font.BOLD, 17));
        email.setBackground(new Color(0, 51, 51));
        email.setForeground(Color.WHITE);
        frame.add(email);

        // Adding Field For Username
        TextFieldName = new JTextField();
        TextFieldName.setBounds(150, 70, 150, 20);
        frame.add(TextFieldName);

        // Adding Label For Password
        JLabel password = new JLabel("Password");
        password.setBounds(40, 110, 100, 40);
        password.setFont(new Font("Arial", Font.BOLD, 15));
        password.setBackground(new Color(0, 51, 51));
        password.setForeground(Color.WHITE);
        frame.add(password);

        // Adding Field for Password
        PasswordField = new JPasswordField();
        PasswordField.setBounds(150, 120, 150, 20);
        frame.add(PasswordField);

        // Adding Login Button
        LoginButton = new JButton("Login");
        LoginButton.setBounds(40, 160, 120, 30);
        LoginButton.setBackground(new Color(0, 51, 51));
        LoginButton.setForeground(Color.WHITE);
        LoginButton.addActionListener(this);
        frame.add(LoginButton);

        // Adding Exit Button
        ExitButton = new JButton("Exit");
        ExitButton.setBounds(180, 160, 120, 30);
        ExitButton.setBackground(new Color(0, 51, 51));
        ExitButton.setForeground(Color.WHITE);
        ExitButton.addActionListener(this);
        frame.add(ExitButton);

        // Adding Label
        JLabel forRegister = new JLabel("For Admin Login Click Here");
        forRegister.setBounds(120, 250, 200, 40);
        forRegister.setFont(new Font("Arial", Font.BOLD, 13));
        forRegister.setBackground(new Color(0, 51, 51));
        forRegister.setForeground(Color.WHITE);
        frame.add(forRegister);

        // Adding Register Button
        AdminLogin = new JButton("Login");
        AdminLogin.setBounds(310, 260, 90, 20);
        AdminLogin.setBackground(new Color(0, 51, 51));
        AdminLogin.setForeground(Color.WHITE);
        AdminLogin.addActionListener(this);
        frame.add(AdminLogin);

        // Make the frame visible
        frame.setVisible(true);
    }

    @Override
public void actionPerformed(ActionEvent e) {
    Conn co = new Conn();
    co.call();
    Student student = new Student();
    
    if (e.getSource() == LoginButton) {
        String email = TextFieldName.getText();
        String password = new String(PasswordField.getPassword());

        String query = "SELECT * FROM studentLogin WHERE email = ? AND password = ?";
        try {
            if (Conn.connection == null) {
                throw new IllegalStateException("Connection is not initialized");
            }
            PreparedStatement statement = Conn.connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Login Successful");
                student = getStudent(email);
                System.out.println(student.getDepartmentID());
                HomeFrame homeFrame = new HomeFrame(student);
                homeFrame.setVisible(true);
                
                // Dispose the current login frame
                frame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection not initialized: " + ex.getMessage());
        }
    } else if (e.getSource() == ExitButton) {
        System.exit(0); // Exit the application
    } else if (e.getSource() == AdminLogin) {
        new Login().setVisible(true);
        frame.setVisible(false);
    }
}


    public static Student getStudent(String email) {
        Conn co = new Conn();
        co.call();
        Student student = new Student();
        
        String query = "SELECT * FROM student WHERE Email = ?";
        PreparedStatement statement;
        try {
            statement = Conn.connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {  // Ensure we have a result
                student.setStudentID(resultSet.getString("Student_ID"));
                student.setStudentName(resultSet.getString("Student_Name"));
                student.setDepartmentID(resultSet.getString("Department_ID"));
                student.setSemesterID(resultSet.getString("Semester_ID"));
                student.setEmail(resultSet.getString("Email"));
                student.setProgramName(resultSet.getString("Program_Name"));
            } else {
                JOptionPane.showMessageDialog(null, "No student found with the provided email.");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
        }
        return student;
    }
    

    public static void main(String[] args) {
        new StudentViewLogin();

    }
}

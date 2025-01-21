package studentView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import university.management.system.Conn;

public class DashboardFrame extends WorkingFrame implements ActionListener {
    private ArrayList<Pair<Club, ArrayList<Event>>> club_event_list;

    public DashboardFrame(Student student) {
        this.student = student;
        try {
            bootUp();
            club_event_list = initialiseClubEventList();
            initialiseFrame("Dashboard");
            initialiseMenuBar();
            getContentPane().setBackground(new Color(0, 51, 51));
            setInfo();
            setEventsPanel(180, 200, 780, 300); // Adjust the position and size
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection not initialized: " + ex.getMessage());
        }
    }

    private void setInfo() {
        addMainHeader("DASHBOARD", 200, 10, 200, 30); //bounds
        addSubHeader("Student Name: " + student.getStudentName(), 20, 60, 200, 30); //bounds
        addSubHeader("Student ID:" + student.getStudentID(), 20, 90, 200, 30); //bounds
        addSubHeader("CGPA: " + record.getCGPA(), 20, 120, 200, 30); //bounds
        addSubHeader("Department Name: " + department.getDepartmentName(), 240, 60, 250, 30); //bounds
        addSubHeader("Degree Type: " + student.getProgramName(), 240, 90, 200, 30); //bounds
        addSubHeader("Current Semester: " + semester.getSemesterName(), 240, 120, 200, 30); //bounds
    }

    private void setEventsPanel(int x, int y, int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(x, y, width, height);
        panel.setBackground(new Color(0,51,51));
        panel.setForeground(Color.WHITE);
        addMainHeader("Clubs & Events", x, y - 30, 200, 30); // Adjust position for header

        try {
            for (Pair<Club, ArrayList<Event>> pair : club_event_list) {
                JPanel sub_panel = new JPanel();
                sub_panel.setLayout(new BoxLayout(sub_panel, BoxLayout.Y_AXIS));
                sub_panel.setBackground(new Color(0, 51, 51));
                Club club = pair.getFirst();
                ArrayList<Event> list = pair.getSecond();
                addEventHeader(sub_panel, club.getClubName(), 10, 10, 200, 30); //bounds

                for (Event event : list) {
                    addEventLabel(sub_panel, event.getEventType(), 10, 10, 200, 30); //bounds
                }

                panel.add(sub_panel);
            }
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection not initialized: " + ex.getMessage());
        }

        getContentPane().add(panel);
    }

    private static ArrayList<Pair<Club, ArrayList<Event>>> initialiseClubEventList() throws SQLException, IllegalStateException {
        ArrayList<Pair<Club, ArrayList<Event>>> club_event_list = new ArrayList<>();
        ArrayList<Club> clubs = initialistClubsList();

        for (Club club : clubs) {
            ArrayList<Event> event_list = new ArrayList<>();

            String query = "SELECT Event_Type FROM Event WHERE Club_ID = ?";

            try {
                ResultSet result_set = getResultSet(query, club.getClubID());

                while (result_set.next()) {
                    Event event = new Event();
                    event.setEventType(result_set.getString("Event_Type"));
                    event_list.add(event);
                }

                if (event_list.isEmpty()) {
                    throw new SQLException("No events found for: " + club.getClubName());
                }

                club_event_list.add(new Pair<>(club, event_list));
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            } catch (IllegalStateException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Connection not initialized: " + ex.getMessage());
            }
        }

        return club_event_list;
    }

    private void addEventLabel(JPanel panel, String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBackground(new Color(0, 51, 51));
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.PLAIN, 8));
        panel.add(label);
    }

    private void addEventHeader(JPanel panel, String text, int x, int y, int width, int height) {
        JLabel header = new JLabel(text);
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        header.setBackground(new Color(0, 51, 51));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 10));
        panel.add(header);
    }

    protected static ArrayList<Club> initialistClubsList() throws SQLException, IllegalStateException {
        ArrayList<Club> club_list = new ArrayList<>();

        String query = "SELECT * FROM Club";

        try {
            PreparedStatement statement = Conn.connection.prepareStatement(query);

            ResultSet result_set = statement.executeQuery();

            while (result_set.next()) {
                Club club = new Club();

                club.setClubID(result_set.getString("Club_ID"));
                club.setClubName(result_set.getString("Club_Name"));

                club_list.add(club);
            }

            if (club_list.isEmpty()) {
                throw new SQLException("No clubs found");
            }

            return club_list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            throw ex;
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection not initialized: " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home_item) {
            try {
                new HomeFrame(student);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            dispose();
        } else if (e.getSource() == dashboard_item) {
            new DashboardFrame(student).setVisible(true);
            dispose();
        } else if (e.getSource() == exit_item) {
            dispose();
        }
    }

    public static void main(String[] args, Student student) {
        new DashboardFrame(student);
    }
}

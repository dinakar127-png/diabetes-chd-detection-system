package ui;

import javax.swing.*;
import java.sql.*;
import db.DBConnection;

public class DoctorDashboard extends JFrame {

    JTextArea area;

    public DoctorDashboard() {
        setTitle("Doctor Dashboard");
        setSize(500, 400);

        area = new JTextArea();
        add(new JScrollPane(area));

        loadReports();
    }

    void loadReports() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                "SELECT * FROM lab_reports"
            );

            while (rs.next()) {
                area.append(
                    "User ID: " + rs.getInt("user_id") +
                    " | Glucose: " + rs.getDouble("glucose") +
                    " | BP: " + rs.getDouble("bp") +
                    " | Cholesterol: " + rs.getDouble("cholesterol") +
                    " | Status: " + rs.getString("status") +
                    "\n"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
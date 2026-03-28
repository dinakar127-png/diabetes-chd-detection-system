package ui;

import javax.swing.*;
import java.sql.*;
import db.DBConnection;

public class AdminDashboard extends JFrame {

    JTextArea area;

    public AdminDashboard() {
        setTitle("Admin Panel");
        setSize(500, 400);

        area = new JTextArea();
        add(new JScrollPane(area));

        loadUsers();
    }

    void loadUsers() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                area.append(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("email") + "\n"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
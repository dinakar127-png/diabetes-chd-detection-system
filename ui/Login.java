package ui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import db.DBConnection;

public class Login extends JFrame {

    JTextField email;
    JPasswordField pass;

    public Login() {
        setTitle("Login");
        setSize(300, 200);
        setLayout(null);

        JLabel l1 = new JLabel("Username:");
        l1.setBounds(20, 20, 100, 30);
        add(l1);

        email = new JTextField();
        email.setBounds(120, 20, 150, 30);
        add(email);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(20, 60, 100, 30);
        add(l2);

        pass = new JPasswordField();
        pass.setBounds(120, 60, 150, 30);
        add(pass);

        JButton login = new JButton("Login");
        login.setBounds(80, 110, 100, 30);
        add(login);

        login.addActionListener(e -> loginUser());
    }

    void loginUser() {
        String username = email.getText();
        String password = new String(pass.getPassword());

        try {
            Connection con = DBConnection.getConnection();

            //  ADMIN LOGIN
            PreparedStatement ps1 = con.prepareStatement(
                "SELECT * FROM admin WHERE username=? AND password=?"
            );
            ps1.setString(1, username);
            ps1.setString(2, password);
            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {
                JOptionPane.showMessageDialog(this, "Admin Login Success");
                new AdminDashboard().setVisible(true);
                return;
            }

            //  DOCTOR LOGIN (simple check)
            if (username.equals("doctor") && password.equals("doctor123")) {
                JOptionPane.showMessageDialog(this, "Doctor Login Success");
                new DoctorDashboard().setVisible(true);
                return;
            }

            //  LAB LOGIN
            if (username.equals("lab") && password.equals("lab123")) {
                JOptionPane.showMessageDialog(this, "Lab Login Success");
                new LabDashboard().setVisible(true);
                return;
            }

            //  USER LOGIN (from DB)
            PreparedStatement ps2 = con.prepareStatement(
                "SELECT * FROM users WHERE email=? AND password=?"
            );
            ps2.setString(1, username);
            ps2.setString(2, password);
            ResultSet rs2 = ps2.executeQuery();

            if (rs2.next()) {
                JOptionPane.showMessageDialog(this, "User Login Success");
                new UserDashboard().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
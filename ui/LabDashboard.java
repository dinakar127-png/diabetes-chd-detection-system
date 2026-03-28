package ui;

import javax.swing.*;
import java.sql.*;
import db.DBConnection;
import model.PredictionModel;

public class LabDashboard extends JFrame {

    JTextField userId, glucose, bp, chol;

    public LabDashboard() {
        setTitle("Lab Module");
        setSize(400, 300);
        setLayout(null);

        userId = new JTextField();
        userId.setBounds(150, 20, 150, 30);
        add(userId);

        glucose = new JTextField();
        glucose.setBounds(150, 60, 150, 30);
        add(glucose);

        bp = new JTextField();
        bp.setBounds(150, 100, 150, 30);
        add(bp);

        chol = new JTextField();
        chol.setBounds(150, 140, 150, 30);
        add(chol);

        JButton submit = new JButton("Submit Report");
        submit.setBounds(120, 190, 140, 30);
        add(submit);

        submit.addActionListener(e -> saveReport());
    }

    void saveReport() {
        try {
            Connection con = DBConnection.getConnection();

            double g = Double.parseDouble(glucose.getText());
            double b = Double.parseDouble(bp.getText());
            double c = Double.parseDouble(chol.getText());

            String status = PredictionModel.predict(g, b, c);

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO lab_reports(user_id, glucose, bp, cholesterol, status) VALUES(?,?,?,?,?)"
            );

            ps.setInt(1, Integer.parseInt(userId.getText()));
            ps.setDouble(2, g);
            ps.setDouble(3, b);
            ps.setDouble(4, c);
            ps.setString(5, status);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Report Saved");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
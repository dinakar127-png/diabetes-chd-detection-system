package ui;

import javax.swing.*;
import model.PredictionModel;

public class UserDashboard extends JFrame {

    JTextField glucose, bp, chol;

    public UserDashboard() {
        setTitle("User Dashboard");
        setSize(400, 300);
        setLayout(null);

        glucose = new JTextField();
        glucose.setBounds(150, 30, 150, 30);
        add(glucose);

        bp = new JTextField();
        bp.setBounds(150, 70, 150, 30);
        add(bp);

        chol = new JTextField();
        chol.setBounds(150, 110, 150, 30);
        add(chol);

        JButton check = new JButton("Check Risk");
        check.setBounds(120, 160, 120, 30);
        add(check);

        check.addActionListener(e -> {
            double g = Double.parseDouble(glucose.getText());
            double b = Double.parseDouble(bp.getText());
            double c = Double.parseDouble(chol.getText());

            String result = PredictionModel.predict(g, b, c);

            JOptionPane.showMessageDialog(this, result);
        });
    }
}
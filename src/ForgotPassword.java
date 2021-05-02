import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class ForgotPassword {
    static JPanel forgotPanel = new JPanel();
    JButton backB = new JButton("< BACK to Login");

    ForgotPassword() {
        forgotPanel.setBounds(451, 0, 750, 700);
        forgotPanel.setVisible(false);

        JLabel recoverLogin = new JLabel("Recover Password");
        recoverLogin.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel anLabel = new JLabel("Account No.");
        anLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField anTextField = new JTextField();
        anTextField.setFont(new Font("Arial", Font.BOLD, 18));

        String questions[] = { "Security Questions", "What is your pet's name?", "What makes you happy?" };
        JComboBox<String> sqCB = new JComboBox<String>(questions);

        JTextField ansField = new JTextField();
        ansField.setFont(new Font("Arial", Font.BOLD, 18));
        JButton recover = new JButton("Recover!");

        recoverLogin.setBounds(230, 130, 300, 40);
        anLabel.setBounds(180, 200, 150, 30);
        anTextField.setBounds(351, 200, 200, 30);
        sqCB.setBounds(180, 230, 150, 30);
        ansField.setBounds(351, 230, 200, 30);
        backB.setBounds(10, 10, 150, 20);
        recover.setBounds(300, 290, 100, 30);

        forgotPanel.add(backB);
        forgotPanel.add(recoverLogin);
        forgotPanel.add(anLabel);
        forgotPanel.add(anTextField);
        forgotPanel.add(sqCB);
        forgotPanel.add(ansField);
        forgotPanel.add(recover);
        forgotPanel.setLayout(new BorderLayout());

        recover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection con = null;
                PreparedStatement ps = null;
                String acn = anTextField.getText();
                String sq = sqCB.getSelectedItem().toString();
                String pin = ansField.getText();
                ResultSet rs = null;
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
                    String query = "SELECT * FROM userdata WHERE AccountNo=? AND SecurityQuestion=? AND Answer=?";
                    ps = con.prepareStatement(query);
                    ps.setString(1, acn);
                    ps.setString(2, sq);
                    ps.setString(3, pin);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(forgotPanel,
                                "Recovered Successfuly!\nYour Pin is: " + rs.getString("Pin"), "Success!",
                                JOptionPane.INFORMATION_MESSAGE);

                        forgotPanel.setVisible(false);
                        Login.loginFormPanel.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(forgotPanel, "Incorrect Inputs.\nTRY AGAIN!!!", "Failure!",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception rpEx) {
                    JOptionPane.showMessageDialog(forgotPanel, "Unable to Fetch!", "Error",
                            JOptionPane.WARNING_MESSAGE);
                }

            }

        });

        backB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                forgotPanel.setVisible(false);
                Login.loginFormPanel.setVisible(true);

            }

        });
    }
}

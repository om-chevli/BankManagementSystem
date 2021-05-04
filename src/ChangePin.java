import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChangePin {
    static JPanel changePinPanel = new JPanel();

    JPasswordField txtOldPin = new JPasswordField();
    JPasswordField txtNewPin = new JPasswordField();
    JPasswordField txtCPin = new JPasswordField();

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public void changePin() {
        String newPass = String.valueOf(txtNewPin.getPassword());
        String oldPass = String.valueOf(txtOldPin.getPassword());
        String confPass = String.valueOf(txtCPin.getPassword());
        if (newPass.equals(confPass)) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
                String infoquery = "SELECT * FROM userdata WHERE AccountNo=? AND Pin=?";
                ps = con.prepareStatement(infoquery);
                ps.setString(1, Login.acN);
                ps.setString(2, oldPass);
                rs = ps.executeQuery();
                if (rs.next()) {
                    if (!newPass.equals(oldPass)) {
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
                        String updateQuery = "UPDATE userdata SET Pin='" + String.valueOf(txtNewPin.getPassword())
                                + "' WHERE AccountNo='" + Login.acN + "';";
                        ps = con.prepareStatement(updateQuery);
                        ps.execute();
                        JOptionPane.showMessageDialog(changePinPanel, "Pin Updated Successfuly!", "Success!",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(changePinPanel, "New password cannot be same as old password!",
                                "Error!", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(changePinPanel, "Incorrect Pin!", "Failure!",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(changePinPanel, "Unable to fetch data!", "Error!",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(changePinPanel, "Confirm Password is incorrect!", "Failure!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    ChangePin() {
        changePinPanel.setVisible(false);
        JLabel title = new JLabel("Change Pin");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.black);

        JLabel oldPinLabel = new JLabel("Old Pin :");
        oldPinLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        JLabel newPinLabel = new JLabel("New Pin :");
        newPinLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        JLabel confirmPinLabel = new JLabel("Confirm Pin :");
        confirmPinLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        txtOldPin.setFont(new Font("Arial", Font.BOLD, 20));
        txtNewPin.setFont(new Font("Arial", Font.BOLD, 20));
        txtCPin.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnChangepin = new JButton("Change Pin");
        btnChangepin.setFont(new Font("Arial", Font.BOLD, 15));
        btnChangepin.setBackground(Color.BLACK);
        btnChangepin.setForeground(Color.white);

        changePinPanel.setBounds(300, 0, 900, 700);
        title.setBounds(380, 45, 400, 40);
        oldPinLabel.setBounds(300, 200, 150, 30);
        txtOldPin.setBounds(500, 200, 200, 30);
        newPinLabel.setBounds(300, 250, 150, 30);
        txtNewPin.setBounds(500, 250, 200, 30);
        confirmPinLabel.setBounds(300, 300, 150, 30);
        txtCPin.setBounds(500, 300, 200, 30);
        btnChangepin.setBounds(380, 380, 180, 40);

        btnChangepin.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                changePin();
            }
        });

        changePinPanel.setBackground(new Color(239, 251, 198));

        changePinPanel.add(title);
        changePinPanel.add(oldPinLabel);
        changePinPanel.add(txtOldPin);
        changePinPanel.add(newPinLabel);
        changePinPanel.add(txtNewPin);
        changePinPanel.add(confirmPinLabel);
        changePinPanel.add(txtCPin);
        changePinPanel.add(btnChangepin);
        changePinPanel.setLayout(new BorderLayout());
    }
}

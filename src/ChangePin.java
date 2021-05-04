import javax.swing.*;
import javax.swing.event.ChangeEvent;

import java.awt.*;

public class ChangePin {
    static JPanel changePinPanel = new JPanel();

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

        JPasswordField txtOldPin = new JPasswordField();
        txtOldPin.setFont(new Font("Arial", Font.BOLD, 20));
        JPasswordField txtNewPin = new JPasswordField();
        txtNewPin.setFont(new Font("Arial", Font.BOLD, 20));
        JPasswordField txtCPin = new JPasswordField();
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
        btnChangepin.setBounds(380,380,180,40);

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

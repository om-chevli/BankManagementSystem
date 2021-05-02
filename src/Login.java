import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class Login {

    public static JPanel loginFormPanel = new JPanel();
    static JPanel logoPanel = new JPanel();
    static Registeration rPanel = new Registeration();
    static ForgotPassword fPanel = new ForgotPassword();

    enum toWhere {
        LoginPage, RegisterPage,
    };

    public static void main(String[] args) {

        // Panel Initialize
        
        logoPanel.setBounds(0, 0, 450, 700);
        logoPanel.setBackground(new Color(31, 30, 31));
        // logoPanel.setVisible(false);

        // loginFormPanel.setBackground(Color.BLUE);
        loginFormPanel.setBounds(451, 0, 750, 700);

        // logoPanel
        JLabel sideLogoLabel = new JLabel();
        ImageIcon logo = new ImageIcon("lib/logo_withoutBg.png");
        sideLogoLabel.setIcon(logo);
        sideLogoLabel.setText("© 2021 IND Bank Ltd. All Rights Reserved.");
        sideLogoLabel.setHorizontalTextPosition(JLabel.CENTER);
        sideLogoLabel.setVerticalTextPosition(JLabel.BOTTOM);
        sideLogoLabel.setIconTextGap(100);
        sideLogoLabel.setForeground(new Color(145, 141, 145));
        logoPanel.add(sideLogoLabel);

        // loginFormPanel
        JLabel txtLogin = new JLabel("Login Here");
        txtLogin.setFont(new Font("Arial", Font.BOLD, 30));
        JLabel anLabel = new JLabel("Account No.");
        anLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel pinLabel = new JLabel("PIN");
        pinLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField anTextField = new JTextField();
        anTextField.setFont(new Font("Arial", Font.BOLD, 18));
        JPasswordField pinField = new JPasswordField();
        pinField.setFont(new Font("Arial", Font.BOLD, 18));
        JButton loginButton = new JButton("Login");
        JButton forgotPass = new JButton("Forgot Password");
        JButton cnaButton = new JButton("Create New Account");

        txtLogin.setBounds(290, 130, 200, 40);
        anLabel.setBounds(200, 200, 150, 30);
        anTextField.setBounds(351, 200, 200, 30);
        pinLabel.setBounds(200, 260, 150, 30);
        pinField.setBounds(351, 260, 200, 30);
        forgotPass.setBounds(200, 330, 150, 30);
        loginButton.setBounds(450, 330, 100, 30);
        cnaButton.setBounds(290, 420, 200, 30);

        cnaButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginFormPanel.setVisible(false);
                rPanel.registrationPanel.setVisible(true);
            };

        });

        forgotPass.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                loginFormPanel.setVisible(false);
                fPanel.forgotPanel.setVisible(true);
            }
        });

        loginFormPanel.add(txtLogin);
        loginFormPanel.add(anLabel);
        loginFormPanel.add(anTextField);
        loginFormPanel.add(pinLabel);
        loginFormPanel.add(pinField);
        loginFormPanel.add(forgotPass);
        loginFormPanel.add(loginButton);
        loginFormPanel.add(cnaButton);
        loginFormPanel.setLayout(new BorderLayout());

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Connection con = null;
                PreparedStatement ps = null;
                String acn = anTextField.getText();
                String pin = String.valueOf(pinField.getPassword());
                ResultSet rs = null;
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
                    String query = "SELECT * FROM userdata WHERE AccountNo=? AND Pin=?";
                    ps = con.prepareStatement(query);
                    ps.setString(1, acn);
                    ps.setString(2, pin);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(loginFormPanel, "Login Successful!", "Success!",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(loginFormPanel, "Incorrect Credentials.\nTRY AGAIN!!!", "Failure!",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(loginFormPanel, "Unable to login!", "Error!",
                                JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // JFrame frame = new JFrame();
        // frame.setTitle("IND Bank Limited - Client Software");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setResizable(false);
        // frame.setSize(1200, 700);
        // ImageIcon iconLogo = new ImageIcon("lib/iconLogo.png");
        // frame.setIconImage(iconLogo.getImage());
        // frame.setVisible(true);

        // frame.setLayout(null);
        // frame.add(logoPanel);
        // frame.add(loginFormPanel);
        // frame.add(rPanel.registrationPanel);
        // frame.add(fPanel.forgotPanel);
    }
}
// City and address textfeild
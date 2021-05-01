import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;
import javax.swing.*;

public class Registeration {
    JPanel registrationPanel = new JPanel();
    JButton backB = new JButton("< BACK to Login");

    Registeration() {
        // registrationPanel

        // Login l= new Login();
        registrationPanel.setBounds(451, 0, 750, 700);
        registrationPanel.setVisible(false);

        JLabel txtRegister = new JLabel("Register");
        txtRegister.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel txtPI = new JLabel("Personal Information");
        txtPI.setFont(new Font("Arial", Font.BOLD, 22));
        txtPI.setForeground(Color.gray);

        JLabel fnLabel = new JLabel("Full Name");
        fnLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel idLabel = new JLabel("Aadhar Card");
        idLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel pnLabel = new JLabel("Mobile No");
        pnLabel.setFont(new Font("Arial", Font.BOLD, 18));

        String gender[] = { "---Gender---", "Male", "Female", "Other" };
        JComboBox<String> gCB = new JComboBox<String>(gender);// <string> defines data type of ComboBox

        String mStatus[] = { "---Marrital Status---", "Married", "UnMarried" };
        JComboBox<String> msCB = new JComboBox<String>(mStatus);

        String religion[] = { "---Religion---", "Hindu", "Muslim", "Sikh", "Christian", "Others" };
        JComboBox<String> rCB = new JComboBox<String>(religion);

        JLabel dobLabel = new JLabel("Date of Birth");
        dobLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel cityLabel = new JLabel("City");
        cityLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel adrLabel = new JLabel("Address");
        adrLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel txtAI = new JLabel("Account Information");
        txtAI.setFont(new Font("Arial", Font.BOLD, 22));
        txtAI.setForeground(Color.gray);

        String account[] = { "--Account Type--", "Savings", "Current" };
        JComboBox<String> atCB = new JComboBox<String>(account);

        JLabel pinLabel = new JLabel("Pin");
        pinLabel.setFont(new Font("Arial", Font.BOLD, 18));

        String questions[] = { "Security Questions", "What is your pet's name?", "What makes you happy?" };
        JComboBox<String> sqCB = new JComboBox<String>(questions);

        JTextField fnField = new JTextField();
        JTextField idField = new JTextField();
        JTextField pnField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField cityField = new JTextField();
        JTextField adrField = new JTextField();
        JPasswordField pinField = new JPasswordField();
        JTextField ansField = new JTextField();
        JButton createAccount = new JButton("Create Account");

        backB.setBounds(10, 10, 150, 20);
        txtRegister.setBounds(290, 2, 200, 35);
        txtPI.setBounds(255, 30, 280, 40);
        fnLabel.setBounds(200, 65, 150, 18);
        idLabel.setBounds(200, 95, 150, 18);
        pnLabel.setBounds(200, 125, 150, 18);
        fnField.setBounds(351, 65, 200, 20);
        idField.setBounds(351, 95, 200, 20);
        pnField.setBounds(351, 125, 200, 20);
        gCB.setBounds(280, 155, 200, 25);
        msCB.setBounds(280, 195, 200, 25);
        rCB.setBounds(280, 235, 200, 25);
        dobLabel.setBounds(200, 275, 150, 18);
        dobField.setBounds(351, 275, 200, 20);
        cityLabel.setBounds(200, 305, 150, 18);
        cityField.setBounds(351, 305, 200, 20);
        adrLabel.setBounds(200, 335, 150, 18);
        adrField.setBounds(351, 335, 200, 20);
        txtAI.setBounds(255, 380, 280, 40);
        atCB.setBounds(280, 430, 200, 25);
        pinLabel.setBounds(200, 470, 150, 18);
        pinField.setBounds(351, 470, 200, 20);
        ansField.setBounds(351, 505, 200, 20);
        sqCB.setBounds(200, 505, 140, 25);

        createAccount.setBounds(295, 600, 150, 25);

        createAccount.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Connection con = null;
                PreparedStatement ps = null;
                try {
                    // // Class.forName("com.mysql.cj.jdbc.Driver");
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
                    String name = fnField.getText();
                    String acn = idField.getText();
                    String mno = fnField.getText();
                    String gend = gCB.getSelectedItem().toString();
                    String relg = rCB.getSelectedItem().toString();
                    String ms = msCB.getSelectedItem().toString();
                    String dob = dobField.getText();
                    String city = cityField.getText();
                    String addr = adrField.getText();
                    String pin = String.valueOf(pinField.getPassword());
                    String acty = atCB.getSelectedItem().toString();
                    String bal = "1000";
                    String sQu = sqCB.getSelectedItem().toString();
                    String ans = ansField.getText();

                    Random rnd = new Random();
                    int accountNumber = 10000000 + rnd.nextInt(90000000);

                    String query = "INSERT INTO userdata VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    ps = con.prepareStatement(query);
                    ps.setString(1, name);
                    ps.setString(2, acn);
                    ps.setString(3, mno);
                    ps.setString(4, gend);
                    ps.setString(5, ms);
                    ps.setString(6, relg);
                    ps.setString(7, dob);
                    ps.setString(8, city);
                    ps.setString(9, addr);
                    ps.setString(10, String.valueOf(accountNumber));
                    ps.setString(11, pin);
                    ps.setString(12, acty);
                    ps.setString(13, bal);
                    ps.setString(14, sQu);
                    ps.setString(15, ans);

                    int conf = ps.executeUpdate();
                    if (conf > 0) {
                        JOptionPane.showMessageDialog(registrationPanel,
                                "Your Account is Successfuly Created.\nYour Account No: " + accountNumber
                                        + "\nPlease take a note of your Account No.\n",
                                "Account Created!", JOptionPane.INFORMATION_MESSAGE);
                        registrationPanel.setVisible(false);
                        Login.loginFormPanel.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(registrationPanel, "Error in creating Account.\nTRY AGAIN!!!",
                                "Error!", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(registrationPanel, "Error in creating Account", "Error!",
                            JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            };
        });

        registrationPanel.add(backB);
        registrationPanel.add(txtRegister);
        registrationPanel.add(txtPI);
        registrationPanel.add(fnLabel);
        registrationPanel.add(fnField);
        registrationPanel.add(idLabel);
        registrationPanel.add(idField);
        registrationPanel.add(pnLabel);
        registrationPanel.add(pnField);
        registrationPanel.add(gCB);
        registrationPanel.add(msCB);
        registrationPanel.add(rCB);
        registrationPanel.add(dobLabel);
        registrationPanel.add(dobField);
        registrationPanel.add(cityLabel);
        registrationPanel.add(cityField);
        registrationPanel.add(adrLabel);
        registrationPanel.add(adrField);
        registrationPanel.add(txtAI);
        registrationPanel.add(atCB);
        registrationPanel.add(pinLabel);
        registrationPanel.add(pinField);
        registrationPanel.add(sqCB);
        registrationPanel.add(ansField);
        registrationPanel.add(createAccount);
        registrationPanel.setLayout(new BorderLayout());

        backB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrationPanel.setVisible(false);
                Login.loginFormPanel.setVisible(true);

            }

        });

    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TfMoney {
    static JPanel tfMoneyPanel = new JPanel();

    private JLabel userAnTxt = new JLabel();
    private JLabel userBalTxt = new JLabel();
    private JTextField txtTfAc = new JTextField();
    private JTextField txtAmt = new JTextField();
    private JPasswordField txtPin = new JPasswordField();

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private boolean isValid = true;

    public void setInfo() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            String query = "SELECT * FROM userdata WHERE AccountNo=?";
            ps = con.prepareStatement(query);
            ps.setString(1, Login.acN);
            rs = ps.executeQuery();
            if (rs.next()) {
                userAnTxt.setText(rs.getString("AccountNo"));
                userBalTxt.setText(rs.getString("Balance"));
            } else {
                JOptionPane.showMessageDialog(tfMoneyPanel, "Incorrect Credentials.\nLogout and TRY AGAIN!!!",
                        "Failure!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(tfMoneyPanel, "Unable to fetch data!", "Error!", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void searchAC() {
        if (txtTfAc.getText().equals(userAnTxt.getText())) {
            JOptionPane.showMessageDialog(null, "Cannot process transfer to same account!", "Same Account",
                    JOptionPane.ERROR_MESSAGE);
        } else {

            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
                String infoquery = "SELECT * FROM userdata WHERE AccountNo=?";
                ps = con.prepareStatement(infoquery);
                ps.setString(1, txtTfAc.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Name of AC Holder: " + rs.getString("Name"),
                            "Account No. Valid!", JOptionPane.INFORMATION_MESSAGE);
                    isValid = true;
                } else {
                    JOptionPane.showMessageDialog(null, "We don't have this account number with us!",
                            "Account No. Invalid!", JOptionPane.ERROR_MESSAGE);
                    isValid = false;

                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(tfMoneyPanel, "Technical Issue", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void transfer() {
        searchAC();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            String userQuery = "SELECT * FROM userdata WHERE AccountNo=? AND Pin=?";
            ps = con.prepareStatement(userQuery);
            ps.setString(1, userAnTxt.getText());
            ps.setString(2, String.valueOf(txtPin.getPassword()));
            rs = ps.executeQuery();
            if (rs.next() && isValid) {
                try {
                    int totalAmt = Integer.parseInt(userBalTxt.getText());
                    int tfAmt = Integer.parseInt(txtAmt.getText());
                    if (tfAmt > totalAmt) {
                        JOptionPane.showMessageDialog(tfMoneyPanel, "Insufficient Balance!", "Failure!",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (tfAmt > 1) {
                            // debit from userAc
                            int balance = totalAmt - tfAmt;
                            String updateQuery = "UPDATE userdata SET Balance='" + balance + "' WHERE AccountNo='"
                                    + userAnTxt.getText() + "'";
                            ps = con.prepareStatement(updateQuery);
                            ps.execute();

                            // credit to tranferAc
                            String tfAcquery = "SELECT * FROM userdata WHERE AccountNo=?";
                            ps = con.prepareStatement(tfAcquery);
                            ps.setString(1, txtTfAc.getText());
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                int tfAcAmt = Integer.parseInt(rs.getString("Balance"));
                                balance = tfAcAmt + tfAmt;
                                String updateTfQuery = "UPDATE userdata SET Balance='" + balance + "' WHERE AccountNo='"
                                        + txtTfAc.getText() + "'";
                                ps = con.prepareStatement(updateTfQuery);
                                ps.execute();
                            }

                            // adding record into transfer table
                            String insertQuery = "INSERT INTO transfers VALUES(?,?,?,?,?)";
                            ps = con.prepareStatement(insertQuery);
                            ps.setString(1, userAnTxt.getText());
                            ps.setString(2, txtTfAc.getText());
                            ps.setString(3, txtAmt.getText());
                            LocalDate date = LocalDate.now();
                            ps.setString(4, date.toString());
                            LocalTime timeObj = LocalTime.now();
                            String time = timeObj.format(formatObj);
                            ps.setString(5, time);

                            int i = ps.executeUpdate();
                            if (i > 0) {
                                JOptionPane.showMessageDialog(tfMoneyPanel,
                                        "Transfer of Rs. " + txtAmt.getText() + " is Successful!", "Success!",
                                        JOptionPane.INFORMATION_MESSAGE);
                                setInfo();
                                txtAmt.setText("");
                                txtPin.setText("");
                                txtTfAc.setText("");
                            } else {
                                JOptionPane.showMessageDialog(tfMoneyPanel, "Unable to insert data!", "Failure!",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(tfMoneyPanel, "Negative Values are prohibited", "Failure!",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(tfMoneyPanel, "Enter Amount in DIGITS!", "Failure!",
                            JOptionPane.WARNING_MESSAGE);
                }

            } else {
                if (!isValid) {
                    JOptionPane.showMessageDialog(tfMoneyPanel, "Please Enter a valid AC No!!.", "Failure!",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(tfMoneyPanel, "Incorrect Credentials.", "Failure!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tfMoneyPanel, "Technical Issue", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    TfMoney() {
        tfMoneyPanel.setVisible(false);
        tfMoneyPanel.setBounds(300, 0, 900, 700);

        JLabel title = new JLabel("Transfer Money");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.black);

        JLabel userAnLabel = new JLabel("Account No. :");
        userAnLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        JLabel userBalLabel = new JLabel("Account Bal. :");
        userBalLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        JLabel tfAnLabel = new JLabel("To AC No. :");
        tfAnLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        JLabel amtLabel = new JLabel("Amount :");
        amtLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        JLabel pinLabel = new JLabel("Pin:");
        pinLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        JButton btnTransfer = new JButton("Transfer");
        btnTransfer.setFont(new Font("Arial", Font.BOLD, 15));
        btnTransfer.setBackground(Color.RED);
        btnTransfer.setForeground(Color.BLACK);

        btnTransfer.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                transfer();
            }
        });

        JButton btnSearch = new JButton("Search AC");
        btnSearch.setFont(new Font("Arial", Font.BOLD, 15));
        btnSearch.setBackground(Color.GRAY);
        btnSearch.setForeground(Color.white);

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                searchAC();
            }
        });

        userAnTxt.setFont(new Font("Arial", Font.BOLD, 20));
        userBalTxt.setFont(new Font("Arial", Font.BOLD, 20));
        txtTfAc.setFont(new Font("Arial", Font.BOLD, 20));
        txtAmt.setFont(new Font("Arial", Font.BOLD, 20));
        txtPin.setFont(new Font("Arial", Font.BOLD, 20));

        title.setBounds(320, 45, 400, 40);
        userAnLabel.setBounds(200, 120, 150, 30);
        userAnTxt.setBounds(400, 120, 200, 30);
        userBalLabel.setBounds(200, 170, 150, 30);
        userBalTxt.setBounds(400, 170, 200, 30);
        tfAnLabel.setBounds(200, 220, 200, 30);
        txtTfAc.setBounds(400, 220, 200, 30);
        btnSearch.setBounds(620, 220, 130, 30);
        amtLabel.setBounds(200, 270, 150, 30);
        txtAmt.setBounds(400, 270, 200, 30);
        pinLabel.setBounds(200, 320, 150, 30);
        txtPin.setBounds(400, 320, 200, 30);
        btnTransfer.setBounds(300, 400, 180, 40);

        tfMoneyPanel.add(title);
        tfMoneyPanel.add(userAnLabel);
        tfMoneyPanel.add(userAnTxt);
        tfMoneyPanel.add(userBalLabel);
        tfMoneyPanel.add(userBalTxt);
        tfMoneyPanel.add(tfAnLabel);
        tfMoneyPanel.add(txtTfAc);
        tfMoneyPanel.add(amtLabel);
        tfMoneyPanel.add(txtAmt);
        tfMoneyPanel.add(pinLabel);
        tfMoneyPanel.add(txtPin);
        tfMoneyPanel.add(btnTransfer);
        tfMoneyPanel.add(btnSearch);
        tfMoneyPanel.setBackground(new Color(239, 251, 198));
        tfMoneyPanel.setLayout(new BorderLayout());
    }
}

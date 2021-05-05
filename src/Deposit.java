import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deposit {
    static JPanel depositPanel = new JPanel();

    private JLabel txtAn = new JLabel();
    private JLabel txtBal = new JLabel();
    private JTextField txtDamt = new JTextField();
    private JPasswordField txtPin = new JPasswordField();

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public void setInfo() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            String query = "SELECT * FROM userdata WHERE AccountNo=?";
            ps = con.prepareStatement(query);
            ps.setString(1, Login.acN);
            rs = ps.executeQuery();
            if (rs.next()) {
                txtAn.setText(rs.getString("AccountNo"));
                txtBal.setText(rs.getString("Balance"));
            } else {
                JOptionPane.showMessageDialog(depositPanel, "Incorrect Credentials.\nLogout and TRY AGAIN!!!",
                        "Failure!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(depositPanel, "Unable to fetch data!", "Error!", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void depositButton() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            String infoquery = "SELECT * FROM userdata WHERE AccountNo=? AND Pin=?";
            ps = con.prepareStatement(infoquery);
            ps.setString(1, txtAn.getText());
            ps.setString(2, String.valueOf(txtPin.getPassword()));
            rs = ps.executeQuery();
            if (rs.next()) {

                int totalAmt = Integer.parseInt(txtBal.getText());
                int deAmt = Integer.parseInt(txtDamt.getText());

                if (deAmt > 1) {
                    int balance = totalAmt + deAmt;
                    String updateQuery = "UPDATE userdata SET Balance='" + balance + "' WHERE AccountNo='"
                            + txtAn.getText() + "'";
                    ps = con.prepareStatement(updateQuery);
                    ps.execute();
                    String insertQuery = "INSERT INTO deposit VALUES(?,?,?,?,?)";
                    ps = con.prepareStatement(insertQuery);
                    ps.setString(1, txtAn.getText());
                    ps.setString(2, String.valueOf(deAmt));
                    ps.setString(3, String.valueOf(balance));
                    LocalDate date = LocalDate.now();
                    ps.setString(4, date.toString());
                    LocalTime timeObj = LocalTime.now();
                    String time = timeObj.format(formatObj);
                    ps.setString(5, time);

                    int i = ps.executeUpdate();
                    if (i > 0) {
                        JOptionPane.showMessageDialog(depositPanel,
                                "Rs. " + deAmt + " Successfuly Deposited!\nNew Balance is Rs. " + balance + "!",
                                "Success!", JOptionPane.INFORMATION_MESSAGE);
                        setInfo();
                        txtDamt.setText("");
                        txtPin.setText("");
                    } else {
                        JOptionPane.showMessageDialog(depositPanel, "Unable to insert data!", "Failure!",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(depositPanel, "Negative Values are prohibited", "Failure!",
                            JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(depositPanel, "Incorrect Credentials.", "Failure!",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(depositPanel, "Unable to fetch data!", "Error!", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    Deposit() {
        depositPanel.setBounds(300, 0, 900, 700);
        JLabel title = new JLabel("Deposit Money");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.black);

        JLabel anLabel = new JLabel("Account No. :");
        anLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        JLabel balLabel = new JLabel("Account Bal. :");
        balLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        JLabel pinLabel = new JLabel("Pin :");
        pinLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        JLabel withAmtLabel = new JLabel("Amount :");
        withAmtLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        JButton btnDeposit = new JButton("Deposit Money");
        btnDeposit.setFont(new Font("Arial", Font.BOLD, 15));
        btnDeposit.setBackground(Color.GREEN);
        // btnDeposit.setForeground(Color.BLACK);

        txtAn.setFont(new Font("Arial", Font.BOLD, 20));
        txtBal.setFont(new Font("Arial", Font.BOLD, 20));
        txtDamt.setFont(new Font("Arial", Font.BOLD, 20));
        txtPin.setFont(new Font("Arial", Font.BOLD, 20));

        title.setBounds(320, 45, 400, 40);
        anLabel.setBounds(300, 200, 150, 30);
        txtAn.setBounds(500, 200, 200, 30);
        balLabel.setBounds(300, 250, 150, 30);
        txtBal.setBounds(500, 250, 250, 30);
        withAmtLabel.setBounds(300, 300, 150, 30);
        txtDamt.setBounds(500, 300, 200, 30);
        pinLabel.setBounds(300, 350, 150, 30);
        txtPin.setBounds(500, 350, 200, 30);
        btnDeposit.setBounds(390, 420, 200, 40);

        btnDeposit.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                depositButton();
            }
        });

        depositPanel.add(title);
        depositPanel.add(anLabel);
        depositPanel.add(txtAn);
        depositPanel.add(balLabel);
        depositPanel.add(txtBal);
        depositPanel.add(withAmtLabel);
        depositPanel.add(txtDamt);
        depositPanel.add(pinLabel);
        depositPanel.add(txtPin);
        depositPanel.add(btnDeposit);

        depositPanel.setBackground(new Color(239, 251, 198));
        depositPanel.setLayout(new BorderLayout());
    }
}

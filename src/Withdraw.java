import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Withdraw {
    static JPanel withdrawPanel = new JPanel();

    private JLabel txtAn = new JLabel();
    private JLabel txtBal = new JLabel();
    private JTextField txtWamt = new JTextField();
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
                JOptionPane.showMessageDialog(withdrawPanel, "Incorrect Credentials.\nLogout and TRY AGAIN!!!",
                        "Failure!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(withdrawPanel, "Unable to fetch data!", "Error!", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void withdrawButton() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            String infoquery = "SELECT * FROM userdata WHERE AccountNo=? AND Pin=?";
            ps = con.prepareStatement(infoquery);
            ps.setString(1, txtAn.getText());
            ps.setString(2, String.valueOf(txtPin.getPassword()));
            rs = ps.executeQuery();
            if (rs.next()) {

                int totalAmt = Integer.parseInt(txtBal.getText());
                int wdAmt = Integer.parseInt(txtWamt.getText());

                if (wdAmt > totalAmt && wdAmt > 1) {
                    JOptionPane.showMessageDialog(withdrawPanel,
                            "Insufficient Balance!",
                            "Failure!", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (wdAmt > 1) {
                        int balance = totalAmt - wdAmt;
                        String updateQuery = "UPDATE userdata SET Balance='" + balance + "' WHERE AccountNo='"
                                + txtAn.getText() + "'";
                        ps = con.prepareStatement(updateQuery);
                        ps.execute();
                        String insertQuery = "INSERT INTO withdraw VALUES(?,?,?,?,?)";
                        ps = con.prepareStatement(insertQuery);
                        ps.setString(1, txtAn.getText());
                        ps.setString(2, String.valueOf(wdAmt));
                        ps.setString(3, String.valueOf(balance));
                        LocalDate date = LocalDate.now();
                        ps.setString(4, date.toString());
                        LocalTime timeObj = LocalTime.now();
                        String time = timeObj.format(formatObj);
                        ps.setString(5, time);

                        int i = ps.executeUpdate();
                        if (i > 0) {
                            JOptionPane.showMessageDialog(withdrawPanel,
                                    "Rs. " + wdAmt + " Successfuly Withdrawn!\nNew Balance is Rs. " + balance + "!",
                                    "Success!", JOptionPane.INFORMATION_MESSAGE);
                            setInfo();
                            txtWamt.setText("");
                            txtPin.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(withdrawPanel, "Negative Values are prohibited", "Failure!",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
            } else {
                JOptionPane.showMessageDialog(withdrawPanel, "Incorrect Credentials.", "Failure!",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(withdrawPanel, "Unable to fetch data!", "Error!", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    Withdraw() {
        withdrawPanel.setBounds(300, 0, 900, 700);
        JLabel title = new JLabel("Withdraw Amount");
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

        JButton btnWithdraw = new JButton("Withdraw Money");
        btnWithdraw.setFont(new Font("Arial", Font.BOLD, 15));
        btnWithdraw.setBackground(Color.RED);
        btnWithdraw.setForeground(Color.white);

        txtAn.setFont(new Font("Arial", Font.BOLD, 20));
        txtBal.setFont(new Font("Arial", Font.BOLD, 20));
        txtWamt.setFont(new Font("Arial", Font.BOLD, 20));
        txtPin.setFont(new Font("Arial", Font.BOLD, 20));

        title.setBounds(320, 45, 400, 40);
        anLabel.setBounds(300, 200, 150, 30);
        txtAn.setBounds(500, 200, 200, 30);
        balLabel.setBounds(300, 250, 150, 30);
        txtBal.setBounds(500, 250, 250, 30);
        withAmtLabel.setBounds(300, 300, 150, 30);
        txtWamt.setBounds(500, 300, 200, 30);
        pinLabel.setBounds(300, 350, 150, 30);
        txtPin.setBounds(500, 350, 200, 30);
        btnWithdraw.setBounds(390, 420, 200, 40);

        btnWithdraw.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                withdrawButton();
            }
        });

        withdrawPanel.setBackground(new Color(239, 251, 198));
        withdrawPanel.add(title);
        withdrawPanel.add(anLabel);
        withdrawPanel.add(txtAn);
        withdrawPanel.add(balLabel);
        withdrawPanel.add(txtBal);
        withdrawPanel.add(withAmtLabel);
        withdrawPanel.add(txtWamt);
        withdrawPanel.add(pinLabel);
        withdrawPanel.add(txtPin);
        withdrawPanel.add(btnWithdraw);
        withdrawPanel.setLayout(new BorderLayout());
    }
}

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Withdraw {
    static JPanel withdrawPanel = new JPanel();

    private JLabel txtAn = new JLabel();
    private JLabel txtBal = new JLabel();
    private JTextField wAmt = new JTextField();
    private JPasswordField txtPin = new JPasswordField();

    public void setInfo() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
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

        txtAn.setFont(new Font("Arial", Font.BOLD, 20));
        txtBal.setFont(new Font("Arial", Font.BOLD, 20));
        wAmt.setFont(new Font("Arial", Font.BOLD, 20));
        txtPin.setFont(new Font("Arial", Font.BOLD, 20));

        title.setBounds(320, 45, 400, 40);
        anLabel.setBounds(300, 200, 150, 30);
        txtAn.setBounds(500, 200, 200, 30);
        balLabel.setBounds(300, 250, 150, 30);
        txtBal.setBounds(500, 250, 250, 30);
        withAmtLabel.setBounds(300, 300, 150, 30);
        wAmt.setBounds(500, 300, 200, 30);
        pinLabel.setBounds(300, 350, 150, 30);
        txtPin.setBounds(500, 350, 200, 30);

        withdrawPanel.setBackground(new Color(239, 251, 198));
        withdrawPanel.add(title);
        withdrawPanel.add(anLabel);
        withdrawPanel.add(txtAn);
        withdrawPanel.add(balLabel);
        withdrawPanel.add(txtBal);
        withdrawPanel.add(withAmtLabel);
        withdrawPanel.add(wAmt);
        withdrawPanel.add(pinLabel);
        withdrawPanel.add(txtPin);
        withdrawPanel.setLayout(new BorderLayout());
    }
}

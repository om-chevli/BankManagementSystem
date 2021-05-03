import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountInfo {
    static JPanel accInfoPanel = new JPanel();

    private JLabel txtAn = new JLabel();
    private JLabel txtG = new JLabel();
    private JLabel txtAt = new JLabel();
    private JLabel txtBal = new JLabel();

    public void setAccountInfo() {
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
                txtG.setText(rs.getString("Gender"));
                txtAt.setText(rs.getString("AccountType"));
                txtBal.setText(rs.getString("Balance"));
            } else {
                JOptionPane.showMessageDialog(accInfoPanel, "Incorrect Credentials.\nLogout and TRY AGAIN!!!(from aI)",
                        "Failure!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(accInfoPanel, "Unable to fetch data!", "Error!", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    AccountInfo() {
        accInfoPanel.setVisible(false);
        accInfoPanel.setBounds(300, 0, 900, 700);
        JLabel title = new JLabel("Account Information");
        title.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel anLabel = new JLabel("Account No. :");
        anLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        txtAn.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel gLabel = new JLabel("Gender :");
        gLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        txtG.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel atLabel = new JLabel("Account Type :");
        atLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        txtAt.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel balLabel = new JLabel("Account Bal. :");
        balLabel.setFont(new Font("Arial", Font.ITALIC, 20));

        txtBal.setFont(new Font("Arial", Font.BOLD, 20));

        title.setForeground(Color.gray);
        title.setBounds(320, 45, 400, 40);
        anLabel.setBounds(300, 200, 150, 30);
        txtAn.setBounds(500, 200, 200, 30);
        gLabel.setBounds(300, 250, 150, 30);
        txtG.setBounds(500, 250, 250, 30);
        atLabel.setBounds(300, 300, 150, 30);
        txtAt.setBounds(500, 300, 200, 30);
        balLabel.setBounds(300, 350, 150, 30);
        txtBal.setBounds(500, 350, 200, 30);

        accInfoPanel.add(title);
        accInfoPanel.add(anLabel);
        accInfoPanel.add(txtAn);
        accInfoPanel.add(gLabel);
        accInfoPanel.add(txtG);
        accInfoPanel.add(atLabel);
        accInfoPanel.add(txtAt);
        accInfoPanel.add(balLabel);
        accInfoPanel.add(txtBal);
        accInfoPanel.setBackground(new Color(239, 251, 198));
        accInfoPanel.setLayout(new BorderLayout());
    }
}

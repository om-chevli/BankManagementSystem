import javax.swing.*;
import java.awt.*;

public class AccountInfo {
    static JPanel accInfoPanel = new JPanel();

    AccountInfo() {
        // accInfoPanel.setVisible(false);
        accInfoPanel.setBounds(300, 0, 900, 700);
        JLabel title = new JLabel("Account Information");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.black);
        title.setBounds(320,45,400,40);
        accInfoPanel.add(title);
        accInfoPanel.setBackground(new Color(239,251,198));
        accInfoPanel.setLayout(new BorderLayout());
    }
}

import javax.swing.*;
import java.awt.*;

public class Withdraw {
    static JPanel withdrawPanel = new JPanel();

    Withdraw() {
        // withdrawPanel.setVisible(false);
        withdrawPanel.setBounds(300, 0, 900, 700);
        JLabel title = new JLabel("Withdraw");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.black);
        title.setBounds(320, 45, 400, 40);
        withdrawPanel.setBackground(new Color(239, 251, 198));
        withdrawPanel.add(title);
        withdrawPanel.setLayout(new BorderLayout());
    }
}

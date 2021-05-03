import javax.swing.*;
import java.awt.*;

public class Deposit {
    static JPanel depositPanel = new JPanel();

    Deposit() {
        depositPanel.setVisible(false);
        depositPanel.setBounds(300, 0, 900, 700);
        JLabel title = new JLabel("Deposit Money");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.black);
        title.setBounds(320, 45, 400, 40);
        depositPanel.add(title);
        depositPanel.setBackground(new Color(239, 251, 198));
        depositPanel.setLayout(new BorderLayout());
    }
}

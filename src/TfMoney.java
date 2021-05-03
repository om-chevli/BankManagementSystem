import javax.swing.*;
import java.awt.*;

public class TfMoney {
    static JPanel tfMoneyPanel = new JPanel();

    TfMoney() {
        tfMoneyPanel.setVisible(false);
        tfMoneyPanel.setBounds(300, 0, 900, 700);
        JLabel title = new JLabel("Transfer Money");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.black);
        title.setBounds(320, 45, 400, 40);
        tfMoneyPanel.add(title);
        tfMoneyPanel.setBackground(new Color(239, 251, 198));
        tfMoneyPanel.setLayout(new BorderLayout());
    }
}

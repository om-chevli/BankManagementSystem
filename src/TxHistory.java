import javax.swing.*;
import java.awt.*;

public class TxHistory {
    static JPanel txHistoryPanel = new JPanel();

    TxHistory() {
        txHistoryPanel.setVisible(false);
        txHistoryPanel.setBounds(300, 0, 900, 700);
        JLabel title = new JLabel("Transcation Panel");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.black);
        title.setBounds(320, 45, 400, 40);
        txHistoryPanel.setBackground(new Color(239, 251, 198));
        txHistoryPanel.add(title);
        txHistoryPanel.setLayout(new BorderLayout());
    }
}

import javax.swing.*;
import java.awt.*;
public class ChangePin {
    static JPanel changePinPanel = new JPanel();
    ChangePin() {
        changePinPanel.setVisible(false);
        changePinPanel.setBounds(300, 0, 900, 700);
        JLabel title = new JLabel("Change Pin");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.black);
        title.setBounds(320,45,400,40);
        changePinPanel.setBackground(new Color(239,251,198));
        changePinPanel.add(title);
        changePinPanel.setLayout(new BorderLayout());
    }
}

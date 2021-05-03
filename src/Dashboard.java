import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;

public class Dashboard {
    static JPanel contentPanel = new JPanel();
    static JPanel menuPanel = new JPanel();
    static JPanel mainPanel = new JPanel();
    static AccountInfo acinfoRef = new AccountInfo();
    static Withdraw withdrawRef = new Withdraw();
    static Deposit depoRef = new Deposit();
    static TfMoney tfRef = new TfMoney();
    static TxHistory txRef = new TxHistory();
    static ChangePin chpin = new ChangePin();

    private static void styleMenuButton(JButton varName) {
        varName.setFont(new Font("Arial", Font.BOLD, 15));
        varName.setBackground(Color.GRAY);
        varName.setForeground(Color.white);
    }

    // private static void repaintPanel

    public static void main(String[] args) {

        menuPanel.setBounds(0, 0, 300, 700);
        menuPanel.setBackground(new Color(31, 30, 31));
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setLayout(null);
        contentPanel.setLayout(null);
        contentPanel.setBounds(300, 0, 900, 700);

        String name = "User";
        JLabel txtGreetings = new JLabel("Hello " + name + "!");
        txtGreetings.setFont(new Font("Arial", Font.BOLD, 18));
        txtGreetings.setForeground(Color.white);
        JLabel txtUD = new JLabel("User Dashboard");
        txtUD.setFont(new Font("Arial", Font.BOLD, 30));
        txtUD.setForeground(Color.gray);

        JButton accInfoButton = new JButton("Account Information");
        styleMenuButton(accInfoButton);

        JButton withdrawButton = new JButton("Withdraw");
        styleMenuButton(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        styleMenuButton(depositButton);

        JButton txButton = new JButton("Transfer History");
        styleMenuButton(txButton);

        JButton transferButton = new JButton("Transfer Money");
        styleMenuButton(transferButton);

        JButton chPinButton = new JButton("Change Pin");
        styleMenuButton(chPinButton);

        JButton logoutButton = new JButton("Logout!");
        styleMenuButton(logoutButton);

        txtUD.setBounds(320, 45, 400, 40);
        txtGreetings.setBounds(30, 45, 200, 30);
        accInfoButton.setBounds(20, 150, 230, 40);
        withdrawButton.setBounds(20, 220, 230, 40);
        depositButton.setBounds(20, 290, 230, 40);
        txButton.setBounds(20, 360, 230, 40);
        transferButton.setBounds(20, 430, 230, 40);
        chPinButton.setBounds(20, 500, 230, 40);
        logoutButton.setBounds(20, 600, 230, 40);
        logoutButton.setBackground(Color.red);

        menuPanel.add(txtGreetings);
        menuPanel.add(accInfoButton);
        menuPanel.add(withdrawButton);
        menuPanel.add(depositButton);
        menuPanel.add(txButton);
        menuPanel.add(transferButton);
        menuPanel.add(chPinButton);
        menuPanel.add(logoutButton);

        contentPanel.add(txtUD);

        mainPanel.add(contentPanel);
        mainPanel.add(menuPanel);
        mainPanel.setBounds(0, 0, 1200, 700);

        mainPanel.setLayout(new BorderLayout());

        JFrame f = new JFrame();
        f.setTitle("IND Bank Limited - Client Software");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(1200, 700);
        ImageIcon iconLogo = new ImageIcon("lib/iconLogo.png");
        f.setIconImage(iconLogo.getImage());
        f.setVisible(true);
        f.setLayout(null);
        f.add(mainPanel);

        Component aIJcomps[] = AccountInfo.accInfoPanel.getComponents();
        Component whcomps[] = Withdraw.withdrawPanel.getComponents();
        Component deComps[]= Deposit.depositPanel.getComponents();
        Component tsfComps[]=TfMoney.tfMoneyPanel.getComponents();
        Component txComps[]= TxHistory.txHistoryPanel.getComponents();
        Component chPComps[]= ChangePin.changePinPanel.getComponents();
        accInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                contentPanel.removeAll();
                contentPanel.repaint();
                for (Component comp : aIJcomps) {
                    contentPanel.add(comp);
                    contentPanel.repaint();
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                contentPanel.removeAll();
                contentPanel.repaint();
                for (Component comp : whcomps) {
                    contentPanel.add(comp);
                    contentPanel.repaint();
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                contentPanel.removeAll();
                contentPanel.repaint();
                for (Component comp : deComps) {
                    contentPanel.add(comp);
                    contentPanel.repaint();
                }
            }
        });

        txButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                contentPanel.removeAll();
                contentPanel.repaint();
                for (Component comp : txComps) {
                    contentPanel.add(comp);
                    contentPanel.repaint();
                }
            }
        });

        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                contentPanel.removeAll();
                contentPanel.repaint();
                for (Component comp : tsfComps) {
                    contentPanel.add(comp);
                    contentPanel.repaint();
                }
            }
        });

        chPinButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                contentPanel.removeAll();
                contentPanel.repaint();
                for (Component comp : chPComps) {
                    contentPanel.add(comp);
                    contentPanel.repaint();
                }
            }
        });
    }
}

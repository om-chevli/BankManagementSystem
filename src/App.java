import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {
        Login.main(args);
        
        JFrame frame = new JFrame();
        
        frame.setTitle("IND Bank Limited - Client Software");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1200, 700);
        ImageIcon iconLogo = new ImageIcon("lib/iconLogo.png");
        frame.setIconImage(iconLogo.getImage());
        frame.setVisible(true);

        frame.setLayout(null);
        frame.add(Login.loginFormPanel);
        frame.add(Login.logoPanel);
        frame.add(Registeration.registrationPanel);
        frame.add(ForgotPassword.forgotPanel);
        frame.add(Dashboard.mainPanel);
    }
}

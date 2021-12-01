import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 * 
 */
public class Supervisor extends Login implements workDetails {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Default constructor
     */

    public Supervisor() {

        JFrame f = new JFrame();// creating instance of JFrame
        f.getContentPane().invalidate();
        f.setSize((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
        f.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH); // full size frame
        f.getContentPane().setBackground(Color.decode("#f0f0f0"));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight() / 4);
        headerPanel.setBackground(Color.decode("#f0f0f0"));

        ImageIcon logo = new ImageIcon(ConstructionManagment.class.getResource("/Images/construction_logo.png"));
        JLabel logoLabel = new JLabel(logo, SwingConstants.LEFT);

        JLabel titleLabel = new JLabel("Albatross Builders");
        titleLabel.setHorizontalAlignment(SwingConstants.LEADING);
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 48));

        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);

        f.add(headerPanel);
        f.setVisible(true);// making the frame visible
        // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// terminate program when
        // closes frame
        login();
    }

    /**
     * 
     */
    // public void s_id;

    /**
     * 
     */
    // public void s_Name;

    /**
     * 
     */
    public void seeAllworkReq() {
        // TODO implement here
    }

    /**
     * @param value
     */
    public void acceptWorkReq(int value) {
        // TODO implement here
    }

    /**
     * 
     */
    public void updateSitedetails() {
        // TODO implement here
    }

    /**
     * @return
     */
    public void getS_id() {
        // TODO implement here
        // return null;
    }

    /**
     * @return
     */
    public void getS_Name() {
        // TODO implement here
        // return null;
    }

    /**
     * @param value
     */
    public void setS_Name(int value) {
        // TODO implement here
    }

    /**
     * 
     */

    /**
     * 
     */

    @Override
    public void login() {
        // TODO Auto-generated method stub

        System.out.print("Login to Supervisor");

        JFrame loginFrame = new JFrame();
        loginFrame.setLayout(new FlowLayout());      
        loginFrame.setSize((int) screenSize.getWidth() / 2, (int) screenSize.getHeight()/4);
        loginFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);

       

        JLabel userLabel = new JLabel();
        userLabel.setText("<html>&nbsp;&nbsp;&nbsp;Username</html>"); // set label value for textField1
        userLabel.setBackground(Color.DARK_GRAY);
        

        // create text field to get username from the user
        JTextField usernameField = new JTextField(15); // set length of the text

        // create label for password
        JLabel passLabel = new JLabel();
        passLabel.setText("<html>&nbsp;&nbsp;&nbsp;Password</html>"); // set label value for textField2

        // create text field to get password from the user
        JPasswordField passwordField = new JPasswordField(15);

        JButton submitBtn = new JButton("<html>&nbsp;Submit</html>");
        submitBtn.setBackground(Color.decode("#40392f"));
        submitBtn.setForeground(Color.decode("#ebc38a"));
        submitBtn.setFocusPainted(false);

        JPanel usernamePanel = new JPanel((new FlowLayout(FlowLayout.LEFT)));  
        usernamePanel.add(userLabel);
        usernamePanel.add(usernameField);
        // usernamePanel.setAlignmentX(alignmentX);

        JPanel passwordPanel = new JPanel((new FlowLayout(FlowLayout.LEADING)));  
        passwordPanel.add(passLabel);
        passwordPanel.add(passwordField);

        JPanel submitPanel = new JPanel((new FlowLayout()));  
        submitPanel.setAlignmentY(SwingConstants.BOTTOM);
        submitPanel.add(submitBtn);
        
        // loginPanel.add(userLabel);    //set username label to panel  
        // loginPanel.add(usernameField);   //set text field to panel  
        // loginPanel.add(passLabel);    //set password label to panel  
        // loginPanel.add(passwordField);   //set text field to panel  
        // loginPanel.add(submitBtn);

        loginFrame.add(usernamePanel);
        loginFrame.add(passwordPanel);
        loginFrame.add(submitPanel);
        loginFrame.setVisible(true);

    }

    @Override
    public void logout() {
        // TODO Auto-generated method stub

    }

    @Override
    public void viewWork(int value) {
        // TODO Auto-generated method stub

    }

}
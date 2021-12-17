
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.util.UUID;

public class Admin extends Login implements workDetails, ActionListener {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    JFrame f;
    JTextField usernameField, loginUsernameField;
    JButton loginSubmitBtn, goTologinBtn, addClntBtn;
    // ss
    JTextField mailIdField, phoneField, fName, lName;
    JPasswordField passwordField, loginPasswordField;
    JPasswordField cnfPasswordField;
    Boolean loginStatus = false;
    JFrame createFrame;
    JFrame loginFrame;

    Config connection = new Config();
    Connection con = connection.dbConnect();
    public String StringId;
    private String name;

    public Admin() {
        login();
    }

    /**
     * 
     */
    // public void ad_id;

    /**
     * 
     */
    // public void ad_name;

    /**
     * 
     */
    public void addSupervisor() {
        // TODO implement here
    }

    /**
     * 
     */
    public void viewAllWorks() {
        // TODO implement here
    }

    /**
     * @return
     */
    // public void getAd_id() {
    // // TODO implement here
    // return null;
    // }

    /**
     * @return
     */
    // public void getAd_name() {
    // // TODO implement here
    // return null;
    // }

    /**
     * @param value
     */
    // public void setAd_name(void value) {
    // // TODO implement here
    // }

    /**
     * 
     */

    /**
     * 
     */

    @Override
    public void login() {
        System.out.print("Login as Admin");

        loginFrame = new JFrame();
        loginFrame.setLayout(new GridLayout(4, 0));
        loginFrame.setSize((int) screenSize.getWidth() / 2, (int) (screenSize.getHeight() + 250) / 4);
        loginFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);

        JPanel titlePanel = new JPanel();
        JLabel adminLabel = new JLabel("Admin Login");
        adminLabel.setFont(new Font("SansSerif", Font.PLAIN, 38));
        titlePanel.add(adminLabel);
        JLabel userLabel = new JLabel();
        userLabel.setText("<html>&nbsp;&nbsp;&nbsp;Username</html>"); // set label value for textField1
        userLabel.setBackground(Color.DARK_GRAY);

        // create text field to get username from the user
        loginUsernameField = new JTextField(15); // set length of the text

        // create label for password
        JLabel passLabel = new JLabel();
        passLabel.setText("<html>&nbsp;&nbsp;&nbsp;Password</html>"); // set label value for textField2

        // create text field to get password from the user
        loginPasswordField = new JPasswordField(15);

        loginSubmitBtn = new JButton("<html>&nbsp;Submit</html>");
        loginSubmitBtn.setBackground(Color.decode("#40392f"));
        loginSubmitBtn.setForeground(Color.decode("#ebc38a"));
        loginSubmitBtn.setFocusPainted(false);

        /*
         * createClnBtn = new JButton("<html>&nbsp;Dont have Account?</html>");
         * createClnBtn.setBackground(Color.decode("#40392f"));
         * createClnBtn.setForeground(Color.decode("#ebc38a"));
         * createClnBtn.setFocusPainted(false);
         */

        JPanel usernamePanel = new JPanel();
        usernamePanel.add(userLabel);
        usernamePanel.add(loginUsernameField);
        // usernamePanel.setAlignmentX(alignmentX);

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passLabel);
        passwordPanel.add(loginPasswordField);

        JPanel submitPanel = new JPanel();
        submitPanel.setAlignmentY(SwingConstants.BOTTOM);
        submitPanel.add(loginSubmitBtn);
        // submitPanel.add(createClnBtn);

        loginFrame.add(titlePanel);
        loginFrame.add(usernamePanel);
        loginFrame.add(passwordPanel);
        loginFrame.add(submitPanel);
        loginFrame.setVisible(true);

        loginSubmitBtn.addActionListener(this);

    }

    @Override
    public void logout() {
        // TODO Auto-generated method stub

    }

    public void addClient() {
        JFrame createFrame = new JFrame();
        createFrame.setLayout(new GridLayout(5, 0));
        createFrame.setSize((int) (screenSize.getWidth()), (int) (screenSize.getHeight() / 2));
        createFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);

        JPanel titlePanel = new JPanel();
        JLabel supervisorLabel = new JLabel("Create An Account");
        supervisorLabel.setFont(new Font("SansSerif", Font.PLAIN, 38));
        titlePanel.add(supervisorLabel);
        JLabel userLabel = new JLabel();
        userLabel.setText("<html>Username</html>"); // set label value for textField1
        userLabel.setBackground(Color.DARK_GRAY);

        JLabel fnameLabel = new JLabel();
        fnameLabel.setText("<html>First Name</html>");
        fnameLabel.setBackground(Color.DARK_GRAY);

        JLabel mailLabel = new JLabel();
        mailLabel.setText("<html>Mail Id</html>");
        mailLabel.setBackground(Color.DARK_GRAY);

        JLabel lnameLabel = new JLabel();
        lnameLabel.setText("<html>Last Name</html>");
        lnameLabel.setBackground(Color.DARK_GRAY);
        // create text field to get username from the user

        fName = new JTextField(15); // set length of the text
        lName = new JTextField(15);
        mailIdField = new JTextField(35);
        phoneField = new JTextField(10);
        usernameField = new JTextField(25);

        // create label for password
        JLabel passLabel = new JLabel();
        passLabel.setText("<html>Password</html>"); // set label value for textField2

        JLabel phoneLabel = new JLabel();
        phoneLabel.setText("<html>Phone No: </html>");

        JLabel cnfPassLabel = new JLabel();
        cnfPassLabel.setText("<html>Confirm Password</html>");
        // create text field to get password from the user

        passwordField = new JPasswordField(15);
        cnfPasswordField = new JPasswordField(15);

        goTologinBtn = new JButton("<html>&nbsp;Already have an Accunt, Login?</html>");
        goTologinBtn.setBackground(Color.decode("#40392f"));
        goTologinBtn.setForeground(Color.decode("#ebc38a"));
        goTologinBtn.setFocusPainted(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == loginSubmitBtn) {
            System.out.println("calling ..");
            loadAdminPanel("1");
        } else if (e.getSource() == addClntBtn) {
            addClient();
        }

    }

    /**
     * @param value
     */
    // public abstract void viewWork(void value);

    /**
     * @param value
     */
    // public abstract void viewWork(void value);

    /**
     * @param value
     */
    // public abstract void viewWork(void value);

    public void loadAdminPanel(String stringID) {

        f = new JFrame();// creating instance of JFrame
        f.getContentPane().invalidate();
        f.setSize((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
        f.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH); // full size frame
        f.setLayout(new GridLayout(10, 0));
        f.getContentPane().setBackground(Color.decode("#f0f0f0"));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight() / 8);
        headerPanel.setBackground(Color.decode("#f0f0f0"));

        ImageIcon logo = new ImageIcon(ConstructionManagment.class.getResource("/Images/construction_logo.png"));
        JLabel logoLabel = new JLabel(logo, SwingConstants.LEFT);

        JLabel titleLabel = new JLabel("<html>Albatross Builders</html>");
        titleLabel.setHorizontalAlignment(SwingConstants.LEADING);
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 48));

        JLabel welcomeLabel = new JLabel("<html> Welcome ADMIN <font color='#ebc38a'></font></html>");
        welcomeLabel.setHorizontalAlignment(JLabel.LEFT);
        welcomeLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 35));
        // welcomeLabel.setForeground(Color.decode("#a38f72"));

        // ADDING BUTTONS
        JPanel bodyButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bodyButtons.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight() / 8);
        bodyButtons.setBackground(Color.decode("#f0f0f0"));
        f.setVisible(true);

        JButton seeAllWork = new JButton("SEE ALL WORKS");
        seeAllWork.setBackground(Color.decode("#a39887"));
        seeAllWork.setAlignmentX(100);
        seeAllWork.setFocusPainted(false);
        seeAllWork.addActionListener(this);

        JButton ongoingWork = new JButton("SEE ONGOING WORKS");
        ongoingWork.setBackground(Color.decode("#a39887"));
        ongoingWork.setAlignmentX(100);
        ongoingWork.setFocusPainted(false);
        ongoingWork.addActionListener(this);

        JButton pastWork = new JButton("SEE PAST WORKS");
        pastWork.setBackground(Color.decode("#a39887"));
        pastWork.setAlignmentX(100);
        pastWork.setFocusPainted(false);
        pastWork.addActionListener(this);

        JButton supList = new JButton("SEE ALL SUPERVISORS");
        supList.setBackground(Color.decode("#a39887"));
        supList.setAlignmentX(100);
        supList.setFocusPainted(false);
        supList.addActionListener(this);

        JButton clist = new JButton("SEE ALL CLIENTS");
        clist.setBackground(Color.decode("#a39887"));
        clist.setAlignmentX(100);
        clist.setFocusPainted(false);
        clist.addActionListener(this);

        JButton addSup = new JButton("ADD SUPERVISOR");
        addSup.setBackground(Color.decode("#a39887"));
        addSup.setAlignmentX(100);
        addSup.setFocusPainted(false);
        addSup.addActionListener(this);

        addClntBtn = new JButton("ADD CLIENT");
        addClntBtn.setBackground(Color.decode("#a39887"));
        addClntBtn.setAlignmentX(100);
        addClntBtn.setFocusPainted(false);
        addClntBtn.addActionListener(this);

        // add all components
        f.add(headerPanel);
        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);
        f.add(welcomeLabel);
        f.add(bodyButtons);
        bodyButtons.add(seeAllWork);
        bodyButtons.add(ongoingWork);
        bodyButtons.add(pastWork);
        bodyButtons.add(supList);
        bodyButtons.add(clist);
        bodyButtons.add(addSup);
        bodyButtons.add(addClntBtn);
    }

    @Override
    public void viewWork(int value) {
        // TODO Auto-generated method stub

    }
}
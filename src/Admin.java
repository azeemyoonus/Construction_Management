
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
    JButton loginSubmitBtn, goTologinBtn, addmatBtn, addOfficeStaff, addStaffBtn;
    // ss
    JTextField mailIdField, phoneField, fName, lName, mailArea, setUsenameField, setPassField;
    JPasswordField passwordField, loginPasswordField;
    JPasswordField cnfPasswordField;
    Boolean loginStatus = false;
    JFrame createFrame;

    JFrame loginFrame;

    Config connection = new Config();
    Connection con = connection.dbConnect();
    public String StringId;
    private String name;

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == loginSubmitBtn) {
            System.out.println("calling ..");
            loadAdminPanel("1");
        } else if (e.getSource() == addmatBtn) {
            JFrame addMatFrame = new JFrame();
            addMatFrame.setLayout(new GridLayout(4, 0));
            addMatFrame.setSize((int) screenSize.getWidth() / 2, (int) (screenSize.getHeight() + 550) / 3);
            addMatFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);

            JTextField nameField = new JTextField();
            JTextField costField = new JTextField();
            JTextField sizeField = new JTextField();
            JTextField companyField = new JTextField();

            JLabel nameLabel = new JLabel("Name: ");

            JPanel namePanel = new JPanel();
            namePanel.add(nameLabel);
            namePanel.add(nameField);

            // addMatpanel.add(nameField);
            // addMatpanel.add(new JLabel("cost: "));
            // addMatpanel.add(costField);
            // addMatpanel.add(new JLabel("size: "));
            // addMatpanel.add(sizeField);
            // addMatpanel.add(new JLabel("Comapany: "));
            // addMatpanel.add(companyField);

            // addMatFrame.add(addMatpanel);
            addMatFrame.setVisible(true);

        } else if (e.getSource() == addOfficeStaff) {
            JFrame f = new JFrame();
            f.setVisible(true);
            f.setSize(700, 550);
            f.setLayout(null);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("Add New Staff");

            JLabel headerLabel = new JLabel("Staff Details");
            headerLabel.setForeground(Color.black);
            headerLabel.setFont(new Font("Serif", Font.BOLD, 20));
            headerLabel.setBounds(200, 10, 400, 30);

            JLabel fnameLabel = new JLabel("First name");
            fnameLabel.setFont(new Font("Serif", Font.BOLD, 15));
            fnameLabel.setBounds(80, 70, 200, 30);

            JLabel lnameLabel = new JLabel("Last name");
            fnameLabel.setFont(new Font("Serif", Font.BOLD, 15));
            fnameLabel.setBounds(80, 110, 200, 30);

            JLabel rollLabel = new JLabel("Roll");
            rollLabel.setFont(new Font("Serif", Font.BOLD, 15));
            rollLabel.setBounds(80, 155, 200, 30);

            JLabel mailLabel = new JLabel("Email");
            mailLabel.setFont(new Font("Serif", Font.BOLD, 15));
            mailLabel.setBounds(80, 200, 200, 30);

            JLabel pNum = new JLabel("Phone Number");
            pNum.setFont(new Font("Serif", Font.BOLD, 15));
            pNum.setBounds(80, 250, 200, 30);

            JLabel setUname = new JLabel("Set Username");
            setUname.setFont(new Font("Serif", Font.BOLD, 15));
            setUname.setBounds(80, 300, 200, 30);

            JLabel setPass = new JLabel("Set Password");
            setPass.setFont(new Font("Serif", Font.BOLD, 15));
            setPass.setBounds(80, 350, 200, 30);

            String[] optionsToChoose = { "Admin", "Supervisor" };
            JComboBox<String> rolesComboxBox = new JComboBox<>(optionsToChoose);
            rolesComboxBox.setBackground(Color.white);

            fName = new JTextField();
            fName.setBounds(300, 70, 200, 30);

            lName = new JTextField();
            lName.setBounds(300, 110, 200, 30);

            mailArea = new JTextField();
            mailArea.setBounds(300, 210, 250, 30);

            phoneField = new JTextField();
            phoneField.setBounds(300, 260, 250, 30);

            setUsenameField = new JTextField();
            setUsenameField.setBounds(300, 310, 250, 30);

            setPassField = new JTextField();
            setPassField.setBounds(300, 360, 250, 30);

            addStaffBtn = new JButton("ADD");
            addStaffBtn.setBounds(200, 420, 150, 30);

            rolesComboxBox.setBounds(300, 160, 200, 30);

            f.add(headerLabel);
            f.add(fnameLabel);
            f.add(fName);

            f.add(lnameLabel);
            f.add(lName);

            f.add(rollLabel);
            f.add(rolesComboxBox);

            f.add(mailLabel);
            f.add(mailArea);

            f.add(pNum);
            f.add(phoneField);

            f.add(setUname);
            f.add(usernameField);
            f.add(setPass);
            f.add(passwordField);

            f.add(addOfficeStaff);

        }

    }

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

        addOfficeStaff = new JButton("Ad Office Staff");
        addOfficeStaff.setBackground(Color.decode("#a39887"));
        addOfficeStaff.setAlignmentX(100);
        addOfficeStaff.setFocusPainted(false);
        addOfficeStaff.addActionListener(this);

        addmatBtn = new JButton("Add Materials");
        addmatBtn.setBackground(Color.decode("#a39887"));
        addmatBtn.setFocusPainted(false);
        addmatBtn.addActionListener(this);

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
        bodyButtons.add(addOfficeStaff);
        bodyButtons.add(addmatBtn);
    }

    @Override
    public void viewWork(int value) {
        // TODO Auto-generated method stub

    }
}
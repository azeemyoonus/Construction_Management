import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

/**
 * 
 */
public class Client extends Login implements workDetails, ActionListener {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    JTextField usernameField;
    JTextField fName;
    JTextField lName;
    JTextField mailIdField;
    JPasswordField passwordField;
    JPasswordField cnfPasswordField;
    Boolean loginStatus = false;
    JFrame createFrame;
    JFrame loginFrame;
    JButton loginBtn, loginSubmitBtn, accCreateClnBtn, goTologinBtn;    
    JButton createClnBtn;

    /**
     * Default constructor
     */
    public Client() {
        login();
    }

    public void accountCreate(){
        createFrame = new JFrame();
        createFrame.setLayout(new GridLayout(5, 0));
        createFrame.setSize((int) screenSize.getWidth() / 2, (int) (screenSize.getHeight()/2 ));
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
        lName = new  JTextField(15);
        mailIdField = new JTextField(15);

        // create label for password
        JLabel passLabel = new JLabel();
        passLabel.setText("<html>Password</html>"); // set label value for textField2

        JLabel cnfPassLabel = new JLabel();
        cnfPassLabel.setText("<html>Confirm Password</html>");
        // create text field to get password from the user

        passwordField = new JPasswordField(15);
        cnfPasswordField = new JPasswordField(15);

        goTologinBtn = new JButton("<html>&nbsp;Already have an Accunt, Login?</html>");
        goTologinBtn.setBackground(Color.decode("#40392f"));
        goTologinBtn.setForeground(Color.decode("#ebc38a"));
        goTologinBtn.setFocusPainted(false);

        accCreateClnBtn = new JButton("<html>&nbsp;Create Account</html>");
        accCreateClnBtn.setBackground(Color.decode("#40392f"));
        accCreateClnBtn.setForeground(Color.decode("#ebc38a"));
        accCreateClnBtn.setFocusPainted(false);


        JPanel namePanel = new JPanel();
        namePanel.add(fnameLabel);
        namePanel.add(fName);
        namePanel.add(lnameLabel);
        namePanel.add(lName);
               
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(userLabel);
        usernamePanel.add(usernameField);
        usernamePanel.add(mailLabel);
        usernamePanel.add(mailIdField);
        // usernamePanel.setAlignmentX(alignmentX);

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passLabel);
        passwordPanel.add(passwordField);
        passwordPanel.add(cnfPassLabel);
        passwordPanel.add(cnfPasswordField);

        JPanel submitPanel = new JPanel();
        submitPanel.setAlignmentY(SwingConstants.BOTTOM);
        submitPanel.add(accCreateClnBtn);
        submitPanel.add(goTologinBtn);


        createFrame.add(titlePanel);
        createFrame.add(namePanel);

        createFrame.add(usernamePanel);
        createFrame.add(passwordPanel);
        createFrame.add(submitPanel);
        createFrame.setVisible(true);

        goTologinBtn.addActionListener(this);
        accCreateClnBtn.addActionListener(this);
    }

    @Override
    public void login() {
        // TODO Auto-generated method stub
        System.out.print("Login as Client");

        loginFrame = new JFrame();
        loginFrame.setLayout(new GridLayout(4, 0));
        loginFrame.setSize((int) screenSize.getWidth() / 2, (int) (screenSize.getHeight() + 250) / 4);
        loginFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);

        JPanel titlePanel = new JPanel();
        JLabel supervisorLabel = new JLabel("Client Login");
        supervisorLabel.setFont(new Font("SansSerif", Font.PLAIN, 38));
        titlePanel.add(supervisorLabel);
        JLabel userLabel = new JLabel();
        userLabel.setText("<html>&nbsp;&nbsp;&nbsp;Username</html>"); // set label value for textField1
        userLabel.setBackground(Color.DARK_GRAY);

        // create text field to get username from the user
        usernameField = new JTextField(15); // set length of the text

        // create label for password
        JLabel passLabel = new JLabel();
        passLabel.setText("<html>&nbsp;&nbsp;&nbsp;Password</html>"); // set label value for textField2

        // create text field to get password from the user
        passwordField = new JPasswordField(15);

        loginSubmitBtn = new JButton("<html>&nbsp;Submit</html>");
        loginSubmitBtn.setBackground(Color.decode("#40392f"));
        loginSubmitBtn.setForeground(Color.decode("#ebc38a"));
        loginSubmitBtn.setFocusPainted(false);

        createClnBtn = new JButton("<html>&nbsp;Dont have Account?</html>");
        createClnBtn.setBackground(Color.decode("#40392f"));
        createClnBtn.setForeground(Color.decode("#ebc38a"));
        createClnBtn.setFocusPainted(false);


        JPanel usernamePanel = new JPanel();
        usernamePanel.add(userLabel);
        usernamePanel.add(usernameField);
        // usernamePanel.setAlignmentX(alignmentX);

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passLabel);
        passwordPanel.add(passwordField);


        JPanel submitPanel = new JPanel();
        submitPanel.setAlignmentY(SwingConstants.BOTTOM);
        submitPanel.add(loginSubmitBtn);
        submitPanel.add(createClnBtn);

        
        loginFrame.add(titlePanel);
        loginFrame.add(usernamePanel);
        loginFrame.add(passwordPanel);
        loginFrame.add(submitPanel);
        loginFrame.setVisible(true);

        loginSubmitBtn.addActionListener(this);
        createClnBtn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loginSubmitBtn){
            int supervisorId = 1;
            loadClientPanel(supervisorId);
            loginFrame.dispose();
        }
        else if (e.getSource()== createClnBtn){
            System.out.println("create an client account");
            loginFrame.dispose();
            accountCreate();
        }
        else if (e.getSource()== accCreateClnBtn){
            System.out.println("Please create an account for client");
            createFrame.dispose();
            login();

        }
        else if (e.getSource()== goTologinBtn){
            createFrame.dispose();
            login();
        }

    }

    public void loadClientPanel(int id) {

        // todo fetch loggined supervisor details from database

        JFrame f = new JFrame();// creating instance of JFrame
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
        JButton loginBtn = new JButton("Login");
        loginBtn.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 20));
        welcomePanel.setBackground(Color.decode("#f0f0f0"));

        JLabel welcomeLabel = new JLabel("<html> Welcome <font color='#ebc38a'> Client </font></html>");
        welcomeLabel.setHorizontalAlignment(JLabel.LEFT);
        welcomeLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 35));
        // welcomeLabel.setForeground(Color.decode("#a38f72"));

        JPanel forcurrentWorkLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forcurrentWorkLabel.setBackground(Color.decode("#40392f"));
        forcurrentWorkLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

        JLabel currentWorkLabel = new JLabel("Your Current Work");
        currentWorkLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        currentWorkLabel.setHorizontalAlignment(JLabel.LEFT);
        currentWorkLabel.setForeground(Color.decode("#ebc38a"));

        currentWorkLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));

        forcurrentWorkLabel.add(currentWorkLabel);

        JPanel currentWorkPanel = new JPanel(new GridLayout(2, 0));

        currentWorkPanel.setBackground(Color.decode("#d1c4b2"));
        currentWorkPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

        JLabel currentWID = new JLabel("Work Id : " + "2323222");
        currentWID.setBorder(new EmptyBorder(20, 20, 20, 20));
        currentWID.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        currentWID.setBackground(Color.decode("#f29105"));

        JLabel currentWStart = new JLabel("Work Started : " + " 05-12-2020");
        currentWStart.setBorder(new EmptyBorder(20, 20, 20, 20));
        currentWStart.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        currentWStart.setBackground(Color.decode("#f29105"));

        JLabel currentClientName = new JLabel("Supervisor Name : " + " Anil");
        currentClientName.setBorder(new EmptyBorder(20, 20, 20, 20));
        currentClientName.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        currentClientName.setBackground(Color.decode("#f29105"));

        JLabel currentLocation = new JLabel("Location : " + " Kochi");
        currentLocation.setBorder(new EmptyBorder(20, 20, 20, 20));
        currentLocation.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        currentLocation.setBackground(Color.decode("#f29105"));

        JLabel currentArea = new JLabel("Area (Sq): " + " 1200sqft");
        currentArea.setBorder(new EmptyBorder(20, 20, 20, 20));
        currentArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        currentArea.setBackground(Color.decode("#f29105"));

        JPanel currentWorkOptions = new JPanel(new GridLayout(2, 0, 5, 5));
        // currentWorkOptions.setBackground(Color.decode("#d1c4b2"));
        // currentWorkOptions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // currentWorkOptions.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        // JButton UpdateBtn = new JButton("Update Work Progress");
        // UpdateBtn.setBackground(Color.decode("#a39887"));
        // // UpdateBtn.setForeground(Color.decode("#0a0a0a"));
        // UpdateBtn.setFocusPainted(false);

        JButton materialBtn = new JButton("Used Materials");
        materialBtn.setBackground(Color.decode("#a39887"));
        // materialBtn.setForeground(Color.decode("#0a0a0a"));
        materialBtn.setFocusPainted(false);

        JButton contactClient = new JButton("Contact Supervisor");
        contactClient.setBackground(Color.decode("#a39887"));
        // contactClient.setForeground(Color.decode("#0a0a0a"));
        contactClient.setFocusPainted(false);

        // currentWorkOptions.add(UpdateBtn);
        currentWorkOptions.add(materialBtn);
        currentWorkOptions.add(contactClient);

        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);
        welcomePanel.add(welcomeLabel);
        currentWorkPanel.add(currentWID);
        currentWorkPanel.add(currentWStart);
        currentWorkPanel.add(currentClientName);
        currentWorkPanel.add(currentLocation);
        currentWorkPanel.add(currentArea);
        // currentWorkPanel.add(currentWorkOptions);

        JPanel forPreviousWorkLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forPreviousWorkLabel.setBackground(Color.decode("#40392f"));
        forPreviousWorkLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

        JLabel previousWorkLabel = new JLabel("<html>Your Previous Work</html>");
        previousWorkLabel.setHorizontalAlignment(JLabel.LEFT);
        previousWorkLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        previousWorkLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        previousWorkLabel.setForeground(Color.decode("#ebc38a"));
        forPreviousWorkLabel.add(previousWorkLabel);

        JPanel previousWorkPanel = new JPanel(new GridLayout(2, 0));

        previousWorkPanel.setBackground(Color.decode("#d1c4b2"));
        previousWorkPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

        JLabel previousWID = new JLabel("Work Id : " + "11111");
        previousWID.setBorder(new EmptyBorder(20, 20, 20, 20));
        previousWID.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        previousWID.setBackground(Color.decode("#f29105"));

        JLabel previousWStart = new JLabel("Work Started on : " + " 08-12-2020");
        previousWStart.setBorder(new EmptyBorder(20, 20, 20, 20));
        previousWStart.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        previousWStart.setBackground(Color.decode("#f29105"));

        JLabel previousWEnd = new JLabel("Work Ended on  : " + " 01-1-2021");
        previousWEnd.setBorder(new EmptyBorder(20, 20, 20, 20));
        previousWEnd.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        previousWEnd.setBackground(Color.decode("#f29105"));

        JLabel previousClientName = new JLabel("Supervisor Name : " + " Ashwin");
        previousClientName.setBorder(new EmptyBorder(20, 20, 20, 20));
        previousClientName.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        previousClientName.setBackground(Color.decode("#f29105"));

        JLabel previousLocation = new JLabel("Location :" + " Kannur");
        previousLocation.setBorder(new EmptyBorder(20, 20, 20, 20));
        previousLocation.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        previousLocation.setBackground(Color.decode("#f29105"));

        JLabel previousArea = new JLabel("Area (sq) :" + " 1345 sqft");
        previousArea.setBorder(new EmptyBorder(20, 20, 20, 20));
        previousArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        previousArea.setBackground(Color.decode("#f29105"));

        JLabel presviousEstimate = new JLabel("Estimate :" + " 45,60,000 RS");
        presviousEstimate.setBorder(new EmptyBorder(20, 20, 20, 20));
        presviousEstimate.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        presviousEstimate.setBackground(Color.decode("#f29105"));

        JPanel previousWorkOptions = new JPanel(new GridLayout(2, 0, 5, 5));
        previousWorkOptions.setBackground(Color.decode("#d1c4b2"));
        previousWorkOptions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        previousWorkOptions.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

        JButton getReportBtn = new JButton("Get Report");
        getReportBtn.setBackground(Color.decode("#a39887"));
        // getReportBtn.setForeground(Color.decode("#ebc38a"));
        getReportBtn.setFocusPainted(false);

        previousWorkOptions.add(getReportBtn);

        previousWorkPanel.add(previousWID);
        previousWorkPanel.add(previousWStart);
        previousWorkPanel.add(previousWEnd);
        previousWorkPanel.add(previousWID);
        previousWorkPanel.add(previousClientName);
        previousWorkPanel.add(previousLocation);
        previousWorkPanel.add(previousArea);
        previousWorkPanel.add(presviousEstimate);

        JPanel dummy = new JPanel(new FlowLayout());
        dummy.setBackground(Color.decode("#f0f0f0"));


        JPanel footer = new JPanel();
        footer.setLayout(new BorderLayout());
        JLabel footerLabel = new JLabel("<html>copyright © 2021 - Albatross Builders <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;All Rights Reserved </html>", SwingConstants.CENTER);
        footerLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        footerLabel.setBounds(0,(int)screenSize.getHeight(), (int)screenSize.getWidth(),(int)screenSize.getHeight()/4);
        footerLabel.setForeground(Color.decode("#403f3f"));
        footer.setBackground(Color.decode("#f0f0f0"));
        footerLabel.setBounds(0, 0,(int)screenSize.getWidth(),100);
        footerLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footer.add(footerLabel,BorderLayout.PAGE_END);


        f.add(headerPanel);
        f.add(welcomePanel);
        f.add(forcurrentWorkLabel);
        f.add(currentWorkPanel);
        f.add(currentWorkOptions);
        f.add(dummy);
        f.add(forPreviousWorkLabel);
        f.add(previousWorkPanel);
        f.add(previousWorkOptions);
f.add(footer);
        f.setVisible(true);// making the frame visible
        // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// terminate program when
        // closes frame
    }
    /**
     * 
     */
    //private void c_Id;

    /**
     * 
     */
  //  private void c_Name;





    /**
     * 
     */
    public void signUp() {
        // TODO implement here
    }

    /**
     * 
     */
    public void workReq() {
        // TODO implement here
    }

    /**
     * @param value
     */
   // public void getAllMyWork(void value) {
        // TODO implement here
  //  }

    /**
     * 
     */
    public void getpastWork() {
        // TODO implement here
    }

    /**
     * 
     */
    public void cancelWork() {
        // TODO implement here
    }

    /**
     * @param value
     */
    // public void makePayment(void value) {
    //     // TODO implement here
    // }

    /**
     * @return
     */
    // public void getC_Id() {
    //     // TODO implement here
    //     return null;
    // }

    /**
     * @return
     */
    // public void getC_Name() {
    //     // TODO implement here
    //     return null;
    // }

    /**
     * @param value
     */
    // public void setC_Name(void value) {
    //     // TODO implement here
    // }

    /**
     * 
     */
    

    /**
     * 
     */
    

   

    @Override
    public void logout() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void viewWork(int value) {
        // TODO Auto-generated method stub
        
    }

    /**
     * @param value
     */
   // public abstract void viewWork(void value);

}
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
public class Supervisor extends Login implements workDetails, ActionListener {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Default constructor
     */

    JTextField usernameField;
    JPasswordField passwordField;
    Boolean loginStatus = false;
    JFrame loginFrame;

    public Supervisor() {
        login();

    }

    public void loadSupervisorPanel(int id) {

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

        JLabel welcomeLabel = new JLabel("<html> Welcome <font color='#ebc38a'> Supervisor </font></html>");
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

        JLabel currentClientName = new JLabel("Client Name : " + " Anil");
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
        currentWorkOptions.setBackground(Color.decode("#d1c4b2"));
        currentWorkOptions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        currentWorkOptions.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        JButton UpdateBtn = new JButton("Update Work Progress");
        UpdateBtn.setBackground(Color.decode("#a39887"));
        // UpdateBtn.setForeground(Color.decode("#0a0a0a"));
        UpdateBtn.setFocusPainted(false);

        JButton materialBtn = new JButton("Used Materials");
        materialBtn.setBackground(Color.decode("#a39887"));
        // materialBtn.setForeground(Color.decode("#0a0a0a"));
        materialBtn.setFocusPainted(false);

        JButton contactClient = new JButton("Contact Client");
        contactClient.setBackground(Color.decode("#a39887"));
        // contactClient.setForeground(Color.decode("#0a0a0a"));
        contactClient.setFocusPainted(false);

        currentWorkOptions.add(UpdateBtn);
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

        JLabel previousClientName = new JLabel("ClientName : " + " Ashwin");
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

        loginFrame = new JFrame();
        loginFrame.setLayout(new GridLayout(4, 0));
        loginFrame.setSize((int) screenSize.getWidth() / 2, (int) (screenSize.getHeight() + 250) / 4);
        loginFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);

        JPanel titlePanel = new JPanel();
        JLabel supervisorLabel = new JLabel("Supervisor Login");
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

        JButton submitBtn = new JButton("<html>&nbsp;Submit</html>");
        submitBtn.setBackground(Color.decode("#40392f"));
        submitBtn.setForeground(Color.decode("#ebc38a"));
        submitBtn.setFocusPainted(false);

        JPanel usernamePanel = new JPanel();
        usernamePanel.add(userLabel);
        usernamePanel.add(usernameField);
        // usernamePanel.setAlignmentX(alignmentX);

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passLabel);
        passwordPanel.add(passwordField);

        JPanel submitPanel = new JPanel();
        submitPanel.setAlignmentY(SwingConstants.BOTTOM);
        submitPanel.add(submitBtn);

        loginFrame.add(titlePanel);
        loginFrame.add(usernamePanel);
        loginFrame.add(passwordPanel);
        loginFrame.add(submitPanel);
        loginFrame.setVisible(true);

        submitBtn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        // here verfiying login credentials with database

        // checking database connection running successfully

        // Config config = new Config();
        // Connection con = config.dbConnect();
        // Statement st;
        // try {
        // st = con.createStatement();
        // ResultSet rs= st.executeQuery("select * from salesman");
        // while(rs.next()){

        // System.out.println(rs.getString(2));
        // }
        // con.close();
        // } catch (SQLException e1) {
        // // TODO Auto-generated catch block
        // e1.printStackTrace();
        // }

        int supervisorId = 1;
        loadSupervisorPanel(supervisorId);
        loginFrame.dispose();

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
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.util.UUID;

/**
 * 
 */

public class Client extends Login implements workDetails, ActionListener {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    JFrame f;
    JPanel currentWorkPanel, allWorkPanel, allPreviousWorkPanel;
    JPanel currentWorkOptions;
    JTextField usernameField, loginUsernameField;
    JTextField fName;
    JTextField lName;
    JTextField mailIdField, phoneField;
    JScrollPane allWorkScrollPane, allPreviousWorkScrollPane;
    JPasswordField passwordField, loginPasswordField;
    JPasswordField cnfPasswordField;
    Boolean loginStatus = false;
    JFrame createFrame;
    JFrame loginFrame;
    JButton loginBtn, loginSubmitBtn, accCreateClnBtn, goTologinBtn;
    JButton createClnBtn, reqAworkBtn, refreshBtn, logOutBtn, paymentBtn;

    Config connection = new Config();
    Connection con = connection.dbConnect();
    public String StringId;
    private String name;

    /**
     * Default constructor
     */
    public Client() {
        login();
    }
    // Password p = new Password(new BCryptPasswordEncoder().encode(encodedPw));

    public void accountCreate() {
        createFrame = new JFrame();
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

        accCreateClnBtn = new JButton("<html>&nbsp;Create Account</html>");
        accCreateClnBtn.setBackground(Color.decode("#40392f"));
        accCreateClnBtn.setForeground(Color.decode("#ebc38a"));
        accCreateClnBtn.setFocusPainted(false);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(fnameLabel);
        namePanel.add(fName);
        namePanel.add(lnameLabel);
        namePanel.add(lName);

        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernamePanel.add(userLabel);
        usernamePanel.add(usernameField);
        usernamePanel.add(mailLabel);
        usernamePanel.add(mailIdField);
        usernamePanel.add(phoneLabel);
        usernamePanel.add(phoneField);
        // usernamePanel.setAlignmentX(alignmentX);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
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

        createClnBtn = new JButton("<html>&nbsp;Dont have Account?</html>");
        createClnBtn.setBackground(Color.decode("#40392f"));
        createClnBtn.setForeground(Color.decode("#ebc38a"));
        createClnBtn.setFocusPainted(false);

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
        if (e.getSource() == loginSubmitBtn) {
            String sql = "select * from logindetails where username=? and password =?";
            try {
                PreparedStatement prepareStatement = con.prepareStatement(sql);
                prepareStatement.setString(1, loginUsernameField.getText());
                prepareStatement.setString(2, new String(loginPasswordField.getPassword()));

                ResultSet rs = prepareStatement.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getObject(1) + " " + rs.getString(2));
                    this.StringId = rs.getString(1);
                    loadClientPanel(StringId);
                    
                    loginFrame.dispose();

                }

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } else if (e.getSource() == createClnBtn) {
            System.out.println("create an client account");
            loginFrame.dispose();
            accountCreate();
        } else if (e.getSource() == accCreateClnBtn) {
            System.out.println("Please create an account for client");
            createFrame.dispose();
            con = connection.dbConnect();

            String sql = "insert into client values(?,?,?); insert into logindetails(id, username, password) values (?,?,?); insert into contact(id, phone, mailid) values(?, ?, ?)";
            try {
                UUID idOne = UUID.randomUUID();

                PreparedStatement prepareStatement = con.prepareStatement(sql);
                prepareStatement.setObject(1, idOne);
                prepareStatement.setString(2, fName.getText());
                prepareStatement.setString(3, lName.getText());

                prepareStatement.setObject(4, idOne);
                prepareStatement.setString(5, usernameField.getText());
                prepareStatement.setString(6, new String(passwordField.getPassword()));

                prepareStatement.setObject(7, idOne);
                prepareStatement.setString(8, mailIdField.getText());
                prepareStatement.setString(9, phoneField.getText());

                int updatedCount = prepareStatement.executeUpdate();
                System.out.println(updatedCount + "  updated ");

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getSource() == goTologinBtn) {
            createFrame.dispose();
            login();
        } else if (e.getSource() == reqAworkBtn) {
            System.out.println("Req a work ");
            work w1 = new work(StringId);
        } else if (e.getSource() == refreshBtn) {
            System.out.println("Referesh");
            this.f.dispose();
            loadClientPanel(this.StringId);

        } else if (e.getSource() == logOutBtn) {
            System.out.println("Log Out Man");
            f.dispose();
        }
        else{
            JButton button = (JButton) e.getSource();
            String code = button.getName().substring(0, 3);
            String idneed = button.getName().substring(3);
            if (code.equals("ACW")) {

            }
            else if(code.equals("UMT")){
                Materials m1 = new Materials(idneed);
            
            }
        }

    }
    

    public void loadClientPanel(String stringID) {

        // todo fetch loggined supervisor details from database
        String sql = " select * from client where c_id=? ";
        ResultSet rs;
        String name;
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setObject(1, UUID.fromString(stringID));
            rs = prepareStatement.executeQuery();
            while (rs.next()) {
                // System.out.println(rs.getObject(1) + " " + rs.getString(2));
                this.name = rs.getString(2) + rs.getString(3);
                // loadClientPanel(rs.getString(1));
                // loginFrame.dispose();
            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        f = new JFrame();// creating instance of JFrame
        f.getContentPane().invalidate();
        f.setSize((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
        f.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH); // full size frame
        f.setLayout(null);

        f.getContentPane().setBackground(Color.decode("#f0f0f0"));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBounds(0, 0, (int) screenSize.getWidth(), 60);
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
        welcomePanel.setBounds(0 , 60, (int)screenSize.getWidth(), 80);

        reqAworkBtn = new JButton("Request a Work");
        reqAworkBtn.setBackground(Color.decode("#a39887"));
        reqAworkBtn.setAlignmentX(100);
        reqAworkBtn.setFocusPainted(false);
        reqAworkBtn.addActionListener(this);

        refreshBtn = new JButton("Refresh");
        refreshBtn.setBackground(Color.decode("#a39887"));
        // getReportBtn.setForeground(Color.decode("#ebc38a"));
        refreshBtn.setFocusPainted(false);
        refreshBtn.addActionListener(this);

        logOutBtn = new JButton("Log Out");
        logOutBtn.setBackground(Color.decode("#a39887"));
        // getReportBtn.setForeground(Color.decode("#ebc38a"));
        logOutBtn.setFocusPainted(false);
        logOutBtn.addActionListener(this);

        JPanel welcomBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 50, 20));

        welcomBtnPanel.add(refreshBtn);
        welcomBtnPanel.add(reqAworkBtn);       
        welcomBtnPanel.add(logOutBtn);

        JLabel welcomeLabel = new JLabel("<html> Welcome <font color='#ebc38a'>" + " " + this.name + " </font></html>");
        welcomeLabel.setHorizontalAlignment(JLabel.LEFT);
        welcomeLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 35));
        // welcomeLabel.setBounds(0, 0, (int)screenSize.getWidth(), 100);
        // welcomeLabel.setForeground(Color.decode("#a38f72"));

        JPanel forcurrentWorkLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forcurrentWorkLabel.setBackground(Color.decode("#40392f"));
        forcurrentWorkLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        forcurrentWorkLabel.setBounds(0,140,(int) screenSize.getWidth(),60);

        JLabel currentWorkLabel = new JLabel("Your Current Work");
        currentWorkLabel.setBorder(new EmptyBorder(20, 20, 10, 10));
        currentWorkLabel.setHorizontalAlignment(JLabel.LEFT);
        currentWorkLabel.setForeground(Color.decode("#ebc38a"));

        currentWorkLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));

        forcurrentWorkLabel.add(currentWorkLabel);

        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);
        welcomePanel.add(welcomeLabel);

        welcomePanel.add(welcomBtnPanel);

        // currentWorkPanel.add(currentWorkOptions);

        JPanel forPreviousWorkLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forPreviousWorkLabel.setBackground(Color.decode("#40392f"));
        forPreviousWorkLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        forPreviousWorkLabel.setBounds(0, 400, (int)screenSize.getWidth(), 60);

        JLabel previousWorkLabel = new JLabel("<html>Your Previous Work</html>");
        previousWorkLabel.setHorizontalAlignment(JLabel.LEFT);
        previousWorkLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        previousWorkLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        previousWorkLabel.setForeground(Color.decode("#ebc38a"));
        forPreviousWorkLabel.add(previousWorkLabel);

       
        JPanel dummy = new JPanel(new FlowLayout());
        dummy.setBackground(Color.decode("#f0f0f0"));
       

        JPanel footer = new JPanel();
        footer.setLayout(new BorderLayout());
        JLabel footerLabel = new JLabel(
                "<html>copyright © 2021 - Albatross Builders <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;All Rights Reserved </html>",
                SwingConstants.CENTER);
        footerLabel.setVerticalAlignment(SwingConstants.TOP);
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);        
        footerLabel.setForeground(Color.decode("#403f3f"));
        footer.setBackground(Color.decode("#f0f0f0"));
        footer.setBounds(0, 660, (int) screenSize.getWidth(), 40);
        footerLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footer.add(footerLabel, BorderLayout.PAGE_END);

        // dummy.setBounds(0, 670, (int)screenSize.getWidth(), 60);
        allWorkPanel = new JPanel();
        // allWorkPanel.setSize(800, 00);
        allWorkScrollPane = new JScrollPane(allWorkPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        allWorkPanel.setLayout(new  BoxLayout(allWorkPanel, BoxLayout.Y_AXIS));
        allWorkScrollPane.setBounds(0, 200, (int)screenSize.getWidth()-60, 200);

      
        f.add(headerPanel);
        f.add(welcomePanel);
        f.add(forcurrentWorkLabel);

        String sql2 = "select w_id, s_day, s_month, s_year, e_day, e_month, e_year, siteloc,fname, totalarea, totalestimate, paymentstatus, s_id from officestaff natural join work natural join site where work.c_id=?";
        ResultSet workDetails;
        try {
            PreparedStatement prepareStatement1 = con.prepareStatement(sql2);
            prepareStatement1.setObject(1, UUID.fromString(stringID));
            workDetails = prepareStatement1.executeQuery();

            while (workDetails.next()) {
                JLabel currentWID = new JLabel("Work Id : " + workDetails.getString(1));
                currentWID.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentWID.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentWID.setBackground(Color.decode("#f29105"));

                JLabel currentWStart = new JLabel("Work Started : " + workDetails.getString(2) + " / "
                        + workDetails.getString(3) + "/" + workDetails.getString(4));
                currentWStart.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentWStart.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentWStart.setBackground(Color.decode("#f29105"));

                JLabel currentClientName = new JLabel("Supervisor Name : " + workDetails.getString(9));
                currentClientName.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentClientName.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentClientName.setBackground(Color.decode("#f29105"));

                JLabel currentLocation = new JLabel("Location : " + workDetails.getString(8));
                currentLocation.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentLocation.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentLocation.setBackground(Color.decode("#f29105"));

                JLabel currentArea = new JLabel("Area (Sq): " + workDetails.getString(10));
                currentArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentArea.setBackground(Color.decode("#f29105"));

                JLabel estimateArea = new JLabel("Estimate : " + workDetails.getString(11));
                estimateArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                estimateArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                estimateArea.setBackground(Color.decode("#f29105"));

                JLabel PaymentStatusArea = new JLabel("Paymenet Status: " + workDetails.getString(12));
                PaymentStatusArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                PaymentStatusArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                PaymentStatusArea.setBackground(Color.decode("#f29105"));


                currentWorkOptions = new JPanel(new GridLayout(2, 0, 5, 5));
                currentWorkOptions.setBackground(Color.decode("#d1c4b2"));

                JButton materialBtn = new JButton("Used Materials");
                materialBtn.setBackground(Color.decode("#a39887"));
                // materialBtn.setForeground(Color.decode("#0a0a0a"));
                materialBtn.setFocusPainted(false);
                materialBtn.setName("UMT"+workDetails.getString(13));
                materialBtn.addActionListener(this);

                JButton paymentBtn = new JButton("Payment");
                paymentBtn.setBackground(Color.decode("#a39887"));
                // contactClient.setForeground(Color.decode("#0a0a0a"));
                paymentBtn.setFocusPainted(false);

                JButton contactClient = new JButton("Contact Supervisor");
                contactClient.setBackground(Color.decode("#a39887"));
                // contactClient.setForeground(Color.decode("#0a0a0a"));
                contactClient.setFocusPainted(false);

                // currentWorkOptions.add(UpdateBtn);
                currentWorkOptions.add(materialBtn);
                currentWorkOptions.add(paymentBtn);
                currentWorkOptions.add(contactClient);
                currentWorkPanel = new JPanel(new GridLayout(3, 0));
                currentWorkPanel.setBackground(Color.decode("#d1c4b2"));
                currentWorkPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
                currentWorkPanel.add(currentWID);
                currentWorkPanel.add(currentWStart);
                currentWorkPanel.add(currentClientName);
                currentWorkPanel.add(currentLocation);
                currentWorkPanel.add(currentArea);
                currentWorkPanel.add(estimateArea);
                currentWorkPanel.add(PaymentStatusArea);

                currentWorkPanel.add(currentWorkOptions);
                // allWorkScrollBar.add(currentWorkPanel);
                // allWorkScrollBar.add(currentWorkOptions);
                allWorkPanel.add(currentWorkPanel);
              //  allWorkPanel.add(currentWorkOptions);
                // f.add(currentWorkPanel);
                // f.add(currentWorkOptions);

            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
      
      //  allWorkScrollPane.setPreferredSize(new Dimension((int) screenSize.getWidth(),600));
        // f.add(currentWorkPanel);
        // f.add(currentWorkOptions);
        // f.add(dummy);
        f.add(allWorkScrollPane);
        f.add(forPreviousWorkLabel);

        allPreviousWorkPanel = new JPanel(new GridLayout(3,0));
        // allPreviousWorkPanel.setSize(800, 00);
        allPreviousWorkScrollPane = new JScrollPane(allPreviousWorkPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        allPreviousWorkPanel.setLayout(new  BoxLayout(allPreviousWorkPanel, BoxLayout.Y_AXIS));
        allPreviousWorkScrollPane.setBounds(0, 460, (int)screenSize.getWidth()-60, 200);
      

        String sql3 = "select w_id, s_day, s_month, s_year, e_day, e_month, e_year, siteloc,fname, totalarea, totalestimate, paymentstatus from officestaff natural join work natural join site where work.c_id=? and e_day is not null";
        ResultSet previousWorkDetails;
        try {
            PreparedStatement prepareStatement1 = con.prepareStatement(sql3);
            prepareStatement1.setObject(1, UUID.fromString(stringID));
            previousWorkDetails = prepareStatement1.executeQuery();

            while (previousWorkDetails.next()) {
                JPanel previousWorkPanel = new JPanel(new GridLayout(3, 0));
                previousWorkPanel.setBackground(Color.decode("#d1c4b2"));
                previousWorkPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        
                JLabel previousWID = new JLabel("Work Id : " + previousWorkDetails.getString(1));
                previousWID.setBorder(new EmptyBorder(20, 20, 20, 20));
                previousWID.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                previousWID.setBackground(Color.decode("#f29105"));
        
                JLabel previousWStart = new JLabel("Work Started on : " +  previousWorkDetails.getString(2) + " / "
                + previousWorkDetails.getString(3) + "/" + previousWorkDetails.getString(4));
                previousWStart.setBorder(new EmptyBorder(20, 20, 20, 20));
                previousWStart.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                previousWStart.setBackground(Color.decode("#f29105"));
        
                JLabel previousWEnd = new JLabel("Work Ended on  : " +  previousWorkDetails.getString(5) + " / "
                + previousWorkDetails.getString(6) + "/" + previousWorkDetails.getString(7));
                previousWEnd.setBorder(new EmptyBorder(20, 20, 20, 20));
                previousWEnd.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                previousWEnd.setBackground(Color.decode("#f29105"));
        
                JLabel previousClientName = new JLabel("Supervisor Name : " + previousWorkDetails.getString(9));
                previousClientName.setBorder(new EmptyBorder(20, 20, 20, 20));
                previousClientName.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                previousClientName.setBackground(Color.decode("#f29105"));
        
                JLabel previousLocation = new JLabel("Location :" + previousWorkDetails.getString(8));
                previousLocation.setBorder(new EmptyBorder(20, 20, 20, 20));
                previousLocation.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                previousLocation.setBackground(Color.decode("#f29105"));
        
                JLabel previousArea = new JLabel("Area (sq) :" +previousWorkDetails.getString(10));
                previousArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                previousArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                previousArea.setBackground(Color.decode("#f29105"));
        
                JLabel presviousEstimate = new JLabel("Estimate :" + previousWorkDetails.getString(11));
                presviousEstimate.setBorder(new EmptyBorder(20, 20, 20, 20));
                presviousEstimate.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                presviousEstimate.setBackground(Color.decode("#f29105"));
        
                JLabel paymentStatus = new JLabel("Payment Status :" +previousWorkDetails.getString(12) );
                paymentStatus.setBorder(new EmptyBorder(20, 20, 20, 20));
                paymentStatus.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                paymentStatus.setBackground(Color.decode("#f29105"));

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
                previousWorkPanel.add(paymentStatus);

                allPreviousWorkPanel.add(previousWorkPanel);
                allPreviousWorkPanel.add(previousWorkOptions);
        
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    
        f.add(allPreviousWorkScrollPane);
        // f.add(previousWorkOptions);
        f.add(footer);
        f.setVisible(true);// making the frame visible
        // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// terminate program when
        // closes frame
    }
    /**
     * 
     */
    // private void c_Id;

    /**
     * 
     */
    // private void c_Name;

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
    // }

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
    // // TODO implement here
    // }

    /**
     * @return
     */
    // public void getC_Id() {
    // // TODO implement here
    // return null;
    // }

    /**
     * @return
     */
    // public void getC_Name() {
    // // TODO implement here
    // return null;
    // }

    /**
     * @param value
     */
    // public void setC_Name(void value) {
    // // TODO implement here
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
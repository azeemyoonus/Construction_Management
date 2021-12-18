
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
    JScrollPane allAvailabeWorkScrollPane;
    JFrame createFrame;
    JPanel allAvailableWorkPanel;
    JFrame loginFrame;

    Config connection = new Config();
    Connection con = connection.dbConnect();
    public String StringId;
    private String name;
    public int totalWorks, totalClients, totalSupervisor, totalOngoingwork;

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
            f.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);
            f.setLayout(null);          
            f.getContentPane().setBackground(Color.decode("#f0f0f0"));
            f.setTitle("Add New Staff");

            JLabel headerLabel = new JLabel("Staff Details");
            headerLabel.setForeground(Color.black);        
            headerLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
            headerLabel.setBounds(200, 10, 400, 30);

            // JPanel namepanel = new JPanel(new GridLayout(1, 0, 5, 5));
            // currentWorkOptions.setBackground(Color.decode("#d1c4b2"));

            JLabel fnameLabel = new JLabel("First name");
            // fnameLabel.setForeground(Color.decode("#ebc38a"));
            fnameLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
            fnameLabel.setBounds(80, 70, 200, 30);

            JLabel lnameLabel = new JLabel("Last name");
            // lnameLabel.setForeground(Color.decode("#ebc38a"));
            lnameLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
            lnameLabel.setBounds(80, 110, 200, 30);

            JLabel rollLabel = new JLabel("Roll");
            // rollLabel.setForeground(Color.decode("#ebc38a"));
            rollLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
            rollLabel.setBounds(80, 155, 200, 30);

            JLabel mailLabel = new JLabel("Email");
            // mailLabel.setForeground(Color.decode("#ebc38a"));
            mailLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
            mailLabel.setBounds(80, 200, 200, 30);

            JLabel pNum = new JLabel("Phone Number");
            // pNum.setForeground(Color.decode("#ebc38a"));
            pNum.setFont(new Font("SansSerif", Font.PLAIN, 25));
            pNum.setBounds(80, 250, 200, 30);

            JLabel setUname = new JLabel("Set Username");
            // setUname.setForeground(Color.decode("#ebc38a"));
            setUname.setFont(new Font("SansSerif", Font.PLAIN, 25));
            setUname.setBounds(80, 300, 200, 30);

            JLabel setPass = new JLabel("Set Password");
            // setPass.setForeground(Color.decode("#ebc38a"));
            setPass.setFont(new Font("SansSerif", Font.PLAIN, 25));
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
            f.add(setUsenameField);

            f.add(setPass);
            f.add(setPassField);

            f.add(addStaffBtn);

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

    // public void addClient() {
    //     JFrame createFrame = new JFrame();
    //     createFrame.setLayout(new GridLayout(5, 0));
    //     createFrame.setSize((int) (screenSize.getWidth()), (int) (screenSize.getHeight() / 2));
    //     createFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);

    //     JPanel titlePanel = new JPanel();
    //     JLabel supervisorLabel = new JLabel("Create An Account");
    //     supervisorLabel.setFont(new Font("SansSerif", Font.PLAIN, 38));
    //     titlePanel.add(supervisorLabel);
    //     JLabel userLabel = new JLabel();
    //     userLabel.setText("<html>Username</html>"); // set label value for textField1
    //     userLabel.setBackground(Color.DARK_GRAY);

    //     JLabel fnameLabel = new JLabel();
    //     fnameLabel.setText("<html>First Name</html>");
    //     fnameLabel.setBackground(Color.DARK_GRAY);

    //     JLabel mailLabel = new JLabel();
    //     mailLabel.setText("<html>Mail Id</html>");
    //     mailLabel.setBackground(Color.DARK_GRAY);

    //     JLabel lnameLabel = new JLabel();
    //     lnameLabel.setText("<html>Last Name</html>");
    //     lnameLabel.setBackground(Color.DARK_GRAY);
    //     // create text field to get username from the user

    //     fName = new JTextField(15); // set length of the text
    //     lName = new JTextField(15);
    //     mailIdField = new JTextField(35);
    //     phoneField = new JTextField(10);
    //     usernameField = new JTextField(25);

    //     // create label for password
    //     JLabel passLabel = new JLabel();
    //     passLabel.setText("<html>Password</html>"); // set label value for textField2

    //     JLabel phoneLabel = new JLabel();
    //     phoneLabel.setText("<html>Phone No: </html>");

    //     JLabel cnfPassLabel = new JLabel();
    //     cnfPassLabel.setText("<html>Confirm Password</html>");
    //     // create text field to get password from the user

    //     passwordField = new JPasswordField(15);
    //     cnfPasswordField = new JPasswordField(15);

    //     goTologinBtn = new JButton("<html>&nbsp;Already have an Accunt, Login?</html>");
    //     goTologinBtn.setBackground(Color.decode("#40392f"));
    //     goTologinBtn.setForeground(Color.decode("#ebc38a"));
    //     goTologinBtn.setFocusPainted(false);

    // }

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

        JLabel welcomeLabel = new JLabel("<html> Welcome  <font color='#ebc38a'>ADMIN</font></html>");
        welcomeLabel.setHorizontalAlignment(JLabel.LEFT);
        welcomeLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 35));
        // welcomeLabel.setForeground(Color.decode("#a38f72"));
        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);

        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 20));
        welcomePanel.setBackground(Color.decode("#f0f0f0"));
        welcomePanel.setBounds(0, 60, (int) screenSize.getWidth(), 80);

        welcomePanel.add(welcomeLabel);

        // ADDING BUTTONS
        JPanel bodyButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bodyButtons.setBounds(0, 140, (int) screenSize.getWidth(), 60);
        bodyButtons.setBackground(Color.decode("#f0f0f0"));
        f.setVisible(true);

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

        addOfficeStaff = new JButton("Add Office Staff");
        addOfficeStaff.setBackground(Color.decode("#a39887"));
        addOfficeStaff.setAlignmentX(100);
        addOfficeStaff.setFocusPainted(false);
        addOfficeStaff.addActionListener(this);

        addmatBtn = new JButton("Add Materials");
        addmatBtn.setBackground(Color.decode("#a39887"));
        addmatBtn.setFocusPainted(false);
        addmatBtn.addActionListener(this);

        // add all components

        // bodyButtons.add(seeAllWork);
        // bodyButtons.add(ongoingWork);
        bodyButtons.add(pastWork);
        bodyButtons.add(supList);
        bodyButtons.add(clist);
        bodyButtons.add(addOfficeStaff);
        bodyButtons.add(addmatBtn);

        JPanel forOngoingtWorkLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forOngoingtWorkLabel.setBackground(Color.decode("#40392f"));
        forOngoingtWorkLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        forOngoingtWorkLabel.setBounds(0, 200, (int) screenSize.getWidth(), 60);

        JLabel ongoingWorkLabel = new JLabel("Ongoing Work");
        ongoingWorkLabel.setBorder(new EmptyBorder(20, 20, 10, 10));
        ongoingWorkLabel.setHorizontalAlignment(JLabel.LEFT);
        ongoingWorkLabel.setForeground(Color.decode("#ebc38a"));

        ongoingWorkLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));

        forOngoingtWorkLabel.add(ongoingWorkLabel);

        allAvailableWorkPanel = new JPanel();
        // allWorkPanel.setSize(800, 00);
        allAvailabeWorkScrollPane = new JScrollPane(allAvailableWorkPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        allAvailableWorkPanel.setLayout(new BoxLayout(allAvailableWorkPanel, BoxLayout.Y_AXIS));
        allAvailabeWorkScrollPane.setBounds(0, 260, (int) screenSize.getWidth() - 60, 200);

        f.add(headerPanel);
        f.add(welcomePanel);
        f.add(bodyButtons);
        f.add(forOngoingtWorkLabel);
        String sql2 = "select w_id, s_day, s_month, s_year, e_day, e_month, e_year, siteloc,fname, totalarea, totalestimate, paymentstatus, s_id from officestaff natural join work natural join site where e_day is null";
        ResultSet workDetails;
        try {
            PreparedStatement prepareStatement1 = con.prepareStatement(sql2);
            // prepareStatement1.setObject(1, UUID.fromString(stringID));
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

                JPanel availabeWorkOptions = new JPanel(new GridLayout(2, 0, 5, 5));
                availabeWorkOptions.setBackground(Color.decode("#d1c4b2"));

                JButton contactClient = new JButton("Contact Supervisor");
                contactClient.setBackground(Color.decode("#a39887"));
                // contactClient.setForeground(Color.decode("#0a0a0a"));
                contactClient.setFocusPainted(false);

                // currentWorkOptions.add(UpdateBtn);
                availabeWorkOptions.add(contactClient);

                JPanel availableWorkPanel = new JPanel(new GridLayout(3, 0));
                availableWorkPanel.setBackground(Color.decode("#d1c4b2"));
                availableWorkPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
                availableWorkPanel.add(currentWID);
                availableWorkPanel.add(currentWStart);
                availableWorkPanel.add(currentClientName);
                availableWorkPanel.add(currentLocation);
                availableWorkPanel.add(currentArea);
                availableWorkPanel.add(estimateArea);
                availableWorkPanel.add(PaymentStatusArea);

                availableWorkPanel.add(availabeWorkOptions);
                // allWorkScrollBar.add(currentWorkPanel);
                // allWorkScrollBar.add(currentWorkOptions);
                allAvailableWorkPanel.add(availableWorkPanel);
                // allWorkPanel.add(currentWorkOptions);
                // f.add(currentWorkPanel);
                // f.add(currentWorkOptions);

            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        f.add(allAvailabeWorkScrollPane);

        JPanel generalInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        generalInfoPanel.setBackground(Color.decode("#40392f"));
        generalInfoPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        generalInfoPanel.setBounds(0, 460, (int) screenSize.getWidth(), 60);

        JLabel generalInfoLabel = new JLabel("General Info");
        generalInfoLabel.setBorder(new EmptyBorder(20, 20, 10, 10));
        generalInfoLabel.setHorizontalAlignment(JLabel.LEFT);
        generalInfoLabel.setForeground(Color.decode("#ebc38a"));
        generalInfoLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));

        generalInfoPanel.add(generalInfoLabel);

        f.add(generalInfoPanel);

        String sql = "select count(work) as totalwork from work ;";
        String sql1 = " select count(client) as totalclient from client;";
        String sql4 = " select count(officeStaff) as totalsupervisor from officestaff where role='Supervisor';";
        String sql5 = "  select count(work) as totalongoingwok from work where e_day is null; ";

        ResultSet totalwork, tClient, tSupervisor, tOnWork;
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            PreparedStatement prepareStatement1 = con.prepareStatement(sql1);
            PreparedStatement prepareStatement4 = con.prepareStatement(sql4);
            PreparedStatement prepareStatement5 = con.prepareStatement(sql5);
            totalwork = prepareStatement.executeQuery();
            while (totalwork.next()) {
                totalWorks = totalwork.getInt(1);
            }

            tClient = prepareStatement1.executeQuery();
            while (tClient.next()) {
                totalClients = tClient.getInt(1);
            }
            tSupervisor = prepareStatement4.executeQuery();
            while (tSupervisor.next()) {
                totalSupervisor = tSupervisor.getInt(1);
            }
            tOnWork = prepareStatement5.executeQuery();
            while (tOnWork.next()) {
                totalOngoingwork = tOnWork.getInt(1);
            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        

        JLabel totalWorkLabel = new JLabel("Total Work Got: " + totalWorks);
        totalWorkLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        totalWorkLabel.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        totalWorkLabel.setBackground(Color.decode("#f29105"));

        JLabel totalClientLabel = new JLabel("Total Client : " + totalClients);
        totalClientLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        totalClientLabel.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        totalClientLabel.setBackground(Color.decode("#f29105"));

        JLabel totalSupervisorLabel = new JLabel("Total Supervisor : " + totalSupervisor);
        totalSupervisorLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        totalSupervisorLabel.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        totalSupervisorLabel.setBackground(Color.decode("#f29105"));

        JLabel onGoingWorkLabel = new JLabel("Works Ongoing : " + totalOngoingwork);
        onGoingWorkLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        onGoingWorkLabel.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        onGoingWorkLabel.setBackground(Color.decode("#f29105"));

        JLabel profitLabel = new JLabel("Total Profit : " + " ");
        profitLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        profitLabel.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        profitLabel.setBackground(Color.decode("#f29105"));

        JPanel infoPanel = new JPanel(new GridLayout(3, 0));
        infoPanel.setBackground(Color.decode("#d1c4b2"));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        infoPanel.setBounds(0, 520, (int) screenSize.getWidth(), 150);

        infoPanel.add(totalWorkLabel);
        infoPanel.add(totalClientLabel);
        infoPanel.add(totalSupervisorLabel);  
        infoPanel.add(onGoingWorkLabel);            
        infoPanel.add(profitLabel);

        JPanel footer = new JPanel();
        footer.setLayout(new BorderLayout());
        JLabel footerLabel = new JLabel(
                "<html>copyright © 2021 - Albatross Builders <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;All Rights Reserved </html>",
                SwingConstants.CENTER);
        footerLabel.setVerticalAlignment(SwingConstants.TOP);
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);        
        footerLabel.setForeground(Color.decode("#403f3f"));
        footer.setBackground(Color.decode("#f0f0f0"));
        footer.setBounds(0, 670, (int) screenSize.getWidth(), 30);
        footerLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footer.add(footerLabel, BorderLayout.PAGE_END);

        f.add(infoPanel);        
        f.add(footer);

    }

    @Override
    public void viewWork(int value) {
        // TODO Auto-generated method stub

    }
}
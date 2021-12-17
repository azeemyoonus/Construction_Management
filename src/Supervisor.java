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

    JTextField usernameField;
    JPasswordField passwordField;
    Boolean loginStatus = false;
    JFrame loginFrame, f;
    JButton submitBtn;
    JPanel allAcceptWorkPanel, allAvailableWorkPanel;
    JScrollPane allAvailableWorkScrollPane, allAcceptedWorkPane;

    Config connection = new Config();
    Connection con = connection.dbConnect();

    public String StringId;

    private String name;

    /**
     * Default constructor
     */
    public Supervisor() {
        login();

    }

    public void loadSupervisorPanel(String id) {

        // todo fetch loggined supervisor details from database

        String sql = " select * from officestaff where id=? ";
        ResultSet rs;
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setObject(1, UUID.fromString(id));
            rs = prepareStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getObject(1) + " " + rs.getString(2));
                this.name = rs.getString(2) + " " + rs.getString(3);
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
        welcomePanel.setBounds(0, 60, (int) screenSize.getWidth(), 80);

        JLabel welcomeLabel = new JLabel("<html> Welcome <font color='#ebc38a'>" + this.name + " </font></html>");
        welcomeLabel.setHorizontalAlignment(JLabel.LEFT);
        welcomeLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 35));
        // welcomeLabel.setForeground(Color.decode("#a38f72"));

        JPanel forcurrentWorkLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forcurrentWorkLabel.setBackground(Color.decode("#40392f"));
        forcurrentWorkLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        forcurrentWorkLabel.setBounds(0, 140, (int) screenSize.getWidth(), 60);

        JLabel currentWorkLabel = new JLabel("Your Current Work");
        currentWorkLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        currentWorkLabel.setHorizontalAlignment(JLabel.LEFT);
        currentWorkLabel.setForeground(Color.decode("#ebc38a"));

        currentWorkLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));

        forcurrentWorkLabel.add(currentWorkLabel);

        JPanel forPreviousWorkLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forPreviousWorkLabel.setBackground(Color.decode("#40392f"));
        forPreviousWorkLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        forPreviousWorkLabel.setBounds(0, 400, (int) screenSize.getWidth(), 60);

        JLabel availableWork = new JLabel("<html>Available  Work</html>");
        availableWork.setHorizontalAlignment(JLabel.LEFT);
        availableWork.setBorder(new EmptyBorder(20, 20, 20, 20));
        availableWork.setFont(new Font("SansSerif", Font.PLAIN, 20));
        availableWork.setForeground(Color.decode("#ebc38a"));
        forPreviousWorkLabel.add(availableWork);

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

        allAcceptWorkPanel = new JPanel();
        allAcceptWorkPanel.setSize(800, 00);
        allAcceptedWorkPane = new JScrollPane(allAcceptWorkPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // JScrollBar bar = allAcceptedWorkPane.getVerticalScrollBar();
        // bar.setPreferredSize(new Dimension(40, 0));
        allAcceptWorkPanel.setLayout(new BoxLayout(allAcceptWorkPanel, BoxLayout.Y_AXIS));
        allAcceptedWorkPane.setBounds(0, 200, (int) screenSize.getWidth() - 60, 200);

        f.add(headerPanel);
        f.add(welcomePanel);
        f.add(forcurrentWorkLabel);

        String sql2 = "select w_id,  s_day, s_month, s_year, e_day, e_month, e_year, siteloc,fname||' '||lname, totalarea, totalestimate, paymentstatus, s_id from client natural join work natural join site where work.o_id=?";
        ResultSet acceptWorkDetails;
        try {
            PreparedStatement prepareStatement1 = con.prepareStatement(sql2);
            prepareStatement1.setObject(1, UUID.fromString(StringId));
            acceptWorkDetails = prepareStatement1.executeQuery();

            while (acceptWorkDetails.next()) {
                JPanel currentWorkPanel = new JPanel(new GridLayout(3, 0));

                currentWorkPanel.setBackground(Color.decode("#d1c4b2"));
                currentWorkPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

                JLabel currentWID = new JLabel("Work Id : " + acceptWorkDetails.getString(1));
                currentWID.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentWID.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentWID.setBackground(Color.decode("#f29105"));

                JLabel currentWStart = new JLabel("Work Started : " + acceptWorkDetails.getString(2) + " / "
                        + acceptWorkDetails.getString(3) + "/" + acceptWorkDetails.getString(4));
                currentWStart.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentWStart.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentWStart.setBackground(Color.decode("#f29105"));

                JLabel currentClientName = new JLabel("Client Name : " + acceptWorkDetails.getString(9));
                currentClientName.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentClientName.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentClientName.setBackground(Color.decode("#f29105"));

                JLabel currentLocation = new JLabel("Location : " + acceptWorkDetails.getString(8));
                currentLocation.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentLocation.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentLocation.setBackground(Color.decode("#f29105"));

                JLabel currentArea = new JLabel("Area (Sq): " + acceptWorkDetails.getString(10));
                currentArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentArea.setBackground(Color.decode("#f29105"));

                JLabel estimateArea = new JLabel("Estimate :" + acceptWorkDetails.getString(11));
                estimateArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                estimateArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                estimateArea.setBackground(Color.decode("#f29105"));

                JLabel paymentstatus = new JLabel("Payment Status: " + acceptWorkDetails.getString(12));
                paymentstatus.setBorder(new EmptyBorder(20, 20, 20, 20));
                paymentstatus.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                paymentstatus.setBackground(Color.decode("#f29105"));

                JPanel currentWorkOptions = new JPanel(new GridLayout(2, 0, 5, 5));
                currentWorkOptions.setBackground(Color.decode("#d1c4b2"));
                currentWorkOptions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                currentWorkOptions.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
                JButton UpdateBtn = new JButton("Update Work Progress");
                UpdateBtn.setBackground(Color.decode("#a39887"));
                // UpdateBtn.setForeground(Color.decode("#0a0a0a"));
                UpdateBtn.setFocusPainted(false);
                UpdateBtn.setName("UWP" + acceptWorkDetails.getString(1));
                UpdateBtn.addActionListener(this);

                JButton materialBtn = new JButton("Add Materials");
                materialBtn.setBackground(Color.decode("#a39887"));
                // materialBtn.setForeground(Color.decode("#0a0a0a"));
                materialBtn.setFocusPainted(false);
                materialBtn.setName("UMT" + acceptWorkDetails.getString(1) + acceptWorkDetails.getString(13));
                materialBtn.addActionListener(this);

                JButton getReportBtn = new JButton("Get Report");
                getReportBtn.setBackground(Color.decode("#a39887"));
                // materialBtn.setForeground(Color.decode("#0a0a0a"));
                getReportBtn.setFocusPainted(false);
                getReportBtn.setName(acceptWorkDetails.getString(1));
                getReportBtn.addActionListener(this);

                JButton contactClientBtn = new JButton("Contact Client");
                contactClientBtn.setBackground(Color.decode("#a39887"));
                // contactClient.setForeground(Color.decode("#0a0a0a"));
                contactClientBtn.setFocusPainted(false);
                contactClientBtn.setName(acceptWorkDetails.getString(1));
                contactClientBtn.addActionListener(this);

                currentWorkOptions.add(UpdateBtn);
                currentWorkOptions.add(materialBtn);
                currentWorkOptions.add(contactClientBtn);
                currentWorkOptions.add(getReportBtn);

                headerPanel.add(logoLabel);
                headerPanel.add(titleLabel);
                welcomePanel.add(welcomeLabel);
                currentWorkPanel.add(currentWID);
                currentWorkPanel.add(currentWStart);
                currentWorkPanel.add(currentClientName);
                currentWorkPanel.add(currentLocation);
                currentWorkPanel.add(currentArea);
                currentWorkPanel.add(estimateArea);
                currentWorkPanel.add(paymentstatus);

                allAcceptWorkPanel.add(currentWorkPanel);
                allAcceptWorkPanel.add(currentWorkOptions);

            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        f.add(allAcceptedWorkPane);
        f.add(forPreviousWorkLabel);

        allAvailableWorkPanel = new JPanel();
        allAvailableWorkPanel.setSize((int) screenSize.getWidth(), 200);
        allAvailableWorkScrollPane = new JScrollPane(allAvailableWorkPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        allAvailableWorkPanel.setLayout(new BoxLayout(allAvailableWorkPanel, BoxLayout.Y_AXIS));
        allAvailableWorkScrollPane.setBounds(0, 460, (int) screenSize.getWidth() - 60, 200);

        String sql3 = "select w_id, s_day, s_month, s_year, e_day, e_month, e_year,fname||' '||lname, siteloc,totalarea, totalestimate, paymentstatus from client natural join work natural join site  where work.o_id is null";
        ResultSet availableWorkDetails;

        try {
            PreparedStatement prepareStatement1 = con.prepareStatement(sql3);
            // prepareStatement1.setObject(1, UUID.fromString(id));
            availableWorkDetails = prepareStatement1.executeQuery();

            while (availableWorkDetails.next()) {

                JPanel currentWorkPanel = new JPanel(new GridLayout(3, 0));

                currentWorkPanel.setBackground(Color.decode("#d1c4b2"));
                currentWorkPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

                JLabel currentWID = new JLabel("Work Id : " + availableWorkDetails.getString(1));
                currentWID.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentWID.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentWID.setBackground(Color.decode("#f29105"));

                JLabel currentWStart = new JLabel("Work Started : " + availableWorkDetails.getString(2) + " / "
                        + availableWorkDetails.getString(3) + "/" + availableWorkDetails.getString(4));
                currentWStart.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentWStart.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentWStart.setBackground(Color.decode("#f29105"));

                JLabel currentClientName = new JLabel("Client Name : " + availableWorkDetails.getString(8));
                currentClientName.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentClientName.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentClientName.setBackground(Color.decode("#f29105"));

                JLabel currentLocation = new JLabel("Location : " + availableWorkDetails.getString(9));
                currentLocation.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentLocation.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentLocation.setBackground(Color.decode("#f29105"));

                JLabel currentArea = new JLabel("Area (Sq): " + availableWorkDetails.getString(10));
                currentArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentArea.setBackground(Color.decode("#f29105"));

                JLabel estimateArea = new JLabel("Estimate :" + availableWorkDetails.getString(11));
                estimateArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                estimateArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                estimateArea.setBackground(Color.decode("#f29105"));

                JLabel paymentstatus = new JLabel("Payment Status: " + availableWorkDetails.getString(12));
                paymentstatus.setBorder(new EmptyBorder(20, 20, 20, 20));
                paymentstatus.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                paymentstatus.setBackground(Color.decode("#f29105"));

                JPanel currentWorkOptions = new JPanel(new GridLayout(2, 0, 5, 5));
                currentWorkOptions.setBackground(Color.decode("#d1c4b2"));
                currentWorkOptions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                currentWorkOptions.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

                JButton acceptWorkBtn = new JButton("Accept");
                acceptWorkBtn.setBackground(Color.decode("#a39887"));
                // UpdateBtn.setForeground(Color.decode("#0a0a0a"));
                acceptWorkBtn.setFocusPainted(false);
                acceptWorkBtn.setName("ACW" + availableWorkDetails.getString(1));
                acceptWorkBtn.addActionListener(this);

                currentWorkOptions.add(acceptWorkBtn);

                headerPanel.add(logoLabel);
                headerPanel.add(titleLabel);
                welcomePanel.add(welcomeLabel);
                currentWorkPanel.add(currentWID);
                currentWorkPanel.add(currentWStart);
                currentWorkPanel.add(currentClientName);
                currentWorkPanel.add(currentLocation);
                currentWorkPanel.add(currentArea);
                currentWorkPanel.add(estimateArea);
                currentWorkPanel.add(paymentstatus);

                allAvailableWorkPanel.add(currentWorkPanel);
                allAvailableWorkPanel.add(currentWorkOptions);

            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        f.add(allAvailableWorkScrollPane);
        f.add(footer);
        f.setVisible(true);// making the frame visible

        // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// terminate program when
        // closes frame
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource() == submitBtn) {
            String sql = "select * from logindetails where username=? and password =?";
            try {
                PreparedStatement prepareStatement = con.prepareStatement(sql);
                prepareStatement.setString(1, usernameField.getText());
                prepareStatement.setString(2, new String(passwordField.getPassword()));

                ResultSet rs = prepareStatement.executeQuery();
                while (rs.next()) {
                    System.out.println("hello ");
                    System.out.println(rs.getObject(1) + " " + rs.getString(2));
                    this.StringId = rs.getString(2);
                    loadSupervisorPanel(this.StringId);
                    loginFrame.dispose();
                }

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        else {
            JButton button = (JButton) e.getSource();
            String code = button.getName().substring(0, 3);
            String idneed = button.getName().substring(3);
            if (code.equals("ACW")) {
                System.out.println(code + " " + idneed);
                String sql3 = "update work set o_id=? where w_id=?";
                try {
                    PreparedStatement prepareStatement = con.prepareStatement(sql3);
                    prepareStatement.setObject(1, UUID.fromString(StringId));
                    prepareStatement.setObject(2, UUID.fromString(idneed));
                    System.out.println("Work Id: " + UUID.fromString(idneed));
                    System.out.println("supervisor Id: " + UUID.fromString(StringId));
                    int count = prepareStatement.executeUpdate();
                    // while (rs.next()) {
                    // System.out.println(rs.getObject(1) + " " + rs.getString(2));
                    // this.StringId = rs.getString(2);
                    // loadSupervisorPanel(StringId);
                    // loginFrame.dispose();
                    // }
                    System.out.println(count + " updated");
                    // loadSupervisorPanel(StringId);
                    this.f.dispose();
                    loadSupervisorPanel(StringId);

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if (code.equals("UWP")) {
                System.out.println("upadte work progress ");
                JFrame updateWorkFrame;
                // JLabel staffDetailsLabel, fNameLabel, lNameLabel, rollLabel, emailLabel, phoneLabel, usernameLabel,
                //         passwordLabel;
                // JTextField tf1, tf2, tf5, tf6, tf7, tf8;
                // JButton addBtn;
                // updateWorkFrame.setVisible(true);
                // updateWorkFrame.setSize(700, 550);
                // updateWorkFrame.setLayout(null);
                // updateWorkFrame.setTitle("Add New Staff");
                // staffDetailsLabel = new JLabel("Staff Details");
                // staffDetailsLabel.setForeground(Color.black);
                // staffDetailsLabel.setFont(new Font("Serif", Font.BOLD, 20));

                // fNameLabel = new JLabel("First name");
                // fNameLabel.setFont(new Font("Serif", Font.BOLD, 15));
                // lNameLabel = new JLabel("Last name");
                // lNameLabel.setFont(new Font("Serif", Font.BOLD, 15));

                // rollLabel = new JLabel("Roll");
                // rollLabel.setFont(new Font("Serif", Font.BOLD, 15));
                // emailLabel = new JLabel("Email");
                // emailLabel.setFont(new Font("Serif", Font.BOLD, 15));

                // phoneLabel = new JLabel("Phone Number");
                // phoneLabel.setFont(new Font("Serif", Font.BOLD, 15));

                // usernameLabel = new JLabel("Set Username");
                // usernameLabel.setFont(new Font("Serif", Font.BOLD, 15));

                // passwordLabel = new JLabel("Set Password");
                // passwordLabel.setFont(new Font("Serif", Font.BOLD, 15));

                // String[] optionsToChoose = { "Admin", "Supervisor" };
                // JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
                // jComboBox.setBackground(Color.white);

                // tf1 = new JTextField();
                // tf2 = new JTextField();
                // tf5 = new JTextField();
                // tf6 = new JTextField();
                // tf7 = new JTextField();
                // tf8 = new JTextField();

                // addBtn = new JButton("ADD");

                // staffDetailsLabel.setBounds(200, 10, 400, 30);
                // fNameLabel.setBounds(80, 70, 200, 30);
                // lNameLabel.setBounds(80, 110, 200, 30);
                // rollLabel.setBounds(80, 155, 200, 30);
                // emailLabel.setBounds(80, 200, 200, 30);
                // phoneLabel.setBounds(80, 250, 200, 30);
                // usernameLabel.setBounds(80, 300, 200, 30);
                // passwordLabel.setBounds(80, 350, 200, 30);

                // tf1.setBounds(300, 70, 200, 30);
                // tf2.setBounds(300, 110, 200, 30);
                // jComboBox.setBounds(300, 160, 200, 30);
                // tf5.setBounds(300, 210, 250, 30);
                // tf6.setBounds(300, 260, 250, 30);
                // tf7.setBounds(300, 310, 250, 30);
                // tf8.setBounds(300, 360, 250, 30);
                // addBtn.setBounds(200, 420, 150, 30);

                // updateWorkFrame.add(staffDetailsLabel);
                // updateWorkFrame.add(fNameLabel);
                // updateWorkFrame.add(tf1);
                // updateWorkFrame.add(lNameLabel);
                // updateWorkFrame.add(tf2);
                // updateWorkFrame.add(rollLabel);
                // updateWorkFrame.add(emailLabel);
                // updateWorkFrame.add(jComboBox);
                // updateWorkFrame.add(emailLabel);
                // updateWorkFrame.add(tf5);
                // updateWorkFrame.add(phoneLabel);
                // updateWorkFrame.add(tf6);
                // updateWorkFrame.add(usernameLabel);
                // updateWorkFrame.add(tf7);
                // updateWorkFrame.add(l8);
                // updateWorkFrame.add(tf8);

                // updateWorkFrame.add(addBtn);

            } else if (code.equals("UMT")) {
                System.out.println("used Materials  ");

                Materials m1 = new Materials(idneed, idneed.substring(36));

            } else if (code.equals("CCT")) {
                System.out.println("Contact client");

            } else if (code.equals("GTR")) {
                System.out.println("GET report ");

            }

        }

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

        submitBtn = new JButton("<html>&nbsp;Submit</html>");
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
    public void logout() {
        // TODO Auto-generated method stub

    }

    @Override
    public void viewWork(int value) {
        // TODO Auto-generated method stub

    }

}
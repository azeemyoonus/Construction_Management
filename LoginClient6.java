import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class LoginFrame implements ActionListener {

    public static void main(String[] args) {

        System.out.println("Welcome to Construction Management");
        LoginFrame construction = new LoginFrame();    
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    
        
    }
    JFrame f = new JFrame();//creating instance of JFrame
    public LoginFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();


        // JFrame f=new JFrame();         
        f.setSize((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/2);
        f.setLocation((int)screenSize.getWidth()/4, (int)screenSize.getHeight()/4);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH); //full size frame       
        f.getContentPane().setBackground(Color.decode("#f0f0f0"));       
        f.setLayout(new GridLayout(4,0));

        JPanel headerPanel= new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        headerPanel.setBounds(0,0,(int)screenSize.getWidth(),(int)screenSize.getHeight()/4);          
        headerPanel.setBackground(Color.decode("#f0f0f0"));
       
        ImageIcon logo = new ImageIcon( LoginFrame.class.getResource("/Images/construction_logo.png") );
        JLabel logoLabel = new JLabel(logo, SwingConstants.LEFT);               
       
        JLabel titleLabel =new JLabel("Albatross Builders");         
        titleLabel.setHorizontalAlignment(SwingConstants.LEADING);       
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 48));
       
        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel); 

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);       
        contentPanel.setBounds(0, 0,(int)screenSize.getWidth(),(int)screenSize.getHeight()/4);
        JLabel headingLabel = new JLabel("<html><center><font color='#ebc38a'> Login</font></center></html>");
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setLocation(0, 0);
        headingLabel.setForeground(Color.decode("#ebc38a"));
        headingLabel.setBounds(0, 0,(int)screenSize.getWidth(),(int)screenSize.getHeight()/4);
        headingLabel.setFont(new Font("SansSerif", Font.PLAIN, 35));
        contentPanel.add(headingLabel);

        Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
    



    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);


    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            if (userText.equalsIgnoreCase("mehtab") && pwdText.equalsIgnoreCase("12345")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        }
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }
    }
    }
}

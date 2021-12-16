import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.border.EmptyBorder;
import javax.swing.*;


public class work {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    JFrame reqWorkFrame;
    JTextField nameField;

    Config connection = new Config();
    Connection con= connection.dbConnect();

    private String name;


 

    
    /**
     * Default constructor
     */
    public work(String StringId) {

      String sql = " select * from client where c_id=? ";
        ResultSet rs;
        String name ;
        try {                    
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setObject(1, UUID.fromString(StringId) );           
            rs = prepareStatement.executeQuery();
            while (rs.next()) {                    
                // System.out.println(rs.getObject(1) + " " + rs.getString(2));
                this.name =rs.getString(2)+ rs.getString(3);
                // loadClientPanel(rs.getString(1));
                // loginFrame.dispose();
            }
          
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        reqWorkFrame = new JFrame();
        reqWorkFrame.setLayout(new GridLayout(4, 0));
        reqWorkFrame.setSize((int) screenSize.getWidth() / 2, (int) (screenSize.getHeight() + 250) / 4);
        reqWorkFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);

        JPanel titlePanel = new JPanel();
        JLabel workLabel = new JLabel("Request A Work");
        workLabel.setFont(new Font("SansSerif", Font.PLAIN, 38));
        titlePanel.add(workLabel);        
        JLabel userLabel = new JLabel();
        userLabel.setText("<html>&nbsp;&nbsp;&nbsp;Name: </html>"); // set label value for textField1
        userLabel.setBackground(Color.DARK_GRAY);
        titlePanel.add(workLabel);

        nameField = new JTextField(15);
        nameField.setText(this.name);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(userLabel);
        namePanel.add(nameField);

        Jpanel sitePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        reqWorkFrame.add(titlePanel);
        reqWorkFrame.add(namePanel);
        reqWorkFrame.setVisible(true);
    }

    /**
     * 
     */

  //  public void w_id;

    /**
     * 
     */
 //   public void date;

    /**
     * 
     */
  //  public void s_id;

    /**
     * 
     */
   // public void type;

    /**
     * 
     */
    //private void paymentStatus;



}
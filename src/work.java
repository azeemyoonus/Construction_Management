import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;

public class work implements ActionListener, ItemListener {

  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

  JFrame reqWorkFrame;
  JTextField nameField, siteLocField, siteAreaField;
  JButton addWorkBtn;
  JComboBox workTypesBox;
  

  Config connection = new Config();
  Connection con = connection.dbConnect();
  String workTypes[] = { "House", "offices", "Shopping Complex" };
  private String name;
  public int s_day, s_month, s_year, e_day, e_month, e_year;
public String c_id;

  /**
   * Default constructor
   */
  
  public work(String c_id) {

    String sql = " select * from client where c_id=? ";
    ResultSet rs;
    String name;
    this.c_id= c_id;

    try {
      PreparedStatement prepareStatement = con.prepareStatement(sql);
      prepareStatement.setObject(1, UUID.fromString(c_id));
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

    reqWorkFrame = new JFrame();
    reqWorkFrame.setLayout(new GridLayout(6, 0));
    reqWorkFrame.setSize((int) screenSize.getWidth() / 2, (int) (screenSize.getHeight() + 550) / 3);
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
    siteLocField = new JTextField(15);
    siteAreaField = new JTextField(15);

    JLabel siteLocLabel = new JLabel();
    siteLocLabel.setText("<html>&nbsp;&nbsp;&nbsp;Site Location: </html>"); // set label value for textField1
    siteLocLabel.setBackground(Color.DARK_GRAY);

    JLabel siteAreaLabel = new JLabel();
    siteAreaLabel.setText("<html>&nbsp;&nbsp;&nbsp;Site Area in sq.ft: </html>"); // set label value for textField1
    siteAreaLabel.setBackground(Color.DARK_GRAY);

    JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    namePanel.add(userLabel);
    namePanel.add(nameField);

    JPanel sitePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    sitePanel.add(siteLocLabel);
    sitePanel.add(siteLocField);
    sitePanel.add(siteAreaLabel);
    sitePanel.add(siteAreaField);

    JLabel workTypeLabel = new JLabel();
    workTypeLabel.setText("<html>&nbsp;&nbsp;&nbsp;Work Type </html>"); // set label value for textField1
    workTypeLabel.setBackground(Color.DARK_GRAY);

    workTypesBox = new JComboBox<>(workTypes);
    workTypesBox.addItemListener(this);

    JPanel workTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    workTypePanel.add(workTypeLabel);
    workTypePanel.add(workTypesBox);

    addWorkBtn = new JButton("<html>&nbsp;Request A Work</html>");
    addWorkBtn.setBackground(Color.decode("#40392f"));
    addWorkBtn.setForeground(Color.decode("#ebc38a"));
    addWorkBtn.setFocusPainted(false);
    addWorkBtn.addActionListener(this);

    JPanel submitPanel = new JPanel();
    submitPanel.setAlignmentY(SwingConstants.BOTTOM);
    submitPanel.add(addWorkBtn);

    reqWorkFrame.add(titlePanel);
    reqWorkFrame.add(namePanel);
    reqWorkFrame.add(sitePanel);
    reqWorkFrame.add(workTypePanel);
    reqWorkFrame.add(submitPanel);

    reqWorkFrame.setVisible(true);
  }

  /**
   * 
   */

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    if (e.getSource() == addWorkBtn) {
      System.out.println("Add work req");
      String sql = "insert into work (w_id, c_id, s_day, s_month, s_year, workType) values (?,?,?,?,?,?); insert into site (s_id, w_id, siteloc, totalArea) values (?,?,?,?);";
      this.s_day= LocalDate.now().getDayOfMonth();
      this.s_month= LocalDate.now().getMonthValue();
      this.s_year = LocalDate.now().getYear();
      System.out.println(s_day+" " +s_month+" "+ s_year);
      try {
      UUID w_id = UUID.randomUUID();
      UUID site_id = UUID.randomUUID();
        
      PreparedStatement prepareStatement = con.prepareStatement(sql);
      prepareStatement.setObject(1, w_id);
      prepareStatement.setObject(2, UUID.fromString(c_id));
      prepareStatement.setString(3, String.valueOf(s_day));
      prepareStatement.setString(4, String.valueOf(s_month));
      prepareStatement.setString(5, String.valueOf(s_year));
      prepareStatement.setString(6, (String) workTypesBox.getSelectedItem());

      prepareStatement.setObject(7, site_id);
      prepareStatement.setObject(8,w_id);
      prepareStatement.setString(9, siteLocField.getText());
      prepareStatement.setString(10, siteAreaField.getText());
    
      int updatedCount = prepareStatement.executeUpdate();
      System.out.println(updatedCount + " updated ");
      this.reqWorkFrame.dispose();
      

      } catch (SQLException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
      }
    }

  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    // TODO Auto-generated method stub
    if (e.getSource() == workTypesBox) {
      //
    }

  }

  // public void w_id;

  /**
   * 
   */
  // public void date;

  /**
   * 
   */
  // public void s_id;

  /**
   * 
   */
  // public void type;

  /**
   * 
   */
  // private void paymentStatus;

}
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
  JTextField nameField, siteLocField, siteAreaField, estimateArea;
  JButton addWorkBtn, updateWorkBtn;
  JLabel workIdArea;
  JComboBox workTypesBox, paymentStatusComboBox, workStatusComboBox;

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
    if (c_id != null) {
      String sql = " select * from client where c_id=? ";
      ResultSet rs;
      String name;
      this.c_id = c_id;

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

  }

  /**
   * 
   */
  public void updateWorkProgess(String w_id) {
    System.out.println("reached   .");
    JFrame updateWorkFrame = new JFrame();
    updateWorkFrame.setLayout(null);
    updateWorkFrame.setSize(600, 300);
    updateWorkFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);
    updateWorkFrame.setTitle("Work Details");
    updateWorkFrame.getContentPane().setBackground(Color.decode("#f0f0f0"));

    JLabel titelLabel = new JLabel("Update your Work Details");
    titelLabel.setForeground(Color.black);
    titelLabel.setFont(new Font("Serif", Font.BOLD, 20));
    titelLabel.setBounds(170, 30, 400, 30);

    JLabel wordIdLabel = new JLabel("Work Id:");
    wordIdLabel.setBounds(80, 70, 200, 30);

    JLabel estLabel = new JLabel("Total Estimate:");
    estLabel.setBounds(80, 110, 200, 30);

    JLabel payLabel = new JLabel("Payment Status");
    payLabel.setBounds(80, 155, 200, 30);

    JLabel workStatusLabel = new JLabel("Work Status");
    workStatusLabel.setBounds(80, 155, 200, 30);

    String[] paymentStatusToChoose = { "true", "false" };

    paymentStatusComboBox = new JComboBox<>(paymentStatusToChoose);
    paymentStatusComboBox.setBackground(Color.white);

    String[] options = { "Work in Progress", "Completed" };
    workStatusComboBox = new JComboBox<>(options);
    workStatusComboBox.setBackground(Color.white);

    workIdArea = new JLabel(w_id);
    // workIdArea.setPreferredSize(new Dimension(400, 50));

    estimateArea = new JTextField();

    updateWorkBtn = new JButton("Submit");
    updateWorkBtn.setBounds(220, 220, 150, 30);
    updateWorkBtn.setBackground(Color.decode("#a39887"));
    updateWorkBtn.setFocusPainted(false);
    updateWorkBtn.setName(w_id);
    updateWorkBtn.addActionListener(this);

    workIdArea.setBounds(300, 70, 200, 30);

    estimateArea.setBounds(300, 110, 200, 30);
    paymentStatusComboBox.setBounds(300, 160, 200, 30);
    workStatusComboBox.setBounds(300, 160, 200, 30);

    updateWorkFrame.add(titelLabel);
    updateWorkFrame.add(wordIdLabel);
    updateWorkFrame.add(workIdArea);

    updateWorkFrame.add(estLabel);
    updateWorkFrame.add(estimateArea);

    // updateWorkFrame.add(payLabel);
    // updateWorkFrame.add(paymentStatusComboBox);

    updateWorkFrame.add(workStatusLabel);
    updateWorkFrame.add(workStatusComboBox);
    // addMatFrame.add(l5);
    // addMatFrame.add(workStatus);
    updateWorkFrame.add(updateWorkBtn);
    updateWorkFrame.setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    if (e.getSource() == addWorkBtn) {
      System.out.println("Add work req");
      String sql = "insert into work (w_id, c_id, s_day, s_month, s_year, workType) values (?,?,?,?,?,?); insert into site (s_id, w_id, siteloc, totalArea) values (?,?,?,?);";
      this.s_day = LocalDate.now().getDayOfMonth();
      this.s_month = LocalDate.now().getMonthValue();
      this.s_year = LocalDate.now().getYear();
      System.out.println(s_day + " " + s_month + " " + s_year);
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
        prepareStatement.setObject(8, w_id);
        prepareStatement.setString(9, siteLocField.getText());
        prepareStatement.setString(10, siteAreaField.getText());

        int updatedCount = prepareStatement.executeUpdate();
        System.out.println(updatedCount + " updated ");
        this.reqWorkFrame.dispose();

      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    } else if (e.getSource() == updateWorkBtn) {
      if (workStatusComboBox.getSelectedItem() == "Completed") {
        String sql1 = "update work set totalestimate=?, e_day=?, e_month=?, e_year=? where w_id=?";
        this.e_day = LocalDate.now().getDayOfMonth();
        this.e_month = LocalDate.now().getMonthValue();
        this.e_year = LocalDate.now().getYear();

        try {
          PreparedStatement prepareStatement1 = con.prepareStatement(sql1);
          prepareStatement1.setString(1, estimateArea.getText());
          prepareStatement1.setInt(2, e_day);
          prepareStatement1.setInt(3, e_month);
          prepareStatement1.setInt(4, e_year);
          prepareStatement1.setObject(5, UUID.fromString(updateWorkBtn.getName()));
          int count = prepareStatement1.executeUpdate();

          System.out.println(count + " row updated with date");
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }

      } else {
        String sql = "update work set totalestimate=? where w_id=? ";
        try {
          PreparedStatement prepareStatement = con.prepareStatement(sql);
          prepareStatement.setString(1, estimateArea.getText());
          prepareStatement.setObject(2, UUID.fromString(updateWorkBtn.getName()));

          int count = prepareStatement.executeUpdate();
          System.out.println(count + " update without date");
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
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
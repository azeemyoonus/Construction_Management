import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;

/**
 * 
 */
public class Materials implements ActionListener, ItemListener {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    JFrame addMaterials, showMaterialsFrame, addMatToComFrame;
    JButton addmatBtn, addMatsubBtn, addMatToComBtn;
    JTextField matQtyArea, estimateArea, mNameField, mCostField, mSizeField, mCompanyField;
    JLabel workIdArea;
    JComboBox materalsBox, paymentStatusComboBox;
    JPanel allMaterialPanel;
    JScrollPane allMaterialScrollPane;
    ArrayList<String> materialNames = new ArrayList<String>();
    JComboBox<String> workStatusComboBox;
    Config connection = new Config();
    Connection con = connection.dbConnect();
    String w_id, siteId;
    String[] str;

    /**
     * Default constructor
     */
    public Materials(String w_id, String siteId) {
        this.w_id = w_id;
        this.siteId = siteId;
        addMaterial();
    }

    public Materials(String siteId) {
        this.siteId = siteId;
        showMaterials();
    }

    public Materials() {

    }

    public void addMaterialstoCompany() {

        addMatToComFrame = new JFrame();
        addMatToComFrame.getContentPane().invalidate();
        addMatToComFrame.setSize(700, 350);
        addMatToComFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);        
        addMatToComFrame.setLayout(null);

        JPanel forheaderLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forheaderLabel.setBackground(Color.decode("#40392f"));
        forheaderLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        forheaderLabel.setBounds(0, 0, (int) screenSize.getWidth(), 80);

        JLabel headerLabel = new JLabel("Material Details");
        headerLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        headerLabel.setHorizontalAlignment(JLabel.LEFT);
        headerLabel.setForeground(Color.decode("#ebc38a"));
        headerLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));

        forheaderLabel.add(headerLabel);



        

        JLabel mNameLabel = new JLabel("Material name: ");
        // fnameLabel.setForeground(Color.decode("#ebc38a"));
        mNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
        mNameLabel.setBounds(80, 80, 200, 30);

        JLabel mCostLabel = new JLabel("Cost : ");
        // lnameLabel.setForeground(Color.decode("#ebc38a"));
        mCostLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
        mCostLabel.setBounds(80, 120, 200, 30);

        JLabel mSizeLabel = new JLabel("Size : ");
        // rollLabel.setForeground(Color.decode("#ebc38a"));
        mSizeLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
        mSizeLabel.setBounds(80, 165, 200, 30);

        JLabel mCompanyLabel = new JLabel("Company : ");
        // mailLabel.setForeground(Color.decode("#ebc38a"));
        mCompanyLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
        mCompanyLabel.setBounds(80, 210, 200, 30);

        mNameField = new JTextField();
        mNameField.setBounds(300, 85, 200, 30);

        mCostField = new JTextField();
        mCostField.setBounds(300, 125, 200, 30);

        mSizeField = new JTextField();
        mSizeField.setBounds(300, 170, 200, 30);

        mCompanyField = new JTextField();
        mCompanyField.setBounds(300, 210, 200, 30);

        addMatToComBtn = new JButton("ADD");
        addMatToComBtn.setBackground(Color.decode("#a39887"));
        // addStaffBtn.setAlignmentX(300);
        addMatToComBtn.setFocusPainted(false);
        addMatToComBtn.addActionListener(this);
        addMatToComBtn.setBounds(250, 260, 150, 30);

        addMatToComFrame.getContentPane().setBackground(Color.decode("#f0f0f0"));

        addMatToComFrame.add(forheaderLabel);

        addMatToComFrame.add(mNameLabel);
        addMatToComFrame.add(mNameField);

        addMatToComFrame.add(mCostLabel);
        addMatToComFrame.add(mCostField);

        addMatToComFrame.add(mSizeLabel);
        addMatToComFrame.add(mSizeField);

        addMatToComFrame.add(mCompanyLabel);
        addMatToComFrame.add(mCompanyField);

        addMatToComFrame.add(addMatToComBtn);

        addMatToComFrame.setVisible(true);

    }

    void showMaterials() {

        showMaterialsFrame = new JFrame();
        showMaterialsFrame.setLayout(null);
        showMaterialsFrame.setSize((int) screenSize.getWidth(), 300);
        showMaterialsFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(Color.decode("#40392f"));
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        titlePanel.setBounds(0, 0, (int) screenSize.getWidth(), 60);

        JLabel usedMaterialsLabel = new JLabel("Used Materials");
        usedMaterialsLabel.setBorder(new EmptyBorder(20, 20, 10, 10));
        usedMaterialsLabel.setHorizontalAlignment(JLabel.LEFT);
        usedMaterialsLabel.setForeground(Color.decode("#ebc38a"));

        titlePanel.add(usedMaterialsLabel);

        allMaterialPanel = new JPanel();
        // allWorkPanel.setSize(800, 00);
        allMaterialScrollPane = new JScrollPane(allMaterialPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        allMaterialPanel.setLayout(new BoxLayout(allMaterialPanel, BoxLayout.Y_AXIS));
        allMaterialScrollPane.setBounds(0, 60, (int) screenSize.getWidth() - 60, 200);
        String sql3 = "select  m_id, m_name, m_cost, m_size, m_company, m_quantity from materials natural join materialspec where materials.site_id =? ";
        Border blackline = BorderFactory.createLineBorder(Color.black);
        try {
            // System.out.println("index: "+materalsBox.getSelectedIndex());

            // System.out.println(id);
            System.out.println(this.siteId);
            PreparedStatement prepareStatement = con.prepareStatement(sql3);
            prepareStatement.setObject(1, UUID.fromString(this.siteId));

            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getObject(1) + " " + rs.getString(4));
                JPanel materialPanel = new JPanel(new GridLayout(2, 0));
                materialPanel.setBackground(Color.decode("#d1c4b2"));
                materialPanel.setBorder(blackline);

                JLabel matID = new JLabel("Material Id : " + rs.getString(1));
                matID.setBorder(new EmptyBorder(20, 20, 20, 20));
                matID.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                matID.setBackground(Color.decode("#f29105"));

                JLabel mName = new JLabel("Material Name : " + rs.getString(2));
                mName.setBorder(new EmptyBorder(20, 20, 20, 20));
                mName.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                mName.setBackground(Color.decode("#f29105"));

                JLabel mCost = new JLabel("Cost : " + rs.getString(3));
                mCost.setBorder(new EmptyBorder(20, 20, 20, 20));
                mCost.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                mCost.setBackground(Color.decode("#f29105"));

                JLabel mSize = new JLabel("Size : " + rs.getString(4));
                mSize.setBorder(new EmptyBorder(20, 20, 20, 20));
                mSize.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                mSize.setBackground(Color.decode("#f29105"));

                JLabel mCompany = new JLabel("Company : " + rs.getString(5));
                mCompany.setBorder(new EmptyBorder(20, 20, 20, 20));
                mCompany.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                mCompany.setBackground(Color.decode("#f29105"));

                JLabel mQty = new JLabel("Quantity : " + rs.getString(6));
                mQty.setBorder(new EmptyBorder(20, 20, 20, 20));
                mQty.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                mQty.setBackground(Color.decode("#f29105"));

                materialPanel.add(matID);
                materialPanel.add(mName);
                materialPanel.add(mCost);
                materialPanel.add(mSize);
                materialPanel.add(mCompany);
                materialPanel.add(mQty);

                allMaterialPanel.add(materialPanel);

            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        showMaterialsFrame.add(titlePanel);
        showMaterialsFrame.add(allMaterialScrollPane);
        showMaterialsFrame.setVisible(true);
    }

    private void addMaterial() {
        addMaterials = new JFrame();
        addMaterials.setLayout(new GridLayout(4, 0));
        addMaterials.setSize((int) screenSize.getWidth() / 2, (int) (screenSize.getHeight() + 600) / 6);
        addMaterials.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);

        JPanel titlePanel = new JPanel();
        JLabel addMaterialLabel = new JLabel("Add Materials");
        addMaterialLabel.setFont(new Font("SansSerif", Font.PLAIN, 38));
        titlePanel.add(addMaterialLabel);

        JLabel matSpecLabel = new JLabel();
        matSpecLabel.setText("<html>&nbsp;&nbsp;&nbsp;Material Sepc: </html>"); // set label value for textField1
        matSpecLabel.setBackground(Color.DARK_GRAY);

        matQtyArea = new JTextField(2);

        JLabel matQtyLabel = new JLabel();
        matQtyLabel.setText("<html>&nbsp;&nbsp;&nbsp;Material Quantity: </html>"); // set label value for textField1
        matQtyLabel.setBackground(Color.DARK_GRAY);

        JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        qtyPanel.add(matQtyLabel);
        qtyPanel.add(matQtyArea);

        getMaterials();

        this.str = new String[materialNames.size()];

        for (int i = 0; i < materialNames.size(); i++) {
            str[i] = materialNames.get(i).substring(36);
        }

        materalsBox = new JComboBox<>(str);
        materalsBox.addItemListener(this);

        JPanel materialPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        materialPanel.add(matSpecLabel);
        materialPanel.add(materalsBox);

        addmatBtn = new JButton("<html>&nbsp;Add Material</html>");
        addmatBtn.setBackground(Color.decode("#40392f"));
        addmatBtn.setForeground(Color.decode("#ebc38a"));
        addmatBtn.setFocusPainted(false);
        addmatBtn.addActionListener(this);

        JPanel submitPanel = new JPanel();
        submitPanel.setAlignmentY(SwingConstants.BOTTOM);
        submitPanel.add(addmatBtn);

        addMaterials.add(titlePanel);
        addMaterials.add(materialPanel);
        addMaterials.add(qtyPanel);
        addMaterials.add(submitPanel);

        addMaterials.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == addmatBtn) {

            String sql3 = "insert into  materials values (?,?,? )";

            try {
                // System.out.println("index: "+materalsBox.getSelectedIndex());

                String id = this.materialNames.get(materalsBox.getSelectedIndex());
                id = id.substring(0, 36);
                System.out.println(id);
                System.out.println(siteId);
                PreparedStatement prepareStatement = con.prepareStatement(sql3);
                prepareStatement.setObject(1, UUID.fromString(id));
                prepareStatement.setInt(3, Integer.parseInt(matQtyArea.getText()));
                prepareStatement.setObject(2, UUID.fromString(siteId));

                int count = prepareStatement.executeUpdate();
                System.out.println(count + " updated");

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } else if (e.getSource() == addMatsubBtn) {
            String sql3 = "insert into  materialspec values (?,?,?,?,? )";

            try {
                UUID m_id = UUID.randomUUID();
                PreparedStatement prepareStatement = con.prepareStatement(sql3);
                prepareStatement.setObject(1, m_id);
                prepareStatement.setObject(2, UUID.fromString(siteId));
                prepareStatement.setInt(3, Integer.parseInt(matQtyArea.getText()));

                int count = prepareStatement.executeUpdate();
                System.out.println(count + " updated");

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
        else if (e.getSource()==addMatToComBtn){
            String sql1 = "insert into materialspec values(?,?,?,?,?)";
            UUID id = UUID.randomUUID();
            try{
                PreparedStatement preparedStatement1 =con.prepareStatement(sql1);
                preparedStatement1.setObject(1, id);
                preparedStatement1.setString(2, mNameField.getText());
                preparedStatement1.setInt(3, Integer.parseInt(mCostField.getText()));
                preparedStatement1.setString(4, mSizeField.getText());
                preparedStatement1.setString(5, mCompanyField.getText());
              int count = preparedStatement1.executeUpdate();
              System.out.println(count+" row updated");
                if (count==1){
                    addMatToComFrame.dispose();
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    }

    public void getMaterials() {

        String sql = " select * from materialspec";
        ResultSet rs;
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            rs = prepareStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                String item = rs.getString(1) + rs.getString(2) + "  size: " + rs.getString(4) + " cost : "
                        + rs.getInt(3) + " company: " + rs.getString(5);
                this.materialNames.add(item);
                i++;
            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    /**
     * 
     */
    // private void m_Id;

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub

    }

    // /**
    // *
    // */
    // private void m_Name;

    // /**
    // *
    // */
    // private void m_company;

    // /**
    // *
    // */
    // protected void m_cost;

    // /**
    // *
    // */
    // protected void m_quantity;

    // /**
    // *
    // */
    // protected void total_Mcost;

    // /**
    // * @return
    // */
    // public void getM_Id() {
    // // TODO implement here
    // return null;
    // }

    // /**
    // * @param value
    // */
    // public void setM_Id(void value) {
    // // TODO implement here
    // }

    // /**
    // *
    // */
    // public void materialCost() {
    // // TODO implement here
    // }

    // /**
    // * @return
    // */
    // public void getM_Id() {
    // // TODO implement here
    // return null;
    // }

    // /**
    // * @return
    // */
    // public void getM_Name() {
    // // TODO implement here
    // return null;
    // }

    // /**
    // * @param value
    // */
    // public void setM_Name(void value) {
    // // TODO implement here
    // }

    // /**
    // * @return
    // */
    // public void getM_company() {
    // // TODO implement here
    // return null;
    // }

    // /**
    // * @param value
    // */
    // public void setM_company(void value) {
    // // TODO implement here
    // }

    // /**
    // * @return
    // */
    // public void getM_quantity() {
    // // TODO implement here
    // return null;
    // }

    // /**
    // * @param value
    // */
    // public void setM_quantity(void value) {
    // // TODO implement here
    // }

    // /**
    // * @return
    // */
    // public void getTotal_Mcost() {
    // // TODO implement here
    // return null;
    // }

    // /**
    // * @param value
    // */
    // public void setTotal_Mcost(void value) {
    // // TODO implement here
    // }

}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDADMIN;

/**
 *
 * @author ivan
 */

import DSA.LinkedListAccounts;
import DSA.NodeAccounts;
import LogSigBackEnd.UserService;
    import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class adminCOA extends JFrame{
     private JPanel pan5 = new JPanel();
    private JPanel pan6 = new JPanel();
    private JPanel pan7 = new JPanel();
    private JLabel amlbl = new JLabel("Account Management");
    private JTable accountTable = new JTable();
    private JScrollPane accountTableScp = new JScrollPane(accountTable);
    private JTextField searchField = new JTextField();
    private JButton searchbtn = new JButton("Search");
    private JButton addAccount = new JButton("Add Account");
    private JButton deleteAccount = new JButton("Delete Account");
    private JButton unblockAccount = new JButton("Unblock Account"); // no function yet
    private JButton changePassword = new JButton("Change Password");
    private JButton backbtnCOA = new JButton("Back");
    private JComboBox<String> userCategory = new JComboBox<>(new String[]{"All Accounts", "Librarian Accounts", "Admin Accounts", "User Accounts", "Blocked Accounts"});

    private static String[] columnsForAccounts = {"Member ID", "Username", "Email", "Contact", "Role"};
    private static DefaultTableModel accountTableModel = new DefaultTableModel(columnsForAccounts, 0);

    private LinkedListAccounts accounts;
    private UserService userService;

    public adminCOA(UserService userService,LinkedListAccounts accounts) {
        this.userService =  userService;
        this.accounts = accounts;

        // Call to set up the UI components
        //setupUI();

        // Load the accounts initially
       // loadAccounts("All Accounts");
          userService.updateUserTable(accountTable);
    }

   
        public  void setupUI(){
        
        pan5.setBackground(new Color(0x99582A));
        pan5.setLayout(null);
        pan5.setPreferredSize(new Dimension(800, 875));

        pan6.setBackground(new Color(0x6F1D1B));
        pan6.setLayout(null);
        pan6.setBounds(850, 86, 758, 840);
        
        pan7.setLayout(null);
        pan7.setBackground(new Color(0xD9D9D9));
        pan7.setBounds(0, 0, 1530, 86);

        amlbl.setBounds(70, 21, 560, 77);
        amlbl.setFont(new Font("Bebas Neue", Font.BOLD, 50));
        amlbl.setForeground(new Color(0xBB9457));

        accountTable.setModel(accountTableModel);
        accountTableScp.setBounds(50, 175, 780, 490);

        searchField.setBounds(50, 125, 250, 30);
        searchbtn.setBounds(310, 125, 100, 30);
        userCategory.setBounds(420, 125, 200, 30);
        
        addAccount.setBounds(185, 200, 310, 88);
        addAccount.setFont(new Font("Bebas Neue", Font.BOLD, 30));
        addAccount.setForeground(new Color(0x6F1D1B));
        addAccount.setBackground(new Color (0xD9D9D9));
        
        deleteAccount.setBounds(185, 350, 310, 88);
        deleteAccount.setFont(new Font("Bebas Neue", Font.BOLD, 30));
        deleteAccount.setForeground(new Color(0x6F1D1B));
        deleteAccount.setBackground(new Color (0xBB9457));
        
        backbtnCOA.setBounds(185, 500, 310, 88);
        backbtnCOA.setFont(new Font("Bebas Neue", Font.BOLD, 30));
        backbtnCOA.setForeground(new Color(0x6F1D1B));
        backbtnCOA.setBackground(new Color (0xD9D9D9));
        backbtnCOA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                   dashBoard dashb = new dashBoard(userService,accounts);
                   dashb.setVisible(true);
                   
                   ((JFrame) SwingUtilities.getWindowAncestor(backbtnCOA)).dispose();
            }
        });
        
        addAccount.addActionListener(e -> addAccountDialog());
        deleteAccount.addActionListener(e -> {
           int selectedRow = accountTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select an account to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Retrieve the userID from the selected row (column index may vary, here assumed to be 0)
    int userID = (int) accountTable.getValueAt(selectedRow, 0);

    // Perform deletion using userID
    boolean deleted = accounts.deleteById(userID);

    if (deleted) {
        JOptionPane.showMessageDialog(null, "Account deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Reload the table
    loadAccounts("All Accounts");
        });

        userCategory.addActionListener(e -> filterAccounts());
        searchbtn.addActionListener(e -> searchAccounts());
        searchField.addActionListener(e -> searchAccounts());

        
        loadAccounts("All Accounts");
        
        pan5.add(pan6);
        pan5.add(pan7);
        pan5.add(accountTableScp);
        pan5.add(searchField);
        pan5.add(searchbtn);
        pan5.add(userCategory);
        
        pan6.add(amlbl);
        pan6.add(addAccount);
        pan6.add(deleteAccount);
        pan6.add(backbtnCOA);

        this.add(pan5);
        
        this.setSize(1537, 875);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Account Management");
        this.setResizable(false);
    }

    private void addAccountDialog() {
        
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField emailField = new JTextField();
        JTextField contactField = new JTextField();
        JComboBox<String> roleField = new JComboBox<>(new String[]{"Librarian", "Admin"});
        
      

        Object[] message = {
            "Username:", usernameField,
            "Password:", passwordField,
            "Email:", emailField,
            "Contact Number:", contactField,
            "Role:", roleField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add New Account", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();
            String contact = contactField.getText();
            String role = (String) roleField.getSelectedItem();
            
            if (username.isEmpty()|| password.isEmpty() || email.isEmpty() || contact.isEmpty()) {
    JOptionPane.showMessageDialog(null, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}
            
          int userID = userService.addUser(username, password, email, contact, role);
          
            accounts.insertAtBeginning(username, password, email, contact,userID, role);
            
            userService.updateUserTable(accountTable);
            
            JOptionPane.showMessageDialog(null, "Account added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadAccounts("All Accounts");
        }
    }
      public void populateTable() {
          System.out.println("Andito kana sa populatetable");
     DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
    model.setRowCount(0);  // Clear the table

    NodeAccounts current = accounts.getHead();  // Assuming you have a head pointer in the LinkedList
    while (current != null) {
         System.out.println("Adding account: " + current.getUserName());
        model.addRow(new Object[] {
            
            current.getMemberID(), 
            current.getUserName(), 
            current.getEMail(), 
            current.getContactNum(), 
            current.getRole()
        });
       
        current = current.next;  // Traverse the linked list
    }
   }
    private void filterAccounts() {
        String selectedCategory = (String) userCategory.getSelectedItem();
        loadAccounts(selectedCategory);
    }

    private void searchAccounts() {
        String searchText = searchField.getText().trim();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search term.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

       
        accountTableModel.setRowCount(0);
        NodeAccounts current = accounts.getHead();
        while (current != null) {
            if (String.valueOf(current.getMemberID()).contains(searchText) || 
    current.getUserName().contains(searchText) || 
    current.getRole().contains(searchText.toLowerCase())) {
    accountTableModel.addRow(new Object[]{
        current.getMemberID(),
        current.getUserName(),
        current.getEMail(),
        current.getContactNum(),
        current.getRole()
    });
}

            current = current.next;
        }

        if (accountTableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No results found.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void loadAccounts(String filterRole) {
    accountTableModel.setRowCount(0); // Clear the table

    NodeAccounts current = accounts.getHead();
    while (current != null) {
        boolean addRow = false;

        // Determine if the row should be added based on the filter
        if (filterRole.equals("All Accounts")) {
            addRow = true; // Show all accounts
        } else if (filterRole.equals("Librarian Accounts") && current.getRole().equals("librarian")) {
            addRow = true;
        } else if (filterRole.equals("Admin Accounts") && current.getRole().equals("admin")) {
            addRow = true;
        } else if (filterRole.equals("User Accounts") && current.getRole().equals("user")) {
            addRow = true;
        } else if (filterRole.equals("Blocked Accounts") && current.getRole().equals("blocked")) {
            addRow = true;
        }

        // Add matching rows to the table
        if (addRow) {
            accountTableModel.addRow(new Object[]{
                current.getMemberID(),
        current.getUserName(),
        current.getEMail(),
        current.getContactNum(),
        current.getRole()
            });
        }

        current = current.next;
    }
}


    public static void main(String[] args) {
     //   adminCOA create = new adminCOA();
     //   create.setVisible(true);
    }
}


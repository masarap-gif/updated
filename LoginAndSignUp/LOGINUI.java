/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginAndSignUp;

import BACKENDUSER.LLhistory;
import LogSigBackEnd.UserService;
import LogSigBackEnd.Users;
import DSA.LinkedListAccounts;
import DSA.LinkedlistBook;
import DSA.NodeAccounts;
import FRONTENDADMIN.dashBoard;
import FRONTENDLIB.DASHBOARDUI;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import FRONTENDLIB.DASHBOARDUI;

/**
 *
 * @author ivan
 */
public class LOGINUI extends JFrame  {

    private JTextField nnField;
    private JPasswordField ppField;
     LinkedlistBook bookList;
     LinkedListAccounts acc;
     LLhistory history;
     
    
    
    public LOGINUI( LinkedlistBook bookList,LinkedListAccounts acc,LLhistory history) {
        this.bookList = bookList;
        this.acc = acc;
        this.history = history;
        UserService nyes = UserService.getInstance(); //
        setTitle("Log-in Form");
        setSize(719, 358);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel formTitle = new JLabel("Login Form");
        formTitle.setBounds(456, 10, 300, 40);
        formTitle.setFont(new Font("Bebas Neue", Font.BOLD, 36));
        formTitle.setForeground(new Color(0x6F1D1B));
        add(formTitle);

        JLabel nn = new JLabel("Username:");
        JLabel pp = new JLabel("Password:");
        nn.setBounds(39, 60, 200, 37);
        nn.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 24));
        pp.setBounds(39, 140, 200, 37);
        pp.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 24));

        nnField = new JTextField();
        nnField.setBounds(170, 60, 493, 48);
        nnField.setBackground(new Color(0xD9D9D9));

        ppField = new JPasswordField();
        ppField.setBounds(170, 140, 493, 48);
        ppField.setBackground(new Color(0xD9D9D9));

        JButton login = new JButton("Log-In");
        login.setBounds(170, 240, 234, 48);
        login.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        login.setForeground(Color.white);
        login.setBackground(new Color(0xBB9457));
        login.addActionListener(e -> {
            String username = nnField.getText();
            String passwordInput = new String(ppField.getPassword());
            UserService userService = UserService.getInstance();

      if (username.equals("librarian") && passwordInput.equals("lib123")) {
    System.out.println("Librarian credentials match.");
    DASHBOARDUI librarianDash = new DASHBOARDUI(bookList,acc,history);
    this.dispose();
}
// Check for admin login
else if (username.equals("admin") && passwordInput.equals("admin123")) {
    System.out.println("Admin credentials match.");
    JOptionPane.showMessageDialog(null, "Login successful!");
    dashBoard adminDash = new dashBoard(nyes,acc);
    this.dispose();
}
// General user login using UserService
else if (userService.userExists(username) && userService.validatePassword(username, passwordInput)) {
    NodeAccounts loggedInUser = userService.getLinkedListAccounts().linearSearch(username);
    if (loggedInUser != null) {
        System.out.println("User found, role is: " + loggedInUser.getRole());
        if (loggedInUser.getRole().trim().equalsIgnoreCase("librarian")) {
            System.out.println("Role is librarian.");
            JOptionPane.showMessageDialog(null, "Login successful!");
            DASHBOARDUI librarianDash = new DASHBOARDUI(bookList,acc,history);
            this.dispose();
        } else if (loggedInUser.getRole().trim().equalsIgnoreCase("admin")) {
            System.out.println("Role is admin.");
            JOptionPane.showMessageDialog(null, "Login successful!");
            dashBoard adminDash = new dashBoard(nyes,acc);
            this.dispose();
        } else {
            System.out.println("Invalid role found.");
            JOptionPane.showMessageDialog(null, "Invalid role.");
        }
    } else {
        System.out.println("User not found.");
        JOptionPane.showMessageDialog(null, "User not found.");
    }
} else {
    System.out.println("Invalid username or password.");
    JOptionPane.showMessageDialog(null, "Invalid username or password.");
}
        });
        


        

        add(nn);
        add(pp);
        add(nnField);
        add(ppField);
        add(login);
        

        setVisible(true);
    }
      private boolean validateLogin(String username, String password) {
        // Default usernames and passwords for admin and librarian
        if (username.equals("admin") && password.equals("admin123")) {
            return true;  // Admin login success
        } else if (username.equals("librarian") && password.equals("lib123")) {
            return true;  // Librarian login success
        }
        return false;  // Invalid credentials
    }
    public static void main (String [] args){
    //    new LOGINUI();
    }
    
}

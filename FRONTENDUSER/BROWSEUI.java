/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDUSER;

/**
 *
 * @author ivan
 */
import BACKENDUSER.LLhistory;
import DSA.LinkedlistBook;
import DSA.NodeBook;
import LogSigBackEnd.User;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;



public class BROWSEUI extends JFrame implements ActionListener, ItemListener { 
//    ImageIcon icon = new ImageIcon("C:\\Users\\danic\\Downloads\\icons8-search-50.png");
   LinkedlistBook bookList;
  LLhistory bookHistory;
    private JComboBox<String> genreComboBox;
    private JPanel bookPanel;
    
    private JLabel a, lb1, lb2, lb3, lb4, b;
    private JTextField isbn;
    private JButton borrow, back, bbrw, search, exit;
    private JPanel pnl1, pnl2, pnl3, pnl4;
     private String[] headers = {"Title", "Author", "ISBN", "Genre", "Availability"};
     User users;
   DefaultTableModel defTab;

     
     public BROWSEUI(User user,LinkedlistBook bookList , LLhistory bookHistory){
           this.users = user;
               this.bookList = bookList;
               this.bookHistory = bookHistory;
//            public BROWSEUI(){
        
        //Add table
         
      defTab = new    DefaultTableModel(this.bookList.getBookData(), headers);
        JTable table = new JTable(defTab);
        JScrollPane sp = new JScrollPane(table);
        
        
        // When the this frame display, the sorted books displayed without performng an action
        updateTableWithSortedBooks(defTab);
            
        pnl2 = new JPanel();
        pnl2.setLayout(null);
        pnl2.setBackground(new Color(0xD9D9D9));
        pnl2.setBounds(0, 0, 1530, 86);
        
        
        search = new JButton();
        ImageIcon icon = new ImageIcon("C:\\Users\\danic\\Downloads\\icons8-search-50.png");
        Image image = icon.getImage();
        Image image2 = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        search.setIcon(new ImageIcon(image2));
        search.setBounds(215, 11, 81, 53);
        search.setBorder(null);
//        search.setText("Search");
        search.setHorizontalTextPosition(JButton.LEFT);
        search.setFont(new Font("Bebas Neue", Font.BOLD, 30));
        search.setForeground(Color.WHITE);
        search.setBackground(new Color(0xBB9457));
//        search.setIcon(new ImageIcon(image2));
        search.addActionListener(e -> {
            String inputISBN = isbn.getText(); // Get d input as a string     

            if (!inputISBN.isEmpty()) {
                try {
            // Convert input string to int
            int isbnNumber = Integer.parseInt(inputISBN);

           // Search for d book in the LL. Linear Search is used
            NodeBook book = bookList.LinearSeach(isbnNumber); 
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear the table

            // Check if the book is found
            if (book != null) {
                model.addRow(new Object[]{
                    book.getTitle(), book.getAuthor(), book.getISBN(), book.getGenre(), book.getIsAvailable() ? "Available" : "Checked Out"
                });
            } else {
                JOptionPane.showMessageDialog(this, "No book found with ISBN " + isbnNumber, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            // handle invalid num input
            JOptionPane.showMessageDialog(this, "Invalid ISBN. Please enter a valid numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // If input is empty
        JOptionPane.showMessageDialog(this, "Please enter an ISBN to search.", "Error", JOptionPane.ERROR_MESSAGE);
        resetTableData(); // Reset the table data
    }
    // Update table with all books sorted by title
    updateTableWithSortedBooks(defTab);
});
        
        a = new JLabel(users.getUsername());
        a.setFont(new Font("Bebas Neue", Font.BOLD, 48));
        a.setBounds(116, 20, 315, 60);
        
        exit = new JButton();
        exit.setBounds(1259, 15, 187, 56);
        exit.setText("Back");
        exit.setFont(new Font("Bebas Neue",Font.BOLD, 40));
        exit.setForeground(Color.white);
        exit.setBackground(new Color(0xBB9457));
        exit.setLayout(null);
       
                    
                
            
       
        lb1 = new JLabel("ISBN");
        lb1.setFont(new Font("Bebas Neue", Font.BOLD, 36));
        lb1.setBounds(1000, 100, 100, 43);
        lb1.setForeground(new Color(0xBB9457));

        isbn = new JTextField();
        isbn.setBounds(1000, 144, 300, 76);
        isbn.setBackground(new Color(0xBB9457));
        isbn.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
        isbn.setForeground(Color.white);
        isbn.add(search);

        lb2 = new JLabel("Genre");
        lb2.setFont(new Font("Bebas Neue", Font.BOLD, 36));
        lb2.setBounds(80, 100, 200, 43);
        lb2.setForeground(new Color(0xBB9457));

        genreComboBox = new JComboBox<>(new String[]{"All", "Fiction", "Non-Fiction", "Education"});
        genreComboBox.setSelectedIndex(0);
        genreComboBox.setBounds(80, 144, 320, 76);
        genreComboBox.setPreferredSize(new Dimension(333, 76));
        genreComboBox.setBackground(new Color(0x99582A));
        genreComboBox.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
        genreComboBox.setForeground(Color.white);
        genreComboBox.addActionListener(e -> {
        DefaultTableModel model = (DefaultTableModel) defTab;
        model.setRowCount(0); // Clear the table

        String selectedGenre = (String) genreComboBox.getSelectedItem();
        boolean found = false;

        // If a genre other than "All" is selected, filter books
        if (!selectedGenre.equals("All")) {
            Object[][] getBookData = bookList.getBookData(); // Get the book data

            // Loop the books t check the genre
            for (Object[] book : getBookData) {
                String bookGenre = (String) book[3];
                if (bookGenre.equalsIgnoreCase(selectedGenre)) {
                    String availability = (boolean) book[4] ? "Available" : "Checked Out";
                    model.addRow(new Object[]{book[0], book[1], book[2], book[3], availability});
                    found = true;
                }
            }

            // If no books were found for slected gen
            if (!found) {
                JOptionPane.showMessageDialog(this, "No books found in the genre: " + selectedGenre, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            //display sorted books "All"
            updateTableWithSortedBooks(defTab); 
        }
    });

        // Book panel for displaying books of the selected genre
        bookPanel = new JPanel();
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
        bookPanel.setBounds(33, 250, 728, 300);
        bookPanel.setBackground(new Color(0x6F1D1B));

        pnl1 = new JPanel();
        pnl1.setLayout(null);
        pnl1.setBackground(new Color(0x6F1D1B));
        pnl1.setBounds(-7, 86, 1500, 875);

        borrow = new JButton("Borrow");
        borrow.setBounds(950, 6550, 250, 88);
        borrow.setFont(new Font("Bebas Neue", Font.BOLD, 50));
        borrow.setForeground(new Color(0x6F1D1B));
        borrow.setBackground(Color.white);  
          borrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow(); // Get the selected row in the JTable
                if (row == -1) { // If no row is selected
                    JOptionPane.showMessageDialog(null, "Please select a row to borrow.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Retrieve the selected book from the LinkedList
                    NodeBook selectedBook = bookList.getAtIndex(row);

                    // Check if the selected book is available
                    if (!selectedBook.getIsAvailable()) {
                        JOptionPane.showMessageDialog(null, "This book is already checked out.", "Unavailable", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    
                    int choice = JOptionPane.showConfirmDialog(null, "Do you want to borrow this book?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (choice == JOptionPane.YES_OPTION) {
                        // Mark the book as unavailable in the LinkedList
                        selectedBook.setIsAvailable(false);

                        // Update the JTable 
                        table.setValueAt("Checked Out", row, 4); // index 4 for availability

                        
                        JOptionPane.showMessageDialog(null, "You have successfully borrowed the book!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Invalid row selected.", "Error", JOptionPane.ERROR_MESSAGE);
                }
    }
});
       
        back = new JButton("Back");
        back.setBounds(1200, 750, 226, 88);
        back.setFont(new Font("Bebas Neue", Font.BOLD, 50));
        back.setForeground(new Color(0x6F1D1B));
        back.setBackground(Color.white);
        
        exit.addActionListener(e -> {
    this.dispose(); // Dispose of current frame
    new DashboardUser(user, bookList,bookHistory).setVisible(true); // Open DashboardUser
});

back.addActionListener(e -> {
    this.dispose(); // Dispose of current frame
    new DashboardUser(user, bookList,bookHistory).setVisible(true); // Navigate back
});
       

        pnl4 = new JPanel();
        pnl4.setLayout(null);
        pnl4.setBounds(33, 240, 1390, 450);
        pnl4.setBackground(new Color(0xBB9457));

        b = new JLabel("Borrowing");
        b.setFont(new Font("Bebas Neue", Font.BOLD, 70));
        b.setBounds(270, 0, 826, 100);
        b.setForeground(new Color(0x6F1D1B));
        sp.setBounds(33, 20, 1320, 400);
        table.setFont(new Font("Bebas Neue", Font.PLAIN, 15));

        pnl2.add(a);   
        pnl2.add(exit);
        pnl1.add(lb1);
        pnl1.add(lb2);
        pnl1.add(isbn);
        pnl1.add(sp);       
        pnl1.add(genreComboBox);
        pnl1.add(bookPanel);
        pnl4.add(sp);


        this.add(borrow);
        this.add(pnl4);
        this.add(pnl2);
        this.add(pnl1);
      
        this.setSize(1476, 896);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);
        this.setTitle("Borrowing");
     }
     private void updateTableWithSortedBooks(DefaultTableModel defTab) {
    // Get the sorted books data
    Object[][] sortedBooks = bookList.sortByTitle(); // Calls quickSortByTitle() inside sortByTitle()

    // Clear the table model
    defTab.setRowCount(0);

    // Add the sorted books to the table model
    for (Object[] book : sortedBooks) {
        defTab.addRow(book);
    }
}
                
    public void resetTableData() {
    DefaultTableModel model = (DefaultTableModel) defTab;
    Object[][] getBookData = bookList.getBookData();
    model.setRowCount(0); // Clear the table
    
    for (Object[] book : getBookData) {
        String availability = (boolean) book[4] ? "Available" : "Checked Out";
        model.addRow(new Object[]{book[0], book[1], book[2], book[3], availability});
    }
}
    @Override
    public void actionPerformed(ActionEvent e) {
 
        } 
    @Override
    public void itemStateChanged(ItemEvent e) {  
    }
    public static void main (String[] args){
       //new  BROWSEUI(username);
    }
}
    

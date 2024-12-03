/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DSA;

/**
 *
 * @author ivan
 */

    import javax.swing.JOptionPane;

public class LinkedListAccounts{
    public static NodeAccounts head;
    private final int maxSize = 100;
    private int size = 0;

    public LinkedListAccounts() {
        this.head = null;
    }

   
    public boolean isFull() {
        return size >= maxSize;
    }

    public void insertAtBeginning(String userName, String password, String eMail, String contactNum,int id,  String role) {
        if (isFull()) {
            System.out.println("List is full. Cannot add more accounts.");
            return;
        }
       // int memberID = generateRandomID();
        NodeAccounts newNode = new NodeAccounts(userName, password, eMail, contactNum, id, role);
        newNode.next = head;
        head = newNode;
        size++;
    }
    public int generateRandomID() {
    int id = (int) (Math.random() * 100); // Generates a random ID within a specific range.
    while (!isUniqueID(id)) { // Keep generating until a unique ID is found
        id = (int) (Math.random() * 100);
    }
    return id;
}
    

public boolean isUniqueID(int id) {
    NodeAccounts current = getHead();
    while (current != null) {
        if (current.getMemberID()== id) {
            return false; // ID is not unique
        }
        current = current.getNext();
    }
    return true; // ID is unique
}

    public void deleteAtEnd() {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }

        if (head.next == null) {
            head = null;
        } else {
            NodeAccounts current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
        size--;
    }

  public boolean deleteById(int userID) {
    if (head == null) {
        System.out.println("The list is empty.");
        return false;
    }

    // If the head node matches
    if (head.getMemberID() == userID) {
        int choice = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this account?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (choice == JOptionPane.NO_OPTION) {
            System.out.println("Deletion canceled.");
            return false;
        }

        head = head.next; // Remove head node
        size--;
        System.out.println("Deleted account with userID: " + userID);
        return true;
    }

    // Traverse the list to find the matching node
    NodeAccounts current = head;
    while (current.next != null) {
        if (current.next.getMemberID() == userID) {
            int choice = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete this account?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (choice == JOptionPane.NO_OPTION) {
                System.out.println("Deletion canceled.");
                return false;
            }

            current.next = current.next.next; // Remove the matching node
            size--;
            System.out.println("Deleted account with userID: " + userID);
            return true;
        }
        current = current.next;
    }

    // If no match is found
    System.out.println("Account with userID: " + userID + " not found.");
    return false;
}

    public void createAccount(String userName, String password, String eMail, String contactNum,int id, String role) {
    if (isFull()) {
        System.out.println("List is full. Cannot create new account.");
        return;
    }
   // int memberID = generateRandomID();
    NodeAccounts newNode = new NodeAccounts(userName, password, eMail, contactNum, id, role);

    if (head == null) {
        head = newNode;
    } else {
        NodeAccounts current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }
    size++;
}

    public NodeAccounts getHead() {
        return head;
    }

    public NodeAccounts linearSearch(String userName) {
         NodeAccounts current = head; // Assuming `head` is the start of your linked list.
    while (current != null) {
        if (current.getUserName().equals(userName)) {
            System.out.println("Found user in linked list during search: " + userName);
            return current;
        }
        current = current.getNext();
    }
    System.out.println("User not found in linked list during search: " + userName);
    return null;
    }
    public boolean isExisted(int id){
        NodeAccounts current = head;
        while(current != null){
         if(current.getMemberID()==id){
             System.out.println("Found user in linked list during search: " + id);
            return true;
         }
         current = current.getNext();
    }
        System.out.println("User not found in linked list during search: " + id);
    return false;
    }

    


}

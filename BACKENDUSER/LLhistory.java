/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BACKENDUSER;

import DSA.LinkedListAccounts;
import DSA.NodeAccounts;
import LogSigBackEnd.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ivan
 */
public class LLhistory {
     private LinkedListAccounts linkedListAccounts; 
      private NodeHistory head;  // Head of the linked list

    // Constructor
    public LLhistory() {
        this.head = null;  // Initially, the list is empty
    }
   

    // Method to add a new NodeHistory at the end of the list
    public void addNode(NodeHistory newNode) {
        
        if (head == null) {
            head = newNode; 
              System.out.println("Added first node to history: " + newNode.getTitle());// If the list is empty, the new node becomes the head
        } else {
            NodeHistory current = head;
            // Traverse to the last node
            while (current.getNext() != null) {
                current = current.getNext();
               
            }
            // Add the new node at the end of the list
            current.next = current;
             System.out.println("Added node to history: " + newNode.getTitle());
        }
    }
    public void addBookToHistory(NodeHistory book) {
    if (head == null) {
        head = book;
    } else {
        NodeHistory current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(book);
    }

    // Log the added book for debugging
    System.out.println("Book added to history: " + book.getTitle() + " (User: " + book.getCd() + ")");
}
   
    
   
      public NodeHistory getHead() {
        return head;
    }

    // Method to retrieve a NodeHistory by index
    public NodeHistory getNode(int index) {
        NodeHistory current = head;
        int count = 0;
        // Traverse the list to find the node at the given index
        while (current != null) {
            if (count == index) {
                return current;  // Node found at index
            }
            count++;
            current = current.getNext();
        }
        return null;  // Return null if the index is out of bounds
    }

    // Method to remove a NodeHistory by index
    public boolean removeNode(int index) {
        if (head == null) {
            return false;  // List is empty
        }

        // Special case: if we need to remove the head node
        if (index == 0) {
            head = head.getNext();
            return true;
        }

        NodeHistory current = head;
        int count = 0;
        // Traverse the list to find the node to remove
        while (current != null && count < index - 1) {
            current = current.getNext();
            count++;
        }

        // If current is null or the next node is null, index is out of bounds
        if (current == null || current.getNext() == null) {
            return false;
        }

        // Remove the node at the given index
        current.setNext(current.getNext().getNext());
        return true;
    }

    // Method to display all nodes in the list (for debugging or printing)
    public void displayList() {
        NodeHistory current = head;
        while (current != null) {
            System.out.println(current);  // Print the node data (NodeHistory's toString method)
            current = current.getNext();
        }
    }

    // Method to get the size of the linked list
    public int size() {
        int size = 0;
        NodeHistory current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }
   
     public Object[][] getHistoryData(User user) {
    if (head == null) {
        System.out.println("History is empty for user: " + user.getUserID());
        return new Object[0][0];  // Return an empty array if no history
    }

    List<Object[]> historyData = new ArrayList<>();
    NodeHistory current = head;
    while (current != null) {
        Object[] row = {
            current.getTitle(),
            current.getAuthor(),
            current.getCd(),
            current.getGenre(),
            current.getBrrDate(),
            current.getReturnDate(),
            current.getPrice()
        };
        historyData.add(row);
        current = current.getNext();
    }
    

    // Debugging: Print out the history data
    System.out.println("History data for user " + user.getUserID() + ":");
    for (Object[] row : historyData) {
        System.out.println(Arrays.toString(row));
    }

    return historyData.toArray(new Object[0][0]);
}
    public Object[][] getHistoryData() {
    List<Object[]> data = new ArrayList<>();
    NodeHistory current = head;
    while (current != null) {
        Object[] record = {
            current.getTitle(),
            current.getAuthor(),
            current.getCd(),
            current.getGenre(),
            current.getBrrDate(),
            current.getReturnDate(),
            current.getPrice()
        };
        data.add(record);
        current = current.getNext();
    }

    // Debugging: Print the history data for checking
    if (data.isEmpty()) {
        System.out.println("No data found in history.");
    } else {
        System.out.println("History data retrieved.");
        for (Object[] record : data) {
            System.out.println(Arrays.toString(record));
        }
    }

    return data.toArray(new Object[0][]);  // Convert list to 2D array
}


       public void displayHistory() {
    NodeHistory current = head;  // Assuming 'head' is the first node in the list
    while (current != null) {
        System.out.println("History Record: " + current);
        current = current.getNext();  // Traverse the linked list
    }
}
}

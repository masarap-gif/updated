/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DSA;

/**
 *
 * @author ivan
 */

   
    public class NodeAccounts {
    private String userName, password, eMail, contactNum, role;
    private int memberID;
    public NodeAccounts next;

    public NodeAccounts(String userName, String password, String eMail, String contactNum, int memberID, String role) {
        this.userName = userName;
        this.password = password;
        this.eMail = eMail;
        this.contactNum = contactNum;
        this.memberID = memberID;
        this.role = role;
        this.next = null;
    }

    // Getters
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEMail() {
        return eMail;
    }

    public String getContactNum() {
        return contactNum;
    }

    public int getMemberID() {
        return memberID;
    }

    public String getRole() {
        return role;
    }

    public NodeAccounts getNext() {
        return next;
    }

    // Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setNext(NodeAccounts next) {
        this.next = next;
    }
}



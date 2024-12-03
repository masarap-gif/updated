/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDLIB;

import BACKENDLIB.arryList;
import BACKENDUSER.LLhistory;
import DSA.LinkedListAccounts;
import DSA.LinkedlistBook;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import FRONTENDLIB.RecordPayUI;
import LogSigBackEnd.User;
import javax.swing.*;

/**
 *
 * @author ivan
 */
public class DASHBOARDUI implements ActionListener{
 LinkedlistBook bookList;
 public static   arryList sharedTransac = new arryList();
 public LinkedListAccounts acc;
 public LLhistory history;
 public User user;
 public  RecordPayUI pay = new RecordPayUI(sharedTransac,bookList);
     JFrame dash = new JFrame();
        JPanel pnl1 = new JPanel();
        JPanel pnl2 = new JPanel();
        JButton list = new JButton();
        JButton brrw = new JButton();
        JButton rtn = new JButton();
        JButton payment = new JButton();
        JButton report = new JButton();
        JLabel lbllist = new JLabel();
        JLabel lbllist1 = new JLabel();
        JLabel lbllist2 = new JLabel();
        JLabel lblbrw = new JLabel();
        JLabel lblrtn = new JLabel();
        JLabel lblpay = new JLabel();
        JLabel lblpay1 = new JLabel();
        JLabel lblrep = new JLabel();
        JLabel lblrep1 = new JLabel();
        JLabel greet = new JLabel();
        JLabel w = new JLabel();
         JLabel a = new JLabel();
          JLabel d = new JLabel();
            JButton exit = new JButton();
            
         
            
            
        // LinkedList storage = new LinkedList();
    
   public DASHBOARDUI( LinkedlistBook bookList,LinkedListAccounts acc, LLhistory hisotry)
    {   
        this.acc = acc;
        this.history = hisotry;
        this.bookList = bookList;
         pnl2.setLayout(null);
        pnl2.setBackground(new Color(0xD9D9D9));
        pnl2.setBounds(0, 0, 1530, 86);
       
        pnl1.setLayout(null);
        pnl1.setBackground(new Color(0x6F1D1B));
        pnl1.setBounds(-7, 86, 1537, 875);
        
                    list.setLayout(null);
                  list.setBounds(363, 332, 217, 380);
                  list.setBackground(new Color(0xBB9457));
                  
                

                       lbllist.setText("View");
                      lbllist.setBounds(10, 240 , 104, 58);
                  lbllist.setForeground(Color.white);
                  lbllist.setFont(new Font("Bebas Neue",Font.BOLD,40));

                  lbllist1.setText("List Of");
                  lbllist1.setBounds(10, 290 , 203, 48);
                  lbllist1.setForeground(Color.white);
                  lbllist1.setFont(new Font("Bebas Neue",Font.BOLD,40));

                  lbllist2.setText("Books");
                  lbllist2.setBounds(10, 332 , 203, 48);
                  lbllist2.setForeground(Color.white);
                  lbllist2.setFont(new Font("Bebas Neue",Font.BOLD,40));

                  list.add(lbllist);
                  list.add(lbllist1);
                  list.add(lbllist2);
                  list.addActionListener(e -> new VIEWLISTUI());

                   brrw.setBounds(611, 332, 217, 198);
                   brrw.setLayout(null);
                   brrw.setBackground(new Color(0xBB9457));

                      lblbrw.setText("Borrowing");
                      lblbrw.setForeground(Color.white);
                      lblbrw.setFont(new Font("Bebas Neue",Font.BOLD,30));
                      lblbrw.setBounds(35, 150, 203, 48);
                      

                      brrw.add(lblbrw);
                     brrw.addActionListener(e -> new BORROWINGUI(bookList,pay,acc,user));

                   payment.setBounds(611, 573 , 217, 139);
                   payment.setLayout(null);
                   payment.setBackground(new Color(0xBB9457));
                      lblpay.setText("Record");
                      lblpay.setForeground(Color.white);
                      lblpay.setFont(new Font("Bebas Neue",Font.BOLD,40));
                      lblpay.setBounds(37, 30, 203, 48);

                       lblpay1.setText("Payment");
                      lblpay1.setForeground(Color.white);
                      lblpay1.setFont(new Font("Bebas Neue",Font.PLAIN,30));
                      lblpay1.setBounds(47, 70, 203, 48);


                      payment.add(lblpay1);
                      payment.add(lblpay);
                      payment.addActionListener(e -> {
                          new RecordPayUI(sharedTransac,bookList).design();
                          //dispose();
                      });
                     


                   rtn.setBounds(875, 332, 217, 139);
                   rtn.setLayout(null);
                   rtn.setBackground(new Color(0xBB9457));
                        lblrtn.setText("Returning");
                      lblrtn.setForeground(Color.white);
                      lblrtn.setFont(new Font("Bebas Neue",Font.BOLD,30));
                      lblrtn.setBounds(35, 90, 203, 48);

                      rtn.add(lblrtn);
                       rtn.addActionListener(e -> {
                          new ReturnUI(sharedTransac,pay,bookList).initUI();
                          
                          //dispose();
                      });


                   report.setBounds(875, 522, 217, 193);
                   report.setLayout(null);
                   report.setBackground(new Color(0xBB9457));
                                  lblrep.setText("Account");
                      lblrep.setForeground(Color.white);
                      lblrep.setFont(new Font("Bebas Neue",Font.BOLD,45));
                      lblrep.setBounds(20, 140, 203, 48);  

                       


                      report.add(lblrep);
                       report.addActionListener(e -> {
                         // new GenerateReport();
                         //dispose();
                      });
                
                    
                      
                      a.setText("Pangalan mo");
                      a.setFont(new Font("Bebas Neue",Font.BOLD, 48));
                      a.setBounds(116, 20, 315, 60);
                      
                      exit.setBounds(1259, 15, 187, 56);
                      exit.setBackground(new Color(0xBB9457));
                      exit.setLayout(null);
                      
                      d.setText("Exit");
                      d.setBounds(55, 6, 150, 55);
                     d.setFont(new Font("Bebas Neue",Font.BOLD, 40));
                     d.setForeground(Color.white);
                      
                      exit.add(d);
                      exit.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e) {
                dash. dispose();
             }
                      }
                      );
                      
                      pnl2.add(exit);
                      pnl2.add(a);
                      
                pnl1.add(list);
                pnl1.add(brrw);
                pnl1.add(payment);
                pnl1.add(rtn);
                pnl1.add(report);
               dash.add(pnl1);
               dash.add(pnl2);

               dash.setSize(1758, 1020);
               dash.setResizable(false);
               dash.setLocationRelativeTo(null);
               dash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               dash.setVisible(true);
               dash.setLayout(null);
               dash.setTitle("DashBoard");
           }
   

       
      public static void main(String[] args) {
        //new DASHBOARDUI(); // Start the application
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


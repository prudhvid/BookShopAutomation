/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import gui.AuthorSuggest;
import gui.BookSuggest;
import gui.manager.statistics;
import gui.showbookintable;
import gui.customer.cart;
import gui.manager.order;
import gui.login;
import gui.employeedetails;
import gui.clerk.clerkpanel;
import gui.manager.ShowRequest;
import gui.manager.booksbelowthreshold;
import gui.bookdetails;
import gui.Request;
import gui.IncompleteBooks;
import gui.customer.CustomerRegister;
import gui.employee.CompleteBooks;
import db.*;
import dbfunctions.*;
import feature.*;
import feature.extrafunctions;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableModel;
import test.*;
/**
 *
 * @author sumit
 */
public class home extends javax.swing.JFrame {

    public static home h;
    /**
     * Creates new form home
     */
    public home() {
        
        initComponents();
        Manager manager=new Manager();
        setTitle("Bookshop Automation Software");
        
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        
        scrollpanecat.setViewportView(categorytable);
        scrollpanebooks.setViewportView(new JPanel());
        addtrendingbooks();
        
        showrequests.setVisible(false);
        orders.setVisible(false);
        receiveorders.setVisible(false);
        stats.setVisible(false);
        home.setVisible(false);
        editbook.setVisible(false);

        comboboxtitle.setEditable(true);
        comboboxauthor.setEditable(true);
        AuthorSuggest as=new AuthorSuggest(comboboxauthor);
        comboboxauthor.setModel(as);
        comboboxauthor.addItemListener(as);
        categorytable.setRowHeight(40);
        categorytable.setAlignmentY(CENTER_ALIGNMENT);
        
        BookSuggest bsm=new BookSuggest(comboboxtitle);
        comboboxtitle.setModel(bsm);
        comboboxtitle.addItemListener(bsm);
        
        final java.util.List<Bisac> categorylist = Bisacf.getList();
        int i=0;
        TableModel model = categorytable.getModel();
        for(Bisac abc : categorylist)
        {
            model.setValueAt(abc.getSubject(), i, 0);
            i++;
        }
        
        categorytable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    
                    // do some action if appropriate column
                    Bisac obj = new Bisac();
                    obj = categorylist.get(row);
                    java.util.List<Book> categorybooks = extrafunctions.getcategoryBooks(obj);
                    JPanel categorypanel = new JPanel();
                    categorypanel.setLayout(new GridLayout(0, 1));
                    for(Book book : categorybooks)
                    {
                        showbookintable newbook = new showbookintable(book);
                        categorypanel.add(newbook);
                    }
                    scrollpanebooks.setViewportView(categorypanel);
                }
            }
        });
        
        comboboxtitle.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() 
{
    @Override
    public void keyPressed(KeyEvent evt)
    {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        bytitleActionPerformed(null);
    }
});


comboboxauthor.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() 
{
    @Override
    public void keyPressed(KeyEvent evt)
    {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            byauthorActionPerformed(null);
    }
});
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cart = new javax.swing.JButton();
        bytitle = new javax.swing.JButton();
        byauthor = new javax.swing.JButton();
        homebas = new javax.swing.JButton();
        comboboxtitle = new javax.swing.JComboBox();
        comboboxauthor = new javax.swing.JComboBox();
        mainpanel = new javax.swing.JPanel();
        scrollpanebooks = new javax.swing.JScrollPane();
        bookpanel = new javax.swing.JPanel();
        scrollpanecat = new javax.swing.JScrollPane();
        categorytable = new javax.swing.JTable();
        shop = new javax.swing.JButton();
        showrequests = new javax.swing.JButton();
        orders = new javax.swing.JButton();
        receiveorders = new javax.swing.JButton();
        stats = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        home = new javax.swing.JButton();
        editbook = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 0, 153));

        cart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/images/cart.jpg"))); // NOI18N
        cart.setText("cart");
        cart.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        cart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartActionPerformed(evt);
            }
        });

        bytitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/images/search.jpg"))); // NOI18N
        bytitle.setText("search");
        bytitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bytitleActionPerformed(evt);
            }
        });

        byauthor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/images/search.jpg"))); // NOI18N
        byauthor.setText("search");
        byauthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byauthorActionPerformed(evt);
            }
        });

        homebas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/images/logo (2).jpg"))); // NOI18N
        homebas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        homebas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homebasActionPerformed(evt);
            }
        });

        comboboxtitle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboboxauthor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(homebas, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comboboxauthor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(byauthor, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboboxtitle, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bytitle, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(cart, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(homebas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bytitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboboxtitle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(byauthor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(cart, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(comboboxauthor, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))))
                .addGap(30, 30, 30))
        );

        mainpanel.setBackground(new java.awt.Color(204, 204, 204));

        scrollpanebooks.setAutoscrolls(true);

        bookpanel.setLayout(new java.awt.GridLayout(0, 1));
        scrollpanebooks.setViewportView(bookpanel);

        categorytable.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N
        categorytable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {""},
                {""},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "                    CATEGORIES"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollpanecat.setViewportView(categorytable);

        javax.swing.GroupLayout mainpanelLayout = new javax.swing.GroupLayout(mainpanel);
        mainpanel.setLayout(mainpanelLayout);
        mainpanelLayout.setHorizontalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollpanecat, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpanebooks, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainpanelLayout.setVerticalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollpanecat, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addComponent(scrollpanebooks))
                .addContainerGap())
        );

        shop.setText("SHOP ACCESS");
        shop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shopActionPerformed(evt);
            }
        });

        showrequests.setText("SHOW REQUESTS");
        showrequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showrequestsActionPerformed(evt);
            }
        });

        orders.setText("ORDERS");
        orders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersActionPerformed(evt);
            }
        });

        receiveorders.setText("RECEIVE ORDERS");
        receiveorders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiveordersActionPerformed(evt);
            }
        });

        stats.setText("STATISTICS");
        stats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statsActionPerformed(evt);
            }
        });

        jButton1.setText("CLEAR SESSION");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        home.setText("HOME");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        editbook.setText("EDIT BOOK");
        editbook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbookActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(shop, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(showrequests, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(orders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(receiveorders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(home, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editbook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(mainpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(shop, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editbook, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(showrequests, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(orders, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(receiveorders, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stats, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartActionPerformed
        
        String result="In cart \n";
        List<Book> cartBooks=UserSession.getBooksList();
        for (Book book : cartBooks) {

            result+=book.getTitle()+"\n";
        }
        cart usercart = new cart(new JFrame(),true,cartBooks);
        usercart.setVisible(true);
        
    }//GEN-LAST:event_cartActionPerformed

    private void byauthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_byauthorActionPerformed
        // TODO add your handling code here:
        List<Book> books=Library.suggestBooks(comboboxauthor.getEditor().getItem().toString(),3);
        if(books.isEmpty())
        {
            
            if(UserSession.getCustomer()==null)
            {
                Book t=new Book();
                CustomerRegister cr = new CustomerRegister(h,new bookdetails(t, h),1);
                cr.setVisible(true);
                setscrollpanebooks(cr);
            }else{
                Request request = new Request(h,new bookdetails(null, h),1);
                request.setVisible(true);
                setscrollpanebooks(request);
            }
        }else {
            JPanel newpanel = new JPanel();
            newpanel.setLayout(new GridLayout(0, 1));
            for(Book book : books)
            {
                showbookintable newbook = new showbookintable(book);
                newpanel.add(newbook);
            }
            scrollpanebooks.setViewportView(newpanel);
        }
    }//GEN-LAST:event_byauthorActionPerformed

    private void bytitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bytitleActionPerformed
        System.out.println("Searching by title");
        List<Book> books=Library.suggestBooks(comboboxtitle.getEditor().getItem().toString());
        if(books.isEmpty())
        {
              if(UserSession.getCustomer()==null)
            {
                CustomerRegister cr = new CustomerRegister(h,new bookdetails(Library.suggestBooks("").get(0), h),1);
                cr.setVisible(true);
                setscrollpanebooks(cr);
            }else{
                Request request = new Request(h,new bookdetails(Library.suggestBooks("").get(0), h),1);
                request.setVisible(true);
                setscrollpanebooks(request);
            }
        }else {
            JPanel newpanel = new JPanel();
            newpanel.setLayout(new GridLayout(0, 1));
            for(Book book : books)
            {        
                showbookintable newbook = new showbookintable(book);
                newpanel.add(newbook);
            }
            scrollpanebooks.setViewportView(newpanel);
        }
    }//GEN-LAST:event_bytitleActionPerformed

    private void shopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shopActionPerformed
        // TODO add your handling code here:
        
        shopaccess abc = new shopaccess(new javax.swing.JFrame(), true);
        abc.setVisible(true);
    }//GEN-LAST:event_shopActionPerformed

    private void homebasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homebasActionPerformed
        // TODO add your handling code here:
        shop.setEnabled(true);
        scrollpanecat.setViewportView(categorytable);
        addtrendingbooks();
        showrequests.setVisible(false);
        orders.setVisible(false);
        receiveorders.setVisible(false);
        stats.setVisible(false);
        home.setVisible(false);
        editbook.setVisible(false);
    }//GEN-LAST:event_homebasActionPerformed

    private void showrequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showrequestsActionPerformed
        // TODO add your handling code here:
        List<Book> requestedbooks = Library.getAllRequestedBookList();
        JPanel pan = new JPanel();
        pan.setVisible(true);
        pan.setLayout(new GridLayout(0, 1));
        for(Book b:requestedbooks)
        {
            ShowRequest rb = new ShowRequest(b);
            pan.add(rb);
        }
        setscrollpanebooks(pan);
    }//GEN-LAST:event_showrequestsActionPerformed

    private void ordersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersActionPerformed
     List<Orders> orderses=Ordersf.getOrdersToBePlacedList();
     order obj = new order(orderses,1);
        setscrollpanebooks(obj);
     
    }//GEN-LAST:event_ordersActionPerformed

    private void receiveordersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiveordersActionPerformed
        List<Orders> receivedList=Ordersf.getOrdersToBeReceivedList();
        order obj = new order(receivedList,2);
        setscrollpanebooks(obj);
    }//GEN-LAST:event_receiveordersActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        UserSession.clearsession();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
       Employee e = UserSession.getLoggedInEmployee();
       employeedetails ed = new employeedetails(e);
        setscrollpanecategory(ed);
        if(e.getType()==Employeef.CLERK_TYPE)
        {
            addclerkpanel();
        }  
        else if(e.getType()==Employeef.EMPLOYEE_TYPE)
        {
            addincompletebooks();
        }
        else if(e.getType()==Employeef.MANAGER_TYPE)
        {
            addmanagerpanel();
        }
        else if(e.getType()==Employeef.OWNER_TYPE)
        {
            addownerpanel();
        }
    }//GEN-LAST:event_homeActionPerformed

    private void editbookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbookActionPerformed
        CompleteBooks cb = new CompleteBooks(new Book());
        setscrollpanebooks(cb);
    }//GEN-LAST:event_editbookActionPerformed

    private void statsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statsActionPerformed
      
      statistics st=new statistics();
      setscrollpanebooks(st);
        
    }//GEN-LAST:event_statsActionPerformed

    /**
     * @param args the command line arguments
     */
 public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                h = new home();
                h.setVisible(true);
            }
        });
    }

public class shopaccess extends javax.swing.JDialog {

    /**
     * Creates new form shopaccess
     */
    public shopaccess(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        shoppass.requestFocus();
        shoppass.addKeyListener(new KeyAdapter() 
        {
            public void keyPressed(KeyEvent evt)
               {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    accessActionPerformed(null);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        shoppass = new javax.swing.JPasswordField();
        access = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SHOP ACCESS");

        jPanel1.setLayout(null);
        jPanel1.add(shoppass);
        shoppass.setBounds(160, 360, 160, 30);

        access.setText("ACCESS");
        access.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accessActionPerformed(evt);
            }
        });
        jPanel1.add(access);
        access.setBounds(160, 400, 160, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/images/lock.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 479, 457);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>                        

    private void accessActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        
        if("bas".equals(String.valueOf(shoppass.getPassword())))
        {
            this.dispose();
            shop.setEnabled(false);
            login empl = new login(h);
            setscrollpanebooks(empl);
        }
        else
        {
            shoppass.setText(null);
            JOptionPane.showMessageDialog(mainpanel, "IT SEEMS YOU FORGOT THE PASSWORD.\nPLEASE TRY AGAIN.");
        }
        
    }                                      
                
    private javax.swing.JButton access;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField shoppass;
    // End of variables declaration                   
}

public void setscrollpanecategory(JPanel panel)
{
    scrollpanecat.setViewportView(panel);
}

public void setscrollpanebooks(JPanel panel)
{
    scrollpanebooks.setViewportView(panel);
}

public void addtrendingbooks()
{
    
    List<Book> tb = Library.getTrendingBooks();
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(0, 1));
    for(Book book : tb)
    {
        showbookintable newbook = new showbookintable(book);
        panel.add(newbook);
    }
    scrollpanebooks.setViewportView(panel);
}

public void addincompletebooks()
{
    List<Book> books=Library.getIncompleteBooksList();
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(0, 1));
    for(Book b:books)
    {
        IncompleteBooks newbook = new IncompleteBooks(b);
        panel.add(newbook);
    }
    scrollpanebooks.setViewportView(panel);
    home.setVisible(true);
    editbook.setVisible(true);
}

public void addclerkpanel()
{
    home.setVisible(true);
    setscrollpanebooks(new clerkpanel());
}

public void addmanagerpanel()
{
        home.setVisible(true);
        showrequests.setVisible(true);
        orders.setVisible(true);
        receiveorders.setVisible(true);
        stats.setVisible(true);
        
        List<Book> requestedbooks = Library.getAllRequestedBookList();
        JPanel pan = new JPanel();
        pan.setVisible(true);
        pan.setLayout(new GridLayout(0, 1));
        for(Book b:requestedbooks)
        {
            ShowRequest rb = new ShowRequest(b);
            pan.add(rb);
        }
        setscrollpanebooks(pan);
}

public void addownerpanel()
{
    home.setVisible(true);
     List<Book> requestedbooks = Statistics.getBooksBelowThreshold(10);//CHANGE TO BOOKS BELOW THRESHOLD
        JPanel pan = new JPanel();
        pan.setVisible(true);
        pan.setLayout(new GridLayout(0, 1));
        for(Book b:requestedbooks)
        {
            booksbelowthreshold rb = new booksbelowthreshold(b);
            pan.add(rb);
        }
        setscrollpanebooks(pan);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bookpanel;
    private javax.swing.JButton byauthor;
    private javax.swing.JButton bytitle;
    private javax.swing.JButton cart;
    private javax.swing.JTable categorytable;
    private javax.swing.JComboBox comboboxauthor;
    private javax.swing.JComboBox comboboxtitle;
    private javax.swing.JButton editbook;
    private javax.swing.JButton home;
    private javax.swing.JButton homebas;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JButton orders;
    private javax.swing.JButton receiveorders;
    public static javax.swing.JScrollPane scrollpanebooks;
    private javax.swing.JScrollPane scrollpanecat;
    private javax.swing.JButton shop;
    private javax.swing.JButton showrequests;
    private javax.swing.JButton stats;
    // End of variables declaration//GEN-END:variables
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import gui.OutputPrinter;
import gui.customer.CustomerRegister;
import static java.awt.SystemColor.text;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import db.*;
import dbfunctions.*;
import feature.Library;
import feature.extrafunctions;
import javax.swing.JOptionPane;
import main.UserSession;
import main.home;

/**
 *
 * @author sumit
 */
public class bookdetails extends javax.swing.JPanel {
    private String Title,Authors;
    Book currentBook;
    home h;
    /**
     * Creates new form bookdetails
     */
    public bookdetails(Book book1,home hom) {
        
        initComponents();
        h=hom;
//        requestinc.setEnabled(false);
        currentBook=book1;
        if(currentBook.getCopiesPresent()==0)
            requestinc.setEnabled(true);
        Library.viewBook(book1);

        String string="Sorry!!   No Description Avialiable. ";
        if(currentBook.getDescription()!=null&&!"".equals(currentBook.getDescription()))
             string=currentBook.getDescription();
        
        String labelText = String.format("<html><div WIDTH=%d>%s</div><html>", 800, string);
        description.setText(labelText);
        titledetails.setText(book1.getTitle());
        publisher.setText(book1.getPublisher().getName());
        price.setText("Rs. "+book1.getSellprice()+" /-");
        coopies.setText(currentBook.getCopiesPresent()+"  copies available");
        authordetail.setText(Bookf.getAuthors(book1).get(0).getName());
        rack.setText("On rack "+extrafunctions.getracknumber(book1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        addtocart = new javax.swing.JButton();
        requestinc = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        titledetails = new javax.swing.JLabel();
        authordetail = new javax.swing.JLabel();
        coopies = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        rack = new javax.swing.JLabel();
        publisher = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/images/cover.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        addtocart.setText("ADD TO CART");
        addtocart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addtocartActionPerformed(evt);
            }
        });

        requestinc.setText("REQUEST FOR INCREMENT");
        requestinc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestincActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Description :");

        description.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        description.setText("<html></html>");
        description.setToolTipText("");
        description.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        description.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        titledetails.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        titledetails.setText("sumit");
        titledetails.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        authordetail.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        authordetail.setText("author");
        authordetail.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        coopies.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        coopies.setText("jLabel3");

        price.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        price.setText("jLabel3");

        rack.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        rack.setText("jLabel3");

        publisher.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        publisher.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(requestinc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addtocart, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titledetails, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                    .addComponent(price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(coopies, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(authordetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(publisher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(description))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addtocart, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(requestinc, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(80, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titledetails, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(authordetail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(publisher, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rack, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(coopies, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(description))))
        );

        description.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void addtocartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addtocartActionPerformed
        // TODO add your handling code here:
        UserSession.addToCart(currentBook);
        
    }//GEN-LAST:event_addtocartActionPerformed

    private void requestincActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestincActionPerformed
        // TODO add your handling code here:
        if(UserSession.getCustomer()==null)
        {
            CustomerRegister cr = new CustomerRegister(h,this,0);
            cr.setVisible(true);
            h.setscrollpanebooks(cr);
        }else{
            Request request = new Request(h,this,0);
            request.setVisible(true);
            h.setscrollpanebooks(request);
        }
    }//GEN-LAST:event_requestincActionPerformed

        private void printToPrinter()
{
    String printData = description.getText() ;
    PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintable(new OutputPrinter(printData));
    boolean doPrint = job.printDialog();
    if (doPrint)
    { 
        try 
        {
            job.print();
        }
        catch (PrinterException e)
        {
            // Print job did not complete.
        }
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addtocart;
    private javax.swing.JLabel authordetail;
    private javax.swing.JLabel coopies;
    private javax.swing.JLabel description;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel price;
    private javax.swing.JLabel publisher;
    private javax.swing.JLabel rack;
    private javax.swing.JButton requestinc;
    private javax.swing.JLabel titledetails;
    // End of variables declaration//GEN-END:variables
}

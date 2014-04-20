/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;

/**
 *
 * @author Prudhvi
 */
public class Manager extends javax.swing.JFrame {

    public static EntityManagerFactory emf;
    public static EntityManager em;

    
    public Manager() {
        initComponents();
        
        emf=Persistence.createEntityManagerFactory("mainPU");
        em=emf.createEntityManager();
        
        
        System.out.println("good");
       
        this.setDefaultCloseOperation(Manager.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
                {
                    System.out.println("closed");
                    if(em.getTransaction().isActive())
                        em.getTransaction().commit();
                    
                    em.close();
                    emf.close();
                    exit();
                }
        });
        
    }
    public void exit()
    {
        this.dispose();
    }
    public static void Activate() {    
        if(!em.getTransaction().isActive())
            em.getTransaction().begin();
    }
    
    public  static boolean commit()
    {   
        Activate();
        try {
            em.flush();
            em.getTransaction().commit();
            
        } catch (Exception e) {
             e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean persist(Object x)
    {
        Activate();
        try {
            em.persist(x);
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean remove(Object x)
    {
        Activate();
        try {
            em.remove(x);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public static void clear()
    {
        Activate();
        em.clear();
    }
    public static boolean refresh(Object x) {
        Activate();
        try {
            Manager.em.refresh(x);
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean commit(Object x){
        Activate();
        if( persist(x)&&commit())
        {
//            refresh(x);
            return true;
        }
        return false;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

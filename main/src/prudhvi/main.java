/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prudhvi;

import db.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import db.*;
/**
 *
 * @author Prudhvi
 */
public class main {
   public static void main(String args[])
   {
       try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
//       EntityManagerFactory emf=Persistence.createEntityManagerFactory("mainPU");
//       EntityManager em=emf.createEntityManager();
//       em.getTransaction().begin();
       NewJFrame f=new NewJFrame();
       f.setVisible(true);
       f.pack();
//       Tab x=new Tab(5);
//       em.persist(x);
       
       
//       Author x=new Author();
//       x.setName("Dpp");
//       if(em.createNamedQuery("Author.findByName").setParameter("name", x.getName()).getResultList().size()>0)
//           System.out.println("true");
//       em.getTransaction().commit();
//          
//          
//          
////       em1.getTransaction().commit();
//       em.close();
//       emf.close();
       
      Manager x=new Manager();
      Manager.Activate();
      Author a=new Author("Test");
      
      Manager.persist(a);
      Manager.commit();
      x.exit();
   }
}

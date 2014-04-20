/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package feature;

import db.*;
import dbfunctions.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sumit
 */
public class Statistics {
   public static List<Sales> getAllSaleswithinPeriod(Date d1,Date d2)
   {
       List<Sales> list=new LinkedList<>();
       list.addAll(Manager.em.createQuery("SELECT s FROM Sales s WHERE s.date>=:d1 AND s.date<=:d2").setParameter("d1", d1)
               .setParameter("d2", d2).getResultList());
       return list;
   }
   public static List<Book> getBooksBelowThreshold(int threshold)
   {
       return Manager.em.createQuery("SELECT b FROM Book b WHERE b.copiesPresent<:thres").setParameter("thres", threshold)
               .getResultList();
   }
   public static List<Object[]> getSoldBooksInDescendingOrder(Date d1,Date d2)
   {
       
       d1=new Date();d2=new Date();
       
       List<Object[]> queryList=Manager.em.createQuery("SELECT s.book.id,SUM(s.copies) FROM Sales s WHERE s.date>=:d1 AND s.date<=:d2  GROUP BY s.book.id").
               setParameter("d1", d1).
               setParameter("d2", d2)
               .getResultList();
       List<Object[]> returnList = new LinkedList<>();
       
       for (Object[] queryObjects : queryList) {
           Book b=Manager.em.find(Book.class, (Integer)queryObjects[0]);
           Object [] returnObjects=new Object[3];
           returnObjects[0]=b;
           returnObjects[1]=queryObjects[1];
           
           
           List<Invoice> invoices=Manager.em.createQuery("SELECT i FROM Invoice i WHERE i.book=:book").setParameter("book", b)
                   .getResultList();
           
           float orderCostperOrder=0;
           for (Invoice invoice : invoices) {
               orderCostperOrder+=invoice.getCopies()*b.getBuyingprice();
           }
           if(!invoices.isEmpty())
               orderCostperOrder/=invoices.size();
           else
               orderCostperOrder=b.getBuyingprice();
           
           Long demand=(Long) returnObjects[1];
           float holdingCostPerUnit=b.getBuyingprice()/50;
           float inventoryLevel=(float) Math.sqrt(2*orderCostperOrder*demand/holdingCostPerUnit);
           returnObjects[2]=inventoryLevel; 
           returnList.add(returnObjects);
       }
       
       
       
       return returnList;
   }
   
   
   public static String getAllDetailsOfABook(Book book)
   {
       String statString="";
       statString+="TOTAL PURCHASES FOR THIS BOOK\n";
           List<Invoice> invoices=Manager.em.createQuery("SELECT i FROM Invoice i WHERE i.book=:book").setParameter("book", book)
                   .getResultList();
           statString+="COPIES\t\tPRICE\n";
           for (Invoice invoice : invoices) {
           statString+=""+invoice.getCopies()+"\t\t"+invoice.getBook().getBuyingprice()+"\n";
       }
       return statString;
   }
    public static void main(String[] args) {
        Manager em=new Manager(); 
//        System.out.println(getAllDetailsOfABook(Manager.em.find(Book.class, 64)));
        
        List<Object[]> objectsList=getSoldBooksInDescendingOrder(null, null);
        for (Object[] objects : objectsList) {
            System.out.println(objects[0]);
            System.out.println(objects[1]);
            System.out.println(objects[2]);
        }
        
        
    }
}

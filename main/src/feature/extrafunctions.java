/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package feature;

import feature.*;
import db.*;
import dbfunctions.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author sumit
 */
public class extrafunctions {

    public extrafunctions() {
    }
    
    public static void main(String[] args)
    {
        
    }
    
    public static List<Book> getcategoryBooks(Bisac b)
    {
        return Manager.em.createQuery("SELECT b FROM Book b WHERE b.bisac=:bisac").setParameter("bisac", b).getResultList();
    }
    
    public static int getracknumber (Book b)
    {
        char first;
        Bisac bs = b.getBisac();
        first =  bs.getSubject().charAt(0);
        Racksection r;
        try {
            return (Integer)Manager.em.createQuery("SELECT r.rack.number FROM Racksection r where r.bisac=:bisac AND r.rack.intitial<=:first AND r.rack.final1>=:first")
                .setParameter("bisac", bs).setParameter("first", first).getSingleResult();
        } catch (Exception e) {
            return -Integer.MAX_VALUE;
        }
        
    }
    
}

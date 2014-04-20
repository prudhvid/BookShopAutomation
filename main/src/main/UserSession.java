

package main;
import db.*;
import dbfunctions.Bookf;
import dbfunctions.Customerf;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;


public class UserSession extends Customerf{
    private static Customer customer;
    private static List<Book> cart=new LinkedList<>();
    private static Employee employee=null;
    public static void main(String[] args) {
        
    }
    public static Customer getCustomer()
    {
        return customer;
    }
    public static Customer giveCustomerDetails(String name,String mail,String phone)
    {
        customer=new Customer();
        customer.setName(name);
        customer.setMail(mail);
        customer.setPhone(phone);
        customer=Customerf.addCustomer(customer);
        return customer;
    }
    
    public static void clearsession()
    {
        customer=null;
        cart.clear();
    }
    
    public static void addToCart(Book b)
    {
        if(cart.contains(b))
        {
            JOptionPane.showMessageDialog(null, "Already in cart!");
            return;
        }
        b=Bookf.addBook(b);
        cart.add(b);
        JOptionPane.showMessageDialog(null, "Succesfully added "+b.getTitle()+" to cart");
    }
    public static double getCartPrice(){
        double cost=0;
        for (Book book : cart) {
            cost+=book.getSellprice();
        }
        return cost;
    }
    
    public static int getNumberOfBooks()
    {
        return cart.size();
    }
    public static void clearCart()
    {
        cart.clear();
    }
    public static List<Book> getBooksList(){
        return cart;
    }
    public static void storeLoggedInEmployee(Employee e)
    {
        employee=e;
    }
    public static Employee getLoggedInEmployee()
    {
        if(employee!=null)
            return employee;
        return null;
    }
    
}

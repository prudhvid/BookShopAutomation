

package dbfunctions;

import db.*;
import java.util.Date;
import javax.swing.JOptionPane;
import main.UserSession;
import org.eclipse.persistence.sessions.SessionProfiler;




public class Reservesf {
    
//    public static Reserves getReserves(Book b)
//    {
//        Book x=Bookf.search(b);
//        if(x!=null)
//        {
//            
//        }
//    }
    final public static char INCREMENT_TYPE='C';
    final public static char REQUEST_TYPE='R';
    final public static char ARRIVED_TYPE='A';
    final public static char ACCEPTED_TYPE='T';
    public static Character GIVEN_TYPE='G';
    public static Reserves addReserve(Book book,Customer c,int copies)//assuming book is already there in db and refernce of it is given 
    {
        copies=1;
        Book indbBook=Bookf.search(book);
        if(indbBook!=null&&indbBook.getState()==Bookf.COMPLETE_BOOK_STATE)
        {
            if(indbBook.getCopiesPresent()!=0)
            {
                JOptionPane.showMessageDialog(null, "Sorry already enough copies present");
                return null;
            }
            System.out.println("inDB");
            indbBook.setState(Bookf.INCREMENT_STATE);
            Reserves rs=new Reserves();
            rs.setBook(indbBook);
            rs.setCopies(1);
            rs.setDate(new Date());
            rs.setType(INCREMENT_TYPE);
            rs.setCustomer(c);
            if( Manager.commit(indbBook)&&Manager.commit(rs))
                return rs;
            return null;  
        }
        if(indbBook==null)
            book=Bookf.addBook(book);
        else
            book=Bookf.search(book);
        System.out.println(book);
        System.out.println("in db not");
        Reserves rs=new Reserves();
        rs.setBook(book);
        rs.setCopies(1);
        rs.setDate(new Date());
        rs.setType(REQUEST_TYPE);
        rs.setCustomer(c);
        book.setState(Bookf.REQUEST_STATE);
        
        if(Manager.commit(book)&&Manager.commit(rs))
            return rs;
        
        return null;
    }
    public static void main(String[] args) {
        Manager manager=new Manager();
        Book b=new Book("Devil home");
       Customer customer= UserSession.giveCustomerDetails("Prudhvi", "dharmana.prudhvi@gmial.co", "87236871263");
        
       if(addReserve(b, customer, 1)==null)
            System.out.println(false);
        
    }
       
}

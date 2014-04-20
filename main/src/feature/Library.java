

package feature;

import db.*;
import dbfunctions.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import main.UserSession;

public class Library {

    Library lib=null;
    private Library(){

    }
    public static void main(String[] args) {
        Manager manager=new Manager();
        
//        List<Book> books=suggestBooks("harry", "rowling");
//        for (Book book : books) {
//            System.out.println(book.getTitle());
//        }
       List<Reserves> list=getCustomerReservedBooks();
        for (Reserves book : list) {
            System.out.println(book);
        }
        
        
    }
    public Library createLibrary()
    {
        if(lib==null)
        {
            lib= new Library();
            return lib;
        }
        return lib;
    }


    public static List<Book> suggestBooks(String par_title)
    {
        return Bookf.getSuggestions(par_title);
    }
    
    public static List<Book> suggestBooks(String title,List<Author> authors)
    {
        List<Book> list=new ArrayList<Book>();
        for (Author author : authors) {
            boolean addAll = list.addAll(Manager.em.createQuery("SELECT ba.book FROM Bookauthor ba WHERE ba.book.title LIKE :title AND ba.author LIKE :par2")
                    .setParameter("title", title+"%").setParameter("par2", author.getName()+"%").getResultList());
        }

        return list;
    }
    public static List<Book> suggestBooks(List<Author> authors){

        List<Book> list=new ArrayList<Book>();
        for (Author author : authors) {
            list.addAll(Manager.em.createQuery("SELECT ba.book FROM Bookauthor ba WHERE ba.author LIKE :par1 Order By ba.book.views DESC").
                    setParameter("par1", author.getName()+"%").setMaxResults(10).getResultList() );
        }
        return list;
    }
    public static List<Book> suggestBooks(String authorString,int forauthorsDeafault){

        List<Book> list=new ArrayList<Book>();
        list.addAll(Manager.em.createQuery("SELECT ba.book FROM Bookauthor ba WHERE ba.author.name LIKE :par1 and ba.book.state=:state "
                + " ORDER BY  ba.book.views DESC").setParameter("state", Bookf.COMPLETE_BOOK_STATE).
                    setParameter("par1", "%"+authorString.toUpperCase()+"%").setMaxResults(10).getResultList() );
        return list;
    }
    public static List<Book> suggestBooks(String titleString,String authorString )
    {
        List<Book> list=new ArrayList<>();
        list.addAll(Manager.em.createQuery("SELECT ba.book FROM Bookauthor ba WHERE ba.author.name LIKE :par1 "
                + "and ba.book.state=:state and ba.book.title LIKE :title"
                + " ORDER BY  ba.book.views DESC").setParameter("state", Bookf.COMPLETE_BOOK_STATE).
                    setParameter("title", "%"+titleString.toUpperCase()+"%").
                    setParameter("par1", "%"+authorString.toUpperCase()+"%").setMaxResults(10).getResultList() );
        return list;
    }
    
    
    public static List<Author> suggestAuthors(String par_author){
        return Authorf.getSuggestions(par_author);
    }
    
    
    public static List<Book> getRequestSuggestions(String bookname){
        List<Book> books=Manager.em.createQuery("SELECT rs.book FROM Reserves rs WHERE rs.book.title LIKE :title AND RS.type=:type")
                .setParameter("type", Reservesf.REQUEST_TYPE)
                .setParameter("title", bookname).getResultList();
        return books;
    }

    
    
    
    //increments and requests
    
    public static Book requestBook(Book b,int copies){
        copies=1;
        Customer customer=UserSession.getCustomer();
        Publisherf.addPublisher(b.getPublisher());
        Book book=Bookf.search(b);
        
        if (book==null) {
            book=new Book(b.getTitle());
            book.setPublisher(b.getPublisher());
            book.setState(Bookf.DATA_GIVEN);
            book.setCopiesReserved(1);
            book.setViews(1);
            book=Bookf.addBook(book);
            
            System.out.println("added new Book in Database ");
            Reservesf.addReserve(book,customer,copies);
        }
        else if(book.getState()==Bookf.DATA_GIVEN){
            book.setViews(book.getViews()+1);
            book.setCopiesReserved(book.getCopiesReserved()+copies);
            Manager.commit(book);
            System.out.println("Incremented total Requests for that book");
            Reservesf.addReserve(book,customer,copies);
        }
        else
        {
            book.setViews(book.getViews()+1);
            book.setCopiesReserved(book.getCopiesReserved()+copies);
            Manager.commit(book);
            System.out.println("Incremented total Requests for that complete book");
            Reservesf.addReserve(book, customer, copies);
        }
        return book;
    }
    
    
//    public static Book requestIncrementBook(Book b,int copies){
//       
//        Customer customer=UserSession.getCustomer();
//        if(Manager.em.contains(b)&&b.getCopiesPresent()==0)
//        {
//            b.setViews(b.getViews()+1);
//            b.setCopiesReserved(b.getCopiesReserved()+copies);
//            Manager.persist(b);
//            Reservesf.addReserve(b, customer, copies);
//        }
//        JOptionPane.showMessageDialog(null, "Sorry book already present in available quantity or not in request form");
//        return null;
//        
//    }
    
    public static Book storeCompleteBook(String isbn,Book b,Publisher publisher,Bisac bisac,List<Author> AuthStrings)
    {
        publisher=Publisherf.addPublisher(publisher);
        bisac=Bisacf.getBisac(bisac.getSubject());
        List<Book> books=Manager.em.createNamedQuery("Book.findByIsbn").setParameter("isbn", isbn).getResultList();
        if(Bookf.checkISBN(isbn))
        {
            if(books.isEmpty())
            {
                System.out.println("adding book");
                b.setPublisher(publisher);
                b.setBisac(bisac);
                b.setState(Bookf.COMPLETE_BOOK_STATE);
                b.setIsbn(isbn);
                b=Bookf.addBook(b, AuthStrings);
                b=Bookf.addBook(b);
                JOptionPane.showMessageDialog(null, "Book added !!");
                return b;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please check your ISBN \n ISBN already given!!");
                return null;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Wrong ISBN");
        }
        return null;
    }
    
    
    
    
    
    
    public static Book viewBook(Book book){
        book.setViews(book.getViews()+1);
        Manager.commit(book);
        return book;
    }
    
 public static boolean sellBooks(List<Sales> booksSalesList)
    {
        
        for (Sales sales : booksSalesList) {
            Book b=sales.getBook();
            b.setCopiesPresent(b.getCopiesPresent()-sales.getCopies());
            b.setCopiesSold(b.getCopiesSold()+sales.getCopies());
            float sellprice=sales.getBook().getSellprice()*sales.getCopies();
            float profit=sellprice-sales.getBook().getBuyingprice()*sales.getCopies();
            sales.setPrice(sellprice);
            sales.setProfit(profit);
            sales.setDate(new Date());
            Manager.persist(sales);
            Manager.persist(b);
        }
        if(Manager.commit())
            return true;
        return false; 
    } 
    
    public static Book getBookByIsbn(String isbn)
    {
        List<Book> books=Manager.em.createNamedQuery("Book.findByIsbn").setParameter("isbn", isbn).getResultList();
        if(books.isEmpty())
            return null;
        return books.get(0);
    }

        public static List<Book> getTrendingBooks()
    {
        List<Book> list =new LinkedList<>();
        list=Manager.em.createQuery("SELECT b FROM Book b WHERE b.state=:state ORDER BY b.views DESC").
                setParameter("state", Bookf.COMPLETE_BOOK_STATE).
                setMaxResults(20).getResultList();
        return list;
    }
    
    public static List<Book> getIncompleteBooks()
    {
        try {
            return Manager.em.createQuery("SELECT b FROM Book b WHERE b.state=:state ORDER BY b.title").
                    setParameter("state", Bookf.ORDER_ARRIVED_STATE)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static List<Book> getAllRequestedBookList()
    {
        try {
//            return Manager.em.createQuery("SELECT rs.book FROM Reserves rs WHERE rs.type=:type1 OR rs.type=:type2 ORDER BY rs.date").
//                    setParameter("type1", Reservesf.INCREMENT_TYPE).setParameter("type2", Reservesf.REQUEST_TYPE).
//                    getResultList();
            return Manager.em.createQuery("SELECT b FROM Book b WHERE b.state=:type1 OR b.state=:type2 and b.copiesReserved>0 ORDER BY b.copiesReserved DESC")
                    .setParameter("type1", Bookf.INCREMENT_STATE).setParameter("type2", Bookf.REQUEST_STATE).
                    getResultList();
        } catch (Exception e) {
        }
        return null;
    }
    
    public static List<Book> getIncompleteBooksList(){
        return Manager.em.createQuery("SELECT b from Book b WHERE b.state=:state ORDER BY b.copiesReserved")
                .setParameter("state", Bookf.ORDER_ARRIVED_STATE).getResultList();
    }
    
    public static void deleteBookRequests(Book book){
        List<Reserves> reserveses=Manager.em.createQuery("SELECT rs FROM Reserves rs WHERE rs.book=:book and RS.type=:type1 OR RS.type=:type2").
                setParameter("type1", Reservesf.INCREMENT_TYPE).setParameter("type2", Reservesf.REQUEST_TYPE).
                setParameter("book", book).getResultList();
        for (Reserves reserves : reserveses) {
            Manager.em.remove(reserves);
            Manager.commit();
        }
        book.setCopiesReserved(0);
        Manager.commit(book);
        JOptionPane.showMessageDialog(null, "Succesfully deleted all requests to this book");
    }
    
    public static List<Reserves> getCustomerReservedBooks()
    {
        return Manager.em.createQuery("SELECT rs FROM Reserves rs WHERE rs.book.requestedCopiesPresent>=1 ORDER BY rs.date DESC")
                .getResultList();
    }
            
    public static void giveReservedBook(Reserves rs)
    {
        rs.setType(Reservesf.GIVEN_TYPE);
        Book b=rs.getBook();
        if(b.getRequestedCopiesPresent()>=1)
            b.setRequestedCopiesPresent(b.getRequestedCopiesPresent()-1);
        Manager.commit(b);
        Manager.commit(rs);
    }
    
}

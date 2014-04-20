

package dbfunctions;
import db.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import main.constraint;


public class Bookf extends Book{
    public static final char ORDER_PLACED_STATE='O';
    public static final char DATA_GIVEN='D';
    public static final char INCREMENT_STATE='I';
    public static final char COMPLETE_BOOK_STATE='C';
    public static final char REQUEST_STATE='R';
    public static final char ORDER_ARRIVED_STATE='A';
    public static final char ADDED_TO_ORDER_STATE='S';
    
    
    public static void copyBtoA(Book a, Book b) {
        System.out.println("copying");
        
        if(b.getIsbn()!=null||b.getIsbn()!="")
            a.setIsbn(b.getIsbn());
        
        a.setEdition(b.getEdition());
        
        if(b.getCopiesBought()!=0)
            a.setCopiesBought(b.getCopiesBought());
        
        a.setCopiesPresent(b.getCopiesPresent());
        a.setCopiesReserved(b.getCopiesReserved());
        a.setCopiesSold(b.getCopiesSold());
        a.setBuyingprice(b.getBuyingprice());
        
        if(b.getBisac()!=null)
            a.setBisac(b.getBisac());
        
        if(b.getPublisher()!=null)
            a.setPublisher(b.getPublisher());
        
        if(a.getState()!=COMPLETE_BOOK_STATE)
            a.setState(b.getState());
        
        a.setSellprice(b.getSellprice());
        
        if(b.getViews()>a.getViews())
            a.setViews(b.getViews());
        if(!"".equals(b.getDescription()))
            a.setDescription(b.getDescription());
    }

    public Bookf(String a) {
        super(a);
    }
    
    
    public static Book search(String a,Publisher publisher)
    {
        
        List<Book> list=(List<Book>)Manager.em.createQuery("SELECT b from Book b WHERE b.title=:title AND b.publisher.name=:pub").
                setParameter("title", a).setParameter("pub", publisher.getName()).getResultList();
        if(list.isEmpty())
            return null;
        return list.get(0);
        
    }
    
    public static Book search(Book b){
        if(b.getTitle()==null||b.getPublisher()==null)
        {
            return null;
        }
        
        Book x=search(b.getTitle(),b.getPublisher());
        return x;
    }
    
    
    public static Book addBook(String titleString){
       
        Book b=new Book(titleString);
        return addBook(b);
    }
    public static Book addBook(String titleString,Publisher p){
        Book x=new Book(titleString);
        x.setPublisher(p);
        return addBook(x);
    }
    public static Book addBook(Book book,List<String> authorString,int index){
        List<Author> list=Authorf.getAuthorList(authorString);
        Book b=addBook(book, list);
        return b;
    }
    public static Book addBook(Book book,List<Author> list){
        Book b=addBook(book);
        if(b!=null){
            for (Author author : list) {
                author=Authorf.addAuthor(author);
                Bookauthor ba=new Bookauthor();
                ba.setBook(b);
                ba.setAuthor(author);
                if(Manager.em.createQuery("SELECT ba FROM Bookauthor ba WHERE ba.author=:author AND ba.book=:book")
                        .setParameter("author", author).setParameter("book", b).getResultList().size()==0)
                    Manager.persist(ba);
            }
            if(Manager.commit())
                return b;  
        }
        return null;
    }

    public static Book addBook(Book b){
        
        if(Manager.em.contains(b))
            return b;
        Book x=Bookf.search(b.getTitle(), b.getPublisher());
       
        if(x!=null)
        {
            System.out.println("B's copies="+b.getCopiesReserved());
            System.out.println("Initial copies="+x.getCopiesReserved());
            copyBtoA(x,b);
            System.out.println("FInal copies="+x.getCopiesReserved());
            Manager.commit(x);
            return x;
        }
        b.setPublisher(Publisherf.addPublisher(b.getPublisher()));
        if(Manager.commit(b))
            return b;
        return null;
    }
    
    
   
    public static boolean checkISBN(String IsbnString){
        System.out.println(IsbnString);
        if(IsbnString.length()==10)
            return true;
        if(IsbnString==null)
            return false;
        if(IsbnString.length()!=13)
            return false;
        
        int odd,even;
        even=odd = 0;
        
        
        for (int i = 0; i < IsbnString.length()-1; i++) {
            if(i%2==0)
                odd+=IsbnString.charAt(i)-'0';
            else
                even+=IsbnString.charAt(i)-'0';
        }
//        System.out.println("even="+even+"\nodd="+odd+"\ntotal="+(even*3+odd));
        int res=(even*3+odd)%10;
        res=10-res;
        return res==IsbnString.charAt(12)-'0';
    }
    public static void main(String[] args) {
//        System.out.println(checkISBN("9780306406157"));
        Manager m=new Manager();
//        Book a=new Book("Love");
//        List<String> autStrings = new LinkedList<>();
//        autStrings.add("JK Rowling");
//        Publisher publisher=new Publisher();
//        publisher.setName("PHI");
//        a.setPublisher(publisher);
//        Book b=new Book("Devil Home");
//        Book c=new Book("House of cards");
//        addBook(b,autStrings,1);
//        addBook(a);
//        addBook(c);
        Book x=new Book("Harry potter and the chamber of secrets");
        x=addBook(x);
        List<Author> list=getAuthors(x);
        for (Author author : list) {
            System.out.println(author.getName());
        }
        
    }
    
    
    public static List<Book> getSuggestions(String bookString){
        List<Book> list=Manager.em.
                createQuery("SELECT DISTINCT b FROM Book b WHERE b.title LIKE :title AND (b.state=:state OR b.state=:state2) ").
                setParameter("title", "%"+bookString.toUpperCase()+"%").setParameter("state", Bookf.COMPLETE_BOOK_STATE).
                setParameter("state2",INCREMENT_STATE).
                setMaxResults(10).getResultList(); 
        return list;
        
    }
    
    public static List<Author> getAuthors(Book b){
        Book b1=search(b);
        List<Author> list=(List<Author>)Manager.em.createQuery("SELECT ba.author FROM Bookauthor ba WHERE BA.book.title=:title "
                + " ORDER BY ba.author.name")
                .setParameter("title", b.getTitle()).getResultList();
        for (int i = 0; i < list.size()-1; i++) {
            if(list.get(i)==list.get(i+1))
            {list.remove(i);i--;}
        }
        return list;
    }
    public static List<Book> getBook(String titString)
    {
        
        List<Book> list=(List<Book>)Manager.em.createQuery("SELECT  b from Book b WHERE b.title=:title and b.publisher!=:pub").
                setParameter("title", titString).setParameter("pub", Publisherf.getEmptyPublisher()).getResultList();
        
        return list;
        
    }
    public static boolean isComplete(Book b)
    {
        
        if(b.getBisac()!=null&&checkISBN(b.getIsbn())&&b.getPublisher()!=null&&b.getSellprice()!=0)
            return true;
        return false;
    }
}

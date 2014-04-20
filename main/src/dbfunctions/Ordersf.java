

package dbfunctions;


import db.*;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;



public class Ordersf {
    public final static char GENERATED_STATE='G';
    public final static char PLACED_STATE='P';
    public final static char RECEIVED_STATE='R';
    public final static char UPDATED_STATE='U';
    
    
    
    public static void main(String[] args) {
        Manager manager=new Manager();
        Publisher publisher=Manager.em.find(Publisher.class, 566);
        System.out.println(publisher);
        recieveOrder(publisher);
    }
    
    public static Invoice addInvoice(Invoice invoice,Publisher publisher)
    {
        publisher=Publisherf.addPublisher(publisher);
        Orders os=getGeneratedOrder(publisher);
        if(os==null){
           JOptionPane.showMessageDialog(null, "Sorry no order generated!!");return null;
        }
        invoice.setOrders(os);
        if(invoice.getBook().getPublisher()!=publisher)
        {
            JOptionPane.showMessageDialog(null, "Sorry that book doesnt belong to the same publisher");
            return null;
        }
        
        invoice.setState(Invoice.ACCEPTED_STATE);
        Book b=invoice.getBook();
        b.setState(Bookf.ADDED_TO_ORDER_STATE);
        Manager.commit(b);
        List<Invoice> initInvoiceList= Manager.em.createQuery("SELECT i FROM Invoice i WHERE i.book=:book").setParameter("book", b).getResultList();
        
        if(!initInvoiceList.isEmpty())
        {
            Invoice initInvoice=initInvoiceList.get(0);
            if(initInvoice.getBook()==b)
            {
               initInvoice.setCopies(invoice.getCopies()+initInvoice.getCopies());
               initInvoice.setPrice(getInvoicePrice(initInvoice));
               Manager.commit(initInvoice);
               return initInvoice;
            }
            
        }
        else
        {
            invoice.setPrice(getInvoicePrice(invoice));
            Manager.commit(invoice);
            return invoice;
        }
        return null;
    }
    
    public static void editInvoice()
    {
        
    }
    
    public static Orders placeOrder(Publisher publisher)
    {
        publisher=Publisherf.addPublisher(publisher);
        Orders os=getGeneratedOrder(publisher);
        if(os==null){
            JOptionPane.showMessageDialog(null, "Sorry no order generated or order is already placed!!");return null;
        }
        os.setDateplaced(new Date());
        os.setState(PLACED_STATE);
        
        
        List<Invoice> invoices=getInvoiceList(os);
        for (Invoice invoice : invoices) {
            Book b=invoice.getBook();
            b.setState(Bookf.ORDER_PLACED_STATE);
            Manager.commit(b);
        }
        
        Manager.commit(os);
        System.out.println("order placed");
        return os;
    }
    public static Orders recieveOrder(Publisher p)
    {
        Orders os=getPlacedOrder(p);
        if(os==null){JOptionPane.showMessageDialog(null, "Sorry no orders placed!!");return  null;}
        
        os.setDatereceived(new Date());
        os.setState(RECEIVED_STATE);
        
        
        //making all the state of books to ORDER_ARRIVED STATE
        List<Book> books=Manager.em.createQuery("SELECT i.book FROM Invoice i WHERE i.orders=:orders")
                .setParameter("orders", os).getResultList();
        for (Book book : books) {
            book.setState(Bookf.ORDER_ARRIVED_STATE);
            Manager.commit(book);
        }
                
                
                
        Manager.commit(os);
        System.out.println("Order Received from Publisher"+p.getName());
        updateOrder(p);
        return os;
    }
    
    
    public static Orders updateOrder(Publisher p)
    {
        Orders os=getReceivedOrder(p);
        os.setState(UPDATED_STATE);
        List<Invoice> invoices=getInvoiceList(os);
        for (Invoice invoice : invoices) {
            Book b=invoice.getBook();
            int copies=invoice.getCopies();
            b.setCopiesBought(b.getCopiesBought()+copies);
            b.setState(Bookf.ORDER_ARRIVED_STATE);
            if(Bookf.isComplete(b))
                b.setState(Bookf.COMPLETE_BOOK_STATE);
            if(copies>b.getCopiesReserved()){
                b.setRequestedCopiesPresent(b.getCopiesReserved());
                b.setCopiesPresent(copies-b.getCopiesReserved());
                b.setCopiesReserved(0);
            }
            else
            {
                b.setRequestedCopiesPresent(copies);
                b.setCopiesReserved(b.getCopiesReserved()-copies);
            }
            
            Manager.commit(b);
            
            //sending mail left
            List<Reserves> reserves=Manager.em.createQuery("SELECT rs FROM Reserves rs WHERE rs.book=:book ORDER BY rs.date ASC").
                    setParameter("book", b).getResultList();
            int count=b.getRequestedCopiesPresent();
            for (Reserves reserve : reserves) {
                if(count>0)
                {
                    reserve.setType(Reservesf.ARRIVED_TYPE);
                //Write malining function to customer
                    String mail=reserve.getCustomer().getMail();
                    Manager.commit(reserve);
                    Manager.commit(b);
                    count--;
                }
                else
                    break;
            }
            Manager.commit(b);
            
        }
        Manager.commit(os);
        return os;
    }
    
    public static Orders genrateOrder(Publisher publisher)
    {
        publisher=Publisherf.addPublisher(publisher);
        if(getGeneratedOrder(publisher)!=null)
            return getGeneratedOrder(publisher);
        if(getPlacedOrder(publisher)!=null||getReceivedOrder(publisher)!=null)
        {
            JOptionPane.showMessageDialog(null, "Sorry orders are already intransit for this publisher!\n Please"
                    + "place those orders and then get a new order");
            
            return null;
        }
            
        Orders os=new Orders();
        os.setPublisher(publisher);
        os.setState(GENERATED_STATE);
        System.out.println("order generated for "+publisher.getName());
        if(Manager.commit(os))
            return os; 
        return null;
    }
    
    public static Orders getGeneratedOrder(Publisher p)
    {
        List<Orders> list=Manager.em.createQuery("SELECT o FROM Orders o WHERE o.state=:state AND o.publisher=:publisher").
                setParameter("state", GENERATED_STATE).setParameter("publisher", p).getResultList();
        if(list.size()!=1)
            return null;
        return list.get(0);
    }
     public static Orders getPlacedOrder(Publisher p)
    {
        List<Orders> list=Manager.em.createQuery("SELECT o FROM Orders o WHERE o.state=:state AND o.publisher=:publisher").
                setParameter("state", PLACED_STATE).setParameter("publisher", p).getResultList();
        if(list.size()!=1)
            return null;
        return list.get(0);
    }
    public static Orders getReceivedOrder(Publisher p)
    {
        List<Orders> list=Manager.em.createQuery("SELECT o FROM Orders o WHERE o.state=:state AND o.publisher=:publisher").
                setParameter("state", RECEIVED_STATE).setParameter("publisher", p).getResultList();
        if(list.isEmpty())
            return null;
        return list.get(0);
    }
     private static float getInvoicePrice(Invoice v)
     {
        
         return v.getBook().getBuyingprice()*v.getCopies();
        
     }
     public static List<Invoice> getInvoiceList(Orders os)
     {
         return Manager.em.createQuery("SELECT i FROM Invoice i WHERE i.orders=:orders").setParameter("orders", os)
                 .getResultList();
     }
     public static List<Orders> getOrdersToBePlacedList()
     {
         return Manager.em.createQuery("SELECT os FROM Orders os WHERE os.state=:state").setParameter("state", GENERATED_STATE)
                 .getResultList();
     }
     public static List<Orders> getOrdersToBeReceivedList()
     {
         return Manager.em.createQuery("SELECT os FROM Orders os WHERE os.state=:state").setParameter("state", PLACED_STATE)
                 .getResultList();
     }
     
     
}

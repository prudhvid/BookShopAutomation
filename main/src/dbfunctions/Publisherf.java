

package dbfunctions;

import db.*;
import java.util.List;

public class Publisherf extends Publisher{

    public Publisherf() {
    }
    
    public static void main(String[] args) {
        
    }
    
    private static Publisher search(String a)
    {
        List<Publisher> list=(List<Publisher>)Manager.em.createNamedQuery("Publisher.findByName").setParameter("name", a).getResultList();
        if(list.isEmpty())
            return null;
        return list.get(0); 
    }
    
    public static Publisher addPublisher(String pub,String vendorString){
        Publisher p=new Publisher();
        p.setName(pub);
        p.setVendor(vendorString);
        return addPublisher(p);
        
    }
    
    public static Publisher addPublisher(Publisher pub){
        if(pub==null)
            return null;
        System.out.println("received to add"+pub);
        Publisher t=search(pub.getName());
        if(t!=null)
        {
            System.out.println("already present publisher ");
            t.setVendor(pub.getVendor());
            t.setAddress(pub.getAddress());
            t.setMail(pub.getMail());
            t.setPhone(pub.getPhone());
            Manager.commit(t);
            return t;
        }
        System.out.println("adding new publisher");
        if(Manager.commit(pub))
            return pub; 
        return null;
    }
    
    
    public static Publisher getEmptyPublisher()
    {
        Publisher pub=new Publisher();
        pub.setName("UNKNOWN");
        pub=addPublisher(pub);
        return pub;
    }
    public static boolean isComplete(Publisher publisher)
    {
        return !"".equals(publisher.getName())&&!"".equals(publisher.getVendor())&&!"".equals(publisher.getAddress());
    }
}

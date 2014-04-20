

package dbfunctions;
import db.*;
import java.util.List;


public class Bisacf extends Bisac{

    public Bisacf() {
       
    }
    
    
    public static void main(String[] args) {
        Manager x=new Manager();
         List <Bisac> list=getList();
        for (Bisac bisac : list) {
            System.out.println(bisac.getSubject());
        }
        
    }
    
    
    public static Bisac search(String a)
    {
        List<Bisac> list=(List<Bisac>)Manager.em.createNamedQuery("Bisac.findBySubject").setParameter("subject", a).getResultList();
        if(list.isEmpty())
            return null;
        return list.get(0);
        
    }
    
    private static Bisac addHeading(String heading){
        
        Bisac b=search(heading);
        if(b!=null)
            return b;
        b=new Bisac();
        b.setSubject(heading);
        if(Manager.commit(b))
            return b;
        
        return null;
    }
    public static List<Bisac> getSuggestions(String parname)
    {
        Manager.Activate();
        List<Bisac> list=(List<Bisac>) Manager.em.createQuery("SELECT a FROM Bisac a WHERE a.subject LIKE :x  ORDER BY a.subject").
                setParameter("x", parname+"%").getResultList();
        return list;
    }
    public  static Bisac getBisac(String string){
        return search(string);
    }
    public static List<Bisac> getList()
    {
        List<Bisac> list=(List<Bisac>) Manager.em.createQuery("SELECT a FROM Bisac a ORDER BY a.subject").getResultList();
        
        return list;
        
    }
}

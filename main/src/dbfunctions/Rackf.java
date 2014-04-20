

package dbfunctions;

import db.*;
import java.util.List;

public class Rackf extends Rack{

    public Rackf() {
    }
    
    public static void main(String[] args) {
        Manager manager=new Manager();
        Rack x=new Rack(50);
        addRack(x);
        
        
    }
    
    protected  static void copyBtoA(Rack a,Rack b){
        a.setIntitial(b.getIntitial());
        a.setFinal1(b.getFinal1());
    }
    public static Rack addRack(int num,char i1,char i2 ){
        Rack r=new Rack(num);
        r.setIntitial(i1);
        r.setFinal1(i1);
        return addRack(r);
    }
    
    public static Rack addRack(Rack r)
    {
        
        if(r.getIntitial()==null)r.setIntitial('A');
        if(r.getFinal1()==null )r.setFinal1('Z');
            
        Rack x=Manager.em.find(r.getClass(), r.getNumber());
//        List<Rack> list =Manager.em.createNamedQuery("Rack.findByNumber").setParameter("number", r.getNumber())
//                .getResultList();
//        if(list.isEmpty())
//        {
//            Manager.commit(r);
//        }
        
        if(x!=null)
            copyBtoA(x, r);
        else
            x=r;
        if(Manager.commit(x))return x;
        
        return null;
    }
    
    public static Rack getRack(int num)
    {
        return Manager.em.find(Rack.class, num);
    }
    
    
    public static boolean addSection(int num,Bisac b)
    {
        List<Bisac> list=Manager.em.createNamedQuery("Bisac.findBySubject").setParameter("subject", b.getSubject()).getResultList();
        Rack r=getRack(num);
        if(!list.isEmpty())
        {
           Racksection rs=new Racksection();
           rs.setBisac(b);
           rs.setRack(r);
            boolean commit = Manager.commit(rs);
            return commit;
        }
        return false;
        
    }
    
}



package dbfunctions;

import db.Employee;
import db.Manager;
import java.util.List;
import javax.swing.JOptionPane;



public class Employeef extends Employee{
    final public static char MANAGER_TYPE='m';
    final public static char EMPLOYEE_TYPE='e';
    final public static char CLERK_TYPE='c';
    final public static char OWNER_TYPE='o';
    protected static Employee addEmployee(Employee e)
    {
        if(e.getName()==null||e.getLoginid()==null||e.getPassword()==null){
            return null;
        }
        if(Manager.em.createNamedQuery("Employee.findByLoginid").setParameter("loginid", e.getLoginid()).getResultList().size()>0)
        {
            JOptionPane.showMessageDialog(null, "Sorry loginID already present");
            return null;
        }
        if(Manager.commit(e))
            return e;
        return null; 
    }
    
    protected static Employee editEmployee(Employee e){
        Employee e1=getEmployee(e.getLoginid());
        
        if(e1==null)
            return null;
        e1.setName(e.getName());
        e1.setMail(e.getMail());
        e1.setPassword(e.getPassword());
        e1.setPhone(e.getPhone());
        Manager.persist(e1);
        return e1;
    }
    protected static Employee getEmployee(String loginID)
    {
        try {
            return (Employee) Manager.em.createNamedQuery("Employee.findByLoginid").setParameter("loginid", loginID).getResultList().get(0);
        } catch (Exception e) {
            return null;
        }
       
       
    }
    public static void main(String[] args) {
        Manager x=new Manager();
        Employee e = new Employee();
        e.setLoginid("prudhvid");
        e.setName("PRUDHVID");
        e.setPassword("dpp");
        e.setType('s');
        Employee e1=addEmployee(e);
        if(e1==null )
            System.out.println("sorry");
    }
    
}

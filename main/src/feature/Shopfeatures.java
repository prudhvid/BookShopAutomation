

package feature;
import db.*;
import dbfunctions.*;
import java.util.List;
import javax.swing.JOptionPane;


public class Shopfeatures extends Employeef{
    public static void main(String[] args) {
        
    }
    
    public static Employee registerEmployee(Employee e)
    {
        e.setType(EMPLOYEE_TYPE);
        return addEmployee(e);
    }
    public static Employee registerClerk(Employee e)
    {
        e.setType(CLERK_TYPE);
        return addEmployee(e);
    }
    public static Employee registerManager(Employee e)
    {
        if(getManager()==null)
        {
            e.setType(MANAGER_TYPE);
            return addEmployee(e);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Already registered!!");
            return null;
        }
    }
    public static Employee registerOwner(Employee e)
    {
        if(getOwner()==null)
        {
            e.setType(OWNER_TYPE);
            return addEmployee(e);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Already registered!!");
            return null;
        }   
      }
    public static Employee getOwner()
    {
        List<Employee> list=Manager.em.createNamedQuery("Employee.findByType").setParameter("type", OWNER_TYPE).getResultList();
        if(list.size()==0)
            return null;
        return list.get(0);
    }
    public static Employee getManager()
    {
        List<Employee> list=Manager.em.createNamedQuery("Employee.findByType").setParameter("type", MANAGER_TYPE).getResultList();
        if(list.size()==0)
            return null;
        return list.get(0);
    }
    public static Employee getEmployeefromLogin(String loginID)
    {
        return Employeef.getEmployee(loginID);
    }
    public static void PlaceOrder(Publisher publisher)
    {
        publisher=Publisherf.addPublisher(publisher);
        
    }
    
    public static Employee editemployee(Employee e)
    {
        return editEmployee(e);
    }
}

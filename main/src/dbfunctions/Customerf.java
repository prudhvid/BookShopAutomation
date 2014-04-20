

package dbfunctions;

import db.*;
import main.constraint;



public class Customerf extends Customer{

    public Customerf() {
    }

    
    protected static Customer addCustomer(Customer c)
    { 
        if(c.getName()!=null)
        {
            System.out.println("added customer from Customerf");
            if(Manager.commit(c))
                return c;
        }
        
        return null;
    }
}

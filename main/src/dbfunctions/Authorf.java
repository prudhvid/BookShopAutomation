

package dbfunctions;
import db.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class Authorf extends Author{

    public Authorf(String AString) {
        super(AString);
    }
    
    public static void main(String[] args) {   
        Manager man=new Manager();
        getSuggestions("ro");
        
       
    }
    
    
    public static Author addAuthor(Author a){
        Author x=search(a.getName());
        if(x!=null)
        {
//            a.setId(x.getId());
//            Manager.commit(a);
//            return a;
            x.setBio(a.getBio());
            x.setDob(a.getDob());
            x.setUrl(a.getUrl());
            Manager.commit(x);
            return x;
        }
        if(Manager.commit(a))
            return a;
        return null;
    }
    public static Author search(String a)
    {
        List<Author> list=(List<Author>)Manager.em.createNamedQuery("Author.findByName").setParameter("name", a).getResultList();
        if(list.isEmpty())
            return null;
        return list.get(0);
        
    }
    public static Author addAuthor(String authString)
    {
        Author a=new Author(authString);
        a.setName(authString);
        return addAuthor(a); 
    }
    
    
    
    
    public static List<Author> getAuthorList(String[] authorStrings){
        List<Author> list=new LinkedList<>();
        for (String authorString : authorStrings) {
            Author a = addAuthor(authorString);
            if(a!=null)
                list.add(a);
        }
        return list;
    }
    public static List<Author> getAuthorList(List<String> authStrings){
        List<Author> list = new LinkedList<>();
        for (String authorString : authStrings) {
            Author a = addAuthor(authorString);
            if(a!=null)
                list.add(a);
        }
        
        return list;
    }
    
    public static List<Author>  getSuggestions(String parname){
        
        Manager.Activate();
        List<Author> list=(List<Author>) Manager.em.createQuery("SELECT a FROM Author a "
                + " WHERE a.name LIKE :x  ORDER BY a.name").
                setParameter("x", "%"+parname.toUpperCase()+"%").getResultList();
        return list;
    }
    
    public static List<Author> getList()
    {
        return Manager.em.createNamedQuery("Author.findAll").getResultList();
    }
    
    
}

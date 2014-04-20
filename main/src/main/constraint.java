

package main;


import java.util.prefs.Preferences;
import java.util.regex.Pattern;

/**
 * 
 * The below class is to implement all reusable code for checking various constraints generally to be done like phone,email etc.
 * The user preferences are saved using Preferences API link -- 
 * http://java.amitph.com/2012/10/java-preferences-api-introduction.html#.Uzbno_mSzL8
 * 
 */
public class constraint {
    final static String Mob_Num="MOB_NUM_Length";
    final static String PASSWORD="pasword";
    static Preferences userPreferences=Preferences.userRoot();
    public static boolean checkmobile(String mob)
    {
        
        userPreferences.putInt(Mob_Num, 10);;
        int length=userPreferences.getInt(Mob_Num, 10);
        if(mob.length()<length)
            return false;
        for (int i = 0; i < length; i++) {
            if(!Character.isDigit(mob.charAt(i)))
                return false;
        }
        return true;
    }
    public static void changeMobnumlength(int num)
    {
        Preferences userPreferences=Preferences.userRoot();
        userPreferences.putInt(Mob_Num, num);
    }
    
    private static final Pattern rfc2822 = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
    
    public static boolean checkemail(String emailString)
    {
//        java.util.regex.Pattern p = java.util.regex.Pattern.compile(".+@.+\\.[a-z]+");
//        java.util.regex.Matcher m = p.matcher(emailString);
//        return m.matches();   
        return rfc2822.matcher(emailString).matches();
    }
    public static void main(String args[])
    {
        String email="dharma@na@h.com";
       
        System.out.println(checkemail(email));
        System.out.println(checkmobile("17345"));
    }
    public static void setShopPassword(String password)
    {
       
        userPreferences.put(PASSWORD, password);
    }
    public static String getShopPassword()
    {
        return userPreferences.get(PASSWORD, "password");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import test.EmailSender;

/**
 *
 * @author sumit
 */
public class maildemo {
    
    public static void main(String[] args)
    {
        String to[] = {"sumitgoswami075@gmail.com","dharmana.prudhvi@gmail.com"};
        if( EmailSender.sendMail("xprudhvi123@gmail.com","xprudhvi", "this is  just to inform that i got it :P", to))
        {
            System.out.println("email sent");
        }
        else
        {
            System.out.println("some error ");
        }
    }
    
}

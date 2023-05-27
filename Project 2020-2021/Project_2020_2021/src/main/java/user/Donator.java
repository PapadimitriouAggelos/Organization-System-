//Κλάση που αναπαριστά ένα χρήστη που είναι δωρητής

package user;

import organization.Offers;
import java.util.ArrayList;

public class Donator extends User
{
    //Μέλη
    public Offers offersList;
    
    //Constructor
    public Donator(String n, String p)
    {
        super(n,p);
        
        offersList=new Offers();
    }
    
    public Donator()
    {
        
    }
    
    //toString() method: η οποία επιστρέφει όλες τις πληροφορίες για τους δωρητές
   @Override
   public String toString()
   {
       return "Όνομα Δωρητή= "+getName()+", Τηλέφωνο Δωρητή= "+getPhone();
   }
   
}


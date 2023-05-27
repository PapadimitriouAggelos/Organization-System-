//Κλάση που αναπαριστά τον επωφελούμενο

package user;

import organization.RequestDonationList;
import organization.Requests;
import java.util.ArrayList;

public class Beneficiary extends User
{
   //Μέλη
   private int noPersons=1;
   
   public RequestDonationList receivedList; //αναπαριστά τη λίστα των ειδών που έχει λάβει
   //που έχει ήδη λάβει
   
   public Requests requestsList; //αναπαριστά τη τρέχουσα λίστα των ειδών και των 
   //ποσοτήτων που ζητά να του δοθούν
   
   //Constructor
   public Beneficiary(String n, String p, int noPersons)
   {
       super(n,p);
       
       this.noPersons=noPersons;
       
       receivedList=new RequestDonationList();
       
       requestsList=new Requests();
       
   }
   
   public Beneficiary()
   {
       
   }
   
   
   //setter για το μέλος noPersons
   public void setNoPersons(int noPersons)
   {
       this.noPersons=noPersons;
   }
   
   //getter για το μέλος noPersons
   public int getNoPersons()
   {
       return noPersons;
   }
   
   //toString() method: η οποία επιστρέφει όλες τις πληροφορίες για τους επωφελούμενους
   @Override
   public String toString()
   {
       return "Όνομα Επωφελούμενου= "+getName()+", Τηλέφωνο Επωφελούμενου= "+getPhone()+", Αριθμός μελών οικογένειας Επωφελούμενου= "+getNoPersons();
   }
   
}

//Κλάση που αναπαριστά ένα χρήστη

package user;

abstract public class User 
{
   //Μέλη
   private String name;
   private String phone;
   
   //Constructor
   public User(String n, String p)
   {
       name=n;
       phone=p;
   }
   
   public User()
   {
       
   }
   
   //setters για τα μέλη της κλάσης
   public void setName(String n)
   {
       name=n;
   }
   
   public void setPhone(String p)
   {
       phone=p;
   }
   
   //getters για τα μέλη της κλάσης
   public String getName()
   {
       return name;
   }
   
   public String getPhone()
   {
       return phone;
   }
}

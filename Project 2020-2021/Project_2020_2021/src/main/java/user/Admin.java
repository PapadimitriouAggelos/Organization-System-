//Κλάση που αναπαριστά τον διαχειριστή του συστήματος

package user;

public class Admin extends User
{
    //Μέλη
    private boolean isAdmin;
    
    //Constructor
    public Admin(String n, String p)
    {
        super(n,p);
        
        this.isAdmin=true;
    }
    
    //getter για το μέλος της κλάσης
    public boolean getIsAdmin()
    {
        return isAdmin;
    }
}

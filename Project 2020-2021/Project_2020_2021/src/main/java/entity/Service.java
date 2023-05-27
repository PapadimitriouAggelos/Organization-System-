//Κλάση που αντιπροσωπεύει ένα είδος δωρεάς, το οποίο είναι υπηρεσία

package entity;

public class Service extends Entity
{
    //Constructor
    public Service(String n, String d, int id)
    {
        super(n,d,id);
    }
    
    //υλοποίηση της μεθόδου getDetails()
    public String getDetails()
    {
        String s;
        
        s="Το είδος δωρεάς είναι Service";
        
        return s;
    }
}

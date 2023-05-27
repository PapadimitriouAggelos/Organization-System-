//Κλάση που αντιπροσωπεύει ένα είδος δωρεάς

package entity;

abstract public class Entity //η κλάση είναι abstract διότι δεν μπορεί να δημιουργήσει στιγμιότυπα
{
   //Μέλη
   private String name; //όνομα δωρεάς
   private String description; //σύντομη περιγραφή δωρεάς
   private int id; //κωδικός είδους
   
   //Constructor
   public Entity(String n, String d, int id)
   {
       name=n;
       description=d;
       this.id=id;
   }
   
   public Entity()
   {
       
   }
   
   //setters για τα μέλη της κλάσης
   public void setName(String n)
   {
       name=n;
   }
   
   public void setDescription(String d)
   {
       description=d;
   }
   
   public void setId(int id)
   {
       this.id=id;
   }
   
    //getters για τα μέλη της κλάσης
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public int getId()
    {
        return id;
    }
    
    //String getEntityInfo() method, που επιστρέφει τις προηγούμενες πληροφορίες
    public String getEntityInfo()
    {
        String s;
        
        s="Όνομα είδους= "+getName()+", Περιγραφή είδους= "+getDescription()+", Κωδικός είδους= "+getId();
        
        return s;
    }
    
    //String getDetails() method
    public String getDetails()
    {
        String s=" ";
        
        return s;
    }
    
    //String toString() method, που καλεί τις άλλες δύο
    @Override
    public String toString()
    {
        return getEntityInfo()+"\n"+getDetails()+"\n";
    }

    public double getLevel1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getLevel2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getLevel3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

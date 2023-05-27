//Κλάση που αντιπροσωπεύει ένα είδος δωρεάς, το οποίο είναι υλικό αγαθό

package entity;

public class Material extends Entity
{
    //Μέλη
    private double level1; //ποσότητα που δικαιούται 1 άτομο
    private double level2; //ποσότητα που δικαιούνται από 2-4 άτομα
    private double level3; //ποσότητα που δικαιούνται από 5 και περισσότερα άτομα
    
    //Constructor
    public Material(String n, String d, int id, double l1, double l2, double l3)
    {
        super(n,d,id);
        
        level1=l1;
        level2=l2;
        level3=l3;
    }
    
    //getters για τα μέλη της κλάσης
    public double getLevel1()
    {
        return level1;
    }
    
    public double getLevel2()
    {
        return level2;
    }
    
    public double getLevel3()
    {
        return level3;
    }
    
    //υλοποίηση της μεθόδου getDetails()
    public String getDetails()
    {
        String s;
        
        s="Το είδος της δωρεάς είναι Material\n";
        s+="Ποσότητα Level1: "+getLevel1()+"\n"; //s= s +"Ποσότητα Level1: "+getLevel1()+"\n"
        s+="Ποσότητα Level2: "+getLevel2()+"\n"; //
        s+="Ποσότητα Level3: "+getLevel3()+"\n";
        
        return s;
    }
    
}

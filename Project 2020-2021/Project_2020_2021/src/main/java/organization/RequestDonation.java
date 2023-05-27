//Κλάση που απεικονίζει το αίτημα ή την παροχή για ένα συγκεκριμένο Entity σε συγκεκριμένη ποσότητα quantity

package organization;

import entity.Entity;

public class RequestDonation 
{
    //Μέλη
    public Entity entity; //δωρεά και η ποσότητα γι αυτή τη δωρεά
    public double quantity; //ποσότητα
    
    //Constructor
    public RequestDonation(Entity e, double q)
    {
        entity=e;
        quantity=q;
    }

    
    //setter για το μέλος quantity
    public void setQuantity(double q)
    {
        quantity=q;
    }
    
    //getters για τα μέλη της κλάσης
    public Entity getEntity()
    {
        return entity;
    }
    
    public double getQuantity()
    {
        return quantity;
    }
}

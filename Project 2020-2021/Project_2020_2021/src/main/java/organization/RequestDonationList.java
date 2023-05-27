//Κλάση που αναπαριστά μια συλλογή από αντικείμενα τύπου RequestDonation 

package organization;

import entity.*;
import exception.EntityException;
import java.util.ArrayList;
import java.util.Scanner;
import organization.Organization;
import user.*;

public class RequestDonationList 
{
    Scanner sc=new Scanner(System.in);
    
    public ArrayList <RequestDonation> rdEntities=new ArrayList <RequestDonation>(); //λίστα με στιγμιότυπα τύπου RequestDonation
    
    
    //get(): Επιστρέφει ένα συγκεκριμένο RequestDonation από τη συλλογή, δίνοντας το id του Entity
    public RequestDonation get(int id)
    {
        for(int i=0;i<rdEntities.size();i++)
        {
            if(rdEntities.get(i).getEntity().getId()==id)
                return rdEntities.get(i); // επιστρέφει το στιγμιότυπο ή αντικείμενο
        }
        return null;
    }
    
    //remove(): Αφαιρεί ένα RequestDonation από την rdEntities
    public void remove(int id)
    {
        rdEntities.remove(get(id));
    }
    
    //modify(): Για ένα αντικείμενο στη συλλογή τροποποιεί το quantity(ποσότητα)
    public void modify(int id, double q)
    {
        get(id).quantity=q; //με την get() παίρνουμε το συγκεκριμένο αντικείμενο και αλλάζουμε την ποσότητα του
    }
    
    //add(): Μέθοδος η οποία τοποθετεί ένα συγκεκριμένο RequestDonation στην λίστα rdEntities. Αν το requestDonation
    //αφορά entity που ήδη υπάρχει σε κάποιο requestDonation της συλλογής, γίνεται ενημέρωση της ποσότητας του υπάρχοντος.
    //Αν αφορά entity που δεν υπάρχει σε κάποιο requestDonation, δημιουργεί νέο αντικείμενο και το προσθέτει στη συλλογή.
    //Αν αφορά entity που δεν υπάρχει στην entityList του οργανισμού, εγείρεται εξαίρεση
    public void add(int id,Organization org,double d) throws EntityException
    {        
        if(!org.foundId(id)) //αν δεν βρεις entity με το συγκεκριμένο id στη λίστα entityList πέτα εξαίρεση
        {
            throw new EntityException("δεν υπάρχει Entity με τέτοιο id!");
        }
        else //αλλιώς, δηλαδή αν βρεις entity με το συγκεκριμένο id στη λίστα entityList
        {
            if(get(id)!=null) //αν το συγκεκριμένο RequestDonation υπάρχει στην rdEntities
            {
                double quantity=d+get(id).quantity;
                modify(id,quantity);
            }
            else
            {
                for(Entity en:org.entityList)
                {
                    if(en.getId()==id)
                    {
                        RequestDonation rq=new RequestDonation(en,d);
                        rdEntities.add(rq);
                        break;
                    }
                }
            }
        }
        
    }
                
    
    //monitor(): Εμφανίζει το σύνολο των ειδών που βρίσκεται στη λίστα rdEntities, δηλαδή τα ονόματα των entity και τις
    //ποσότητες που τους αντιστοιχούν
    public void monitor()
    {
        System.out.println("Σύνολο ειδών στην λίστα:");
        
        for(RequestDonation k: rdEntities)
        {
            System.out.println("Όνομα= "+k.entity.getName()+", ID= "+k.entity.getId()+", Ποσότητα= "+k.quantity);
        }
    }
    
    //reset(): Αφαιρεί όλα τα αντικείμενα από την λίστα
    public void reset()
    {
        rdEntities.removeAll(rdEntities);
    }
}
    
        
                

        


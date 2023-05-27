//Κλάση που αναπαριστά το σύνολο των ειδών που προτίθεται να συνεισφέρει ο Donator

package organization;

import organization.RequestDonationList;
import exception.EntityException;
import organization.Organization;

public class Offers extends RequestDonationList
{
    /*
    Ενημερώνει τα currentDonations του οργανισμού με τις προσφορές που περιέχονται στη λίστα rdEntities. 
    Αν αυτό ολοκληρωθεί επιτυχώς, διαγράφει τα περιεχόμενα της λίστας rdEntities
    */
    public void commit(Organization org) 
    {
      //επανάληψη για όλα τα αντικείμενα της rdEntities
      for(int i=0;i<rdEntities.size();i++)
      {
          try
          {
              org.currentDonations.add(rdEntities.get(i).getEntity().getId(), org, rdEntities.get(i).getQuantity());
          }
          catch(EntityException e)
          {
              
          }
      }    
      reset(); //καλούμε τη συνάρτηση reset της κλάσης RequestDonationList και διαγράφουμε κάθε rdEntity
      
    }
    
}


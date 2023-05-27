//Κλάση που αναπαριστά το σύνολο των ειδών (Material ή Services) που ζητά ο Beneficiary

package organization;

import exception.EntityException;
import exception.NoProductException;
import exception.ProductQuantityException;
import exception.QuantityLimitException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import organization.Organization;
import user.Beneficiary;

public class Requests extends RequestDonationList
{    
   Scanner sc=new Scanner(System.in);
   
   //Constructor
   public Requests()
   {
       
   }
   
   //validRequestDonation() method, παίρνει σαν όρισμα το id του Entity
   //Ελέγχει αν η ποσότητα ενός RequestDonation είναι μέσα στο επιτρεπόμενο όριο με βάση τον αριθμό μελών που 
   //έχει δηλώσει o Beneficiary και την ποσότητα που ζητάει να του δοθεί
   //ορίσματα: το id του Entity, έναν οργανισμό, την ποσότητα που ζητάει ο Beneficiary να πάρει και 
   //ο αριθμός μελών της οικογένειας του beneficiary 
   public boolean validRequestDonation(int id, Organization org,double quantity,int numPersons) throws QuantityLimitException
   {
       boolean flag=false; //έστω ότι δεν θα πάρει την ποσότητα που ζητάει
       
       for(int i=0;i<org.entityList.size();i++)
       {
           if(org.entityList.get(i).getId()==id && org.entityList.get(i).getDetails().equals("Το είδος δωρεάς είναι Service"))
               break;
           
           if(org.entityList.get(i).getId()==id) //αν είναι material και υπάρχει στη λίστα
           {
               if(numPersons<2)
               {
                   if(quantity>org.entityList.get(i).getLevel1()) //αν ξεπερνάει την ποσότητα του Level1
                       throw new QuantityLimitException("Η ποσότητα που ζητάει ξεπερνάει το όριο που του αναλογεί. Η ποσότητα που του αντιστοιχεί είναι: "+org.entityList.get(i).getLevel1()+"!");
               }
               
               if(numPersons<5)
               {
                   if(quantity>org.entityList.get(i).getLevel2()) //αν ξεπερνάει την ποσότητα του Level2
                       throw new QuantityLimitException("Η ποσότητα που ζητάει ξεπερνάει το όριο που του αναλογεί. Η ποσότητα που του αντιστοιχεί είναι: "+org.entityList.get(i).getLevel2()+"!");
               }
               else
               {
                   if(quantity>org.entityList.get(i).getLevel3()) //αν ξεπερνάει την ποσότητα του Level3
                       throw new QuantityLimitException("Η ποσότητα που ζητάει ξεπερνάει το όριο που του αναλογεί. Η ποσότητα που του αντιστοιχεί είναι: "+org.entityList.get(i).getLevel3()+"!");
               }
               break;
           }
       }
      flag=true;
      return flag; 
   }
   
   //συνάρτηση που ελέγχει αν ένα συγκεκριμένο αντικείμενο υπάρχει στη λίστα currentDonations
   //έχει ως όρισμα το id του αντικειμένου και τον οργανισμό 
   public boolean ProductStock(int id, Organization org) throws NoProductException
   {
       if(org.currentDonations.get(id)==null)
           throw new NoProductException("Δεν υπάρχει το συγκεκριμένο αντικείμενο στη λίστα");
       else
           return true;
   }
   
   //συνάρτηση που ελέγχει για ένα συγκεκριμένο αντικείμενο εάν υπάρχει στη λίστα currentDonations επαρκής ποσότητα
   //έχει ως όρισμα το id του αντικειμένου, τον οργανισμό και την ποσότητα που ζητάει ο Beneficiary
   public boolean ProductQuantity(int id, double quantity, Organization org) throws ProductQuantityException
   {
       if(org.currentDonations.get(id).getQuantity()<quantity)
           throw new ProductQuantityException("Δεν επαρκεί η ποσότητα για το συγκεκριμένο είδος");
       else
           return true;
   }
           
    //add(), modify(): Υπερκαλύπτουν τις μεθόδους της υπερκλάσης και τελικά τις καλούν, εφόσον επιτύχουν και οι δύο έλεγχοι 
    //α) η ποσότητα του entity είναι διαθέσιμη στον οργανισμό και 
    //β) o Beneficiary τη δικαιούται (validRequestDonation()). Αν δεν ισχύει το α) θα δημιουργηθεί κατάλληλη εξαίρεση, 
    //ενώ αν δεν ισχύει το β) θα εγερθεί κατάλληλη εξαίρεση διαφορετικού τύπου.

    //παίρνει σαν όρισμα το id του Entity, τον Οργανισμό καθώς και η ποσότητα που υπάρχει για ένα αντικείμενο
    public void add(int id, Organization org, double quantity,int numPersons)
    {
        boolean flag=false; 
        
        //Ο πρώτος έλεγχος που κάνουμε είναι εάν υπάρχει το αντικείμενο στην λίστα, χρησιμοποιώντας την μέθοδο
        //ProductStock(). Αν υπάρχει το αντικείμενο, τότε το flag θα γίνει true. Αν δεν υπάρχει τότε θα μπει 
        //στο catch και θα τυπώσει μήνυμα σφάλματος
        try
        {
            flag=ProductStock(id,org);
        }
        catch(NoProductException e)
        {
            flag=false;
            System.out.println(e.getMessage());
        }
        
        //αν το αντικείμενο υπάρχει στην λίστα, θα πρέπει να γίνει έλεγχος για το εάν για το εάν επαρκεί η ποσότητα
        //χρησιμοποιώντας τη μέθοδο ProductQuantity(). Αν η ποσότητα επαρκεί τότε το flag θα γίνει true. Αν δεν επαρκεί
        //τότε θα μπει στο catch και θα τυπώσει μήνυμα σφάλματος
        if(flag==true)
        {
           try
           {
              flag=ProductQuantity(id,quantity,org);
           }
           catch(ProductQuantityException e)
           {
               flag=false;
               System.out.println(e.getMessage());
           }
        }
        
        if(flag==true) //δηλαδή αν υπάρχει το αντικείμενο και αν επαρκεί η ποσότητα
        {
            //αν είναι Service
            if(org.currentDonations.get(id).entity.getDetails().equals("Το είδος δωρεάς είναι Service"))
            {
                try
                {
                    //καλούμε την μέθοδο add() της υπερκλάσης και προστίθεται το νέο Service
                    super.add(id, org, quantity); 
                }
                catch(EntityException e)
                {
                    System.out.println(e.getMessage());
                }
            }        
            else //αν δηλαδή είναι Material
            {
                try
                {
                    //αν μπορεί να πάρει ο Beneficiary την ποσότητα που ζητάει
                    if(validRequestDonation(id,org,quantity,numPersons)==true)

                    try
                    {
                        //καλούμε την μέθοδο add() της υπερκλάσης και προστίθεται το νέο Material
                        super.add(id, org, quantity);
                    }
                    catch(EntityException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
                catch(QuantityLimitException e)
                {
                    System.out.println(e.getMessage());
                }
            }
                
        }
    }
    
    //modify() method
    public void modify(int id, double quantity,Organization org, int numPersons)
    {
        boolean flag=false;
        
        //Ο πρώτος έλεγχος που κάνουμε είναι εάν επαρκεί η ποσότητα του αντικειμένου στην λίστα, χρησιμοποιώντας την μέθοδο
        //ProductQuantity(). Αν επαρκεί το flag θα γίνει true και θα συνεχίσει(δεν μπαίνει στο catch). Αν δεν επαρκεί 
        //τότε θα μπει στο catch και θα τυπώσει μήνυμα σφάλματος
        try
        {
            flag=ProductQuantity(id,quantity,org);
        }
        catch(ProductQuantityException e)
        {
            flag=false;
            System.out.println(e.getMessage());
        }
        
        //ελέγχουμε αν ο Beneficiary με βάση αυτά που ζητάει, ξεπερνάει το όριο που του αντιστοιχεί
        try
        {
            flag=flag&&validRequestDonation(id,org,quantity,numPersons);
        }
        catch(QuantityLimitException e)
        {
            flag=false;
            System.err.println(e.getMessage());
        }
        
        if(flag==true) //αν οι προηγούμενοι έλεγχοι είναι σωστοί, δηλαδή το flag παραμένει true
        {
            // αν εντοπίζεται το Entity και είναι Service
            if(org.currentDonations.get(id).getEntity().getDetails().equals("Το είδος δωρεάς είναι Service"))
                super.modify(id, quantity); //καλεί την modify που είναι μέθοδος της υπερκλάσης και αλλάζει η ποσότητα
            else //είναι Material
            {
                //αν ζητήσει παραπάνω ποσότητα από την ποσότητα που δικαιούται για το Material
                //τότε εγείρεται εξαίρεση και μπαίνει στο catch
                try
                {
                    if(validRequestDonation(id,org,quantity,numPersons)==true)
                        super.modify(id, quantity); //καλούμε τη συνάρτηση modify της υπερκλάσης και αλλάζουμε
                        //τη ποσότητα 
                }
                catch(QuantityLimitException e)
                {
                    System.out.println(e.getMessage());
                }
            }
                
        }
    }
    
    //commit(): Ελέγχει εκ νέου (γιατί;) και εξυπηρετεί τα requestDonation αντικείμενα της rdEntities. 
    //Για κάθε requestDonation ελέγχονται τα α, β και εγείρεται κατάλληλη εξαίρεση αν κάποιος έλεγχος αποτύχει. 
    //Αν επιτύχουν οι έλεγχοι, αφαιρείται η ποσότητα από το συγκεκριμένο είδος στην currentDonations του οργανισμού, 
    //το συγκεκριμένο requestDonation γίνεται remove() από την rdEntities και add() στη receivedList του Beneficiary.
    public void commit(Organization org, Beneficiary b)
    {
        boolean flag=true; 
        
        for(int i=0; i<rdEntities.size();i++)
        {
            //εκ νέου έλεγχος για το εάν το αντικείμενο υπάρχει στη λίστα currentDonations
            try
            {
               flag=ProductStock(rdEntities.get(i).getEntity().getId(),org); 
            }
            catch(NoProductException e)
            {
                flag=false;
                System.out.println(e.getMessage());
                break;
            }
            
            //εκ νέου έλεγχος για την ποσότητα του αντικειμένου στη λίστα currentDonations
            try
            {
                flag=flag&&ProductQuantity(rdEntities.get(i).getEntity().getId(),rdEntities.get(i).getQuantity(),org);
            }
            catch(ProductQuantityException e)
            {
                flag=false;
                System.out.println(e.getMessage());
                break;
            }
            
            try //έλεγχος για το αν ο χρήστης δεν ξεπερνάει το όριο που του αναλογεί, αν το ξεπερνάει εγείρεται εξαίρεση
            {
                 flag=flag&&validRequestDonation(rdEntities.get(i).getEntity().getId(),org,rdEntities.get(i).getQuantity(),b.getNoPersons()); 
            }
            catch(QuantityLimitException e) 
            {
                flag=false;
                System.out.println(e.getMessage());
                break;
            }
        }
        
            //η currentDonations περιέχει rdEntities στοιχεία.
            if(flag==true) //δηλαδή αν ικανοποιουνται και οι 3 προυποθεσεις
            {
               for(int i=0;i<rdEntities.size();i++)
               {
                    //καλούμε τη μέθοδο modify() της κλάσης RequestDonationList στη λίστα currentDonations
                    //την καλέσαμε έτσι ώστε να αφαιρέσουμε από την λίστα την ποσότητα που θα δοθεί ως προσφορά
                    try
                    {
                        org.currentDonations.modify(rdEntities.get(i).getEntity().getId(), org.currentDonations.get(rdEntities.get(i).getEntity().getId()).getQuantity()-rdEntities.get(i).getQuantity());

                        //αν το υπόλοιπο του είδους μηδενιστεί τότε πρέπει να το αφαιρέσουμε από τη λίστα currentDonations
                        if(org.currentDonations.get(rdEntities.get(i).getEntity().getId()).getQuantity()==0)
                            org.currentDonations.remove(rdEntities.get(i).getEntity().getId()); //αφαιρούμε από την currentDonations
                            //το συγκεκριμένο είδος

                        //προσθέτουμε στη λίστα receivedList του Beneficiary το αντικείμενο καθώς και την ποσότητα του    
                        b.receivedList.add(rdEntities.get(i).getEntity().getId(), org, rdEntities.get(i).getQuantity());
                    }
                    catch(EntityException e)
                    {
                        
                    }
               }
               
               reset(); //καλούμε τη μέθοδο reset() έτσι ώστε να σβήσουμε τη requestsList του Beneficiary
            }
            
             
    }
}
    
    
    
   
    
  
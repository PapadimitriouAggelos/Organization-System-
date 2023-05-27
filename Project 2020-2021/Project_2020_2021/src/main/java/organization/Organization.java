//Κλάση που αντιστοιχεί στον οργανισμό που υποστηρίζει το σύστημα 

package organization;

import entity.Entity;
import entity.Material;
import entity.Service;
import exception.BeneficiaryException;
import exception.DonatorException;
import exception.EntityException;
import java.util.*;
import user.Admin;
import user.Beneficiary;
import user.Donator;

public class Organization 
{
    //Μέλη
    private String name; //όνομα οργανισμού
    private Admin admin; //διαχειριστής 
    
    public ArrayList <Entity> entityList=new ArrayList <Entity>(); //λίστα που περιέχει όλα τα Entity που μπορούν να διανεμηθούν
    public ArrayList <Donator> donatorList=new ArrayList <Donator>(); //λίστα που περιέχει τους δωρητές
    public ArrayList <Beneficiary> beneficiaryList=new ArrayList <Beneficiary>(); //λίστα που περιέχει τους επωφελούμενους
    
    public RequestDonationList currentDonations; //λίστα με τις διαθέσιμες προσφορές και τις ποσότητες τους
    
    Scanner sc=new Scanner(System.in);
    
    //Constructor
    public Organization(String n, Admin a)
    {
        name=n;
        admin=a;
        
        currentDonations=new RequestDonationList();
    }
    
    //setter για το μέλος admin τύπου Admin
    public void setAdmin(Admin a)
    {
        admin=a;
    }
    
    //setter για το μέλος name
    public void setName(String n)
    {
        name=n;
    }
    
    //getter για το μέλος admin τύπου Admin
    public Admin getAdmin()
    {
        return admin;
    }
    
    //getter για το μέλος name
    public String getName()
    {
        return name;
    }
    
    //addEntity() method, προσθέτει ένα είδος (entity/δωρεά) που μπορεί να διακινηθεί από τον οργανισμό 
    //Γίνεται χειρισμός εξαίρεσης αν υπάρχει ήδη
    public void addEntity() throws EntityException
    {
        String name,description,ans;
        int id;
        double l1, l2, l3;
        
        System.out.println("Δώσε το όνομα της Δωρεάς: ");
        name=sc.next();
        
        System.out.println("Δώσε σύντομη περιγραφή της Δωρεάς");
        sc.nextLine();
        description=sc.nextLine();
        
        System.out.println("Δώσε τον κωδικό(id) της Δωρεάς");
        id=sc.nextInt();
        
        System.out.println("Δώσε 'Μ' εάν η δωρεά είναι Material και 'S' εάν η δωρεά είναι Service ");
        ans=sc.next();
        
        Entity e;
        
        if(ans.equals("M"))
        {
            System.out.println("Η δωρεά είναι Material");
            System.out.println("Δώσε τις ποσότητες που δικαιούνται ανάλογα με τα μέλη της οικογένειας");
            
            System.out.println("Δώσε ποσότητα για το Level1:");
            l1=sc.nextDouble();
            
            System.out.println("Δώσε ποσότητα για το Level2:");
            l2=sc.nextDouble();
            
            System.out.println("Δώσε ποσότητα για το Level3:");
            l3=sc.nextDouble();
            
            e=new Material(name,description,id,l1,l2,l3);
        }
        else
        {
            System.out.println("Η δωρεά είναι Service");
            
            e=new Service(name,description,id);
            
            for(int i=0;i<entityList.size();i++)
            {
                if(entityList.get(i).getId()==e.getId())
                {
                    throw new EntityException("Υπάρχει το ίδιο id");
                }
                else
                    entityList.add(e);
            }
           
        }
        
    }
    
    //removeEntity() method, διαγράφει ένα είδος (entity)
    public void removeEntity(int id)
    {
        for(int i=0;i<entityList.size();i++)
        {
            if(entityList.get(i).getId()==id) //αν το id υπάρχει ήδη
            {
                entityList.remove(entityList.get(i));
                break; //για να βγούμε από την επανάληψη
            }
        }
    }
    
    //insertDonator(): προσθέτει έναν Donator στον οργανισμό. Γίνεται χειρισμός εξαίρεσης αν υπάρχει ήδη
    public void insertDonator() throws DonatorException
    {
        String name,phone;
        
        System.out.println("Δώσε το όνομα του Donator: ");
        name=sc.next();
        
        System.out.println("Δώσε το τηλέφωνο του Donator: ");
        phone=sc.next();
        
        Donator donator=new Donator(name,phone);
        
        for(int i=0;i<donatorList.size();i++)
            if(donatorList.get(i).getPhone().equals(phone))
                throw new DonatorException("");
            else
                donatorList.add(donator);
    }
    
    //removeDonator(): αφαιρεί έναν Donator από τον οργανισμό
    public void removeDonator(String p)
    {
        for(int i=0;i<donatorList.size();i++)
            if(p.equals(donatorList.get(i).getPhone()))
            {
                donatorList.remove(donatorList.get(i));
                break;
            }
    }
    
    //insertBeneficiary(): προσθέτει έναν Beneficiary στον οργανισμό. Γίνεται χειρισμός εξαίρεσης αν υπάρχει ήδη
    public void insertBeneficiary() throws BeneficiaryException
    {
        String name,phone;
        int num;
        
        System.out.println("Δώσε το όνομα του Beneficiary: ");
        name=sc.next();
        
        System.out.println("Δώσε το τηλέφωνο του Beneficiary: ");
        phone=sc.next();
        
        System.out.println("Δώσε αριθμό για τα μέλη της οικογένειας του Beneficiary: ");
        num=sc.nextInt();
        
        Beneficiary beneficiary=new Beneficiary(name,phone,num);
        
        for(int i=0;i<beneficiaryList.size();i++)
        {
            if(beneficiaryList.get(i).getPhone().equals(phone))
                throw new BeneficiaryException("");
            else
                beneficiaryList.add(beneficiary);
        }
    }
    
    //removeBeneficiary(): αφαιρεί έναν Beneficiary από τον οργανισμό
    public void removeBeneficiary(String p)
    {
        for(int i=0;i<beneficiaryList.size();i++)
            if(p.equals(beneficiaryList.get(i).getPhone()))
            {
                beneficiaryList.remove(beneficiaryList.get(i));
                break;
            }
    }
    
    //listEntities(): Εμφανίζει τις υπάρχουσες κατηγορίες των entity(material,services) και λίστα με όλα τα entity
    //ανά συγκεκριμένη κατηγορία
    public void listEntities()
    {        
        ArrayList <Entity> materials=new ArrayList <Entity>();
        
        ArrayList <Entity> services=new ArrayList <Entity>();
        
        //κάνουμε μια επανάληψη στην λίστα entityList η οποία περιέχει όλα τα entities
        for(int i=0;i<entityList.size();i++)
            if(entityList.get(i).getDetails().equals("Το είδος δωρεάς είναι Service")) //αν είναι η δωρεά Service
                services.add(entityList.get(i)); //βάζω το στιγμιότυπο στην λίστα με τα services
            else  
                materials.add(entityList.get(i)); //βάζω το στιγμιότυπο στην λίστα με τα materials
        
        //εκτύπωση του Δυναμικού Πίνακα που περιέχει τις δωρεές τύπου Service
        System.out.println("Δυναμικός Πίνακας που περιέχει τα Services: ");
        for(int i=0;i<services.size();i++)
            System.out.println(services.get(i).toString());
        
        //εκτύπωση του Δυναμικού Πίνακα που περιέχει τις δωρεές τύπου Material
        System.out.println("Δυναμικός Πίνακας που περιέχει τα Materials: ");
        for(int i=0;i<materials.size();i++)
            System.out.println(materials.get(i).toString());
    }
    
    //listBeneficiaries(): εμφανίζει τους επωφελούμενους Beneficiaries και την τρέχουσα κατάσταση παροχών που έχουν 
    //λάβει ανά είδος
    public void listBeneficiaries()
    {
        System.out.println("Λίστα Επωφελούμενων: ");
        
        for(int i=0;i<beneficiaryList.size();i++)
            System.out.println(beneficiaryList.get(i).toString());
    }
    
    //listDonators(): εμφανίζει όλους τους δωρητές(Donators) 
    public void listDonators()
    {
        System.out.println("Λίστα Δωρητών: ");
        
        for(int i=0;i<donatorList.size();i++)
            System.out.println(donatorList.get(i).toString());
    }
    
    public Donator foundDonator(String phone)
    {
        for(int i=0;i<donatorList.size();i++)
            if(donatorList.get(i).getPhone().equals(phone))
            {
                return donatorList.get(i);
            }
        return null;
    }
    
    public Beneficiary foundBeneficiary(String phone)
    {
        for(int i=0;i<beneficiaryList.size();i++)
            if(beneficiaryList.get(i).getPhone().equals(phone))
            {
                return beneficiaryList.get(i);
            }
        return null;
    }
    
    public boolean foundId(int id)
    {
        boolean flag=false;
        
        for(Entity en: this.entityList)
        {
            if(id==en.getId())
            {
                flag=true;
                break;
            }
        }
        return flag;
    }
}

package organization;

import entity.Material;
import entity.Service;
import exception.EntityException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.Admin;
import user.Beneficiary;
import user.Donator;

public class Main 
{

    public static void main(String[] args)  
    {
        
        //δημιουργούμε ένα στιγμιότυπο τύπου Admin
        Admin admin=new Admin("a","1"); 
        
       //δημιουργούμε ένα στιγμιότυπο τύπου Organization
       Organization org=new Organization("Organization",admin);
       
       //δημιουργώ 3 Material και 3 Service
       Material m1=new Material("Milk","Χαμηλά Λιπαρά",1,2,4,6);
       Material m2=new Material("Sugar","Λευκή",2,2,4,7);
       Material m3=new Material("Rice","Κίτρινο",3,3,4,6);
       Service s1=new Service("BabySitting","Για μωρά",4);
       Service s2=new Service("MedicalSupport","Για ευπαθείς ομάδες",5);
       Service s3=new Service("NurserySupport","Για ευπαθείς ομάδες",6);
       
        
        //Βάζουμε τα στιγμιότυπα μέσα στη λίστα entityList
        try
        {
            org.entityList.add(m1);
            org.entityList.add(m2);
            org.entityList.add(m3);
            org.entityList.add(s1);
            org.entityList.add(s2);
            org.entityList.add(s3);
            
            org.currentDonations.add(1, org, 4);
            org.currentDonations.add(2, org, 4);
            org.currentDonations.add(3, org, 3);
            org.currentDonations.add(4, org, 10);
            org.currentDonations.add(5, org, 7);
        }
        catch(EntityException e)
        {
            
        }
        
        
        //δημιουργούμε 2 στιγμιότυπα τύπου Beneficiaries
        Beneficiary b1=new Beneficiary("b","2",4);
        Beneficiary b2=new Beneficiary("c","3",5);
        
       
       //προσθέτουμε τους Beneficiaries στη λίστα beneficiaryList
       org.beneficiaryList.add(b1);
       org.beneficiaryList.add(b2);
       
       System.out.println(); //για να αφήσουμε μια γραμμή κενή  
       
       org.listBeneficiaries();

       System.out.println();
       
       //δημιουργούμε 1 στιγμιότυπο τύπου Donator
       Donator d1=new Donator("d","4");
              
       //προσθέτουμε τον Donator στη λίστα donatorList
       org.donatorList.add(d1);
       
       org.listDonators();
       
       
       try
       {
           //δημιουργώ 3 request(αιτήματα) για τον Beneficiary που βρίσκεται στη θέση 0 στη λίστα beneficiaryList
            org.beneficiaryList.get(0).requestsList.add(1, org, 2);
            org.beneficiaryList.get(0).requestsList.add(2, org, 3);
            
            //για τον Beneficiary που βρίσκεται στη θέση 0 στη λίστα beneficiaryList φτιάχνω την receivedList
            //με τις δωρεές που έχει ήδη λάβει.
            org.beneficiaryList.get(0).receivedList.add(1, org, 1);
            org.beneficiaryList.get(0).receivedList.add(2, org, 2);
            org.beneficiaryList.get(0).receivedList.add(5, org, 5);
            
            //φτιάχνω 2 δωρεές του donator που βρίσκεται στη θέση 0 στη λίστα donatorList
            org.donatorList.get(0).offersList.add(2, org, 50);
            org.donatorList.get(0).offersList.add(3, org, 50);

       }
       catch(EntityException e)
       {
           
       }
       
          
       Menu m=new Menu();
       
       m.login(org);
        
        org.currentDonations.monitor();
        
    }
    
}

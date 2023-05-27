package organization;

import exception.BeneficiaryException;
import exception.DonatorException;
import exception.EntityException;
import java.util.*;
import user.Beneficiary;
import user.Donator;

public class Menu 
{
    Scanner sc=new Scanner(System.in);
    
    //μέθοδος που εμφανίζει το Menu του Admin
    public void adminMenu(Organization org)
    {
            do
            {
                System.out.println("\nΚαλωσήρθες Admin με όνομα "+org.getAdmin().getName()+" και τηλέφωνο "+org.getAdmin().getPhone());
                System.out.println("=====ADMIN MENU=====");
                System.out.println("Επιλέξτε μία από τις επιλογές του Menu:");
                System.out.println("1. View");
                System.out.println("2. Monitor Organization");
                System.out.println("3. Back");
                System.out.println("4. Logout");
                System.out.println("5. Exit");

                int ans=sc.nextInt();

                //αν επιλέξουμε View
                if(ans==1)
                {
                    do
                    {
                        System.out.println("\nΕπιλέξτε: ");
                        System.out.println("1: Material");
                        System.out.println("2.Service");
                        System.out.println("3. Back");

                        int ans2=sc.nextInt();
                        
                        //αν έχω επιλέξει material
                        if(ans2==1)
                        {
                            for(int i=0; i<org.entityList.size();i++)
                            {
                                if(!org.entityList.get(i).getDetails().equals("Το είδος δωρεάς είναι Service")) //δηλαδή αν είναι Material
                                {
                                    System.out.println(org.entityList.get(i).getName()+","+org.entityList.get(i).getId());
                                    
                                    //αν δεν υπάρχει το αντικείμενο στη λίστα δηλ. αν δεν δόθηκε σε προσφορά
                                    if(org.currentDonations.get(org.entityList.get(i).getId())==null) 
                                        System.out.println("Το υπόλοιπο είναι (0)");
                                    else
                                        System.out.println("Το υπόλοιπο είναι ("+org.currentDonations.get(org.entityList.get(i).getId()).getQuantity()+")");
                                }
                            }

                            System.out.println("Επιλέξτε συγκεκριμένο Material με βάση το id: ");
                            int b=sc.nextInt();

                            //εντοπίζουμε το συγκεκριμένο Material και καλούμε την συνάρτηση toString() για να εμφανιστούν
                            //όλες οι πληροφορίες του
                            System.out.println(org.entityList.get(b-1).toString());
                        }
                        
                        //αν έχω επιλέξει service
                        if(ans2==2)
                        {
                            for(int i=0;i<org.entityList.size();i++)
                            {
                                if(org.entityList.get(i).getDetails().equals("Το είδος δωρεάς είναι Service")) //δηλαδή αν είναι Service
                                {
                                    System.out.println(org.entityList.get(i).getName()+", "+org.entityList.get(i).getId());
                                    
                                    if(org.currentDonations.get(org.entityList.get(i).getId())==null)
                                        System.out.println("Το υπόλοιπο είναι (0)");
                                    else
                                        System.out.println("Το υπόλοιπο είναι ("+org.currentDonations.get(org.entityList.get(i).getId()).getQuantity()+")");
  
                                }
                            }

                            System.out.println("Επιλέξτε συγκεκριμένο Service με βάση το id: ");
                            int b=sc.nextInt();

                            //εντοπίζουμε το συγκεκριμένο Service και καλούμε την συνάρτηση toString() για να εμφανιστούν
                            //όλες οι πληροφορίες του
                            System.out.println(org.entityList.get(b-1).toString());
                        }

                        if(ans2==3)
                            break; //αν επιλέξουμε την επιλογή back τότε κάνει break το for και επιστρέφει στο μενού 

                    }while(true);  
                }

                //αν επιλέξουμε Monitor Organization
                if(ans==2)
                {
                    do
                    {
                        System.out.println("\nΕπιλέξτε:");
                        System.out.println("1. List Beneficiaries");
                        System.out.println("2. List Donators");
                        System.out.println("3. Reset Beneficiaries Lists");
                        System.out.println("4. Back");
                        
                        int ans2=sc.nextInt(); //δίνουμε επιλογή από το πληκτρολόγιο
                        
                        //εάν επιλέξουμε List Beneficiaries:
                        if(ans2==1)
                        {
                         
                                org.listBeneficiaries(); //καλεί τη μέθοδο που επιστρέφει όλους τους Beneficiaries

                                System.out.println("Επιλέξτε έναν Beneficiary με βάση το κινητό του τηλέφωνο");
                                String phone=sc.next();

                                        do
                                        {
                                            System.out.println("\nΕπιλέξτε:");
                                            System.out.println("1. Εμφάνιση δωρεών που έχει λάβει:");
                                            System.out.println("2. Άδειασμα της λίστας των δωρεών που έχει λάβει");
                                            System.out.println("3. Διαγραφή Επωφελούμενου");
                                            System.out.println("4. Back");
                                            int ans3=sc.nextInt();

                                            if(ans3==1)
                                            {
                                                for(Beneficiary m:org.beneficiaryList)
                                                {
                                                    if(m.getPhone().equals(phone))
                                                    {
                                                        m.receivedList.monitor(); //εμφανίζει την receivedList του Beneficiary
                                                        
                                                        if(m.receivedList.rdEntities.isEmpty()) //αν η  received list είναι άδεια
                                                        {
                                                            System.out.println("Ο Beneficiary δεν έχει λάβει καμία δωρεά!");
                                                        }
                                                        
                                                    }
                                                }
                                                
                                            }

                                            if(ans3==2)
                                            {
                                                
                                                for(int i=0;i<org.beneficiaryList.size();i++)
                                                    if(org.beneficiaryList.get(i).getPhone().equals(phone))
                                                    {
                                                        //το θαυμαστικό είναι το false
                                                        //αν η receivedlist του Beneficiary ΔΕΝ είναι άδεια
                                                        if(!org.beneficiaryList.get(i).receivedList.rdEntities.isEmpty())
                                                        {
                                                            //στην αρχή σβήνω τον συγκεκριμένο beneficiary από τη λίστα  beneficiaryList
                                                            //σβήνω την receivedList του συγκεκριμένου beneficiary
                                                            //προσθέτω πάλι τον συγκεκριμένο beneficiary ΞΑΝΑ στην λίστα beneficiaryList
                                                            org.beneficiaryList.remove(org.beneficiaryList.get(i));
                                                            org.beneficiaryList.get(i).receivedList.reset();
                                                            org.beneficiaryList.add(org.beneficiaryList.get(i));
                                                            System.out.println("Πραγματοποιήθηκε reset της λίστας δωρεών που έχει λάβει ο Beneficiary!");
                                                        }
                                                        else
                                                            System.out.println("O Beneficiary δεν έχει λάβει καμία δωρεά!");
                                                    }
                                            }

                                            if(ans3==3)
                                            {
                                                org.removeBeneficiary(phone);
                                                System.out.println("O Beneficiary διαγράφηκε επιτυχώς!");
                                            }

                                            if(ans3==4)
                                                break;
                                                        
                                        }while(true);
                        }
                                
                        
                        //εάν επιλέξουμε List Donators:
                        if(ans2==2)
                        {
                                org.listDonators(); //καλεί τη μέθοδο που επιστρέφει όλους τους  Donators

                                System.out.println("Επιλέξτε έναν Donator με βάση το κινητό του τηλέφωνο");
                                String p=sc.next();
                                
                                do
                                {
                                    System.out.println("\nΕπιλέξτε:");
                                    System.out.println("1. Εμφάνιση δωρεών που θέλει να προσφέρει");
                                    System.out.println("2. Διαγραφή Δωρητή");
                                    System.out.println("3. Back");

                                    int ans4=sc.nextInt();
                                    
                                    //αν επιλέξω Εμφάνιση δωρεών που θέλει να προσφέρει
                                    if(ans4==1)
                                    {
                                        for(int i=0;i<org.donatorList.size();i++)
                                            if(org.donatorList.get(i).getPhone().equals(p))
                                            {
                                                //αν η offersList του Donator είναι άδεια
                                                if(org.donatorList.get(i).offersList.rdEntities.isEmpty())
                                                    System.out.println("Δεν υπάρχουν δωρεές από τον Donator");
                                                else
                                                    org.donatorList.get(i).offersList.monitor();

                                                break;
                                            }
                                    }

                                    if(ans4==2)
                                    {
                                        org.removeDonator(p);
                                        System.out.println("O Donator διαγράφηκε επιτυχώς!");
                                    }

                                    if(ans4==3)
                                        break;


                                }while(true);
                        }
                        
                        //εάν επιλέξουμε Reset Beneficiaries Lists:
                        if(ans2==3)
                        {
                            for(int i=0;i<org.beneficiaryList.size();i++)
                            {
                                org.beneficiaryList.get(i).receivedList.reset();
                            }
                            System.out.println("Έγινε καθαρισμός της λίστας των Beneficiaries!");
                        }
                        
                        //εάν επιλέξουμε back
                        if(ans2==4)
                            break;
                                
                    }while(true);

                }
                
                //εάν επιλέξουμε Back
                if(ans==3)
                    break;
                
                //εάν επιλέξουμε Logout
                if(ans==4)
                    break;
                
                //εάν επιλέξουμε exit, τερματίζει το πρόγραμμα
                if(ans==5)
                    System.exit(0);
                    }while(true);             
    } //τέλος μεθόδου adminMenu()
            
    
    //μέθοδος που εμφανίζει το Menu του Donator, παίρνει ως όρισμα ένα τηλέφωνο και έναν οργανισμό
    public void donatorMenu(String p, Organization org) 
    {
        int id;
        Donator d;
        d=org.foundDonator(p);
        
        do
        {
            System.out.println("\nΚαλωσήρθες Donator με όνομα "+d.getName()+" και τηλέφωνο "+d.getPhone()+" του Οργανισμού: "+org.getName());
            System.out.println("=====DONATOR MENU=====");
            System.out.println("Επιλέξτε μία από τις επιλογές του Menu:");
            System.out.println("1. Add Offer");
            System.out.println("2. Show Offers");
            System.out.println("3. Commit");
            System.out.println("4. Back");
            System.out.println("5. Logout");
            System.out.println("6. Exit");

            int ans=sc.nextInt();
            
            //αν επιλέξουμε Add Offer
            if(ans==1)
            {
                do
                {
                   System.out.println("\nΕπιλέξτε: ");
                   System.out.println("1. Material");
                   System.out.println("2.Service");
                   System.out.println("3. Back");

                   int ans2=sc.nextInt(); //δίνουμε από το πληκτρολόγιο την επιλογή μας για το Menu
                   
                   //αν διαλέξουμε Material
                   if(ans2==1)
                   {
                      for(int i=0; i<org.entityList.size();i++)
                      {
                        if(!org.entityList.get(i).getDetails().equals("Το είδος δωρεάς είναι Service")) //δηλαδή αν είναι Material
                            System.out.println("Όνομα: "+org.entityList.get(i).getName()+",id: "+org.entityList.get(i).getId()); 
                      
                        if(org.currentDonations.get(org.entityList.get(i).getId())==null)
                            System.out.println("Το υπόλοιπο είναι (0)");
                        else
                            System.out.println("Το υπόλοιπο είναι ("+org.currentDonations.get(org.entityList.get(i).getId()).getQuantity()+")");
                      }
                      
                      System.out.println("Επιλέξτε συγκεκριμένο Material δίνοντας το id του: ");
                      int b=sc.nextInt(); //εντοπίζουμε το συγκεκριμένο Material με βάση το id του και καλούμε την συνάρτηση toString() 
                      //για να εμφανιστούν όλες οι πληροφορίες του
                      
                      System.out.println(org.entityList.get(b-1).toString());
                      
                      System.out.println("Θέλετε να προσφέρετε ποσότητα για το αντικείμενο "+org.entityList.get(b-1).getName()+"?");
                      System.out.println("Παρακαλώ, πατήστε 'y' εάν θέλετε να προσφέρετε ποσότητα, αλλιώς πατήστε 'n':");
                      String s=sc.next(); //δίνουμε από το πληκτρολόγιο "y" or "n"
                      
                      if(s.equals("y") || s.equals("Y"))
                      {
                          System.out.println("Παρακαλώ εισάγεται την ποσότητα: ");
                          double q=sc.nextDouble();
                          
                          for(int i=0;i<org.donatorList.size();i++)
                              if(org.donatorList.get(i).getPhone().equals(p))
                              {
                                    try
                                    {
                                      org.donatorList.get(i).offersList.add(b, org, q);
                                      System.out.println("Η δωρεά στο αντικείμενο "+org.donatorList.get(i).offersList.get(b).getEntity().getName()+" είναι: "+org.donatorList.get(i).offersList.get(b).getQuantity());
                                    }
                                    catch(EntityException e)
                                    {
                                        System.err.println(e.getMessage()); //με το err θα τυπώσει το μήνυμα με κόκκινα γράμματα
                                    }
                              }
                      }
                    }
                   
                   //αν επιλέξουμε Service
                   if(ans2==2)
                   {
                       for(int i=0; i<org.entityList.size();i++)
                       {
                            if(org.entityList.get(i).getDetails().equals("Το είδος δωρεάς είναι Service")) //δηλαδή αν είναι Service
                                System.out.println(org.entityList.get(i).getName()+","+org.entityList.get(i).getId());
                            
                            //αν το συγκεκριμένο στιγμιότυπο δεν υπάρχει στην currentDonations
                            if(org.currentDonations.get(org.entityList.get(i).getId())==null)
                            System.out.println("Το υπόλοιπο είναι (0)");
                        else
                            System.out.println("Το υπόλοιπο είναι ("+org.currentDonations.get(org.entityList.get(i).getId()).getQuantity()+")");
                       }
                       
                      System.out.println("Επιλέξτε συγκεκριμένο Service δίνοντας το id του: ");
                      int k=sc.nextInt();
                      
                      //εντοπίζουμε το συγκεκριμένο Service και καλούμε την συνάρτηση toString() για να εμφανιστούν
                      //όλες οι πληροφορίες του
                      System.out.println(org.entityList.get(k-1).toString());
                      
                      System.out.println("Θέλετε να προσφέρετε επιπλέον ώρες για την υπηρεσία "+org.entityList.get(k-1).getName()+"?");
                      System.out.println("Παρακαλώ, πατήστε 'y' εάν θέλετε να προσφέρετε επιπλέον ώρες, αλλιώς πατήστε 'n':");
                      String s=sc.next(); //δίνουμε από το πληκτρολόγιο "y" or "n"
                      
                      if(s.equals("y") || s.equals("Y"))
                      {
                          System.out.println("Παρακαλώ εισάγεται τις ώρες υπηρεσίας: ");
                          double h=sc.nextDouble();
                          
                          for(int i=0;i<org.donatorList.size();i++)
                              if(org.donatorList.get(i).getPhone().equals(p))
                              {
                                    try
                                    {
                                      org.donatorList.get(i).offersList.add(k, org, h);
                                      System.out.println("Η δωρεά στο αντικείμενο "+org.donatorList.get(i).offersList.get(k).getEntity().getName()+" είναι: "+org.donatorList.get(i).offersList.get(k).getQuantity()+" ώρες υπηρεσίας");
                                    }
                                    catch(EntityException e)
                                    {
                                        System.err.println(e.getMessage());
                                    }
                               }
                       }
                    }
                      
                   
                   //αν διαλέξουμε back
                   if(ans2==3)
                       break;
                
                }while(true);
            }
            
            //αν επιλέξουμε Show Offers
            if(ans==2)
            {
                do
                {
                   
                   for(int i=0; i<org.donatorList.size();i++)
                        if(org.donatorList.get(i).getPhone().equals(p)) //αν εντοπιστεί ο Donator
                        {
                            //αν η λίστα offersList του Donator είναι άδεια
                            if(org.donatorList.get(i).offersList.rdEntities.isEmpty())
                                System.out.println("Δεν υπάρχουν δωρεές από τον Donator "+org.donatorList.get(i).getName()+" στη λίστα!");
                            else
                            {
                                org.donatorList.get(i).offersList.monitor(); //με τη χρήση της συνάρτησης
                                //monitor() εμφανίζουμε τη λίστα των ειδών που προσφέρει ο Donator
                            }
                        }
                   
                   System.out.println("\nΕπιλέξτε: ");
                   System.out.println("1: Επιλογή συγκεκριμένης δωρεάς");
                   System.out.println("2. Καθαρισμός όλων των παροχών");
                   System.out.println("3. Commit");
                   System.out.println("4. Back");
                   
                   int ans2=sc.nextInt(); //δίνουμε από το πληκτρολόγιο την επιλογή μας για το Menu
                   
                   //αν επιλέξουμε Επιλογή συγκεκριμένης δωρεάς
                   if(ans2==1)
                   {
                      System.out.println("Επιλέξτε συγκεκριμένη δωρεά δίνοντας το id του αντικειμένου:");
                      id=sc.nextInt(); //δίνουμε από το πληκτρολόγιο το id του αντικειμένου
                      
                      System.out.println("Θέλετε να αλλάξετε την ποσότητα της παροχής με id: "+id+" ή θέλετε να κάνετε διαγραφή παροχής?");
                      System.out.println("Πατήστε (1) για αλλαγή ποσότητας ή (2) για διαγραφή της παροχής");
                      int k=sc.nextInt();
                      
                      for(int i=0;i<org.donatorList.size();i++)
                          if(org.donatorList.get(i).getPhone().equals(p))
                          {
                              if(k==1)
                              {
                                  System.out.println("Παρακαλώ δώστε νέα ποσότητα:");
                                  double q=sc.nextDouble(); //νέα ποσότητα που την δίνουμε από το πληκτρολόγιο
                                  
                                  org.donatorList.get(i).offersList.modify(id, q); //με τη χρήση της μεθόδου modify αλλάζουμε τη ποσότητα
                                  
                                  System.out.println("H ποσότητα άλλαξε επιτυχώς και είναι: "+q);
                              }
                              
                              if(k==2)
                              {
                                  org.donatorList.get(i).offersList.remove(id); //διαγραφή παροχής
                                  System.out.println("Η διαγραφή  του αντικειμένου "+org.entityList.get(id).getName()+" ολοκληρώθηκε!");
                                  
                                  if(org.donatorList.get(i).offersList.rdEntities.isEmpty())
                                      break;
                              }
                          }
                   }

                   //αν επιλέξουμε Καθαρισμό όλων των παροχών του Donator                   
                   if(ans2==2)
                   {
                       for(int i=0;i<org.donatorList.size();i++)
                           if(org.donatorList.get(i).getPhone().equals(p))
                           {
                               //κάνουμε χρήση της μεθόδου reset() που βρίσκεται στη κλάση RequestDonationList
                               org.donatorList.get(i).offersList.reset();
                               break;
                           }
                       
                       System.out.println("Όλες οι δωρεές του Donator διαγράφηκαν επιτυχώς!");
                   }
                   
                   //αν επιλέξουμε Commit                   
                   if(ans2==3)
                   {
                       for(int i=0;i<org.donatorList.size();i++)
                           if(org.donatorList.get(i).getPhone().equals(p))
                               org.donatorList.get(i).offersList.commit(org);
                               
                        System.out.println("Οι δωρεές ελέγχθηκαν και στη συνέχεια προστέθηκαν με επιτυχία");
                   }
                   
                   //αν επιλέξουμε back
                   if(ans2==4)
                       break;
                    
                }while(true);
            }
            
            //αν επιλέξουμε Commit
            if(ans==3)
            {
                for(int i=0;i<org.donatorList.size();i++)
                    if(org.donatorList.get(i).getPhone().equals(p))
                    {
                        
                        if(org.donatorList.get(i).offersList.rdEntities.isEmpty())
                            System.out.println("Δεν υπάρχουν δωρεές από τον Donator");
                        
                        org.donatorList.get(i).offersList.commit(org);
                        
                        if(org.donatorList.get(i).offersList.rdEntities.isEmpty())
                            System.out.println("Οι δωρεές προστέθηκαν επιτυχώς!");
                    }
            }
            
            //αν επιλέξουμε Back
            if(ans==4)
                break;
            
            //αν επιλέξουμε Logout
            if(ans==5)
                break;
            
            //αν επιλέξουμε Exit, τερματίζεται το πρόγραμμα
            if(ans==6)
                System.exit(0);
            
            
        }while(true);
                    
    } //τέλος μεθόδου donatorMenu()
    
    
    public void beneficiaryMenu(String p, Organization org)
    {
        Beneficiary b;
        b=org.foundBeneficiary(p);

        
        do
        {
            System.out.println("\nΚαλωσήρθες Beneficiary με όνομα "+b.getName()+" και τηλέφωνο "+p+" του Οργανισμού: "+org.getName());
            System.out.println("=====BENEFICIARY MENU=====");
            System.out.println("Επιλέξτε μία από τις επιλογές του Menu:");
            System.out.println("1. Add Request");
            System.out.println("2. Show Requests");
            System.out.println("3. Commit στη λίστα των Request");
            System.out.println("4. Back");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            
            int ans=sc.nextInt(); //δίνουμε επιλογή του Μενού από το πληκτρολόγιο
            
            //αν επιλέξουμε Add Request
            if(ans==1)
            {
               do
                {
                   System.out.println("\nΕπιλέξτε: ");
                   System.out.println("1: Material");
                   System.out.println("2.Service");
                   System.out.println("3. Back");

                   int ans2=sc.nextInt(); //δίνουμε από το πληκτρολόγιο την επιλογή μας για το Menu
                   
                   //αν επιλέξουμε Material
                   if(ans2==1)
                   {
                       for(int i=0; i<org.currentDonations.rdEntities.size();i++)
                         if(!org.currentDonations.rdEntities.get(i).getEntity().getDetails().equals("Το είδος δωρεάς είναι Service")) //δηλαδή αν είναι Material
                            System.out.println(org.currentDonations.rdEntities.get(i).getEntity().getName()+","+org.currentDonations.rdEntities.get(i).getEntity().getId()+" και υπόλοιπο: ("+org.currentDonations.rdEntities.get(i).getQuantity()+")");
                       
                      System.out.println("Επιλέξτε συγκεκριμένο Material δίνοντας το id του: ");
                      int k=sc.nextInt();
                      
                      //εντοπίζουμε το συγκεκριμένο Material και καλούμε την συνάρτηση toString() για να εμφανιστούν
                      //όλες οι πληροφορίες του
                      System.out.println(org.entityList.get(k-1).toString());
                      
                      System.out.println("Θέλετε να λάβετε το συγκεκριμένο αντικείμενο?");
                      System.out.println("Παρακαλώ, πατήστε 'y' εάν θέλετε να λάβετε το αντικείμενο, αλλιώς πατήστε 'n':");
                      String s=sc.next(); //δίνουμε από το πληκτρολόγιο "y" or "n"
                      
                      if(s.equals("y") || s.equals("Y"))
                      {
                          System.out.println("Παρακαλώ εισάγεται την ποσότητα που θέλετε να λάβετε: ");
                          double q=sc.nextDouble();
                          
                          
                          for(int i=0;i<org.beneficiaryList.size();i++)
                              if(org.beneficiaryList.get(i).getPhone().equals(p))
                              {
                                    //κάνουμε χρήση της μεθόδου add() της κλάσης Requests  
                                    org.beneficiaryList.get(i).requestsList.add(k, org, q, org.beneficiaryList.get(i).getNoPersons());
                                    
                                    //αν το συγκεκριμένο στιγμιότυπο από τη λίστα rdEntities υπάρχει
                                    //στην requestsList του συγκεκριμένου beneficiary
                                    if(org.beneficiaryList.get(i).requestsList.get(k)!=null) 
                                        System.out.println("Η νέα ποσότητα του Material "+org.currentDonations.get(k).getEntity().getName()+" είναι "+org.beneficiaryList.get(i).requestsList.get(k).getQuantity());
                               }
                       }
                    }
                      
                   //αν επιλέξουμε Service
                   if(ans2==2)
                   {
                       for(int i=0; i<org.currentDonations.rdEntities.size();i++)
                       {
                            if(org.currentDonations.rdEntities.get(i).getEntity().getDetails().equals("Το είδος δωρεάς είναι Service")) //δηλαδή αν είναι Service
                                System.out.println(org.currentDonations.rdEntities.get(i).getEntity().getName()+","+org.currentDonations.rdEntities.get(i).getEntity().getId()+" και υπόλοιπο ("+org.currentDonations.rdEntities.get(i).getQuantity()+")");
                       }

                      System.out.println("Επιλέξτε συγκεκριμένο Service δίνοντας το id του: ");
                      int l=sc.nextInt();
                      
                      //εντοπίζουμε το συγκεκριμένο Service και καλούμε την συνάρτηση toString() για να εμφανιστούν
                      //όλες οι πληροφορίες του
                      System.out.println(org.entityList.get(l-1).toString());
                      
                      System.out.println("Θέλετε να λάβετε τη συγκεκριμένη υπηρεσία?");
                      System.out.println("Παρακαλώ, πατήστε 'y' εάν θέλετε να λάβετε την υπηρεσία, αλλιώς πατήστε 'n':");
                      String str=sc.next(); //δίνουμε από το πληκτρολόγιο "y" or "n"
                      
                      if(str.equals("y") || str.equals("Y"))
                      {
                          System.out.println("Παρακαλώ εισάγεται τις ώρες υπηρεσίας που θα θέλατε: ");
                          double h=sc.nextDouble();
                          
                          
                          for(int i=0;i<org.beneficiaryList.size();i++)
                              if(org.beneficiaryList.get(i).getPhone().equals(p))
                              {
                                    org.beneficiaryList.get(i).requestsList.add(l, org, h, org.beneficiaryList.get(i).getNoPersons());

                                    if(org.beneficiaryList.get(i).requestsList.get(l)!=null)
                                        System.out.println("Η νέα ποσότητα του Service "+org.currentDonations.get(l).getEntity().getName()+" είναι "+org.beneficiaryList.get(i).requestsList.get(l).getQuantity()+" ώρες");

                              }
                      }
                   }
                   
                   //αν επιλέξουμε Back
                   if(ans2==3)
                       break;
                   
                }while(true);
             }
            
            //αν επιλέξουμε Show Requests
            if(ans==2)
            {
                do
                {
                   
                   for(int i=0; i<org.beneficiaryList.size();i++)
                   {
                        if(org.beneficiaryList.get(i).getPhone().equals(p)) //αν εντοπιστεί ο Beneficiary
                        {
                            //αν η λίστα requestsList του Beneficiary είναι άδεια
                            if(org.beneficiaryList.get(i).requestsList.rdEntities.isEmpty())
                            {
                                System.out.println("Δεν υπάρχουν requests ακόμα στη λίστα!");
                                break;
                            }
                            else
                            {
                                org.beneficiaryList.get(i).requestsList.monitor(); //με τη χρήση της συνάρτησης
                                //monitor() εμφανίζουμε τη λίστα με τα Requests του Beneficiary 
                            }
                                
                        }
                   }
                   
                   System.out.println("\nΕπιλέξτε: ");
                   System.out.println("1: Επιλογή συγκεκριμένης δωρεάς από τη λίστα με τα Request");
                   System.out.println("2. Καθαρισμός όλων των παροχών");
                   System.out.println("3. Commit");
                   System.out.println("4. Back");
                   
                   int ans2=sc.nextInt(); //δίνουμε από το πληκτρολόγιο την επιλογή μας για το Menu
                   
                   //αν επιλέξουμε Επιλογή συγκεκριμένης δωρεάς
                   if(ans2==1)
                   {
                      System.out.println("Επιλέξτε συγκεκριμένη δωρεά  από το RequestList δίνοντας το id του αντικειμένου:");
                      int id=sc.nextInt();
                      
                      System.out.println("Θέλετε να αλλάξετε την ποσότητα της παροχής με id: "+id+" ή θέλετε να κάνετε διαγραφή παροχής?");
                      System.out.println("Πατήστε (1) για αλλαγή ποσότητας ή (2) για διαγραφή της παροχής");
                      int n=sc.nextInt();
                      
                      for(int i=0;i<org.beneficiaryList.size();i++)
                          if(org.beneficiaryList.get(i).getPhone().equals(p))
                          {
                              if(n==1)
                              {
                                  System.out.println("Παρακαλώ δώστε νέα ποσότητα για την παροχή με id: "+id);
                                  double q=sc.nextDouble(); //νέα ποσότητα που την δίνουμε από το πληκτρολόγιο
                                  
                                  org.beneficiaryList.get(i).requestsList.modify(id, q); //με τη χρήση της μεθόδου modify αλλάζουμε τη ποσότητα
                                  System.out.println("Η ποσότητα του αντικειμένου "+org.currentDonations.get(id).getEntity().getName()+" είναι: "+q);
                              }
                              
                              if(n==2)
                              {
                                  org.beneficiaryList.get(i).requestsList.remove(id); //διαγραφή παροχής
                                  System.out.println("Το "+org.entityList.get(id).getName()+" διαγράφηκε επιτυχώς από τη λίστα με τα Request!");
                                  
                                  if(org.beneficiaryList.get(i).requestsList.rdEntities.isEmpty())
                                      break;
                              }
                          }
                   }

                   //αν επιλέξουμε Καθαρισμό όλων των requests του Beneficiary                   
                   if(ans2==2)
                   {
                       for(int i=0;i<org.beneficiaryList.size();i++)
                           if(org.beneficiaryList.get(i).getPhone().equals(p))
                           {
                               org.beneficiaryList.get(i).requestsList.reset();
                               
                               System.out.println("Έγινε καθαρισμός της λίστας με τα Request του Beneficiary!");
                               break;
                           }
                   }
                   
                   //αν επιλέξουμε Commit                   
                   if(ans2==3)
                   {
                       for(int i=0;i<org.beneficiaryList.size();i++)
                           if(org.beneficiaryList.get(i).getPhone().equals(p))
                           {
                                    org.beneficiaryList.get(i).requestsList.commit(org, org.beneficiaryList.get(i));
                                    
                                    if(org.beneficiaryList.get(i).requestsList.rdEntities.isEmpty())
                                        System.out.println("Οι δωρεές εγκρίθηκαν επιτυχώς!");
                           }
                   }
                   
                   //αν επιλέξουμε back
                   if(ans2==4)
                       break;
                    
                }while(true);
            }
            
            
            //αν επιλέξουμε Commit
            if(ans==3)
            {
                for(int i=0;i<org.beneficiaryList.size();i++)
                    if(org.beneficiaryList.get(i).getPhone().equals(p))
                    {
                        if(org.beneficiaryList.get(i).requestsList.rdEntities.isEmpty())
                        {
                            System.out.println("Η λίστα με τα Request δεν περιέχει δωρεές για να πάρει ο Beneficiary!");
                            break;
                        }
                        
                        org.beneficiaryList.get(i).requestsList.commit(org, org.beneficiaryList.get(i));
                        
                        if(org.beneficiaryList.get(i).requestsList.rdEntities.isEmpty())
                            System.out.println("Οι δωρεές εγκρίθηκαν επιτυχώς!");
                    }
            }
            
            //αν επιλέξουμε Back
            if(ans==4)
                break;
            
            //αν επιλέξουμε Logout
            if(ans==5)
                break;
            
            //αν επιλέξουμε Exit, τερματίζεται το πρόγραμμα
            if(ans==6)
                System.exit(0);
            
            
        }while(true);
    
    }
    
    //μέθοδος που κάνει το Login του χρήστη
    public void login(Organization org)  
    {
        do
        {
            boolean f=false; //έστω ότι δεν είναι εγγεγραμμένος

            System.out.println("\nΠαρακαλώ εισάγεται τον αριθμό του κινητού σας τηλεφώνου :");
            String phone=sc.next();

            //Εξετάζουμε αν είναι Admin
            if(phone.equals(org.getAdmin().getPhone()))
            {
                f=true; //άρα είναι εγγεγραμένος
                adminMenu(org); //καλούμε τη συνάρτηση adminMenu()
            }
            
            //Εξετάζουμε αν είναι Donator
            for(int i=0;i<org.donatorList.size();i++)
                if(org.donatorList.get(i).getPhone().equals(phone))
                {
                    f=true; //άρα είναι εγγεγραμένος
                    donatorMenu(phone,org); //καλούμε τη συνάρτηση donatorMenu()
                }
            
            //Εξετάζουμε αν είναι Beneficiary
            for(int i=0;i<org.beneficiaryList.size();i++)
                if(org.beneficiaryList.get(i).getPhone().equals(phone))
                {
                    f=true; //άρα είναι εγγεγραμένος
                    beneficiaryMenu(phone,org); //καλούμε τη συνάρτηση beneficiaryMenu()
                }

            //Αν δεν είναι Admin, ούτε Donator, ούτε Beneficiary
            if(f==false)
            {
                System.out.println("Δεν είστε εγγεγραμμένος στο σύστημα. Θέλετε να πραγματοποιήσετε νέα εγγραφή?");
                System.out.println("Για εγγραφή πατήστε 'y', αλλιώς πατήστε 'n'");
                String str=sc.next();
                
                if(str.equals("y") || str.equals("Y"))
                {
                    System.out.println("Για εγγραφή ως δωρητής πατήστε 'd', ενώ για εγγραφή ως επωφελούμενος πατήστε 'b':");
                    String str2=sc.next();
                    
                    if(str2.equals("d") || str2.equals("D"))
                    {
                        try
                        {
                            org.insertDonator();
                            System.out.println("Η εγγραφή πραγματοποιήθηκε επιτυχώς!");
                        }
                        catch(DonatorException e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    else 
                        if(str2.equals("b") || str2.equals("B"))
                        {
                            try
                            {
                               org.insertBeneficiary();
                               System.out.println("Η εγγραφή πραγματοποιήθηκε επιτυχώς!");
                            }
                            catch(BeneficiaryException e)
                            {
                                System.out.println(e.getMessage());
                            }
                        }
                }
            }
            
        }while(true);
        
    }
     
}




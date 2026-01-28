import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VirtualPetRunner
{
// Prints menu and returns user choice
 public static int getChoice(Scanner input)
 {
   int selection = 0;
   while (selection < 1 || selection > 4)
   {
     System.out.println("------Virtual Pet Menu------");
     System.out.println("1. Get Pet Information");
     System.out.println("2. Feed Pet" );
     System.out.println("3. Play with Pet" );
     System.out.println("4. Quit" );
     System.out.print("Enter your choice ..... ");
     selection = input.nextInt();
   }
   return selection;
  }
    
  // Displays a picture of the pet
  public static void printPet(String emo)
  {
    System.out.println(" /\\_/\\");  
    System.out.println("( o.o )"); 
    System.out.println(" > " + emo + " <");
  }
    
  public static void main(String[] args) 
  {
    // CHANGE THIS VARIABLE VALUE TO TEST AT A DIFFERENT SPEED
    final int INTERVAL_IN_SECONDS = 10;
      
    // Sets up Scanner for user input
    Scanner input = new Scanner(System.in);
        
    VirtualPet myPet = new VirtualPet("67 pet");
       
    
    // Sets up a ScheduledExecutorService object that will call updateStatus
    // every 1 minute.
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    scheduler.scheduleAtFixedRate(() -> { myPet.updateStatus(); },  
             INTERVAL_IN_SECONDS, INTERVAL_IN_SECONDS, TimeUnit.SECONDS);

    System.out.println(myPet);
    printPet("ᵔ");
        
    int choice = getChoice(input);
    while (choice != 4)
    {
      if (choice == 1)
      {
        System.out.println(myPet);
      }
      else if (choice == 2)
      {   
        if (myPet.getEnergyLevel() < 100) {
        myPet.feed();
        System.out.println("\n\nYou have fed " + myPet.getName());
      } else {
          System.out.println("\n\n" + myPet.getName() + " is already at maximum energy!");
      }
      }
      else if (choice == 3)
      {  
        if (myPet.getEnergyLevel() > 0 && myPet.getHappinessLevel() < 100) {
        myPet.play();
        System.out.println("\n\nYou have played with " + myPet.getName());                } else {
          System.out.println("\n\n" + myPet.getName()+ " isn't heavy enough and/or is already at maximum happiness!")
        }
       }
        remove comment for part B
       if (myPet.getEnergyLevel() >= 5  && myPet.getHappinessLevel() >= 5)
       {
         printPet("ᵕ");
       }
       else
       {
         printPet("ᵔ");
       }
           
       System.out.println(myPet.getName().toUpperCase());
       choice = getChoice(input);
     }
     scheduler.shutdown();
  }
}

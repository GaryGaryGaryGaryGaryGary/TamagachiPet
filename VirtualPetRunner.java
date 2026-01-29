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
     System.out.println("2. Feed Pet (Weight++, Energy++)" );
     System.out.println("3. Play with Pet (Happiness++, Weight--, Energy--)" );
     System.out.println("4. Quit" );
     System.out.println("Enter your choice ..... ");
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
    System.out.println("Welcome to the Tamagachi Pet Game! What would you like to name your pet?");
    VirtualPet myPet = new VirtualPet(input.nextLine());
    System.out.println("Good Choice! Your pet is now named: " + myPet.getName());
    
    // Sets up a ScheduledExecutorService object that will call updateStatus
    // every 1 minute.
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    scheduler.scheduleAtFixedRate(() -> { myPet.updateStatus(); System.out.println("("+myPet.getName()+" has aged...)"); },  
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
          String foodName;
          int foodEnergy;
          int foodHappiness;
          int foodWeight;
        System.out.println("What would you like to feed " + myPet.getName() + "?");
        foodName = input.nextLine();
        System.out.println("How much energy does it give?");
        foodEnergy = input.nextint();
        myPet.feed(foodName, foodEnergy, foodHappiness, foodWeight);
        System.out.println("\n\nYou have fed " + myPet.getName());
      } else {
          System.out.println("\n\n" + myPet.getName() + " Cannot eat anymore!");
      }
      }
      else if (choice == 3)
      {  
        if (myPet.getEnergyLevel() > 0 && myPet.getHappinessLevel() < 100 && myPet.getWeight()>0) {
        myPet.play();
        System.out.println("\n\nYou have played with " + myPet.getName());                } else {
          System.out.println("\n\n" + myPet.getName()+ " cannot play!");
        }
       }
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

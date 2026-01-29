public class VirtualPet { 
    private String name;
  
    private int happiness = 0;
    private int energy = 0;
    private int weight = 5;
    private int ageYears = 0;
    private int ageMonths = 0;
    
    public VirtualPet(String n) {
        name = n;
    }

    public String getName() {
      return name;
    }

    public String toString() {
       return name +"'s Information: \n Energy: "+ energy +" \n Happiness: "+happiness +" \n Weight: "+ weight + "g \n Age: " + ageMonths+" months and " + ageYears + " years";
      
    }
    
    public void feed(String n, int e, int h, int w) 
    {
      if (e > (100-energy)) {
        e = 100-energy;
      }
      if (h > (100-happiness)) {
        h = 100-happiness;
      }
      energy += e;
      happiness += h;
      weight += w;
      
    }
    
    public int getEnergyLevel() {
    	return energy;
    }

    public int getWeight() {
    	return weight;
    }
    
    public int getHappinessLevel() {
    	return happiness;
    }

    public void play() {
      happiness++;
      weight--;
      energy--;
    }

    public void updateStatus() {
      if (energy > 0) {
        energy--;
      } 
      if (happiness > 0) {
        happiness--;
      }
      if (ageMonths < 12) {
        ageMonths++;
      } else {
        ageYears++;
        ageMonths = 0;
      }
    }
  }
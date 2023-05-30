/**
 * Class which is used to store all information of quokkas
 * @author : Zecheng Pan
 * @version : 1.0.0
 */
public class Quokka
{
    private int foodConsume;
    private int foodIntake;
    private int foodStore;
    private int foodSupply;
    private boolean hasBaby;
    private boolean hasNewBorn;
    private String idCode;
    private boolean isAlive;
    public int noFoodDay;
    private int starveDay;

    /**
     * Default consturctor which creats the object of class Quokka
     */
    public Quokka()
    {
        foodConsume = 0;
        foodIntake = 0;
        foodStore = 0;
        foodSupply = 0;
        hasBaby = false;
        hasNewBorn = false;
        idCode = "";
        isAlive = true;
        noFoodDay = 0;
        starveDay = 0;
    }

    /**
     * Non-default constructor which creates the objects of class Quokka
     * @param foodConsume   an integer representing the consumed food bags of a quokka in a day
     * @param foodIntake    an integer representing the food bags a quokka should intake each day
     * @param foodStore     an integer representing the stored food of a quokka
     * @param foodSupply    an integer representing the food bags a quokka received in a day
     * @param hasBaby       a boolean representing the if a quokka has baby
     * @param hasNewBorn    a boolean representing if a quokka give birth in a day
     * @param idCode        a string representing the idCode of a quokka
     * @param isAlive       a boolean representing if a quokka is alive
     * @param noFoodDay     an integer representing the day a quokka has no food eat 
     * @param starveDay     an integer representing the day a quokka has no enough food eat
     */
    public Quokka(int foodConsume, int foodIntake, int foodStore, int foodSupply, boolean hasBaby, boolean hasNewBorn, String idCode, boolean isAlive, int noFoodDay, int starveDay)
    {
        this.foodConsume = foodConsume;
        this.foodIntake = foodIntake;
        this.foodStore = foodStore;
        this.foodSupply = foodSupply;
        this.hasBaby = hasBaby;
        this.hasNewBorn = hasNewBorn;
        this.idCode = idCode;
        this.isAlive = isAlive;
        this.noFoodDay = noFoodDay;
        this.starveDay = starveDay;
    }

    /**
     * Check if a quokka give birth in a day
     */
    public void checkBirth()
    {
        int randNum = RandomNum.generateRandomNum(100, 1);
        // The quokka has new baby will not have new quokka again
        if (hasNewBorn)
            hasNewBorn = false;
        // The situation that only a alive quokka without baby have 5% to have a baby each day
        if ((!hasBaby) && isAlive && randNum <= 5)
        {
            hasBaby = true;
            hasNewBorn = true;
        }
    }
    
    /**
     * Set the food bags a quokka should eat a day according to if it has baby
     */
    public void checkFoodIntake()
    {
        if (hasBaby)
            foodIntake = 3;
        else
            foodIntake = 2;
    }
    
    /**
     * Check if a quokka has baby
     */
    public void checkHasBaby()
    {
        int randNum = RandomNum.generateRandomNum(100, 1);
        // 20% a quokka has baby at the start of simulation
        if (randNum <= 20)
            hasBaby = true;
        else
            hasBaby = false;
    }

    /**
     * Set the initial food bags a quokka can get at start of simulation according to if it has baby
     */
    public void checkInitFood()
    {
        if (getHasBaby())
            setFoodStore(3);
        else
            setFoodStore(2);
    }

    /**
     * Manage the food change and quokka status change
     */
    public void checkQuokkaFoodStatus()
    {
        // The food quokka get each day is added to the quokka's stored food first
        foodStore += foodSupply;
        // The situation that quokka don't have stored food and don't get any food in a day
        if (foodStore == 0)
        {
            noFoodDay += 1;
            starveDay += 1;
            // The situation that if quokka does not eat food for two days it die  
            if (noFoodDay == 2)
                isAlive = false;
        }
        // The situation that quokka have stored food
        else
        {
            noFoodDay = 0;
            // The situation that quokka eat enough food
            if ((foodStore - foodIntake) > 0)
            {
                foodStore -= foodIntake;
                foodConsume = foodIntake;
            } 
            // The situation that quokka don't eat enough food
            else
            {
                foodConsume = foodStore;
                foodStore = 0;
                starveDay += 1;
            } 
        }

        // If the quokka do not each enough food for five days, it dies
        if (starveDay >= 5)
            isAlive = false;
    }

    /**
     * Display Information of a quokka
     */
    public String display()
    {
        return "ID: " + idCode + "\n The status of this Quokka:\nIf this quokka alive? " + isAlive + "\nIf it has baby? " + hasBaby + "\nThe food it get today: " + foodSupply + 
        "\nThe food it eat today" + foodIntake + "\nThe food it store today: " + foodStore + "\nHow many days it doesn't eat any food: " + noFoodDay + "\nThe strive day it suffers: " + starveDay;
    }

    /**
     * Accessor method to get the consumed food
     * @return foodConsume    An integer representing the consumed food
     */
    public int getFoodConsume()
    {
        return foodConsume;
    }
    
    /**
     * Accessor method to get the food a quokka should intake in a day
     * @return foodIntake     An integer representing the food a quokka should intake a day
     */
    public int getFoodIntake()
    {
        return foodIntake;
    }

    /**
     * Accessor method to get the stored food
     * @return foodStore      An integer representing the stored food
     */
    public int getFoodStore()
    {
        return foodStore;
    }
    
    /**
     * Accessor method to get the food that a quokko received in a day
     * @return foodSupply     An integer representing the food that a quokko received in a day
     */
    public int getFoodSupply()
    {
        return foodSupply;
    }

    /**
     * Accessor method to get the status if a quokka has baby
     * @return hasBaby        A boolean representing the status if a quokka has baby
     */
    public boolean getHasBaby()
    {
        return hasBaby;
    }

    /**
     * Accessor method to get the status if a quokka give birth
     * @return hasNewBorn     A boolean representing the status if a quokka give birth
     */
    public boolean getHasNewBorn()
    {
        return hasNewBorn;
    }

    /**
     * Accessor method to get the id code of a quokka
     * @return idCode         A string representing the id code of a quokka
     */
    public String getIdCode()
    {
        return idCode;
    }

    /**
     * Accessor method to get the status if a quokka is alive
     * @return isAlive        A boolean representing the status if a quokka is alive
     */
    public boolean getIsAlive()
    {
        return isAlive;
    }

    /**
     * Accessor method to get the number of day a quokka has no food eat
     * @return noFoodDay         An integer representing the number of day a quokka has no food eat
     */
    public int getNoFoodDay()
    {
        return noFoodDay;
    }

    /**
     * Accessor method to get the number of day a quokka has no enough food eat
     * @return starveDay      An integer representing the number of day a quokka has no enough food eat
     */
    public int getStarveDay()
    {
        return starveDay;
    }

    /**
     * Mutator method to set the consumed food
     * @param foodConsume     An integer representing the consumed food
     */
    public void setFoodConsume(int foodConsume)
    {
        if (foodConsume < 0)
            System.out.println("Invalid foodConsume!");
        else
            this.foodConsume = foodConsume;
    }
    
    /**
     * Mutator method to set the food a quokka should intake in a day
     * @param foodIntake     An integer representing the food a quokka should intake in a day
     */
    public void setFoodIntake(int foodIntake)
    {
        if (foodIntake < 0)
            System.out.println("Invalid foodIntake!");
        else
            this.foodIntake = foodIntake;
    }

    /**
     * Mutator method to set the stored food
     * @param foodStore     An integer representing the stored food
     */
    public void setFoodStore(int foodStore)
    {
        if (foodStore < 0)
            System.out.println("Invalid foodStore!");
        else
            this.foodStore = foodStore;
    }
    
    /**
     * Mutator method to set the food that a quokko received in a day
     * @param foodSupply     An integer representing the food that a quokko received in a day
     */
    public void setFoodSupply(int foodSupply)
    {
        if (foodSupply < 0)
            System.out.println("Invalid foodSupply!");
        else
            this.foodSupply = foodSupply;
    }

    /**
     * Mutator method to set the status if a quokka has baby
     * @param hasBaby        A boolean representing the status if a quokka has baby
     */
    public void setHasBaby(boolean hasBaby)
    {
        this.hasBaby = hasBaby;
    }

    /**
     * Mutator method to set the status if a quokka give birth
     * @param hasNewBorn     A boolean representing the status if a quokka give birth
     */
    public void setHasNewBorn(boolean hasNewBorn)
    {
        this.hasNewBorn = hasNewBorn;
    }

    /**
     * Mutator method to set the id code of a quokka
     * @param idCode         A string representing the id code of a quokka
     */
    public void setIdCode(String idCode)
    {
        for (int index = 0; index < idCode.length(); index ++)
        {
            char c = idCode.charAt(0);
            if (c != 'Q')
                System.out.println("Invalid idCode!");
            else
                this.idCode = idCode;
        }
    }

    /**
     * Mutator method to set the status if a quokka is alive
     * @param isAlive        A boolean representing the status if a quokka is alive
     */
    public void setIsAlive(boolean isAlive)
    {
        this.isAlive = isAlive;
    }

    /**
     * Mutator method to set the number of day a quokka has no food eat
     * @param noFoodDay      An integer representing the number of day a quokka has no food eat
     */
    public void setNoFoodDay(int noFoodDay)
    {
        if (noFoodDay < 0)
            System.out.println("Invalid noFoodDay!");
        else
            this.noFoodDay = noFoodDay;
    }

    /**
     * Mutator method to set the number of day a quokka has no enough food eat
     * @param starveDay      An integer representing the number of day a quokka has no enough food eat
     */
    public void setStarveDay(int starveDay)
    {
        if (starveDay < 0)
            System.out.println("Invalid starveDay!");
        else
            this.starveDay = starveDay;
    }
}

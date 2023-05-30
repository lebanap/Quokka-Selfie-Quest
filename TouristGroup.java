/**
 * Class which is used to store all information of tourist groups
 * @author : Zecheng Pan
 * @version : 1.0.0
 */
public class TouristGroup
{
    private boolean hasSelfie;
    private Selfie selfie;
    private int size;
    
    /**
     * Default constructor which creats the object of TouristGroup
     */
    public TouristGroup()
    {
        hasSelfie = false;
        selfie = new Selfie();
        size = 0;
    }

    /**
     * Non-default constructor which creates the objects of TouristGroup
     * @param hasSelfie     a boolean representing if a tourist group has selfie 
     * @param selfie        a Selfie object representing the activity a tourist interact with quokka 
     * @param size          an integer representing the number of tourists in a tourist group
     */
    public TouristGroup(boolean hasSelfie, Selfie selfie, int size)
    {
        this.hasSelfie = hasSelfie;
        this.selfie = selfie;
        this.size = size;
    }
    
    /**
     * Generate the number of tourists in a tourist group
     */
    public void checkNumOfTourists()
    {
        int randNum = RandomNum.generateRandomNum(100, 1);

        // 30% only one tourist in a group 
        if (randNum <= 30)
            this.size = 1;
        // 30% two tourists in a group
        else if (randNum > 30 && randNum<= 60)
            this.size = 2;
        // 10% three tourists in a group
        else if (randNum > 60 && randNum <= 70)
            this.size = 3;
        // 10% four tourists in a group
        else if (randNum > 70 && randNum <= 80)
            this.size = 4;
        // 10% five tourists in a group
        else if (randNum > 80 && randNum <= 90)
            this.size = 5;
        // Left 10% for six tourists in a group
        else
            this.size = 6;
    }
    
    /**
     * Invoke generateSelfieInfo() to generate the selfie information
     * Verify if sketch are only possible for individual group
     */
    public void checkSelfieInfo()
    {
        while (true)
        {
            generateSelfieInfo();
            String selfieType = selfie.getType();
            // Make sure the sketch is only be allowed for individual group  
            if (!(size != 1 && selfieType.equals("sketch")))
                break;
        }
    }
    
    /**
     * Display the information of a tourist 
     */
    public void display()
    {
        System.out.println("This group has: " + size + " people." +
         "\n They choose the " + selfie.getType());
    }

    /**
     * Generate the selfie information a tourist group has
     */
    public void generateSelfieInfo()
    {
        int randNum = RandomNum.generateRandomNum(100, 1);
        // 60% the selfie type is photo
        if (randNum <= 60)
        {
            // Create a selfie object as using the constructor of subclass Photo
            selfie = new Photo();
            selfie.setType("photo");
            // if tourist group is individual group
            if (size == 1)
                selfie.setFoodPayment(1);
            // if tourist group is not individual group
            else
                selfie.setFoodPayment(2);
        }
        // 35% the selfie type is video
        else if (randNum > 60 && randNum <= 95)
        {
            // Create a selfie object as using the constructor of subclass Video
            selfie = new Video();
            selfie.setType("video");
            //selfie.checkTime();
            // Check the type of selfie is Video
            if (selfie instanceof Video)
            {
                ((Video) selfie).checkTime();
                int selfieTime = ((Video) selfie).getSeconds();
                // The situation that selfie time is not a multiple of 20
                if (selfieTime % 20 != 0)
                    // eg. selfieTime = 21second, so the payment is caculated as 40seconds
                    selfie.setFoodPayment(selfieTime / 20 + 1);
                // The situation that selfie time is a multiple of 20
                else
                    selfie.setFoodPayment(selfieTime / 20);
                // The situation that the tourist group is not individual group
                if (size > 1)
                {
                    int foodPayment = selfie.getFoodPayment() * 2;
                    selfie.setFoodPayment(foodPayment);
                }
            }
        }
        // The situation that the type of selfie is sketch
        else
        {
            selfie = new Sketch();
            selfie.setType("sketch");
            if (selfie instanceof Sketch)
            {
                ((Sketch) selfie).checkTime();
                int selfieTime = ((Sketch) selfie).getMinutes();
                if (selfieTime == 5)
                    selfie.setFoodPayment(6);
                else
                    selfie.setFoodPayment(10);
            }
        }
        hasSelfie = true;
    }
    
    /**
     * Accessor method to get the status if tourist group has selfie
     * @return hasSelfie     A boolean representing the status of tourist group
     */ 
    public boolean getHasSelfie()
    {
        return hasSelfie;
    }

    /**
     * Accessor method to get the selfie of a tourist group
     * @return selfie       An object of Selfie representing the selfie of tourist group
     */ 
    public Selfie getSelfie()
    {
        return selfie;
    }

    /**
     * Accessor method to get the tourist number of group
     * @return size         An integer representing the tourist number of group
     */ 
    public int getSize()
    {
        return size;
    }

    /**
     * Mutator method to set the status of whether a tourist group has selfie
     * @param hasSelfie     A boolean representing the status of tourist group 
     */
    public void setHasSelfie(boolean hasSelfie)
    {
        this.hasSelfie = hasSelfie;
    }

    /**
     * Mutator method to set the selfie of a tourist group
     * @param hasSelfie     An object of Selfie representing the selfie of tourist group 
     */
    public void setSelfie(Selfie selfie)
    {
        this.selfie = selfie;
    }

    /**
     * Mutator method to set the tourist number of group
     * @param size          An integer representing the tourist number of group 
     */
    public void setSize(int size)
    {
        this.size = size;
    }
}

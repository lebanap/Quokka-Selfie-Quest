/**
 * Class which is used to manage sketches
 * @author : Zecheng Pan
 * @version : 1.0.0
 */
public class Sketch extends Selfie
{
    private int minutes;
    
    /**
     * Default constructor which creats the object of Sketch
     */
    public Sketch()
    {
        // Call the constructor of parent class Selfie
        super();
        minutes = 0;
    }

    /**
     * Non-default constructor which creates the objects of class Sketch
     * @param foodPayment   an integer representing the food bags a a sketch can supply
     * @param type          a string representing the type of this selfie
     * @param minutes       an integer representing the pose time of sketch
     */
    public Sketch(int foodPayment, String type, int minutes)
    {
        super(foodPayment, type);
        this.minutes = minutes;
    }

    /**
     * Generate the pose time for a sketch
     */
    public void checkTime()
    {
        int randNum = RandomNum.generateRandomNum(2, 1);
        if (randNum == 1)
            minutes = 5;
        else
            minutes = 10;
    }

    /**
     * Display the information of a sketch
     */
    public void display()
    {
        System.out.println("Time for this selfie is: " + minutes + "minutes");
    }

    /**
     * Accessor method to get the pose time of a sketch
     * @return minutes     An integer representing the pose time of a sketch
     */ 
    public int getMinutes()
    {
        return minutes;
    }

    /**
     * Mutator method to set the pose time of a video
     * @param minutes     An integer representing the pose time of a video
     */
    public void setMinutes(int minutes)
    {
        this.minutes = minutes;
    }
}

/**
 * Class which is used to manage videos 
 * @author : Zecheng Pan
 * @version : 1.0.0
 */
public class Video extends Selfie
{
    private int seconds;
    
    /**
     * Default consturctor which creats the object of class Video
     */
    public Video()
    {
        // Call the constructor of parent class Selfie 
        super();
        seconds = 0;
    }

    /**
     * Non-default constructor which creates the objects of class Video
     * @param foodPayment   an integer representing the food bags a a video can supply
     * @param type          a string representing the type of this selfie
     * @param minutes       an integer representing the length of video
     */
    public Video(int foodPayment, String type, int seconds)
    {
        super(foodPayment, type);
        this.seconds = seconds;
    }
    
    /**
     * Generate the video length for a video
     */
    public void checkTime()
    {
        seconds = RandomNum.generateRandomNum(60, 1);
    }

    /**
     * Display the information of a video
     */
    public void display()
    {
        System.out.println("Time for this selfie is: " + seconds + " seconds");
    }

    /**
     * Accessor method to get the time length of a video
     * @return seconds     An integer representing the length of a video
     */ 
    public int getSeconds()
    {
        return seconds;
    }

    /**
     * Mutator method to set the time length of a video
     * @param seconds     An integer representing the length of a video
     */
    public void setMinutes(int minutes)
    {
        this.seconds = seconds;
    }
}

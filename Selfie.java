/**
 * Class which is used to manage all kinds of selfies
 * @author : Zecheng Pan
 * @version : 1.0.0
 */
public class Selfie
{
    private int foodPayment;
    private String type;
    
    /**
     * Default consturctor which creats the object of class Selfie
     */
    public Selfie()
    {
        foodPayment = 0;
        type = "";
    }

    /**
     * Non-default constructor which creates the objects of class Selfie
     * @param foodPayment   an integer representing the food bags a a sketch can supply
     * @param type          a string representing the type of this selfie
     */
    public Selfie(int foodPayment, String type)
    {
        this.foodPayment = foodPayment;
        this.type = type;
    }

    /**
     * Display the information of a selfie
     */
    public void display()
    {
        System.out.println("The type of this selfie is: " + type + "\nThe foodbags this selfie can support is: " + foodPayment);
    }

    /**
     * Accessor method to get the number of food bags a selfie can support
     * @return foodPayment    An integer representing the food a selfie can support
     */ 
    public int getFoodPayment()
    {
        return foodPayment;
    }

    /**
     * Accessor method to get the type of a selfie
     * @return type           A string representing the type of a selfie
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * Mutator method to set the food bags a selfie can support
     * @param foodPayment     An integer representing the food bags a selfie can support
     */
    public void setFoodPayment(int foodPayment)
    {
        this.foodPayment = foodPayment;
    }

    /**
     * Mutator method to set the type of a selfie
     * @param type            A string representing the type of a selfie
     */
    public void setType(String type)
    {
        this.type = type;
    }
}

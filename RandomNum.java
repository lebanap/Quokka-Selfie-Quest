/**
 * Class which is used to generate a random integer
 * @author : Zecheng Pan
 * @version : 1.0.0
 */
public class RandomNum
{
    /**
     * Default consturctor which creats the object of class RandomNum
     */
    public RandomNum()
    {
        
    }
    
    /**
     * Generate a random integer
     * @param maxValue  An integer representing the maximum value of the range
     * @param minValue An integer representing the minimal value of the range
     * @return num  An integer representing the generated random number
     */
    public static int generateRandomNum(int maxValue, int minValue)
    {
        int num = (int)(Math.random() * (maxValue - minValue + 1 ) + minValue);
        return num;
    }
}

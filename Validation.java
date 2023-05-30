/**
 * Class which is used to verify user inputs
 * @author : Zecheng Pan
 * @version : 1.0.0
 */
public class Validation
{
    /**
     * Default consturctor which creats the object of class Validation
     */
    public Validation()
    {

    }

    /**
     * Verify user input when user choose option
     * User can only input 1 or 2
     * @param userInput  A string representing the user input
     * @return           Return the verfication result: if valid, return true; if not, return false
     */
    public static boolean verifyChoice(String userInput)
    {
        if (!(userInput.equals("1") || userInput.equals("2")))
            return false;
        return true;
    }
    
    /**
     * Verify user input when user should input numbers
     * User can only input a number
     * A regular expression and match() to verify if user input are number
     * @param userInput  A string representing the user input
     * @return           Return the verfication result: if valid, return true; if not, return false
     */
    public static boolean verifyInputNum(String userInput)
    {
        return userInput.matches("-?\\d+");
    }
}

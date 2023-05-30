import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class which is used to control the running of interaction between quokka and tourist groups
 * @author : Zecheng Pan
 * @version : 1.0.0
 */
public class QuokkaSelfieQuest
{
    private int initialQuokkaNum;
    private int endQuokkaNum;
    private ArrayList<Integer> quokkaDeadDays;
    private ArrayList<Quokka> quokkaGroup;
    private int totalDeadQuokkaNum;
    private int totalFoodSupply;
    private int totalNewBornQuokkaNum;
    private ArrayList<ArrayList<TouristGroup>> totalTouristGroups;

    /**
     * Default consturctor which creats the object of class QuokkaSelfieQuest
     */
    public QuokkaSelfieQuest()
    {
        initialQuokkaNum = 0;
        endQuokkaNum = 0;
        quokkaDeadDays = new ArrayList<>();
        quokkaGroup = new ArrayList<>();
        totalDeadQuokkaNum = 0;
        totalFoodSupply = 0;
        totalNewBornQuokkaNum = 0;
        totalTouristGroups = new ArrayList<>();
    }

    /**
     * Non-default constructor which creates the objects of class QuokkaSelfieQuest
     * @param initialQuokkaNum      an integer representing the number of quokka before simulation 
     * @param endQuokkaNum          an integer representing the number of quokka after simulation
     * @param quokkaDeadDays        an ArrayList representing stores the day where have quokka death
     * @param quokkaGroup           an ArrayList representing store the quokkas attending the simulation
     * @param totalDeadQuokkaNum    an integer representing the numebr of total dead quokkas
     * @param totalFoodSupply       an integer representing total food quokka earned
     * @param totalNewBornQuokkaNum an integer representing the number of total new born quokka
     * @param totalTouristGroups    an ArrayList representing all quokka groups in 30 days
     */
    public QuokkaSelfieQuest(int initialQuokkaNum, int endQuokkaNum, ArrayList<Integer> quokkaDeadDays, int totalDeadQuokkaNum, int totalFoodSupply, int totalNewBornQuokkaNum, ArrayList<Quokka> quokkaGroup, ArrayList<ArrayList<TouristGroup>> totalTouristGroups)
    {
        this.initialQuokkaNum = initialQuokkaNum;
        this.endQuokkaNum = endQuokkaNum;
        this.quokkaDeadDays = quokkaDeadDays;
        this.quokkaGroup = quokkaGroup;
        this.totalDeadQuokkaNum = totalDeadQuokkaNum;
        this.totalFoodSupply = totalFoodSupply;
        this.totalNewBornQuokkaNum = totalNewBornQuokkaNum;
        this.totalTouristGroups = totalTouristGroups;
    }

    /**
     * Calculate the food a quokka can get when it interacted with a tourist group
     * @param quokka       a quokka object
     * @param touristGroup a touristGroup object 
     */
    public void calculateFoodSupports(Quokka quokka, TouristGroup touristGroup)
    {
        int foodBags = touristGroup.getSelfie().getFoodPayment();
        if (quokka.getHasBaby())
        {
            foodBags *= 2;
        }
        int foodSupply = quokka.getFoodSupply();
        foodSupply += foodBags;
        quokka.setFoodSupply(foodSupply);
    }

    /**
     * Calculate QPSF
     * @return QPSF
     */
    public String calculateQPSF()
    {
        double result = ((double)endQuokkaNum - initialQuokkaNum) / initialQuokkaNum;
        String QPSF = String.format("%.2f", result);
        return QPSF;
    }

    /**
     * Calculate QSQSF
     * @return QSQSF
     */
    public String calculateQSQSF()
    {
        double result = (1- ((double)totalDeadQuokkaNum / initialQuokkaNum)) * 100;
        String QSQSF = String.format("%.2f", result);
        return QSQSF;
    }

    
    /**
     * Calculate all information about quokka and return one of according to different conditions
     * @return result
     */
    public int calculateQuokkaInfo(String infoType)
    {
        int result = 0;
        switch (infoType)
        {
            case "aliveQuokka":
                for (int index = 0; index < quokkaGroup.size(); index ++)
                {
                    if (quokkaGroup.get(index).getIsAlive())
                    {
                        if (quokkaGroup.get(index).getHasBaby())
                            result += 2;
                        else
                            result += 1;
                    }  
                }  
                break;
            case "deadQuokka":
                for (int index = 0; index < quokkaGroup.size(); index ++)
                {
                    if (!(quokkaGroup.get(index).getIsAlive()))
                    {
                        if (quokkaGroup.get(index).getHasBaby())
                            result += 2;
                        else
                            result += 1;
                    }
                }
                break;
            case "babyQuokka":
                for (int index = 0; index < quokkaGroup.size(); index ++)
                {
                    if (quokkaGroup.get(index).getIsAlive())
                    {
                        if (quokkaGroup.get(index).getHasBaby())
                            result += 1;
                    }   
                }
                break;
            case "newBornQuokka":
                for (int index = 0; index < quokkaGroup.size(); index ++)
                {
                    if (quokkaGroup.get(index).getHasNewBorn())
                        result += 1;
                }
                break;
            case "foodConsume":
                for (int index = 0; index < quokkaGroup.size(); index ++)
                {
                    result += quokkaGroup.get(index).getFoodConsume();
                }
                break;
            case "foodSupply":
                for (int index = 0; index < quokkaGroup.size(); index ++)
                {
                    result += quokkaGroup.get(index).getFoodSupply();
                }
                break;
            case "foodStore":
                for (int index = 0; index < quokkaGroup.size(); index ++)
                {
                    result += quokkaGroup.get(index).getFoodStore();
                }
                break;
            default:
                System.out.println("Invalid infomation type!");
                break;
        }
        return result;   
    }

    /**
     * Calculate all information about tourist group
     * @return   return different values according to the different conditions
     */
    public int calculateTouristGroupInfoPerDay(String infoType, int day)
    {
        int groupVideo = 0;
        int groupPhoto = 0;
        int individualPhoto = 0;
        int individualVideo = 0;
        int individualSketch = 0;
        int totalSelfies = 0;
        for (int index = 0; index < totalTouristGroups.get(day).size(); index ++)
        {
            int groupNum = totalTouristGroups.get(day).get(index).getSize();
            //System.out.println(groupNum);
            String selfieType = totalTouristGroups.get(day).get(index).getSelfie().getType();
            //System.out.println(selfieType);
            if (groupNum > 1 && selfieType.equals("video"))
            {
                groupVideo += 1;
                totalSelfies += 1;
            }
            else if (groupNum > 1 && selfieType.equals("photo"))
            {
                groupPhoto += 1;
                totalSelfies += 1;
            }
            else if (groupNum == 1 && selfieType.equals("video"))
            {
                individualVideo += 1;
                totalSelfies += 1;
            }
            else if (groupNum == 1 && selfieType.equals("photo"))
            {
                individualPhoto += 1;
                totalSelfies += 1;
            }
            else
            {
                individualSketch += 1;
                totalSelfies += 1;
            } 
        }
        switch (infoType)
        {
            case "touristGroupPerDayNum":
                int groupNumPerDay = totalTouristGroups.get(day).size();
                return groupNumPerDay;
            case "groupVideoNum":
                return groupVideo;
            case "groupPhotoNum":
                return groupPhoto;
            case "individualVideoNum":
                return individualVideo;
            case "individualPhotoNum":
                return individualPhoto;
            case "individualSketchNum":
                return individualSketch;
            case "totalSelfiesNum":
                return totalSelfies;
            default:
                System.out.println("Invalid infomation type!");
                return 100;
        }
    }

    /**
     * Check the days where have quokka death 
     */
    public void checkQuokkaDeadDays(int day)
    {
        for (int index = 0; index < quokkaGroup.size(); index ++)
        {
            if (!(quokkaGroup.get(index).getIsAlive()))
            {
                if (!(quokkaDeadDays.contains(day + 1)))
                    quokkaDeadDays.add(day + 1);
            }   
        }
    }

    /**
     * Generate the id for each quokka 
     */
    public void generateQuokkaId()
    {
        ArrayList<String> idList = new ArrayList<>();
        for (Quokka quokka: quokkaGroup)
        {
            while (true)
            {
                int id = RandomNum.generateRandomNum(999, 100);
                String idCode = "Q" + id;
                if (!(idList.contains(idCode)))
                {
                    idList.add(idCode);
                    quokka.setIdCode(idCode);
                    break;
                }
            }
        }
    }

    /**
     * Accessor method to get the initial number of quokka
     * @return initialQuokkaNum    
     */
    public int getInitialQuokkaNum()
    {
        return initialQuokkaNum;
    }

    /**
     * Accessor method to get the number of quokka after simulation
     * @return endQuokkaNum    
     */
    public int getEndQuokkaNum()
    {
        return endQuokkaNum;
    }

    /**
     * Accessor method to get the day where has quokka death
     * @return quokkaDeadDays    
     */
    public ArrayList<Integer> getQuokkaDeadDays()
    {
        return quokkaDeadDays;
    }

    /**
     * Accessor method to get the ArrayList of quokka group
     * @return quokkaGroup    
     */
    public ArrayList<Quokka> getQuokkaGroup()
    {
        return quokkaGroup;
    }

    /**
     * Accessor method to get total number of dead quokkas
     * @return totalDeadQuokkaNum    
     */
    public int getTotalDeadQuokkaNum()
    {
        return totalDeadQuokkaNum;
    }

    /**
     * Accessor method to get total number of food quokka earned
     * @return totalFoodSupply    
     */
    public int getTotalFoodSupply()
    {
        return totalFoodSupply;
    }

    /**
     * Accessor method to get total number of new born quokka
     * @return totalNewBornQuokkaNum   
     */
    public int getTotalNewBornQuokkaNum()
    {
        return totalNewBornQuokkaNum;
    }

    /**
     * Accessor method to get an ArrayList of total tourist groups in 30 days
     * @return totalTouristGroups    
     */
    private ArrayList<ArrayList<TouristGroup>> getTotalTouristGroups()
    {
        return totalTouristGroups;
    }

    /**
     * Manage the user input
     * Let user re enter if input is invalid   
     */
    public int getUserInput(String condition)
    {
        Scanner console = new Scanner(System.in);
        int result = 0;
        switch (condition)
        {
            case "start/exit":
                while (true)
                {
                    System.out.println("1-Start new simulation\n2-Exit Quokka-Selfie-Quest\nEnter your option:");
                    String option = console.nextLine().trim();
                    if (Validation.verifyInputNum(option))
                    {
                        if (Validation.verifyChoice(option))
                        {
                            result = Integer.parseInt(option);
                            break;
                        }
                        else
                            System.out.println("Invalid input! Please enter 1 or 2");
                    }
                    else
                        System.out.println("Invalid input! Please enter 1 or 2");
                }
                break;
            case "quokkaNum":
                while (true)
                {
                    System.out.println("Please enter the number of quokkas:");
                    String inputNum = console.nextLine().trim();
                    if (Validation.verifyInputNum(inputNum))
                    {
                        result = Integer.parseInt(inputNum);
                        break;
                    }
                    else
                        System.out.println("Invalid input! Please enter a number");
                }
                break;
            case "nextDay":
                while (true)
                {
                    System.out.println("\n1-Continue to the next day\n2-Exit simulation\nEnter your option:");
                    String userInput = console.nextLine().trim();
                    if (Validation.verifyInputNum(userInput))
                    {
                        if (Validation.verifyChoice(userInput))
                        {
                            result = Integer.parseInt(userInput);
                            break;
                        }
                        else
                            System.out.println("Invalid input! Please enter 1 or 2");
                    }
                    else
                        System.out.println("Invalid input! Please enter 1 or 2");
                }
                break;
            default:
                System.out.println("Invalid condition!");
                break;      
        }
        return result;
    }

    /**
     * Initialise all quokkas  
     */
    public void initialiseQuokkaGroup(int quokkaNum)
    {
        quokkaGroup.clear();
        for (int index = 0; index < quokkaNum; index ++)
        {
            Quokka quokka = new Quokka();
            quokka.checkHasBaby();
            quokka.checkFoodIntake();
            quokka.checkInitFood();
            quokkaGroup.add(quokka);
        }
        generateQuokkaId();
    }

    /**
     * Initialise all tourist groups  
     */
    public void initialiseTotalTouristGroups()
    {
        totalTouristGroups.clear();
        DataHandle dataHandle = new DataHandle();
        ArrayList<Integer> numberOfTouristGroupsPerDayList = dataHandle.readFile();
        for (int index = 0; index < numberOfTouristGroupsPerDayList.size(); index ++)
        {
            ArrayList<TouristGroup> touristGroupsPerDay = new ArrayList<>();
            for (int sequence = 0; sequence < numberOfTouristGroupsPerDayList.get(index); sequence ++)
            {
                TouristGroup touristGroup = new TouristGroup();
                touristGroup.checkNumOfTourists();
                touristGroup.checkSelfieInfo();
                //System.out.println(touristGroup.getSelfie().getType());
                touristGroupsPerDay.add(touristGroup);
            }
            totalTouristGroups.add(touristGroupsPerDay);
        }
    }

    /**
     * Manage the interaction between quokkas and tourist groups in a day  
     */
    public void interactWithQuokkaPerDay(ArrayList<TouristGroup> touristGroupsPerDay)
    {
        for (int index = 0; index < touristGroupsPerDay.size(); index ++)
        {
            Quokka quokka = quokkaGroup.get(index % quokkaGroup.size());
            TouristGroup touristGroup = touristGroupsPerDay.get(index);
            calculateFoodSupports(quokka, touristGroup);
        }
        for (int sequence = 0; sequence < quokkaGroup.size(); sequence ++)
        {
            quokkaGroup.get(sequence).checkQuokkaFoodStatus();
            quokkaGroup.get(sequence).checkBirth();
        }
    }

    /**
     * Program start  
     */
    public static void main(String[] args)
    {
        QuokkaSelfieQuest quokkaSelfieQuest = new QuokkaSelfieQuest();
        quokkaSelfieQuest.runProgram();
    }
    
    /**
     * Manage the running of the process of simulation  
     */
    public void operateSimulation()
    {
        quokkaDeadDays.clear();
        totalDeadQuokkaNum = 0;
        totalFoodSupply = 0;
        totalNewBornQuokkaNum = 0;
        int quokkaNum = getUserInput("quokkaNum");
        initialQuokkaNum = quokkaNum;
        initialiseQuokkaGroup(quokkaNum);
        int day = 0;
        while (day < 30)
        {
            System.out.println("*** " + "Day " + (day + 1) + " ***");
            System.out.println("Current alive quokkas(including babies): " + calculateQuokkaInfo("aliveQuokka"));
            System.out.println("Current baby quokkas: " + calculateQuokkaInfo("babyQuokka"));
            System.out.println("Current total rest food: " + calculateQuokkaInfo("foodStore"));
            sortQuokkas();
            System.out.println("Quokkas are lined up!");
            initialiseTotalTouristGroups();
            System.out.println("Tourist groups has arrived!");
            System.out.println("Number of tourist groups today is: " + calculateTouristGroupInfoPerDay("touristGroupPerDayNum", day));
            System.out.println("Quokkas are offering selfies to tourist groups...");
            ArrayList<TouristGroup> touristGroupsPerDay = totalTouristGroups.get(day);
            System.out.println("\nSelfie Stats\n============");
            System.out.println("Group Video: " + calculateTouristGroupInfoPerDay("groupVideoNum", day));
            System.out.println("Group Photo: " + calculateTouristGroupInfoPerDay("groupPhotoNum", day));
            System.out.println("Individual Photo: " + calculateTouristGroupInfoPerDay("individualPhotoNum", day));
            System.out.println("Individual Video: " + calculateTouristGroupInfoPerDay("individualVideoNum", day));
            System.out.println("Individual Sketch: " + calculateTouristGroupInfoPerDay("individualSketchNum", day));
            System.out.println("Total selfies: " + calculateTouristGroupInfoPerDay("totalSelfiesNum", day));
            interactWithQuokkaPerDay(touristGroupsPerDay);
            System.out.println("Earned food bags: " + calculateQuokkaInfo("foodSupply"));
            System.out.println("\nDaily Summary\n============");
            System.out.println("Current live quokkas: " + calculateQuokkaInfo("aliveQuokka"));
            System.out.println("Dead quokkas today: " + calculateQuokkaInfo("deadQuokka"));
            System.out.println("New baby today: " + calculateQuokkaInfo("newBornQuokka"));
            System.out.println("Current baby quokkas: " + calculateQuokkaInfo("babyQuokka"));
            System.out.println("Consumed food today: " + calculateQuokkaInfo("foodConsume"));
            System.out.println("Current total rest food: " + calculateQuokkaInfo("foodStore"));
            totalNewBornQuokkaNum += calculateQuokkaInfo("newBornQuokka");
            checkQuokkaDeadDays(day);
            totalDeadQuokkaNum += calculateQuokkaInfo("deadQuokka");
            totalFoodSupply += calculateQuokkaInfo("foodSupply");
            resetQuokkaStatus();
            removeDeadQuokkas();
            int choice = getUserInput("nextDay");
            if (choice == 2)
                break;
            day ++;
        }
        endQuokkaNum = calculateQuokkaInfo("aliveQuokka");
        System.out.println("\nGenerating report...");
        DataHandle dataHandle = new DataHandle();
        dataHandle.writeFile(endQuokkaNum, totalNewBornQuokkaNum, totalDeadQuokkaNum, totalFoodSupply);
    }
    
    /**
     * Remove dead quokkas in a day  
     */
    public void removeDeadQuokkas()
    {
        for (int index = quokkaGroup.size() - 1; index >= 0; index --)
        {
            if (!(quokkaGroup.get(index).getIsAlive()))
            {
                quokkaGroup.remove(index);
            }        
        }
    }

    /**
     * Reset some attributes of quokka object   
     */
    public void resetQuokkaStatus()
    {
        for (int index = 0; index < quokkaGroup.size(); index ++)
        {
            quokkaGroup.get(index).setFoodSupply(0);
            quokkaGroup.get(index).setFoodConsume(0);
        }
    }

    /**
     * Manage the running of whole program  
     */
    public void runProgram()
    {
        System.out.println("Welcome to the Quokka-Selfie-Quest!\nThis is a simulation program to verify a possible solution to save cute quokkas!" + 
        "\nThe result of simulation will be reported in a generated txt file.\nNow let's start the program!");
        while (true)
        {
            int choice = getUserInput("start/exit");
            if (choice == 1)
            {
                System.out.println("\nSimulation start!\n=================");
                operateSimulation();
                String QSQSF = calculateQSQSF();
                String QPSF = calculateQPSF();
                System.out.println("Simulation finished!");
                System.out.println("The QSQSF is: " + QSQSF);
                System.out.println("The QPSF is: " + QPSF);
                if (!(quokkaDeadDays.isEmpty()))
                {
                    System.out.println("The following days have quokka death:");
                    for (int index = 0; index < quokkaDeadDays.size(); index ++)
                    {
                        System.out.print(quokkaDeadDays.get(index) + " ");
                    }
                    System.out.println("\n");
                }
                else
                    System.out.println("No quokka dead!");
            }
            else
            {
                System.out.println("Exit successfully!");
                break;
            }
        }
    }

    public void setInitialQuokkaNum(int initialQuokkaNum)
    {
        this.initialQuokkaNum = initialQuokkaNum;
    }

    public void setEndQuokkaNum(int endQuokkaNum)
    {
        this.endQuokkaNum = endQuokkaNum;
    }

    public void setQuokkaDeadDays(ArrayList<Integer> quokkaDeadDays)
    {
        this.quokkaDeadDays = quokkaDeadDays;
    }
    
    public void setQuokkaGroup(ArrayList<Quokka> quokkaGroup)
    {
        this.quokkaGroup = quokkaGroup;
    }
    
    public void setTotalDeadQuokkaNum(int totalDeadQuokkaNum)
    {
        this.totalDeadQuokkaNum = totalDeadQuokkaNum;
    }

    public void setTotalFoodSupply(int foodSupply)
    {
        this.totalFoodSupply = foodSupply;
    }

    public void setTotalNewBornQuokkaNum(int newBornQuokka)
    {
        this.totalNewBornQuokkaNum = newBornQuokka;
    }

    public void setTouristGroup(ArrayList<ArrayList<TouristGroup>> totalTouristGroup)
    {
        this.totalTouristGroups = totalTouristGroup;
    }

    /**
     * Sort quokkas using bubble sort  
     */
    public void sortQuokkas()
    {
        for (int index = 0; index < quokkaGroup.size() - 1; index ++)
        {
            for (int sequence = 1; sequence < quokkaGroup.size() - index - 1; sequence ++)
            {
                if (quokkaGroup.get(sequence).getFoodStore() > quokkaGroup.get(sequence + 1).getFoodStore())
                {
                    Quokka temp = quokkaGroup.get(sequence);
                    quokkaGroup.set(sequence, quokkaGroup.get(sequence + 1));
                    quokkaGroup.set(sequence + 1, temp);
                }
            }
        }
    }
}

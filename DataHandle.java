import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class which is used to manage the fileIO
 * @author : Zecheng Pan
 * @version : 1.0.0
 */
public class DataHandle
{

    /**
     * Default constructor to create a object of FileHandle
     */
    public DataHandle()
    {
        
    }

    /**
     * Read data from tourist.txt
     */
    public ArrayList<Integer> readFile()
    {
        ArrayList<Integer> numOfGroups = new ArrayList<>();
        try
        {
            FileReader reader = new FileReader("tourist.txt");
            Scanner file = new Scanner(reader);
            while (file.hasNextLine())
            {
                String line = file.nextLine();
                String[] info = line.split(",");
                for (int index = 0; index < info.length; index ++)
                {
                    int groupNum = Integer.parseInt(info[index]);
                    numOfGroups.add(groupNum); 
                }
            }
            file.close();
            reader.close();
        }
        catch (IOException e)
        {
            System.out.println("Error in reading data!");
            e.printStackTrace();
        }
        return numOfGroups;
    }

    /**
     * Write data to populationFinal.txt
     */
    public void writeFile(int endQuokkaNum, int totalNewBornQuokkaNum, int totalDeadQuokkaNum, int totalFoodSupply)
    {
        try
        {
            FileWriter writer = new FileWriter("populationFinal.txt");
            writer.append("The number of live quokkas: " + endQuokkaNum + "\n");
            writer.append("The number of new born quokkas: " + totalNewBornQuokkaNum + "\n");
            writer.append("The number of dead quokkas: " + totalDeadQuokkaNum + "\n");
            writer.append("The number of total food supply: " + totalFoodSupply + "\n");
            System.out.println("The report has been generated!");
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("Error in writing data to file!");
            e.printStackTrace();
        }
    }
}

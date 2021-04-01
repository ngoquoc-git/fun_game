import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ItemGenerator {
    ArrayList <Item> itemList;
    
    /**
     * Constructer open the ItemList and get the data from it
     */
    ItemGenerator()
    {
        Item item;

        try{
            //Return to the current working directory
            String currentDir = System.getProperty("ItemList.txt");

            //Using scanner to open the file
            Scanner scan = new Scanner(new File(currentDir));
            while(scan.hasNextLine())
            {
                item = new Item(scan.nextLine());
                itemList.add(item);
            }

            //Close the file
            scan.close();
        }catch(IOException e)
        {
            e.getStackTrace();
        }
    }

    /**
     * Generate items from the list
     * @return return a random item from the list
     * @throws IOException throw exception when the file was not found
     */
    public Item generateItem() throws IOException
    {
        ItemGenerator itemGenerator = new ItemGenerator();

        Random random = new Random();

        int number = random.nextInt(itemGenerator.itemList.size());

        return itemGenerator.itemList.get(number);
    }
}

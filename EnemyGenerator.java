

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class EnemyGenerator {
    ArrayList<Enemy> enemyList;
    ItemGenerator ig;
    static final int EXTRA_HP = 10;
    static final int EXTRA_DAMAGE = 3;

    /**
     * Constructor read input file and load into ArrayList enemyList
     * @param ig generate item
     */
    EnemyGenerator(ItemGenerator ig)
    {
        Enemy enemy;
        int hp = 0;
        String name = "", input = "";
        Item item;


        try{
            //Return to the current working directory
            String currentDir = System.getProperty("EnemyList.txt");

            //Using scanner to open the file
            Scanner scan = new Scanner(new File(currentDir));
            while(scan.hasNextLine())
            {
                input = scan.nextLine();
                name = input.split(",")[0];
                hp = Integer.parseInt(input.split(",")[1]);
                item = ig.generateItem();
                enemy = new Enemy(name,hp, item);
                enemyList.add(enemy);
            }

            //Close the file
            scan.close();
        }catch(IOException e)
        {
            e.getStackTrace();
        }
    }

    /**
     * Generate enemy according to its level
     * @param level level of enemy
     * @return return enemy corresponding to its level
     */
    public Enemy generateEnemy(int level)
    {
        String name = "";
        int hp = 0;
        Item item;
        ItemGenerator itemGenerator = new ItemGenerator();
        EnemyGenerator enemyGenerator = new EnemyGenerator(itemGenerator);
        Random random = new Random();

        int number = random.nextInt(enemyGenerator.enemyList.size());

        name = enemyGenerator.enemyList.get(number).getName();
        //Modify hp according to level
        hp = (level - 1) * EXTRA_HP + enemyGenerator.enemyList.get(number).getMaxHP();
        item = enemyGenerator.enemyList.get(number).getItem();

        Enemy enemy = new Enemy(name, hp, item);

        return enemy;
    }
    
}

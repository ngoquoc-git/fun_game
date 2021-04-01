import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException{
        Map map = new Map();
        Random rand = new Random();
        ItemGenerator ig = new ItemGenerator();
        EnemyGenerator eg = new EnemyGenerator(ig);
        Enemy enemy;
        int decision;
        int level = 1;
        int[] mazeLevels = { 1, 2, 3 };
        String heroName;
        int mapSelection;
        boolean endGame  = false;
        Hero myHero;


        //Create player
        System.out.println("What is your name traveler?");
        heroName = CheckInput.getString();
        heroName = CheckInput.getString();
        mapSelection= rand.nextInt(mazeLevels.length);
        map.loadMap(mazeLevels[mapSelection]);
        myHero = new Hero(heroName, map);

        //Loop to break the game if plaer decide to quit, hero dies, or complete 3 levels
        do{
            System.out.println(myHero.toString());
            map.displayMap(myHero.getLocation());
            System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
            decision = CheckInput.getIntRange(1, 5);
            switch (decision){
                case 1: myHero.goNorth(); break;
                case 2: myHero.goSouth(); break;
                case 3: myHero.goEast(); break;
                case 4: myHero.goWest(); break;
                case 5: endGame = true; break;
            }
            //End game immediately if user chooses quit
            if(endGame == false){
                //Choose actions base on location's character
                if (map.getCharAtLoc(myHero.getLocation()) == 's') System.out.println("You are back at the start.");
                else if (map.getCharAtLoc(myHero.getLocation()) == 'i') itemRoom(myHero, map, ig);
                else if (monsterRoom(myHero, map, eg, level)){
                    enemy = eg.generateEnemy(level);
                    System.out.println("You've encountered a " + enemy.getName());
                    while(fight(myHero, enemy));
                    if (enemy.getHP() == 0) map.removeCharAtLoc(myHero.getLocation());
                }
                else if (map.getCharAtLoc(myHero.getLocation()) == 'f') {
                    level++;
                    System.out.println("You have reached the finish point. Move on to the next level. \n");
                    mapSelection = rand.nextInt(3);
                    map.loadMap(mazeLevels[mapSelection]);
                    myHero.heal(myHero.getMaxHP());
                    if(level < 4) System.out.println ("Level: " + level); 
                }
                else System.out.println("Go Ahead.");
            }
        } while(myHero.getHP() > 0 && level < 4 && endGame == false);
        
        //Print out result based on hero's health points
        if (myHero.getHP() < 1){
            System.out.println("Game Over. You died.\n");
        }

        if (level < 4) System.out.println("Game Over, Try again sometimes.\n");
        else System.out.println("Congratulation, you successfully seized the maze.\n");
    }

    

    public static boolean monsterRoom(Hero h, Map m, EnemyGenerator eg, int level){
        if (m.getCharAtLoc(h.getLocation()) == 'm'){
            eg.generateEnemy(level);
            return true;
        }
        else return false;
    }

    public static void itemRoom(Hero h, Map m, ItemGenerator it) throws IOException{
        if (m.getCharAtLoc(h.getLocation()) == 'i'){
            h.pickUpItem(it.generateItem());
            m.removeCharAtLoc(h.getLocation());
        }
    }

    public static boolean fight(Hero h, Enemy e){
        boolean battle = true;
        int decision, attacks, magic;
        Random rand = new Random();

        if (e instanceof Magical) e = (MagicalEnemy) e;
        System.out.println(e.toString());

        do{
            if(h.hasPotion() && h.getHP() < e.getMaxHP()){
                System.out.println("1. Fight.\n2. Run Away.\n3. Drink Potion.\n");
                decision = CheckInput.getIntRange(1, 3);
            }
            else{
                System.out.println("1. Fight.\n2. Run Away.");
                decision = CheckInput.getIntRange(1, 2);
            }
            //Fight
            if (decision == 1){
                System.out.println("1. Physical Attack.\n2. Magical Attack.");
                attacks = CheckInput.getIntRange(1, 2);
                //Physical attack
                if (attacks == 1) System.out.print(h.attack(e));
                //Magical attack
                else{
                    System.out.println(Magical.MAGIC_MENU);
                    magic = CheckInput.getIntRange(1, 3);
                    switch (magic){
                        case 1:  System.out.print(h.magicalMisslle(e)); break;
                        case 2:  System.out.print(h.fireBall(e)); break;
                        case 3:  System.out.print(h.thunderClap(e)); break;
                    }
                }
                //Stop if enemy die
                if (e.getHP() == 0) {
                    System.out.print(e.getName() + " has been killed.");
                    return true;
                }
                else {
                    if (e instanceof Magical){
                        attacks = rand.nextInt(4);
                        switch(attacks){
                            case 0:  System.out.print(e.magicalMisslle(h)); break;
                            case 1:  System.out.print(e.fireBall(h)); break;
                            case 2:  System.out.print(e.thunderClap(h)); break;
                            case 3:  System.out.print(e.attack(h)); break;
                        }
                    }
                    else System.out.print(e.attack(h));
                    
                    if(h.getHP() == 0) return false;
                }
            }
            //Run Away
            else if (decision == 2) {
                int fleePercent = rand.nextInt(100);
                //Successfully run away
                if (fleePercent > 20) return false;
                //Unsuccessfully run away and lose 1 turn
                else{
                    System.out.println("You could not get away this time.");
                    System.out.print(e.attack(h));
                    if (h.getHP() == 0) return false;
                }     
            }
            //Drink Potion
            else h.drinkPotion();
        } while (battle);
    }
}
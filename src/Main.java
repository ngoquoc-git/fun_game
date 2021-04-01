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
        int decision;
        int attack;
        
        //Create a magical enemy if condition is satisfied
        if (e instanceof Magical) e = (MagicalEnemy) e;
        System.out.println(e.toString());
        
        //check if hero has potions to use
        if(h.hasPotion()){
            System.out.println("1. Fight.\n2. Run Away.\n3. Drink Health Potion.\n");
            decision = CheckInput.getIntRange(1, 3);
        }
        else{
            System.out.println("1. Fight.\n2. Run Away.\n");
            decision = CheckInput.getIntRange(1, 2);
        }

        //Fight
        if (decision == 1){
            System.out.println("1.Physical Attack\n2.Magical Attack");
            attack = CheckInput.getIntRange(1, 2);

            if (attack == 1){
                System.out.println(h.attack(e));
                if (e.getHP() > 0){
                    System.out.println(e.attack(h));
                }
            }

            else{
                System.out.println(Magical.MAGIC_MENU);
                int magicalAttack = CheckInput.getIntRange(1, 3);
                if(magicalAttack == 1){
                    System.out.println(h.magicalMissile(e));
                    if (e.getHP() > 0){
                        System.out.println(e.attack(h));
                    }
                }
                else if (magicalAttack == 2){
                    System.out.println(h.fireBall(e));
                    if (e.getHP() > 0){
                        System.out.println(e.attack(h));
                    }
                }
                else {
                    System.out.println(h.thunderClap(e));
                    if (e.getHP() > 0){
                        System.out.println(e.attack(h));
                    }
                }
            }
            //end fight if enemy is death and let hero pick up item if possible
            if (e.getHP() < 1) {
                System.out.println("You defeated the " + e.getName());
                h.pickUpItem(e.getItem());
                return false;
            }
            return true;
        }
        //Run Away
        else if(decision == 2){
            Random rand = new Random();
            int getAway = rand.nextInt(2);
            int run;
            //Run successfully
            if(getAway == 1){
                System.out.println(h.getName() + " successfully got away.");
                if(h.getLocation().x == 0 && h.getLocation().y == 0) h.goEast();
                else if(h.getLocation().x == 0 && h.getLocation().y == 4) h.goSouth();
                else if(h.getLocation().x == 4 && h.getLocation().y == 0) h.goNorth();
                else if(h.getLocation().x == 4 && h.getLocation().y == 4) h.goWest();
                else { 
                    run = rand.nextInt(3);
                    switch(run){
                        case 0: h.goSouth(); break;
                        case 1: h.goNorth(); break;
                        case 2: h.goEast(); break;
                        case 3: h.goWest(); break;
                    }   
                }
                return false;
            }
            //hero got hity if run failed
            else{
                System.out.println(e.getName() + " did not let you get away.");
                System.out.println(e.attack(h));
                System.out.println(h.toString());
                if(h.getHP() > 0) return true;
                else return false;
            }
        }
        else {
            h.drinkPotion();
            return true;
        }   
    }
}
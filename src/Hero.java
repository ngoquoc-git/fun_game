

import java.util.Random;
import java.util.ArrayList;
public class Hero extends Entity implements Magical{

    private ArrayList<Item> items;
    private Map map;
    private Point location;
    
    /**
     * Magical Misslle skill
     * @param e entity that is attacked
     * @return attack action
     */
    @Override
    public String magicalMisslle(Entity e){
        Random rand = new Random();
        int damage = rand.nextInt(3);
        e.takeDamage(damage);
        if (damage == 0) return "The attack has missed.\n";
        else if (damage == 1) return getName() + " shots a misslle that deals 1 damage on " + e.getName() + "\n"; 
        else return getName() + " shots a misslle that deals " + damage + " damages on " + e.getName() + "\n"; 
    }

    /**
     * Fire Ball skill
     * @param e entity that is attacked
     * @return attack action
     */
    @Override
    public String fireBall(Entity e){
        Random rand = new Random();
        int damage = rand.nextInt(3);
        e.takeDamage(damage);
        if (damage == 0) return "The attack has missed.\n";
        else if (damage == 1) return getName() + " throws a fire ball at " + e.getName() + " that deals 1 damage.\n"; 
        else return getName() + " throws a fire ball at " + e.getName() + " that deals " + damage + " damages.\n";
    }

    /**
     * Thunderclap skill
     * @param e entity that is attacked
     * @return attack action
     */
    @Override
    public String thunderClap(Entity e){
        Random rand = new Random();
        int damage = rand.nextInt(4);
        e.takeDamage(damage);
        if (damage == 0) return "The attack has missed.\n";
        else if (damage == 1) return getName() + " claps " + e.getName() + " that deals 1 damage.\n"; 
        else return getName() + " claps " + e.getName() + " that deals " + damage + " damages";
    }

    /**
     * Abstract attack action that will be used by Heroes and Enemies
     * @param e Entity that is attacked
     * @return attack action
     */
    @Override
    public String attack(Entity e){
        Random rand = new Random();
        int damage = rand.nextInt(6);
        e.takeDamage(damage);
        if (damage == 0) return "The attack has missed.\n";
        else if (damage == 1) return getName() + " scracthes " + e.getName() + " that deals 1 damage.\n"; 
        else return getName() + " chops " + e.getName() + " vertically with his sword that deals " + damage + " damages";
    }

    /**
     * Create a hero with 25 HP and given name
     * Find the start point of that hero
     * @param n name of hero
     * @param m map level
     */
    public Hero(String n, Map m){
        super(n, 25);
        items = new ArrayList<>();
        map = m;
        location = map.findStart();
    }

    /**
     * Show hero's name and HP
     */
    public String toString(){
        return getName() + ": " + getHP() + "/" + getMaxHP();
    }

    /**
     * Items that hero is holding
     * @return List of items
     */
    public String itemsToString(){
        String itemList = "";
        for(int i =0; i < items.size(); i++){
            itemList = itemList + String.valueOf(i+1) + ". " +  items.get(i) + ".\n";
        }
        return itemList;
    }

    /**
     * Get total number of item the hero is holding
     * @return number of item
     */
    public int getNumItems(){return items.size();}

    /**
     * Drop an unwanted item
     * @param index location of the item in the bag
     */
    public void dropItem(int index) {items.remove(index);}

    /**
     * Pick up an item after encountering it
     * @param i the item to be picked
     * @return true if it is picked, false if it is not
     */
    public boolean pickUpItem(Item i){
        int decision;
        System.out.println("You receive " + i.getName());
        if(items.size() < 6){
            items.add(i);
            return true;
        }
        else{
            System.out.println("Your bag is full. Choose an item to remove (Choose 6 to keep previous items.\n");
            System.out.println(itemsToString());
            decision = CheckInput.getIntRange(1, 6);

            if(decision == 6) return false;
            else {
                dropItem(decision - 1);
                items.add(i);
                return true;
            }
        }
    }

    /**
     * Check if any potions exist in the inventory
     * @return true if any, false otherwise
     */
    public boolean hasPotion(){
        for (int i = 0; i < items.size(); i++){
            if (items.get(i).getName().equals("Health Point")) return true;
        }
        return false;
    }

    /**
     * Use potion to heal
     */
    public void drinkPotion(){
        int i = 0;
        while(items.get(i).getName().equals("Health Potion") && i < items.size()){
            i++;
        }
        if(i < items.size()) {
            heal(8);
            dropItem(i);
            toString();
        }
        else System.out.print("You don't have any Health Potion.\n");
    }

    /**
     * Retrieve hero's current location
     * @return location
     */
    public Point getLocation(){
        return location;
    }

    /**
     * Indicate hero to go north if it does not hit the boundary
     * reveal its previous footstep
     * @return hero's location
     */
    public char goNorth(){
        if (location.x == 0){
            return map.getCharAtLoc(location);
        }
        map.revealed(location);
        location.x--;
        return map.getCharAtLoc(location);
    }

    /**
     * Indicate hero to go south if it does not hit the boundary
     * reveal its previous footstep
     * @return hero's location
     */
    public char goSouth(){
        if (location.x == 4){
            return map.getCharAtLoc(location);
        }
        map.revealed(location);
        location.x++;
        return map.getCharAtLoc(location);
    }

    /**
     * Indicate hero to go east if it does not hit the boundary
     * reveal its previous footstep
     * @return hero's location
     */
    public char goEast(){
        if (location.y == 4){
            return map.getCharAtLoc(location);
        }
        map.revealed(location);
        location.y++;
        return map.getCharAtLoc(location);
    }

    /**
     * Indicate hero to go west if it does not hit the boundary
     * reveal its previous footstep
     * @return hero's location
     */
    public char goWest(){
        if (location.y == 0){
            return map.getCharAtLoc(location);
        }
        map.revealed(location);
        location.y--;
        return map.getCharAtLoc(location);
    }
}


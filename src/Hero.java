import java.util.Random;
import java.util.ArrayList;
public class Hero extends Entity implements Magical{

    private ArrayList<Item> items = new ArrayList<>();
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

    
}

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        System.out.println("Code starts");
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
        
    }
}
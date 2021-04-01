import java.util.Random;
import java.util.ArrayList;
public class Hero extends Entity implements Magical{
    
    /**
     * Magical Misslle skill
     * @param e entity that is attacked
     * @return attack action
     */
    @Override
    public String magicalMisslle(Entity e){

    }

    /**
     * Fire Ball skill
     * @param e entity that is attacked
     * @return attack action
     */
    @Override
    public String fireBall(Entity e){

    }

    /**
     * Thunderclap skill
     * @param e entity that is attacked
     * @return attack action
     */
    @Override
    public String thunderClap(Entity e){

    }

    /**
     * Abstract attack action that will be used by Heroes and Enemies
     * @param e Entity that is attacked
     * @return attack action
     */
    @Override
    public String attack(Entity e){

    }
}

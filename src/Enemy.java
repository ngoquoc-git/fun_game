

import java.util.Random;

public class Enemy extends Entity{
    /**
     * Item of the enemy
     * The factor of critical hit
     */
    private Item item;
    static final int CRITICAL = 3;
    static final int MAX_ATTACK = 8;

    /**
     * Create construction Enemy with name n, max health mHP, and item i
     * @param n name of the enemy
     * @param mHp maximum Hp of the enemy
     * @param i item of the enemy
     */
    Enemy(String n, int mHp, Item i)
    {
        super(n, mHp);
        item = i;
    }

    /**
     * Get the item of the enemy
     * @return return the item of the enemy
     */
    Item getItem()
    {
        return item;
    }

    /**
     * Enemy attack other Entities
     * @param e entity is attacked by this enemy
     * @return display a string which shows how much damage this enemy deal to the entity
     */
    public String attack(Entity e) 
    {
        int chance = 0;
        Random random = new Random();

        //Enemy will have a random damage range from 1 to 8
        int attack = random.nextInt(MAX_ATTACK) + 1;

        //There is 20% the enemy have a critical attack
        //Critical attack will be attack multiply by CRITICAL(3)
        if(chance >= 0 && chance < 20)
        {
            attack *= CRITICAL;  
            e.takeDamage(attack);
            return "\033[0;1m" + this.getName() + " deal " + attack + " damage to " + e.getName();
        } 
        else 
        {
            e.takeDamage(attack);
            return this.getName() + " deal " + attack + " damage to " + e.getName();
        }
    }

}

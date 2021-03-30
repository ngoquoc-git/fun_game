public abstract class Entity {
    
    //An entity basic components
    private String name;
    private int maxHP;
    private int hp;

    /**
     * Create an entity with a name and max HP
     * @param name name of an Entity
     * @param mHP maximum HP of an entity
     */
    public Entity(String name, int mHP){
        this.name = name;
        this.maxHP = mHP;
        this.hp = mHP;
    }

    /**
     * Abstract attack action that will be used by Heroes and Enemies
     * @param e Entity that is attacked
     * @return attack action
     */
    abstract String attack(Entity e);

    /**
     * Get entity's name
     * @return name of the entity
     */
    public String getName(){return name;}
    
    /**
     * Get entity's current health points
     * @return Entity's current HP
     */
    public int getHP(){return hp;}

    /**
     * Get entity's meximum health points
     * @return Entity's max HP
     */
    public int getMaxHP(){return maxHP;}

    /**
     * Heal an entity h health point
     * @param h heal amount
     */
    public void heal (int h){
        hp+=h;
        if (hp > maxHP) hp = maxHP;
    }

    /**
     * The entity takes damage and lose HP
     * @param d amount of damage taken
     */
    public void takeDamage(int d){
        hp-=d;
        if(hp < 0) hp = 0;
    }

    /**
     * Entity's status
     */
    public String toString(){return name + " " + hp + "/" + maxHP;}
}

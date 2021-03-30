public abstract class Entity {
    
    //An entity basic components
    private String name;
    private int maxHP;
    private int hp;

    //Create an entity with a name and max HP
    public Entity(String name, int mHP){
        this.name = name;
        this.maxHP = mHP;
    }

    //Abstract attack action that will be used by Heroes and Enemies
    abstract String attack(Entity e);

    //Get entity's name
    public String getName(){return name;}
    
    //Get entity's current health points
    public int getHP(){return hp;}

    //Get entity's meximum health points
    public int getMaxHP(){return maxHP;}


}

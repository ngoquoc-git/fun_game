
public interface Magical {
    public static final String MAGIC_MENU = "1.Magical Misslle.\n2. Fire Ball.\n3. Thunderclap.";

    /**
     * Magical Misslle skill
     * @param e entity that is attacked
     * @return attack action
     */
    public String magicalMisslle(Entity e);

    /**
     * Fire Ball skill
     * @param e entity that is attacked
     * @return attack action
     */
    public String fireBall(Entity e);

    /**
     * Thunderclap skill
     * @param e entity that is attacked
     * @return attack action
     */
    public String thunderClap(Entity e);

}

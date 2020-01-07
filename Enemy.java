import java.util.ArrayList;
public class Enemy extends NPC
{
    private String name;
    private Item[] dropPool;
    public Enemy(int atk, int def, int health, String n, Item a, Item b, Item c)
    {
        super(atk, def, health);
        name = n;
        dropPool = new Item[3];
        dropPool[0] = a;
        dropPool[1] = b;
        dropPool[2] = c;
    }
    public int attack()
    {
        int rand = (int)(Math.random()*5);
        int enemyAttack = getAttack() + rand;
        return enemyAttack;
    }
    public int defend()
    {
        int rand = (int)(Math.random()*5);
        int enemyDefence = getDefense() + rand;
        return enemyDefence;
    }
    public String name()
    {
        return name;
    }
    public Item drop()
    {
        int rand = (int)(Math.random()*3);
        return dropPool[rand];
    }

    
}

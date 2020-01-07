
/**
 * NPC is a general class that lays out basic attributes of players and enemies
 */
public class NPC
{
    private int maxH;
    public int health;
    public int attack;
    public int defense;
    
    //public NPC()    {}
    public NPC(int NPCattack, int NPCdefense, int NPChealth)
    {
        maxH = NPChealth;
        health = NPChealth;
        attack = NPCattack;
        defense = NPCdefense;
    }
    public void setHealth(int nHealth)
    {
        health = nHealth;
    }
    public void setAttack(int nAttack)
    {
        attack = nAttack;
    }
    public void setDefense(int nDefense)
    {
        defense = nDefense;
    }
    public int getHealth()
    {
        return health;
    }
    public int getAttack()
    {
        return attack;
    }
    public int getDefense()
    {
        return defense;
    }
    public void resetHealth()
    {
        health = maxH;
    }
    public String toString()
    {
        return "Health: " + health + "\nAttack: " + attack + "\nDefense: " + defense;
    }
}

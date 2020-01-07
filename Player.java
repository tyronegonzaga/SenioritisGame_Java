
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.List;
import java.util.ArrayList;
public class Player extends NPC
{
    ArrayList<Item> inventory = new ArrayList<Item>();
    Item weapon;
    Item armour;
    double cash = 0.00;
    public Player()
    {
        super(2,2,10);
    }
    public void pickUp(Item i)
    {
        inventory.add(i);
    }
    public void drop(int i)
    {
        inventory.remove(i);
    }
    public void showInventory()
    {
        for(int i = 0; i < inventory.size(); i++)
        {
            System.out.println( i + ":" + inventory.get(i).name());
        }
    }
    public int inventoryLength()
    {
        return inventory.size();
    }
    public Item getItem(int i)
    {
        return inventory.get(i);
    }
    public int attack()
    {
        if (weapon instanceof Weapon)
        {
            int rand = (int)(Math.random()*5);
            int playerAttack = getAttack() + weapon.use() + rand;
            return playerAttack;
        }
        else
        {
            int rand = (int)(Math.random()*5);
            int playerAttack = getAttack() + rand;
            return playerAttack;
        }
    }
    public int defend()
    {
        if (armour instanceof Armour)
        {
            int rand = (int)(Math.random()*5);
            int playerDefense = getDefense() + armour.use() + rand;
            return playerDefense;
        }
        else
        {
            int rand = (int)(Math.random()*5);
            int playerDefense = getDefense() + rand;
            return playerDefense;
        }
    }
    public void equipWeapon(int i)
    {
        weapon = inventory.get(i);
    }
    public void equipArmour(int i)
    {
        armour = inventory.get(i);
    }
    public void pay(double p)
    {
        cash -= p;
    }
    public void gain(double g)
    {
        cash += g;
    }
    public double getCash()
    {
        return cash;
    }
    public double getInventoryTotal()
    {
        double total = 0.00;
        for (Item i : inventory)
        {
            total += i.getValue();
        }
        return total;
    }
    }


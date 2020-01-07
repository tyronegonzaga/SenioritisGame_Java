public class Weapon implements Item
{
    private int mod;
    private double val;
    private String name;
    public Weapon(int attack, double value, String n)
    {
        mod = attack;
        val = value;
        name = n;
    }
    
    public int use()
    {
        return mod;
    }
    
    public double getValue()
    {
        return val;
    }
    
    public String name()
    {
        return name;
    }
}

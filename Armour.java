public class Armour implements Item
{
    private int mod;
    private double val;
    private String name;
    public Armour(int defence, double value, String n)
    {
        mod = defence;
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
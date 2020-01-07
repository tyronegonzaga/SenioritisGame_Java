public class BandAid implements Item
{
    private double val;
    private String name;
    
    public BandAid()
    {
        val = 5.00;
        name = "BandAid";
    }
    
    public int use()
    {
        return 2;
    }
    
    public double getValue()
    {
        return 5.00;
    }
    
    public String name()
    {
        return name;
    }
}
public class Map
{
    private Classroom[][] floor;
    private int locR;
    private int locC;
    private int topBossLocR;
    private int topBossLocC;
    private int bottomBossLocR;
    private int bottomBossLocC;
    public Map()
    {}
    public void updateMap(boolean top)
    {
        if (top)
        floor = World.getFloor(true);
        else
        floor = World.getFloor(false);
    }

    public int getBossLocR(boolean top)
    {
        if (top)
        return topBossLocR;
        else
        return bottomBossLocR;
    }
    public void setBossLocR(int r, boolean top)
    {
        if (top)
        topBossLocR = r;
        else
        bottomBossLocR = r;
    }
    public int getBossLocC(boolean top)
    {
        if (top)
        return topBossLocC;
        else
        return bottomBossLocC;
    }
    public void setBossLocC(int c, boolean top)
    {
        if (top)
        topBossLocC = c;
        else
        bottomBossLocC = c;
    }
    
    public int getLocR()
    {
        return locR;
    }
    public void setLocR(int r)
    {
        locR = r;
    }
    public int getLocC()
    {
        return locC;
    }
    public void setLocC(int c)
    {
        locC = c;
    }
    
    
    
    public void printMap(boolean top)
    {
        for (int r = 0; r < 4; r++)
        {
            for (int c = 0; c < 4; c++)
            {
                System.out.print("[");
                if (r == locR && c == locC)
                System.out.print("p");
                else if (floor[r][c] instanceof ExploredRoom)
                System.out.print("x");
                else if (top && r == topBossLocR && c == topBossLocC)
                System.out.print("e");
                else if (!top && r == bottomBossLocR && c == bottomBossLocC)
                System.out.print("e");
                else
                System.out.print(" ");
                
                System.out.print("]");
                
            }
            System.out.println();
        }
    }
}

public class Room1 implements Classroom
{
    Nerd nerd;
    public Room1()
    {
        nerd = new Nerd();
    }
    public Enemy getEnemy()
    {
        return nerd;
    }
    public String roomStatement()
    {
        return "You enter a room with a Nerd in it";
    }
}

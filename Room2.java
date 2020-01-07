public class Room2 implements Classroom
{
    Jock jock;
    public Room2()
    {
        jock = new Jock();
    }
    public Enemy getEnemy()
    {
        return jock;
    }
    public String roomStatement()
    {
        return "You enter a room with a Jock in it";
    }
}

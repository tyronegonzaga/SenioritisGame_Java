public class Room6 implements Classroom
{
    Security security;
    public Room6()
    {
        security = new Security();
    }
    public Enemy getEnemy()
    {
        return security;
    }
    public String roomStatement()
    {
        return "You enter a room with a security guard in it";
    }
}

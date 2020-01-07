public class Room4 implements Classroom
{
    Locker locker;
    public Room4()
    {
        locker = new Locker();
    }
    public Enemy getEnemy()
    {
        return locker;
    }
    public String roomStatement()
    {
        return "You enter a room with a locker in it.\nIt is locked tight, but you may be able to open it with some brute force";
    }
}

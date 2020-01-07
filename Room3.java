public class Room3 implements Classroom
{
    Teacher teacher;
    public Room3()
    {
        teacher = new Teacher();
    }
    public Enemy getEnemy()
    {
        return teacher;
    }
    public String roomStatement()
    {
        return "You enter a room with a teacher in it";
    }
}

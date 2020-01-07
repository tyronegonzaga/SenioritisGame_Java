public class Room5 implements Classroom
{
    Nurse nurse;
    public Room5()
    {
        nurse = new Nurse();
    }
    public Enemy getEnemy()
    {
        return nurse;
    }
    public String roomStatement()
    {
        return "You enter the nurse's office\nShe seems pretty weak and she might have something to treat your battle wounds with";
    }
}

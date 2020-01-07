public class Room7 implements Classroom
{
    Janitor jan;
    public Room7()
    {
        jan = new Janitor();
    }
    public Enemy getEnemy()
    {
        return jan;
    }
    public String roomStatement()
    {
        return "You enter the janitor's closet\nHe does not want to fight you\nInstead he offers to buy and sell items with you";
    }
}

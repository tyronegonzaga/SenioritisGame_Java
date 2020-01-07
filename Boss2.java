public class Boss2 implements Classroom
{
    Dean dean;
    public Boss2()
    {
        dean = new Dean();
    }
    public Enemy getEnemy()
    {
        return dean;
    }
    public String roomStatement()
    {
        return "You approach the main doors and you can see a glimpse of the freedom you've faught so hard to achieve\nSuddenly, a figure steps out in front of you\nYou recognize him as the dean, come to enact his revenge for all the classes you skipped";
    }
}

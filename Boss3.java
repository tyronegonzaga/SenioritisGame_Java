public class Boss3 implements Classroom
{
    Principal principal;
    public Boss3()
    {
        principal = new Principal();
    }
    public Enemy getEnemy()
    {
        return principal;
    }
    public String roomStatement()
    {
        return "You step over the body of the dean and open the doors to the outside world\n Finally freedom is yours\n But just as you think you've made it, the principal himself steps out to block your way\n This is it, defeat him and freedom is yours";
    }
}

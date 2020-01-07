public class Boss1 implements Classroom
{
    HallMonitor hallMon;
    public Boss1()
    {
        hallMon = new HallMonitor();
    }
    public Enemy getEnemy()
    {
        return hallMon;
    }
    public String roomStatement()
    {
        return "You step out onto the staircase landing.\nBetween you and the stairs stands the Hall Monitor\nHe notices you have no hall pass and, pushing his glasses up the bridge of his nose, prepares for battle";
    }
}

import java.util.List; 
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.Math;
public class World
{
   private static Classroom[][] topFloor;
   private static Classroom[][] bottomFloor;
   private Classroom[] topPool;
   private Classroom[] bottomPool;
   
   public World()
   {
       topFloor = new Classroom[4][4];
       bottomFloor = new Classroom[4][4];
       populatePools();
       populateFloors();
       
       
   }

   public Classroom getRoom(boolean top, int row, int col)
   {
       if (top)
       return topFloor[row][col];
       else 
       return bottomFloor[row][col];
   }
   public void setRoom(boolean top, int row, int col, Classroom c)
   {
       if (top)
       topFloor[row][col] = c;
       else 
       bottomFloor[row][col] = c;
   }
   public void Explore(boolean top, int row, int col)
   {
       if (top)
       topFloor[row][col] = new ExploredRoom();
       else
       bottomFloor[row][col] = new ExploredRoom();
   }
   public static Classroom[][] getFloor(boolean top)
   {
       if (top)
       return topFloor;
       else 
       return bottomFloor;
   }
   public void populatePools()
   {
       topPool = new Classroom[]{new Room1(), new Room1(), new Room1(), new Room1(), new Room1(), new Room7(), new Room2(), new Room3(), new Room4(), new Room5()};
       bottomPool = new Classroom[]{new Room6(), new Room2(), new Room2(), new Room3(), new Room3(), new Room3(), new Room7(), new Room4(), new Room5(), new Room6()};
   }
   public void populateFloors()
   {
       for (int r = 0; r < 4; r++)
       {
           for (int c = 0; c < 4; c++)
           {
               bottomFloor[r][c] = bottomPool[(int)(Math.random() * 10)];
               topFloor[r][c] = topPool[(int)(Math.random() * 10)];
           }
       }
       
       topFloor[(int)(Math.random() * 4)][(int)(Math.random() * 4)] = new Boss1();
       bottomFloor[(int)(Math.random() * 4)][(int)(Math.random() * 4)] = new Boss2();
   }
  
   
}

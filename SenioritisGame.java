import java.util.Scanner;
import java.lang.String;
public class SenioritisGame
{
    private static Scanner kb;
    private static boolean started;
    private static Player p1;
    public static void main()
    {   
        World gameWorld = null;
        p1 = null;
        Map map = null;
        Enemy enemy = null;
        boolean combat = false;
        boolean top = false;
        started = false;
        
        System.out.println("It is the last day of school and you are a senior");
        System.out.println("This means that as soon as you get out, you are free from high school forever");
        System.out.println("The final bell rings, now's your chance");
        System.out.println("Escape the building");
        System.out.println("Scavange what you can and battle your way through the students and faculty who stand in your way");
        System.out.println();
        System.out.println("***SENIORITIS***");
        System.out.println();
        System.out.println("Type 'Controls' for a list of commands");
        System.out.println("Type 'Start' to begin");
        
        kb = new Scanner(System.in);
    do
    {
        String input = kb.nextLine();
        if (input.toLowerCase().equals("controls"))
            {
                System.out.println(controls());
            }
        else if (input.toLowerCase().equals("start"))
        {
            gameWorld = new World();
            p1 = new Player();
            map = new Map();
            top = true;
            map.updateMap(top);
            combat = true;
            started = true;
            map.setLocR(0);
            map.setLocC(0);
            enemy = gameWorld.getRoom(top, map.getLocR(), map.getLocC()).getEnemy();
            
            System.out.println(gameWorld.getRoom(top, map.getLocR(), map.getLocC()).roomStatement());
        }
    } while(!started);

    do
    {
        String input = kb.nextLine();
            if (combat)
            {
                if (input.toLowerCase().equals("inventory"))
                {
                    p1.showInventory();
                }
                
                else if (input.toLowerCase().equals("equip"))
                {
                    int item;
                    char a;
                    String response;

                    do
                    {
                        System.out.println("Which item would you like to equip?");
                        response = kb.nextLine();
                        a = response.charAt(0);
                    }while (!response.toLowerCase().equals("cancel") && !(response.length() == 1 && Character.isDigit(a)));
                    if (response.toLowerCase().equals("cancel"))
                    {
                        System.out.println("Equip canceled");
                    }
                    else
                    {
                        item = Integer.parseInt(response);                        
                        if (item < p1.inventoryLength())
                        {
                            if (p1.getItem(item) instanceof Weapon)
                            {
                                p1.equipWeapon(item);
                                System.out.println("You equiped " + p1.getItem(item).name() + " as your weapon");
                            }
                            
                            else if (p1.getItem(item) instanceof Armour)
                            {
                                p1.equipArmour(item);
                                System.out.println("You equiped " + p1.getItem(item).name() + " as your armour");                            
                            }
                            else
                                System.out.println("You may not equip that item");
                        }
                        else
                            System.out.println("There is no item in that position");
                    }
                }
                
                else if (input.toLowerCase().equals("heal"))
                {
                    int item;
                    char a;
                    String response;

                    do
                    {
                        System.out.println("Which item would you like to use?");
                        response = kb.nextLine();
                        a = response.charAt(0);
                    }while (!response.toLowerCase().equals("cancel") && !(response.length() == 1 && Character.isDigit(a)));
                    if (response.toLowerCase().equals("cancel"))
                    {
                        System.out.println("Heal canceled");
                    }
                    else
                    {
                        item = Integer.parseInt(response);                    
                        if (item < p1.inventoryLength())
                        {
                            if (p1.getItem(item) instanceof BandAid)
                            {
                                p1.drop(item);
                                p1.setHealth(10);
                                System.out.println("You patched up your wounds and you feel good as new");
                            }
                            else
                                System.out.println("You may not use that item");
                        }
                        else
                            System.out.println("There is no item in that position");
                    }
                }
                
                else if (input.toLowerCase().equals("drop"))
                {
                    int item;
                    char a;
                    String yn;
                    String response;

                    do
                    {
                        System.out.println("Which item would you like to drop?");
                        response = kb.nextLine();
                        a = response.charAt(0);
                    }while (!response.toLowerCase().equals("cancel") && !(response.length() == 1 && Character.isDigit(a)));
                    if (response.toLowerCase().equals("cancel"))
                    {
                        System.out.println("Drop canceled");
                    }
                    else
                    {
                        item = Integer.parseInt(response);
                        if (item < p1.inventoryLength())
                        {
                            do
                            {
                                System.out.println("Are you sure you would like to drop " + p1.getItem(item).name() + "\nYou will not be able to retrieve it. Y/N");
                                yn = kb.nextLine();
                            } while (!yn.toLowerCase().equals("y") && !yn.toLowerCase().equals("n"));
                            if(yn.toLowerCase().equals("y"))
                            {   
                                p1.drop(item);
                                System.out.println("You dropped the item");
                            }
                            else
                            {
                                System.out.println("Drop canceled");
                            }
                        }
                        else
                            System.out.println("There is no item in that position");
                    }
                }
                
                else if (input.toLowerCase().equals("attack"))
                {
                    int pRoll = p1.attack();
                    int eRoll = enemy.defend();
                    boolean bossFix = true;
                    
                        System.out.println("You scored a " + pRoll + " on the attack");
                        System.out.println(enemy.name() + " scored a " + eRoll + " on the defense");
                        if (pRoll - eRoll > 0)
                        {
                            System.out.println("You dealt " + (pRoll  - eRoll) +  " points of damage");                    
                            enemy.setHealth(enemy.getHealth() - (pRoll - eRoll));
                            System.out.println(enemy.name() + " now has " + enemy.getHealth() + " health");
                        }
                        
                        else
                            System.out.println("You dealt no damage");
                            
                            if (p1.getHealth() <= 0)
                            {
                                String response;
                                do
                                {
                                    gameOver();
                                    response = kb.nextLine();
                                } while (!response.toLowerCase().equals("restart"));
                                started = false;
                            }
                            
                            else if (enemy.getHealth() <= 0)
                            {
                                System.out.println(enemy.name() + " was defeated");
                                Item drop = enemy.drop();
                                String yn;

                                do
                                {
                                    System.out.println("Upon defeat, " + enemy.name() + " dropped a " + drop.name() + ".\nWould you like to take it? Y/N");
                                    yn = kb.nextLine();
                                } while (!yn.toLowerCase().equals("y") && !yn.toLowerCase().equals("n"));
                                
                                if (yn.toLowerCase().equals("y"))
                                {
                                    System.out.println(drop.name() + " was added to your inventory");
                                    p1.pickUp(drop);
                                }
                                else if (yn.toLowerCase().equals("n"))
                                {
                                    System.out.println("You toss the item aside");
                                }
                                
                                if (enemy instanceof HallMonitor)
                                {
                                    System.out.println("You successfully defeated the Hall Monitor and you decend the stairs to the ground floor\nAll you have to do now is make it to the main entrance");
                                    top = false;
                                    map.updateMap(top);
                                    enemy = gameWorld.getRoom(top, map.getLocR(), map.getLocC()).getEnemy();
                                    enemy.resetHealth();
                                    System.out.println(gameWorld.getRoom(top, map.getLocR(), map.getLocC()).roomStatement());
                                    combat = true;
                                    bossFix = false;
                                }
                                else if (enemy instanceof Dean)
                                {
                                    gameWorld.setRoom(top, map.getLocR(), map.getLocC(), new Boss3());
                                    enemy = gameWorld.getRoom(top, map.getLocR(), map.getLocC()).getEnemy();
                                    enemy.resetHealth();
                                    System.out.println(gameWorld.getRoom(top, map.getLocR(), map.getLocC()).roomStatement());
                                    combat = true;
                                    bossFix = false;
                                }
                                else if(enemy instanceof Principal)
                                {
                                    end();
                                }
                                else
                                {
                                    gameWorld.setRoom(top, map.getLocR(), map.getLocC(), new ExploredRoom());
                                    combat = false;
                                }
                            }
                        
                        
                        if (combat && bossFix)
                        {
                            pRoll = p1.defend();
                            eRoll = enemy.attack();
                            System.out.println(enemy.name() + " scored a " + eRoll + " on the attack");
                            System.out.println("You scored a " + pRoll + " on the defense");
                        
                            if (eRoll - pRoll > 0)
                            {
                                System.out.println(enemy.name() + " dealt " + (eRoll  - pRoll) +  " points of damage");  
                                p1.setHealth(p1.getHealth() - (eRoll - pRoll));
                                System.out.println("You now have " + p1.getHealth() + " health");
                            }
                            
                            else
                                System.out.println(enemy.name() + " dealt no damage");  
                        }
                        
                        if (p1.getHealth() <= 0 && combat && bossFix)
                            {
                                String response;
                                do
                                {
                                    gameOver();
                                    response = kb.nextLine();
                                } while (!response.toLowerCase().equals("restart"));
                                started = false;
                            }
                            
                            else if (enemy.getHealth() <= 0 && combat)
                            {
                                System.out.println(enemy.name() + " was defeated");
                                Item drop = enemy.drop();
                                String yn;

                                do
                                {
                                    System.out.println("Upon defeat, " + enemy.name() + " dropped a " + drop.name() + ".\nWould you like to take it? Y/N");
                                    yn = kb.nextLine();
                                } while (!yn.toLowerCase().equals("y") && !yn.toLowerCase().equals("n"));
                                
                                if (yn.toLowerCase().equals("y"))
                                {
                                    System.out.println(drop.name() + " was added to your inventory");
                                    p1.pickUp(drop);
                                }
                                else if (yn.toLowerCase().equals("n"))
                                {
                                    System.out.println("You toss the item aside");
                                }
                                
                                gameWorld.setRoom(top, map.getLocR(), map.getLocC(), new ExploredRoom());
                                combat = false;
                            }
                }
                    
                
                else if (input.toLowerCase().equals("map"))
                {
                    map.printMap(top);
                }
                
                else if (input.toLowerCase().equals("move"))
                {
                    System.out.println("You may not leave the room during combat.");
                }
                
                else if (input.toLowerCase().equals("controls"))
                {
                    System.out.println(controls());
                }
                
                else if (input.toLowerCase().equals("health"))
                {
                    System.out.println(p1.getHealth());
                }
                
                else
                {
                    System.out.println("That is not a valid command. Type 'Controls' for a list of commands.");
                }
            }
            
            else
            {
                if (input.toLowerCase().equals("inventory"))
                {
                    p1.showInventory();
                }
                
                else if (input.toLowerCase().equals("equip"))
                {
                    int item;
                    char a;
                    String response;

                    do
                    {
                        System.out.println("Which item would you like to equip?");
                        response = kb.nextLine();
                        a = response.charAt(0);
                    }while (!response.toLowerCase().equals("cancel") && !(response.length() == 1 && Character.isDigit(a)));
                    if (response.toLowerCase().equals("cancel"))
                    {
                        System.out.println("Equip canceled");
                    }
                    else
                    {
                        item = Integer.parseInt(response);                        
                        if (item < p1.inventoryLength())
                        {
                            if (p1.getItem(item) instanceof Weapon)
                            {
                                p1.equipWeapon(item);
                                System.out.println("You equiped " + p1.getItem(item).name() + " as your weapon");
                            }
                            
                            else if (p1.getItem(item) instanceof Armour)
                            {
                                p1.equipArmour(item);
                                System.out.println("You equiped " + p1.getItem(item).name() + " as your armour");                            
                            }
                            else
                                System.out.println("You may not equip that item");
                        }
                        else
                            System.out.println("There is no item in that position");
                    }
                }
                
                else if (input.toLowerCase().equals("heal"))
                {
                    int item;
                    char a;
                    String response;

                    do
                    {
                        System.out.println("Which item would you like to use?");
                        response = kb.nextLine();
                        a = response.charAt(0);
                    }while (!response.toLowerCase().equals("cancel") && !(response.length() == 1 && Character.isDigit(a)));
                    if (response.toLowerCase().equals("cancel"))
                    {
                        System.out.println("Heal canceled");
                    }
                    else
                    {
                        item = Integer.parseInt(response);                    
                        if (item < p1.inventoryLength())
                        {
                            if (p1.getItem(item) instanceof BandAid)
                            {
                                p1.drop(item);
                                p1.setHealth(10);
                                System.out.println("You patched up your wounds and you feel good as new");
                            }
                            else
                                System.out.println("You may not use that item");
                        }
                        else
                            System.out.println("There is no item in that position");
                    }
                }
                
                else if (input.toLowerCase().equals("drop"))
                {
                    int item;
                    char a;
                    String yn;
                    String response;

                    do
                    {
                        System.out.println("Which item would you like to drop?");
                        response = kb.nextLine();
                        a = response.charAt(0);
                    }while (!response.toLowerCase().equals("cancel") && !(response.length() == 1 && Character.isDigit(a)));
                    if (response.toLowerCase().equals("cancel"))
                    {
                        System.out.println("Drop canceled");
                    }
                    else
                    {
                        item = Integer.parseInt(response);
                        if (item < p1.inventoryLength())
                        {
                            do
                            {
                                System.out.println("Are you sure you would like to drop " + p1.getItem(item).name() + "\nYou will not be able to retrieve it. Y/N");
                                yn = kb.nextLine();
                            } while (!yn.toLowerCase().equals("y") && !yn.toLowerCase().equals("n"));
                            if(yn.toLowerCase().equals("y"))
                            {   
                                p1.drop(item);
                                System.out.println("You dropped the item");
                            }
                            else
                            {
                                System.out.println("Drop canceled");
                            }
                        }
                        else
                            System.out.println("There is no item in that position");
                    }
                }
                
                
                else if (input.toLowerCase().equals("attack"))
                {
                    System.out.println("You may only attack during combat");
                }

                else if (input.toLowerCase().equals("map"))
                {
                    map.printMap(top);
                }
                
                else if (input.toLowerCase().equals("move"))
                {
                    int moveX = 0;
                    int moveY = 0;
                    System.out.println("Which direction would you like to move? (N/S/E/W)");
                    String response = kb.nextLine();
                    if (response.toLowerCase().equals("cancel"))
                    {
                        System.out.println("Move canceled");
                    }
                    
                    else if (response.toLowerCase().equals("n"))
                    {
                        if (map.getLocR() > 0)
                        map.setLocR(map.getLocR() - 1);
                        else
                        System.out.println("You cannot move that direction");
                        moveY = -1;
                        map.updateMap(top);
                    }
                    
                    else if (response.toLowerCase().equals("s"))
                    {
                        if (map.getLocR() < 3)
                        map.setLocR(map.getLocR() + 1);
                        else
                        System.out.println("You cannot move that direction");
                        moveY = 1;
                        map.updateMap(top);
                    }
                    
                    else if (response.toLowerCase().equals("e"))
                    {
                        if (map.getLocC() < 3)
                        map.setLocC(map.getLocC() + 1);
                        else
                        System.out.println("You cannot move that direction");
                        moveX = 1;
                        map.updateMap(top);
                    }
                    
                    else if (response.toLowerCase().equals("w"))
                    {
                        if (map.getLocC() > 0)
                        map.setLocC(map.getLocC() - 1);
                        else
                        System.out.println("You cannot move that direction");
                        moveX = -1;
                        map.updateMap(top);
                    }
                    
                    if (gameWorld.getRoom(top, map.getLocR(), map.getLocC()) instanceof ExploredRoom)
                    {
                        System.out.println(gameWorld.getRoom(top, map.getLocR(), map.getLocC()).roomStatement());                        
                    }
                    
                    else if (gameWorld.getRoom(top, map.getLocR(), map.getLocC()) instanceof Room7)
                    {
                        String yn;
                        String bs;
                        enemy = gameWorld.getRoom(top, map.getLocR(), map.getLocC()).getEnemy();
                        System.out.println(gameWorld.getRoom(top, map.getLocR(), map.getLocC()).roomStatement());
                        do
                        {
                            System.out.println("Would you like to do business with the Janitor? Y/N");
                            yn = kb.nextLine();
                        
                          if (yn.toLowerCase().equals("y"))
                          {
                            do
                            {
                                System.out.println("Would you like to buy or sell?");
                                bs = kb.nextLine();
                            }
                            while (!bs.toLowerCase().equals("buy") && !bs.toLowerCase().equals("sell") && !bs.toLowerCase().equals("cancel"));
                            if (bs.toLowerCase().equals("buy"))
                            {
                                Item sale = enemy.drop();
                                String deal;
                                System.out.println("The janitor is offering to sell you a " + sale.name() + " for $" + sale.getValue());
                                do
                                {
                                    System.out.println("Is it a deal? Y/N");
                                    deal = kb.nextLine();
                                }
                                while (!deal.toLowerCase().equals("y") && !bs.toLowerCase().equals("n"));
                                if (deal.toLowerCase().equals("y"))
                                {
                                    if(p1.getCash() > sale.getValue())
                                    {
                                        p1.pay(sale.getValue());
                                        p1.pickUp(sale);
                                        System.out.println("Transaction complete\nYou forked out $" + sale.getValue() + " and " + sale.name() + " was added to your inventory");
                                    }
                                    else
                                    {
                                        System.out.println("You don't have the cash for that");
                                    }
                                }
                                else if (deal.toLowerCase().equals("n"))
                                {
                                    System.out.println("Transaction canceled");
                                }
                            }
                            else if (bs.toLowerCase().equals("sell"))
                            {
                                String r;
                                char a;
                                Item sale;
                                String deal;
                                
                                do
                                {
                                    System.out.println("Which item would you like to sell?");
                                    p1.showInventory();
                                    r = kb.nextLine();
                                    a = r.charAt(0);
                                }while (!r.toLowerCase().equals("cancel") && !(r.length() == 1 && Character.isDigit(a)));
                                if (r.toLowerCase().equals("cancel"))
                                {
                                    System.out.println("Transaction canceled");
                                }
                                else
                                {
                                    if (Integer.parseInt(r) < p1.inventoryLength())
                                    {
                                        sale = p1.getItem(Integer.parseInt(r));
                                        System.out.println("The janitor is offering to buy your " + sale.name() + " for $" + sale.getValue());
                                        do
                                        {
                                            System.out.println("Is it a deal? Y/N");
                                            deal = kb.nextLine();
                                        }
                                        while (!deal.toLowerCase().equals("y") && !deal.toLowerCase().equals("n"));
                                            if (deal.toLowerCase().equals("y"))
                                            {
                                                p1.gain(sale.getValue());
                                                p1.drop(Integer.parseInt(r));
                                                System.out.println("Transaction complete\nYou made $" + sale.getValue() + " and " + sale.name() + " was removed from your inventory");
                                            }
                                            else if (deal.toLowerCase().equals("n"))
                                            {
                                                System.out.println("Transaction canceled");
                                            }
                                    }
                                    else
                                        System.out.println("There is no item in that position");
                                }                                
                            }
                            else
                            {
                                System.out.println("Transaction canceled");
                            }
                          }
                          else if (yn.toLowerCase().equals("n"))
                          {
                              System.out.println("Transaction canceled");
                          }
                        }
                        while (!yn.toLowerCase().equals("n"));
                    }
                    
                    else if (gameWorld.getRoom(top, map.getLocR(), map.getLocC()) instanceof Boss1 || gameWorld.getRoom(top, map.getLocR(), map.getLocC()) instanceof Boss2 ||
                                gameWorld.getRoom(top, map.getLocR(), map.getLocC()) instanceof Boss3)
                    {
                        String yn;
                        
                        map.setBossLocR(map.getLocR(), top);
                        map.setBossLocC(map.getLocC(), top);
                        
                        System.out.println("You are attempting to ");
                        if (top)
                        System.out.print("go out onto the staircase landing ");
                        else
                        System.out.print("leave through the main exit ");
                        
                        System.out.println("There is likely going to be a very challenging enemy waiting for you\nDo you wish to continue?");
                        
                        do
                        {
                            System.out.println("Please respond with 'Y' or 'N'");
                            yn = kb.nextLine();
                        }
                        while (!yn.toLowerCase().equals("y") && !yn.toLowerCase().equals("n"));
                        if (yn.toLowerCase().equals("y"))
                        {
                            enemy = gameWorld.getRoom(top, map.getLocR(), map.getLocC()).getEnemy();
                            enemy.resetHealth();
                            System.out.println(gameWorld.getRoom(top, map.getLocR(), map.getLocC()).roomStatement());
                            combat = true;
                        }
                        
                        else if (yn.toLowerCase().equals("n"))
                        {
                            map.setLocR(map.getLocR() - moveY);
                            map.setLocC(map.getLocC() - moveX);
                            System.out.println(gameWorld.getRoom(top, map.getLocR(), map.getLocC()).roomStatement());
                        }
                    }
                    
                    else
                    {
                        enemy = gameWorld.getRoom(top, map.getLocR(), map.getLocC()).getEnemy();
                        enemy.resetHealth();
                        System.out.println(gameWorld.getRoom(top, map.getLocR(), map.getLocC()).roomStatement());
                        combat = true;
                    }
                }
                
                else if (input.toLowerCase().equals("controls"))
                {
                    System.out.println(controls());
                }
                
                else if (input.toLowerCase().equals("health"))
                {
                    System.out.println(p1.getHealth());
                }
                
                else
                {
                    System.out.println("That is not a valid command. Type 'Controls' for a list of commands.");
                }
            }
    }while (started);
    main();
    }

    private static void gameOver()
    {
        System.out.println("GAME OVER\nAlas, you were defeated in battle and now you will remain in highschool for eternity\nTo start a new game, enter 'Restart'");
    }
    public static String controls()
    {
        return "Enter 'Inventory' to check your inventory\nEnter 'Drop' to drop an item\nEnter 'Equip' to equip an item\nEnter 'Heal' to use a BandAid\nEnter 'Map' to view the map\nEnter 'Move' to move between rooms\nEnter 'Attack' to initiate a round of combat\nEnter 'Health' to check your health";
    }
    private static void end()
    {
        String response;
        System.out.println("Victory is yours. You step out of the school's front entrance and into the world\nWith your new freedom, you can do anything\nThe world outside highschool is filled with opportunities\nGood luck");
        System.out.println("Your net worth, including your cash and all the items in you inventory, came out to $" + (p1.getCash() + p1.getInventoryTotal()));
        do
        {
            System.out.println("To play again, enter 'Restart'");
            response = kb.nextLine();
        } while (!response.toLowerCase().equals("restart"));
        started = false;
    }
    }

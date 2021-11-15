// Game.java
// contains logic for running the Game

import java.util.ArrayList;
import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class Game {

    //creating an int field that is used to call the appropriate room
    //starts at room 1
    private int roomNumber = 1;

    private Room room;
    // new room objects below and Position used for warp
    private Room2 room2;
    private Room3 room3;
    private Position warpPosit;

    private Player player;
    private ArrayList<Box> boxes;
    private ArrayList<Enemy> enemies;
    private ArrayList<Warp> warps;
    private ArrayList<World> rooms = new ArrayList<>(); //made this a World arraylist so that all 3 room classes
    //can fit inside it, but we may need to change this
    private World world;

    private String name;

    public Game() {
        room = new Room();
        // added new room2/3 objects and a position used for warping
        room2 = new Room2();
        room3 = new Room3();
        warpPosit = new Position();
        player = new Player(room.getPlayerStart());
        boxes = room.getBoxes();
        enemies = room.getEnemies();
        warps = room.getWarp();
        world = new World();
    }

    // this method prints the games plot
    protected void titleScreen() {
        // clearing the terminal screen
        for (int i = 0; i < 100; i++) {
            Terminal.warpCursor(i, 0);
            System.out.print("                                                                                                                               ");
        }

        // printing the games name
        // code generated from www.coolgenerator.com/ascii-text-generator   font used: ANSI shadow
        Terminal.warpCursor(6, 26);
        System.out.print("██████╗  ██████╗  ██████╗ ██╗   ██╗███████╗    ███████╗██╗  ██╗ █████╗ ██████╗  ██████╗ ██╗    ██╗");
        Terminal.warpCursor(7, 26);
        System.out.print("██╔══██╗██╔═══██╗██╔════╝ ██║   ██║██╔════╝    ██╔════╝██║  ██║██╔══██╗██╔══██╗██╔═══██╗██║    ██║");
        Terminal.warpCursor(8, 26);
        System.out.print("██████╔╝██║   ██║██║  ███╗██║   ██║█████╗      ███████╗███████║███████║██║  ██║██║   ██║██║ █╗ ██║");
        Terminal.warpCursor(9, 26);
        System.out.print("██╔══██╗██║   ██║██║   ██║██║   ██║██╔══╝      ╚════██║██╔══██║██╔══██║██║  ██║██║   ██║██║███╗██║");
        Terminal.warpCursor(10, 26);
        System.out.print("██║  ██║╚██████╔╝╚██████╔╝╚██████╔╝███████╗    ███████║██║  ██║██║  ██║██████╔╝╚██████╔╝╚███╔███╔╝");
        Terminal.warpCursor(11, 26);
        System.out.print("╚═╝  ╚═╝ ╚═════╝  ╚═════╝  ╚═════╝ ╚══════╝    ╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝  ╚═════╝  ╚══╝╚══╝ ");


        // printing the stories plot
        Terminal.warpCursor(14, 36);
        System.out.print("*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*");
        Terminal.warpCursor(15, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(16, 36);//40
        System.out.print("*   You the adventurer must set forth into the dark world of Rogue Shadow.  *");
        Terminal.pause(1.5);
        Terminal.warpCursor(17, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(18, 36);//38
        System.out.print("* A land of mystical wonder and danger presents itself around every corner. *");
        Terminal.pause(1.5);
        Terminal.warpCursor(19, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(20, 36);//38
        System.out.print("* The goal of the game is to survive and collect as many items as possible. *");
        Terminal.pause(1.5);
        Terminal.warpCursor(21, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(22, 36);//46
        System.out.print("*         Beware of the dungeons enemies or you will surely perish!         *");
        Terminal.warpCursor(23, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(24, 36);
        System.out.print("*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*");

        //setting the players name
        Terminal.warpCursor(26, 58);
        System.out.print("What is your name adventurer? ");
        Scanner in = new Scanner(System.in);
        Terminal.warpCursor(26, 88);
        // initializing the name of the player
        name = in.next();
        player.setName(name);

    }

    // prints a help menu to the left of the map
    private void showHelp() {

        Terminal.setForeground(Color.CYAN);

        String[] cmds = {"Commands:",
            "---------",
            "Move: Arrow Keys",
            "Pickup an item: p",
            "Drop an item: d",
            "List items: l",
            "Equip weapon: w",
            "Equip armor: a",
            "Save: s",
            "Quit: q"
        };
        Terminal.setForeground(Color.GREEN);
        for (int row = 0; row < cmds.length; row++) {
            Terminal.warpCursor(row + 1, room.getCols() + 1);
            System.out.print(cmds[row]);
        }

        // adding the player info below the commands
        String[] info = {
            "Name: " + player.getName(),
            "HP: " + player.getHealth(),
            "Strength: " + player.getDamage(),
            "Defense: " + player.getProtection()
        };
        int line = 0; //the current line of the info array
        for (int i = 11; i < 15; i++) {
            Terminal.warpCursor(i + 1, room.getCols() + 1);
            System.out.print(info[line]);
            line++;
        }
        Terminal.reset();
    }

    // right under the map we keep a line for status messages
    private void setStatus(String mesg) {
        // clear anything old first
        Terminal.warpCursor(room.getRows(), 0);
        for (int i = 0; i < 100; i++) {
            System.out.print(" ");
        }

        // then print the message
        Terminal.warpCursor(room.getRows(), 0);
        System.out.print(mesg);
    }

    // code for when the player tries to pickup an item
    private void pickup() {
        Box thing = checkForBox();
        if (thing == null) {
            setStatus("There is nothing here to pick up...");
            Terminal.pause(1.25);
        } else {
            if (player.getInventory().add(thing.getItem())) {
                setStatus("You added the " + thing.getItem().getName() + " to your inventory.");
                boxes.remove(thing);
            } else {
                setStatus("This is too large for you to add!");
            }
            Terminal.pause(1.25);
        }
    }


    // code for when the player tries to drop an item
    private void drop() {
        if (checkForBox() == null) {
            Item dropped = player.getInventory().drop();
            if (dropped != null) {
                boxes.add(new Box(player.getRow(), player.getCol(), dropped));
            }
            redrawMapAndHelp();
        } else {
            setStatus("You cannot drop something on an existing item...");
            Terminal.pause(1.25);
        }
    }

    //*** I cant remember if I did this or someone else -Wade
    //*** BROKE needs to be redone
    //private void roomWarp(){
    //	Box thing= checkForBox();
    //	if (thing==null){
    //		setStatus("Cannot Warp at this moment");
    //		Terminal.pause(1.25);
    //	}
    //	else{
    //	//	int room = roomUpdate();
    //	//	nextRoom(room);
    //	}
    //}



    // handle the key which was read - return false if we quit the game
    private boolean handleKey(Key key) {
        switch (key) {
            case p:
                pickup();
                break;

            case l:
                player.getInventory().print();
                redrawMapAndHelp();
                break;

            case d:
                drop();
                break;

            case w:
                player.getInventory().equipWeapon();
                redrawMapAndHelp();
                break;

            case a:
                player.getInventory().equipArmor();
                redrawMapAndHelp();
                break;

            case s:
                //saves the current game info to a file
                try {
                    PrintWriter pw = new PrintWriter(new File("game.txt"));
                    pw.println(player.getName());
                    pw.println(player.getHealth());
                    pw.println(player.getDamage());
                    pw.println(player.getProtection());
                    pw.println(player.getWeapon()); //used to make sure the game knows what weapon is equipped
                    pw.println(player.getArmor()); //used to make sure the game knows what armor is equipped
                    //having difficulty with adding the inventory
                    //tried making an array of strings that the items of the inventory would be added to, then
                    //print them, but that wasnt working due to the player.getInventory method returning an inventory
                    //maybe theres another way we can store the inventory here before adding it to the save file
                    pw.println(world.getRoom());
                    pw.close();
                }
                catch (FileNotFoundException e) {
                    System.out.print("Could not save data");
                }

                //***currently working on this    
                // used for warping
            case ENTER:
                break;

                // handle movement
            case LEFT: player.move(0, -1, room);
                       break;
            case RIGHT: player.move(0, 1, room);
                        break;
            case UP: player.move(-1, 0, room);
                     break;
            case DOWN: player.move(1, 0, room);
                       break;

                       // and finally the quit command
            case q:
                       return false;
        }

        return true;
    }

    //*** THIS NEEDS WORK because it will allow you to warp to multiple rooms from the same warp
    // this is called when we need to redraw the room and help menu
    // this happens after going into a menu like for choosing items
    //*** MODIFYING THIS checks the room number and prints the appropriate room
    private void redrawMapAndHelp() {
        if (roomNumber == 1) {
            room.draw();
            showHelp();
        } else if (roomNumber == 2) {
            room2.draw();
            warpPosit = room2.getPlayerStart();
            int row = warpPosit.getRow();
            int col = warpPosit.getCol();
            player.setPosition(row,col);
            showHelp();

        } else if (roomNumber == 3) {
            room3.draw();
            warpPosit = room3.getPlayerStart();
            int row = warpPosit.getRow();
            int col = warpPosit.getCol();
            player.setPosition(row,col);
            showHelp();

        } else {
            //defaults to room 1 at the moment
            room.draw();
            showHelp();
        }
    }

    // returns a Box if the player is on it -- otherwise null
    private Box checkForBox() {
        Position playerLocation = player.getPosition();

        for (Box box : boxes) {
            if (playerLocation.equals(box.getPosition())) {
                return box;
            }
        }

        return null;
    }

    // returns a Warp if the player is on it -- otherwise null
    private Warp checkForWarp() {
        Position playerLocation = player.getPosition();

        for (Warp warp : warps) {
            if (playerLocation.equals(warp.getPosition())) {
                return warp;
            }
        }

        return null;
    }

    // check for battles and return false if player has died
    private boolean checkBattles() {
        Position playerLocation = player.getPosition();

        // look for an enemy that is close
        Enemy opponent = null;
        for (Enemy enemy : enemies) {
            if (playerLocation.isAdjacent(enemy.getPosition())) {
                opponent = enemy;
            }
        }

        // now do the battle
        if (opponent != null) {
            opponent.setBattleActive();
            return player.fight(opponent, room, enemies);
        }

        return true;
    }

    public void run() {
        // draw these for the first time now
        redrawMapAndHelp();
        rooms.add(room);
        rooms.add(room2);
        rooms.add(room3);

        boolean playing = true;
        while (playing) {
            // draw the entities
            for (Box box : boxes) {
                box.draw();
            }
            for (Enemy enemy : enemies) {
                enemy.draw();
            }
            for (Warp warp : warps) {
                warp.draw();
            }
            player.draw();

            // read a key from the user
            //cant do rooms.get(currentRoom.getRoom().getRows() since getRoom returns an int and getRows wants a room
            //maybe theres a way to grab the room number and then modify room, room2, or room3, but we have to take
            //into account that these are 3 different objects of different classes
            Terminal.warpCursor(room.getRows() + 1, 0);
            Key key = Terminal.getKey();
            playing = handleKey(key);

            // clear status by default
            setStatus("");

            // move the enemies
            for (Enemy enemy : enemies) {
                enemy.walk(room);
            }

            // check for battles
            if (!checkBattles()) {
                setStatus("You have been killed :(\n\r");
                playing = false;
            }

            // check if we are on a box and print what's in it
            Box thingHere = checkForBox();
            if (thingHere != null) {
                setStatus("Here you find: " + thingHere.getItem().getName() + " Weight: " + thingHere.getItem().getWeight() + " Value: " + thingHere.getItem().getValue() + " Strength: " + thingHere.getItem().getStrength());
            }

            // copied the same idea as above however it may make sense to make this bit below its own method
            // check if we are on a warp, print question, and store return response from user
            Warp aWarp = checkForWarp();
            if (aWarp != null) {
                setStatus("Would you like to go to the next room? Y or N: ");
                // asking for the response
                Scanner response = new Scanner(System.in);
                String answer = response.next();
                if (answer.equalsIgnoreCase("Y")) {
                    roomNumber++;
                    //System.out.println("Test sat");
                    redrawMapAndHelp();
                }
                // else do nothing

            }
        }
    }
}

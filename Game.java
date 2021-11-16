// Game.java
// contains logic for running the Game

import java.util.ArrayList;
import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class Game {

    // creating an int field that is used to call the appropriate room
    // starts at room 1
    private int roomNumber = World.instance().getRoom();
    private Room room;
    // new room objects below and Position used for warp
    private Room2 room2;
    private Room3 room3;
    private Position warpPosit;
    private Player player;
    private ArrayList<Box> boxes;
    private ArrayList<Enemy> enemies;
    // warps is instantiated in to Room class
    private ArrayList<Warp> warps;
    // made this a World arraylist so that all 3 room classes
    // can fit inside it, but we may need to change this
    private ArrayList<World> rooms = new ArrayList<>();
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
        //Terminal.pause(1.5);
        Terminal.warpCursor(17, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(18, 36);//38
        System.out.print("* A land of mystical wonder and danger presents itself around every corner. *");
        //Terminal.pause(1.5);
        Terminal.warpCursor(19, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(20, 36);//38
        System.out.print("* The goal of the game is to survive and collect as many items as possible. *");
        //Terminal.pause(1.5);
        Terminal.warpCursor(21, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(22, 36);//46
        System.out.print("*         To advance to the next floor you must kill all the enemies.       *");
        //Terminal.pause(1.5);
        Terminal.warpCursor(23, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(24, 36);//46
        System.out.print("*         Beware of the dungeons enemies or you will surely perish!         *");
        Terminal.warpCursor(25, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(26, 36);
        System.out.print("*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*");

        //setting the players name
        Terminal.warpCursor(28, 58);
        System.out.print("What is your name adventurer? ");
        Scanner in = new Scanner(System.in);
        Terminal.warpCursor(28, 88);
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
                "Restore: r",
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
        for (int i = 12; i < 16; i++) {
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
            //debug menu used for printing
            case b:
                Terminal.clear();
                Terminal.setForeground(Color.RED);
                //title prompt for information
                System.out.printf("Welcome to the debug menu XuX");
                Terminal.reset();
                //put test statement below this line
                System.out.print("\n\rThese are the current enemies\n\r" + enemies.size());
                System.out.printf("\n\rPress any key to return...\n\r");
                Terminal.getKey();


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
                    PrintWriter pw = new PrintWriter(new File("save.txt"));
                    pw.println(player.getName());
                    pw.println(player.getHealth());
                    pw.println(player.getDamage());
                    pw.println(player.getProtection());
                    pw.println(player.getWeapon()); //used to make sure the game knows what weapon is equipped
                    pw.println(player.getArmor()); //used to make sure the game knows what armor is equipped

                    //this needs to be tested, hopefully it works
                    ArrayList<Item> inventory = player.getInventory().getItems();
                    for (Item item : inventory) {
                        pw.println(item);
                    }
                    pw.println("."); //delimiter used to mark the end of the inventory
                    pw.println(World.instance().getRoom());
                    for (Enemy enemy : enemies) { //enemies from room 1
                        pw.println(enemy);
                    }
                    pw.println("."); //marking end of enemies from room 1

                    //commenting out below for testing using just one enemy arraylist
                    //for (Enemy enemy : enemies2) {
                    //    pw.println(enemy);
                    //}
                    //pw.println("."); //marking end of enemies from room 2
                    //for (Enemy enemy : enemies3) {
                    //    pw.println(enemy);
                    //}

                    for (int i = 0; i < boxes.size(); )

                        pw.println("."); //marking end of enemies from room 3
                    pw.close();
                } catch (FileNotFoundException e) {
                    System.out.print("Could not save data");
                }
                // handle movement
            case r:
                //restore save data from file
            case LEFT:
                player.move(0, -1, room, room2, room3);
                break;
            case RIGHT:
                player.move(0, 1, room, room2, room3);
                break;
            case UP:
                player.move(-1, 0, room, room2, room3);
                break;
            case DOWN:
                player.move(1, 0, room, room2, room3);
                break;

            // and finally the quit command
            case q:
                return false;
        }

        return true;
    }

    //*** ATM this will allow you to advance rooms no matter what warp you go thru
    //*** MODIFYING THIS checks the room number and prints the appropriate room
    // this is called when we need to redraw the room and help menu
    // this happens after going into a menu like for choosing items
    private void redrawMapAndHelp() {
        roomNumber = World.instance().getRoom();
        if (roomNumber == 1) {
            room.draw();
            showHelp();
        } else if (roomNumber == 2) {
            boxes = room2.getBoxes();
            enemies = room2.getEnemies();
            warps = room2.getWarp();
            room2.draw();
            warpPosit = room2.getPlayerStart();
            int row = warpPosit.getRow();
            int col = warpPosit.getCol();
            player.setPosition(row, col);
            showHelp();

        } else if (roomNumber == 3) {
            // here we reset roomNumber to 0 because there are only 3 rooms so the next time the player goes to a warp
            // they will go back to room 1
            roomNumber = 0;
            //***I THINK WE ARE MISSING something here, maybe it will need to set the getRoom() method of World to zero, could
            // be done via returnRoom() three times but need to resolve other issues before testing this.
            boxes = room3.getBoxes();
            enemies = room3.getEnemies();
            warps = room3.getWarp();
            room3.draw();
            warpPosit = room3.getPlayerStart();
            int row = warpPosit.getRow();
            int col = warpPosit.getCol();
            player.setPosition(row, col);
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

        if (enemies.size() == 0) {
            for (Warp warp : warps) {
                if (playerLocation.equals(warp.getPosition())) {
                    return warp;
                }
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
            // cant do rooms.get(currentRoom.getRoom().getRows() since getRoom returns an int and getRows wants a room
            // maybe theres a way to grab the room number and then modify room, room2, or room3, but we have to take
            // into account that these are 3 different objects of different classes
            Terminal.warpCursor(room.getRows() + 1, 0);
            Key key = Terminal.getKey();
            playing = handleKey(key);

            // clear status by default
            setStatus("");

            // move the enemies
            for (Enemy enemy : enemies) {
                enemy.walk(room, room2, room3);
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
                if (enemies.size() == 0) {
                    setStatus("Would you like to go to the next room? Y or N: ");
                    // asking for the response
                    Scanner response = new Scanner(System.in);
                    String answer = response.next();
                    if (answer.equalsIgnoreCase("Y")) {
                        roomNumber = World.instance().roomUpdate();
                        redrawMapAndHelp();
                    }
                } else if (enemies.size() < 0 ) {
                    setStatus("Door is locked! Rip and Tear!");
                }
            }
        }
    }
}

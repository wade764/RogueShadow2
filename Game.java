// Game.java
// contains logic for running the Game

import java.util.ArrayList;
import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

/** Manages the Game object
 *
 */
public class Game {
    // making game a singleton
    private static Game theInstance;

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
    // used to save the size of boxes
    private int boxSize; 
    // used to save the size of enemies
    private int enemySize; 

    // warps is instantiated in to Room class
    private ArrayList<Warp> warps;
    private String name;

    // used if the player is on the final &
    private Boolean endOfDungeon = false;

    // used in showHelp() to set the color randomly only once
    private int justOnceHelp = 0;
    private int helpForeground = 0;

    // used in redrawMapAndHelp() to set the color randomly only once
    private int justOnce = 0;
    private int Foreground = 0;

    private boolean room2Ready = false;
    private boolean room3Ready = false;

    /** Creates a singleton for creating an instance of the game
     *
     * @return the instance of the game
     */
    public static synchronized Game instance() {
        if (theInstance == null) {
            theInstance = new Game();
        }
        return theInstance;
    }

    private Game() {
        room = new Room();
        room2 = new Room2();
        room3 = new Room3();
        warpPosit = new Position();

        player = new Player(room.getPlayerStart());
        boxes = room.getBoxes();
        enemies = room.getEnemies();
        warps = room.getWarp();

    }

    private void playerWon() {
        Terminal.clear();
        Terminal.setForeground(Color.YELLOW);
        for (int i = 0; i < 40; i++) {
            Terminal.warpCursor(i, 82);
            System.out.print("                                                                                               ");
        }
        Terminal.warpCursor(12, 30);
        System.out.print("");
        Terminal.warpCursor(13, 30);

        System.out.print("██╗   ██╗ ██████╗ ██╗   ██╗    ██╗    ██╗ ██████╗ ███╗   ██╗██╗");
        Terminal.warpCursor(14, 30);
        System.out.print("╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║    ██║██╔═══██╗████╗  ██║██║");
        Terminal.warpCursor(15, 30);
        System.out.print(" ╚████╔╝ ██║   ██║██║   ██║    ██║ █╗ ██║██║   ██║██╔██╗ ██║██║");
        Terminal.warpCursor(16, 30);
        System.out.print("  ╚██╔╝  ██║   ██║██║   ██║    ██║███╗██║██║   ██║██║╚██╗██║╚═╝");
        Terminal.warpCursor(17, 30);
        System.out.print("   ██║   ╚██████╔╝╚██████╔╝    ╚███╔███╔╝╚██████╔╝██║ ╚████║██╗");
        Terminal.warpCursor(18, 30);
        System.out.print("   ╚═╝    ╚═════╝  ╚═════╝      ╚══╝╚══╝  ╚═════╝ ╚═╝  ╚═══╝╚═╝");
        Terminal.warpCursor(40, 1);
        Terminal.pause(5);
    }

    private void gameOver() {

        Terminal.clear();

        Terminal.setForeground(Color.RED);

        for (int i = 0; i < 40; i++) {
            Terminal.warpCursor(i, 82);
            System.out.print("                                                                                               ");
        }
        Terminal.warpCursor(12, 30);
        System.out.print("");
        Terminal.warpCursor(13, 30);

        System.out.print("██╗   ██╗ ██████╗ ██╗   ██╗    ██████╗ ██╗███████╗██████╗ ");
        Terminal.warpCursor(14, 30);
        System.out.print("╚██╗ ██╔╝██╔═══██╗██║   ██║    ██╔══██╗██║██╔════╝██╔══██╗");
        Terminal.warpCursor(15, 30);
        System.out.print(" ╚████╔╝ ██║   ██║██║   ██║    ██║  ██║██║█████╗  ██║  ██║");
        Terminal.warpCursor(16, 30);
        System.out.print("  ╚██╔╝  ██║   ██║██║   ██║    ██║  ██║██║██╔══╝  ██║  ██║");
        Terminal.warpCursor(17, 30);
        System.out.print("   ██║   ╚██████╔╝╚██████╔╝    ██████╔╝██║███████╗██████╔╝");
        Terminal.warpCursor(18, 30);
        System.out.print("   ╚═╝    ╚═════╝  ╚═════╝     ╚═════╝ ╚═╝╚══════╝╚═════╝ ");
        Terminal.warpCursor(40, 1);
        Terminal.pause(5);
    }

    private void debugMenu() {
        Terminal.clear();

        //title prompt for information
        Terminal.warpCursor(1, 1);
        System.out.print("");
        Terminal.warpCursor(2, 1);
        System.out.print("  ██╗ ██╗          ██████╗ ███████╗██████╗ ██╗   ██╗ ██████╗     ███╗   ███╗███████╗███╗   ██╗██╗   ██╗          ██╗ ██╗ ");
        Terminal.warpCursor(3, 1);
        System.out.print(" ██╔╝██╔╝▄ ██╗▄    ██╔══██╗██╔════╝██╔══██╗██║   ██║██╔════╝     ████╗ ████║██╔════╝████╗  ██║██║   ██║    ▄ ██╗▄╚██╗╚██╗ ");
        Terminal.warpCursor(4, 1);
        System.out.print("██╔╝██╔╝  ████╗    ██║  ██║█████╗  ██████╔╝██║   ██║██║  ███╗    ██╔████╔██║█████╗  ██╔██╗ ██║██║   ██║     ████╗ ╚██╗╚██╗");
        Terminal.warpCursor(5, 1);
        System.out.print("╚██╗╚██╗ ▀╚██╔▀    ██║  ██║██╔══╝  ██╔══██╗██║   ██║██║   ██║    ██║╚██╔╝██║██╔══╝  ██║╚██╗██║██║   ██║    ▀╚██╔▀ ██╔╝██╔╝");
        Terminal.warpCursor(6, 1);
        System.out.print(" ╚██╗╚██╗  ╚═╝     ██████╔╝███████╗██████╔╝╚██████╔╝╚██████╔╝    ██║ ╚═╝ ██║███████╗██║ ╚████║╚██████╔╝      ╚═╝ ██╔╝██╔╝ ");
        Terminal.warpCursor(7, 1);
        System.out.print("  ╚═╝ ╚═╝          ╚═════╝ ╚══════╝╚═════╝  ╚═════╝  ╚═════╝     ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝           ╚═╝ ╚═╝  ");
        Terminal.warpCursor(8, 1);
        Terminal.reset();

        //put test statement below this line
        System.out.print("\n\rThese are the current enemies "+enemies.size()+"\n\r");
        //System.out.print("This is the players icon "+playerIcon);

        System.out.printf("\n\rPress any key to return...\n\r");
        Terminal.getKey();
    }

    private void showHelp() {

        //setting the color for help once per game
        if (justOnceHelp == 0) {
            justOnceHelp++;

            // setting the color randomly
            Random rng = new Random();
            helpForeground = rng.nextInt(6);
        }
        switch (helpForeground) {
            case 0: Terminal.setForeground(Color.CYAN);
                    break;
            case 1: Terminal.setForeground(Color.GREEN);
                    break;
            case 2: Terminal.setForeground(Color.MAGENTA);
                    break;
            case 3: Terminal.setForeground(Color.RED);
                    break;
            case 4: Terminal.setForeground(Color.WHITE);
                    break;
            case 5: Terminal.setForeground(Color.YELLOW);
                    break;
            default: Terminal.setForeground(Color.RED);
        }

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

    protected boolean handleKey(Key key) {
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
                debugMenu();
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
                save();
                break;
            case r:
                //restore save data from file
                File file = new File("save.txt");
                try {

                    Terminal.clear();

                    Scanner in = new Scanner(file);
                    roomNumber = in.nextInt();
                    enemySize = in.nextInt();
                    boxSize = in.nextInt();

                    // needed because calling nextInt() leaves the cursor on the same line
                    in.nextLine();

                    player = new Player(in);

                    //read in enemies on current floor
                    for (int i = 0; i < enemySize; i++) { 
                        enemies.set(i, new Enemy(in));
                    }
                    //read in items on current floor
                    for (int i = 0; i < boxSize; i++) { 
                        boxes.set(i, new Box(in));
                    }
                    //consuming a delimeter
                    in.nextLine();
                    
                    // redrawing the correct room
                    roomNumber = World.instance().getRoom();
                    if (roomNumber == 1) {
                        room = new Room(in);
                        room.draw();
                    }
                    else if (roomNumber == 2) {
                        room2 = new Room2(in);
                        room2.draw();
                    }
                    else if (roomNumber == 3) {
                        room3 = new Room3(in);
                        room3.draw();
                    }

                        redrawMapAndHelp();

                    } catch (FileNotFoundException e) {
                        Terminal.warpCursor(40,0);
                        System.out.print("Save data does not exist"); //needs to be formatted
                        Terminal.pause(2);
                    }

                    break;
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

        // this is called when we need to redraw the room and help menu
        // this happens after going into a menu like for choosing items
    private void redrawMapAndHelp() {
        //setting the color for the game once
        if (justOnce == 0) {
            justOnce++;

            // setting the color randomly
            Random rng = new Random();
            Foreground = rng.nextInt(6);
        }
        switch (Foreground) {
            case 0: Terminal.setForeground(Color.CYAN);
                    break;
            case 1: Terminal.setForeground(Color.GREEN);
                    break;
            case 2: Terminal.setForeground(Color.MAGENTA);
                    break;
            case 3: Terminal.setForeground(Color.RED);
                    break;
            case 4: Terminal.setForeground(Color.WHITE);
                    break;
            case 5: Terminal.setForeground(Color.YELLOW);
                    break;
            default: Terminal.setForeground(Color.RED);
        }
        if (roomNumber == 1) {
            room.draw();
            showHelp();
        } else if (roomNumber == 2) {
            if (!room2Ready) {
                initializeRoom2();
            }
            room2.draw();
            showHelp();
        } else if (roomNumber == 3) {
            if (!room3Ready) {
                initializeRoom3();
            }
            room3.draw();
            showHelp();
        } else {
            //defaults to room 1 at the moment
            room.draw();
            showHelp();
        }
    }

    // creating a method that will initialize the values for Room 2
    private boolean initializeRoom2() {
            Terminal.clear();
            boxes = room2.getBoxes();
            enemies = room2.getEnemies();
            warps = room2.getWarp();
            warpPosit = room2.getPlayerStart();
            int row = warpPosit.getRow();
            int col = warpPosit.getCol();
            player.setPosition(row, col);
            room2Ready = true; 
        return room2Ready;
    }

    // creating a method that will initialize the values for Room 3
    private boolean initializeRoom3() {
            Terminal.clear();
            boxes = room3.getBoxes();
            enemies = room3.getEnemies();
            warps = room3.getWarp();
            warpPosit = room3.getPlayerStart();
            int row = warpPosit.getRow();
            int col = warpPosit.getCol();
            player.setPosition(row, col);
            room3Ready = true; 
        return room3Ready;
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
                gameOver();
                playing = false;
            }
            if (playing) {
                showHelp();
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
                    setStatus("The door unlocked");
                    Terminal.pause(2);
                    if (!endOfDungeon) {
                        setStatus("Would you like to go to the next room? Y or N: ");
                        // asking for the response
                        Scanner response = new Scanner(System.in);
                        String answer = response.next();
                        if (answer.equalsIgnoreCase("Y")) {
                            if (roomNumber < 3) {
                                roomNumber = World.instance().roomUpdate();
                                player.resetHP(); //resets player's hp when they go to the next floor
                                redrawMapAndHelp();
                                save();
                            }
                            else {
                                playing = false;
                            }
                        }
                    } else if (endOfDungeon) {
                        // the game is over the player has won!
                        playerWon();
                        playing = false;
                    }
                } else if (enemies.size() > 0 ) { //if not all of the enemies in the room are dead yet
                    setStatus("Door is locked! Rip and Tear!");
                }
            }
        }
    }

    /** Creates a text file that data from the game is written into with a printwriter. This file stores the number
     * of the room that the player last saved in, along with the number of enemies and boxes in that room. The method
     * also saves data about the player, the enemies and boxes on the map, and the map room layout itself to the file
     *
     */
    public void save() {
        try {
            PrintWriter pw = new PrintWriter("save.txt");
            pw.println(roomNumber);
            enemySize = enemies.size(); //helps with loading in save data
            boxSize = boxes.size();
            pw.println(enemySize);
            pw.println(boxSize);
            // Saves the player as an Entity
            player.save(pw);
            //info about enemies on the floor
            for (Enemy enemy : enemies) {
                enemy.save(pw);
            }
            //info about items on the floor
            for (Box box : boxes) {
                box.save(pw);
            }
            pw.println(".");
            if (roomNumber == 1) {
                room.save(pw);
            }
            else if (roomNumber == 2) {
                room2.save(pw);
            }
            else {
                room3.save(pw);
            }
            pw.close(); //closes the printwriter

            //Prints message to display
            Terminal.warpCursor(40,0);
            setStatus("Your game was saved");
            Terminal.pause(2);

        } catch (FileNotFoundException e) {
            Terminal.warpCursor(40,0);
            System.out.print("Could not save data");
            Terminal.pause(2);
        }
    }
}

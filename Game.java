// Game.java
// contains logic for running the Game

import java.util.ArrayList;
import ansi_terminal.*;
import java.util.Scanner;

public class Game {
    private Room room;
    private Player player;
    private ArrayList<Box> boxes;
    private ArrayList<Enemy> enemies;
    private ArrayList<Warp> warps;

    private String name;

    public Game() {
        room = new Room();
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
            "Quit: q"
        };
        Terminal.setForeground(Color.GREEN);
        for (int row = 0; row < cmds.length; row++) {
            Terminal.warpCursor(row + 1, room.getCols() + 1);
            System.out.print(cmds[row]);
        }

        // adding the player info below the commands
        Terminal.warpCursor(10, 61);
        System.out.print("\nName: " + player.getName());
        Terminal.warpCursor(11, 61);
        System.out.print("\nHP: " + player.getHealth());
        Terminal.warpCursor(12, 61);
        System.out.print("\nStrength: " + player.getDamage());
        Terminal.warpCursor(13, 61);
        System.out.println("\nDefense: " + player.getProtection());

        Terminal.reset();
    }

    // printing the command and additional info to the screen
    //public void drawInfo() {
    //Terminal.setForeground(Color.CYAN);
    //System.out.print("Commands:");
    //System.out.print("Move: arrow keys");
    //System.out.print("Pickup: p");
    //System.out.println("Attack: a");
    //System.out.println("View inventory: i");
    //System.out.print("Player stats:");


    // this may make more sense to put in a different method 

    //System.out.print("Enemy Health:");
    //if (dragon.getEnemyHP() > 0) {
    //    System.out.print("Dragon: " + dragon.getEnemyHP());
    //} else {
    //    System.out.print("Dragon: dead (X_X)");
    //}
    //Terminal.warpCursor(15, 85);
    //if (goblin.getEnemyHP() > 0) {
    //    System.out.print("Goblin: " + goblin.getEnemyHP());
    //} else {
    //    System.out.print("Goblin: dead (X_X)"); 
    //}
    //Terminal.warpCursor(40, 0);

    //Terminal.reset();
    //}

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

    // this is called when we need to redraw the room and help menu
    // this happens after going into a menu like for choosing items
    private void redrawMapAndHelp() {
        room.draw();
        showHelp();
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
                enemy.walk(room);
            }

            // check for battles
            if (!checkBattles()) {
                setStatus("You have been killed :(\n\r");
                playing = false;
            }
            
            //***SEE BELOW*** this could be changed to check if the box is an item or a warp
            //***EDIT*** the warp is now no longer a Box but a Warp that extends Entity, still working on this step

            // check if we are on a box and print what's in it
            Box thingHere = checkForBox();
            if (thingHere != null) {
                setStatus("Here you find: " + thingHere.getItem().getName() + " Weight: " + thingHere.getItem().getWeight() + " Value: " + thingHere.getItem().getValue() + " Strength: " + thingHere.getItem().getStrength());
            }
        }
    }
}

import ansi_terminal.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    // adding the items and item coordinates as a field
    private static Item item1 = ItemGenerator.generate();
    private static ArrayList<Integer> item1Coors;
    private static Item item2 = ItemGenerator.generate();
    private static ArrayList<Integer> item2Coors;
    private static Item item3 = ItemGenerator.generate();
    private static ArrayList<Integer> item3Coors;
    private static Item item4 = ItemGenerator.generate();
    private static ArrayList<Integer> item4Coors;
    private static Item item5 = ItemGenerator.generate();
    private static ArrayList<Integer> item5Coors;
    private static Item item6 = ItemGenerator.generate();
    private static ArrayList<Integer> item6Coors;

    // changed the player name so that it will print to the display
    private static String name = "Hero";
    private static boolean pause = false;

    // making a playerCoordinates variable
    private static ArrayList<Integer> pcoordinates = new ArrayList<>(); //player's coordinates
    private static ArrayList<Integer> dcoordinates = new ArrayList<>(); //dragon's coordinates
    private static ArrayList<Integer> gcoordinates = new ArrayList<>(); //goblin's coordinates
    private static char cell;
    private static Player thePlayer;
    private static Enemy goblin;
    private static Enemy dragon;

    private static String[] grid = {
        "##################                          #########################           ",
        "##              ##                          ##                     ##           ",
        "##              ##                          ##                     ##           ",
        "##              ##################          ##  i                  ##           ",
        "##                              ##          ##                     ##           ",
        "##              ##############  ##          #####################  ##           ",
        "##              ##          ##  ##                             ##  ##           ",
        "##################          ##  #####################          ##  ##           ",
        "                            ##                     ##          ##  ##           ",
        "                            ##                     ##          ##  ##           ",
        "                            ##                     ##          ##  ##           ",
        "                            ##    i                ##          ##  ##           ",
        "                            ##                     ##          ##  ##           ",
        "                            #################  ######          ##  ##           ",
        "                                           ##  ##              ##  ##           ",
        "                                           ##  ##              ##  ##           ",
        "                              ###############  ##################  ######       ",
        "                              ##                           i           ##       ",
        "                              ##  i                                    ##       ",
        "                              ##                                       ##       ",
        "    ############################                                       ##       ",
        "    ##                                                                 ##       ",
        "    ##  ########################                                 i     ##       ",
        "    ##  ##                    ##                                       ##       ",
        "    ##  ##                    ####################  #####################       ",
        "    ##  ##                                      ##  ##                          ",
        "    ##  ##                                      ##  ##                          ",
        "    ##  ##                                      ##  ##                          ",
        "    ##  ##                                      ##  ##                          ",
        "    ##  ##                                      ##  ##                          ",
        "    ##  ##                                      ##  ##                          ",
        "    ##  ##                                 #######  #######                     ",
        "    ##  ##                                 ##            ##                     ",
        "######  ####                               ##    i       ##                     ",
        "##        ##                               ##            ##                     ",
        "##        ##                               ################                     ",
        "##        ##                                                                    ",
        "##        ##                                                                    ",
        "##        ##                                                                    ",
        "############                                                                    "
    };

    // We need a getter for the cell in order to determine if it contains "#"
    public static char getCell(int row, int col) {
        cell = grid[row].charAt(col);

        return cell;
    }

    // creating a getter for the player coordinates that the Enemy classes uses
    // to determine if the enemy can move or not
    public ArrayList<Integer> getPlayerLocation() {
        return pcoordinates;
    }

    // creating a method that will get the location of the enemy based on the enemy name
    public ArrayList<Integer> getEnemyLocation(String enemy) {
        if (enemy.equals("Dragon")) {
            return dcoordinates;
        } else if (enemy.equals("Goblin")) {
            return gcoordinates;
        }
        return null;
    }

    public static void drawMap(int hrow, int hcol) {
        Terminal.clear();
        for (int row = 0; row < 40; row++) {
            for (int col = 0; col < 80; col++) {
                if (row == hrow && col == hcol) {
                    Terminal.setForeground(Color.MAGENTA);
                    System.out.print("@");
                    Terminal.reset();
                }
                //places the dragon on the map if it's still alive
                else if (row == dcoordinates.get(0) && col == dcoordinates.get(1)) {
                    if (dragon.getEnemyStatus()) {
                        Terminal.setForeground(Color.CYAN);
                        System.out.print("D");
                    }
                    else {
                        System.out.print('\u2588'); //prints block symbol at (0, 0) if enemy is dead
                    }
                    Terminal.reset();
                }
                //places the goblin on the map if it's still alive
                else if (row == gcoordinates.get(0) && col == gcoordinates.get(1)) {
                    if (goblin.getEnemyStatus()) {
                        Terminal.setForeground(Color.CYAN);
                        System.out.print("G");
                    }
                    else {
                        System.out.print('\u2588'); //prints block symbol at (0, 0) if enemy is dead
                    }
                    Terminal.reset();
                }
                else {
                    cell = grid[row].charAt(col);
                    if (cell == '#') {
                        // a unicode block symbol
                        System.out.print('\u2588');
                    } else {
                        System.out.print(cell);
                    }
                }
            }
            System.out.print("\n\r");            
        }
        // This will print the name of the item if the player is standing on top of it
        printItemName();
    }

    public static void drawInfo() {
        // we can use warpCursor to move around the screen                                                                                               
        Terminal.setForeground(Color.CYAN);
        Terminal.warpCursor(1, 82);
        System.out.print("Commands:");
        Terminal.warpCursor(2, 85);
        System.out.print("Move: arrow keys");
        Terminal.warpCursor(3, 85);
        System.out.print("Pickup: p");
        Terminal.warpCursor(4, 85);
        System.out.println("Attack: a");
        Terminal.warpCursor(5, 85);
        System.out.println("View inventory: i");
        Terminal.warpCursor(7, 82);
        System.out.print("Player stats:");
        Terminal.warpCursor(8, 85);
        System.out.print("Name: " + thePlayer.getPlayerName());
        Terminal.warpCursor(9, 85);
        System.out.print("HP: " + thePlayer.getPlayerHP());
        Terminal.warpCursor(10, 85);
        System.out.print("Strength: " + thePlayer.getPlayerStrength());
        Terminal.warpCursor(11, 85);
        System.out.println("Defense: " + thePlayer.getPlayerDefense());

        Terminal.warpCursor(13, 82);
        System.out.print("Enemy Health:");
        Terminal.warpCursor(14, 85);
        if (dragon.getEnemyHP() > 0) {
            System.out.print("Dragon: " + dragon.getEnemyHP());
        } else {
            System.out.print("Dragon: dead (X_X)");
        }
        Terminal.warpCursor(15, 85);
        if (goblin.getEnemyHP() > 0) {
            System.out.print("Goblin: " + goblin.getEnemyHP());
        } else {
            System.out.print("Goblin: dead (X_X)"); 
        }
        Terminal.warpCursor(40, 0);

        Terminal.reset();
    }

    // We need a getter method that returns the state of pause
    public static boolean getPause() {
        return pause;
    }

    // this method prints the name of the item to the screen if the player is located at an item's posit
    public static void printItemName() {
        cell = Game.getCell(pcoordinates.get(0), pcoordinates.get(1));
        Terminal.setForeground(Color.GREEN);
        if (pcoordinates.equals(item1Coors) && cell == 'i') {
            Terminal.warpCursor(20, 82);
            System.out.print(item1.toString() + "\r\n");
        }

        else if (pcoordinates.equals(item2Coors) && cell == 'i'){
            Terminal.warpCursor(20,82);
            System.out.print(item2.toString() + "\r\n");
        }

        else if (pcoordinates.equals(item3Coors) && cell == 'i'){
            Terminal.warpCursor(20,82);
            System.out.print(item3.toString() + "\r\n");
        }

        else if (pcoordinates.equals(item4Coors) && cell == 'i'){
            Terminal.warpCursor(20,82);
            System.out.print(item4.toString() + "\r\n");
        }

        else if (pcoordinates.equals(item5Coors) && cell == 'i'){
            Terminal.warpCursor(20,82);
            System.out.print(item5.toString() + "\r\n");
        }

        else if (pcoordinates.equals(item6Coors) && cell == 'i'){
            Terminal.warpCursor(20,82);
            System.out.print(item6.toString() + "\r\n");
        }
        Terminal.reset();
    }

    public static boolean game() {
        // very important, I modified the game class to return a boolean so that main() knows when to quit

        boolean done = false;
        Terminal.warpCursor(26, 58);
        System.out.print("What is your name adventurer? ");
        //Changed the way the program reads the player name in so that the line stays in positon
        Scanner in = new Scanner(System.in);
        Terminal.warpCursor(26, 88);
        name = in.next();

        //creating new Attack objects
        Attack dieFiend = new Attack();
        Attack takeThisPlayer = new Attack();

        //instantiating a new Player() and calling Player() with the provided name
        thePlayer = new Player();
        thePlayer.Player(name);
        Inventory inventory = new Inventory(30, thePlayer);
        dragon = new Enemy("Dragon", 80, 25); //denoted with D on map
        goblin = new Enemy("Goblin", 60, 25); //denoted with G on map

        //hardcodes starting items
        Item startWeapon = new Item (ItemType.Weapon, "Gold Sword", 5, 24, 25);
        Item startArmor = new Item (ItemType.Armor, "Metal Suit", 10, 20, 15);
        Item startOther = new Item (ItemType.Other, "Night Goggles", 1, 5, 0);

        inventory.add(startWeapon);
        inventory.add(startArmor);
        inventory.add(startOther);

        //items and their coordinates
        item1Coors = new ArrayList<>();
        item1Coors.add(3);
        item1Coors.add(48);

        item2Coors = new ArrayList<>();
        item2Coors.add(11);
        item2Coors.add(34);

        item3Coors = new ArrayList<>();
        item3Coors.add(17);
        item3Coors.add(59);

        item4Coors = new ArrayList<>();
        item4Coors.add(18);
        item4Coors.add(34);

        item5Coors = new ArrayList<>();
        item5Coors.add(22);
        item5Coors.add(65);

        item6Coors = new ArrayList<>();
        item6Coors.add(33);
        item6Coors.add(49);

        //The following code is used for the initial posistion of the player and enemies
        //the positions are stored in an ArrayList
        int hrow = 4, hcol = 4;
        pcoordinates.add(hrow);
        pcoordinates.add(hcol);

        int drow = 2, dcol = 52; //dragon corrdinates
        dcoordinates.add(drow);
        dcoordinates.add(dcol);

        int grow = 37, gcol = 4; //goblin coordinates
        gcoordinates.add(grow);
        gcoordinates.add(gcol);

        while (!done) {
            drawMap(hrow, hcol);
            drawInfo();

            //creating variables to compare the location of the player to the enemy
            int dragonRowL = dcoordinates.get(0) - 1;
            int dragonRowR = dcoordinates.get(0) + 1;
            int dragonColL = dcoordinates.get(1) - 1;
            int dragonColR = dcoordinates.get(1) + 1;
            int goblinRowL = gcoordinates.get(0) - 1;
            int goblinRowR = gcoordinates.get(0) + 1;
            int goblinColL = gcoordinates.get(1) - 1;
            int goblinColR = gcoordinates.get(1) + 1;
            int pRow = pcoordinates.get(0);
            int pCol = pcoordinates.get(1);

            //this "blocks" until we get a key from the user
            Key key = Terminal.getKey();
            switch (key) {
                case ESCAPE:
                    done = true;
                    //we must return done here in order to quit the game
                    return done;

                case LEFT:
                    cell = grid[hrow].charAt(hcol - 1);
                    if (cell == '#' || (pRow == dcoordinates.get(0) && pCol == dragonColR ) || (pRow == gcoordinates.get(0) && pCol == goblinColR) ) {
                        System.out.println("You cannot move in the direction!");
                        Terminal.pause(1.5);
                    } else {
                        //adjusting the players coordinates
                        int currentPosition = hcol - 1;
                        pcoordinates.set(1,currentPosition);
                        hcol--;
                    }
                    break;
                case RIGHT:
                    cell = grid[hrow].charAt(hcol + 1);
                    if (cell == '#' || (pRow == dcoordinates.get(0) && pCol == dragonColL) || (pRow == gcoordinates.get(0) && pCol == goblinColL) ) {
                        System.out.println("You cannot move in the direction!");
                        Terminal.pause(1.5);
                    } else {
                        //adjusting the players coordinates
                        int currentPosition = hcol + 1;
                        pcoordinates.set(1,currentPosition);
                        hcol++;
                    }
                    break;
                case UP:    
                    cell = grid[hrow - 1].charAt(hcol);
                    if (cell == '#' || (pCol == dcoordinates.get(1) && pRow == dragonRowR) || (pCol == gcoordinates.get(1) && pRow == goblinRowR) ) {
                        System.out.println("You cannot move in the direction!");
                        Terminal.pause(1.5);
                    } else {
                        //adjusting the players coordinates
                        int currentPosition = hrow - 1;
                        pcoordinates.set(0,currentPosition);
                        hrow--;
                    }
                    break;
                case DOWN: 
                    cell = grid[hrow + 1].charAt(hcol);
                    if (cell == '#' || (pCol == dcoordinates.get(1) && pRow == dragonRowL) || (pCol == gcoordinates.get(1) && pRow == goblinRowL) ) {
                        System.out.println("You cannot move in the direction!");
                        Terminal.pause(1.5);
                    } else {
                        //adjusting the players coordinates
                        int currentPosition = hrow + 1;
                        pcoordinates.set(0,currentPosition);
                        hrow++;
                    }
                    break;
                case p:
                    cell = grid[pcoordinates.get(0)].charAt(pcoordinates.get(1));
                    if (cell == 'i') {
                        boolean result;
                        Terminal.setForeground(Color.GREEN);
                        //these all edit the map so that the i that corresponds to an item no longer appears if the item gets picked up
                        if (pcoordinates.equals(item1Coors)) {
                            result = inventory.add(item1);	
                            Terminal.warpCursor(20,82);
                            System.out.print("                                                                 ");
                            Terminal.warpCursor(20, 82);
                            if (result == true) {
                                System.out.print("The " + item1.getName() + " was added to your inventory");
                                item1.setStatus();
                                grid[3] = "##              ##################          ##                     ##           ";
                            }
                            else {
                                System.out.print("The " + item1.getName() + " was not added to your inventory");
                                Terminal.warpCursor(22, 82);
                                System.out.print("You have reached the maximum weight limit that you can carry");
                                Terminal.warpCursor(23, 82);
                                System.out.print("Please drop an item before picking up this one");
                            }
                        }
                        else if (pcoordinates.equals(item2Coors)){
                            result = inventory.add(item2);
                            Terminal.warpCursor(20,82);
                            System.out.print("                                                                 ");
                            Terminal.warpCursor(20, 82);
                            if (result == true) {
                                System.out.print("The " + item2.getName() + " was added to your inventory");
                                item2.setStatus();
                                grid[11] = "                            ##                     ##          ##  ##           ";
                            }
                            else {
                                System.out.print("The " + item2.getName() + " was not added to your inventory");
                                Terminal.warpCursor(22, 82);
                                System.out.print("You have reached the maximum weight limit that you can carry");
                                Terminal.warpCursor(23, 82);
                                System.out.print("Please drop an item before picking up this one");
                            }
                        }

                        else if (pcoordinates.equals(item3Coors)){
                            result = inventory.add(item3);	
                            Terminal.warpCursor(20,82);
                            System.out.print("                                                                 ");
                            Terminal.warpCursor(20, 82);
                            if (result == true) {
                                System.out.print("The " + item3.getName() + " was added to your inventory");
                                item3.setStatus();
                                grid[17] = "                              ##                                       ##       ";
                            }
                            else {
                                System.out.print("The " + item3.getName() + " was not added to your inventory");
                                Terminal.warpCursor(22, 82);
                                System.out.print("You have reached the maximum weight limit that you can carry");
                                Terminal.warpCursor(23, 82);
                                System.out.print("Please drop an item before picking up this one");
                            }
                        }

                        else if (pcoordinates.equals(item4Coors)){
                            result = inventory.add(item4);
                            Terminal.warpCursor(20,82);
                            System.out.print("                                                                 ");
                            Terminal.warpCursor(20, 82);
                            if (result == true) {
                                System.out.print("The " + item4.getName() + " was added to your inventory");
                                item4.setStatus();
                                grid[18] = "                              ##                                       ##       ";
                            }
                            else {
                                System.out.print("The " + item4.getName() + "was not added to your inventory");
                                Terminal.warpCursor(22, 82);
                                System.out.print("You have reached the maximum weight limit that you can carry");
                                Terminal.warpCursor(23, 82);
                                System.out.print("Please drop an item before picking up this one");
                            }
                        }

                        else if (pcoordinates.equals(item5Coors)){
                            result = inventory.add(item5);
                            Terminal.warpCursor(20,82);
                            System.out.print("                                                                 ");
                            Terminal.warpCursor(20, 82);
                            if (result == true) {
                                System.out.print("The " + item5.getName() + " was added to your inventory");
                                item5.setStatus();
                                grid[22] = "    ##  ########################                                       ##       ";
                            }
                            else {
                                System.out.print("The " + item5.getName() + " was not added to your inventory");
                                Terminal.warpCursor(22, 82);
                                System.out.print("You have reached the maximum weight limit that you can carry");
                                Terminal.warpCursor(23, 82);
                                System.out.print("Please drop an item before picking up this one");
                            }
                        }

                        else if (pcoordinates.equals(item6Coors)){
                            result = inventory.add(item6);
                            Terminal.warpCursor(20,82);
                            System.out.print("                                                                 ");
                            Terminal.warpCursor(20, 82);
                            if (result == true) {
                                System.out.print("The " + item6.getName() + " was added to your inventory");
                                item6.setStatus();
                                grid[33] = "######  ####                               ##            ##                     ";
                            }
                            else {
                                System.out.print("The " + item6.getName() + " was not added to your inventory");
                                Terminal.warpCursor(22, 82);
                                System.out.print("You have reached the maximum weight limit that you can carry");
                                Terminal.warpCursor(23, 82);
                                System.out.print("Please drop an item before picking up this one");
                            }
                        }
                    }
                    else { //if the current cell doesn't have an i, then there is nothing to pick up
                        Terminal.warpCursor(20, 82);
                        System.out.print("There is nothing here");
                    }
                    Terminal.reset();
                    Terminal.pause(1.5);
                    break;

                case a:
                    if ( (pRow == dragonRowL && pCol == dcoordinates.get(1)) || (pRow == dragonRowR && pCol == dcoordinates.get(1)) || (pCol == dragonColL && pRow == dcoordinates.get(0))|| (pCol == dragonColR && pRow == dcoordinates.get(0)) ) {
                        Terminal.warpCursor(38, 82);
                        int playerDamage = takeThisPlayer.enemyAttack(thePlayer, dragon);
                        System.out.print("The dragon scorched thyn hide! " + name + " took " + playerDamage + " damage!\n\r");
                        Terminal.pause(2.5);
                        Terminal.warpCursor(39, 82);
                        // checking if the player is alive then attacking
                        if (thePlayer.getPlayerHP() > 0) {
                            int enemyDamage = dieFiend.playerAttack(thePlayer, dragon);
                            System.out.print(name + " rebounded and dealt " + enemyDamage + " damage!\n\r");
                            Terminal.pause(2.5);
                        }
                        dragon.setEnemyStatus(dragon.getEnemyHP()); //checks if the dragon is dead
                        //printing when the enemy is defeated
                        if (!dragon.getEnemyStatus()) {
                            Terminal.warpCursor(40, 82);
                            System.out.println("The dragon was killed!\n\r");
                            dcoordinates.set(0, 0); //moves enemy to (0, 0) to not block the space they died in)
                            dcoordinates.set(1, 0);
                            Terminal.pause(2.5);
                        }
                    } else if ( (pRow == goblinRowL && pCol == gcoordinates.get(1)) || (pRow == goblinRowR && pCol == gcoordinates.get(1)) || (pCol == goblinColL && pRow == gcoordinates.get(0))|| (pCol == goblinColR && pRow == gcoordinates.get(0)) ) {
                        Terminal.warpCursor(38, 82);
                        int playerDamage = takeThisPlayer.enemyAttack(thePlayer, goblin);
                        System.out.print("The goblin caught " + name + " off guard! " + name + " took " + playerDamage + " damage!\n\r");
                        Terminal.pause(2.5);
                        Terminal.warpCursor(39, 82);
                        // checking if the player is alive then attacking
                        if (thePlayer.getPlayerHP() > 0) {
                            int enemyDamage = dieFiend.playerAttack(thePlayer, goblin);
                            System.out.print(name + " took their revenge and dealt " + enemyDamage + " damage!\n\r"); 
                            Terminal.pause(2.5);
                        }
                        goblin.setEnemyStatus(goblin.getEnemyHP()); //checks if the goblin is dead
                        //printing when the enemy is defeated
                        if (!goblin.getEnemyStatus()) {
                            Terminal.warpCursor(40, 82);
                            System.out.println("The goblin was killed!\n\r");
                            gcoordinates.set(0, 0); //moves enemy to (0, 0) to not block the space they died in
                            gcoordinates.set(1, 0);
                            Terminal.pause(2.5);
                        }
                    } else {
                        Terminal.warpCursor(38, 82);
                        System.out.print("Attack out of range, try again!\n\r");
                        Terminal.pause(1.5);
                    }
                    break;
                case i:
                    if (pause == false) {
                        pause = true;
                        Terminal.setForeground(Color.CYAN);
                        Terminal.warpCursor(1, 82);
                        System.out.print("GAME PAUSED: Please press i again to resume game");
                        inventory.print();
                        key = Terminal.getKey(); //key pressed while in the inventory menu
                        if (key == Key.i) {
                            pause = false; //exits inventory menu and resumes the game
                        }
                        else if (key == Key.w) {
                            pause = false;
                            inventory.equipWeapon();
                        }
                        else if (key == Key.a) {
                            pause = false;
                            inventory.equipArmor();
                        }
                        else if (key == Key.d) {
                            pause = false;
                            inventory.drop();
                        }
                        else {
                            if (key != Key.i || key != Key.w || key != Key.a || key != Key.d) {

                                Terminal.warpCursor(38, 82);
                                System.out.print("Please enter a valid key.");
                                //warping cursor back to top of command area and making a counter to skip lines
                                int i = inventory.getSize();
                                // skipping the pause line
                                i++;
                                Terminal.warpCursor(i, 82);
                                key = Terminal.getKey();
                            }
                            //this is important to make sure the inventory can come up again
                            pause = false;
                        }
                        break;    
                    }
            }

            // Enemy must move after the player for the attack to land properly
            if (dragon.getEnemyStatus()) { 
                dcoordinates = dragon.enemyMove(dcoordinates,"Dragon");
            }
            if (goblin.getEnemyStatus()) {  
                gcoordinates = goblin.enemyMove(gcoordinates,"Goblin");
            }

            if (GameOver.playerDeath(thePlayer.getPlayerHP())) { //checks if the player is dead
                done = true;
            } else if (GameOver.enemyCount()) { //checks if all enemies are dead
                done = true;
            }
        }
        return done;
    }

    // Creating an ending screen that will run after the map clears
    public static void endScreen() {
        Terminal.clear();
        if (GameOver.playerDeath(thePlayer.getPlayerHP())) {
            for (int i = 0; i < 40; i++) {
                Terminal.warpCursor(i, 82);
                System.out.print("                                                                                               ");
            }
            Terminal.warpCursor(12, 30);
            System.out.print("██╗   ██╗ ██████╗ ██╗   ██╗    ██████╗ ██╗███████╗██████╗ ");
            Terminal.warpCursor(13, 30);
            System.out.print("╚██╗ ██╔╝██╔═══██╗██║   ██║    ██╔══██╗██║██╔════╝██╔══██╗");
            Terminal.warpCursor(14, 30);
            System.out.print(" ╚████╔╝ ██║   ██║██║   ██║    ██║  ██║██║█████╗  ██║  ██║");
            Terminal.warpCursor(15, 30);
            System.out.print("  ╚██╔╝  ██║   ██║██║   ██║    ██║  ██║██║██╔══╝  ██║  ██║");
            Terminal.warpCursor(16, 30);
            System.out.print("   ██║   ╚██████╔╝╚██████╔╝    ██████╔╝██║███████╗██████╔╝");
            Terminal.warpCursor(17, 30);
            System.out.print("   ╚═╝    ╚═════╝  ╚═════╝     ╚═════╝ ╚═╝╚══════╝╚═════╝");
        }
        else if (GameOver.enemyCount()) {
            for (int i = 0; i < 40; i++) {
                Terminal.warpCursor(i, 82);
                System.out.print("                                                                                               ");
            }
            Terminal.warpCursor(12, 30);
            System.out.print("██╗   ██╗ ██████╗ ██╗   ██╗    ██╗    ██╗ ██████╗ ███╗   ██╗██╗");
            Terminal.warpCursor(13, 30);
            System.out.print("╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║    ██║██╔═══██╗████╗  ██║██║");
            Terminal.warpCursor(14, 30);
            System.out.print(" ╚████╔╝ ██║   ██║██║   ██║    ██║ █╗ ██║██║   ██║██╔██╗ ██║██║");
            Terminal.warpCursor(15, 30);
            System.out.print("  ╚██╔╝  ██║   ██║██║   ██║    ██║███╗██║██║   ██║██║╚██╗██║╚═╝");
            Terminal.warpCursor(16, 30);
            System.out.print("   ██║   ╚██████╔╝╚██████╔╝    ╚███╔███╔╝╚██████╔╝██║ ╚████║██╗");
            Terminal.warpCursor(17, 30);
            System.out.print("   ╚═╝    ╚═════╝  ╚═════╝      ╚══╝╚══╝  ╚═════╝ ╚═╝  ╚═══╝╚═╝");
        }
        Terminal.warpCursor(40, 0);
    }
}

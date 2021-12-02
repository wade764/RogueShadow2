// Player.java

import ansi_terminal.*;
import java.io.PrintWriter;
import java.util.Scanner;

public class Player extends Character {
    private Inventory items;

    // default values for the player
    private static String name = "Hero";
    private static char playerIcon = '@';

    // Creating a static method that stores the name and icon of the player, it needs to be called before instantiating a player
    public static void setPlayerInfo() {
        //setting the players name
        Terminal.warpCursor(28, 58);
        System.out.print("What is your name adventurer? ");
        Scanner in = new Scanner(System.in);
        Terminal.warpCursor(28, 88);

        // initializing the name of the player
        Player.name = in.next();

        //printing an empty string to clear the terminal on the current line
        Terminal.warpCursor(28, 58);
        System.out.print("                                                                                    ");

        // Asking the player for their class
        Terminal.warpCursor(28, 58);
        System.out.print("Who are you? ");
        Terminal.warpCursor(28, 71);
        Player.playerIcon = in.next().charAt(0);
    }

    public Player(Position start) {

        super(start.getRow(), start.getCol(), playerIcon, Color.CYAN, 100);

        // we can carry 100 pounds of items
        items = new Inventory(100);

        // give them some basic stuff to start with
        items.addAndEquip(new Item(ItemType.Weapon, "Orb of Destruction", 3, 34, 56));
        items.addAndEquip(new Item(ItemType.Armor, "Silver Shield", 15, 18, 15));
    }

    @Override
    public int getHealth() {
            return super.hp;
        }

    @Override
    public int getDamage() {
        Item weapon = items.getEquippedWeapon();
        if (weapon != null) {
            return weapon.getStrength();
        } else {
            // if we have no weapon, our fists are pretty weak...
            return 1;
        }
    }

    @Override
    public String getName() {
            return name;
        }

    @Override
    public int getProtection() {
        Item armor = items.getEquippedArmor();
        if (armor != null) {
            return armor.getStrength();
        } else {
            // without armor, we have no protection
            return 0;
        }
    }

    //used for saving the player's current weapon
    public Item getWeapon() {
        return items.getEquippedWeapon();
    }

    //used for saving the player's current armor
    public Item getArmor() {
        return items.getEquippedArmor();
    }

    public Inventory getInventory() {
        return items;
    }

    /** Resets the player's hp when they go from rooms 1-3
     *
     */
    public void resetHP() {
        hp = 100;
    }

    /** Resets the player's hp and gives them extra hp for the boss fight
     *
     */
    public void resetHPBossFight() {
        hp = 100;
    }

    /** Writes the player's info and their inventory to the save file
     *
     * @param out the printwriter used to write data to a file
     */
    public void save(PrintWriter out) {
        super.save(out);
        out.println(name);
        items.save(out);
    }

    /** A constructor used for reading in data about the player from the save file
     *
     * @param in the scanner used to read in data from the file
     */
    public Player(Scanner in) {
        super(in);
        name = in.nextLine();
        items = new Inventory(in);
    }
}

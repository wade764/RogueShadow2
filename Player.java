import ansi_terminal.*;
import java.io.PrintWriter;
import java.util.Scanner;

/** This class creates a Player object and gives them a weapon and armor to start the game with
 *
 */
public class Player extends Character {
    private Inventory items;

    // default values for the player
    private static String name = "Hero";
    private static char playerIcon = '@';

    public Player(Position start) {
        super(start.getRow(), start.getCol(), playerIcon, Color.CYAN, 50);

        // we can carry 100 pounds of items
        items = new Inventory(100);

        // give them some basic stuff to start with
        items.addAndEquip(new Item(ItemType.Weapon, "Orb of Destruction", 3, 34, 27));
        items.addAndEquip(new Item(ItemType.Armor, "Silver Shield", 15, 18, 17));
    }

    /** Creating a static method that stores the name and icon of the player, it needs to be called before
     * instantiating a player
     *
     */
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

    @Override
    public int getHealth() {
        return super.hp;
    }

    @Override
    public int getDamage() {
        int totalDamage;
        Item weapon = items.getEquippedWeapon();
        Item other = items.getEquippedOther();
        if (weapon != null && other != null) {
            totalDamage = other.getStrength() +  weapon.getStrength();
            return totalDamage;
            //weapon.getStrength();
        } else if (weapon == null && other != null){
            totalDamage = other.getStrength();
            // if we have no weapon, our fists are pretty weak...
            return totalDamage;
        } else if(weapon != null && other == null){
            // if we have no weapon, our fists are pretty weak...
            totalDamage= weapon.getStrength();
            return totalDamage;
        }
        else {
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

    public Inventory getInventory() {
        return items;
    }

    /** Resets the player's hp when they move to the next room
     *
     */
    public void resetHP() {
        hp = 50;
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

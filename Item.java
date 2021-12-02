// Item.java
// this class represents a single item, it could be an equippable
// thing like weapon or ring, or something generic

import ansi_terminal.Terminal;
import java.util.Scanner;
import java.io.PrintWriter;

public class Item {

    // what sort of item it is
    private ItemType type;

    // modified to have a temp name
    // the name of the item as shown to the user
    private String name = "temp";

    // how much it weighs (player can only carry so much)
    private int weight;

    // how much the item is worth for buying/selling
    private int value;

    // the item's strength - this differs based on the type
    // for a weapon, it's damage
    // for armor, it's protection
    private int strength;

    public Item(ItemType type, String name, int weight, int value, int strength) {
        this.type = type;
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.strength = strength;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public int getStrength() {
        return strength;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    @Override
        public String toString() {
            return name + " " + weight + " " + value + " " + strength;
        }

    /** Writes the item's name, type, weight, value, and strength to the save file
     *
     * @param out the printwriter used to write data to a file
     */
    public void save(PrintWriter out) {
        out.println(name);
        out.println(type);
        out.println(weight);
        out.println(value);
        out.println(strength);
    }

    /** A constructor used for reading in data about the item's name, type, weight, value, and strength from the
     * save file
     *
     * @param in the scanner used to read in data from the file
     */
    public Item(Scanner in) {

        Terminal.clear();
        String temp = "";
        name = in.nextLine();

        String t = in.nextLine();
        if (t.equals("Weapon")) {
            type = ItemType.Weapon;
        }
        else if (t.equals("Armor")) {
            type = ItemType.Armor;
        }
        else if (t.equals("Other")) {
            type = ItemType.Other;
        }

        temp = in.nextLine();
        weight = Integer.parseInt(temp);
        temp = in.nextLine();
        value = Integer.parseInt(temp);
        temp = in.nextLine();
        strength = Integer.parseInt(temp);
    }
}

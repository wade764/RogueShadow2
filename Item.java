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

    // TESTING FOR LOAD
    protected static int counter = 0;

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

    public void save(PrintWriter out) {
        //out.println("Item");
        out.println(name);
        out.println(type);
        out.println(weight);
        out.println(value);
        out.println(strength);
    }

    public Item(Scanner in) {

        //Wades attempt
        Terminal.clear();

        String temp = "";

        //while (!in.hasNext(".")) { //might be the issue, reads in one item only, dont loop through
            name = in.nextLine();

            /*Terminal.warpCursor(10, 0);
              System.out.print("Item's name is: " + name);
              Terminal.pause(2);*/

            // changed from String t to temp
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

            /*Terminal.warpCursor(11, 0);
              System.out.print("Item's type is: " + type); // changed to t from type
              Terminal.pause(2);*/

            temp = in.nextLine();
            weight = Integer.parseInt(temp);

            /*Terminal.warpCursor(12, 0);
              System.out.print("Weight: " + weight);
              Terminal.pause(2);*/

            // PUTTING the try catch in here screwed up the order
            // Throwing a java.lang.NumberFormatException here when reading the 'i' NOT TOTALLY SURE WHY
            //try {
            temp = in.nextLine();
            value = Integer.parseInt(temp);
            //} catch (java.lang.NumberFormatException e) {
            //for now do nothing we may need to change code
            //}

            /*Terminal.warpCursor(13, 0);
              System.out.print("Value: " + value);
              Terminal.pause(2);*/

            temp = in.nextLine();
            strength = Integer.parseInt(temp);

            /*Terminal.warpCursor(14, 0);
              System.out.print("Strength: " + strength);
              Terminal.pause(2);*/

        //}
    }
}

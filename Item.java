// Item.java
// this class represents a single item, it could be an equippable
// thing like weapon or ring, or something generic

import ansi_terminal.Terminal;

import java.util.Scanner;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class Item {
    // what sort of item it is
    private ItemType type;

    // the name of the item as shown to the user
    private String name;

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

    public void save(PrintWriter out) {
        //out.println("Item");
        out.println(name);
        out.println(type);
        out.println(weight);
        out.println(value);
        out.println(strength);
    }

    public Item(Scanner in) {
        name = in.nextLine();
        Terminal.warpCursor(37, 0);
        System.out.print(name);
        String t = in.nextLine();
        if (t.equals("Weapon")) {
            type = ItemType.Weapon;
        }
        else if (t.equals("Armor")) {
            type = ItemType.Armor;
        }
        else {
            type = ItemType.Other;
        }
        Terminal.warpCursor(38, 0);
        System.out.print(type);
        /*try {
            weight = in.nextInt(); //have issues reading this in
            value = in.nextInt();
            strength = in.nextInt();
        //} catch (InputMismatchException e) {
            //e.printStackTrace();
        }*/
        String temp = in.next(); //have issues reading this in
        Terminal.warpCursor(39, 0);
        System.out.print(temp);
        value = in.nextInt();
        strength = in.nextInt();
    }
}

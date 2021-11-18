// Box.java
// represents a pickup-able item

import ansi_terminal.*;

import java.io.PrintWriter;
import java.util.Scanner;

public class Box extends Entity {
    // the Item that is in the box
    private Item item;

    // add a box with a given item in it
    public Box(int row, int col, Item item) {
        super(row, col, 'i', Color.MAGENTA);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void save(PrintWriter out) {
        out.println(item.getName());
        out.println(item.getType());
        out.println(item.getWeight());
        out.println(item.getValue());
        out.println(item.getStrength());
    }

    public Box(Scanner in) {
        super(in);
        String name = in.nextLine();
        String t = in.next();
        ItemType type;
        if (t.equals("Weapon")) {
            type = ItemType.Weapon;
        }
        else if (t.equals("Armor")) {
            type = ItemType.Armor;
        }
        else {
            type = ItemType.Other;
        }
        int weight = in.nextInt();
        int value = in.nextInt();
        int strength = in.nextInt();
        item = new Item(type, name, weight, value, strength);
    }
}

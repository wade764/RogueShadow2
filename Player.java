// Player.java

import ansi_terminal.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Player extends Character {
    private Inventory items;
    private String name = "Hero";

    public Player(Position start) {
        // our starting details
        super(start.getRow(), start.getCol(), '@', Color.CYAN, 50);
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

    // setter used in the Game class to set the players name
    public void setName(String n) {
        name = n;
    }

    public void save(PrintWriter out) {
        out.println(name);
        out.println(getRow());
        out.println(getCol());
        out.println(getHealth());
        out.println(getWeapon());
        out.println(getArmor());
        out.println(getDamage());
        out.println(getProtection());
        for (int i = 0; i < items.getItems().size(); i++) {
            out.println(items.getItems().get(i).getName());
            out.println(items.getItems().get(i).getType());
            out.println(items.getItems().get(i).getWeight());
            out.println(items.getItems().get(i).getValue());
            out.println(items.getItems().get(i).getStrength());
            if (i < items.getItems().size() - 1) {
                out.println("...");
            }
        }
        out.println("*");
    }

    public void load(Scanner in, File file) {
        name = in.nextLine();
        //row
        //column
        //hp
        //weapon
        //armor
        //strength
        //defense
        String line = in.nextLine();
        while (!line.equals("*")) {
            String name = in.nextLine();
            Object tempType = in.nextLine(); //used for converting this line to ItemType
            ItemType type = (ItemType) tempType;
            int weight = in.nextInt();
            int value = in.nextInt();
            int strength = in.nextInt();
            Item item = new Item(type, name, weight, value, strength);
            items.add(item);
            line = in.nextLine(); //skips the delimeter between items
        }
    }
}

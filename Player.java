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
        //info for the equipped weapon
        out.println(getWeapon().getName());
        out.println(getWeapon().getType());
        out.println(getWeapon().getWeight());
        out.println(getWeapon().getValue());
        out.println(getWeapon().getStrength());
        out.println("#");
        //info for the equipped armor
        out.println(getArmor().getName());
        out.println(getArmor().getType());
        out.println(getArmor().getWeight());
        out.println(getArmor().getValue());
        out.println(getArmor().getStrength());
        //out.println(getDamage());
        //out.println(getProtection());
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
        name = in.nextLine(); //player's name
        int row = in.nextInt();
        int column = in.nextInt();
        setPosition(row, column);
        super.hp = in.nextInt();
        in.nextLine();
        //weapon
        String name = in.nextLine(); //item's name
        Object tempType = in.nextLine(); //used for converting this line to ItemType
        ItemType type = (ItemType) tempType; //how do we catch ClassCastException here?
        int weight = in.nextInt();
        int value = in.nextInt();
        int strength = in.nextInt();
        Item weapon = new Item(type, name, weight, value, strength);
        items.addAndEquip(weapon);
        in.nextLine();
        //armor
        name = in.nextLine(); //item's name
        tempType = in.nextLine(); //used for converting this line to ItemType
        type = (ItemType) tempType; //how do we catch ClassCastException here?
        weight = in.nextInt();
        value = in.nextInt();
        strength = in.nextInt();
        Item armor = new Item(type, name, weight, value, strength);
        items.addAndEquip(armor);
        //strength
        //defense

        String line = in.nextLine();
        while (!line.equals("*")) {
            name = in.nextLine(); //item's name
            tempType = in.nextLine(); //used for converting this line to ItemType
            type = (ItemType) tempType; //how do we catch ClassCastException here?
            weight = in.nextInt();
            value = in.nextInt();
            strength = in.nextInt();
            Item item = new Item(type, name, weight, value, strength);
            if (!item.equals(weapon) && !item.equals(armor)) { //adds items to inventory if they are not equipped
                //since those have already been added
                items.add(item);
            }
            in.nextLine(); //avoid issues with scanning in different types
            line = in.nextLine(); //skips the delimiter between items
        }
    }
}

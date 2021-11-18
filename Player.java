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
    }

    public Player(Scanner in) {
        super(in);
        name = in.nextLine();
    }
}

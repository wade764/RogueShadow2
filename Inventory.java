import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import ansi_terminal.Terminal;

/** Creates an inventory object that stores the items of the player and also keeps track of which items are equipped
 *
 */
public class Inventory {
    // the actual list of items
    private ArrayList<Item> items;

    // which item is equipped, if any
    private Item equippedArmor;
    private Item equippedWeapon;
    private Item equippedOther;

    // the max weight limit for the player here
    private int maxWeight;

    public Inventory(int maxWeight) {
        items = new ArrayList<>();
        this.maxWeight = maxWeight;
    }

    // returns true on success, false when full
    public boolean add(Item item) {
        if ((item.getWeight() + totalWeight()) > maxWeight) {
            return false;
        } else {
            items.add(item);
            return true;
        }
    }

    // this method not only adds the item, but equips it into the correct slot
    // it is used for setting up the player's starting gear
    public void addAndEquip(Item item) {
        items.add(item);

        if (item.getType() == ItemType.Weapon) {
            equippedWeapon = item;
        } else if (item.getType() == ItemType.Armor) {
            equippedArmor = item;
        } else if (item.getType() == ItemType.Other) {
            equippedOther = item;
        }
    }

    // get the equipped weapon and armor
    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    public Item getEquippedArmor() {
        return equippedArmor;
    }

    public Item getEquippedOther(){
        return equippedOther;
    }

    // returns the total weight of all items stored
    public int totalWeight() {
        int total = 0;
        for (Item i : items) {
            total += i.getWeight();
        }
        return total;
    }

    // print all of the items in the list, that match the given type (can be null)
    // returns the number of items matching they type
    private int print(ItemType filter) {
        // clear the terminal so we print over all else
        Terminal.clear();

        // print a heading row
        // the numbers and junk are to make it print in nice columns
        System.out.printf("%-4s %-40s %-8s %-8s %-8s\n\r", "No.", "Name", "Weight", "Value", "Strength");
        Terminal.reset();

        // print each item out
        int num = 0;
        for (Item i : items) {
            if (filter == null || i.getType() == filter) {
                System.out.printf("%-4d %-40s %-8s %-8s %-8s", num + 1, i.getName(), i.getWeight(), i.getValue(), i.getStrength());

                // tell them if this thing is equipped
                if (i == equippedArmor) {
                    System.out.print(" (equipped armor)");
                } else if (i == equippedWeapon) {
                    System.out.print(" (equipped weapon)");
                } else if (i == equippedOther) {
                    System.out.print(" (equipped other)");
                }
                System.out.print("\n\r");
                num++;
            }
        }
        return num;
    }

    // stay here until the user is ready to go back
    public void pressAnyKey() {
        System.out.printf("\n\rPress any key to return...\n\r");
        Terminal.getKey();
    }

    // print all of the items in the list
    public void print() {
        print(null);
        pressAnyKey();
    }

    // drop an item from the inventory, return what was dropped
    public Item drop() {
        Item toDrop = pickItem(null);
        if (toDrop != null) {
            // if we're dropping our equipped stuff, remove those too!
            if (equippedWeapon == toDrop) {
                equippedWeapon = null;
            } else if (equippedArmor == toDrop) {
                equippedArmor = null;
            }
            items.remove(toDrop);
        }

        if (toDrop != null) {
            System.out.print("You dropped the " + toDrop.getName() + "...\n\r");
        } else {
            System.out.print("You dropped nothing...\n\r");
        }
        pressAnyKey();
        return toDrop;
    }

    // equip something
    private Item equip(ItemType type) {
        Item thing = pickItem(type);
        if (thing != null) {
            System.out.print("You equipped the " + thing.getName() + "\n\r");
        } else {
            System.out.print("You equipped nothing...\n\r");
        }
        pressAnyKey();
        return thing;
    }

    // equip a weapon
    public void equipWeapon() {
        equippedWeapon = equip(ItemType.Weapon);
    }

    // equip a piece of armor
    public void equipArmor() {
        equippedArmor = equip(ItemType.Armor);
    }

    //equip a piece of other
    public void equipOther(){
        equippedOther = equip(ItemType.Other);
    }

    // a method which allows users to choose an item
    // this is private - only called by drop and equip
    private Item pickItem(ItemType filter) {
        // print all the matching items
        int options = print(filter);

        if (options == 0) {
            System.out.print("You have no appropriate items!\n\r");
            return null;
        }

        // give them a cancel option as well
        System.out.print((options + 1) + "    None\n\r");

        // get their choice, only allowing ints in the correct range
        int choice;
        do {
            String entry = Terminal.getLine("Select an item: ");
            try {
                choice = Integer.parseInt(entry) - 1;
            } catch (NumberFormatException e) {
                choice = -1;
            }
        } while (choice < 0 || choice > options);

        // go through and skip items until we reach this one
        int realIndex = 0;
        for (Item i : items) {
            if (filter == null || i.getType() == filter) {
                if (choice == 0) {
                    break;
                }
                choice--;
            }
            realIndex++;
        }

        // return the thing they chose
        if (realIndex < 0 || realIndex >= items.size()) {
            return null;
        } else {
            return items.get(realIndex);
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    /** Writes each item in the inventory, including the equipped weapon, armor, and other obtained items to the
     * save file
     *
     * @param out the printwriter used to write data to a file
     */
    public void save(PrintWriter out) {
        equippedWeapon.save(out);
        equippedArmor.save(out);
        for (Item item : items) {
            if (item != equippedWeapon && item != equippedArmor) {
                item.save(out);
            }
        }
        out.println(".");
    }

    /** A constructor used for reading in the inventory, including the equipped weapon, armor, and other obtained items
     * from the save file
     *
     * @param in the scanner used to read in data from the file
     */
    public Inventory(Scanner in) {
        items = new ArrayList<>();
        maxWeight = 80;
        Item weapon = new Item(in);
        addAndEquip(weapon);
        Item armor = new Item(in);
        addAndEquip(armor);
        while (!in.hasNext(".")) {
            Item item = new Item(in);
            items.add(item);
        }
        in.nextLine();
    }
}

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
        
        //THIS WORKS the following condition reads the delimeter properly
        while (!in.hasNext(".")) {
            name = in.nextLine();

            Terminal.warpCursor(10, 0);
            System.out.print("Item's name is: " + name);
            Terminal.pause(2);
        
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

            Terminal.warpCursor(11, 0);
            System.out.print("Item's type is: " + type); // changed to t from type
            Terminal.pause(2);
        
            temp = in.nextLine();
            weight = Integer.parseInt(temp);
        
            Terminal.warpCursor(12, 0);
            System.out.print("Weight: " + weight);
            Terminal.pause(2);
        
            // PUTTING the try catch in here screwed up the order
            // Throwing a java.lang.NumberFormatException here when reading the 'i' NOT TOTALLY SURE WHY
            //try {
            temp = in.nextLine();
            value = Integer.parseInt(temp);
            //} catch (java.lang.NumberFormatException e) {
                //for now do nothing we may need to change code
            //}
        
            Terminal.warpCursor(13, 0);
            System.out.print("Value: " + value);
            Terminal.pause(2);
        
            temp = in.nextLine();
            strength = Integer.parseInt(temp);
        
            Terminal.warpCursor(14, 0);
            System.out.print("Strength: " + strength);
            Terminal.pause(2);

        } 
        // first method of doing this

        //        Terminal.clear();
        //        // storing the name in a differenwdst variable for testing
        //        String name2 = in.nextLine();
        //        
        //        //*** test print
        //        Terminal.warpCursor(10, 0);
        //        System.out.print("Item's name is: " + name2);
        //        Terminal.pause(2);
        //
        //        String t = in.nextLine();
        //        if (t.equals("Weapon")) {
        //            type = ItemType.Weapon;
        //        }
        //        else if (t.equals("Armor")) {
        //            type = ItemType.Armor;
        //        }
        //        else if (t.equals("Other")) {
        //            type = ItemType.Other;
        //        }
        //        
        //        //*** test print
        //        Terminal.warpCursor(11, 0);
        //        System.out.print("Item's type is: " + t); // changed to t from type
        //        Terminal.pause(2);
        //        
        //        //weight = in.nextInt();
        //        String temp = in.nextLine(); //have issues reading this in // changed from in.next() to in.nextLine()
        //        weight = Integer.parseInt(temp);
        //
        //        Terminal.warpCursor(12, 0);
        //        System.out.print("Weight: " + temp);
        //        Terminal.pause(2);
        //
        //        //value = in.nextInt();
        //        String temp2 = in.nextLine();
        //        value = Integer.parseInt(temp2);
        //        
        //        //*** test print
        //        Terminal.warpCursor(13, 0);
        //        System.out.print("Value: " + temp2);
        //        Terminal.pause(2);
        //
        //        //strength = in.nextInt();
        //        String temp3 = in.nextLine();
        //        strength = Integer.parseInt(temp3);
        //        //in.nextLine();
        //
        //        
        //        //*** test print
        //        Terminal.warpCursor(14, 0);
        //        System.out.print("Strength: " + temp3);
        //        Terminal.pause(2);

        // this silly counter crap doesnt want to work right but its just used for testing I think we could maybe add to it each time scanner takes in a line?
        //counter++;

        //Terminal.warpCursor(15, 0);
        //System.out.print("counter: " + this.counter);
        //Terminal.pause(2);

        // something bad is happening below? MAYBE changed from in.nextLine() to String skipMe
        //String skipMe = in.nextLine();

        //more testing
        //Terminal.warpCursor(16, 0);
        //System.out.print("skipMe: " + skipMe);
        //Terminal.pause(2);

        // I think we can ignore this stuff below leave for now until we can fix the load crap

        //Testing stuff
        /*if (counter == 3) {
          String myNameEmpty = in.nextLine();
          Terminal.warpCursor(17, 0);
          System.out.print("counterNow3SoWeSkipThis: " + myNameEmpty);
          Terminal.pause(2);*/

        //TESTING
        //}
    }
}

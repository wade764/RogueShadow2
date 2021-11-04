import java.util.ArrayList;
import java.util.Scanner;
import ansi_terminal.*;
public class Inventory{
    /*  keeps track of the items and keeps track of the max weight, and equipped armor/weapon
        the methods are to manipulate the arraylist 

     */

    ArrayList<Item> items = new ArrayList<Item>();
    private int maxWeight;
    private Item equippedWeapon;
    private Item equippedArmor;
    private Player player; //takes a Player object so that the inventory methods can modify the Player's stats when items are equipped/dropped
    private int index; //used for formatting on the screen in menus
    private static int numOfItems;

    //constructor to make an inventory object
    Inventory(int maxWeight, Player player){
        this.maxWeight=maxWeight;
        this.player = player;

    }

    /*checks to see if the new item fits in the weight limit
      returns true if there is enough room and false if it does not fit the 
      weight limit
     */
    public boolean add(Item item){
        int check =totalWeight() + item.getWeight();
        if(check< maxWeight){
            items.add(item);
            numOfItems++;
            return true;
        }
        else{	
            return false;
        }
    }

    // adds all of the weights from every item in the arraylist and adds them together
    public int totalWeight(){
        int total=0;
        for (int i=0; i<items.size(); i++){
            total+=items.get(i).getWeight();

        }
        return total;
    }

    // uses the toString method to print out all the info about the object
    public void print (){
        Inventory.clearScreen();
        Terminal.setForeground(Color.CYAN);
        Terminal.warpCursor(3, 85);
        System.out.print("Equipped weapon: " + equippedWeapon);
        Terminal.warpCursor(4, 85);
        System.out.print("Equipped armor: " + equippedArmor);
        Terminal.warpCursor(6, 85);
        System.out.print("Inventory:");
        index = 7;
        for (Item item : items) {
            Terminal.warpCursor(index, 88);
            System.out.print(item.toString());
            index++;
        }
        index += 2; //to separate the 2 sections that appear in the inventory menu
        Terminal.warpCursor(index, 85);
        System.out.print("Commands:");
        index++;
        Terminal.warpCursor(index, 88);
        System.out.print("Equip weapon: w");
        index++;
        Terminal.warpCursor(index, 88);
        System.out.println("Equip armor: a");
        index++;
        Terminal.warpCursor(index, 88);
        System.out.print("Drop item: d");
        index += 2;
        Terminal.reset();

    }

    /*prints a list of everything in the inventory so the user can
      choose an item to remove then uses user input to drop an item at 
      a specified index
     */
    public void drop(){
        Inventory.clearScreen();
	Terminal.warpCursor(1, 82);
	System.out.print("                                                                                           ");
        Terminal.setForeground(Color.CYAN);
        int numLabel=1;
        index = 1;
        Scanner in = new Scanner(System.in);
        for (int i=0; i< items.size(); i++){
            Terminal.warpCursor(index, 82);
            System.out.print("Item " + numLabel + ":  "  + items.get(i).getName());
            index++;
            numLabel++;

        }
        index++;
        //gets the user to enter a number then takes the user input to remove the item at the correct index
        Terminal.warpCursor(index, 82);
        System.out.print("Type the number that corresponds to the item you want to drop: ");
        int removeIndex=0;
        int userNum= in.nextInt();
        removeIndex = userNum  - 1 ;
        index++;
        //tells the user which item is being removed then actually removes the item
        Terminal.warpCursor(index, 82);
        System.out.print("The " + items.get(removeIndex).getName() + " was removed from your inventory");
        if (items.get(removeIndex) == equippedWeapon) {
            equippedWeapon = null;
            player.setPlayerStrength(equippedWeapon);
        }
        else if (items.get(removeIndex) == equippedArmor) {
            equippedArmor = null;
            player.setPlayerDefense(equippedArmor);
        }
        items.remove(removeIndex);
        Terminal.reset();
    }


    /*goes through the inventory and puts weapon type items into its own ArrayList
      then prints the weapons list and gets user input to let them choose what they 
      want to equip
     */
    public void equipWeapon(){
        Inventory.clearScreen();
        int numLabel=1;
        index = 1;
        ArrayList<Item> weaponsList = new ArrayList<Item>();
        Scanner in = new Scanner(System.in);
        for (Item item : items){
            if (item.getType() == ItemType.Weapon){
                weaponsList.add(item);
            } 
        }
        Terminal.setForeground(Color.CYAN);
        Terminal.warpCursor(index, 82);
        System.out.print("Type the number that corresponds to the weapon you want to equip");
        for (int i=0; i<weaponsList.size(); i++){
            index++;
            Terminal.warpCursor(index, 85);
            System.out.print(numLabel + ":  " + weaponsList.get(i).getName() + " ");
            numLabel++;
        }	      

        int userNum= in.nextInt();
        int choice= userNum-1;

        equippedWeapon= weaponsList.get(choice);
        player.setPlayerStrength(equippedWeapon);
        Inventory.clearScreen();
        Terminal.warpCursor(1, 82);
        System.out.print("Equipped: " + equippedWeapon.getName());
        Terminal.reset();
    }


    /* goes through the item array list and puts the armor into its own arrayList then
       prints the armor arrayList so the user can choose one to equip

     */
    public void equipArmor(){
        Inventory.clearScreen();
        int numLabel=1;
        index = 1;
        ArrayList<Item> armorList = new ArrayList<Item>();
        Scanner in = new Scanner(System.in);
        //goes through the inventory and puts weapon type items into its own ArrayList
        for (Item item : items){
            if (item.getType() == ItemType.Armor){
                armorList.add(item);
            }
        }
        Terminal.setForeground(Color.CYAN);
        Terminal.warpCursor(index, 82);
        System.out.print("Type the number that corresponds to the armor you want to equip");
        for (int i=0; i<armorList.size(); i++){
            index++;
            Terminal.warpCursor(index, 85);
            System.out.print(numLabel + ":  " + armorList.get(i).getName() + " ");
            numLabel++;
        }

        int userNum= in.nextInt();
        int choice= userNum-1;

        equippedArmor= armorList.get(choice);
        player.setPlayerDefense(equippedArmor);
        Inventory.clearScreen();
        Terminal.warpCursor(1, 82);
        System.out.print("Equipped: " + equippedArmor.getName());
        Terminal.reset();
    }

    //clears the right side of the screen for use of the inventory methods
    public static void clearScreen() {
        for (int i = 2; i < 40; i++) {
            Terminal.warpCursor(i, 82);
            System.out.print("                                                                                   ");
        }
    }

    // getter for the size of the inventory
    public static int getSize() {
        return numOfItems;
    }
}

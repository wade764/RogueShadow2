import java.util.Random;

/** Generates a random item each time generate() is called
 *
 */
public class ItemGenerator {
    private static ItemGenerator theInstance;
    private static Random rng = new Random();

    /** A singleton that creates a single instance of the ItemGenerator
     *
     * @return the instance of the ItemGenerator
     */
    public static synchronized ItemGenerator instance() {
        if (theInstance == null) {
            theInstance = new ItemGenerator();
        }
        return theInstance;
    }

    // used to set the constructor to private 
    private ItemGenerator() {
    }

    /** Generates a random item by using a random number generator to determine which item gets generated
     *
     * @return an item that was generated
     */
    public static Item generate() {
        int num = rng.nextInt(24);

        //creates a place to store the Item and return at the end
        return switch (num) {
            case 0 -> new Item(ItemType.Weapon, "Gold Sword", 5, 24, 36);
            case 1 -> new Item(ItemType.Other, "Purple Amulet of Mystery", 1, 50, 8);
            case 2 -> new Item(ItemType.Armor, "Cloak of Invisibility", 4, 52, 0);
            case 3 -> new Item(ItemType.Armor, "Bronze Shield", 7, 13, 34);
            case 4 -> new Item(ItemType.Armor, "Plastic Helmet", 1, 5, 10);
            case 5 -> new Item(ItemType.Other, "Chest of Wonders", 23, 56, 0);
            case 6 -> new Item(ItemType.Weapon, "Flame Thrower", 6, 42, 34);
            case 7 -> new Item(ItemType.Other, "Hydration Flask", 8, 12, 2);
            case 8 -> new Item(ItemType.Armor, "Metal Helmet", 9, 20, 18);
            case 9 -> new Item(ItemType.Armor, "Leather Coat", 2, 10, 5);
            case 10 -> new Item(ItemType.Weapon, "Orb of Destruction", 3, 34, 56);
            case 11 -> new Item(ItemType.Weapon, "Glass Sword", 4, 23, 29);
            case 12 -> new Item(ItemType.Weapon, "Wand of Illusions", 1, 23, 34);
            case 13 -> new Item(ItemType.Armor, "Cape of Protection", 4, 34, 40);
            case 14 -> new Item(ItemType.Other, "Teleportation Ring", 1, 23, 5);
            case 15 -> new Item(ItemType.Armor, "Silver Shield", 15, 18, 38);
            case 16 -> new Item(ItemType.Other, "Super Vision Glasses", 1, 29, 5);
            case 17 -> new Item(ItemType.Other, "Infinity Torch", 2, 19, 7);
            case 18 -> new Item(ItemType.Weapon, "Wand of Disappearance", 1, 34, 19);
            case 19 -> new Item(ItemType.Armor, "Fancy Suit", 2, 20, 1);
            case 20 -> new Item(ItemType.Weapon, "Infinity Sword", 3, 12, 30);
            case 21 -> new Item(ItemType.Other, "Deja Vu Potion", 2, 15, 15);
            case 22 -> new Item(ItemType.Armor, "Super Strength Armor", 20, 35, 32);
            case 23 -> new Item(ItemType.Other, "Book of Spells", 2, 13, 5);
            case 24 -> new Item(ItemType.Other, "Healing Potion", 3, 12,20);
                default -> new Item(ItemType.Weapon, "Stick", 1, 1, 1);
        };
    }
}

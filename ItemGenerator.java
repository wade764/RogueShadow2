import java.util.Random;
class ItemGenerator{
    /*this class generates a random object from a choice of 25 possibilities
      the switch statement takes a random number from the generator and the case
      of the random number creates a new Item object
     */
    public static Item generate(){
        int num;
        Random rng= new Random();
        num = rng.nextInt(25);

        //creates a place to store the Item and return at the end
        Item temp= new Item(ItemType.Weapon, "", 1, 1, 1);
        switch (num){
            case 0 : temp = new Item (ItemType.Weapon , "Gold Sword" , 5 , 24, 36);
                     break;				 
            case 1 : temp = new Item (ItemType.Other , "Purple Amulet of Mystery" , 1, 50, 0);
                     break;
            case 2 : temp = new Item (ItemType.Other, "Cloak of Invisibility" , 4, 52, 0);
                     break;
            case 3 : temp = new Item (ItemType.Armor, "Bronze Shield" , 7, 13, 34);
                     break;
            case 4 : temp = new Item (ItemType.Armor, "Plastic Helmet", 1, 5, 10);
                     break;
            case 5 : temp = new Item(ItemType.Other, "Chest of Wonders" , 23, 56, 0);
                     break;
            case 6 : temp = new Item(ItemType.Weapon, "Flame Thrower", 6, 42, 34);
                     break;
            case 7 : temp = new Item(ItemType.Other, "Hydration Flask", 8, 12, 0);
                     break;
            case 8 : temp = new Item(ItemType.Armor, "Metal Helmet", 9, 20, 18);
                     break;
            case 9: temp = new Item(ItemType.Armor, "Leather Coat" , 2, 10, 5);
                    break;
            case 10 : temp = new Item(ItemType.Weapon, "Orb of Destruction", 3, 34, 56);
                      break;
            case 11 : temp = new Item(ItemType.Weapon, "Glass Sword", 4, 23, 29);
                      break;
            case 12 : temp = new Item(ItemType.Weapon, "Wand of Illusions", 1, 23, 34);
                      break;
            case 13 : temp = new Item(ItemType.Armor, "Cape of Protection", 4, 34, 40);
                      break;
            case 14 : temp = new Item(ItemType.Other, "Teleportation Ring", 1, 23, 0);
                      break;
            case 15 : temp = new Item(ItemType.Armor, "Silver Shield", 15, 18, 38);
                      break;
            case 16 : temp = new Item(ItemType.Other, "Super Vision Glasses", 1, 29, 0);
                      break;
            case 17 : temp = new Item(ItemType.Other, "Infinity Torch", 2, 19, 0);
                      break;
            case 18 : temp = new Item(ItemType.Weapon, "Wand of Disapearance", 1, 34, 19);
                      break;
            case 19 : temp = new Item(ItemType.Armor, "Fancy Suit" , 2, 20, 1);
                      break;
            case 20 : temp = new Item(ItemType.Weapon, "Infinity Sword", 3, 12, 30);
                      break;
            case 21 : temp = new Item(ItemType.Other, "Deja Vu Potion", 2, 15, 0);
                      break;
            case 22 : temp = new Item(ItemType.Armor, "Super Strength Armor", 20, 35, 32);
                      break;
            case 23 : temp = new Item(ItemType.Other, "Healing Potion", 2, 14, 0);
                      break;
            case 24 : temp = new Item(ItemType.Other, "Book of Spells", 2, 13, 0);


        }
        //returns the temp variable that held the object
        return temp;
    } 
}

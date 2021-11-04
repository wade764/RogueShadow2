
public class Player {
    
    // These variables must be static to be used in the constructor
    private static String name;
    private static int HP;
    private static int strength;
    private static int defense;


    public static void Player(String playerName) {
        name = playerName;
        HP = 100; //can be changed later
        strength = 5;
        defense = 5;
    }

    public static String getPlayerName() {
        return name;
    }

    public static int getPlayerStrength() {
        return strength;
    }

    public static int getPlayerDefense() {
        return defense;
    }

    public static int getPlayerHP() {
        return HP;
    }

	//changes the player's strength when they equip a weapon
    public void setPlayerStrength(Item weapon) {
	if (weapon == null) {
		strength = 0;
	}
	else {
        	strength = weapon.getStrength();
	}
    }
	//changes the player's defense when they equip a piece of armor
    public void setPlayerDefense(Item armor) {
        if (armor == null) {
		defense = 0;
	}
	else {
		defense = armor.getStrength();
	}
    }
	//changes the player's hp for when they get attacked by an enemy
    public void setPlayerHP(int newHP) {
        HP = newHP;
    }
}

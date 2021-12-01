// Enemy.java

import java.util.Random;
import ansi_terminal.*;
import java.io.PrintWriter;
import java.util.Scanner;

public class Enemy extends Character {
    private String name;
    private int damage;
    private int protection;
    private static Random rng;
    private boolean battleActive;

    public Enemy(String name, int row, int col, int hp, int damage, int protection) {
        super(row, col, '*', Color.RED, hp);
        this.name = name;
        this.damage = damage;
        this.protection = protection;
        this.battleActive = false;
        rng = new Random();
    }

    @Override
    public int getDamage() {
            return damage;
        }

    @Override
    public int getProtection() {
            return protection;
        }

    @Override
    public String getName() {
            return name;
        }

    public void setBattleActive() {
        battleActive = true;
    }

    // randomly move the enemy in the room
    public void walk(Room room, Room2 room2, Room3 room3, Room4 room4) {
        // if a battle is active with this enemy, they DON'T walk right after
        if (battleActive) {
            battleActive = false;
            return;
        }

        // loop forever until we move correctly
        while (true) {
            int choice = rng.nextInt(4);
            switch (choice) {
                case 0:
                    if (move(0, 1, room, room2, room3, room4)) return;
                    break;
                case 1:
                    if (move(0, -1, room, room2, room3, room4)) return;
                    break;
                case 2:
                    if (move(1, 0, room, room2, room3, room4)) return;
                    break;
                case 3:
                    if (move(-1, 0, room, room2, room3, room4)) return;
                    break;
            }

        }
    }

    /** Writes the enemy's Entity and Character data, along with its name, strength, and defense to the save file
     *
     * @param out the printwriter used to write data to a file
     */
    public void save(PrintWriter out) {
        super.save(out);
        out.println(name);
        out.println(damage);
        out.println(protection);
    }

    /** A constructor used for reading in the enemy's Entity and Character data, along with its name, strength, and
     * defense from the save file
     *
     * @param in the scanner used to read in data from the file
     */
    public Enemy(Scanner in) {
        super(in);
        name = in.nextLine();
        damage = in.nextInt(); 
        // added to read the rest of the line
        in.nextLine();
        protection = in.nextInt();
        in.nextLine();
    }
}

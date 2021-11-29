// Enemy.java

import java.util.InputMismatchException;
import java.util.Random;
import ansi_terminal.*;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

/** Manages Enemy-type characters
 *
 */
public class Enemy extends Character {
    private String name;
    private int damage;
    private int protection;
    private static Random rng;
    private boolean battleActive;

    /** Creates an Enemy object
     *
     * @param name the enemy's name
     * @param row the row the enemy is in
     * @param col the column the enemy is in
     * @param hp the enemy's hp
     * @param damage the enemy's strength
     * @param protection the enemy's defense
     */
    public Enemy(String name, int row, int col, int hp, int damage, int protection) {
        super(row, col, '*', Color.RED, hp);
        this.name = name;
        this.damage = damage;
        this.protection = protection;
        this.battleActive = false;
        rng = new Random();
    }

    /** Gets the enemy's strength
     *
     * @return the enemy's strength
     */
    @Override
    public int getDamage() {
            return damage;
        }

    /** Gets the enemy's defense
     *
     * @return the enemy's dense
     */
    @Override
    public int getProtection() {
            return protection;
        }

    /** Gets the enemy's name
     *
     * @return the enemy's name
     */
    @Override
    public String getName() {
            return name;
        }

    /** Sets the battle status of the enemy
     *
     */
    public void setBattleActive() {
        battleActive = true;
    }

    /** Randomly move the enemy in the room
     *
     * @param room the first room
     * @param room2 the second room
     * @param room3 the third room
     */
    public void walk(Room room, Room2 room2, Room3 room3) {
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
                    if (move(0, 1, room, room2, room3)) return;
                    break;
                case 1:
                    if (move(0, -1, room, room2, room3)) return;
                    break;
                case 2:
                    if (move(1, 0, room, room2, room3)) return;
                    break;
                case 3:
                    if (move(-1, 0, room, room2, room3)) return;
                    break;
            }

        }
    }

    /** Saves the character to the save file
     *
     * @param out the printwriter
     */
    public void save(PrintWriter out) {
        super.save(out);
        out.println(name);
        out.println(damage);
        out.println(protection);
    }

    /** Loads the enemy from the save file
     *
     * @param in the scanner
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

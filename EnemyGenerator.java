// EnemyGenerator.java
// this class contains a static method for creating enemies randomly

import java.util.Random;

/** Randomly generates enemies
 *
 */
public class EnemyGenerator {

    // DO NOT DELETE WORKING FOR NEXT PART
     private static EnemyGenerator theInstance;

    /** A singleton for the enemy generator
     *
     * @return the EnemyGenerator instance
     */
     public static synchronized EnemyGenerator instance() {
         if (theInstance == null) {
             theInstance = new EnemyGenerator();
         }
         return theInstance;
     }

    /** Part of the class's singleton
     *
     */
    private EnemyGenerator() {

     }

    /** Generates a new Enemy object
     *
     * @param row the row the enemy is in
     * @param col the column the enemy is in
     * @return an Enemy object
     */
    public static Enemy generate(int row, int col) {
        Random rng = new Random();
        int num = rng.nextInt(4);
        Enemy enemy;
        if (num == 0) {
            enemy = new Enemy("Goblin", row, col, 20, 18, 10);
        }
        else if (num == 1) {
            enemy = new Enemy("Dragon", row, col, 40, 25, 18);
        }
        else if (num == 2) {
            enemy = new Enemy("Zombie", row, col, 22, 20, 13);
        }
        else {
            enemy = new Enemy("Wolf", row, col, 25, 23, 15);
        }
        return enemy;
    }
}

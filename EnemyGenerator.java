// EnemyGenerator.java
// this class contains a static method for creating enemies randomly

import java.util.Random;

/** This class randomly generates an enemy that is then placed on the map where a * is located. Enemies are generated
 * only at the beginning of the map being loaded and the enemies retain the same data while they are alive
 *
 */
public class EnemyGenerator {

     private static EnemyGenerator theInstance;

    /** A singleton that creates an enemy generator
     *
     * @return the EnemyGenerator instance
     */
     public static synchronized EnemyGenerator instance() {
         if (theInstance == null) {
             theInstance = new EnemyGenerator();
         }
         return theInstance;
     }

     private EnemyGenerator() {

     }

    /** Generates a new Enemy object based on a random number that is generated each time
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

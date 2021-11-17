// EnemyGenerator.java
// this class contains a static method for creating enemies randomly

import java.util.Random;

public class EnemyGenerator {
    /**private static EnemyGenerator theInstance;
    private static int num;

    public static synchronized EnemyGenerator instance() {
        if (theInstance == null) {
            theInstance = new EnemyGenerator();
        }
        return theInstance;
    }

    private EnemyGenerator() {
        Random rng = new Random();
        num = rng.nextInt(4);
    }*/

    public static Enemy generate(int row, int col) {
        Random rng = new Random();
        int num = rng.nextInt(1);
        Enemy enemy = null;
        if (num == 0) {
            enemy = new Enemy("Goblin", row, col, 100, 30, 10);
        }
        /**else if (num == 1) {
            enemy = new Enemy("Dragon", row, col, 40, 25, 18);
        }
        else if (num == 2) {
            enemy = new Enemy("Zombie", row, col, 22, 20, 13);
        }
        else {
            enemy = new Enemy("Wolf", row, col, 25, 23, 15);
        }*/
        return enemy;
    }
}


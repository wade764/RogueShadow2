// EnemyGenerator.java
// this class contains a static method for creating enemies randomly

import java.util.Random;

public class EnemyGenerator {
    public static Enemy generate(int row, int col) {
        Random rng = new Random();
        int num = rng.nextInt(4);
        Enemy enemy;
        if (num == 0) {
            enemy = new Enemy("Goblin", row, col, 20, 7, 10);
        }
        else if (num == 1) {
            enemy = new Enemy("Dragon", row, col, 40, 13, 18);
        }
        else if (num == 2) {
            enemy = new Enemy("Zombie", row, col, 22, 9, 13);
        }
        else {
            enemy = new Enemy("Wolf", row, col, 25, 11, 15);
        }
        return enemy;
    }
}


import java.util.Random;
import java.util.ArrayList;

public class Enemy {

    // creating a field for the players and enemy location posits
    private ArrayList<Integer> playerLocation;
    private ArrayList<Integer> enemyLocation;

    private String name;
    private int HP;
    private int strength;
    private static int enemyCounter = 0;
    private boolean enemyStatus;

    public Enemy(String name, int HP, int strength) {
        this.name = name;
        this.HP = HP;
        this.strength = strength;
        enemyStatus = true; //true means alive, false means dead
        enemyCounter++;
    }

    public String getEnemyName() {
        return name;
    }

    public int getEnemyHP() {
        return HP;
    }

    public int getEnemyStrength() {
        return strength;
    }

    public boolean getEnemyStatus() {
        return enemyStatus;
    }

    // made static so that we just have to call Enemy.getEnemyCount instead of calling it on an object
    public static int getEnemyCount() {
        return enemyCounter;
    }

    public ArrayList<Integer> enemyMove(ArrayList<Integer> coordinates, String enemy) {

        Random rng = new Random();

        // instantiating a new Game() object to call getCell()
        Game g  = new Game();

        // calling the getters that will store the current positions of the enemies and player
        playerLocation = g.getPlayerLocation();
        enemyLocation = g.getEnemyLocation(enemy);

        // implementing a similar strategy as the players movement
        int enemyRowL = enemyLocation.get(0) - 1;
        int enemyRowR = enemyLocation.get(0) + 1;
        int enemyColL = enemyLocation.get(1) - 1;
        int enemyColR = enemyLocation.get(1) + 1;
        int pRow = playerLocation.get(0);
        int pCol = playerLocation.get(1);

        int move = rng.nextInt(4); //generates a random direction for the enemy to move in

        if (move == 0) {
            char cell = g.getCell(coordinates.get(0), (coordinates.get(1)-1));
            if (cell == '#' ) {
            } else if (enemyColL != pCol && pRow != coordinates.get(0) ){
                coordinates.set(1, coordinates.get(1) - 1); //move left
            }
        }
        else if (move == 1) {
            char cell = g.getCell(coordinates.get(0), (coordinates.get(1)+1));
            if (cell == '#' ) {
            } else if (enemyColR != pCol && pRow != coordinates.get(0) ){
                coordinates.set(1, coordinates.get(1) + 1); //move right
            }
        }
        else if (move == 2) {
            char cell = g.getCell((coordinates.get(0)-1), coordinates.get(1));
            if (cell == '#' ) {
            } else if (enemyRowL != pRow && pCol != coordinates.get(1) ){
                coordinates.set(0, coordinates.get(0) - 1); //moves up
            }
        }
        else {
            char cell = g.getCell((coordinates.get(0)+1), coordinates.get(1));
            if (cell == '#' ) {
            } else if (enemyRowR != pRow && pCol != coordinates.get(1) ){
                coordinates.set(0, coordinates.get(0) + 1); //moves down
            }
        }
        return coordinates;
    }

    // lets us set the enemy's hp value if it gets modified
    public void setEnemyHP(int newHP) {
        HP = newHP;
    }

    // changes the enemy to being dead if their hp is less than or equal to 0
    public void setEnemyStatus(int HP) {
        if (HP <= 0) {
            enemyStatus = false; //changes the enemy to being dead
        }
    }

    public void enemyDeath() {
        enemyCounter--;
    }
}

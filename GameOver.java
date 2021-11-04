public class GameOver {
    //methods are static so that they can be called upon the class rather than player/enemy objects
    //says whether the player is dead or not
    public static boolean playerDeath(int playerHP) {
        return (playerHP <= 0);
    }

    //says whether there are any enemies still alive
    public static boolean enemyCount() {
        return (Enemy.getEnemyCount() == 0);
    }
}

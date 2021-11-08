import java.lang.Math;

public class Attack {

    // constructor created to make calls to the methods in this class
    public static void Attack() {
        //might not be
    }

    //allows the player to attack the enemy
    public static int playerAttack(Player player, Enemy enemy) {
        int newHP = enemy.getEnemyHP() - player.getPlayerStrength();
        enemy.setEnemyHP(newHP);
        if (enemy.getEnemyHP() <= 0) {
            enemy.enemyDeath();
        }
        return player.getPlayerStrength();
    }

    //allows the enemy to attack the player
    public static int enemyAttack(Player player, Enemy enemy) {
        int EStrength =  enemy.getEnemyStrength();
        int PDefense = player.getPlayerDefense();
        int EStrengthMinusPDefense = EStrength - PDefense;
        int newHP = player.getPlayerHP() - (Math.abs(EStrengthMinusPDefense));
        player.setPlayerHP(newHP);
        return Math.abs(EStrengthMinusPDefense);
    }
}

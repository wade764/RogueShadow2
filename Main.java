// Main.java
// contains the main class for running the game

import ansi_terminal.*;

public class Main {
    public static void main(String[] args) {
        // put terminal in raw mode
        Terminal.rawMode();

        // make and run the Game
        Game game = new Game();

        // printing the title screen, which also sets the players name
        game.titleScreen();

        game.run();
        
        Terminal.warpCursor(40,1);

        // put terminal back into cooked mode
        Terminal.cookedMode();
    }
}

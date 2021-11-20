// Main.java
// contains the main class for running the game

import ansi_terminal.*;

public class Main {
    public static void main(String[] args) {
        // put terminal in raw mode
        Terminal.rawMode();

        //prints the title screen and if returns true the game runs
        Boolean playGame = Menu.titleScreen();

        if (playGame) {

            // printing both the plot and setting the players name and icon
            Menu.storyPlot();
            Player.setPlayerInfo();

            //*** I am thinking that it would make sense for Game to be a singleton

            // make and run the Game
            Game game = new Game();

            game.run();

            // returning the cursor to the bottom of the screen
            Terminal.warpCursor(40,0);

            // put terminal back into cooked mode
            Terminal.cookedMode();
        }
    }
}

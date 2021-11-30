// Main.java
// contains the main class for running the game

import ansi_terminal.*;

public class Main {
    /**
     *
     * @param args the ansi_terminal.jar file
     */
    public static void main(String[] args) {
        // put terminal in raw mode
        Terminal.rawMode();

        //prints the title screen and if returns true the game runs
        boolean playGame = Menu.titleScreen();

        if (playGame) {
            int loading = Menu.getLoadStatus();
            // printing both the plot and setting the players name and icon
            if (loading == 0) {
                Menu.storyPlot();
                Player.setPlayerInfo();
            }
            Game.instance().run();

            // returning the cursor to the bottom of the screen
            Terminal.warpCursor(40,0);

            // put terminal back into cooked mode
            Terminal.cookedMode();
        }
    }
}

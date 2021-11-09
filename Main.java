// Main.java
// contains the main class for running the game

import ansi_terminal.*;

public class Main {
    public static void main(String[] args) {
        // put terminal in raw mode
        Room2 room = new Room2();
        System.out.println(room);
        Terminal.rawMode();

        // make and run the Game
        Game game = new Game();

        // printing the title screen
        game.storyPlot();
        // still on title screen, setting the players name
        game.setName();


        game.run();

        // put terminal back into cooked mode
        Terminal.cookedMode();
    }
}


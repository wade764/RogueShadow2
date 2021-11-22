import ansi_terminal.*;
import java.util.Scanner;
import java.util.Random;

public class Menu {
    // returned in Main class to start the game
    private static Boolean playGame = false;

    // Used for printing the Title screen and creating a new game or loading
    public static Boolean titleScreen() {

        for (int i = 0; i < 100; i++) {
            Terminal.warpCursor(i, 0);
            System.out.print("                                                                                                                                   ");
        }

        // setting the color randomly
        Random rng = new Random();
        int fg = rng.nextInt(6);
        switch (fg) {
            //case 0: Terminal.setForeground(Color.BLUE);
            //break;
            case 0: Terminal.setForeground(Color.CYAN);
                    break;
            case 1: Terminal.setForeground(Color.GREEN);
                    break;
            case 2: Terminal.setForeground(Color.MAGENTA);
                    break;
            case 3: Terminal.setForeground(Color.RED);
                    break;
            case 4: Terminal.setForeground(Color.WHITE);
                    break;
            case 5: Terminal.setForeground(Color.YELLOW);
                    break;
            default: Terminal.setForeground(Color.RED);
        }

        Terminal.warpCursor(0,40);
        System.out.print("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n\r");
        Terminal.warpCursor(1,40);
        System.out.print("<*************************************************************************>\n\r");
        Terminal.warpCursor(2,40);
        System.out.print("<*-----------------------------------------------------------------------*>\n\r");
        Terminal.warpCursor(3,40);
        System.out.print("<*|██████╗  ██████╗  ██████╗ ██╗   ██╗███████╗                          |*>\n\r");
        Terminal.warpCursor(4,40);
        System.out.print("<*|██╔══██╗██╔═══██╗██╔════╝ ██║   ██║██╔════╝               .-.        |*>\n\r");
        Terminal.warpCursor(5,40);
        System.out.print("<*|██████╔╝██║   ██║██║  ███╗██║   ██║█████╗                (o o)       |*>\n\r");
        Terminal.warpCursor(6,40);
        System.out.print("<*|██╔══██╗██║   ██║██║   ██║██║   ██║██╔══╝                | O \\       |*>\n\r");
        Terminal.warpCursor(7,40);
        System.out.print("<*|██║  ██║╚██████╔╝╚██████╔╝╚██████╔╝███████╗               \\   \\      |*>\n\r");
        Terminal.warpCursor(8,40);
        System.out.print("<*|╚═╝  ╚═╝ ╚═════╝  ╚═════╝  ╚═════╝ ╚══════╝                `~~~'     |*>\n\r");
        Terminal.warpCursor(9,40);
        System.out.print("<*|                 ███████╗██╗  ██╗ █████╗ ██████╗  ██████╗ ██╗    ██╗ |*>\n\r");
        Terminal.warpCursor(10,40);
        System.out.print("<*|                 ██╔════╝██║  ██║██╔══██╗██╔══██╗██╔═══██╗██║    ██║ |*>\n\r");
        Terminal.warpCursor(11,40);
        System.out.print("<*|                 ███████╗███████║███████║██║  ██║██║   ██║██║ █╗ ██║ |*>\n\r");
        Terminal.warpCursor(12,40);
        System.out.print("<*|                 ╚════██║██╔══██║██╔══██║██║  ██║██║   ██║██║███╗██║ |*>\n\r");
        Terminal.warpCursor(13,40);
        System.out.print("<*|                 ███████║██║  ██║██║  ██║██████╔╝╚██████╔╝╚███╔███╔╝ |*>\n\r");
        Terminal.warpCursor(14,40);
        System.out.print("<*|                 ╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝  ╚═════╝  ╚══╝╚══╝  |*>\n\r");
        Terminal.warpCursor(15,40);
        System.out.print("<*|                                                                     |*>\n\r");
        Terminal.warpCursor(16,40);
        System.out.print("<*|                           N: New Game                               |*>\n\r");
        Terminal.warpCursor(17,40);
        System.out.print("<*|          _.._                                                       |*>\n\r");
        Terminal.warpCursor(18,40);
        System.out.print("<*|        .' .-'`                                                      |*>\n\r");
        //This line above would have the Load game print
        Terminal.warpCursor(19,40);
        System.out.print("<*|       /  /                                                          |*>\n\r");
        Terminal.warpCursor(20,40);
        System.out.print("<*|       |  |                                                          |*>\n\r");
        Terminal.warpCursor(21,40);
        System.out.print("<*|       \\  '.___.;                                                    |*>\n\r");
        Terminal.warpCursor(22,40);
        System.out.print("<*|        '._  _.'              <>=======()                            |*>\n\r");
        Terminal.warpCursor(23,40);
        System.out.print("<*|           ``                (/\\___   /|\\          ()==========<>_   |*>\n\r");
        Terminal.warpCursor(24,40);
        System.out.print("<*|                                   \\_/ | \\        //|\\   ______/ \\)  |*>\n\r");
        Terminal.warpCursor(25,40);
        System.out.print("<*|                                     \\_|  \\      // | \\_/            |*>\n\r");
        Terminal.warpCursor(26,40);
        System.out.print("<*|                                       \\|\\/|\\_   //  /\\/             |*>\n\r");
        Terminal.warpCursor(27,40);
        System.out.print("<*|                                        (oo)\\ \\_//  /                |*>\n\r");
        Terminal.warpCursor(28,40);
        System.out.print("<*|   ,    ,    /\\   /\\                   //_/\\_\\/ /  |                 |*>\n\r");
        Terminal.warpCursor(29,40);
        System.out.print("<*|  /( /\\ )\\  _\\ \\_/ /_                 @@/  |=\\  \\  |                 |*>\n\r");
        Terminal.warpCursor(30,40);
        System.out.print("<*|  |\\_||_/| < \\_   _/ >                     \\_=\\_ \\ |                 |*>\n\r");
        Terminal.warpCursor(31,40);
        System.out.print("<*|  \\______/  \\|0   0|/                        \\==\\ \\|\\_               |*>\n\r");
        Terminal.warpCursor(32,40);
        System.out.print("<*|    _\\/_   _(_  ^  _)_                    __(\\===\\(  )\\              |*>\n\r");
        Terminal.warpCursor(33,40);
        System.out.print("<*|   ( () ) /`\\|V\"\"\"V|/`\\                  (((~) __(_/   |             |*>\n\r");
        Terminal.warpCursor(34,40);
        System.out.print("<*|     {}   \\  \\_____/  /                       (((~) \\  /             |*>\n\r");
        Terminal.warpCursor(35,40);
        System.out.print("<*|     ()   /\\   )=(   /\\                       ______/ /              |*>\n\r");
        Terminal.warpCursor(36,40);
        System.out.print("<*|     ()   /\\   )=(   /\\                       ______/ /              |*>\n\r");
        Terminal.warpCursor(37,40);
        System.out.print("<*|     {}  /  \\_/\\=/\\_/  \\                      '------'               |*>\n\r");
        Terminal.warpCursor(38,40);
        System.out.print("<*-----------------------------------------------------------------------*>\n\r");
        Terminal.warpCursor(39,40);
        System.out.print("<*************************************************************************>\n\r");
        Terminal.warpCursor(40,40);
        System.out.print("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv\n\r");

        Terminal.warpCursor(20,75);
        Scanner menu = new Scanner(System.in);
        boolean correctAnswer = false;
        while (!correctAnswer) {
            String response = menu.next();
            Terminal.warpCursor(14,65);
            if (response.equalsIgnoreCase("N")) {
                correctAnswer = true;
                playGame = true;
            } 
            // DO NOT DELETE ME WORK IN PROGRESS
            /*else if (response.equalsIgnoreCase("L")) {
              correctAnswer = true;
              playGame = true;*/

            // CANNOT Instantiate a new game here it has not asked for the players name or icon yet!
            // Game g = new Game();
            // This is further proof that  game needs to be a singleton, I need to call handleKey from here but
            // need a game object that can be manipulated
            // and not just call new games
            //g.handleKey(Key.r);
            //}
         else {
            //System.out.print("Please enter N or L: ");
            System.out.print("Press N to start a new game: ");
        }
        }
        return playGame;
    }

    // Printing the games story to the screen
    // important that this comes before new Game() in main method
    public static void storyPlot() {

        // clearing the terminal screen
        for (int i = 0; i < 100; i++) {
            Terminal.warpCursor(i, 0);
            System.out.print("                                                                                                                               ");
        }

        // DO NOT CLEAR TERMINAL UNTIL AFTER Player.setPlayerInfo has been called in main method

        // printing the games name
        // code generated from www.coolgenerator.com/ascii-text-generator   font used: ANSI shadow
        Terminal.warpCursor(6, 26);
        System.out.print("██████╗  ██████╗  ██████╗ ██╗   ██╗███████╗    ███████╗██╗  ██╗ █████╗ ██████╗  ██████╗ ██╗    ██╗");
        Terminal.warpCursor(7, 26);
        System.out.print("██╔══██╗██╔═══██╗██╔════╝ ██║   ██║██╔════╝    ██╔════╝██║  ██║██╔══██╗██╔══██╗██╔═══██╗██║    ██║");
        Terminal.warpCursor(8, 26);
        System.out.print("██████╔╝██║   ██║██║  ███╗██║   ██║█████╗      ███████╗███████║███████║██║  ██║██║   ██║██║ █╗ ██║");
        Terminal.warpCursor(9, 26);
        System.out.print("██╔══██╗██║   ██║██║   ██║██║   ██║██╔══╝      ╚════██║██╔══██║██╔══██║██║  ██║██║   ██║██║███╗██║");
        Terminal.warpCursor(10, 26);
        System.out.print("██║  ██║╚██████╔╝╚██████╔╝╚██████╔╝███████╗    ███████║██║  ██║██║  ██║██████╔╝╚██████╔╝╚███╔███╔╝");
        Terminal.warpCursor(11, 26);
        System.out.print("╚═╝  ╚═╝ ╚═════╝  ╚═════╝  ╚═════╝ ╚══════╝    ╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝  ╚═════╝  ╚══╝╚══╝ ");


        // printing the stories plot
        Terminal.warpCursor(14, 36);
        System.out.print("*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*");
        Terminal.warpCursor(15, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(16, 36);
        System.out.print("*   You the adventurer must set forth into the dark world of Rogue Shadow.  *");
        //Terminal.pause(1.5);
        Terminal.warpCursor(17, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(18, 36);
        System.out.print("* A land of mystical wonder and danger presents itself around every corner. *");
        //Terminal.pause(1.5);
        Terminal.warpCursor(19, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(20, 36);
        System.out.print("* The goal of the game is to survive and collect as many items as possible. *");
        //Terminal.pause(1.5);
        Terminal.warpCursor(21, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(22, 36);
        System.out.print("*         To advance to the next floor you must kill all the enemies.       *");
        //Terminal.pause(1.5);
        Terminal.warpCursor(23, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(24, 36);
        System.out.print("*         Beware of the dungeons enemies or you will surely perish!         *");
        Terminal.warpCursor(25, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(26, 36);
        System.out.print("*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*");
    }
}

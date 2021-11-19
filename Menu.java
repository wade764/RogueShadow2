import ansi_terminal.Terminal;
import ansi_terminal.Key;
import java.util.Scanner;

public class Menu {
    // returned in Main class to start the game
    private static Boolean playGame = false;

    //Used for printing the Title screen and creating a new game or loading
    static Boolean titleScreen() {

        for (int i = 0; i < 100; i++) {
            Terminal.warpCursor(i, 0);
            System.out.print("                                                                                                                                   ");
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
        System.out.print("<*|        .' .-'`            L: Load Game                              |*>\n\r");
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

        //used for testing
        //Terminal.pause(5);

        Terminal.warpCursor(15,75);
        Scanner menu = new Scanner(System.in);
        boolean correctAnswer = false;
        while (!correctAnswer) {
            String response = menu.next();
            Terminal.warpCursor(15,65);
            if (response.equalsIgnoreCase("N")) {
                correctAnswer = true;
                playGame = true;
            } else if (response.equalsIgnoreCase("L")) {
                correctAnswer = true;
                playGame = true;
                
                //***I believe below will load the info
                Game g = new Game();
                g.handleKey(Key.r);

            } else {
                System.out.print("Please enter N or L: ");
            }
        }
        return playGame;
    }
}

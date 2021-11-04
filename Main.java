import ansi_terminal.*;

public class Main {

    // this method prints the games plot
    public static void storyPlot() {
        // clearing the terminal screen
        for (int i = 0; i < 100; i++) {
            Terminal.warpCursor(i, 0);
            System.out.print("                                                                                                                               ");
        }

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
        Terminal.warpCursor(16, 36);//40
        System.out.print("*   You the adventurer must set forth into the dark world of Rogue Shadow.  *");
        Terminal.pause(1.5);
        Terminal.warpCursor(17, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(18, 36);//38
        System.out.print("* A land of mystical wonder and danger presents itself around every corner. *");
        Terminal.pause(1.5);
        Terminal.warpCursor(19, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(20, 36);//38
        System.out.print("* The goal of the game is to survive and collect as many items as possible. *");
        Terminal.pause(1.5);
        Terminal.warpCursor(21, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(22, 36);//46
        System.out.print("*         Beware of the dungeons enemies or you will surely perish!         *");
        Terminal.warpCursor(23, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(24, 36);
        System.out.print("*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*");
    }

    public static void main(String args[]) {
        // we must call rawMode when we start the program, or else nothing else will really work!
        Terminal.rawMode();

        storyPlot();

        Game theGame = new Game();

        // setting the status of pause and done
        boolean pause = false;
        boolean done = false;

        //creating a way to run the game while !done, and also having the ability to pause when needed
        while (!done) {
            if (!pause) {
                done = theGame.game();
            } else {
                //pause is true so do nothing
            }
            theGame.getPause();
        }
        theGame.endScreen();
        // we must call cookedMode before quitting or the terminal will be weirded out after program ends
        Terminal.cookedMode();
    }
}

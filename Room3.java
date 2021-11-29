import ansi_terminal.Terminal;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Room3 {
    // the grid holds the room geometry
    private String[] grid;

    // the size of the room
    private int rows = 30;
    private int cols = 60;

    public Room3() {
        // this initializes the room to one specific space
        //rows = 30;
        //cols = 60;

        // the actual room geometry
        // the i cells refer to where an item should be placed at
        // & symbol is for the staircase
        
        // modifying the rooms to have the same amount of items and enemies
        grid  = new String[] {
                "         ########################    ###################    ",
                "         ##           *        ##    ##               ##    ",
                "         ##  ######            ##    ##     @         ##    ",
                "         ##  ##  ##            ##    ##               ##    ",
                "         ##  ##  ##        &   ##    ###############  ##    ",
                "         ##  ##  ##            ##                 ##  ##    ",
                "         ##  ##  ############  ##                 ##  ##    ",
                "         ##  ##            ##  ##                 ##  ##    ",
                "###########  ####          ##  ################   ##  ##    ",
                "##      i      ##          ##                ##   ##  ##    ",
                "##             ##############                ##   ##  ##    ",
                "##   *                              *        ##   ##  ##    ",
                "##             ##############           i    #######  ##    ",
                "##             ##          ##                         ##    ",
                "##  #############          ##  #####################  ##    ",
                "##  ##                     ##  ##                 ##  ##    ",
                "##  #############          ##  ##                 ##  ##    ",
                "##             ##          ##  ##                 ##  ##    ",
                "#############  ##          ##  ##                 ##  ##    ",
                "           ##  ##          ##  ##                 ##  ##    ",
                "        #####  ##############  ##                 ##  ##    ",
                "        ##       *       i     ##      #############  ##    ",
                "        ##                     ##      ##       *     ##    ",
                "        ##                     ##      ##             ##    ",
                "        ##                     ##      ##             ##    ",
                "        ##                     ##      ##             ##    ",
                "        ##                     ##      ##             ##    ",
                "        ##              *      ##      ##  i          ##    ",
                "        ##  i                  ##      ##             ##    ",
                "        #########################      #################    "
        };
    }

    // returns the player's starting location in this room
    public Position getPlayerStart() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if ( grid[row].charAt(col) == '@') {
                    return new Position(row, col);
                }
            }
        }
        return null;
    }

    // returns a set of item boxes for this map, this is here because it depends on
    // the room geometry for where the boxes make sense to be
    public ArrayList<Box> getBoxes() {
        ArrayList<Box> boxes = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == 'i') {
                    
                    //singleton testing
                    boxes.add(new Box(row, col, ItemGenerator.instance().generate()));
                    //boxes.add(new Box(row, col, ItemGenerator.generate()));
                }
            }
        }
        return boxes;
    }

    // returns a set of enemies from this map, similarly to the boxes above
    public ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '*') {
                    enemies.add(EnemyGenerator.instance().generate(row, col));
                }
            }
        }
        return enemies;
    }

    // returns the warp point
    public ArrayList<Warp> getWarp() {
        ArrayList<Warp> warps = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '&') {
                    warps.add(new Warp(row, col));
                }
            }
        }
        return warps;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // draws the map to the screen
    public void draw() {
        Terminal.clear();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                char cell = grid[row].charAt(col);
                if (cell == '#') {
                    // a unicode block symbol
                    System.out.print('\u2588');
                } else {
                    // whatever else, just draw a blank (we DON'T draw starting items from map)
                    System.out.print(' ');
                }
            }
            System.out.print("\n\r");
        }
    }

    // returns if a given cell in the map is walkable or not
    public boolean canGo(int row, int col) {
        return grid[row].charAt(col) != '#';
    }

    public void save(PrintWriter out) {
        for (String s : grid) {
            out.println(s);
        }
    }

    public Room3(Scanner in) {
        grid = new String[rows];
        while (in.hasNext()) {
            for (int i = 0; i < rows; i++) {
                grid[i] = in.nextLine();
            }
        }
    }
}

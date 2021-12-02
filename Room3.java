import ansi_terminal.Terminal;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/** Creates and manages the layout, enemies, boxes, and player in the third room
 *
 */
public class Room3 {
    // the grid holds the room geometry
    private String[] grid;
    // the size of the room
    private int rows = 30;
    private int cols = 60;

    public Room3() {
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
                    boxes.add(new Box(row, col, ItemGenerator.instance().generate()));
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

    /** Writes the current room layout of Room 3 to the save file
     *
     * @param out the printwriter used to write data to a file
     */
    public void save(PrintWriter out) {
        for (String s : grid) {
            out.println(s);
        }
    }

    /** A constructor used for reading in the room layout of Room 3 from the save file
     *
     * @param in the scanner used to read in data from the file
     */
    public Room3(Scanner in) {
        // we must initialize a new grid here prior to loading
        grid = new String[30];
        while (in.hasNext()) {
            for (int i = 0; i < grid.length; i++) {
                grid[i] = in.nextLine();
            }
        }
    }
}

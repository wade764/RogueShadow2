// Room.java
// provides code for the drawing of a room
// also provides starting locations for the player, boxes, and enemies

import java.util.ArrayList;
import ansi_terminal.*;

public class Room {

    // the grid holds the room geometry
    private String[] grid;

    // the size of the room
    private int rows;
    private int cols;

    // Each room needs a method that sets the players icon
    //private static char playerSymbol;

    public Room() {
        // this initializes the room to one specific space
        rows = 30;
        cols = 60;

        // the actual room geometry
        // the i cells refer to where an item should be placed at
        grid  = new String[] {
                "##################                ######################    ",
                "##              ##                ##      i           ##    ",
                "##  @           ###########       ##        *         ##    ",
                "##                       ##       ##                  ##    ",
                "##              #######  ##       ##################  ##    ",
                "##              ##   ##  ##                       ##  ##    ",
                "##################   ##  ##################       ##  ##    ",
                "                     ##                  ##       ##  ##    ",
                "                     ##   *  i           ##       ##  ##    ",
                "                     ##                  ##       ##  ##    ",
                "                     ##############  ######       ##  ##    ",
                "                                 ##  ##           ##  ##    ",
                "                                 ##  ##           ##  ##    ",
                "                       ############  ###############  ######",
                "                       ##                                 ##",
                "                       ##     *                           ##",
                "    #####################                  *              ##",
                "    ##                                                    ##",
                "    ##  #################                          i      ##",
                "    ##  ##             ##                                 ##",
                "    ##  ##             #################  ##################",
                "    ##  ##                            ##  ##                ",
                "    ##  ##                            ##  ##                ",
                "    ##  ##                       #######  #######           ",
                "    ##  ##                       ##            ##           ",
                "######  ####                     ##  i  *      ##           ",
                "##        ##                     ##         &  ##           ",
                "## i  *   ##                     ################           ",
                "##        ##                                                ",
                "############                                                "
        };
    }

    // returns the player's starting location in this room
    public Position getPlayerStart() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if ( grid[row].charAt(col) == '@' /*|| grid[row].charAt(col) == playerSymbol*/ ) {
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
                    Item i = ItemGenerator.instance().generate();
                    boxes.add(new Box(row, col, i ));
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
                    enemies.add(EnemyGenerator.generate(row, col));
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
        //***print statement for testing
        //Terminal.warpCursor(40,0);
        //System.out.println("This is Room cango() row and col: "+row+" "+col);
        return grid[row].charAt(col) != '#';
    }
}

// Warp.java
// represents a warp point

import ansi_terminal.Color;

public class Warp extends Entity {
    /** Creates a warp object on a given row and column
     *
     * @param row the row the warp is located on
     * @param col the column the warp is located on
     */
    public Warp(int row, int col) {
        super(row, col, '&', Color.YELLOW);
    }
}                               

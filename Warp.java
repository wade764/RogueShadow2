// Warp.java
// represents a warp point

import ansi_terminal.Color;

public class Warp extends Entity {

    // add a warp
    public Warp(int row, int col) {
        super(row, col, '&', Color.YELLOW);
    }
}                               

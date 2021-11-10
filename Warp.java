// Warp.java
// represents a warp point

import ansi_terminal.*;

public class Warp extends Entity {
    // the Item that is in the box
    private Item item;

    // add a box with a given item in it
    public Warp(int row, int col, Item item) {
        super(row, col, '&', Color.YELLOW);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}                               

// Warp.java
// represents a warp point

import ansi_terminal.*;

public class Warp extends Entity {
    // this is bad code
    // the Item that is in the box
    //private Item item;

    // add a warp
    public Warp(int row, int col) {
        super(row, col, '&', Color.YELLOW);
        //this.item = item;
    }

    // this is bad code
    //public Item getItem() {
    //    return item;
    //}
}                               

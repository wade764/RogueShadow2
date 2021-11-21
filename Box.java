// Box.java
// represents a pickup-able item

import ansi_terminal.Color;
import java.io.PrintWriter;
import java.util.Scanner;

public class Box extends Entity {
    // the Item that is in the box
    private Item item;

    // add a box with a given item in it
    public Box(int row, int col, Item item) {
        super(row, col, 'i', Color.MAGENTA);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void save(PrintWriter out) {
        super.save(out);
        item.save(out);
    }

    public Box(Scanner in) {
        super(in);
        item = new Item(in);
        
        // left off here @ 2140 11_20_21
        //Wade - I am not sure if this is needed however, I will comment out for now I did not notice a change when testing with this
        //reading in an emptyline
        //in.nextLine();
    }
}

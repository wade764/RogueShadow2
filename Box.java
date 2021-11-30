// Box.java
// represents a pickup-able item

import ansi_terminal.Color;
import java.io.PrintWriter;
import java.util.Scanner;

/** Manages Box-type entities which contain items scattered across rooms
 *
 */
public class Box extends Entity {
    // the Item that is in the box
    private Item item;

    public Box(int row, int col, Item item) {
        super(row, col, 'i', Color.MAGENTA);
        this.item = item;
    }
    // returns the field item

    public Item getItem() {
        return item;
    }

    /** Saves the box to the save file
     *
     * @param out the printwriter
     */
    public void save(PrintWriter out) {
        super.save(out);
        item.save(out);
        out.println(".");
    }

    /** Loads the boxes from the ave file
     *
     * @param in the scanner
     */
    public Box(Scanner in) {
        super(in);
        item = new Item(in);
        //skipping the delimiter after each box
        in.nextLine();
    }
}

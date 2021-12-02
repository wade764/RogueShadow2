import ansi_terminal.Color;
import java.io.PrintWriter;
import java.util.Scanner;

/** Creates a box object that represents an item on the map that can be picked up
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

    /** Writes the box's position and the item inside it to the save file
     *
     * @param out the printwriter used to write data to a file
     */
    public void save(PrintWriter out) {
        super.save(out);
        item.save(out);
        out.println(".");
    }

    /** A constructor used for reading in the box's position and item inside it from the save file
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

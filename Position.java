// Position.java
// represents a simple row/col position in the world

import java.util.Scanner;
import java.io.PrintWriter;

public class Position {
    private int row;
    private int col;

    public Position() {
        row = 0;
        col = 0;
    }

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object other) {
        Position op = (Position) other;

        // they are equal when both coordinates match
        return this.row == op.row && this.col == op.col;
    }

    // returns whether a position is adjacent to another (or equal)
    public boolean isAdjacent(Position other) {
        int rowdiff = Math.abs(this.row - other.row);
        int coldiff = Math.abs(this.col - other.col);

        //returns a boolean whether this is true or false
        return rowdiff + coldiff < 2;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void save(PrintWriter out) {
        out.println(row);
        out.println(col);
    }

    public Position(Scanner in) {
        row = in.nextInt();
        col = in.nextInt();
    }
}


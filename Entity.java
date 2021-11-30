// Entity.java
// this class represents one moveable, drawable thing in the game

import ansi_terminal.*;
import java.io.PrintWriter;
import java.util.Scanner;

public class Entity {

    // the location of the entity in space
    private Position position;

    // the character used to draw it
    private char display;

    // the color used for drawing
    private Color color;

    // adding the current map/room integer
    private int whatMap;

    public Entity(int row, int col, char display, Color color) {
        position = new Position(row, col);
        this.display = display;
        this.color = color;
    }

    public void setPosition(int row, int col) {
        position = new Position(row, col);
    }

    public Position getPosition() {
        return position;
    }

    public int getRow() {
        return position.getRow();
    }

    public int getCol() {
        return position.getCol();
    }

    // translate the entity in space, unless it would hit a wall
    public boolean move(int rowChange, int colChange, Room room, Room2 room2, Room3 room3) {
        // find new position
        whatMap = World.instance().getRoom();
        int newRow = position.getRow() + rowChange;
        int newCol = position.getCol() + colChange;

        if (whatMap == 1){
            if (room.canGo(newRow, newCol)) {
                // draw a space where it currently is
                Terminal.warpCursor(position.getRow(), position.getCol());
                System.out.print(" ");
                // eliminating flash
                Terminal.warpCursor(40,0);

                // and then move it
                position = new Position(newRow, newCol);
                return true;
            } else {
                return false;
            }
        } else if (whatMap == 2) {
            if (room2.canGo(newRow, newCol)) {
                // draw a space where it currently is
                Terminal.warpCursor(position.getRow(), position.getCol());
                System.out.print(" ");
                //eliminating flash
                Terminal.warpCursor(40,0);

                // and then move it
                position = new Position(newRow, newCol);
                return true;
            } else {
                return false;
            }
        } else if (whatMap == 3) {
            if (room3.canGo(newRow, newCol)) {
                // draw a space where it currently is
                Terminal.warpCursor(position.getRow(), position.getCol());
                System.out.print(" ");
                //eliminating flash
                Terminal.warpCursor(40,0);

                // and then move it
                position = new Position(newRow, newCol);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void draw() {
        Terminal.warpCursor(position.getRow(), position.getCol());
        Terminal.setForeground(color);
        System.out.print(display);
        Terminal.reset();
    }

    /** Saves the entity to the save file
     *
     * @param out the printwriter used to write data to a file
     */
    public void save(PrintWriter out) {
        out.println("Entity");
        position.save(out);
        out.println(display);
        out.println(color);
    }

    /** Loads the entity from the save file
     *
     * @param in the scanner used to read in data from the file
     */
    public Entity(Scanner in) {
        //skips line in file that says Entity
        in.nextLine(); 
        position = new Position(in);
        display = in.nextLine().charAt(0);
        String c = in.nextLine();
        switch (c) {
            case "RED" -> color = Color.RED;
            case "CYAN" -> color = Color.CYAN;
            case "YELLOW" -> color = Color.YELLOW;
            case "GREEN" -> color = Color.GREEN;
            case "MAGENTA" -> color = Color.MAGENTA;
            case "BLUE" -> color = Color.BLUE;
            case "BLACK" -> color = Color.BLACK;
            case "WHITE" -> color = Color.WHITE;
        }
    }
}

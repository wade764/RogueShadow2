// Entity.java
// this class represents one moveable, drawable thing in the game

import ansi_terminal.*;

public class Entity {
    // the location of the entity in space
    private Position position;

    // the character used to draw it
    private char display;

    // the color used for drawing
    private Color color;

    // adding the current map/room integer
    private int whatMap = 1;
    private World world = Game.getWorld();

    public Entity(int row, int col, char display, Color color) {
        position = new Position(row, col);
        this.display = display;
        this.color = color;
    }

    // move the entity to a new location
    public void setPosition(int row, int col) {
        position = new Position(row, col);
    }

    // get the position of this entity
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
        whatMap = world.getRoom();
        int newRow = position.getRow() + rowChange;
        int newCol = position.getCol() + colChange;

        if (whatMap == 1){
            if (room.canGo(newRow, newCol)) {
                // draw a space where it currently is
                Terminal.warpCursor(position.getRow(), position.getCol());
                System.out.print(" ");

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

                // and then move it
                position = new Position(newRow, newCol);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    // draw this entity to the screen
    public void draw() {
        Terminal.warpCursor(position.getRow(), position.getCol());
        Terminal.setForeground(color);
        System.out.print(display);
        Terminal.reset();
    }
}

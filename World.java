public class World {
    private int room = 1;
    private static World theInstance;

    /** A singleton that creates a single instance of World
     *
     * @return the instance of the World
     */
    public static synchronized World instance() {
        if (theInstance == null) {
            theInstance = new World();
        }
        return theInstance;
    }

    private World() {
    }

    //used when the player needs to be moved to the next floor

    /** Updates the room the player is on when they access a warp
     *
     * @return the new room that the player is on
     */
    public int roomUpdate(){
        room++;
        return room;
    }

    /** Returns the room that the player is in
     *
     * @return the number of the room that the player is located in
     */
    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}

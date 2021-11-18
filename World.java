public class World {
    private int room = 1;
    //We need a singleton since there is only one world
    private static World theInstance;

    public static synchronized World instance() {
        if (theInstance == null) {
            theInstance = new World();
        }
        return theInstance;
    }
    
    public World() {
    }

    //used when the player needs to be moved to the next floor
    public int roomUpdate(){
        room++;
        return room;
    }

    public int getRoom() {
        return room;
    }
}

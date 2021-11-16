public class World {
    private int room = 1;
    //We need a signleton since there is only one world
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

    //used to return to the previous room
    public int roomReturn(){
        room--;
        return room;
    }

    public void nextRoom(int room){
        //draw room from parameter
        //room.draw();

    }

    public int getRoom() {
        return room;
    }
}

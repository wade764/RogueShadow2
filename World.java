public class World {
	private int room = 1;

//if warp true? or something like that
//then print the next map	
	
	//used when the player needs to be moved to the next floor
	public int roomUpdate(){
		room++;
		return room;
	}

	public void nextRoom(int room){
		//draw room from parameter
//		room.draw();

	}

	public int getRoom() {
		return room;
	}
}

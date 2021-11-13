public class World{
	private int room=1;

//if warp true? or something like that
//then print the next map	
	
	//room set to one rn so it can compile
	//change later	
	public int roomUpdate(){
		if (room==1 ){
			room++;
		}
		
		return room;
	}

	public void nextRoom(int room){
		//draw room from parameter
//		room.draw();

	}
}

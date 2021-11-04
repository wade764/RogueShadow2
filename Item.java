public class Item{
	//This class is to build Item objectss	
		ItemType type;
		private String name;
		private int weight;
		private int value;
		private int strength;
		private boolean status; //says whether the item has been picked up off the map yet

		//item constructor
		public Item(ItemType type, String name, int weight, int value, int strength){
			this.type = type;
			this.name = name;
			this.weight = weight;
			this.value = value;
			this.strength = strength;
			status = true; //starts out true, gets changed to false when the item gets picked up
		}
		
		//returns the weight of the Item object
		public int getWeight(){
			return weight;
		}

		//returns the value of the Item object
		public int getValue(){
			return value;
		}
		
		//returns the name of the Item object
		public String getName(){
			return name;
		}

		//returns the type of the Item Object
		public ItemType getType(){
			return type;
		}

		//returns the strength of the Item object
		public int getStrength() {
			return strength;
		}

		public boolean getStatus() {
			return status;
		}

		//changes the item's status to false when it has been picked up
		public void setStatus() {
			status = false;
		}

		//returns the weight, value and strength concatenated with string labels
		public String toString(){
		
			return name +  " weight: " + weight + " value: " + value + " strength: " + strength; 

		}
}

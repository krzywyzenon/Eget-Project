package alleycat;

import javax.swing.JTextArea;

public class Room {
String descriptions[];
int descNumber;
private int foodInRoom;
private boolean doorUnlockingStatus = false;
private JTextArea textField = null;

public Room(int food, int description){
		foodInRoom = food;
		
		descriptions = new String[3];
		descriptions[0]= "You are in... guess what!! ... a rooom\n Not so much to look at - just a door, an old TV, a couch, wardrobe and the food bowl.\n ...Oh and did I forget to mention the cage with an evil bat? You did not think it would be too easy now did you?";
		
		descNumber = description;
	}

public Room(int food){
	foodInRoom = food;
}
	
//everytime animal eats this method should follow
	
	public int animalEats(){
		foodInRoom --;
		return foodInRoom;
	}
	
	//method to be used only if the player has the key
	public void unlockDoor(){
		doorUnlockingStatus = true;
		System.out.println("You have unlocked the door.");
		textField.setText("You have unlocked the door.");
	}
	
	public boolean openDoor(){
		if (doorUnlockingStatus==true){
			System.out.println("Congratulations! You have opened the door and.... have to pay 10000kr for the full version of this game.");
			textField.setText("Congratulations! You have opened the door and.... have to pay 10000kr for the full version of this game.");
			return true;
		}
		else
		{
			System.out.println("Sorry the door is locked");
			textField.setText("Sorry the door is locked");
			return false;
		}
	}
	
	public void roomDescription(){
		System.out.println(descriptions[descNumber]);
}
	
	
	public void desc(){
	}
	
	//getters
	public int getFood(){
		return foodInRoom;
	}

	
	public boolean getUnlockStatus(){
		return doorUnlockingStatus;
	}
	
	//setters

	public void setUnlock(boolean value){
		doorUnlockingStatus = value;
	}
	
	public void setTextArea(JTextArea text){
		textField = text;
	}

}

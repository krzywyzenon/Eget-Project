package graphics;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import alleycat.MainCharacter;
import alleycat.Room;

public class DoorMenu {
	JDialog doorDialog = new JDialog();
	JTextArea text = new JTextArea();
	JButton openButton = new JButton("Open the door.");
	JButton leaveButton = new JButton("Cancel");
	JPanel buttonPanel = new JPanel();
	Room room;
	MainCharacter hero;
	
	DoorMenu(Room currentRoom, MainCharacter playerChar){
		text.setEditable(false);
		hero = playerChar;
		room = currentRoom;
		room.setTextArea(text);
		doorDialog.setVisible(true);
		doorDialog.setSize(300, 150);
		doorDialog.setResizable(false);
		doorDialog.setLayout(new GridLayout(2,1));
		
		buttonPanel.setLayout(new GridLayout(2,1));
		buttonPanel.add(openButton);
		buttonPanel.add(leaveButton);
		
		doorDialog.add(buttonPanel);
		doorDialog.add(text);
		
		openButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				if(hero.inventoryCheck("key to the door")==true){
					room.unlockDoor();
					hero.removeFromInventory("key to the door");
				}else{
					room.openDoor();
				}
					
			}
		});
		
		leaveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				doorDialog.dispose();
			}
		});
	}

}

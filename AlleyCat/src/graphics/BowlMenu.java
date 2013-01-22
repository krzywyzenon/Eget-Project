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

public class BowlMenu {
	JDialog bowlDialog = new JDialog();
	JTextArea text = new JTextArea();
	JButton eatButton = new JButton("Eat");
	JButton leaveButton = new JButton("Cancel");
	JPanel buttonPanel = new JPanel();
	Room room;
	MainCharacter hero;
	
	BowlMenu(Room currentRoom, MainCharacter playerChar){
		text.setEditable(false);
		hero = playerChar;
		room = currentRoom;
		room.setTextArea(text);
		bowlDialog.setVisible(true);
		bowlDialog.setSize(300, 180);
		bowlDialog.setResizable(false);
		bowlDialog.setLayout(new GridLayout(2,1));
		
		buttonPanel.setLayout(new GridLayout(2,1));
		buttonPanel.add(eatButton);
		buttonPanel.add(leaveButton);
		
		bowlDialog.add(buttonPanel);
		bowlDialog.add(text);
		
		eatButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				if(room.getFood()>0){
					if(hero.getHp() == hero.getMaxHp())
					{
						text.setText("Whaaaat??? You are at the full HP!!\n Why do you want to eat??? \nWanna get PHAAAAT like those \nAmerican McDonald's kids???");
					}else
					{
						if(hero.getMaxHp()-hero.getHp()>=20){
							System.out.println("Ahh delicious, but frankly you could get some more");
							hero.setHP(20);
						}
						else
						{
							System.out.println("Ahhh now you are full!!!");
							hero.setHP(hero.getMaxHp()-hero.getHp());
						}
						room.animalEats();
					}
					
				}else
				{
					System.out.println("Unfortunately the food bowl is empty - you have eaten everything!!!!");	
				}
			}
		});
		
		leaveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				bowlDialog.dispose();
			}
		});
	}

}

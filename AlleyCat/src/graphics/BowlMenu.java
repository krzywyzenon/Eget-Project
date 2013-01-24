package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import alleycat.MainCharacter;
import alleycat.Room;

//This class takes care of the food bowl and eating process - the logic code is directly pasted from the text version.
public class BowlMenu {
	JDialog bowlDialog = new JDialog();
	JTextArea text = new JTextArea();
	JButton eatButton = new JButton("Eat");
	JButton leaveButton = new JButton("Cancel");
	JPanel buttonPanel = new JPanel();
	Room room;
	MainCharacter hero;
	Font font;
	
	BowlMenu(Room currentRoom, MainCharacter playerChar){
		text.setEditable(false);
		hero = playerChar;
		room = currentRoom;
		room.setTextArea(text);
		bowlDialog.setVisible(true);
		bowlDialog.setSize(300, 180);
		bowlDialog.setResizable(false);
		bowlDialog.setLayout(new GridLayout(2,1));
		font = new Font("Verdana", Font.BOLD, 12);
		text.setFont(font);
		text.setBackground(new Color(118, 54, 18));
		text.setForeground(new Color(246, 192, 83));
		
		buttonPanel.setLayout(new GridLayout(2,1));
		buttonPanel.add(eatButton);
		buttonPanel.add(leaveButton);
		
		eatButton.setBackground(new Color(118, 54, 18));
		eatButton.setForeground(new Color(246, 192, 83));
		eatButton.setBorderPainted(false);
		leaveButton.setBackground(new Color(118, 54, 18));
		leaveButton.setForeground(new Color(246, 192, 83));
		leaveButton.setBorderPainted(false);
		
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
							text.setText("Ahh delicious, but frankly you could get \nsome more");
							hero.setHP(20);
						}
						else
						{
							text.setText("Ahhh now you are full!!!");
							hero.setHP(hero.getMaxHp()-hero.getHp());
						}
						room.animalEats();
					}
					
				}else
				{
					text.setText("Unfortunately the food bowl is empty - \nyou have eaten everything!!!!");	
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

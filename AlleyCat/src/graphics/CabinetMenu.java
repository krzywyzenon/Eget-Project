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

public class CabinetMenu {
	JDialog cabinetDialog = new JDialog();
	JTextArea text = new JTextArea();
	JButton searchButton = new JButton("Search the cabinet");
	JButton leaveButton = new JButton("Cancel");
	JPanel buttonPanel = new JPanel();
	MainCharacter hero;
	static boolean searched = false;
	
	CabinetMenu(MainCharacter playerChar){
		text.setEditable(false);
		hero = playerChar;
		
		cabinetDialog.setVisible(true);
		cabinetDialog.setSize(300, 150);
		cabinetDialog.setResizable(false);
		cabinetDialog.setLayout(new GridLayout(2,1));
		
		buttonPanel.setLayout(new GridLayout(2,1));
		buttonPanel.add(searchButton);
		buttonPanel.add(leaveButton);
		
		cabinetDialog.add(buttonPanel);
		cabinetDialog.add(text);
		
		searchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				if(searched==false){
					hero.addToInventory("scarf");
					text.setText("You found the scarf which can protect you \nfrom the bat's deadly bite");
					searched = true;
					
				}else{
					text.setText("Cabinet is empty");
				}
				
			}
		});
	
		
		leaveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				cabinetDialog.dispose();
			}
		});
		
	}
}

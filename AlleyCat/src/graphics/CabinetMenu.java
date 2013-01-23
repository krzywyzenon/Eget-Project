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

public class CabinetMenu {
	JDialog cabinetDialog = new JDialog();
	JTextArea text = new JTextArea();
	JButton searchButton = new JButton("Search the cabinet");
	JButton leaveButton = new JButton("Cancel");
	JPanel buttonPanel = new JPanel();
	MainCharacter hero;
	static boolean searched = false;
	Font font;
	
	CabinetMenu(MainCharacter playerChar){
		text.setEditable(false);
		hero = playerChar;
		font = new Font("Verdana", Font.BOLD, 12);
		
		cabinetDialog.setVisible(true);
		cabinetDialog.setSize(300, 150);
		cabinetDialog.setResizable(false);
		cabinetDialog.setLayout(new GridLayout(2,1));
		
		text.setFont(font);
		text.setBackground(new Color(118, 54, 18));
		text.setForeground(new Color(246, 192, 83));
		
		searchButton.setBackground(new Color(118, 54, 18));
		searchButton.setForeground(new Color(246, 192, 83));
		searchButton.setBorderPainted(false);
		leaveButton.setBackground(new Color(118, 54, 18));
		leaveButton.setForeground(new Color(246, 192, 83));
		leaveButton.setBorderPainted(false);
		
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

package graphics;
//Class showing the character sheet. Name problems same as with Inventory class
import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import alleycat.MainCharacter;

public class Statss {
	JDialog panel;
	JTextArea text;
	MainCharacter hero;
	Font font;
	
	public Statss(MainCharacter playerChar){
		font = new Font("Verdana", Font.BOLD, 12);
		hero = playerChar;
		panel = new JDialog();
		panel.setSize(375, 150);
		panel.setVisible(true);
		text = new JTextArea();
		text.setEditable(false);
		text.setFont(font);
		text.setBackground(new Color(118, 54, 18));
		text.setForeground(new Color(246, 192, 83));
		panel.add(text);
		hero.setTextField(text);
		hero.statsToGui();
	}

}

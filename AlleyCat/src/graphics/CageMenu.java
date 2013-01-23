package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import alleycat.Battle;
import alleycat.Enemy;
import alleycat.MainCharacter;

public class CageMenu {
	private JDialog cageDialog = new JDialog();
	JTextArea text = new JTextArea();
	JScrollPane scroll = new JScrollPane(text);
	JButton fightButton = new JButton("Fight the bat");
	JButton leaveButton = new JButton("Cancel");
	JPanel buttonPanel = new JPanel();
	Battle battle;
	MainCharacter hero;
	Enemy evilBat;
	private boolean heroDead = false;
	static private boolean batDead = false;
	Thread t;
	Thread cage;
	CageMenu cageToSend;
	boolean battleFinished = false;
	private FirstRoom gameScreen;
	Font font;
	Font font1;
	
	public CageMenu(Battle currentBattle, MainCharacter playerChar, Enemy enemy, FirstRoom screen){
		gameScreen = screen;
		cageToSend = this;
		font = new Font("Verdana", Font.BOLD, 12);
		font1 = new Font("Verdana", Font.BOLD, 24);
		text.setEditable(false);
		text.setFont(font);
		text.setBackground(new Color(118, 54, 18));
		text.setForeground(new Color(246, 192, 83));
		hero = playerChar;
		evilBat = enemy;
		battle = currentBattle;
		battle.setTextArea(text);
		currentBattle.setCage(cageToSend);
		cageDialog.setVisible(true);
		cageDialog.setSize(300, 550);
		cageDialog.setResizable(false);
		cageDialog.setLayout(new GridLayout(2,1));
		t = new Thread(currentBattle);

		fightButton.setBackground(new Color(118, 54, 18));
		fightButton.setForeground(new Color(246, 192, 83));
		fightButton.setBorderPainted(false);
		fightButton.setFont(font1);
		leaveButton.setBackground(new Color(118, 54, 18));
		leaveButton.setForeground(new Color(246, 192, 83));
		leaveButton.setBorderPainted(false);
		leaveButton.setFont(font1);
		
		buttonPanel.setLayout(new GridLayout(2,1));
		buttonPanel.add(fightButton);
		buttonPanel.add(leaveButton);
		
		cageDialog.add(scroll);
		cageDialog.add(buttonPanel);
		
		DefaultCaret caret = (DefaultCaret)text.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		text.setText("In order to get the key you will have to \nfight the EVIIIIILLLLL bat. \nIt is the difficult opponent though - \nafter all - it IS EVIIIIILLLLL!!! \nUnless you have some protection it may be \na tough one.");
		
		fightButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				if(!batDead){
					text.setText("");
					if(hero.inventoryCheck("scarf")==true)
					{
						evilBat.setDamage(25);
						evilBat.setHitChance(45);
						hero.removeFromInventory("scarf");
						
					}
					t.start();
					
				}else{
					text.setText("The bat is dead!!!");
				}
			}
		});
		
		leaveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				cageDialog.dispose();
			}
		});
		
		
	}

	public void setB(boolean s){
		battleFinished = s;
	}
	
	public static void setBatDead(boolean isDead){
		batDead = isDead;
	}
	
	public JDialog getWindow(){
		return cageDialog;
	}
	
	public FirstRoom getGameScreen(){
		return gameScreen;
	}


}

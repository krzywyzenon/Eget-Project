package graphics;

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
	JDialog cageDialog = new JDialog();
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
	
	public CageMenu(Battle currentBattle, MainCharacter playerChar, Enemy enemy){
		cageToSend = this;
		text.setEditable(false);
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
				hero.stats();
//				cageDialog.dispose();
			}
		});
		
		
	}

	public void setB(boolean s){
		battleFinished = s;
	}
	
	public static void setBatDead(boolean isDead){
		batDead = isDead;
	}


}

package graphics;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
	private boolean batDead = false;
	
	CageMenu(Battle currentBattle, MainCharacter playerChar, Enemy enemy){
		text.setEditable(false);
		hero = playerChar;
		evilBat = enemy;
		battle = currentBattle;
		battle.setTextArea(text);
		cageDialog.setVisible(true);
		cageDialog.setSize(300, 550);
		cageDialog.setResizable(false);
		cageDialog.setLayout(new GridLayout(2,1));
		
		buttonPanel.setLayout(new GridLayout(2,1));
		buttonPanel.add(fightButton);
		buttonPanel.add(leaveButton);
		
		cageDialog.add(scroll);
		cageDialog.add(buttonPanel);
		
		text.setText("In order to get the key you will have to \nfight the EVIIIIILLLLL bat. \nIt is the difficult opponent though - \nafter all - it IS EVIIIIILLLLL!!! \nUnless you have some protection it may be \na tough one.");
		
		fightButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				text.setText("");
				if(hero.inventoryCheck("scarf")==true)
				{
					evilBat.setDamage(25);
					evilBat.setHitChance(45);
					hero.removeFromInventory("scarf");
					
				}
				heroDead = battle.fight();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (heroDead==false){
					hero.setXp(battle.xpGain());
					hero.checkXp();
					batDead = battle.foeDead;
					System.out.println("After defeating that flying monstrosity you retrieve the door key!");
					hero.addToInventory("key to the door");
				}
			}
		});
		
		leaveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				cageDialog.dispose();
			}
		});
	}

}

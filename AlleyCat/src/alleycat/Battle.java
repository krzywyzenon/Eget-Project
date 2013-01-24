package alleycat;

import graphics.CageMenu;
import graphics.FirstRoom;
import graphics.GameOver;

import java.util.Random;

import javax.swing.JTextArea;

//Class taking care of battle mechanics, rewards (xp, loot) and sends information to the text area of CageMenu class. In case of defeat this class calls
//upon respective instance of GameOver class
public class Battle implements Runnable {
	public boolean foeDead = false;
	
	Random rnd;
	MainCharacter hero;
	Enemy enemy;
	int battleXp = 0;
	JTextArea text;
	Thread t;
	CageMenu cage;
	FirstRoom gameArea;
		
	public Battle(MainCharacter player, Enemy antagonist){
		hero = player;
		enemy = antagonist;


	}
	
	public Battle(MainCharacter player, Enemy antagonist, JTextArea txt){
		hero = player;
		enemy = antagonist;
		text = txt;
	}
	
	public boolean fight(){
		
		MainCharacter mainChar = hero;
		Enemy opponent= enemy;
		String enemyName = enemy.getName();
		hero.setEnemyName(enemyName);
		int heroAttack;
		int foeAttack;
		boolean heroDead = false;
		rnd = new Random();
		int dices;
		
		do{
			heroAttack=mainChar.strike();
			dices = rnd.nextInt(100);
			if(dices<mainChar.getHitChance()){
				foeDead=opponent.getHit(heroAttack);
				text.append(opponent.getBattleMsg() + System.lineSeparator());
				if(opponent.getDeathMsg()!=null)
					text.append(opponent.getDeathMsg() + System.lineSeparator());
			}else{
				text.append("You missed!" + System.lineSeparator());
			}
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			if(foeDead==false){
				foeAttack=opponent.hit();
				dices = rnd.nextInt(100);
				if(dices<opponent.getHitChance()){
					heroDead=mainChar.getHit(foeAttack);
					text.append(mainChar.getBattleMsg()+System.lineSeparator());
				}
				else
				{
					text.append("You have avoided the blow" + System.lineSeparator());
				}
					
			}else
			{
				text.append("You got " + opponent.getXp() + " xp.");
				battleXp = opponent.getXp();
			}
			
		}while(heroDead == false && foeDead == false);
		
		if(heroDead) text.append(mainChar.getDeathMsg());
		return heroDead;
		
	}
	
	public boolean foeDeadCheck(){
		return foeDead;
	}
	
	public int xpGain(){
		return battleXp;
	}
	
	public void setTextArea(JTextArea txt){
		text = txt;
	}
	
	public void setCage(CageMenu cg){
		cage = cg;
	}

	@Override
	public void run() {

		MainCharacter mainChar = hero;
		Enemy opponent= enemy;
		String enemyName = enemy.getName();
		hero.setEnemyName(enemyName);
		int heroAttack;
		int foeAttack;
		boolean heroDead = false;
		rnd = new Random();
		int dices;
		
		do{
			heroAttack=hero.strike();
			dices = rnd.nextInt(100);
			if(dices<hero.getHitChance()){
				foeDead=enemy.getHit(heroAttack);
				text.append(enemy.getBattleMsg() + System.lineSeparator());
				if(opponent.getDeathMsg()!=null)
					text.append(opponent.getDeathMsg() + System.lineSeparator());
			}else{
				text.append("You missed!" + System.lineSeparator());
			}
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			if(foeDead==false){
				foeAttack=opponent.hit();
				dices = rnd.nextInt(100);
				if(dices<enemy.getHitChance()){
					heroDead=hero.getHit(foeAttack);
					text.append(hero.getBattleMsg()+System.lineSeparator());
				}
				else
				{
					text.append("You have avoided the blow" + System.lineSeparator());
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}else
			{
				text.append("You got " + opponent.getXp() + " xp.");
				battleXp = opponent.getXp();
				foeDead = true;
				System.out.println(foeDead);
				cage.setB(true);
				
				if (heroDead==false){
					hero.setXp(xpGain());
					hero.checkXp();
					CageMenu.setBatDead(foeDead);
					text.append("\nAfter defeating that flying monstrosity \nyou retrieve the door key!");
					hero.addToInventory("key to the door");
				}

			}
			
		}while(heroDead == false && foeDead == false);
		
		if(heroDead) {
			text.append(hero.getDeathMsg());
			cage.getWindow().dispose();
			cage.getGameScreen().getWindow().dispose();
			GameOver go = new GameOver("defeat");
			
		}
		
		
	}

}

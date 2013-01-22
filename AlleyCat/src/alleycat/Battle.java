package alleycat;

import java.util.Random;

import javax.swing.JTextArea;

public class Battle {
	public boolean foeDead = false;
	
	Random rnd;
	MainCharacter hero;
	Enemy enemy;
	int battleXp = 0;
	JTextArea text;
		
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

}

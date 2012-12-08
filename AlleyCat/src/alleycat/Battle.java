package alleycat;

import java.util.Random;

public class Battle {
	boolean foeDead = false;
	
	Random rnd;
	MainCharacter hero;
	Enemy enemy;
	int battleXp = 0;
		
	public Battle(MainCharacter player, Enemy antagonist){
		hero = player;
		enemy = antagonist;
		
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
			}else{
				System.out.println("You missed!");
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
				}
				else
				{
					System.out.println("You have avoided the blow");
				}
					
			}else
			{
				System.out.println("You got " + opponent.getXp() + " xp.");
				battleXp = opponent.getXp();
			}
			
		}while(heroDead == false && foeDead == false);
		
		return heroDead;
		
	}
	
	public boolean foeDeadCheck(){
		return foeDead;
	}
	
	public int xpGain(){
		return battleXp;
	}

}

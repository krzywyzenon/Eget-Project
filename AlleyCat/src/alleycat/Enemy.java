package alleycat;

import java.util.Random;

//Class describing enemy with its name and stats. Like MainCharacter this class contains getHit method
public class Enemy {
	private int hitPoints;
	private int damage;
	private int xpWorth;
	private int hitPointsLoss;
	private int defense;
	private String name;
	private int chanceToHit;
	private Random rnd = new Random();
	private int dices;
	private String battleMsg;
	private String deathMsg=null;
	
	public Enemy(String enemyName, int hp, int dmg, int xp, int def, int hitCh){
		hitPoints = hp;
		damage = dmg;
		xpWorth = xp;
		defense = def;
		name = enemyName;
		chanceToHit = hitCh;
	}
	
	public int hit(){
		return damage;
	}
	
	public boolean getHit(int dmg){
		dices = rnd.nextInt(6);
		hitPointsLoss = dmg + dices - defense;
		if(hitPointsLoss>0){
//			System.out.println("You hit " + name +" for: "+ hitPointsLoss);
			battleMsg = "You hit " + name +" for: "+ hitPointsLoss;
			hitPoints = hitPoints - hitPointsLoss;
		}else
		{
//			System.out.println("You cannot penetrate your enemy's armour!!!");
			battleMsg = "You cannot penetrate your enemy's armour!!!";
			hitPoints = hitPoints;
			
		}
			
		if(hitPoints <= 0){
//			System.out.println(name + " is dead");
			deathMsg = name + " is dead";
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int getHitChance(){
		return chanceToHit;
	}
	
	public int getHp(){
		return hitPoints;
	}

	public int getXp(){
		return xpWorth;
	}
	
	public String getName(){
		return name;
	}
	
	public String getBattleMsg(){
		return battleMsg;
	}

	public String getDeathMsg(){
		return deathMsg;
	}
		
	public void setDefense(int defValue){
		defense = defValue;
	}

	public void setDamage(int dmgValue){
		damage = dmgValue;
	}
	
	public void setHitChance(int n){
		chanceToHit =  n;
	}
	
}

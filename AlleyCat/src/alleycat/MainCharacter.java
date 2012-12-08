package alleycat;

import java.util.Random;

public class MainCharacter {
	private int maxHitPoints;
	private int hitPoints;
	private int hitPointsLoss;
	private int damage;
	private int xp = 0;
	private int defense = 10;
	private int level = 1;
	private int raiseLevel=100;
	private String enemyName;
	private int chanceToHit = 0;
	private Random rnd = new Random();
	private int dices;
	private String inventory[];
	private int invCounter = 0;
//	private boolean check = false;
	
	
	public MainCharacter(int hp, int dmg, int hitCh){
		maxHitPoints = hp;
		hitPoints = hp;
		damage = dmg;
		chanceToHit = hitCh;
		inventory = new String[10];
		for(int i = 0; i<inventory.length; i++){
			inventory[i]= "nothing";
		}
	}
	
	public int strike(){
		return damage;
	}
	
	public boolean getHit(int dmg){
		dices = rnd.nextInt(6);
		hitPointsLoss = dmg + dices - defense;

		if(hitPointsLoss >0){
			System.out.println("You got hit by " + enemyName +" for: "+ hitPointsLoss);
			hitPoints = hitPoints - hitPointsLoss;
		}else{
			System.out.println(enemyName + " strikes too weakly to wound you.");
//			hitPoints = hitPoints;
		}
			
		if(hitPoints <= 0){
			System.out.println("You are dead");
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// getters
	
	public int getMaxHp(){
		return maxHitPoints;
	}
	
	public int getHitChance(){
		return chanceToHit;
	}
	
	public int getHp(){
		return hitPoints;
	}
	public int getXp(){
		return xp;
	}
	public int getDefense(){
		return defense;
	}
	
	public int getRaiseLevel(){
		return raiseLevel;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getInventoryCounter(){
		return invCounter;
	}
	
	//setters
	
	public void setMaxHp(int amount){
		maxHitPoints = maxHitPoints + amount;
	}
	
	public void setHP(int amount){
		hitPoints = hitPoints + amount;
	}

	public void setXp(int amount){
		xp = xp + amount;
	}
	public void setDamage(int amount){
		damage = damage + amount;
	}
	public void setDefense(int amount){
		defense = defense + amount;
	}
	
	public void setLevel(int lvl){
		level = level + lvl;
	}

	public void setRaiseLevel(){
		raiseLevel += 100;
	}
	
	public void setEnemyName(String name){
		enemyName = name;
	}
	
	public void setHitChance(int n){
		chanceToHit = chanceToHit +n;
	}
	
	public boolean setInvCounter(){
			for(int i=0; i<inventory.length; i++){
				if(inventory[i] == "nothing"){
					invCounter = i;
					return true;
				}
			}
			return true;
	}
		
				
	
	//other methods
	
	public void addToInventory(String item){
		inventory[invCounter] = item;
		setInvCounter();
		
	}
	
	public void removeFromInventory(String item){
		for(int i = 0; i<inventory.length; i++){
			if(inventory[i] == item){
				inventory[i] = "nothing";
				setInvCounter();
				break;
			}
		}
	}
	
	public void checkXp(){
	
	}
	
	public boolean inventoryCheck(String thingie){
		 boolean check = false;
		for(int i = 0; i<inventory.length; i++){
			
			if(inventory[i].toLowerCase() == thingie)
				check = true;
		}
		return check;
	}
public  boolean invCheck(String thingie){
		boolean invCheck = false;
		for (int i = 0; i<inventory.length; i++){
			if(inventory[i].toLowerCase() == thingie)
				invCheck = true;
		}
		return invCheck;
	}
	
	public void stats(){
		System.out.println("------------------------------------------------------");
		System.out.println("| Hit | |    HP     | | Atk | | Def | | Xp | | Level |");
		System.out.println("------------------------------------------------------");
		System.out.println("| "+getHitChance()+"% | | "+getHp()+" / " + getMaxHp() + " | |  "+getDamage()+" | |  "+getDefense()+" | |  "+getXp()+" | |     "+getLevel()+ " |");
		System.out.println("------------------------------------------------------");
		
	}
	
	public void inventoryList(){
		System.out.println("Your inventory: ");
		for(int i=0; i<inventory.length; i++){
			if(inventory[i]!="nothing")
				System.out.print(inventory[i] + ", ");
		}
		System.out.print("\n");
		
	}
	
}

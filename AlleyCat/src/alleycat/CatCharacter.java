package alleycat;

public class CatCharacter extends MainCharacter {
	
	public CatCharacter(int hp, int dmg, int hitCh) {
		super(hp, dmg, hitCh);
		super.setDamage(super.getDamage());
	
	}
	
	public void checkXp(){
		if(super.getXp()>super.getRaiseLevel())
		{
			System.out.println("Congratulations! Your level has increased.");
			super.setLevel(1);
			super.setRaiseLevel();
			super.setDamage(5);
			super.setHP(10);
			super.setMaxHp(10);
			super.setHitChance(2);
		}
	}

}

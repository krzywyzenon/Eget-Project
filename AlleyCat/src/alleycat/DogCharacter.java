package alleycat;

public class DogCharacter extends MainCharacter {

	public DogCharacter(int hp, int dmg, int hitCh) {
		super(hp, dmg, hitCh);
		super.setDefense(10);
	}
	
	public void checkXp(){
		if(super.getXp()>super.getRaiseLevel())
		{
			System.out.println("Congratulations! Your level has increased.");
			super.setLevel(1);
			super.setRaiseLevel();
			super.setDamage(1);
			super.setHP(25);
			super.setMaxHp(25);
			super.setHitChance(1);
		}
	}

}

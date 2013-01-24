package alleycat;
//Class inheriting from MainCharacter. The only playable class in GUI version. It has better damage than DogCharacter, but less defense.
//On levelling up it gets more hitting chance and damage comparing to the DogCharacter, but gets less hp instead.
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

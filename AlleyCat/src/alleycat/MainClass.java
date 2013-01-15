package alleycat;

import java.util.Scanner;

public class MainClass {
// Do zrobienia nastepnym razem: pomyslec nad ekwipunkiem - konkretnie indeksowaniem i usuwaniem tegoz (moze zmiana typu na object), 
//	popracowac nad pokojem i scenariuszem - last upd zrobic petle w kazdym wyborze. pamietac o inventory!!!
	// UPD - poza petlami wiekszosc zrobiona - tero scenariusz!!
	static boolean heroDead=false;
	static Scanner scan = new Scanner(System.in);
	static char choice;
	static MainCharacter ourHero;
	static boolean inventoryCheck = false;
	static Room startRoom = new Room(2, 0);
	static Enemy evilBat = new Enemy("EVIIIILLLLL bat", 50, 120, 20, 7, 70);
	static boolean scarfTaken;
	
	public static void main(String[] args) {
//		boolean foeDead=false;
//		int heroAttack;
//		int foeAttack;
		
		ourHero = characterCreation();
//		ourHero.addToInventory("scarf");
////		System.out.println(ourHero.getInventory());
//		System.out.println(ourHero.inventoryCheck("pioch"));
//		System.out.println(ourHero.inventoryCheck("scarf"));
//		System.out.println(ourHero.inventoryCheck("luz"));
		
		firstRoom(true, false, false, false);
//		firstRoomReturn();
//		ourHero.addToInventory("Key to the door");
//		System.out.println(ourHero.getInventoryCounter());
//		ourHero.addToInventory("bottle");
//		System.out.println(ourHero.getInventoryCounter());
//		ourHero.inventoryList();
//		ourHero.removeFromInventory("Key to the door");
//		ourHero.addToInventory("hammer");
//		ourHero.addToInventory("nail");
//		ourHero.addToInventory("screwdriver");
//		ourHero.inventoryList();
//		
		
	}
	
	public static MainCharacter characterCreation(){
		boolean characterChosen = false;
		MainCharacter hero = null;
		do{
			
			System.out.println("Which animal will you be?");
			System.out.println("(C)at");
			System.out.println("(D)og");
			choice = Character.toUpperCase(scan.next().charAt(0));
			if(choice == 'C'){
				System.out.println("Cats, are gracefull, majestic, proud and... and who the hell am I kidding - Cat = +damage -defense");
				System.out.println("Are you sure, you want to be a cat?");
				System.out.println("(Y)es.");
				System.out.println("(N)o.");
				choice = Character.toUpperCase(scan.next().charAt(0));
				if(choice == 'Y'){
					hero = new CatCharacter(100, 10, 50);
					characterChosen = true;
				}else
				{
					characterChosen = false;
				}
				
			}else
			{
				System.out.println("Dogs are loyal, durable and faithful bla bla bla -damage + defense");
				System.out.println("Are you sure, you want to be a dog?");
				System.out.println("(Y)es.");
				System.out.println("(N)o.");
				choice = Character.toUpperCase(scan.next().charAt(0));
				if(choice == 'Y'){
					hero = new DogCharacter(100,10, 40);
					characterChosen = true;
				}else
				{
					characterChosen = false;
				}
			}
		}while(characterChosen==false);
		
		return hero;
	}
	
	public static void firstRoom(boolean presence, boolean unlock,  boolean door, boolean bat){
		boolean inTheRoom = presence;
		boolean openDoor = door;
		boolean batDead = bat;
		startRoom.setUnlock(unlock);
		Battle batFight = new Battle(ourHero, evilBat);
		System.out.println("Something something something adventure, something something something begins...\nUnfortunately your computer is not strong enough to play with graphics, so, dear user you have to experience text version of the game...");
		do{
			System.out.println("Menu");
			System.out.println("(I)nventory");
			System.out.println("(C)haracter sheet");
			System.out.println("(L)ook at your surrounding");
			System.out.println("(Q)uit");
			choice = Character.toUpperCase(scan.next().charAt(0));
			
			switch(choice){
				case 'I':
					ourHero.inventoryList();
					break;
				
				case 'C':
					ourHero.stats();
					break;
				case 'L':
					{
						System.out.println("What is it that you would like to do? Will you take the closer look at the (B)at cage, the (C)abinet, the (D)oor, or maybe you will (E)at?");
						choice = Character.toUpperCase(scan.next().charAt(0));
						switch(choice){
							case 'B':
							{
								if(batDead == false){
									{
										System.out.println("You look at the magnificent, huge, golden cage, and... ahhh whom am I kidding.. what you're interested in is the front door key which lies at the bottom of the cage.");
										System.out.println("Unfortunately in order to get it you will have to fight the EVIIIIILLLLL bat. It is the difficult opponent though - after all - it IS EVIIIIILLLLL!!! Unless you have some protection it may be a tough one.");
										System.out.println("Up to the task? (Y)es or (No)?");
										choice = Character.toUpperCase(scan.next().charAt(0));
										if(choice=='Y'){
											if(ourHero.inventoryCheck("scarf")==true)
											{
												evilBat.setDamage(25);
												evilBat.setHitChance(45);
												ourHero.removeFromInventory("scarf");
												
											}
											heroDead = batFight.fight();
											try {
												Thread.sleep(1000);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
											if (heroDead==false){
												ourHero.setXp(batFight.xpGain());
												ourHero.checkXp();
												batDead = batFight.foeDead;
												System.out.println("After defeating that flying monstrosity you retrieve the door key!");
												ourHero.addToInventory("key to the door");
											}
											
										}else
											break;
									}
										
								}else
								{
									System.out.println("Bat is dead!!!!!");
								}
									
							}
								
								break;
							case 'C':
							{
								if(ourHero.inventoryCheck("scarf")==false && scarfTaken==false){
									System.out.println("Among the worthless rags, you can see an amazingly looking scarf, which can protect you from the Evil Bat's deadly bite");
									System.out.println("Soo you take it or not? Actually it was a rethorical quesiton - of course you take it - what kind of player would you be if you did not collect all the junk you see???");
									ourHero.addToInventory("scarf");
									scarfTaken=true;
								}
								else
								{
								System.out.println("The cabinet is empty. Well not literally empty, but the junk it contains is worthless even for you!");	
								}
									
							}
								
								break;
							case 'D':
							{	
								do{
									choice = '*';
									System.out.println("You are standing before the door. Not just any door - THE DOOR. Your passage to freedom. Will you open them?");
									if(ourHero.inventoryCheck("key to the door")==true && startRoom.getUnlockStatus()==false){
										System.out.println("(U)nlock THE DOOR, or (L)eave the door.");
									}
									else
									{
										System.out.println("(O)pen THE DOOR, or (L)eave the door.");
									}
										
									choice = Character.toUpperCase(scan.next().charAt(0));
									if (choice == 'O'){
										
										openDoor = startRoom.openDoor();
										if(openDoor == true)
										inTheRoom = false;
									}
									else if(choice == 'U')
										startRoom.unlockDoor();
										ourHero.removeFromInventory("key to the door");
								}while(choice!='L' && openDoor==false);
									
							}
								break;
							case 'E':
							{
								do{
									choice = '*';
									if(startRoom.getFood()>0){
										if(ourHero.getHp() == ourHero.getMaxHp())
										{
											System.out.println("Whaaaat??? You are at the full HP!! Why do you want to eat??? Wanna get PHAAAAT like those American McDonald's kids???");
										}else
										{
											if(ourHero.getMaxHp()-ourHero.getHp()>=20){
												System.out.println("Ahh delicious, but frankly you could get some more");
												ourHero.setHP(20);
											}
											else
											{
												System.out.println("Ahhh now you are full!!!");
												ourHero.setHP(ourHero.getMaxHp()-ourHero.getHp());
											}
											startRoom.animalEats();
										}
										
									}else
									{
										System.out.println("Unfortunately the food bowl is empty - you have eaten everything!!!!");	
									}
									System.out.println("(C)ontinue feeding or (L)eave the bowl");
									choice = Character.toUpperCase(scan.next().charAt(0));
									
								}while(choice!='L');
								
							}
								break;
						}
					}
					
					break;
				case 'Q':
					System.out.println("Thank you for playing! Bye");
					System.exit(0);
					break;
			}
			
			
		}while(heroDead==false && inTheRoom == true);
		

	}
	
	public static void firstRoomReturn(){
		firstRoom(true, true, false, true);
	}
	
	
		
	


}

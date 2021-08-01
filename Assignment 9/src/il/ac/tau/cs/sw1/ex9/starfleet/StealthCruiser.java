package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends battleShip  {
	

	private final int ANNUAL_MAINTNANCE = 2500;
	private static List<Weapon> defaultWeaponsList = createDefaultWeaponsList();
	private int stealthCruisersCounter = 0;
	
	
	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons) {
		super(name, commissionYear,maximalSpeed, crewMembers, weapons);
		this.stealthCruisersCounter++;
	}

	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers){
		this(name, commissionYear,maximalSpeed, crewMembers, defaultWeaponsList);
	}
	
	private static List<Weapon> createDefaultWeaponsList(){
		List<Weapon> weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon ("Laser Cannons",10,100));
		
		return weapons;
	}
	
	public List<Weapon> getWeapon(){
		return this.weapons;
	}
	
	public int getFirePower() {
		return super.getFirePower();
	}
	
	public int getAnnualMaintenanceCost() {
		
		int res = ANNUAL_MAINTNANCE;
		
		for(Weapon item: this.weapons) {
			res += item.getAnnualMaintenanceCost();
		}
		
		res+= Math.round(1000*this.maximalSpeed);
		
		int cloakAddition = 50*this.stealthCruisersCounter;
		res += cloakAddition;
		
		return res;
	}
	
	public String toString() {
		
		String res = "StealthCruiser" + System.lineSeparator();
		res += super.toString();
		res +=  System.lineSeparator();
		res += "	";
		res += super.getWeaponsListString();

		return res;
	
		}

	
}

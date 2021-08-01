package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Fighter extends battleShip {
	
	private final int ANNUAL_MAINTNANCE = 2500;
	
	
	public Fighter(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons){
		super(name, commissionYear,maximalSpeed, crewMembers, weapons);
	}
	
	public int getFirePower() {
		return super.getFirePower();
	}
	
	public List<Weapon> getWeapon(){
		return this.weapons;
	}
	
	public int getAnnualMaintenanceCost() {
		int res = ANNUAL_MAINTNANCE;
		
		for(Weapon item: this.weapons) {
			res += item.getAnnualMaintenanceCost();
		}
		
		res+= Math.round(1000*this.maximalSpeed);
		
		return res;
	}
	
	public String toString() {
		
		String res = "Fighter" + System.lineSeparator();
		res += super.toString();
		res +=  System.lineSeparator();
		res += "	";
		res += super.getWeaponsListString();

		return res;
	
		}
	
}

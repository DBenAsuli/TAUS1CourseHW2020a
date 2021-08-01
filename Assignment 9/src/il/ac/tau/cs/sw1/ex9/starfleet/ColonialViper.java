package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class ColonialViper extends Ship {

	private List<Weapon> weapons;
	private final int ANNUAL_MAINTNANCE = 4000;
	private Set<CrewWoman> crewWomen;
	
	public ColonialViper(String name, int commissionYear, float maximalSpeed, Set<CrewWoman> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear,maximalSpeed, crewMembers);
		this.weapons = weapons;
		this.crewWomen = crewMembers;
		
	}
	
	public List<Weapon> getWeapon(){
		return this.weapons;
	}
	
	public int getFirePower(){
		int res = this.defaultFirePower;
		
		for (Weapon item: weapons) {
			res += item.getFirePower();
		}
		
		return res;
	};
	
	public int getAnnualMaintenanceCost() {
		
		int res = ANNUAL_MAINTNANCE;
		
		for(Weapon item: this.weapons) {
			res += item.getAnnualMaintenanceCost();
		}
		
		for (CrewWoman women :this.crewWomen) {
			res += 500;
		}
		
		res+= Math.round(500*this.maximalSpeed);
		
		return res;
	}

	public String getWeaponsListString() {
		String res = "WeaponArray=[";
		
		for (int i=0; i< weapons.size()-1; i++) {
			Weapon item = weapons.get(i);
			res += item.toString() + ", ";
					}
		
		Weapon lastItem = weapons.get(weapons.size()-1);
		res += lastItem.toString() +"]" ;
		
		return res;
		
	}
	
	public String toString() {
		
		String res = "ColonialViper" + System.lineSeparator();
		res += super.toString();
		res +=  System.lineSeparator();
		res += "	";
		res += getWeaponsListString();

		return res;
	
		}

}

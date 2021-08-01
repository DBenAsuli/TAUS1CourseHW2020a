package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class CylonRaider extends Ship {
	
	private List<Weapon> weapons;
	private final int ANNUAL_MAINTNANCE = 3500;
	private Set<Cylon> crewCylons;
	
	public CylonRaider(String name, int commissionYear, float maximalSpeed, Set<Cylon> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear,maximalSpeed, crewMembers);
		this.weapons = weapons;
		this.crewCylons = crewMembers;
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
		
		for (Cylon Cylon :this.crewCylons) {
			res += 500;
		}
		
		res+= Math.round(1200*this.maximalSpeed);
		
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
		
		String res = "CylonRaider" + System.lineSeparator();
		res += super.toString();
		res +=  System.lineSeparator();
		res += "	";
		res += getWeaponsListString();

		return res;
	
		}

}

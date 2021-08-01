package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends battleShip{
	
	private int technicians;
	private final int ANNUAL_MAINTNANCE = 5000;

	public Bomber (String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons, int numberOfTechnicians) {
		super(name, commissionYear,maximalSpeed, crewMembers, weapons);
		this.technicians = numberOfTechnicians;
	}

	
	public List<Weapon> getWeapon(){
		return this.weapons;
	}
	
	public int getFirePower(){
		return super.getFirePower();
	}
	
	public int getNumberOfTechnicians() {
		return this.technicians;
	}
	
	public int getAnnualMaintenanceCost() {
		int res = this.ANNUAL_MAINTNANCE;
		
		float weaponsCost = 0;
		for(Weapon item: this.weapons) {
			weaponsCost += item.getAnnualMaintenanceCost();
		}
		
		weaponsCost = weaponsCost - ((this.technicians)/10)*weaponsCost;
		res += weaponsCost;
		
		return res;
	}
	
	public String toString() {
		
		String res = "Bomber" + System.lineSeparator();
		res += super.toString();
		res +=  System.lineSeparator();
		res += "	";
		res += super.getWeaponsListString() + System.lineSeparator();
		res += "	";
		res = res + "NumberOfTechnicians=" + this.getNumberOfTechnicians();

		return res;
	
		}


}

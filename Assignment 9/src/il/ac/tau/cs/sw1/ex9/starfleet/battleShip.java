package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public abstract class battleShip extends Ship {
	
	protected List<Weapon> weapons;
	
	public battleShip(String name, int comissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons) {
		super(name, comissionYear,maximalSpeed, crewMembers);
		this.weapons = weapons;
	}
	
	public int getFirePower(){
		int res = this.defaultFirePower;
		
		for (Weapon item: weapons) {
			res += item.getFirePower();
		}
		
		return res;
	};

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((weapons == null) ? 0 : weapons.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		battleShip other = (battleShip) obj;
		if (weapons == null) {
			if (other.weapons != null)
				return false;
		} else if (!weapons.equals(other.weapons))
			return false;
		return true;
	}
}

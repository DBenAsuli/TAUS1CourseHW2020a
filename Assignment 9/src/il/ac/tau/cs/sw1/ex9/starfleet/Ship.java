package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public abstract class Ship implements Spaceship{
	
	protected String name;
	protected int comissionYear;
	protected float maximalSpeed;
	protected int defaultFirePower = 10;
	protected Set<? extends CrewMember> crewMembers;
	protected List<Weapon> weapons;
	
	public Ship(String name, int comissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers) {
		this.name = name;
		this.comissionYear= comissionYear;
		this.maximalSpeed = maximalSpeed;
		this.crewMembers = crewMembers;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getCommissionYear() {
		return this.comissionYear;
	}
	
	public float getMaximalSpeed() {
		return this.maximalSpeed;
	}
	
	public int getFirePower() {
		return this.defaultFirePower;
	}
	
	public Set<? extends CrewMember> getCrewMembers(){
		return this.crewMembers;
	}

	public List<Weapon> getWeapon(){
		return this.weapons;
	}
	
	public String toString() {
		
		String res = "	";
		res += "Name=" + this.getName() + System.lineSeparator();
		res += "	";
		res = res + "CommissionYear=" + this.getCommissionYear() + System.lineSeparator();
		res += "	";
		res = res + "MaximalSpeed=" + this.getMaximalSpeed() + System.lineSeparator();
		res += "	";
		res = res + "FirePower=" + this.getFirePower() + System.lineSeparator();
		res += "	";
		res = res + "CrewMembers=" + this.getCrewMembers().size() + System.lineSeparator();
		res += "	";
		res = res + "AnnualMaintenanceCost=" + this.getAnnualMaintenanceCost();
		
		return res;
		
	}
	
	public abstract int getAnnualMaintenanceCost();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + comissionYear;
		result = prime * result + ((crewMembers == null) ? 0 : crewMembers.hashCode());
		result = prime * result + defaultFirePower;
		result = prime * result + Float.floatToIntBits(maximalSpeed);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ship other = (Ship) obj;
		if (comissionYear != other.comissionYear)
			return false;
		if (crewMembers == null) {
			if (other.crewMembers != null)
				return false;
		} else if (!crewMembers.equals(other.crewMembers))
			return false;
		if (defaultFirePower != other.defaultFirePower)
			return false;
		if (Float.floatToIntBits(maximalSpeed) != Float.floatToIntBits(other.maximalSpeed))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}

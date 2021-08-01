package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public class TransportShip extends Ship{
	
	private int cargo;
	private int passangers;
	private final int ANNUAL_MAINTNANCE = 3000;
	
	public TransportShip(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, int cargoCapacity, int passengerCapacity){
		super(name, commissionYear,maximalSpeed, crewMembers);
		this.cargo = cargoCapacity;
		this.passangers = passengerCapacity;
	
	}
	
	public int getCargoCapacity() {
		return this.cargo;
	}

	public int getPassengerCapacity() {
		return this.passangers;
	}
	
	public int getAnnualMaintenanceCost() {
		
		int res = ANNUAL_MAINTNANCE;
		res += 5* this.cargo;
		res += 3* this.passangers;
		
		return res;
		
	}
	
	public String toString() {
		
		String res = "TransportShip" + System.lineSeparator();
		res += super.toString();
		res +=  System.lineSeparator();
		res += "	";
		res = res + "CargoCapacity=" + this.getCargoCapacity() + System.lineSeparator();
		res += "	";
		res = res + "PassengerCapacity=" + this.getPassengerCapacity();
		
		return res;
	
		}
}

package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

public class StarfleetManager {

	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in descending order by commission year, and finally in ascending order by
	 * name
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear (Collection<Spaceship> fleet) {
		
		List<String> res = new ArrayList<String>();
		List<Ship> shipList = new ArrayList<Ship>();
		for (Spaceship ship: fleet) {
			Ship temp = (Ship) ship;
			shipList.add(temp);
		}
		
		//Only works if it's the specific type
		//Since theres no other implementation to Spaceship it's ok
		Collections.sort(shipList, new ShipComparator());
		
		for (Ship shipp: shipList) {
			res.add(shipp.toString());
		}
		
		return res;
	}

	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {
		Map<String, Integer> res = new HashMap<String, Integer>();
		
		for(Spaceship ship: fleet) {
			Class currClass = ship.getClass();
			String className = currClass.getSimpleName();
			
			if (!res.containsKey(className)) {
				res.put(className, 1);
				
			}
			else {
				int currCount = res.get(className);
				res.put(className, currCount+1);
			}
		}
		return res;

	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost (Collection<Spaceship> fleet) {
		
		int res = 0;
		for(Spaceship ship: fleet) {
			res += ship.getAnnualMaintenanceCost();
			
		}
		return res;

	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
		
		Set<String> res = new HashSet<String>();
		
		for(Spaceship ship: fleet) {
			Ship shipp = (Ship) ship;
			List<Weapon> weaponsLst= shipp.getWeapon();
			
			if (weaponsLst != null) {
				for (Weapon weapon: weaponsLst ) {
					String weaponName = weapon.getName();
					if (!res.contains(weaponName)) {
						res.add(weaponName);
					}
				}
			}
			
		}
		return res;

	}

	/*
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		
		int res = 0;
		for(Spaceship ship: fleet) {
			Ship shipp = (Ship) ship;
			Set<? extends CrewMember> crewMembers = shipp.getCrewMembers();
			res += crewMembers.size();
		}
		
		return res;

	}

	/*
	 * Returns the average age of all officers serving on board of the given fleet's ships. 
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
		
		float res = 0;
		int cnt = 0;
		
		for(Spaceship ship: fleet) {
			Ship shipp = (Ship) ship;
			Set<? extends CrewMember> crewMembers = shipp.getCrewMembers();
			
			for (CrewMember worker : crewMembers) {
				Class currClass = worker.getClass();
				String className = currClass.getSimpleName();
				
				if (className.equals("Officer")) {
					res += worker.getAge();
					cnt += 1;		
				}
			}
		}
		
		res = res / cnt;
		return res;
	}

	/*
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
		
		Map<Officer, Spaceship> res = new HashMap<Officer, Spaceship>();
		for(Spaceship ship: fleet) {
			
				Officer maxOfficer = new Officer(null, 0, 0, null); //Temporary initiation
				int currentMaxRank = 1;
				Ship shipp = (Ship) ship;
				Set<? extends CrewMember> crewMembers = shipp.getCrewMembers();
				
				for (CrewMember worker : crewMembers) {
					Class currClass = worker.getClass();
					String className = currClass.getSimpleName();
					int rankNum= 0;
					
					if (className.equals("Officer")) {
						
						if (maxOfficer.getName() == null) {
							maxOfficer = (Officer) worker;
							currentMaxRank = 1;
						}
						
						OfficerRank rank = ((Officer) worker).getRank();
						if (rank == OfficerRank.Ensign ) {
							rankNum= 1;
						} else if (rank == OfficerRank.Lieutenant) {
							rankNum= 2;
						} else if (rank == OfficerRank.LieutenantCommander) {
							rankNum= 3;
						} else if (rank == OfficerRank.Commander) {
							rankNum = 4;
						} else if (rank == OfficerRank.Captain) {
							rankNum = 5;
						} else if (rank == OfficerRank.Admiral) {
							rankNum = 6;
						}
						
						if (rankNum >= currentMaxRank) {
							//Like >=
							
							maxOfficer = (Officer) worker;
							currentMaxRank = rankNum;
									
					}
				}
			}
				if (maxOfficer.getName() != null)	{
					res.put(maxOfficer, ship);
					maxOfficer = new Officer(null, 0, 0, null);
				}
		}
		return res;

	}

	/*
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted ascendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {
		
		List<Map.Entry<OfficerRank, Integer>> res = new ArrayList<Map.Entry<OfficerRank, Integer>>();
		Map<OfficerRank, Integer> res2 = new HashMap<OfficerRank, Integer>();
		
		for(Spaceship ship: fleet) {
			
			Ship shipp = (Ship) ship;
			Set<? extends CrewMember> crewMembers = shipp.getCrewMembers();
			
			for (CrewMember worker : crewMembers) {
				Class currClass = worker.getClass();
				String className = currClass.getSimpleName();
				
				if (className.equals("Officer")) {
					OfficerRank rank = ((Officer) worker).getRank();
					
					
			if (!res2.containsKey(rank)) {
				res2.put(rank, 1);
				
			}
			else {
				int currCount = res2.get(rank);
				res2.put(rank, currCount+1);
			}
	}
			}
		}
		Set<Map.Entry<OfficerRank, Integer>> entries = res2.entrySet();
		
		for (Map.Entry<OfficerRank, Integer> entry : entries) {
			res.add(entry);
		}
		
		
		Collections.sort(res, new EntriesComparator());
		
		return res;
	}

}

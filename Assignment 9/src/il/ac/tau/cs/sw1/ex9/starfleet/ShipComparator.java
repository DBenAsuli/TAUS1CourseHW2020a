package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Comparator;

public class ShipComparator implements Comparator<Ship> {
		
		
		@Override
		public int compare(Ship ship1, Ship ship2) {
			
				Integer firePower1 = ship1.getFirePower();
				Integer firePower2 = ship2.getFirePower();
				
			if(firePower1 != firePower2) {
				return firePower2.compareTo(firePower1);
				//They are in revresed order because we need descending order
			}
			
			//Moving to the next comparing
			
			Integer commissionYear1 = ship1.getCommissionYear();
			Integer commissionYear2 = ship2.getCommissionYear();
			
			if(commissionYear1 != commissionYear2) {
				return commissionYear2.compareTo(commissionYear1);
				//They are in revresed order because we need descending order
			}
			
			String name1 = ship1.getName();
			String name2 = ship2.getName();
			
			return name1.compareTo(name2);
				
				
	}
}

package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class EntriesComparator implements Comparator<Map.Entry<OfficerRank, Integer>>{

	
	public int compare(Entry<OfficerRank, Integer> entry1, Entry<OfficerRank, Integer> entry2) {
		Integer cnt1 = entry1.getValue();
		Integer cnt2 = entry2.getValue();
		
		return cnt1.compareTo(cnt2);
		
	
	}
}
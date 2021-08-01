package il.ac.tau.cs.sw1.ex9.riddles.forth;

import java.util.Iterator;
import java.util.List;

public class B4 implements Iterator{
	
	String[] resList;
	int runner;
	
	public B4(String[] strings, int k) {
		resList = new String[strings.length*k];
		
		for (int i=0; i< k ; i++) {
			for(int j=0; j<strings.length ; j++) {
				resList[i*strings.length+j] = strings[j];
				
			}
		}
	}
	
	public boolean hasNext() {
		if (runner >= resList.length) {
			return false;
		}
		return true;
	}
	
	public String next() {
		String res = resList[runner];
		runner++;
		return res;
		
	}
}

package il.ac.tau.cs.sw1.hw6;

public class SectionB {
	
	/*
	* @post $ret == true iff exists i such that array[i] == value
	*/
	public static boolean contains(int[] array, int value) { 
		if (array != null) {
			for (int i=0; i<array.length; i++) {
				if (array[i]== value ) {
					return true;
				}
			}
		}

		return false;
	}
	/*
	* @pre array != null
	* @pre array.length > 2
	* @pre Arrays.equals(array, Arrays.sort(array))
	*/
	public static int guess(int[] array) { 
		return 0;
	}
	/*
	* @pre Arrays.equals(array, Arrays.sort(array))
	* @pre array.length >= 1
	* @post for all i array[i] >= $ret
	*/
	public static int min(int[] array) { 
		return array[0];
	}
	
	/*
	* @pre array.length >=1
	* @post for all i array[i] >= $ret
	* @post Arrays.equals(array, prev(array))
	*/
	public static int min2(int[] array) { 
		int res = array[0];
		for (int i=1; i<array.length; i++) {
			if (array[i]<res) {
				res = array[i];
			}
		}
		return res;
	}
	
	/*
	* @pre word.length() >=1
	* @post for all i : $ret.charAt(i) == word.charAt(a.length() - i - 1)

	*/
	public static String reverse(String word) 
	{
		String res = "";
		for (int i=(word.length()-1); i>=0; i-- ) {
			res += word.charAt(i);
		}
		return res;
	}


}

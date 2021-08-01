
public class Assignment02Q01Sec02 {
	public static int WordCalc(String wrd) {
		// A function to calculate the value of each word separately
		int AccumVal = 0;
		for (int i = 0; i < wrd.length(); i++) {
			char Letter = wrd.charAt(i);
			int NumericVal = Letter;
			AccumVal += NumericVal;
		}
		return AccumVal;
		
			
	}
	
	public static void main (String[] args) {
		for (String word : args) {
			int Val = WordCalc(word);		
			System.out.println(Val);	
		}
	}
}


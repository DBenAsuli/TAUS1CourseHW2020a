
public class Assignment02Q01Sec01 {
	public static boolean WordChecker(String wrd) {
		char FirstLetter = wrd.charAt(0);
		int Num = FirstLetter;
		if (Num % 2 == 1) {
			return true;
		}
		else {
			return false;
		}
			
	}
	
	public static void main (String[] args) {
		for (String word : args) {
			boolean Val = WordChecker(word);		
			if (Val == true) {
				System.out.println(word.charAt(0) + "\n");
			}
					
		}
	}
}

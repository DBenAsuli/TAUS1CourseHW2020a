
public class Assignment02Q01Sec03 {
	public static boolean ModCalc(int num) {
		// A function to indicate if the Modulo of each number is 1 separately
		int Mod = num % 4;
		if (Mod == 0 || Mod == 2) {
			return true;
		}
		else{
			return false;
		}
	}
			
	
	public static void main (String[] args) {
		int AccVal = 0;
		for (String word : args) {
			int Num = Integer.parseInt(word);
			boolean Val = ModCalc(Num);		
			if (Val==true) {
				AccVal++;
			}
		}
		System.out.println(AccVal);
	}
}

public class Assignment02Q02 {
	public static double PiApprox(double num) {
		double val = 0;
		for (int i = 0; i < num; i++) {
			double base = -1;
			double Top = Math.pow(base, i);
			double Bottom = 2*i +1;
			double Addition = Top/Bottom;
			val += Addition;
			}
		return val;
	}
	
	public static void main(String[] args) {
		double Num = Double.parseDouble(args[0]);
		double ApproximatedPi = 4 * PiApprox(Num);
		double RealPi = Math.PI;
		System.out.println(ApproximatedPi + " " + RealPi);
		
	}
}

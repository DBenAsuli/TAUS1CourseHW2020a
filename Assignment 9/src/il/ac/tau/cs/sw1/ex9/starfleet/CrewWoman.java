package il.ac.tau.cs.sw1.ex9.starfleet;

public class CrewWoman extends Worker {
	
	public CrewWoman(int age, int yearsInService, String name){
		super(age,yearsInService,name);
	}
	public String getName() {
		return super.getName();
	}
	
	public int getAge() {
		return super.getAge();
	}
	
	public int getYearsInService() {
		return super.getYearsInService();
	}
	
	public String toString() {
		
		String res = "CrewWoman" + System.lineSeparator();
		res += super.toString();
		
		return res;
	
		}
}

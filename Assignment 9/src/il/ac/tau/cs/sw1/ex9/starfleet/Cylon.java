package il.ac.tau.cs.sw1.ex9.starfleet;

public class Cylon extends Worker{

	private int model;
	
	public Cylon(String name, int age, int yearsInService, int modelNumber) {
		super(age,yearsInService,name);
		this.model = modelNumber;
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
	
	public int getModelNumber() {
		return this.model;
	}
	
	public String toString() {
		
		String res = "Cylon" + System.lineSeparator();
		res += super.toString();
		res += "	";
		res = res + "YearsInService=" + this.getYearsInService() + System.lineSeparator();
		res += "	";
		res = res + "ModelNumber=" + this.getModelNumber() + System.lineSeparator();
		
		return res;
	
		}
}

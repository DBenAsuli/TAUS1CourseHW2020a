package il.ac.tau.cs.sw1.ex9.starfleet;

public abstract class Worker implements CrewMember {
	
	protected int age;
	protected int service;
	protected String name;
	
	
	public Worker(int age, int yearsInService, String name) {
		this.age = age;
		this.service = yearsInService;
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public int getYearsInService() {
		return this.service;
	}
	
	public String toString() {
		
		String res = "	";
		res += "Name=" + this.getName() + System.lineSeparator();
		res += "	";
		res = res + "Age=" + this.getAge() + System.lineSeparator();
		res += "	";
		res = res + "YearsInService=" + this.getYearsInService() + System.lineSeparator();
		
		return res;
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + service;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Worker other = (Worker) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (service != other.service)
			return false;
		return true;
	}
	
	
	
}

package il.ac.tau.cs.software1.predicate;

public class ByPrice implements Predicate<SmartPhone>{
	
	private double price;
	
	public ByPrice(double maxPrice) { // Q2
		this.price = maxPrice;
	}

	@Override
	public boolean test(SmartPhone phone) { // Q2
		
		double phonePrice = phone.getPrice();
		if (phonePrice <= price) {
			return true;
		}
			
		return false;
	}
	
	

}

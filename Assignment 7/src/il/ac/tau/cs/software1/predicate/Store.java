package il.ac.tau.cs.software1.predicate;

import java.util.List;

public class Store<T extends Product> {
	private List<T> inventory;
	
	public Store(List <T> inventory) {
		this.inventory = inventory;
	}

	public List<T> getInventory() {
		return inventory;
	}

	public String getInventoryDescription() { // Q4
		String res = "";
		
		for (T product : inventory) {
			res += product.getDescription();
		}
		return res; 
	}

	public void transform(Predicate<T> pred, Action<T> action) { // Q5
		for (T product : inventory) {
			
			boolean indicator = pred.test(product);
			
			if (indicator) {
				action.performAction(product);
			}
			
		}
	}
}

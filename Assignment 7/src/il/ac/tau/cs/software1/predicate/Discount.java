package il.ac.tau.cs.software1.predicate;

public class Discount implements Action<Book> {
	private double discountPercentage;
	
	public Discount(double percentage) { // Q3
		this.discountPercentage = percentage;
	}
	
	
	@Override
	public void performAction(Book book) { // Q3

		double bookPrice = book.getPrice();
		double percentage = ( this.discountPercentage / 100);
		double newPrice = percentage * bookPrice;
		
		book.setPrice(newPrice);
		
	}
	
}

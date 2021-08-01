package il.ac.tau.cs.software1.predicate;

public class ByAuthor implements Predicate<Book> {
	
	private char character;
	
	public ByAuthor(char letter) { // Q2
		this.character = letter;
	}

	@Override
	public boolean test(Book book) { // Q2
		
		String author = book.getAuthor();
		char firstLetterTemp = author.charAt(0);
		char firstLetter = Character.toLowerCase(firstLetterTemp);
		char letter = this.character;
		
		if (letter == firstLetter) {
			return true;
			
		}
		
		return false;
	}
}
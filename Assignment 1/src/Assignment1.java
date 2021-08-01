
public class Assignment1 {
	public static void main (String[] args) {
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		int c = Integer.parseInt(args[2]);
		//* checks if the input is even valid, with all numbers greater than 0
		if (a<=0 || b<=0 || c<=0) {
			System.out.println("Invalid input!") ; }
		else {
			boolean ind = true;
			//* This indicator is if a triangle can be made using these measurements. Only if indicator is true
			//* the program will continue to check if a right triangle is possible
			if ( (a+b<c) || (a+c<b) || (c+b<a)) {
				ind = false ;}
			if (ind == false) {
				System.out.println("The input (" + a + "," + b + "," + c + ") does not define a valid triangle!");
				//* a triangle can't be made at all
			} else { //* a triangle can indeed be made
				//* defining squares:
				int a2 = a*a;
				int b2 = b*b;
				int c2 = c*c;
				if ( (a2 + b2 == c2) || (a2 + c2 == b2) || (b2+c2 == a2)) {
					//* If pithagoras happens
					System.out.println("The input (" + a + "," + b + "," + c + ") defines a valid right triangle!");
					//* Pithagoras did not happen, yet it's still a triangle
				} else {
					System.out.println("The input (" + a + "," + b + "," + c + ") defines a valid triangle!"); }
				
			
				
			}
		}
		} 

}

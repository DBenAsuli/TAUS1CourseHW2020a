package il.ac.tau.cs.sw1.ex9.riddles.first;

public class B1 extends A1 {
	public boolean foo() {
		
		//C's foo demands and object, yet "Secret" is a static field
		//So it'll change regardless of which object modified it,
		//its not tied to any of them
		
		C1 C = new C1();
		boolean res = C.foo();
		
		//Returning "Secret" to it's original value for the next foo() action
		C.foo();
		
		return res;
		
	}
}

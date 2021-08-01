package il.ac.tau.cs.sw1.riddle.b;

/**
 * Complete the code of A's methods without changing B and C.
 */
public class A {

	private B b;

	public A(B b) {
		this.b = b;
	}

	public static void printA(B b) {
		final B b2 = new B(b, "print") ;
	}

	public void printA2() {
		final B b3 = this.b;
		B.foo(b3); 
	}

	public static void printA3(A a) {
		final B b4 = a.b;
		b4.methodB(b4);
	}
	
}

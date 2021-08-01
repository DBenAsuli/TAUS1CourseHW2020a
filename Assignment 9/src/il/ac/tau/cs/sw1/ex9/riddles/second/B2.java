package il.ac.tau.cs.sw1.ex9.riddles.second;

public class B2 extends A2 {
	
	public A2 getA(boolean Bool) {
		A2 res;
		
		if(Bool) {
			res = new B2();
		}
		else {
			res = new A2();
		}
		
		return res;
	}
	
	public String foo(String s) {
		return s.toUpperCase();
	}
}

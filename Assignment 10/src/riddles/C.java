package riddles;

public class C extends B {
	
	private int i;
	private int j;

	public C(int i, int j) {
		super(i,j);
		
	}

	@Override
	public int compareTo(A other) {
		if (this.j == other.j && this.i == other.i) {
			return 0;
		}
		if (this.i > other.i) {
			return 1;
		}
		else {
			return -1;
		}
	}



}
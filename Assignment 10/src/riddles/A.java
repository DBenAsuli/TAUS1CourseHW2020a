package riddles;


public class A implements Comparable<A> {
	
	protected int i;
	protected int j;

	public A(int i, int j) {
		this.i = i;
		this.j = j;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + j;
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
		A other = (A) obj;
		if (this.j != other.j) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(A o) {
		if (this.j == o.j) {
			return 0;
		}
		if (this.i > o.i) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	
	public String toString() {return "("+this.i+" "+this.j+")";}



}

package il.ac.tau.cs.sw1.hw6;


public class Polynomial {
	
	private double[] coeffs;
	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 */
	public Polynomial()
	{
		double[] coeff = new double[1];
		coeff[0] = 0;
		this.coeffs = coeff;
	} 
	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) 
	{
		coeffs = coefficients;
	}
	/*
	 * Addes this polynomial to the given one
	 *  and retruns the sum as a new polynomial.
	 */
	public Polynomial adds(Polynomial polynomial)
	{	
		
		double[] resCoeffs;
		double[] firstPolCoeffs = this.coeffs;
		double[] secondPolCoeffs = polynomial.coeffs;
		
		int firstPolDeg = firstPolCoeffs.length;
		int secondPolDeg = secondPolCoeffs.length;
		
		
		if (firstPolDeg >= secondPolDeg) {
			
			resCoeffs = new double[firstPolDeg];
			
			for (int i=0; i<firstPolDeg; i++) {
				double addition = 0;
				addition += firstPolCoeffs[i];
				if (i<secondPolDeg) {
					addition += secondPolCoeffs[i];
				}
				
				resCoeffs[i] = addition;
			}
			
		} else {
			resCoeffs = new double[secondPolDeg];
			
			for (int i=0; i<secondPolDeg; i++) {
				double addition = 0;
				addition += secondPolCoeffs[i];
				if (i<firstPolDeg) {
					addition += firstPolCoeffs[i];
				}	
				resCoeffs[i] = addition;
			}
		}
		
		Polynomial res = new Polynomial(resCoeffs);
		
		return res;
		
	}
	/*
	 * Multiplies a to this polynomial and returns 
	 * the result as a new polynomial.
	 */
	public Polynomial multiply(double a)
	{
		double[] coeffs = this.coeffs;
		int degree = coeffs.length;
		
		double[] resCoeffs = new double[degree];
		
		for (int i=0; i<degree; i++) {
			double addition = coeffs[i];
			addition = addition * a;
			
			resCoeffs[i]= addition; 
		}
		
		Polynomial res = new Polynomial(resCoeffs);
		
		return res;
		
	}
	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree()
	{			
		double[] coeffs = this.coeffs;
		int degree = coeffs.length -1;
		
		return degree;
	}
	/*
	 * Returns the coefficient of the variable x 
	 * with degree n in this polynomial.
	 */
	public double getCoefficient(int n)
	{	
		double[] coeffs = this.coeffs;
		if (n < coeffs.length) {
			return coeffs[n];
		}
		return 0.0;
	}

	/*
	 * set the coefficient of the variable x 
	 * with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that that the coefficient of the variable x 
	 * with degree n was 0, and now it will change to c. 
	 */
	public void setCoefficient(int n, double c)
	{	
		double[] currentCoeffs = this.coeffs;
		
		if (this.getDegree() < n) {
			double[] newCoeffs = new double[n+1];
			for (int i=0; i< currentCoeffs.length; i++ ) {
				newCoeffs[i]= currentCoeffs[i]; 
			}
			newCoeffs[n] = c;
			
			this.coeffs = newCoeffs;
			
		}else {
			coeffs[n] = c;
		}
		

		
	}
	
	/*
	 * Returns the first derivation of this polynomial.
	 *  The first derivation of a polynomal a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	
	 */
	public Polynomial getFirstDerivation()
	{	double[] currentCoeffs = this.coeffs;
		double[] newCoeffs = new double[(currentCoeffs.length)-1];
		
		for (int i=0; i< newCoeffs.length; i++) {
			newCoeffs[i]=  currentCoeffs[i+1]*(i+1);
		}
		
		Polynomial res = new Polynomial(newCoeffs);
		
		return res;
	}
	
	/*
	 * given an assignment for the variable x,
	 * compute the polynomial value
	 */
	public double computePolynomial(double x)
	{	double res = 0.0;
		double currentX = 1;
		
		double[] coeffs = this.coeffs;
		
		for (int i=0; i < coeffs.length; i++ ) {
			res += currentX*coeffs[i];
			currentX = currentX * x;
		}
		return res;
	}
	
	/*
	 * given an assignment for the variable x,
	 * return true iff x is a root of this polynomial
	 */
	public boolean isARoot(double x)
	{	
		double res = this.computePolynomial(x);
		
		if (res == 0.0) {
			return true;
		}
		return false;
	}
	
	
	
	

    
    

}

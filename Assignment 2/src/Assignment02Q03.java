
public class Assignment02Q03 {
	public static int[] GenerateList (int num) {
		int[] lst;
		lst = new int[num];
				
		if (num==0) {
			lst[0] = 1;
			return lst;
		}else if (num==1) {
			lst[0]=1;
			lst[1]=1;
			return lst;
		}
		
		lst[0]=1;
		lst[1]=1;
		lst[2]=2;
		
		int prev = 1;
		int beforePrev = 1;
		int current = 2;
		
		for (int i=3 ; i<num ; i++) {
			beforePrev = prev;
			prev = current;
			current = prev + beforePrev;
			lst[i]=current;
		}
		
		return lst;
	}

	public static String GenerateString(String[] args) {
		int num = Integer.parseInt(args[0]);
		int[] res = GenerateList(num);
		
		String result = "";
		for (int i=0; i< res.length ; i++ ) {
			result += res[i];
			
			if (i != (res.length -1)) {
				result += " ";
			}
				
		}
		
		return result;	
	}
	
	public static double CalculateAvrage(int[] args) {
		double tempRes = 0;
		for (int i=0; i< args.length ; i++ ) {
			double num = args[i];
			tempRes = tempRes + num;
			}
		double Res = (tempRes / args.length) ;
		
		return Res;
	}
	
	public static void main (String[] args) {
		int num = Integer.parseInt(args[0]);
		
		int[] TempList = GenerateList(num);
		String FirstLine = "The first " + num + " Fibonacci numbers are:";
		String SecondLine = GenerateString(args);
		String ThirdLine = "The average is: " + CalculateAvrage(TempList);
		
		System.out.println(FirstLine);
		System.out.println(SecondLine);
		System.out.println(ThirdLine);
		

	}
		
	
}

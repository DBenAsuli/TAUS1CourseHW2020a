package il.ac.tau.cs.software1.date;

public class DateInt implements Date {
	
	private int totalDaysCnt;
	
	public DateInt(int date) {
		this.totalDaysCnt = date;
	}

	@Override
	public String toString() {
		int[] arr = this.getArray();
		String dayStr = Integer.toString(arr[0]);
		String monthStr = Integer.toString(arr[1]);
		String yearStr = Integer.toString(arr[2]);
		String[] strArr = {dayStr, monthStr, yearStr};
		return String.join("/", strArr);
		
	}
	
	private int[] getArray() {
		
		int[] res = new int[3];
		int day = 1;
		int month = 1;
		int year = 1;
		int remaining = this.totalDaysCnt;
			

		if (remaining <= getDaysInMonth(month)) {
			
			day = remaining+1;
			
		} else {
			
			remaining -=  getDaysInMonth(month);
			//We move to the next month
			
			if (month == 12) {
				month = 1;
				year += 1;
				
			} else {
				
				month += 1;
			}
			
			while (remaining > 0) {
				
				if (remaining < getDaysInMonth(month)) {
					
					day = remaining + 1; //the initial date is the first of the month
					remaining = 0;
					//We exist the loop
					
				} else { //remaining > getDaysInMonth(currentMonth)
					day = 1;
					remaining -= getDaysInMonth(month);
					
					if (month == 12) {
						month = 1;
						year += 1;
						
					} else {
						
						month += 1;
					}
					
				}
			}
		}
			
			
			res[0] = day;
			res[1] = month;
			res[2] = year;

			return res;
				
		}
		
	public static int getDaysInMonth(int month) {
		
		if (month == 2) {
			return 28;
		}
		else if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || 
				(month == 8) || (month == 10) || (month == 12) ) {
			return 31;
			
		} else{
			return 30;
		}
	}

	
	@Override
	public int getDay() {
		int[] arr = this.getArray();
		return arr[0];
	}

	@Override
	public int getMonth() {
		int[] arr = this.getArray();
		return arr[1];
	}

	@Override
	public int getYear() {
		int[] arr = this.getArray();
		return arr[2];
	}

	@Override
	public void addDays(int days) {
		int temp = this.totalDaysCnt + days;
		
		if (temp >0) {
			this.totalDaysCnt = temp;
		} else {
			this.totalDaysCnt = 0;
		}
		
	}

}

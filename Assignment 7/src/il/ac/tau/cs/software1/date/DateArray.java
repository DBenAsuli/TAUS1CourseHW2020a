package il.ac.tau.cs.software1.date;

public class DateArray implements Date {

	private int[] array;
	
	public DateArray(int[] date) {
		this.array = date;
	}

	@Override
	public String toString() {
		
		String dayStr = Integer.toString(this.array[2]);
		String monthStr = Integer.toString(this.array[1]);
		String yearStr = Integer.toString(this.array[0]);
		String[] strArr = {dayStr, monthStr, yearStr};
		return String.join("/", strArr);
		
	}

	@Override
	public int getDay() {
		return this.array[2];
	}

	@Override
	public int getMonth() {
		return this.array[1];
	}

	@Override
	public int getYear() {
		return this.array[0];
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
	public void addDays(int days) {
		
		int day = this.array[2];
		int month = this.array[1]; 
		int year = this.array[0];
		
		//for later
		int currentYear = year;
		int currentMonth = month;
		int currentDay = day;
		
		if (days > 0) {
			
			if (day + days <= getDaysInMonth(month)) {
				this.array[2] = day+days;
				
			} else {
				int remaining = days - (getDaysInMonth(month) - day);
				//We move to the next month
				
				if (currentMonth == 12) {
					currentMonth = 1;
					currentYear += 1;
					
				} else {
					
					currentMonth += 1;
					currentYear = currentYear;
				}
				
				while (remaining > 0) {
					
					if (remaining < getDaysInMonth(currentMonth)) {
						
						currentDay = remaining;
						remaining = 0;
						//We exist the loop
						
					} else { //remaining > getDaysInMonth(currentMonth)
						currentDay = 1;
						remaining = remaining  - getDaysInMonth(currentMonth);
						
						if (currentMonth == 12) {
							currentMonth = 1;
							currentYear += 1;
							
						} else {
							
							currentMonth += 1;
							currentYear = currentYear;
						}
						
					}
				}
				this.array[2] = currentDay;
				this.array[1] = currentMonth;
				this.array[0] = currentYear;
				
			}
		} else if (days < 0) {
			
			days = -1 * days; //Absolute value
			//We need to go backwards
			
			if (day - days > 0) {
				this.array[2] = day-days;
			
		} else {
			
			int remaining = days - day;
			
			if (currentMonth == 1) {
				currentMonth = 12;
				currentYear -= 1;
				
			} else {
				
				currentMonth -= 1;
				currentYear = currentYear;
			}
			
			while (remaining > 0) {
				
				if (remaining < getDaysInMonth(currentMonth)) {
					
					currentDay = getDaysInMonth(currentMonth) - remaining;
					remaining = 0;
					//We exist the loop
					
				} else { //remaining > getDaysInMonth(currentMonth)
					currentDay = getDaysInMonth(currentMonth);
					remaining = remaining  - getDaysInMonth(currentMonth);
					
					if (currentMonth == 1) {
						currentMonth = 12;
						currentYear -= 1;
						
					} else {
						
						currentMonth -= -1;
						currentYear = currentYear;
					}
					
				}
				
				this.array[2] = currentDay;
				this.array[1] = currentMonth;
				this.array[0] = currentYear;
		}
		
		}
		}
			

		//If days == 0 nothing happens
	}
	
}

package il.ac.tau.cs.software1.date;

public class DateString implements Date {
	
	private String dateString;
	
	public DateString(String date) {
		this.dateString = date;
	}
	
	
	
	@Override
	public String toString() {
		return this.dateString;
	}

	@Override
	public int getDay() {
		
		return Integer.parseInt(dateString.split("/")[0]);
	}

	@Override
	public int getMonth() {
		
		return Integer.parseInt(dateString.split("/")[1]);
	}

	@Override
	public int getYear() {
		
		return Integer.parseInt(dateString.split("/")[2]);
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
		String[] arr = dateString.split("/");
		int day = Integer.parseInt(arr[0]);
		int month = Integer.parseInt(arr[1]); 
		int year = Integer.parseInt(arr[2]);
		
		//for later
		int currentYear = year;
		int currentMonth = month;
		int currentDay = day;
		
		if (days > 0) {
			
			if (day + days <= getDaysInMonth(month)) {
				arr[0] = Integer.toString(day+days);
				this.dateString = String.join("/", arr);
				
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
				arr[0] = Integer.toString(currentDay);
				arr[1] = Integer.toString(currentMonth);
				arr[2] = Integer.toString(currentYear);
				this.dateString = String.join("/", arr);
				
			}
		} else if (days < 0) {
			
			days = -1 * days; //Absolute value
			//We need to go backwards
			
			if (day - days > 0) {
				arr[0] = Integer.toString(day-days);
				this.dateString = String.join("/", arr);
			
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
						
						currentMonth -= 1;
						currentYear = currentYear;
					}
					
				}
				
				arr[0] = Integer.toString(currentDay);
				arr[1] = Integer.toString(currentMonth);
				arr[2] = Integer.toString(currentYear);
				this.dateString = String.join("/", arr);
		}
		
		}
			
		}
			

		//If days == 0 nothing happens
		

	}
	

}

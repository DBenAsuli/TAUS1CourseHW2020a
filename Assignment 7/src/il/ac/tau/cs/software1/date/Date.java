package il.ac.tau.cs.software1.date;

public interface Date {

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

	public String toString();

	public int getDay();

	public int getMonth();

	public int getYear();

	public void addDays(int days);

	public default int differenceInDays(Date other) {
		
		int earlyYear = this.getYear();
		int earlyMonth = this.getMonth();
		int earlyDay = this.getDay();
		
		int laterYear = other.getYear();
		int laterMonth = other.getMonth();
		int laterDay = other.getDay();
		
		if (earlyYear == laterYear && earlyMonth == laterMonth) {
			//The two dates are in the same month of the same year
			return laterDay - earlyDay;
			
		} else if (earlyYear == laterYear) {
			//The two dates are in the same year but in different months
			if (earlyMonth < laterMonth) {
				
				//First we'll count the days until the current month ends
				int currentMonthLimit = getDaysInMonth(earlyMonth);
				int daysUntilEndOfMonth = currentMonthLimit- earlyDay;
				
				//Now we need to add up all the days in the months between
				int monthsDifference = 0;
				for (int i= earlyMonth+1 ; i< laterMonth ; i++) {
					monthsDifference += getDaysInMonth(earlyMonth);
				}
				
				//And now the remaining days until the finish date
				return daysUntilEndOfMonth + monthsDifference + laterDay;
				
			} else  { //if (earlyMonth > laterMonth)
				//The date labeled as "later" is actually earlier
				
				//First we'll count the days until the current month ends
				int currentMonthLimit = getDaysInMonth(laterMonth);
				int daysUntilEndOfMonth = currentMonthLimit- laterDay;
				
				//Now we need to add up all the days in the months between
				int monthsDifference = 0;
				for (int i= laterMonth+1 ; i< earlyMonth ; i++) {
					monthsDifference += getDaysInMonth(laterMonth);
				}
				
				//And now the remaining days until the finish date
				return (daysUntilEndOfMonth + monthsDifference + earlyDay) * -1 ;
				
			}
		} else {
			//The two dates are in completely different years
			
			if (earlyYear< laterYear) {
				
				//First we'll count the days until the current month ends
				int currentMonthLimit = getDaysInMonth(earlyMonth);
				int daysUntilEndOfMonth = currentMonthLimit- earlyDay;
				
				//Now we'll count the days in the remainder of the year
				int untilEndOfYear = 0;
				for (int i= earlyMonth+1 ; i<= 12; i++) {
					untilEndOfYear += getDaysInMonth(i);
				}
				
				//Now we need to count the year in between
				int yearsDifference = 0;
				for (int i= earlyYear+1 ; i< laterYear ; i++) {
					yearsDifference += 365;
				}
				
				//Now we need to count the days in the months until
				//the target date
				
				int untilTargetMonth = 0;
				for( int i= 1; i < laterMonth; i++) {
					untilTargetMonth += getDaysInMonth(i);
				}
				
				//And now add the remaining days in the month
				
				return daysUntilEndOfMonth + untilEndOfYear + yearsDifference + untilTargetMonth + laterDay;
				
			} else  { ///if (laterYear < earlyYear)
				
				//The date labeled as "later" is actually earlier
				//First we'll count the days until the current month ends
				int currentMonthLimit = getDaysInMonth(laterMonth);
				int daysUntilEndOfMonth = currentMonthLimit- laterDay;
				
				//Now we'll count the days in the remainder of the year
				int untilEndOfYear = 0;
				for (int i= laterMonth+1 ; i<= 12; i++) {
					untilEndOfYear += getDaysInMonth(i);
				}
				
				//Now we need to count the year in between
				int yearsDifference = 0;
				for (int i= laterYear+1 ; i< earlyYear ; i++) {
					yearsDifference += 365;
				}
				
				//Now we need to count the days in the months until
				//the target date
				
				int untilTargetMonth = 0;
				for( int i= 1; i < earlyMonth; i++) {
					untilTargetMonth += getDaysInMonth(i);
				}
				
				//And now add the remaining days in the month
				
				return (daysUntilEndOfMonth + untilEndOfYear + yearsDifference + untilTargetMonth + earlyDay) *-1 ;
			}
		}
		
	}

	public default boolean isBetweenDates(Date date1, Date date2) {
		int betweenDate1 = date1.differenceInDays(this);
		int betweenDate2 = date2.differenceInDays(this);
		
		if (date1.differenceInDays(this)== 0 || date2.differenceInDays(this) == 0) {
			//If the current date is identical to one of the borders
			return true;
		}
		else if ((date1.differenceInDays(this) >0 && date2.differenceInDays(this)<0 )
				|| (date1.differenceInDays(this) <0 && date2.differenceInDays(this)>0 ) ) {
			//If the current date is after date 1 and before date 2
			//or before date2 and before date 1
			
			return true;
		}
		
		return false;
	}
}

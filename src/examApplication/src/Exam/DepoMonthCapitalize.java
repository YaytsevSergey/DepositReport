package examApplication.src.Exam;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DepoMonthCapitalize extends DepoBase {
	
	public DepoMonthCapitalize(String bankName, String depositId, Date startDate, int durationInDays, double sum, double interestRate){
		super(bankName, depositId, startDate, durationInDays, sum, interestRate);
	}
	
	public DepoMonthCapitalize(){}
	
	@Override
	public double getInterest(){
		int dayN = 0;
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH) + 1;
		int daysInYear = getDaysInYear();
		double interest = 0.0;
		double capital = 0.0;
		int periodDay = 0;
		double dayCf = 0.0;
		while (true){
			calendar.set(Calendar.MONTH, month+1);
			month++;
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.DATE, -1);
			periodDay = calendar.get(Calendar.DAY_OF_MONTH) - day + 1;
			if ((dayN + periodDay) > durationInDays) {
				break;
			}
			dayCf = periodDay;
			interest = (sum + capital) * (interestRate / 100.0) * (dayCf / daysInYear);
			capital += interest;
            day = 1;
            dayN += periodDay;
		}
		dayCf = durationInDays - dayN;
		interest = (sum + capital) * (interestRate / 100.0) * (dayCf / daysInYear);
		capital += interest;
		return capital;
	}
	
}

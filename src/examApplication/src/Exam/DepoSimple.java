package examApplication.src.Exam;

import java.util.Date;

public class DepoSimple extends DepoBase {
	
	public DepoSimple(String bankName, String depositId, Date startDate, int durationInDays, double sum, double interestRate){
		super(bankName, depositId, startDate, durationInDays, sum, interestRate);
	}
	
	public DepoSimple(){}
	
	@Override
	public double getInterest(){
		int daysInYear = getDaysInYear();
		double dayCf = durationInDays;
		interest = sum * (interestRate / 100.0) * (dayCf / daysInYear);
		return interest;
	}
}
package examApplication.src.Exam;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class DepoBase implements Comparable<DepoBase> {
	
	protected String bankName;
	protected String depositId;
	protected Date startDate;
	protected int durationInDays;
	protected double sum;
	protected double interestRate;
	protected double interest;	

	public DepoBase() {
		this.interest = -1.0;
	}
	
	public DepoBase(String bankName, String depositId, Date startDate, int durationInDays, double sum, double interestRate){
		this.bankName = bankName;
		this.depositId = depositId;
		this.startDate = startDate;
		this.durationInDays = durationInDays;
		this.sum = sum;
		this.interestRate = interestRate;
		this.interest = -1.0;
	}

	public String getBankName() {
		return bankName;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getDepositId() {
		return depositId;
	}

	public void setDepositId(String depositId) {
		this.depositId = depositId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getDurationInDays() {
		return durationInDays;
	}

	public void setDurationInDays(int durationInDays) {
		this.durationInDays = durationInDays;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public abstract double getInterest();
	
	protected int getDaysInYear(){ 
		   GregorianCalendar calendar = new GregorianCalendar();
		   calendar.setTime(startDate); 
		   GregorianCalendar yearEnd = new GregorianCalendar(calendar.get(Calendar.YEAR), Calendar.DECEMBER, 31);
		   int dayInYear = yearEnd.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
		   calendar.add(GregorianCalendar.YEAR, 1); 
		   dayInYear += calendar.get(Calendar.DAY_OF_YEAR);
		   return dayInYear; 
	} 
	
	public int compareTo(DepoBase depo) {
        if ((this.getInterest() - depo.getInterest()) > 0){
        	return 1;
        }
        if ((this.getInterest() - depo.getInterest()) < 0){
        	return -1;
        }
        return 0;
	}
	
	@Override
	public String toString() {	
		Calendar calendar = Calendar.getInstance(); ;
		calendar.setTime(startDate);
		calendar.add(Calendar.DATE, durationInDays);
		SimpleDateFormat dtFrm = new SimpleDateFormat("dd.MM.yyyy");
			
		String resultingString = "Bank name " + bankName;
		resultingString += "\tDeposit Id " + depositId;
		resultingString += "\tMaturity date " + dtFrm.format(calendar.getTime());;
		resultingString += "\tDeposit sum " + sum;
		resultingString += "\tCalculated intersts " + Math.rint(100.0*this.getInterest())/100.0;	
/*		
		String resultingString = "\t" + bankName + "\t" + depositId + "\t" + dtFrm.format(calendar.getTime()) + "\t" + sum + "\t" +
				Math.rint(100.0*this.getInterest())/100.0;
*/	
		return resultingString;
	}

}

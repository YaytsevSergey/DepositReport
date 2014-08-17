package examApplication.src.Exam;

public class Main {

	public static void main(String[] args){
		
		ListDepo depoList = new ListDepo();
		
		depoList.getDeposits(1000.00);
		depoList.sortList();
		depoList.printReport("DepositReport.txt");
	}
}

package examApplication.src.Exam;

import java.io.*;
import java.sql.*;
import java.util.*;

public class ListDepo {

	public ArrayList<DepoBase> list = new ArrayList<DepoBase>();
	
	public ListDepo(){ }
	
	public ArrayList<DepoBase> getList() {
		return list;
	}
	public void setList(ArrayList<DepoBase> list) {
		this.list = list;
	}

	public void getDeposits(double depositSumMoreThan) {
		try{
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT depositType, bankName, depositId, startDate, durationInDays, depositSum, interestRate"
				+ " FROM deposit where depositSum > " 
				+ depositSumMoreThan);
		
		while (rs.next()){
			DepoBase tmp = null;
			
			if (rs.getInt("depositType") == 1)
			{
				tmp = new DepoSimple();
			}
			if (rs.getInt("depositType") == 2)
			{
				tmp = new DepoMonthCapitalize();
			}
			
			tmp.setBankName(rs.getString("bankName"));
			tmp.setDepositId(rs.getString("depositId"));
			tmp.setStartDate(rs.getDate("startDate"));
			tmp.setDurationInDays(rs.getInt("durationInDays"));
			tmp.setSum(rs.getDouble("depositSum"));
			tmp.setInterestRate(rs.getDouble("interestRate"));
		
			list.add(tmp);
		}
		conn.close();
		
		}
		catch(SQLException ex){
			System.out.println("Error " + ex.getMessage());
		}
		catch(Exception ex){
			System.out.println("Error " + ex.getMessage());
		}
	}

	public void sortList(){
		Collections.sort(list, new DepoComparator());
	}
	
	public void printReport(String fileName){	
		try{
			PrintStream outF = new PrintStream(fileName);
			/*
					outF.format("#" + "\tBank name" + "\tDeposit Id" + "\tMaturity dat" + "\tDeposit sum" + "\tCalculated intersts" + "\n");
			*/		
			for (int i = 0; i != list.size(); ++i){
				outF.format(i+1 + " " + list.get(i).toString());
				outF.println(" ");
			}
			outF.close(); 	
		}
		catch(FileNotFoundException e){
		}
	}

	public static Connection getConnection(){
		Connection conn = null;
		Properties props = new Properties();
		
		try{
			InputStreamReader in = new InputStreamReader(new FileInputStream("Properties.txt"), "UTF-8");
			props.load(in); 
			in.close();  
			
			String connString = props.getProperty("DBConnectionString");
	    
	    	conn = DriverManager.getConnection(connString);
	    }
	    catch(SQLException e){
			System.out.println("Error " + e.getMessage());
		}	
	    catch(IOException ie){
			System.out.println("Error " + ie.getMessage());
		};
	    return conn;
	}
}

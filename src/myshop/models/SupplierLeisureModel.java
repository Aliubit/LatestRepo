package myshop.models;

import java.sql.Date;

public class SupplierLeisureModel {

	public Date originalDate;
	String stringDate;
	String particular;
	String debit;
	String credit;
	String balance;
	
	public SupplierLeisureModel(String date, String particular, String debit, String credit, String balance) {
		super();
		this.stringDate = date;
		//this.originalDate = originalDate;
		this.particular = particular;
		this.debit = debit;
		this.credit = credit;
		this.balance = balance;
	}
	
	public String getStringDate() {
		return stringDate;
	}
	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}
	public String getParticular() {
		return particular;
	}
	public void setParticular(String particular) {
		this.particular = particular;
	}
	public String getDebit() {
		return debit;
	}
	public void setDebit(String debit) {
		this.debit = debit;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	
	
	
}

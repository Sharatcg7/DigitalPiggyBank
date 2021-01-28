package model;

/**
 * @author Sharat G
 *
 */

public class Account {
	// volatile - thread safe
	private volatile double  accBal = 0; 
	
	public double getbalance() {
		return this.accBal;
	}
	
	// deposit
	public void credit(double amount) {
		accBal = getbalance() + amount;
	}
	
	// withdraw
	public void withdraw(double amount) {
		accBal = getbalance() - amount;	
	}	
	
}

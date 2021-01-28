package transaction;

/**
 * @author Sharat G
 *
 */

import model.Account;

public class AccountCredit implements Runnable {
	private Account account ;
	int min = 1; int max = 9;
	private double amount;
	
	public AccountCredit(Account account) {
		this.account = account;
	}
	
	public void run() {
		while(true) {
			// Random amount money in multiples of 100s
			amount = Math.round(Math.random() * (max - min + 1) + min ) * 100;
			 
			amountCredit(amount);
		}
	}
		
	private synchronized void amountCredit(double amount) {
		account.credit(amount);
		System.out.println( Thread.currentThread().getName() +" has just deposited:" + amount);
		System.out.println("Current account balance: " + account.getbalance() + "\n");
		// DEPOSIT thread executes every 3 sec
		try {
			Thread.sleep(3000);
		} catch (Exception e) {System.err.println(e.getMessage()); }
			
	}
}
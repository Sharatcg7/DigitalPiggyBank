package transaction;

/**
 * @author Sharat G
 *
 */

import model.Account;

public class AccountWithdraw implements Runnable {
	private Account account;
	int min = 1; int max = 19;
	private double amount;
	
	public AccountWithdraw(Account account) {
		this.account = account;
	}

	public void run() { 
		while(true) {
			// Random amount money in multiples of 10s 
			amount = Math.round(Math.random() * (max - min + 1) + min ) * 10;
			
			amountWithdrawal(amount); 
		}
	}
	
	private synchronized void amountWithdrawal(double amount) {
		if (account.getbalance() >= amount) {
			account.withdraw(amount);
			System.out.println(Thread.currentThread().getName() + " has just withdrawn:" + amount);
			System.out.println("Current account balance:" + account.getbalance() + "\n");
			try { Thread.sleep(300); } 
			catch (Exception e) { System.err.println(e.getMessage()); }
		}else {
			System.out.println(Thread.currentThread().getName() + " request to withdraw:" + amount + " failed - insufficient balance in the account");
			System.out.println("Current account balance:" + account.getbalance() + "\n");
			try { Thread.sleep(300); } 
			catch (Exception e) { System.err.println(e.getMessage()); }
		}
	}
	
}

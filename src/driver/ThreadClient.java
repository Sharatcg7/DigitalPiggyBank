package driver;

/**
 * @author Sharat G
 *
 */

import model.Account;
import transaction.AccountWithdraw;
import transaction.AccountCredit;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ThreadClient {
	public static void main(String[] args) {
		System.out.println("Digital Piggy Bank \n");
		Account account = new Account();
		AccountWithdraw accountwithdraw = new AccountWithdraw(account);
		AccountCredit accountcredit = new AccountCredit(account);

		// Application exit after 15 sec 
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				    System.exit(0);
				  }
		}, 15000);
		
		
		// Credit thread
		Thread t1 = new Thread(accountcredit) ;
		t1.setName("DEPOSIT");
		t1.start();
	          
		// Create a scanner object
		Scanner scannerObj = new Scanner(System.in);  
	    System.out.println("Enter max. number of withdrawers N: ");
	    int N = scannerObj.nextInt();  
	    scannerObj.close();
	    
		
		// Withdraw thread 
		Thread[] threads = new Thread[N];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(accountwithdraw);
			threads[i].setName("WITHDRAW_" + String.valueOf(i));
			threads[i].start();
		}
		
		
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
		}
		 
	}
}
	

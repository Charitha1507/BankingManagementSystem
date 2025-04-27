package com.model; 
public class CurrentAccount extends Account {
	private static final double OVER_DRAFT_LIMIT=-5000.00;
	public CurrentAccount(String account_number, Customer customer, double balance) {
		super(account_number, customer, balance);
	}
	@Override
	public void withdraw(double amount) {
         if(getBalance()-amount>=OVER_DRAFT_LIMIT) {
        	 System.out.println("Withdraw success.....");
        	 setBalance(getBalance()-amount);
         }
         else {
        	 System.out.println(" You exceeded over draft! Transaction declined....");
         }
	}

}

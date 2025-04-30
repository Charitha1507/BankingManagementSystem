package com.model;

public class SavingsAccount extends Account {
	private static final double MINIMUM_BALANCE = 500.00;

	public SavingsAccount(String account_number, Customer customer, double balance, String pin) {
		super(account_number, customer, balance, pin);
	}

	@Override
	public void withdraw(double amount) {
		if (getBalance() - amount >= MINIMUM_BALANCE) {
			System.out.println("Withdraw success.....");
			setBalance(getBalance() - amount);
		} else {
			System.out.println("Transaction declined....");
		}
	}

}

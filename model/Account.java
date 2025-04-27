package com.model;
public abstract class Account {
   private String account_number;
   private Customer customer;
   private double balance;
public Account(String account_number, Customer customer, double balance) {
	super();
	this.account_number = account_number;
	this.customer = customer;
	this.balance = balance;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}
  public Customer getCustomer() {
	return customer;
}
@Override
public String toString() {
	return "Account [account_number=" + account_number + ", customer=" + customer + ", balance=" + balance + "]";
}
public void deposit(double amount) {
	  balance=balance+amount;
  }
  public abstract void withdraw(double amount);
  
}

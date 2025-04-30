package com.controller;

import com.model.*;
import java.util.*;

public class BankingService {
	LinkedHashMap<String, Account> accounts = new LinkedHashMap<>();
	LinkedHashMap<String, Customer> customers = new LinkedHashMap<>();
	LinkedHashMap<String, Loan> loans = new LinkedHashMap<>();

	public void createCustomer(String cid, String cname) {
		if (customers.containsKey(cid) == false) {
			Customer customer = new Customer(cid, cname);
			customers.put(cid, customer);
			System.out.println("Customer created successfully..");
		} else {
			System.out.println("Customer ID already exists..");
		}
	}

	public void createAccount(String acc_type, String acc_number, String cus_id, double balance, String pin) {
		if (accounts.containsKey(acc_number) == false) {
			if (customers.containsKey(cus_id) == true) {
				if (acc_type.equals("Savings")) {
					Customer customer = customers.get(cus_id);
					Account account = new SavingsAccount(acc_number, customer, balance, pin);
					accounts.put(acc_number, account);
					System.out.println("Account created successfully...");
				} else if (acc_type.equals("Current")) {
					Customer customer = customers.get(cus_id);
					Account account = new CurrentAccount(acc_number, customer, balance, pin);
					accounts.put(acc_number, account);
					System.out.println("Account created successfully...");
				} else {
					System.out.println("Invalid Account type..");
				}
			} else {
				System.out.println("Invalid customer id..");
			}
		} else {
			System.out.println("Account Number already exists..");
		}

	}

	public void withdraw(String withdraw_accno, double withdraw_amt) {
		if (accounts.containsKey(withdraw_accno) == true) {
			Account account = accounts.get(withdraw_accno);
			account.withdraw(withdraw_amt);
		} else {
			System.out.println("Invalid Account number..");
		}
	}

	public void deposit(String deposit_accno, double deposit_amt) {
		if (accounts.containsKey(deposit_accno) == true) {
			Account account = accounts.get(deposit_accno);
			account.deposit(deposit_amt);
			System.out.println("Amount deposit  success..");
		} else {
			System.out.println("Invalid Account number..");
		}
	}

	public void checkBalance(String view_accno) {
		if (accounts.containsKey(view_accno) == true) {
			Account account = accounts.get(view_accno);
			System.out.println("Your Account Balance is : " + account.getBalance());
		} else {
			System.out.println("Invalid Account number..");
		}
	}

	public void viewAccounts() {
		Collection<Account> viewaccounts = accounts.values();
		for (Account obj : viewaccounts) {
			System.out.println(obj);
		}

	}

	public void deleteAccount(String del_accno) {
		if (accounts.containsKey(del_accno)) {
			Account account = accounts.get(del_accno);
			Customer customer = account.getCustomer();
			accounts.remove(del_accno);
			System.out.println("Account deleted successfully..");
		} else {
			System.out.println("Invalid Account number");
		}
	}

	public void apply_loan(String acc_no, double amt, int duration, double salary, String type, String colleteral) {
		if (accounts.containsKey(acc_no)) {

			Account account = accounts.get(acc_no);
			Loan loan = new Loan(acc_no, amt, duration, salary, type, colleteral);

			loans.put(acc_no, loan);
			System.out.println("Loan application submitted successfully..");
			System.out.println("Monthly EMI if approved: " + loan.calculate_EMI());
			System.out.println("Your loan application is under review");

		} else {
			System.out.println("Invalid Account Number. Cannot apply for loan");
		}
	}

	public void processloan(String acc_no) {
		if (loans.containsKey(acc_no)) {
			Loan loan = loans.get(acc_no);
			if (!loan.getStatus().equals("pending")) {
				System.out.println("This loan application has already been processed..");
				return;
			}
			Account account = accounts.get(acc_no);
			Customer customer = account.getCustomer();
			boolean approved = true;
			String rejection_reason = " ";

			if (account.getBalance() < 1000) {
				approved = false;
				rejection_reason = "insufficient account balance history..";
			}
			if (loan.getLoan_amt() > (account.getBalance()) * 10) {
				approved = false;
				rejection_reason = "Loan amount exceeds the eligibility criteria";
			}
			if (loan.getDuration_months() > 60) {
				approved = false;
				rejection_reason = "Loan duration exceeds the duration months";
			}
			double s = loan.getSalary();
			double emi = loan.calculate_EMI();
			if (emi > (s / 2)) {
				approved = false;
				rejection_reason = "EMI exceeds salary..";
			}
			if ((loan.getLoan_type().equals("Home") || loan.getLoan_type().equals("Business")
					|| loan.getLoan_type().equals("Property") || loan.getLoan_type().equals("Personal"))
					&& loan.getLoan_amt() > 500000) {
				if (loan.getColleteral() == "") {
					approved = false;
					rejection_reason = "Colleteral is not mentioned for the loan approvement";
				}
			}
			if (approved) {
				loan.setStatus("Approved");
				account.deposit(loan.getLoan_amt());
				System.out.println(
						"Amount credited to your account " + acc_no + " . Monthly EMI: " + loan.calculate_EMI());
			} else {
				loan.setStatus("Rejected");
				System.out.println("Rejected reason: " + rejection_reason);
			}
		} else {
			System.out.println("Loan is not applied..");
		}
	}
}

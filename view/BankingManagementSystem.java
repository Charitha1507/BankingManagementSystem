package com.view;

import java.util.*;
import com.controller.BankingService;
import com.model.Loan;

public class BankingManagementSystem {
	public static void main(String[] args) {
		BankingService service = new BankingService();
		Scanner sc = new Scanner(System.in);
		System.out.println("**************************************************");
		System.out.println("*********************Welcome to*******************");
		System.out.println("***********Banking Management System**************");
		System.out.println("");
		while (true) {
			System.out.println("1. Create Customer");
			System.out.println("2. Create Account");
			System.out.println("3. Withdraw Amount");
			System.out.println("4. Deposit Amount");
			System.out.println("5. View Balance");
			System.out.println("6. View Accounts");
			System.out.println("7. Delete Account");
			System.out.println("8. Apply loan");
			System.out.println("9. Process loan");
			System.out.println("10. Exit");

			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter Customer ID:");
				String cid = sc.next();
				System.out.println("Enter Customer Name:");
				String cname = sc.next();
				service.createCustomer(cid, cname);
				break;
			case 2:
				System.out.println("Enter account type:Savings/Current");
				String acc_type = sc.next();
				System.out.println("Enter Account Number");
				String acc_number = sc.next();
				System.out.println("Enter Customer ID:");
				String cus_id = sc.next();
				System.out.println("Enter Initial Balance");
				double balance = sc.nextDouble();
				System.out.println("Create a security pin for u account");
				String pin = sc.next();
				service.createAccount(acc_type, acc_number, cus_id, balance, pin);
				break;
			case 3:
				System.out.println("Enter Account Number:");
				String withdraw_accno = sc.next();
				System.out.println("Enter Amout:");
				double withdraw_amt = sc.nextDouble();
				service.withdraw(withdraw_accno, withdraw_amt);
				break;
			case 4:
				System.out.println("Enter Account number:");
				String deposit_accno = sc.next();
				System.out.println("Enter Amount");
				double deposit_amt = sc.nextDouble();
				service.deposit(deposit_accno, deposit_amt);
				break;
			case 5:
				System.out.println("Enter Account Number");
				String view_accno = sc.next();
				service.checkBalance(view_accno);
				break;
			case 6:
				service.viewAccounts();
				break;
			case 7:
				System.out.println("Enter Account number");
				String del_accno = sc.next();
				service.deleteAccount(del_accno);
				break;
			case 8:
				System.out.println("Enter Account number");
				String acc_no = sc.next();
				System.out.println("Enter the loan type");
				String type = sc.next();
				System.out.println("Enter the amount you needed as loan");
				double amt = sc.nextDouble();
				System.out.println("Duration");
				int duration = sc.nextInt();
				System.out.println("Enter the Salary");
				double salary = sc.nextDouble();
				String col = "";
				if ((type.equals("Home") || type.equals("Business") || type.equals("Property")) && amt > 500000) {
					System.out.println("Enter the colleteral property worth");
					String colleteral = sc.next();
					col = colleteral;
				}
				service.apply_loan(acc_no, amt, duration, salary, type, col);
				break;
			case 9:
				System.out.println("Enter loan account number");
				String accno = sc.next();
				service.processloan(accno);
				break;
			case 10:
				System.exit(0);
				sc.close();
			default:
				System.out.println("Enter valid option..");
				break;
			}
		}
	}

}

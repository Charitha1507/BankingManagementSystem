package com.model;

import java.util.*;

public class Loan {
	private String acc_no;
	private double loan_amt;
	private double interest_rate;
	private String loan_type;
	private int duration_months;
	private Date issue_date;
	private String status;
	private double salary;
	private String colleteral;

	public Loan(String acc_no, double loan_amt, int duration_months, double salary, String loan_type,
			String colleteral) {
		super();
		this.acc_no = acc_no;
		this.loan_amt = loan_amt;
		this.duration_months = duration_months;
		this.issue_date = new Date();
		this.status = "pending";
		this.salary = salary;
		this.loan_type = loan_type;
		this.colleteral = colleteral;
		setInterest();
	}

	private void setInterest() {
		switch (loan_type) {
		case "Home":
			this.interest_rate = 8.00;
			break;
		case "Personal":
			this.interest_rate = 10.00;
			break;
		case "Property":
			this.interest_rate = 9.00;
			break;
		case "Education":
			this.interest_rate = 7.00;
			break;
		case "Business":
			this.interest_rate = 11.00;
			break;
		default:
			this.interest_rate = 9.50;
		}
	}

	public String getColleteral() {
		return colleteral;
	}

	public String getLoan_type() {
		return loan_type;
	}

	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}

	public void setColleteral(String colleteral) {
		this.colleteral = colleteral;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}

	public double getLoan_amt() {
		return loan_amt;
	}

	public void setLoan_amt(double loan_amt) {
		this.loan_amt = loan_amt;
	}

	public double getInterest_rate() {
		return interest_rate;
	}

	public void setInterest_rate(double interest_rate) {
		this.interest_rate = interest_rate;
	}

	public int getDuration_months() {
		return duration_months;
	}

	public void setDuration_months(int duration_months) {
		this.duration_months = duration_months;
	}

	public Date getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double calculate_EMI() {
		double monthly_interest_rate = interest_rate / (12 * 100);
		return (loan_amt * monthly_interest_rate * Math.pow(1 + monthly_interest_rate, duration_months))
				/ (Math.pow(1 + monthly_interest_rate, duration_months) - 1);
	}

	@Override
	public String toString() {
		return "Loan [acc_no=" + acc_no + ", loan_amt=" + loan_amt + ", interest_rate=" + interest_rate
				+ ", duration_months=" + duration_months + ", issue_date=" + issue_date + ", status=" + status
				+ ", EMI=" + calculate_EMI() + (colleteral != "" ? ", Colleteral= " + colleteral : "") + "]";
	}

}

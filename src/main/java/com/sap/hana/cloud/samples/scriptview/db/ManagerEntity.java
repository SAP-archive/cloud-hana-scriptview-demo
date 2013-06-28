package com.sap.hana.cloud.samples.scriptview.db;

public class ManagerEntity {
	private int id;
	private String name;
	private String address;
	private String region;
	private double budget;
	private double budgetPercentageShare;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public double getBudgetPercentageShare() {
		return budgetPercentageShare;
	}

	public void setBudgetPercentageShare(double budgetPercentageShare) {
		this.budgetPercentageShare = budgetPercentageShare;
	}
}

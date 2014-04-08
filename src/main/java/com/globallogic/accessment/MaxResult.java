package com.globallogic.accessment;

public class MaxResult {
	private String month;
	private String year;
	private int maxShare;
	private String company;
	
	public MaxResult(String year, String month, int maxShare, String company) {
		this.month = month;
		this.year = year;
		this.maxShare = maxShare;
		this.company = company;
	}

	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public int getMaxShare() {
		return maxShare;
	}
	
	public void setMaxShare(int maxShare) {
		this.maxShare = maxShare;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "MaxResult [month=" + month + ", year=" + year + ", maxShare="
				+ maxShare + ", company=" + company + "]";
	}

	
	
}


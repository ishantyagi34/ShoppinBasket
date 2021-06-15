
package com.shop.bean;

public class Customer
{
	private boolean LoggedIn;
	private float totalBill;

	public float getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(float totalBill) {
		this.totalBill = totalBill;
	}

	public Customer()
	{
		this.LoggedIn=false;
		this.totalBill = 0.0F;
	}
		
	public boolean isLoggedIn() {
		return this.LoggedIn;
	}
	
	public void LogInUser() {
		this.LoggedIn = true;
	}
	
	public void LogOutUser() {
		this.LoggedIn = false;
	}
}
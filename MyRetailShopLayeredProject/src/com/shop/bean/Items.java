package com.shop.bean;

public class Items
{
	private int itemId;
	private String itemName;
	private int quantity;
	private double unitPrice;
	private String itemType;
	public Items()
	{
		
	}
	public Items(int itemId, String itemName, int quantity, int unitPrice)
	{
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double itemPrice) {
		this.unitPrice = itemPrice;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String toString()
	{
		return "Items [itemId=" + itemId + ", itemName=" + itemName + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", itemType=" + itemType +"]";
	}
	

}

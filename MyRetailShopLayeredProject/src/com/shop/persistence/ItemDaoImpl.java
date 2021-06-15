package com.shop.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.shop.bean.Items;
import com.shop.bean.Items;
import com.shop.helper.MySQLConnection;

public class ItemDaoImpl implements ItemDao
{
	@Override
	public Collection<Items> getAllItems() throws SQLException, ClassNotFoundException
	{
		ArrayList<Items> items = null;
		Connection connection = MySQLConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from Items");
		ResultSet resultset = statement.executeQuery();

		Items item = null;
		items = new ArrayList<Items>();

		while (resultset.next())
		{
			item = new Items();
			item.setItemId(resultset.getInt("itemId"));
			item.setItemName(resultset.getString("itemName"));
			item.setQuantity(resultset.getInt("quantity"));
			item.setUnitPrice(resultset.getInt("unitPrice"));
			item.setItemType(resultset.getString("itemType"));
			
			items.add(item);
		}

		connection.close();

		return items;
	}

	int price;
	int total_bill=0;
	int remaining;
	String itemType;
	double itemPrice;
	String itemName;
	
	@Override
	public String getItemType(int id) throws SQLException, ClassNotFoundException
	{
		
		Connection connection = MySQLConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("select itemType from Items where itemId="+id);
		ResultSet resultset = statement.executeQuery();


		while (resultset.next())
		{
			itemType=(resultset.getString("itemType"));
			System.out.println("Item Added is a "+itemType);
		}

		connection.close();

		return itemType;
	}
	
	@Override
	public double getItemPrice(int id) throws SQLException, ClassNotFoundException{
		
		Connection connection = MySQLConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("select unitPrice from Items where itemId="+id);
		ResultSet resultset = statement.executeQuery();


		while (resultset.next())
		{
			itemPrice=(resultset.getDouble("unitPrice"));
		}

		connection.close();

		return itemPrice;
	}
	
	@Override
	public String getItemName(int id) throws SQLException, ClassNotFoundException{
		
		Connection connection = MySQLConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("select itemName from Items where itemId="+id);
		ResultSet resultset = statement.executeQuery();


		while (resultset.next())
		{
			itemName=(resultset.getString("itemName"));
		}

		connection.close();

		return itemName;
	}

	@Override
	public double calculateBill(int id,int quantity,String itemType) throws SQLException, ClassNotFoundException
	{
		double subtotal=0;
		Connection connection = MySQLConnection.getConnection();
		PreparedStatement statement1 = connection.prepareStatement("select quantity from items where itemId="+id);
		ResultSet rs = statement1.executeQuery();
		while (rs.next())
		{
		remaining=(rs.getInt("quantity")-quantity);
		//System.out.println(rs.getInt("quantity")+"hi");
		if(quantity>remaining || quantity<0)
		{
			subtotal=0;
			throw new InvalidQuantityException("The Quantity Entered is either Negative or Greater Than remaining quantity of thr item= "+remaining);
		}
		}
		PreparedStatement statement = connection.prepareStatement("UPDATE items SET quantity = quantity-"+quantity+" WHERE itemId="+id);
		statement.executeUpdate();
		PreparedStatement statement2 = connection.prepareStatement("select unitPrice from Items where itemId="+id);
		ResultSet rs2 = statement2.executeQuery();
		while (rs2.next())
		{
		subtotal=((rs2.getInt("unitPrice"))*(quantity));
		}
		connection.close();
		
		return subtotal;
	}

	@Override
	public Collection<Items> getCart() throws SQLException, ClassNotFoundException {
		ArrayList<Items> cartItems = null;
		// TODO Auto-generated method stub
		return cartItems;
	}
	
	
}
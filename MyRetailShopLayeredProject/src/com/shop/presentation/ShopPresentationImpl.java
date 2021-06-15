package com.shop.presentation;

import com.shop.bean.Items;
import com.shop.bean.Customer;
//import com.sujata.helper.EmployeeInputOutput;
import com.shop.service.ShopService;
import com.shop.service.ShopServiceImpl;
import com.shop.persistence.InvalidQuantityException;

import java.sql.SQLException;
import java.util.*;
import com.shop.bean.*;

public class ShopPresentationImpl implements ShopPresentation
{
	Scanner sc=new Scanner(System.in);
	private ShopService shopService=new ShopServiceImpl();
	Customer customer=new Customer();
	int quantity;
	double total = customer.getTotalBill();
	int remaining;
	int itemId;
	Character more;
	char choose;
	
	@Override
	public void showMenu()
	{
		System.out.println();
		System.out.println("WELCOME TO XYZ REATIL SHOP");
		System.out.println("1. Display All The Items We Have For You..!");
		System.out.println("2. Add Items To Your Cart");
		System.out.println("3. Exit");
	}

	@Override
	public void performMenu(int choice)
	{
		int exceptionFlag = 0;
		switch(choice) {

		case 1:
			try {
				System.out.println();
				System.out.println("=========================================================");
				Collection<Items> items=shopService.getAllItems();
				System.out.println("Item ID\t\tItem Name\t\tItem Type");
				for(Items item:items)
				{
					int space = 25-item.getItemName().length();
					String gap = "";
					for(int i=0;i<space;i++)
						gap += " ";
					System.out.println(item.getItemId()+"\t\t"+item.getItemName()+gap+item.getItemType());
				}
				System.out.println("=========================================================");
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
			
			break;
		case 2:
			if(!customer.isLoggedIn())
				{
					System.out.println("You are not currently logged in, Would you like to Login First or Exit ?(Y/N)");
					choose=sc.next().charAt(0);
					if(choose=='Y'|| choose=='y')
					{
						customer.LogInUser();
						System.out.println("Login Complete, you can add items to your cart now !!");
					}
						performMenu(2);
					}
					else
					{
						do
						{
							System.out.println("Enter the Item Id For the Item, you would like to Purchase:-");
						itemId=sc.nextInt();
						System.out.println("Enter the quantity for the selected item:-");
						quantity=sc.nextInt();
						
						try
						{
							total=shopService.addToCart(itemId,quantity);
							System.out.println("Added To Your Cart Successfully !!!!");
							System.out.println("Subtotal for Cart== "+total);
						}catch (ClassNotFoundException | SQLException e) {
							
							e.printStackTrace();
						}
						catch(InvalidQuantityException exception)
						{
							exceptionFlag = 1;
							System.out.println(exception.getMessage());
							break;
						}
						System.out.println();
						System.out.println("Want to Add More Items (Y/N)?");
						more=sc.next().charAt(0);
						}while(Character.toUpperCase(more)!='N');
						if(exceptionFlag==1)
							break;
						
						try {
							System.out.println("=========================================================");
							HashMap<Integer, Items> cartItems=shopService.getCart();
							System.out.println("YOUR CART:\t");
							System.out.println("Item ID\t\tItem Name\t\tItem Type\t\tUnit Price");
							for(Items item:cartItems.values())
							{
								int space = 25-item.getItemName().length();
								String gap = "";
								String gap2 = "";
								if(item.getItemType().equals("Cosmetics"))
									gap2 = "\t\t";
								else
									gap2 = "\t\t\t";
								for(int i=0;i<space;i++)
									gap += " ";
								System.out.println(item.getItemId()+"\t\t"+item.getItemName()+gap+item.getItemType()+gap2+item.getUnitPrice());
							}
							System.out.println("TOTAL PAY:"+total);
							System.out.println("=========================================================");
							
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//getCartTotal();
					}
			break;
			/*System.out.println("=====Bill Summary=====");
			System.out.println();
			System.out.println("Items"+"\t"+"Quantity"+"\t"+"UnitPrice");
			System.out.println();
			System.out.println("Books"+"\t"+quantity_books+"\t"+"\t"+"500 Each");
			System.out.println("CDs"+"\t"+quantity_cds+"\t"+"\t"+"200 Each");
			System.out.println("Cosmetics  "+quantity_cosmetics+"\t"+"\t"+"1000 Each");
			System.out.println();
			System.out.println("-------------------------------------");
			System.out.println("Total Bill (Including Tax)== "+total);
			System.out.println("(10% Tax on CDs and 12% Tax on Cosmetic Products)");
			System.out.println();
			System.out.println("Thankyou For Shopping, Please Visit Again!!!");
			System.exit(0);*/
		case 3:
			System.out.println();
			System.out.println("Thankyou For Shopping, Please Visit Again!!!");
			System.exit(0);
		default :
			System.out.println("Invalid Choice");
		}
	}
}

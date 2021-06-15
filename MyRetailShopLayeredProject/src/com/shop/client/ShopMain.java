package com.shop.client;

import java.util.Scanner;
import com.shop.presentation.ShopPresentation;
import com.shop.presentation.ShopPresentationImpl;

public class ShopMain
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		ShopPresentation presentation=new ShopPresentationImpl();
		while(true)
		{
			presentation.showMenu();
			System.out.println("Enter Choice : ");
			int choice=sc.nextInt();
			presentation.performMenu(choice);
		}
		
	}

}


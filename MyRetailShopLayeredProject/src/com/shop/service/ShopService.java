package com.shop.service;
import com.shop.bean.Items;
import com.shop.persistence.ItemDao;
import com.shop.persistence.ItemDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import com.shop.bean.Items;

public interface ShopService
{
	Collection<Items> getAllItems() throws SQLException, ClassNotFoundException;
	HashMap<Integer, Items> getCart() throws SQLException, ClassNotFoundException;
	double addToCart(int id,int quantity) throws SQLException, ClassNotFoundException;;
}
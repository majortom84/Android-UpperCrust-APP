
//****************************************************
//Author:       Thomas Cummings
//Date Created: 12-9-14
//Class:        CIST 2373 - Java III
//Project:      Production Exam
//Title:        Upper Crust final
//Description:  Android app, Ordering system 
//****************************************************

package com.ucapp.uppercrustapp;

public class Item {

	//variables 
	private  String item_name;
	private  String item_price;
	private int item_count;
	
	//constructors
	public Item(){};//default
	
	public Item(String item_name, String item_price) {
		super();
		this.item_name = item_name;
		this.item_price = item_price;
	}
	//getters and setters for variables
	public  String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public  String getItem_price() {
		return item_price;
	}
	public void setItem_price(String item_price) {
		this.item_price = item_price;
	}
	public int getItem_count() {
		return item_count;
	}
	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}
	
}//end class
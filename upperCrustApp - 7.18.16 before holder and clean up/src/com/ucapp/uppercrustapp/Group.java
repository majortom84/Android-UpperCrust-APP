
//****************************************************
//Author:       Thomas Cummings
//Date Created: 12-9-14
//Class:        CIST 2373 - Java III
//Project:      Production Exam
//Title:        Upper Crust final
//Description:  Android app, Ordering system 
//****************************************************

package com.ucapp.uppercrustapp;

import java.util.ArrayList;

public class Group {

	private String name;
	 private ArrayList<Item> itemList = new ArrayList<Item>();
	  
	 public Group(String name, ArrayList<Item> itemList) {
	  this.name = name;
	  this.itemList = itemList;
	 }
	 public String getName() {
	  return name;
	 }
	 public void setName(String name) {
	  this.name = name;
	 }
	 public ArrayList<Item> getItemList() {
	  return itemList;
	 }
	 public void setItemList(ArrayList<Item> countryList) {
	  this.itemList = itemList;
	 }
}


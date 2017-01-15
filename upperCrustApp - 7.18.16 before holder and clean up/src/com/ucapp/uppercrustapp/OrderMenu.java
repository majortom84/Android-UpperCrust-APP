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

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.app.ExpandableListActivity;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.os.Build;

public class OrderMenu extends ActionBarActivity {

	//expandable list view variable 
	ExpandableListView exv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); //Remove action bar from screen
		setContentView(R.layout.activity_order_menu);
		//find resource expandable list view by ID
		exv = (ExpandableListView)findViewById(R.id.itemsList);
	    
		//setGroupData();
		setChildData();

		
		exv.setAdapter((ExpandableListAdapter)new MyAdapter(this, group_items));

		/*MyAdapter mNewAdapter = new MyAdapter(group_items, child_items);
		mNewAdapter
				.setInflater(
						(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
						this);
		exv.setAdapter((ExpandableListAdapter)mNewAdapter);*/
		
	}//end on create method 


	//return to previous activity (login)
	public void returnToMain(View v)
	{
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	//add data to group headers breakfast and lunch 10.12.14
	/*public void setGroupData(){
		
		group_items.add("Breakfast");
		group_items.add("Lunch");
	}// end setGroupData method*/
	//list for objects 10.12.14
		ArrayList<Group> group_items = new ArrayList<Group>();
		//ArrayList<Item> child_items = new ArrayList<Item>(); // use Object instead of Item to make generic (and to test)
	
	// add data for child view 
	public void setChildData(){
		ArrayList<Item> child_items = new ArrayList<Item>();
		//set for breakfast
		//ArrayList<Item> child = new ArrayList<Item>();
		/*child.add("");
		child.add("");
		child.add("");*/
		Item item = new Item("test1","1.00");
		child_items.add(item);
		item = new Item("test2","1.00");
		child_items.add(item);
		item = new Item("test3","1.00");
		child_items.add(item);
		//child_items.addAll(child);
		
		Group group = new Group("Breakfast",child_items);
		group_items.add(group);
		
		child_items = new ArrayList<Item>();
		//set for lunch
		//child = new ArrayList<Item>();
		/*child.add("");
		child.add("");
		child.add("");
		child.add("");*/
		item = new Item("test1.2","1.00");
		child_items.add(item);
		item = new Item("test2.2","1.00");
		child_items.add(item);
		item = new Item("test3.2","1.00");
		child_items.add(item);
		group = new Group("Lunch",child_items);
		group_items.add(group);
		//child_items.addAll(child);
	}// end setChildData method

}//end Order menu class
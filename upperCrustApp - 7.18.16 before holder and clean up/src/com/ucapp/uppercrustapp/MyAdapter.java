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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends BaseExpandableListAdapter implements ListAdapter {

	//context variable
	private Context context;
	//String Arrays
	//String [] parentList = {"Breakfast","Lunch"};
	/*String [][] childList = {
			{"", "",""},
			{""}
	};*/
	//list for objects
	ArrayList<Group> group_items = new ArrayList<Group>();
	//ArrayList<Item> child_items = new ArrayList<Item>(); // use Object instead of Item to make generic (and to test)
	
	//private ArrayList<Group> groups;
	
	public LayoutInflater minflater;
	public Activity activity;
	
	
	
	public MyAdapter( Context context, ArrayList<Group> group_items) {
		this.context = context;
		this.group_items = new ArrayList<Group>(); // 10.12.14
		this.group_items.addAll(group_items);
		//this.child_items = child_items;// 10.12.14
		//this.child_items.addAll(child_items);
	}
	
	/*public void setInflater(LayoutInflater mInflater, Activity act) {
		this.minflater = mInflater;
		activity = act;
	}*/
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		//return parentList.length;
		return group_items.size();// 10.12.14
	}

	//@SuppressWarnings("unchecked")
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		//return childList[groupPosition].length;
	    //return ((ArrayList<Object>) child_items.get(groupPosition)).size();// 10.12.14
		//ArrayList<Item> child_items = groups.get(groupPosition).getItems();
		//child_items.get(groupPosition);
		//return child_items.size();
		ArrayList<Item> ItemList = group_items.get(groupPosition).getItemList();
		  return ItemList.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		//return groupPosition;
		return group_items.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		//return null;
		ArrayList<Item> ItemList = group_items.get(groupPosition).getItemList();
		  return ItemList.get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		/*TextView tv = new TextView(context);
		tv.setText(group_items.get(groupPosition));
		tv.setPadding(60, 10, 10, 10);
		tv.setTextSize(20);
		return tv;*/
		Group group = (Group) getGroup(groupPosition);
		  if (convertView == null) {
		   LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		   convertView = layoutInflater.inflate(R.layout.header_view, null);
		  }
		   
		  convertView.setPadding(60, 10, 10, 10);
		  //convertView.setTextSize(20);
		  
		  TextView heading = (TextView) convertView.findViewById(R.id.heading);
		  heading.setText(group.getName().trim());
		   
		  return convertView;
	}


	
	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		Item item = (Item) getChild(groupPosition, childPosition); // 10.14.14
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.child_view, parent, false); 
        }
		convertView.setPadding(10, 0, 0, 10);
		
		TextView itemNameTxt = (TextView)convertView.findViewById(R.id.itemNameTxt);
		TextView itemCostTxt = (TextView)convertView.findViewById(R.id.itemCostTxt);
		
		itemNameTxt.setText(item.getItem_name()); // 10.14.14
		itemCostTxt.setText(item.getItem_price());
		
		//add edittext for item count
		final EditText itemCount = (EditText)convertView.findViewById(R.id.itemQtTxt);
		itemCount.clearFocus(); //stop focus on edittext field 10.13.14
		// 10.11.14 ADD Buttons and each button's onClick listener and method
		//buttons and links to child_view components 
		Button addBtn = (Button)convertView.findViewById(R.id.addBTN);
		Button subBtn = (Button)convertView.findViewById(R.id.subtractBTN); //use infalInflater (convertView) as link
		//addBtn listener and method
		addBtn.setOnClickListener(new OnClickListener(){

			@SuppressLint("UseValueOf") public void onClick(View convertView) {
				// TODO Auto-generated method stub
				int sCount = Integer.parseInt(itemCount.getText().toString());// get string from edittext, turn string to int type
				if(sCount < 10){
					// Toast to test
					Toast.makeText(convertView.getContext(), "add button pressed @ \nparent " + 
					groupPosition + " & child @ "  + childPosition , Toast.LENGTH_SHORT).show();
					// set item count
					//int sCount = Integer.parseInt(itemCount.getText().toString());// get string from edittext, turn string to int type
					
					int intCount = sCount+1;// add one to count
					
					itemCount.setText(new Integer(intCount).toString());// set edittext field
				}
			}
			
		});
		//subBtn listener and method
			subBtn.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View convertView) {
						// TODO Auto-generated method stub
						int sCount = Integer.parseInt(itemCount.getText().toString());
						if (sCount > 0 ){
							// Toast to test
							Toast.makeText(convertView.getContext(), "subtract button pressed @ \nparent " + 
							groupPosition + " & child @ "  + childPosition , Toast.LENGTH_SHORT).show();
							// set item count
							//int sCount = Integer.parseInt(itemCount.getText().toString());// get string from edittext, turn string to int type
							
							int intCount = sCount-1;// add one to count
							
							itemCount.setText(new Integer(intCount).toString());// set edittext field
						}// end if
					}
					
				});
		return convertView;
	}// end getChildView method

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return false;
	}

}//end my adapter method

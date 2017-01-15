
//****************************************************
//Author:       Thomas Cummings
//Date Created: 12-9-14
//Class:        CIST 2373 - Java III
//Project:      Production Exam
//Title:        Upper Crust final
//Description:  Android app, Ordering system 
//****************************************************

package com.ucapp.uppercrustapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class ForgotPassword extends ActionBarActivity {

	//variables 
	//android types
	EditText e_mail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); //Remove action bar from screen
		setContentView(R.layout.activity_forgot_password);
		//get EditText field data by id name
		e_mail = (EditText)findViewById(R.id.emailTxt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forgot_password, menu);
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
	
	// transition to forgot password message screen
	public void sendEmailTransition(View v)
	{
		// change EditText type to String type
		String email = e_mail.getText().toString();
		if(email.isEmpty())
		{
			Toast.makeText(this, "Email is empty", Toast.LENGTH_LONG).show();
		}
		else if(email.contains("@student.wiregrass.edu") || email.contains("@wiregrass.edu"))
		{
			Intent i = new Intent(ForgotPassword.this, SendEmailTransition.class );
			startActivity(i);
		}
		else 
		{
			Toast.makeText(this, "Invalid Email", Toast.LENGTH_LONG).show();
		}
	}
	
	// code to change activity with BTN click (back to login)
		public void returnToMain(View v)
		{
			finish();
		}

}//end class
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
import android.telephony.SmsManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

/*import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class UserRegister extends ActionBarActivity {
	
	//variables 
	//android types
	EditText e_mail;
	EditText fName;
	EditText lName;
	EditText phone;
	CheckBox email_chk;
	CheckBox text_chk;
	CheckBox both_chk;
	String alert_type = "Alert me by: ";
	boolean isAlert = false;
	String altType;
	Random ranPass;
	int ranTempPass;
	String email;
	String first_name;
	String last_name;
	String phonetxt;
	HttpResponse response;
	String result;
	boolean doNoPassGo = false;
	//Mail m;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); //Remove action bar from screen
		setContentView(R.layout.activity_user_register);
		//get EditText field data by id name
		e_mail = (EditText)findViewById(R.id.emailTxt);
		fName = (EditText)findViewById(R.id.firstNameTxt);
		lName = (EditText)findViewById(R.id.lastNameTxt);
		email_chk = (CheckBox)findViewById(R.id.checkBox_email);
		phone = (EditText)findViewById(R.id.phonetxt);
		text_chk = (CheckBox)findViewById(R.id.checkBox_text);
		
		final Button send = (Button) this.findViewById(R.id.submitBTN);
        send.setOnClickListener(new View.OnClickListener() {

        	public void onClick(View view) {
        		
        		if (registerUser()){
        			if (altType.equals("B") || altType.equals("T") ){  //sendSMS and email
        				
        				String message = "UpperCrust Credentails:\n" + "Un: " + email + "\nPass: " + ranTempPass;             

        				/** Creating a pending intent which will be broadcasted when an sms message is successfully sent */
        				//PendingIntent piSent = PendingIntent.getBroadcast(getBaseContext(), 0, new Intent("sent_msg") , 0);

        				/** Creating a pending intent which will be broadcasted when an sms message is successfully delivered */
        				//PendingIntent piDelivered = PendingIntent.getBroadcast(getBaseContext(), 0, new Intent("delivered_msg"), 0);

        				/** Getting an instance of SmsManager to sent sms message from the application*/
        				//SmsManager smsManager = SmsManager.getDefault();                

        				/** Sending the Sms message to the intended party */
        				//smsManager.sendTextMessage(phonetxt, null, message, piSent, piDelivered);
        				
        				//sendSMS(phonetxt,"UpperCrust Credentails:\n" + "Un: " + email + "\nPass: " + ranTempPass);
		        		// m = new Mail("thomas.cummings8@gmail.com", "H3@ther78"); 
		 
					      //String[] toArr = {"thomas.cummings8@gmail.com"}; 
					     /* m.setTo(toArr); 
					      m.setFrom("thomas.cummings8@gmail.com"); 
					      m.setSubject("Wiregrass UpperCrust Ordering app"); 
					      m.setBody("Thank you " + first_name +" "+ last_name + " for registering with the UpperCrust Ordering app."+
					    		  		"\n\nUsername:" + email+
					    		  		"\n\nPassowrd: " + ranTempPass);*/
        			}
        			else{
        				/* m = new Mail("thomas.cummings8@gmail.com", "H3@ther78"); 
        				 
					      String[] toArr = {"thomas.cummings8@gmail.com"}; 
					      m.setTo(toArr); 
					      m.setFrom("thomas.cummings8@gmail.com"); 
					      m.setSubject("Wiregrass UpperCrust Ordering app"); 
					      m.setBody("Thank you " + first_name +" "+ last_name + " for registering with the UpperCrust Ordering app."+
					    		  		"\n\nUsername:" + email+
					    		  		"\n\nPassowrd: " + ranTempPass);*/
        			}
				      try { 
				        //m.addAttachment("/sdcard/filelocation"); 
				 
				        /*if(m.send()) { 
				          Toast.makeText(UserRegister.this, "Email was sent successfully.", Toast.LENGTH_LONG).show(); 
				          
				        } else { 
				          Toast.makeText(UserRegister.this, "Email was not sent.", Toast.LENGTH_LONG).show(); 
				        } */
				      } catch(Exception e) { 
				        //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show(); 
				       // Log.e("MailApp", "Could not send email", e); 
				      }
        		}
			    else
			    	Toast.makeText(UserRegister.this, "do not pass go.", Toast.LENGTH_LONG).show();
    } 
  });

    }//end oncreate
		
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_register, menu);
		return true;
	}

	// code to change activity with BTN click (back to login)
	public void returnToMain(View v)
	{
		finish();
	}
	//submit data for registration 
	public void registerUser(View v)
	{
		// change EditText type to String type
		email = e_mail.getText().toString();
		first_name = fName.getText().toString();
		last_name = lName.getText().toString();
		phonetxt = phone.getText().toString();
		//set alert type method call
		setAlertType();
		// check if text fields are empty and if checkBoxes are checked
		if(first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || phonetxt.isEmpty() || (!text_chk.isChecked() &&
				!email_chk.isChecked() && !both_chk.isChecked()))
		{
			if(first_name.isEmpty() && last_name.isEmpty() && email.isEmpty() && phonetxt.isEmpty())
			{
				Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show();
			}
			else if(first_name.isEmpty() && last_name.isEmpty())
			{
				Toast.makeText(this, "First name is empty\nLast name is empty", Toast.LENGTH_LONG).show();
			}
			else if(first_name.isEmpty() && email.isEmpty())
			{
				Toast.makeText(this, "First name is empty\nEmail is empty", Toast.LENGTH_LONG).show();
			}
			else if(last_name.isEmpty() && email.isEmpty())
			{
				Toast.makeText(this, "Last name is empty\nEmail is empty", Toast.LENGTH_LONG).show();
			}
			else if(last_name.isEmpty() && phonetxt.isEmpty())
			{
				Toast.makeText(this, "Last name is empty\nPhone is empty", Toast.LENGTH_LONG).show();
			}
			else if(phonetxt.isEmpty() && email.isEmpty())
			{
				Toast.makeText(this, "Phone is empty\nEmail is empty", Toast.LENGTH_LONG).show();
			}
			else if(first_name.isEmpty() && phonetxt.isEmpty())
			{
				Toast.makeText(this, "First name is empty\nPhone is empty", Toast.LENGTH_LONG).show();
			}
			else if(first_name.isEmpty())
			{
				Toast.makeText(this, "First name is empty", Toast.LENGTH_LONG).show();
			}
			else if(last_name.isEmpty())
			{
				Toast.makeText(this, "Last name is empty", Toast.LENGTH_LONG).show();
			}
			else if(email.isEmpty())
			{
				Toast.makeText(this, "Email is empty", Toast.LENGTH_LONG).show();
			}
			else if(phonetxt.isEmpty())
			{
				Toast.makeText(this, "Phone is empty", Toast.LENGTH_LONG).show();
			}
			else if(!text_chk.isChecked() && !email_chk.isChecked())
			{
				Toast.makeText(this, "You must have atleast one alert type", Toast.LENGTH_LONG).show();
			}
		}//end if text fields are empty and if checkBoxes are checked
		//test to see if email is a valid @student.wiregeass.edu or @wiregrass.edu
		else if(email.contains("@student.wiregrass.edu") || email.contains("@wiregrass.edu") && isAlert)
		{
			/*Toast toast = Toast.makeText(this, "Check Wiregrass email for confrimation\n" + alert_type, Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);//positioning Toast at Center of the Mobile Screen
    		toast.show();*/
    		
    		// generate random temp password
    		ranPass = new Random();
    		ranTempPass = ranPass.nextInt(99999999) + 10000000;
    		//Integer.toString(ranTempPass);
    		
    		
			// register user (send info to database)
    		registerUserToDB( email, ranTempPass, first_name, last_name, phonetxt, altType);
    		
    		// send confirmation text, email or both
    		
    		
			// return to login activity
			/*Intent i = new Intent(UserRegister.this, MainActivity.class);
			// set the new task and clear flags
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
	        startActivity(i);*/
		}
		else
		{
			Toast.makeText(this, "dont pass go", Toast.LENGTH_LONG).show();
		}
	}//end register user method
	public boolean setAlertType()
	{
		if(text_chk.isChecked())
		{
			//Toast.makeText(this, "Text message", Toast.LENGTH_LONG).show();
			alert_type += "Text message";
			isAlert = true;
			altType = "T";
		}
		if(email_chk.isChecked())
		{
			//Toast.makeText(this, "Email message", Toast.LENGTH_LONG).show();
			alert_type += "Email message";
			isAlert = true;
			altType = "E";
		}
		if((text_chk.isChecked() && email_chk.isChecked()))
		{
			//Toast.makeText(this, "Both Text message and Email message", Toast.LENGTH_LONG).show();
			alert_type += "Both Text message and Email message";
			isAlert = true;
			altType = "B";
		}
		return isAlert;
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
	/**
	 * Register user to database with parameters: 
	 * @param email
	 * @param ranPass
	 * @param first_name
	 * @param last_name
	 * @param phonetxt
	 * @param altType
	 */
	public void registerUserToDB(String email,int ranPass,String first_name,String last_name,String phonetxt, String altType){
		String tempPass = "true";
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    	postParameters.add(new BasicNameValuePair("email", email));
    	postParameters.add(new BasicNameValuePair("password",Integer.toString(ranTempPass)));
    	postParameters.add(new BasicNameValuePair("fname", first_name));
    	postParameters.add(new BasicNameValuePair("lname", last_name));
    	postParameters.add(new BasicNameValuePair("phone", phonetxt));
    	postParameters.add(new BasicNameValuePair("tempPass", tempPass));
    	postParameters.add(new BasicNameValuePair("alert", altType));

		try {
			HttpClient httpClient = createHttpClient();
		        HttpPost httpPost = new HttpPost("http://192.168.1.2:8080/send2.php"); 
	    		httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
	    		response = httpClient.execute(httpPost);

    	} catch (Exception e) {
    		Toast.makeText(this, "Could not connect", Toast.LENGTH_LONG).show();
    	}
		try{
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			while((line = rd.readLine()) != null){
				result = line;
			}
		} catch(Exception e){
			Toast.makeText(this, "read error", Toast.LENGTH_LONG).show();
		}
		if (result.equals("0")){
			Toast.makeText(this, "User not created", Toast.LENGTH_LONG).show();
		}
		else{
			//Toast.makeText(this, "user created", Toast.LENGTH_LONG).show();
			// user creation and register confirmation 
			Toast toast = Toast.makeText(this, "Check Wiregrass email for confrimation\n" + alert_type, Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);//positioning Toast at Center of the Mobile Screen
    		toast.show();
    		// return to login activity
    		Intent i = new Intent(UserRegister.this, MainActivity.class);
    		// set the new task and clear flags
    		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    		startActivity(i);
		}
	}// end register user in DB
	
	
	private HttpClient createHttpClient()
    {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.DEFAULT_CONTENT_CHARSET);
        HttpProtocolParams.setUseExpectContinue(params, true);
        HttpConnectionParams.setConnectionTimeout(params,20000);
        HttpConnectionParams.setSoTimeout(params,20000);

        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);

        return new DefaultHttpClient(conMgr, params);
    }
	
	
	public boolean registerUser()
	{
		// change EditText type to String type
		email = e_mail.getText().toString();
		first_name = fName.getText().toString();
		last_name = lName.getText().toString();
		phonetxt = phone.getText().toString();
		//set alert type method call
		setAlertType();
		// check if text fields are empty and if checkBoxes are checked
		if(first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || phonetxt.isEmpty() || (!text_chk.isChecked() &&
				!email_chk.isChecked() && !both_chk.isChecked()))
		{
			if(first_name.isEmpty() && last_name.isEmpty() && email.isEmpty() && phonetxt.isEmpty())
			{
				Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show();
			}
			else if(first_name.isEmpty() && last_name.isEmpty())
			{
				Toast.makeText(this, "First name is empty\nLast name is empty", Toast.LENGTH_LONG).show();
			}
			else if(first_name.isEmpty() && email.isEmpty())
			{
				Toast.makeText(this, "First name is empty\nEmail is empty", Toast.LENGTH_LONG).show();
			}
			else if(last_name.isEmpty() && email.isEmpty())
			{
				Toast.makeText(this, "Last name is empty\nEmail is empty", Toast.LENGTH_LONG).show();
			}
			else if(last_name.isEmpty() && phonetxt.isEmpty())
			{
				Toast.makeText(this, "Last name is empty\nPhone is empty", Toast.LENGTH_LONG).show();
			}
			else if(phonetxt.isEmpty() && email.isEmpty())
			{
				Toast.makeText(this, "Phone is empty\nEmail is empty", Toast.LENGTH_LONG).show();
			}
			else if(first_name.isEmpty() && phonetxt.isEmpty())
			{
				Toast.makeText(this, "First name is empty\nPhone is empty", Toast.LENGTH_LONG).show();
			}
			else if(first_name.isEmpty())
			{
				Toast.makeText(this, "First name is empty", Toast.LENGTH_LONG).show();
			}
			else if(last_name.isEmpty())
			{
				Toast.makeText(this, "Last name is empty", Toast.LENGTH_LONG).show();
			}
			else if(email.isEmpty())
			{
				Toast.makeText(this, "Email is empty", Toast.LENGTH_LONG).show();
			}
			else if(phonetxt.isEmpty())
			{
				Toast.makeText(this, "Phone is empty", Toast.LENGTH_LONG).show();
			}
			else if(!text_chk.isChecked() && !email_chk.isChecked())
			{
				Toast.makeText(this, "You must have atleast one alert type", Toast.LENGTH_LONG).show();
			}
		}//end if text fields are empty and if checkBoxes are checked
		//test to see if email is a valid @student.wiregeass.edu or @wiregrass.edu
		else if(email.contains("@student.wiregrass.edu") || email.contains("@wiregrass.edu") && isAlert)
		{
			/*Toast toast = Toast.makeText(this, "Check Wiregrass email for confrimation\n" + alert_type, Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);//positioning Toast at Center of the Mobile Screen
    		toast.show();*/
    		
    		// generate random temp password
    		ranPass = new Random();
    		ranTempPass = ranPass.nextInt(99999999) + 10000000;
    		//Integer.toString(ranTempPass);
    		
    		
			// register user (send info to database)
    		registerUserToDB( email, ranTempPass, first_name, last_name, phonetxt, altType);
    		
    		doNoPassGo = true;
    		
    		// send confirmation text, email or both
    		
    		
			// return to login activity
			/*Intent i = new Intent(UserRegister.this, MainActivity.class);
			// set the new task and clear flags
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
	        startActivity(i);*/
		}
		else
		{
			Toast.makeText(this, "Must be a valid wiregrass email", Toast.LENGTH_LONG).show();
			doNoPassGo = false;
		}
		return doNoPassGo;
	}//end register user method
	
	
	/*
	 * BroadcastReceiver mBrSend; BroadcastReceiver mBrReceive;
	 */
	/*private void sendSMS(String phoneNumber, String message) {
	    ArrayList<PendingIntent> sentPendingIntents = new ArrayList<PendingIntent>();
	    ArrayList<PendingIntent> deliveredPendingIntents = new ArrayList<PendingIntent>();
	    PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
	            new Intent(this, SmsSentReceiver.class), 0);
	    PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
	            new Intent(this, SmsDeliveredReceiver.class), 0);
	    try {
	        SmsManager sms = SmsManager.getDefault();
	        ArrayList<String> mSMSMessage = sms.divideMessage(message);
	        for (int i = 0; i < mSMSMessage.size(); i++) {
	            sentPendingIntents.add(i, sentPI);
	            deliveredPendingIntents.add(i, deliveredPI);
	        }
	        sms.sendMultipartTextMessage(phoneNumber, null, mSMSMessage,
	                sentPendingIntents, deliveredPendingIntents);

	    } catch (Exception e) {

	        e.printStackTrace();
	        Toast.makeText(getBaseContext(), "SMS sending failed...",Toast.LENGTH_SHORT).show();
	    }
	}*/
	
	
	
}//end user register activity

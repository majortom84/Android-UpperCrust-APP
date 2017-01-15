
//****************************************************
//Author:       Thomas Cummings
//Date Created: 12-9-14
//Class:        CIST 2373 - Java III
//Project:      Production Exam
//Title:        Upper Crust final
//Description:  Android app, Ordering system 
//****************************************************

package com.ucapp.uppercrustapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;




public class MainActivity extends ActionBarActivity {
	
	//variables
	String login_test_name = "t";
	String login_test_pass = "t";
	//android types
	EditText username;
	EditText pass;
	String jEmailData;
    String jPassData;
    String res;
    boolean skip = false;
	//Remember password variables
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); //Remove action bar from screen
		setContentView(R.layout.activity_main);
		//get EditText field data by id name
		username = (EditText)findViewById(R.id.emailTxt);
		pass = (EditText)findViewById(R.id.passTxt);

		/*********************************************
		 *********************************************
		 *        Code for Terms and conditions agreement message box
		 * *******************************************
		 * *******************************************
		 */
		boolean firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
	    if (firstrun){
	    	
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);

		    builder.setTitle("Terms and Conditions");
		    builder.setMessage("You must pick up your food or you will be flaged and removed from" +
		    		"the system");

		    builder.setPositiveButton("Agree", new DialogInterface.OnClickListener() {

		        public void onClick(DialogInterface dialog, int which) {
		            // Do nothing but close the dialog
		        	getSharedPreferences("PREFERENCE", MODE_PRIVATE)
			        .edit()
			        .putBoolean("firstrun", false)
			        .commit();
		            dialog.dismiss();
		        }

		    });

		    builder.setNegativeButton("Disagree", new DialogInterface.OnClickListener() {

		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		            // Do nothing, but exit. The customer must agree 
		        	finish();
		            System.exit(0);
		        }
		    });

		    AlertDialog alert = builder.create();
		    alert.show();
	    } // end if first run

		

		
	}//end oncreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//code to change activity with BTN click (to register user)
	public void registerUser(View v)
	{
		Intent i = new Intent(MainActivity.this, UserRegister.class );
		startActivity(i);
	}
	
	// code to change activity (to order menu if username and pass are ture)
	public void orderMenu(View v)
	{
		// change EditText type to String type
		String u = username.getText().toString(); 
		String p = pass.getText().toString();
		//test login username and password
		if ( u.isEmpty() && p.isEmpty() || p.isEmpty() || u.isEmpty())
		{
			if ( u.isEmpty() && p.isEmpty())
				Toast.makeText(this, "Email and Password fields are empty", Toast.LENGTH_LONG).show();
			else 
			{
					if(p.isEmpty())
					{
						Toast.makeText(this, "Password field is empty", 
								Toast.LENGTH_LONG).show();
					}
					else
					{
						Toast.makeText(this, "Email field is empty",
								Toast.LENGTH_LONG).show();
					}

			}
		}
		else if(!(u.contains("@student.wiregrass.edu")) && !(u.contains("@wiregrass.edu")))
		{
			Toast.makeText(this, "Must be a valid wiregrass email", Toast.LENGTH_SHORT).show();
			username.setText("");
		}
		// check for valid wiregrass email
		
		/**
		 ************************************************
		 *****     Connect to the database and return the password if the username is found
		 *****     When fields are not empty contact database and retrieve password based on email
		 *****     If not user is found error toast "Could not find user"
		 *****     test database password on password entered into password field
		 ************************************************
		 ************************************************
		 */
		else {
			try{
				authenticate(u);
			}catch(Exception e){
				Toast.makeText(this, "Could not connect", Toast.LENGTH_LONG).show();
				return; // exit method
			}
			//authenticate(u);
				/*ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		    	postParameters.add(new BasicNameValuePair("username", u));
		    	//postParameters.add(new BasicNameValuePair("password", password));
		    	
		    	HttpClient httpClient = new DefaultHttpClient();
		        HttpPost httpPost = new HttpPost("http://10.0.0.2:8080/login4.php"); // connection to server and page
			try {
		    		httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
		    		HttpResponse response = httpClient.execute(httpPost);
		        	res = inputStreamToString(response.getEntity().getContent()).toString();
		        	
			    		
		    	    	JSONArray ja = new JSONArray(res);
		    	    	for(int i = 0 ; i < ja.length() ; i++){
		    	    		jEmailData = ja.getJSONObject(i).getString("email"); //write name of column
		    	    		jPassData = ja.getJSONObject(i).getString("password");
		    	    	}
	
		    	} catch (Exception e) {
		    		res = res.replaceAll("\"", "");
		    		Toast.makeText(this, res, Toast.LENGTH_LONG).show();
		    		username.setText("");
		    	}*/
			if (u.equals(jEmailData) && p.equals(jPassData))
			{
					// change to /order menu activity
					Intent i = new Intent(MainActivity.this, OrderMenu.class );
					startActivity(i);
					// message to user
					Toast.makeText(this, "logged in", Toast.LENGTH_SHORT).show();
					username.setText("");
					pass.setText("");
			}//end if
			else if(!p.equals(jPassData) && !(skip))
			{
					Toast.makeText(this, "Invalid Password", Toast.LENGTH_LONG).show();
					pass.setText("");
					skip = false;
			}
		}
		
	
	}//end order menu method
	
	//cancel app button (Exit app)
	public void cancelApp(View v)
	{
		finish();
		username.setText("");
		pass.setText("");
        System.exit(0);
	}//end cancel app method
	
	//change activity to forgot password 
	public void forgotPassword(View v)
	{
		 Intent i = new Intent(MainActivity.this, ForgotPassword.class );
		 startActivity(i);
		 
	}//end forgotPassword method
	
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
	
	public void authenticate(String email){
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    	postParameters.add(new BasicNameValuePair("username", email));
    	//postParameters.add(new BasicNameValuePair("password", password));

    	//HttpClient httpClient = createHttpClient();
        //HttpPost httpPost = new HttpPost("http://10.0.0.2:8080/login4.php");
    	JSONArray ja = null;
    	JSONObject ja1 = null;

		try {
			HttpClient httpClient = new  DefaultHttpClient(); // change createHttpClient  to new  DefaultHttpClient and comment out below http method
	        //HttpPost httpPost = new HttpPost("http://192.168.1.2:8080/login4.php");
			//HttpPost httpPost = new HttpPost("http://65.5.160.179:8080/login4.php");
			HttpPost httpPost = new HttpPost("http://tcummings.biz/login4.php");
			httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
    		HttpResponse response = httpClient.execute(httpPost);
    		StatusLine statusLine = response.getStatusLine();
    		if(statusLine.getStatusCode() == HttpStatus.SC_OK){
        	res = inputStreamToString(response.getEntity().getContent()).toString();
        	
	    		//ja1 = new JSONObject(res);
    	    	ja = new JSONArray(res);
    	    	for(int i = 0 ; i < ja.length() ; i++){
    	    		//JSONObject finalObject = ja.getJSONObject(0);
    	    		
    	    		jEmailData = ja.getJSONObject(i).getString("email"); //write name of column
    	    		jPassData = ja.getJSONObject(i).getString("password");
    	    		
    	    		//jEmailData = finalObject.optString("email");
    	    		//jPassData = finalObject.optString("password");
    	    		Log.d("Json", ja.getString(0));
    	    	}
    	    	System.out.println(jEmailData);
    	    	System.out.println(jPassData);
    	    	/*
    	    	 * may not have to loop like above, can try ... String pass = JSONOject jdata =  ja.getJSONObject(0);
    	    	 * then ... String pass = jdata.getString("password");
    	    	 *  
    	    	 */
    	    	//JSONObject jdata =  ja.getJSONObject(0);
    	    	 //jPassData = jdata.getString("password");
    		}
    		else{
    			//String jsondata = ja.getJSONObject(0).toString(); // for testing
    			Toast.makeText(this, "Could not parse json data", Toast.LENGTH_LONG).show();
    			return; // exit method
    		}

    	} catch (Exception e) {
    		res = res.replaceAll("\"", "");
    		Toast.makeText(this, res, Toast.LENGTH_LONG).show();
    		username.setText("");
    		skip = true;
    	}
	}// end authenticate 
	
	/*private HttpClient createHttpClient()
    {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.DEFAULT_CONTENT_CHARSET);
        HttpProtocolParams.setUseExpectContinue(params, true);

        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);

        return new DefaultHttpClient(conMgr, params);
    }*/
	
	private StringBuilder inputStreamToString(InputStream is)
    {
    	String rLine = "";
    	StringBuilder answer = new StringBuilder();
    	BufferedReader rd = new BufferedReader(new InputStreamReader(is));
    	
    	try
    	{
    		while ((rLine = rd.readLine()) != null)
    		{
    			answer.append(rLine);
    		}
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	return answer;
    }
	
}//end main activity class

package com.example.keyring;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity {
	
	Button buttonSave;
	Button buttonDelete;
	Button buttonEdit;
	
	String test;
	
     
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		buttonSave = (Button) findViewById(R.id.action_new);
		buttonDelete = (Button) findViewById(R.id.action_delete);
		buttonEdit=(Button) findViewById(R.id.action_edit);
		//buttonSave.setOnClickListener(this);
		
	}
 

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_new:
	            openSave(buttonSave);
	        	
	        	return true;
	            
	        case R.id.action_edit:
	            openSettings(buttonEdit);	
	            return true;
	        case R.id.action_delete:
	        	
	        	deleteSelection(buttonDelete);
	        	
	        	return true;
	        	default:
		            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void openSettings(View view) {
		// TODO Auto-generated method stub
		
		//test=readFromFile();
	}




	public void openSave(View view) {
	    // Do something in response to button
		
		Intent intent = new Intent(this, Save_Activity.class);
		
		startActivity(intent);
		
	}
	
	public void deleteSelection(View view) {
	    // Do something in response to button
		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);
		 
        // Setting Dialog Title
        alertDialog.setTitle("Confirm Delete...");
 
        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want delete this?");
 
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.delete);
 
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
 
            // Write your code here to invoke YES event
            Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
            }
        });
 
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            // Write your code here to invoke NO event
            Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
            dialog.cancel();
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
		
	}

	

}

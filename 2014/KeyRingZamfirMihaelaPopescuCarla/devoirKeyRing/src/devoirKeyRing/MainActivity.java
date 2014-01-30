package devoirKeyRing;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import devoirKeyRing.R;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText masterPass1, masterPass2, passwordEntered;
	String password1, password2;
	String password;
	int failureCount=0;
	
	private String readFromFile() {

	    String ret = "";

	    try {
	    	FileInputStream inputStream = new FileInputStream(new File(Environment.getExternalStorageDirectory() + File.separator + "keyring.txt"));

	    	if ( inputStream != null ) {
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String receiveString = "";
	            StringBuilder stringBuilder = new StringBuilder();

	            while ( (receiveString = bufferedReader.readLine()) != null ) {
	                stringBuilder.append(receiveString);
	            }

	            inputStream.close();
	            ret = stringBuilder.toString();
	        }
	    }
	    catch (FileNotFoundException e) {
	        Log.e("login activity", "File not found: " + e.toString());
	    } catch (IOException e) {
	        Log.e("login activity", "Can not read file: " + e.toString());
	    }

	    return ret;
	}
	
	public Dialog retryMasterPassSetup() {
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    LayoutInflater inflater = this.getLayoutInflater();
	    builder.setView(inflater.inflate(R.layout.masterpass_setup_fail, null))
	    	.setCancelable(false)
        	.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
        		public void onClick(DialogInterface dialog, int id) {
        			showFirstTimePassDialog().show();
        		}
        });
	    return builder.create();
	}
	
	public Dialog retryMasterPass() {
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    LayoutInflater inflater = this.getLayoutInflater();
	    builder.setView(inflater.inflate(R.layout.masterpass_dialog_fail, null))
	    	.setCancelable(false)
        	.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
        		public void onClick(DialogInterface dialog, int id) {
        			showMasterPassDialog().show();
        			
        		}
        });
	    return builder.create();
	}
	
	public Dialog masterPassSetupSuccess() {
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    LayoutInflater inflater = this.getLayoutInflater();
	    builder.setView(inflater.inflate(R.layout.masterpass_setup_successful, null))
	    	.setCancelable(false)
        	.setPositiveButton("Deal!", new DialogInterface.OnClickListener() {
        		public void onClick(DialogInterface dialog, int id) {
        			Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        	        startActivity(intent);
        		}
        });
	    return builder.create();
	}
	
	public Dialog showFirstTimePassDialog() {
		
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    LayoutInflater inflater = this.getLayoutInflater();

	    builder.setView(inflater.inflate(R.layout.masterpass_setup_dialog, null))
	    	.setCancelable(false)
	    	.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int id) {
	    			Dialog f = (Dialog) dialog;
	    			
               		masterPass1 = (EditText)f.findViewById(R.id.masterPassSetup1a);
                	masterPass2 = (EditText)f.findViewById(R.id.masterPassSetup2a);
                
            	   	password1 = masterPass1.getText().toString();
                	password2 = masterPass2.getText().toString();

                	if(password1.equals(password2) &&
                	   !password1.equals(""))
                		
                	{
                		
                		File file = new File(Environment.getExternalStorageDirectory() + File.separator + "keyring.txt");
        				try {
							file.createNewFile();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
        				
        				FileWriter fw;
        				try 
        				{
	        				 fw = new FileWriter(Environment.getExternalStorageDirectory() +  File.separator + "keyring.txt");
	        				 fw.write(password1);
	        				 fw.flush();
	        				 fw.close();
        				}
        				catch(Exception e)
        				{
        			        Log.e("login activity", e.toString());
        				}
                		
                		masterPassSetupSuccess().show();
                        setContentView(R.layout.activity_second);
                	}
                	else
                	{
                	    retryMasterPassSetup().show();
                	}
               }
           });    
	    return builder.create();
	}
	
	private Dialog showMasterPassDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = this.getLayoutInflater();
		
		builder.setView(inflater.inflate(R.layout.masterpass_dialog, null))
	    	.setCancelable(false)
	    	.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int id) {
	    			Dialog f = (Dialog) dialog;	    			
               		passwordEntered = (EditText)f.findViewById(R.id.password);                
            	   	password = passwordEntered.getText().toString();
            	   	if(password.equals(readFromFile()))
            	   	{
                        setContentView(R.layout.activity_second);
            	   	}
            	   	else{
            	   		failureCount++;
            	   		if(failureCount == 3)
            	   		{
            	   	        File file = new File (Environment.getExternalStorageDirectory() + File.separator + "keyring.txt");
            	   	        file.delete();
            	   			showFirstTimePassDialog().show();
            	   			failureCount = 0;
            	   		}
            	   		else
                	   		retryMasterPass().show();
            	   	}
	    		}
	    	});
		return builder.create();
	}
	 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        File file = new File (Environment.getExternalStorageDirectory() + File.separator + "keyring.txt");
        if (file.exists()) {
        	showMasterPassDialog().show();
        }
        else 
        	showFirstTimePassDialog().show();
    }


   

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return true;
    }
    
}
	
	
	

package com.example.keyring;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.keyring.utils.CreationFile;

public class Save_Activity extends Activity implements OnClickListener{
	
	
	EditText password;
	
	
	
	private static final String TAG = CreationFile.class.getName();
    private static final String FILENAME = "myFile.txt";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save);
		// Show the Up button in the action bar.
		setupActionBar();
		password = (EditText) findViewById(R.id.editText1);
		final Button button = (Button) findViewById(R.id.button_save);
	    button.setOnClickListener(this);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	
	
	
	
    
    
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		
//		
//		Intent intent = new Intent(Save_Activity.this, Save_Activity.class);
//		intent.putExtra("Password", password.getText().toString());
//		
//		startActivity(intent);
		Log.d("test" , password.getText().toString());
		writeToFile(password.getText().toString());
		
		Toast.makeText(this,R.string.save,Toast.LENGTH_LONG).show();
		
	}
	
	
	/*Methode pour ecrire dans un fichier txt le mot de passe que nous avons entre au prealable*/
	  public void writeToFile(String data) {
	        try {
	            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(FILENAME, Context.MODE_APPEND));
	            outputStreamWriter.write(data);
	            outputStreamWriter.close();
	        }
	        catch (IOException e) {
	            Log.e(TAG, "File write failed: " + e.toString());
	        } 
	         
	    }
	  
	  
	  
	 
	    public String readFromFile() {
	         
	        String ret = "";
	         
	        try {
	            InputStream inputStream = openFileInput(FILENAME);
	             
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
	            Log.e(TAG, "File not found: " + e.toString());
	        } catch (IOException e) {
	            Log.e(TAG, "Can not read file: " + e.toString());
	        }
	 
	        return ret;
	    }
	
	

}

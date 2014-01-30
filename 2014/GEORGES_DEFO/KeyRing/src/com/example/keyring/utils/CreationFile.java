package com.example.keyring.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class CreationFile extends Activity{
	private static final String TAG = CreationFile.class.getName();
    private static final String FILENAME = "myFile.txt";
     
    /** Called when the activity is first created. */
     
       // String textToSaveString = "";
         
        
         
        String textFromFileString =  readFromFile();
         
//        if ( textToSaveString.equals(textFromFileString) ) {
//            Toast.makeText(getApplicationContext(), "both string are equal", Toast.LENGTH_SHORT).show();
//        
//        }else{
//            Toast.makeText(getApplicationContext(), "there is a problem", Toast.LENGTH_SHORT).show();
//    }
     
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

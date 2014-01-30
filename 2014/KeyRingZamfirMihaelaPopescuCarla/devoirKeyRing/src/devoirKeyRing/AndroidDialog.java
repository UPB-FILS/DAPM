package devoirKeyRing;

import devoirKeyRing.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AndroidDialog extends Activity {
	 final private static int DIALOG_LOGIN = 1;

	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_android_dialog);

	  Button launch_button = (Button) findViewById(R.id.btn_launch);

	  launch_button.setOnClickListener(new View.OnClickListener() {

	  
	@SuppressWarnings("deprecation")
	@Override
	   public void onClick(View v) {
	    showDialog(DIALOG_LOGIN);
	   }
	  });
	 }

	 @Override
	 protected Dialog onCreateDialog(int id) {

	  AlertDialog dialogDetails = null;

	  switch (id) {
	  case DIALOG_LOGIN:
	   LayoutInflater inflater = LayoutInflater.from(this);
	   View dialogview = inflater.inflate(R.layout.dialog_layout, null);

	   AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
	   dialogbuilder.setTitle("Login");
	   dialogbuilder.setView(dialogview);
	   dialogDetails = dialogbuilder.create();

	   break;
	  }

	  return dialogDetails;
	 }

	 @Override
	 protected void onPrepareDialog(int id, Dialog dialog) {

	  switch (id) {
	  case DIALOG_LOGIN:
	   final AlertDialog alertDialog = (AlertDialog) dialog;
	   Button loginbutton = (Button) alertDialog
	     .findViewById(R.id.btn_login);
	   Button cancelbutton = (Button) alertDialog
	     .findViewById(R.id.btn_cancel);
	   final EditText userName = (EditText) alertDialog
	     .findViewById(R.id.txt_name);
	   final EditText password = (EditText) alertDialog
	     .findViewById(R.id.password);

	   loginbutton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
	     alertDialog.dismiss();
	     Toast.makeText(
	       AndroidDialog.this,
	       "User Name : " + userName.getText().toString()
	         + "  Password : "
	         + password.getText().toString(),
	       Toast.LENGTH_LONG).show();
	    }
	   });

	   cancelbutton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
	     alertDialog.dismiss();
	    }
	   });
	   break;
	  }
	 }
}

package com.parse.starter;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity{
	Button ok;
	EditText un,pw,email;
	TextView error;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		ok=(Button)findViewById(R.id.btn_login2);
		un=(EditText)findViewById(R.id.et_un2);
        pw=(EditText)findViewById(R.id.et_pw2);
		ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				startMerchantActivity();
	        	Context context = getApplicationContext();
	        	CharSequence text = "Your Login is being processed ... ";
	        	int duration = Toast.LENGTH_LONG;
				Toast.makeText(context, text, duration).show();
				ParseUser.logInInBackground(un.getText().toString(), pw.getText().toString(), new LogInCallback() {
				    public void done(ParseUser user, ParseException e) {
				        if (user != null) {
//				        	ParseUser currentUser = ParseUser.getCurrentUser();
        		        	Context context = getApplicationContext();
        		        	int duration = Toast.LENGTH_SHORT;
        		        	CharSequence text1;
//        		        	text1 = currentUser.getUsername();
//        		        	Toast.makeText(context, text1, duration).show();
				        	if (user.getBoolean("emailVerified") == true){
				        		if (user.getBoolean("is_merchant") == true){
				        			startMerchantActivity();
				        		}else{
				        			startUserActivity();
				        		}
				        	}
				        	else{
	        		        	text1 = "Please verify your email !!!";
	        		        	Toast.makeText(context, text1, duration).show();
				        	}
				        } else {
        		        	Context context = getApplicationContext();
        		        	CharSequence text = "Login Failed  !!!!!!!!!!! ";
        		        	int duration = Toast.LENGTH_SHORT;
        		        	Toast.makeText(context, text, duration).show();
				        }
				    }
		    	});				
			}
		});	
	}

	private void startMerchantActivity() {
		this.startActivity(new Intent(this, MerchantActivity.class));
	}

	private void startUserActivity(){
		this.startActivity(new Intent(this, UserActivity.class));
	}
}

package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ParseStarterProjectActivity extends Activity {
	Button login, signup;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        login = (Button)findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startLoginActivity();
			}
        });
        signup = (Button)findViewById(R.id.btn_signup);
        signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startSignUpActivity();
			}
        });	
	}

	protected void startSignUpActivity() {
		this.startActivity(new Intent(this, Page1Activity.class));
	}

	protected void startLoginActivity() {
		this.startActivity(new Intent(this, LoginActivity.class));
	}
}
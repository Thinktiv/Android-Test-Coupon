package com.parse.starter;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Page1Activity extends Activity {
	EditText un,pw,email;
	TextView error;
	Button ok;
	CheckBox merchant;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page1);
		un=(EditText)findViewById(R.id.et_un1);
        pw=(EditText)findViewById(R.id.et_pw1);
        email=(EditText)findViewById(R.id.et_email);
		ok=(Button)findViewById(R.id.btn_login1);
        error=(TextView)findViewById(R.id.tv_error1);
        merchant=(CheckBox)findViewById(R.id.merchant);
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
        		ParseUser user = new ParseUser();
        		user.setUsername(un.getText().toString());
        		user.setPassword(pw.getText().toString());
        		user.setEmail(email.getText().toString());
        		user.put("is_merchant", false);
        		if (merchant.isChecked()){
        			user.put("is_merchant", true);
        		}
	        	Context context = getApplicationContext();
	        	CharSequence text = "Your SignUp is being processed ... ";
	        	int duration = Toast.LENGTH_LONG;
				Toast.makeText(context, text, duration).show();

        		user.signUpInBackground(new SignUpCallback() {
        		    public void done(ParseException e) {
        		        if (e == null) {
        		        	Context context = getApplicationContext();
        		        	CharSequence text = "SignUp Success, Mail Sent :)";
        		        	int duration = Toast.LENGTH_SHORT;
        		        	Toast.makeText(context, text, duration).show();
        		        } else {
        		        	Context context = getApplicationContext();
        		        	CharSequence text = "SignUp Failure :(";
        		        	int duration = Toast.LENGTH_SHORT;
        		        	Toast.makeText(context, text, duration).show();
        		        }
        		    }
        		});

            }
        });

	}
}

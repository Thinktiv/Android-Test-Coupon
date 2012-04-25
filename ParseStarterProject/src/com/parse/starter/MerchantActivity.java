package com.parse.starter;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MerchantActivity extends Activity{
	Button add_c;
	EditText et_cp_code, et_cp_name, et_cat_name;
	TextView tv_merchant, tv_c_name, tv_cat_name, tv_c_code;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.merchant);
		add_c = (Button)findViewById(R.id.btn_add_c);
		et_cp_code = (EditText)findViewById(R.id.et_c_code);
		et_cat_name = (EditText)findViewById(R.id.et_cat_name);
		et_cp_name = (EditText)findViewById(R.id.et_c_name);
		tv_merchant = (TextView)findViewById(R.id.tv_merchant);
		tv_c_name = (TextView)findViewById(R.id.tv_c_name);
		tv_cat_name = (TextView)findViewById(R.id.tv_cat_name);
		tv_c_code = (TextView)findViewById(R.id.tv_c_code);
		add_c.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				add_c.setText("Wait ...");
				tv_c_name.setVisibility(View.INVISIBLE);
				tv_cat_name.setVisibility(View.INVISIBLE);
				tv_c_code.setVisibility(View.INVISIBLE);
				et_cp_code.setVisibility(View.INVISIBLE);
				et_cp_name.setVisibility(View.INVISIBLE);
				et_cat_name.setVisibility(View.INVISIBLE);
				ParseQuery query = new ParseQuery("Category");
				query.whereEqualTo("name", et_cat_name.getText().toString().toLowerCase());
				query.findInBackground(new FindCallback() {
				    public void done(List<ParseObject> scoreList, ParseException e) {
						ParseObject newCoupon = new ParseObject("Coupon");
						ParseObject category = new ParseObject("Category");
				        if (e == null) {
				            Log.d("score", "Retrieved " + scoreList.size() + " scores");
				            if (scoreList.size()>0){
				            	category = scoreList.get(0);	
				            }else{
				            	category.put("name", et_cat_name.getText().toString().toLowerCase());	
				            }
				        } else {
				            Log.d("score", "Error: " + e.getMessage());
				        }
						newCoupon.put("category", category);
						newCoupon.put("c_code", et_cp_code.getText().toString());
						newCoupon.put("c_desc", et_cp_name.getText().toString());
						newCoupon.saveInBackground(new SaveCallback() {
						    public void done(ParseException e) {
						        if (e == null) {
						        	et_cat_name.setText("");
						        	et_cp_code.setText("");
						        	et_cp_name.setText("");
						        	add_c.setText("Add");
									tv_c_name.setVisibility(View.VISIBLE);
									tv_cat_name.setVisibility(View.VISIBLE);
									tv_c_code.setVisibility(View.VISIBLE);
									et_cp_code.setVisibility(View.VISIBLE);
									et_cp_name.setVisibility(View.VISIBLE);
									et_cat_name.setVisibility(View.VISIBLE);
						        	Context context = getApplicationContext();
						        	CharSequence text = "Your Coupon Saved.";
						        	int duration = Toast.LENGTH_LONG;
									Toast.makeText(context, text, duration).show();
						        } else {
						        	Context context = getApplicationContext();
						        	CharSequence text = "Your Coupon Failed.";
						        	int duration = Toast.LENGTH_LONG;
									Toast.makeText(context, text, duration).show();
						        }
						    }
						});

				    }
				});
			}
		});
	}
}

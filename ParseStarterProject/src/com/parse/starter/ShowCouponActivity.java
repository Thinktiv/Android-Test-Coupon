package com.parse.starter;

import java.util.Collections;
import java.util.List;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowCouponActivity extends Activity{
	TextView c_desc, c_code, cat;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coupon);
		c_desc = (TextView)findViewById(R.id.tv_coupon_desc);
    	c_code = (TextView)findViewById(R.id.tv_coupon_code);
    	cat = (TextView)findViewById(R.id.tv_coupon_cat);
		String cat_name = getIntent().getExtras().getString("category");
		ParseQuery query = new ParseQuery("Category");
		query.whereEqualTo("name", cat_name);
		query.findInBackground(new FindCallback() {
		    public void done(List<ParseObject> scoreList, ParseException e) {
		        if (e == null) {
		        	ParseObject category = new ParseObject("Category");
		        	category = scoreList.get(0);
		        	final String cat_name  = category.getString("name");
		        	ParseQuery c_query = new ParseQuery("Coupon");
		        	c_query.whereEqualTo("category", category);
		        	c_query.findInBackground(new FindCallback() {
						public void done(List<ParseObject> c_list, ParseException c_error) {
							if (c_list.size() > 0){
								ParseObject coupon = new ParseObject("Coupon");
								Collections.shuffle(c_list);
								coupon = c_list.get(0);
								c_desc.setText("Coupon: "+coupon.getString("c_desc"));
								c_code.setText("Coupon Id: "+coupon.getString("c_code"));
								cat.setText("Category: "+cat_name);								
							}
						}
					});
		        } else {
		            Log.d("score", "Error: " + e.getMessage());
		        }
		    }
		});
	}
}

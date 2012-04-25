package com.parse.starter;

import java.util.Iterator;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class UserActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user);

		final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_category);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item);
		
		ParseQuery query = new ParseQuery("Category");
		query.findInBackground(new FindCallback() {
		    public void done(List<ParseObject> scoreList, ParseException e) {
		        if (e == null) {
		            Iterator<ParseObject> itr = scoreList.iterator();
		            while (itr.hasNext()){
		            	adapter.add(itr.next().getString("name").toLowerCase());
		            }
		            textView.setAdapter(adapter);
		        } else {
		            Log.d("score", "Error: " + e.getMessage());
		        }
		    }
		});
		
		textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {
				displayCouponActivity(((TextView)v).getText());
			}

		});
	}
	private void displayCouponActivity(CharSequence text) {
		Intent i = new Intent(this, ShowCouponActivity.class);
		i.putExtra("category", text);
		this.startActivity(i);
	}
}

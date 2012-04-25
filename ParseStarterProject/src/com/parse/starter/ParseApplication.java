package com.parse.starter;

import com.parse.Parse;
import android.app.Application;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		Parse.initialize(this, "8I3SAzHo5EF2Tz4tPxL9qwBewSEwpmQf8HkOMOl5", "fX8h69ce49l5BOh2Fod3lPuLu8Y3zL2atoF9kZvn");

	}

}

package tcd.com.t;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AllTestWithSidActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_test_with_sid);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all_test_with_sid, menu);
		return true;
	}

}

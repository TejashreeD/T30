package tcd.com.t;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SingleStudentReportActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_student_report);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_student_report, menu);
		return true;
	}

}

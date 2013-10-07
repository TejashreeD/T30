package tcd.com.t;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AfterAdminReportMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_after_admin_report_main);
		Button buttonRAssignment = (Button) findViewById(R.id.buttonRPShowAss);
		Button buttonRAlarm = (Button) findViewById(R.id.buttonRPAlaram);
		Button buttonRTest = (Button) findViewById(R.id.buttonRPshowTest);
		Button buttonRSingleStud = (Button) findViewById(R.id.buttonRPSingleStudent);
		
		buttonRAssignment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intents = new Intent(AfterAdminReportMainActivity.this,
						AssigmentReportActivity.class);
				startActivity(intents);

				
			}
		});
buttonRAlarm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intents = new Intent(AfterAdminReportMainActivity.this,
						AlarmReportActivity.class);
				startActivity(intents);

			}
		});
buttonRTest.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intents = new Intent(AfterAdminReportMainActivity.this,
				TestReportActivity.class);
		startActivity(intents);

	}
});
buttonRSingleStud.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intents = new Intent(AfterAdminReportMainActivity.this,
				SingleStudentReportActivity.class);
		startActivity(intents);

	}
});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.after_admin_report_main, menu);
		return true;
	}

}

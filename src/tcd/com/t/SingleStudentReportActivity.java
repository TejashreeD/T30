package tcd.com.t;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SingleStudentReportActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_student_report);
		Button btnSStudAsg=(Button) findViewById(R.id.buttonRPShowAssSStud);
		Button btnSStudTest=(Button) findViewById(R.id.buttonRPshowTestSStud);
		btnSStudAsg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SingleStudentReportActivity.this, AllAsgWithStudIdActivity.class);
				startActivity(intent);
			
				
			}
		});
		
btnSStudTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SingleStudentReportActivity.this, AllTestWithSidActivity.class);
				startActivity(intent);
			
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_student_report, menu);
		return true;
	}

}

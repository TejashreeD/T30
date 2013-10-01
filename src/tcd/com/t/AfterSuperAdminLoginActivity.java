package tcd.com.t;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AfterSuperAdminLoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_after_super_admin_login);
		Button buttonSchEntry = (Button) findViewById(R.id.buttonSupAdSchool);
		buttonSchEntry.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AfterSuperAdminLoginActivity.this,
						SchoolEntryActivity.class);
				startActivity(intent);
			}
		});
		Button buttonPersonAdd = (Button) findViewById(R.id.buttonSupAdmPersonEntry);
		buttonPersonAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AfterSuperAdminLoginActivity.this,
						TeachersEntryActivity.class);
				startActivity(intent);

			}
		});
		Button buttonreport = (Button) findViewById(R.id.buttonSupAdmReport);
		buttonreport.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AfterSuperAdminLoginActivity.this,
						ReportActivity.class);
				startActivity(intent);

			}
		});
		Button buttonlogout = (Button) findViewById(R.id.buttonSupAdmLogout);
		buttonlogout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(AfterSuperAdminLoginActivity.this,
						LoginMainActivity.class);
				startActivity(intent);

			}
		});
	}

}

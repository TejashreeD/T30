package tcd.com.t;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AfterAdminMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_after_admin_main);
		Button btnAddTeacher=(Button) findViewById(R.id.buttonAfterAdminLoginTeachersEntry);
		Button btnAddStudent= (Button) findViewById(R.id.buttonAfterAdminLoginStudentEntry);
		Button btnAddCls= (Button) findViewById(R.id.buttonAfterAdminLoginStandardEntry);
		Button btnAddDiv= (Button) findViewById(R.id.buttonAfterAdminLoginDivisionEntry);
		Button btnAddSub= (Button) findViewById(R.id.buttonAfterAdminLoginSubjectEntry);
		Button btnAddCalender= (Button) findViewById(R.id.buttonAfterAdminLoginCalender);
		Button btnTimeTable= (Button) findViewById(R.id.buttonAfterAdminLoginCreateTimetable);
		Button btnExamTT= (Button) findViewById(R.id.buttonAfterAdminLoginExamTimetable);
		Button btnReport= (Button) findViewById(R.id.buttonAReport);
		Button btnLogout= (Button) findViewById(R.id.buttonAfterAdminLogout);
		btnAddTeacher.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent1=new Intent(AfterAdminMainActivity.this, TeachersEntryActivity.class);
				startActivity(intent1);
			}
		});
		btnAddStudent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent2=new Intent(AfterAdminMainActivity.this, StudentEntryActivity.class);
				startActivity(intent2);
				
			}
		});
		btnAddCls.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent intent3=new Intent(AfterAdminMainActivity.this, AddClassActivity.class);
			startActivity(intent3);
			}
		});
		btnAddDiv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent intent4 =new Intent(AfterAdminMainActivity.this, AddDivisionActivity.class);
			startActivity(intent4);
			}
		});
		btnAddSub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent5 =new Intent(AfterAdminMainActivity.this, AddSubjectActivity.class);
				startActivity(intent5);	
			}
		});
		btnAddCalender.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent intent6=new Intent(AfterAdminMainActivity.this, ACalenderActivity.class);
			startActivity(intent6);
			}
		});
		btnTimeTable.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent intent7=new Intent(AfterAdminMainActivity.this, TimeTableActivity.class);
			startActivity(intent7);
			}
		});
		btnExamTT.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent8=new Intent(AfterAdminMainActivity.this, ExamSheetActivity.class);
				startActivity(intent8);
					
			}
		});
		btnReport.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent9=new Intent(AfterAdminMainActivity.this, ReportActivity.class);
				startActivity(intent9);
					
			}
		});
btnLogout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent10=new Intent(AfterAdminMainActivity.this, LoginMainActivity.class);
				startActivity(intent10);
				
			}
		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.after_admin_main, menu);
		return true;
	}

}

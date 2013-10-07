package tcd.com.t;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AfterTeacherLoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_after_teacher_login);
		Button btnAddSub=(Button) findViewById(R.id.buttonAddSubjectT);
		Button btnTimetable=(Button) findViewById(R.id.buttonTTimrTableT);
		Button btnAttendence=(Button) findViewById(R.id.buttonAttendenceT);
		Button btnAssignment=(Button) findViewById(R.id.buttonAssignmentT);
		Button btnAlarm=(Button) findViewById(R.id.buttonAlarmT);
		Button btnExam=(Button) findViewById(R.id.buttonExamT);
		Button btnReport=(Button) findViewById(R.id.buttonReportT);
		Button btnLogout=(Button) findViewById(R.id.buttonLogoutT);
		
		btnAddSub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent intent=new Intent(AfterTeacherLoginActivity.this, AllocateSubActivity.class);
			startActivity(intent);
			}
		});
		btnAssignment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(AfterTeacherLoginActivity.this, ATAssignmentActivity.class);
				startActivity(intent);
			}
		});
		btnTimetable.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(AfterTeacherLoginActivity.this, ClassDivActivity.class);
				startActivity(intent);
				
			}
		});
		btnAttendence.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(AfterTeacherLoginActivity.this, AttendenceActivity.class);
				startActivity(intent);
					
			}
		});
		btnAlarm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(AfterTeacherLoginActivity.this, ATAlarmActivity.class);
				startActivity(intent);
					
			}
		});
		btnExam.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(AfterTeacherLoginActivity.this, ExamSheetActivity.class);
				startActivity(intent);
				
			}
		});
		btnReport.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(AfterTeacherLoginActivity.this, AfterAdminReportMainActivity.class);
				startActivity(intent);
				
			}
		}) ;
		btnLogout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(AfterTeacherLoginActivity.this, LoginMainActivity.class);
				startActivity(intent);
				
			}
		});
	
	}

}

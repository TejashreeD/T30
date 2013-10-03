package tcd.com.t;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ClassDivActivity extends Activity {
	EditText currentTime;
	Calendar calendar = Calendar.getInstance();
	int hr = calendar.get(Calendar.HOUR_OF_DAY);
	int mnt = calendar.get(Calendar.MINUTE);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_div);
// here we have to get class and division from spinner
		Button buttonGo = (Button) findViewById(R.id.buttonTimatableGo);
		Button buttonBack = (Button) findViewById(R.id.buttonTTBack);
		

		buttonGo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ClassDivActivity.this,
						ShowTTActivity.class);
				startActivity(intent);
			}
		});
		buttonBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ClassDivActivity.this,
						AfterTeacherLoginActivity.class);
				startActivity(intent);
			}
		});

	}

	
		 void updateTime(int hr, int mnt) {
			// TODO Auto-generated method stub
			String timeSet;
			if (hr > 12) {
				hr -= 12;
				timeSet = "PM";

			} else if (hr == 0) {
				hr += 12;
				timeSet = "AM";
			} else if (hr == 12)
				timeSet = "PM";
			else
				timeSet = "AM";

			if (mnt < 10) {

			} else {
			}
			String setTime = new StringBuilder().append(hr).append(':')
					.append(mnt).append(" ").append(timeSet).toString();
			currentTime.setText(setTime);

		}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.class_div, menu);
		return true;
	}

}

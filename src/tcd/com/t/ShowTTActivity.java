package tcd.com.t;

import java.sql.Date;
import java.util.Calendar;

import javax.security.auth.PrivateCredentialPermission;

import android.os.Bundle;
import android.animation.TimeAnimator.TimeListener;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class ShowTTActivity extends Activity {

	final static int TIME = 1111;
	Calendar calendar = Calendar.getInstance();
	int hr = calendar.get(calendar.HOUR_OF_DAY);
	int mnt = calendar.get(calendar.MINUTE);
	EditText txtTtStartTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_tt);
		Button btnBack = (Button) findViewById(R.id.buttonTTBack);
		txtTtStartTime = (EditText) findViewById(R.id.editTextTTStartTime);
		EditText txtTtEt = (EditText) findViewById(R.id.editTextTTEndTime);
		
		final EditText ctime = (EditText) findViewById(R.id.editTextTTCurrentTime);
		updateTime(hr, mnt);

		Time time = new Time(Time.getCurrentTimezone());

		time.setToNow();
		// System.out.println("Time:"+time.hour+time.minute);
		ctime.setText(time.format("%H:%M"));
		/*ctime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Time time = new Time(Time.getCurrentTimezone());

				time.setToNow();
				// System.out.println("Time:"+time.hour+time.minute);
				ctime.setText(time.format("%H:%M"));

			}
		});*/
		// Date d=new Date(0);
		// CharSequence s = DateFormat.format("EEEE, MMMM d, yyyy ",
		// d.getTime());
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(ShowTTActivity.this,
						ClassDivActivity.class);
				startActivity(intent);
			}
		});
		txtTtStartTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new TimePickerDialog(ShowTTActivity.this, timeSetListener, hr,
						mnt, false).show();

			}
		});

	}

	TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hour2, int mnt2) {
			// TODO Auto-generated method stub
			hr = hour2;
			mnt = mnt2;
			updateTime(hr, mnt);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_tt, menu);
		return true;
	}

	protected void updateTime(int hr, int mnt) {
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

		String minSet = "";
		if (mnt < 10) {
			minSet = "0" + mnt;

		} else {
			minSet = String.valueOf(mnt);
		}
		String setTime = new StringBuilder().append(hr).append(':').append(mnt)
				.append(" ").append(timeSet).toString();
		txtTtStartTime.setText(setTime);

	}

}

package tcd.com.t;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class ShowTTActivity extends Activity {

	final static int TIME = 1111;
	Calendar calendar = Calendar.getInstance();
	int hr = calendar.get(Calendar.HOUR_OF_DAY);
	int mnt = calendar.get(Calendar.MINUTE);
	EditText txtStartTime;
	EditText txtEndTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_tt);
		Button btnBack = (Button) findViewById(R.id.buttonTTBack);
		txtStartTime = (EditText) findViewById(R.id.editTextTTStartTime);
		txtEndTime = (EditText) findViewById(R.id.editTextTTEndTime);

		final EditText ctime = (EditText) findViewById(R.id.editTextTTCurrentTime);
		updateTime(hr, mnt);

		Time time = new Time(Time.getCurrentTimezone());

		time.setToNow();
		// System.out.println("Time:"+time.hour+time.minute);
		ctime.setText(time.format("%H:%M"));

		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(ShowTTActivity.this,
						ClassDivActivity.class);
				startActivity(intent);
			}
		});
		txtStartTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new TimePickerDialog(ShowTTActivity.this, timeSetListener, hr,
						mnt, false).show();

			}
		});
		txtEndTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new TimePickerDialog(ShowTTActivity.this, timeSetListener2, hr,
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

	TimePickerDialog.OnTimeSetListener timeSetListener2 = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hour2, int mnt2) {
			// TODO Auto-generated method stub
			hr = hour2;
			mnt = mnt2;
			updateTime2(hr, mnt);
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

		if (mnt < 10) {

		} else {
		}
		String setTime = new StringBuilder().append(hr).append(':').append(mnt)
				.append(" ").append(timeSet).toString();
		txtStartTime.setText(setTime);

	}

	protected void updateTime2(int hr, int mnt) {
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
		String setTime = new StringBuilder().append(hr).append(':').append(mnt)
				.append(" ").append(timeSet).toString();
		txtEndTime.setText(setTime);

	}

}

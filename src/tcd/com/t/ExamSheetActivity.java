package tcd.com.t;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class ExamSheetActivity extends Activity {
	HttpResponse resp;
	Calendar calendar;
	Intent intent1;
	private String OutputData;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private String respStr;
	String strClass, strDiv,strExamType;
	EditText examStartDate, examEndDate, examId, examTime, examDescptn;
	int hr = calendar.get(Calendar.HOUR_OF_DAY);
	int mnt = calendar.get(Calendar.MINUTE);
	
	Calendar myCalendar, myCalendar1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exam_sheet);
		Button buttonExamSubmit = (Button) findViewById(R.id.buttonExamSubmit);
		Button buttonExamReset = (Button) findViewById(R.id.buttonExamReset);
		Button buttonExamCancel = (Button) findViewById(R.id.buttonExamCancel);
		examStartDate = (EditText) findViewById(R.id.editTextExamStartDate);
		examEndDate = (EditText) findViewById(R.id.editTextExamEndDate);
		examId = (EditText) findViewById(R.id.editTextExamtimeTableID);
		examTime = (EditText) findViewById(R.id.editTextExamTime);
		examDescptn = (EditText) findViewById(R.id.editTextExamDescription);
		myCalendar = Calendar.getInstance();

		examTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new TimePickerDialog(ExamSheetActivity.this, timeSetListener, hr,
						mnt, false).show();

			}
		});
		examStartDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ExamSheetActivity.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();

			}
		});
		myCalendar1 = Calendar.getInstance();
		examEndDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ExamSheetActivity.this, date1, myCalendar1
						.get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
						myCalendar1.get(Calendar.DAY_OF_MONTH)).show();

			}
		});

		buttonExamSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("onClick!!!");
				// Spinner element
				new Thread() {

					public void run() {
						// TODO Run network requests here.
						System.out
								.println("*********txtId.getText().toString()::"
										+ examId.getText().toString()//here we have to add stirng for CDID
										+ examStartDate.getText().toString()
										+ examEndDate.getText().toString()
										+ examTime.getText().toString()
										+ examDescptn.getText().toString());

						HttpClient httpClient = new DefaultHttpClient();
						// HttpPost post = new
						// HttpPost("http://samidha.org/restTrials/login/");
						HttpPost post = new HttpPost(
								"http://115.111.105.152/schoolApp/examttEntry");
						post.setHeader("content-type",
								"application/json; charset=UTF-8");

						// Construimos el objeto cliente en formato JSON
						JSONObject dato = new JSONObject();

						try {

							/*
							 // payload
							 {
            "ETTId": "ETT2012001",
            "Date": "2013-09-26",
            "endDate": null,
            "SubjectId": "Sub_Maths",
            "Time": "10:15:00",
            "CDId": "1A",
            "TestType": "Surprise",
            "Description": ""
        },
							 */

							dato.put("ETTId", examId.getText().toString());
							dato.put("Date", examStartDate.getText().toString());
							dato.put("endDate", examEndDate.getText().toString());
						//	dato.put("SubjectId", .getText().toString());// spinner item
							dato.put("Time", examTime.getText().toString());
						//	dato.put("CDId", exam.getText().toString());// spinner item
						//	dato.put("TestType", exam.getText().toString());
							dato.put("Description", examDescptn.getText().toString());
							System.out.println("OKAY_0!!");

							StringEntity entity = new StringEntity(dato
									.toString());
							System.out.println("OKAY_1!!");
							post.setEntity(entity);
							System.out.println("OKAY_2!!");

							HttpResponse resp = httpClient.execute(post);
							System.out.println("OKAY_3!!");
							respStr = EntityUtils.toString(resp.getEntity());
							System.out.println("OKAY_4!!");

							System.out.println("OKAY_5!!" + respStr);
							// Toast.makeText(getBaseContext(),
							// "OKAY!!!"+respStr, Toast.LENGTH_SHORT).show();
						} catch (Exception exception) {
							Log.e("MYAPP", "exception", exception);
						}
						/* just try */

						JSONObject jsonResponse;

						try {
							System.out.println("OKAY_6!!");
							/******
							 * Creates a new JSONObject with name/value mappings
							 * from the JSON string.
							 ********/
							jsonResponse = new JSONObject(respStr);
							System.out.println("OKAY_7!!");
							/*****
							 * Returns the value mapped by name if it exists and
							 * is a JSONArray.
							 ***/
							/******* Returns null otherwise. *******/
							JSONArray jsonMainNode = jsonResponse
									.optJSONArray("ResultSet");
							System.out.println("OKAY_8!!");
							/*********** Process each JSON Node ***********/

							int lengthJsonArr = jsonMainNode.length();

							for (int i = 0; i < lengthJsonArr; i++) {
								System.out.println("OKAY_9!!");
								/****** Get Object for each JSON node. ***********/
								JSONObject jsonChildNode = jsonMainNode
										.getJSONObject(i);

								/******* Fetch node values **********/
								String email = jsonChildNode.optString(
										"emailId").toString();
								String pwd = jsonChildNode.optString("pwd")
										.toString();

								String firstname = jsonChildNode.optString(
										"firstName").toString();
								String lastname = jsonChildNode.optString(
										"lastName").toString();

								System.out.println("OKAY_10!!");
								OutputData += "Node : \n\n     " + email
										+ " | " + pwd + " | " + firstname
										+ " | " + lastname + " \n\n ";
								// Log.i("JSON parse", song_name);
							}

							/************ Show Output on screen/activity **********/
							System.out.println("OKAY_11!!" + OutputData);

							// startActivity(intent1);
						} catch (JSONException e) {

							e.printStackTrace();
						}

						/* just try end */

						System.out.println("OKAY_settext_inner" + OutputData);

					}

				}.start();
				System.out.println("OKAY_settext_outer");

				// show the data in toast

				Toast.makeText(getApplicationContext(),
						"Selected: " + OutputData, Toast.LENGTH_LONG).show();
				// resultSetOutput.setText(OutputData);

				Toast.makeText(getApplicationContext(),
						"Submited Exam Detail Successfully ", Toast.LENGTH_LONG)
						.show();

			}
		});
		buttonExamReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				examId.setText("");
				examStartDate.setText("");
				examEndDate.setText("");
				examTime.setText("");
				examDescptn.setText("");

			}
		});
		buttonExamCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intents = new Intent(ExamSheetActivity.this,
						AfterAdminMainActivity.class);
				startActivity(intents);

			}
		});

	}

	DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			myCalendar.set(Calendar.YEAR, year);
			myCalendar.set(Calendar.MONTH, monthOfYear);
			myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

			updateLabel();

		}
	};
	DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view1, int year1, int monthOfYear1,
				int dayOfMonth1) {
			// TODO Auto-generated method stub
			myCalendar1.set(Calendar.YEAR, year1);
			myCalendar1.set(Calendar.MONTH, monthOfYear1);
			myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth1);

			updateLabel1();

		}
	};
	TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hour2, int mnt2) {
			// TODO Auto-generated method stub
			hr = hour2;
			mnt = mnt2;
			updateTime(hr, mnt);
		}

		private void updateTime(int hr, int mnt) {
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
			examTime.setText(setTime);


		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exam_sheet, menu);
		return true;
	}

	protected void updateLabel1() {
		// TODO Auto-generated method stub
		String myFormat1 = "MM/dd/yy"; // In which you need put here
		SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);

		examEndDate.setText(sdf1.format(myCalendar1.getTime()));

	}

	protected void updateLabel() {
		// TODO Auto-generated method stub
		String myFormat = "MM/dd/yy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		examStartDate.setText(sdf.format(myCalendar.getTime()));
	}

}
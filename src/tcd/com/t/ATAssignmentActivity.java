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
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class ATAssignmentActivity extends Activity {
	HttpResponse resp;
	Intent intent1;
	private String OutputData;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private String respStr;
	Calendar myCalendar, myCalendar1;
	EditText txtAssId, txtAssStartDate, txtAssEndDate, txtAssDes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atassignment);
		Button buttonASSSubmit = (Button) findViewById(R.id.buttonAssSubmit);
		Button buttonASSReset = (Button) findViewById(R.id.buttonAssReset);
		Button buttonASSCancel = (Button) findViewById(R.id.buttonAssCancel);

		txtAssId = (EditText) findViewById(R.id.editTextATAssID);
		txtAssStartDate = (EditText) findViewById(R.id.editTextAssStartDate);
		txtAssEndDate = (EditText) findViewById(R.id.editTextAssEndDate);
		txtAssDes = (EditText) findViewById(R.id.editTextAssDescription);
		// subject id = spinner element
		// type = spinner element
		myCalendar = Calendar.getInstance();
		txtAssStartDate = (EditText) findViewById(R.id.editTextAssStartDate);
		txtAssStartDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ATAssignmentActivity.this, date,
						myCalendar.get(Calendar.YEAR), myCalendar
								.get(Calendar.MONTH), myCalendar
								.get(Calendar.DAY_OF_MONTH)).show();

			}
		});
		myCalendar1 = Calendar.getInstance();
		txtAssEndDate = (EditText) findViewById(R.id.editTextAssEndDate);
		txtAssEndDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ATAssignmentActivity.this, date1,
						myCalendar1.get(Calendar.YEAR), myCalendar1
								.get(Calendar.MONTH), myCalendar1
								.get(Calendar.DAY_OF_MONTH)).show();

			}
		});

		buttonASSSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("onClick!!!");
				findViewById(R.id.editTextLUsername);
				findViewById(R.id.editTextLPwd);

				// Spinner element
				new Thread() {
					public void run() {
						// TODO Run network requests here.

						System.out
								.println("*********txtId.getText().toString()::"
										+ txtAssId.getText().toString()
										+ txtAssStartDate.getText().toString()
										+ txtAssEndDate.getText().toString()
										+ txtAssDes.getText().toString());

						HttpClient httpClient = new DefaultHttpClient();
						// HttpPost post = new
						// HttpPost("http://samidha.org/restTrials/login/");
						HttpPost post = new HttpPost(
								"http://115.111.105.152/schoolApp/assignment_testEntry");
						post.setHeader("content-type",
								"application/json; charset=UTF-8");

						// Construimos el objeto cliente en formato JSON
						JSONObject dato = new JSONObject();

						try {

							/*
							 payload
							  {
	        "AssId": "Ass201213002",
	        "SubjectId": "Sub_English",
	        "StartDate": "2013-06-10",
	        "EndDate": "2013-06-20",
	        "Type": "Assignment",
	        "Description": "English synonyms"
	    }, */
							dato.put("AssId", txtAssId.getText().toString());
							dato.put("StartDate", txtAssStartDate.getText().toString());
							dato.put("EndDate", txtAssEndDate.getText().toString());
							dato.put("Description", txtAssDes.getText().toString());
							//this is for subject id
							//dato.put("AssId", txtId.getText().toString());
							
							//class spinner element
							//dato.put("AssId", txtId.getText().toString());
							
							//division spinner element
							//dato.put("AssId", txtId.getText().toString());
							
							//types spinner element
							//dato.put("AssId", txtId.getText().toString());
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
						"Submited Assignment Detail Successfully ",
						Toast.LENGTH_LONG).show();

			}
		});
		buttonASSReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				txtAssId.setText("");
				txtAssEndDate.setText("");
				txtAssStartDate.setText("");
				txtAssDes.setText("");

			}
		});
		buttonASSCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intents = new Intent(ATAssignmentActivity.this,
						AfterTeacherLoginActivity.class);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.atassignment, menu);
		return true;
	}

	protected void updateLabel1() {
		// TODO Auto-generated method stub
		String myFormat1 = "MM/dd/yy"; // In which you need put here
		SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);

		txtAssEndDate.setText(sdf1.format(myCalendar1.getTime()));

	}

	protected void updateLabel() {
		// TODO Auto-generated method stub
		String myFormat = "MM/dd/yy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		txtAssStartDate.setText(sdf.format(myCalendar.getTime()));

	}

}

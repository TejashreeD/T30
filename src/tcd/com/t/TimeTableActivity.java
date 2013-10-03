package tcd.com.t;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TimeTableActivity extends Activity implements
		OnItemSelectedListener {
	HttpResponse resp;
	Intent intent1;
	private String OutputData;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private String respStr;
	private TextView resultSetOutput;

	EditText ExamDate;
	Calendar myCalendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_table);

		/**
		 * About Spinner
		 */
		// Spinner element
		Spinner spinner = (Spinner) findViewById(R.id.spinnerETTCDIDspinner);

		// Spinner click listener
		spinner.setOnItemSelectedListener(this);

		// Spinner Drop down elements
		List<String> categories = new ArrayList<String>();
		categories.add("1A");
		categories.add("1B");
		categories.add("2A");
		categories.add("2B");
		// categories.add("Student");

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, categories);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);

		Button btnSubmit = (Button) findViewById(R.id.buttonETTSubmit);
		Button btnReset = (Button) findViewById(R.id.buttonETReset);
		Button btnCancel = (Button) findViewById(R.id.buttonETCancel);
		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(TimeTableActivity.this,
						AfterAdminMainActivity.class);
				startActivity(intent);
			}
		});

		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("onClick!!!");
				final EditText txtId = (EditText) findViewById(R.id.editTextLUsername);
				final EditText txtWord = (EditText) findViewById(R.id.editTextLPwd);

				// Spinner element
				new Thread() {
					private Context Context;

					public void run() {
						// TODO Run network requests here.
						EditText txtId = (EditText) findViewById(R.id.editTextLUsername);
						EditText txtWord = (EditText) findViewById(R.id.editTextLPwd);
						resultSetOutput = (TextView) findViewById(R.id.textViewOutput);
						System.out
								.println("*********txtId.getText().toString()::"
										+ txtId.getText().toString()
										+ txtWord.getText().toString());

						HttpClient httpClient = new DefaultHttpClient();
						// HttpPost post = new
						// HttpPost("http://samidha.org/restTrials/login/");
						HttpPost post = new HttpPost(
								"http://115.111.105.152/schoolApp/login");
						post.setHeader("content-type",
								"application/json; charset=UTF-8");

						// Construimos el objeto cliente en formato JSON
						JSONObject dato = new JSONObject();

						try {

							// Toast.makeText(getBaseContext(), "trying!!!",
							// Toast.LENGTH_SHORT).show();

							dato.put("email", txtId.getText().toString());
							dato.put("pwd", txtWord.getText().toString());
							// dato.put("type",
							// txtDescription.getText().toString());

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
		btnReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				EditText txtEid = (EditText) findViewById(R.id.editTextExamtimeTableID);
				EditText txtEdate = (EditText) findViewById(R.id.editTextExamDate);
				txtEid.setText("");
				txtEdate.setText("");

			}
		});

		ExamDate = (EditText) findViewById(R.id.editTextExamDate);
		ExamDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				myCalendar = Calendar.getInstance();
				new DatePickerDialog(TimeTableActivity.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();

			}
		});

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		// On selecting a spinner item
		String item = parent.getItemAtPosition(pos).toString();

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

	protected void updateLabel() {
		// TODO Auto-generated method stub
		String myFormat = "MM/dd/yy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		ExamDate.setText(sdf.format(myCalendar.getTime()));

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

}

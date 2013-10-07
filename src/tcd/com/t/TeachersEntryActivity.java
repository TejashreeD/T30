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
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TeachersEntryActivity extends Activity {
	HttpResponse resp;
	Intent intent1;
	private String OutputData;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	String respStr;
	TextView resultSetOutput;
	EditText txtSchId, txtFirstName, txtMiddleName, txtLastName,
			txtDesignation, txtAddress, txtEmail, txtQualification, txtBDate,
			txtPwd;

	EditText DOB;
	Calendar myCalendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teachers_entry);
		Button buttonPSubmit = (Button) findViewById(R.id.buttonPSubmit);
		Button buttonPReset = (Button) findViewById(R.id.buttonPReset);
		Button buttonPCancel = (Button) findViewById(R.id.buttonASCancel);

		txtSchId = (EditText) findViewById(R.id.editTextPSchId);
		txtFirstName = (EditText) findViewById(R.id.editTextPFirstName);
		txtMiddleName = (EditText) findViewById(R.id.editTextPMiddleName);
		txtLastName = (EditText) findViewById(R.id.editTextPLastName);
		txtDesignation = (EditText) findViewById(R.id.editTextPDesignation);
		txtAddress = (EditText) findViewById(R.id.editTextPAddress);
		txtEmail = (EditText) findViewById(R.id.editTextPEmail);
		txtQualification = (EditText) findViewById(R.id.editTextPQualification);
		txtBDate = (EditText) findViewById(R.id.editTextPDate);
		txtPwd = (EditText) findViewById(R.id.editTextPPassword);

		myCalendar = Calendar.getInstance();

		txtBDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				new DatePickerDialog(TeachersEntryActivity.this, date,
						myCalendar.get(Calendar.YEAR), myCalendar
								.get(Calendar.MONTH), myCalendar
								.get(Calendar.DAY_OF_MONTH)).show();

			}
		});
		buttonPReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				txtSchId.setText("");
				txtFirstName.setText("");
				txtMiddleName.setText("");
				txtLastName.setText("");
				txtDesignation.setText("");
				txtAddress.setText("");
				txtEmail.setText("");
				txtQualification.setText("");
				txtPwd.setText("");
				txtBDate.setText("");
			}
		});
		buttonPCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(TeachersEntryActivity.this,
						AfterAdminMainActivity.class).putExtra("from",
						"AfterAdminMainActivity");
				if (getIntent().getStringExtra("from").equals(
						"AfterAdminMainActivity")) {
					Intent intent1 = new Intent(TeachersEntryActivity.this,
							AfterAdminMainActivity.class);
					startActivity(intent1);

				} else {
					Intent intent2 = new Intent(TeachersEntryActivity.this,
							AfterSuperAdminLoginActivity.class);
					startActivity(intent2);
				}

			}
		});
		buttonPSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("onClick!!!");

				// Spinner element
				new Thread() {
					private Context Context;

					public void run() {
						// TODO Run network requests here.
						System.out
								.println("*********txtId.getText().toString()::"
										+ txtSchId.getText().toString()
										+ txtFirstName.getText().toString()
										+ txtMiddleName.getText().toString()
										+ txtDesignation.getText().toString()
										+ txtAddress.getText().toString()
										+ txtEmail.getText().toString()
										+ txtPwd.getText().toString()
										+ txtBDate.getText().toString()
										+ txtQualification.getText().toString());

						HttpClient httpClient = new DefaultHttpClient();
						// HttpPost post = new
						// HttpPost("http://samidha.org/restTrials/login/");
						HttpPost post = new HttpPost(
								"http://115.111.105.152/schoolApp/personEntry");
						post.setHeader("content-type",
								"application/json; charset=UTF-8");

						// Construimos el objeto cliente en formato JSON
						JSONObject dato = new JSONObject();

						try {
							dato.put("schoolId", txtSchId.getText().toString());
							dato.put("firstName", txtFirstName.getText()
									.toString());
							dato.put("middleName", txtMiddleName.getText()
									.toString());
							dato.put("designation", txtDesignation.getText()
									.toString());
							dato.put("address", txtAddress.getText().toString());
							dato.put("emailId", txtEmail.getText().toString());
							dato.put("pwd", txtPwd.getText().toString());
							dato.put("DOB", txtBDate.getText().toString());
							dato.put("pid_adminId", "null");

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
					}

				}.start();
				System.out.println("OKAY_settext_outer");

				// show the data in toast

				Toast.makeText(getApplicationContext(),
						"Selected: " + OutputData, Toast.LENGTH_LONG).show();
				// resultSetOutput.setText(OutputData);

				Toast.makeText(getApplicationContext(),
						"Submited Successfully ", Toast.LENGTH_LONG).show();
			}
		});
	}

	private void updateLabel() {
		// TODO Auto-generated method stub
		String myFormat = "MM/dd/yy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		DOB.setText(sdf.format(myCalendar.getTime()));

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.teachers_entry, menu);
		return true;
	}

}

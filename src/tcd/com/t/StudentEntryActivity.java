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
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class StudentEntryActivity extends Activity {
	HttpResponse resp;
	Intent intent1;
	private String OutputData;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	EditText studPrn;
	EditText fName;
	EditText SDOB;
	Calendar myCalendar;
	protected EditText mName;
	protected EditText lName;
	protected EditText email;
	protected EditText bDate, pid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_entry);
		Button buttonCancel = (Button) findViewById(R.id.buttonASCancel);
		Button buttonReset = (Button) findViewById(R.id.buttonASReset);
		Button buttonSSubmit = (Button) findViewById(R.id.buttonASSubmit);

		studPrn = (EditText) findViewById(R.id.editTextStudPRN);
		fName = (EditText) findViewById(R.id.editTextStudFname);
		mName = (EditText) findViewById(R.id.editTextStudMname);
		lName = (EditText) findViewById(R.id.editTextStudLname);
		email = (EditText) findViewById(R.id.editTextStudEmail);
		bDate = (EditText) findViewById(R.id.editTextStudBday);
		pid = (EditText) findViewById(R.id.editTextStudPid);
		buttonCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StudentEntryActivity.this,
						AfterAdminMainActivity.class);
				startActivity(intent);
			}
		});

		buttonReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				studPrn.setText("");
				bDate.setText("");
				fName.setText("");
				mName.setText("");
				lName.setText("");
				email.setText("");
				pid.setText("");
			}
		});
		buttonSSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("onClick!!!");

				// Spinner element
				new Thread() {
					public void run() {
						// TODO Run network requests here.

						System.out
								.println("*********txtId.getText().toString()::"
										+ studPrn.getText().toString()
										+ bDate.getText().toString()
										+ fName.getText().toString()
										+ mName.getText().toString()
										+ lName.getText().toString()
										+ email.getText().toString()
										+ pid.getText().toString());

						HttpClient httpClient = new DefaultHttpClient();
						// HttpPost post = new
						// HttpPost("http://samidha.org/restTrials/login/");
						HttpPost post = new HttpPost(
								"http://115.111.105.152/schoolApp/studentEntry");
						post.setHeader("content-type",
								"application/json; charset=UTF-8");

						// Construimos el objeto cliente en formato JSON
						JSONObject dato = new JSONObject();

						try {
							dato.put("PRN", studPrn.getText().toString());
							dato.put("firstName", fName.getText().toString());
							dato.put("middleName", mName.getText().toString());
							dato.put("lastName", lName.getText().toString());
							dato.put("DOB", bDate.getText().toString());
							dato.put("emailId", email.getText().toString());
							dato.put("pid", pid.getText().toString());
							System.out.println("OKAY_0!!");

							StringEntity entity = new StringEntity(dato
									.toString());
							System.out.println("OKAY_1!!");
							post.setEntity(entity);
							System.out.println("OKAY_2!!");

							httpClient.execute(post);
							System.out.println("OKAY_3!!");
							//respStr = EntityUtils.toString(resp.getEntity());
							System.out.println("OKAY_4!!");

							//sSystem.out.println("OKAY_5!!" + respStr);
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
						"Submited Student Detail Successfully ",
						Toast.LENGTH_LONG).show();
			}
		});

		myCalendar = Calendar.getInstance();

		bDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(StudentEntryActivity.this, date,
						myCalendar.get(Calendar.YEAR), myCalendar
								.get(Calendar.MONTH), myCalendar
								.get(Calendar.DAY_OF_MONTH)).show();

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

		private void updateLabel() {
			// TODO Auto-generated method stub
			String myFormat = "MM/dd/yy"; // In which you need put here
			SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

			bDate.setText(sdf.format(myCalendar.getTime()));

		}

	};

}

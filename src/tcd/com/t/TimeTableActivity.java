package tcd.com.t;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;




import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TimeTableActivity extends Activity{
	

	HttpResponse resp;
	Intent intent1;
	private String OutputData;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private String respStr;
	EditText ExamDate,timeTableId,txtEid,txtEdate,txtTime,txtDesc;
	Calendar myCalendar;
	String itemClass,itemDivision,itemSubject;
	Spinner spinnerClass,spinnerDivision,spinnerType, spinnerSubject;
	List<String> classNames,types;
	List<String> DivisionId = new ArrayList<String>();
	Button btnSubmit ,btnReset,btnCancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_table);

		testAsynch MyTask1 = new testAsynch();
		MyTask1.execute();
		testAsynch2 MyTask2 = new testAsynch2();
		MyTask2.execute();
		testAsynch3 MyTask3 = new testAsynch3();
		MyTask3.execute();
		txtEdate=(EditText) findViewById(R.id.editTextExamEndDate);
		txtTime=(EditText) findViewById(R.id.editTextExamTime);
		txtDesc=(EditText) findViewById(R.id.editTextDesc);
		ExamDate = (EditText) findViewById(R.id.editTextExamDate);
		timeTableId=(EditText) findViewById(R.id.editTextExamtimeTableID);
		
		btnSubmit = (Button) findViewById(R.id.buttonETTSubmit);
		btnReset = (Button) findViewById(R.id.buttonETReset);
		btnCancel = (Button) findViewById(R.id.buttonETCancel);
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

				// Spinner element
				new Thread() {
					public void run() {
						// TODO Run network requests here.
						
						System.out
								.println("*********txtId.getText().toString()::"
										+ timeTableId.getText().toString()
										+ txtEdate.getText().toString()
										+ txtEid.getText().toString()
										+ ExamDate.getText().toString()
										+ txtDesc.getText().toString()
										+ txtTime.getText().toString());

						HttpClient httpClient = new DefaultHttpClient();
						HttpPost post = new HttpPost(
								"http://115.111.105.152/schoolApp/examttEntry");
						post.setHeader("content-type",
								"application/json; charset=UTF-8");

						// Construimos el objeto cliente en formato JSON
						JSONObject dato = new JSONObject();

						try {
							String cdid="spinnerClass"+"spinnerDivision";
							dato.put("ETTId", timeTableId.getText().toString());
							dato.put("Date", ExamDate.getText().toString());
							dato.put("endDate", txtEdate.getText().toString());
							dato.put("SubjectId", spinnerSubject);
							dato.put("Time", txtTime.getText().toString());
							dato.put("CDId",cdid);
							dato.put("TestType", spinnerType);
							dato.put("Description", txtDesc.getText().toString());
							
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
				txtEid.setText("");
				txtEdate.setText("");

			}
		});

		
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
		txtEdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				myCalendar = Calendar.getInstance();
				new DatePickerDialog(TimeTableActivity.this, date1, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

	}

	class testAsynch extends AsyncTask<Void,Integer,String> implements OnItemSelectedListener
	{
		String readFeed;
		JSONObject json;
		StringBuilder builder = new StringBuilder();

		protected void onPreExecute(){
                Log.d("PreExceute","On pre Exceute......");
        }
                
        protected String doInBackground(Void...arg0) {

            Log.d("DoINBackGround","On doInBackground...");
            StringBuilder builder = new StringBuilder();
            HttpClient client = new DefaultHttpClient();

            // domain intentionally obfuscated for security reasons
			HttpGet httpGet = new HttpGet(
					"http://115.111.105.152/schoolApp/allClasses");
			httpGet.setHeader("content-type", "application/json; charset=UTF-8");
            try 
            {
				HttpResponse response = client.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				if (statusCode == 200) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
				} else {
					Log.d("onProgressUpdate","Failed to download file..........");
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}
            return builder.toString();
        }
            
		protected void onProgressUpdate(Integer...a){ 
			Log.d("onProgressUpdate","U r in Progress Update.........."+a[0]);
		}
	
		protected void onPostExecute(String result) {
			String spinnerElement;
			List<String> classNames = new ArrayList<String>();
			setContentView(R.layout.activity_time_table);
			spinnerClass = (Spinner) findViewById(R.id.spinnerETTCDIDspinner);
		  
			Log.d("onPostExecute",""+result);
			/* try */
			try {
				readFeed = result;
				json = new JSONObject(readFeed);
				JSONArray jsonArray = new JSONArray(
						json.optString("ResultSet"));
				Log.i(TimeTableActivity.class.getName(), "Number of entries "
						+ jsonArray.length());

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					spinnerElement = jsonObject.optString("classId");
					System.out.println("**************************" + spinnerElement);
					classNames.add(spinnerElement);
					System.out.print(" add elements ok");
				}
				
				spinnerClass.setOnItemSelectedListener(this);
				ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
						android.R.layout.simple_spinner_item, classNames);
				
				// Drop down layout style - list view with radio button
				dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				// attaching data adapter to spinner
				spinnerClass.setAdapter(dataAdapter);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		  
		}

		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.time_table, menu);
			return true;
		}

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			
			System.out.println("******************onItemSelected");
			// TODO Auto-generated method stub
			itemClass = parent.getItemAtPosition(position).toString();
			Toast.makeText(parent.getContext(), "Selected: " + itemClass,
					Toast.LENGTH_LONG).show();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
		
		
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

	protected void updateLabe2() {
		// TODO Auto-generated method stub
		String myFormat = "MM/dd/yy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		ExamDate.setText(sdf.format(myCalendar.getTime()));

	}

	DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			myCalendar.set(Calendar.YEAR, year);
			myCalendar.set(Calendar.MONTH, monthOfYear);
			myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateLabe2();
		}

	};
	class testAsynch2 extends AsyncTask<Void,Integer,String> implements OnItemSelectedListener
		 {

		String readFeed;
		JSONObject json;
		StringBuilder builder = new StringBuilder();

		protected void onPreExecute(){
                Log.d("PreExceute","On pre Exceute......");
        }
                
        

		protected String doInBackground(Void...arg0) {

            Log.d("DoINBackGround","On doInBackground...");
            StringBuilder builder = new StringBuilder();
            HttpClient client = new DefaultHttpClient();

            // domain intentionally obfuscated for security reasons
			HttpGet httpGet = new HttpGet(
					"http://115.111.105.152/schoolApp/allDivision");
			httpGet.setHeader("content-type", "application/json; charset=UTF-8");
            try 
            {
				HttpResponse response = client.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				if (statusCode == 200) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
				} else {
					Log.d("onProgressUpdate","Failed to download file..........");
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}
            return builder.toString();
        }
            
		protected void onProgressUpdate(Integer...a){ 
			Log.d("onProgressUpdate","U r in Progress Update.........."+a[0]);
		}
	
		protected void onPostExecute(String result) {
			String spinnerElement;
			setContentView(R.layout.activity_time_table);
			spinnerDivision= (Spinner) findViewById(R.id.spinnerDivision);
		  
			Log.d("onPostExecute",""+result);
			/* try */
			try {
				readFeed = result;
				json = new JSONObject(readFeed);
				JSONArray jsonArray = new JSONArray(
						json.optString("ResultSet"));
				Log.i(TimeTableActivity.class.getName(), "Number of entries "
						+ jsonArray.length());

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					spinnerElement = jsonObject.optString("divId");
					System.out.println("**************************" + spinnerElement);
					DivisionId.add(spinnerElement);
					System.out.print(" add elements ok");
				}
				
				spinnerDivision.setOnItemSelectedListener(this);
				ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
						android.R.layout.simple_spinner_item, DivisionId);
				
				// Drop down layout style - list view with radio button
				dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				// attaching data adapter to spinner
				spinnerDivision.setAdapter(dataAdapter);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		  
		}

		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.time_table, menu);
			return true;
		}

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			
			System.out.println("******************onItemSelected");
			// TODO Auto-generated method stub
			itemDivision = parent.getItemAtPosition(position).toString();
			Toast.makeText(parent.getContext(), "Selected: " + itemDivision,
					Toast.LENGTH_LONG).show();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	}


	class testAsynch3 extends AsyncTask<Void,Integer,String> implements OnItemSelectedListener
	 {

	String readFeed;
	JSONObject json;
	StringBuilder builder = new StringBuilder();

	protected void onPreExecute(){
           Log.d("PreExceute","On pre Exceute......");
   }
           
   

	protected String doInBackground(Void...arg0) {

       Log.d("DoINBackGround","On doInBackground...");
       StringBuilder builder = new StringBuilder();
       HttpClient client = new DefaultHttpClient();

       // domain intentionally obfuscated for security reasons
		HttpGet httpGet = new HttpGet(
				"http://115.111.105.152/schoolApp/allsubjectId");
		httpGet.setHeader("content-type", "application/json; charset=UTF-8");
       try 
       {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.d("onProgressUpdate","Failed to download file..........");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
       return builder.toString();
   }
       
	protected void onProgressUpdate(Integer...a){ 
		Log.d("onProgressUpdate","U r in Progress Update.........."+a[0]);
	}

	protected void onPostExecute(String result) {
		String spinnerElement;
		setContentView(R.layout.activity_time_table);
		spinnerType= (Spinner) findViewById(R.id.spinnerSTestType);
	  
		Log.d("onPostExecute",""+result);
		/* try */
		try {
			readFeed = result;
			json = new JSONObject(readFeed);
			JSONArray jsonArray = new JSONArray(
					json.optString("ResultSet"));
			Log.i(TimeTableActivity.class.getName(), "Number of entries "
					+ jsonArray.length());

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				spinnerElement = jsonObject.optString("subId");
				System.out.println("**************************" + spinnerElement);
				DivisionId.add(spinnerElement);
				System.out.print(" add elements ok");
			}
			
			spinnerType.setOnItemSelectedListener(this);
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
					android.R.layout.simple_spinner_item, types);
			
			// Drop down layout style - list view with radio button
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			// attaching data adapter to spinner
			spinnerType.setAdapter(dataAdapter);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	  
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_table, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		
		System.out.println("******************onItemSelected");
		// TODO Auto-generated method stub
		itemSubject = parent.getItemAtPosition(position).toString();
		Toast.makeText(parent.getContext(), "Selected: " + itemSubject,
				Toast.LENGTH_LONG).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
}

	
}

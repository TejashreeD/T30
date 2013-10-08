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
import org.json.JSONException;
import org.json.JSONObject;

import tcd.com.t.TimeTableActivity.testAsynch;
import tcd.com.t.TimeTableActivity.testAsynch2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ACalenderActivity extends Activity {
	final static int TIME = 1111;
	Calendar calendar = Calendar.getInstance();
	int hr = calendar.get(Calendar.HOUR_OF_DAY);
	int mnt = calendar.get(Calendar.MINUTE);
	EditText txtEventTime;
	String itemClass,itemDivision,itemSubject;
	Spinner spinnerClass,spinnerDivision,spinnerType, spinnerSubject;
	List<String> classNames,types;
	List<String> DivisionId = new ArrayList<String>();
	HttpResponse resp;
	Intent intent1;
	private String OutputData;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private String respStr;
	EditText CDOB;
	Calendar myCalendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		testAsynch MyTask1 = new testAsynch();
		MyTask1.execute();
		testAsynch2 MyTask2 = new testAsynch2();
		MyTask2.execute();
	
		setContentView(R.layout.activity_acalender);
		Button buttonCAdd=(Button) findViewById(R.id.buttonCalenderAdd);
		Button buttonCUpdate=(Button) findViewById(R.id.buttonUpdate);
		Button buttonCReset=(Button) findViewById(R.id.buttonCalenderReset);
		Button buttonCCancel=(Button) findViewById(R.id.buttonCalenderCancel);
		 txtEventTime=(EditText) findViewById(R.id.editTextETime);
		
		Time time = new Time(Time.getCurrentTimezone());

		time.setToNow();
		
	txtEventTime.setText(time.format("%H:%M"));

		txtEventTime.setOnClickListener(new OnClickListener() {
			
			private OnTimeSetListener timeSetListener;

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new TimePickerDialog(ACalenderActivity.this, timeSetListener, hr,
						mnt, false).show();

			}
		});
		myCalendar = Calendar.getInstance();
		CDOB = (EditText) findViewById(R.id.editTextCDate);
CDOB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ACalenderActivity.this, date,
						myCalendar.get(Calendar.YEAR), myCalendar
								.get(Calendar.MONTH), myCalendar
								.get(Calendar.DAY_OF_MONTH)).show();


				
			}
		});

		buttonCCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(ACalenderActivity.this, AfterAdminMainActivity.class);
				startActivity(intent);
			}
		});
		
	
		buttonCAdd.setOnClickListener(new OnClickListener() {
			
		

			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				System.out.println("onClick!!!");
					
				final EditText txtEventId=(EditText) findViewById(R.id.editTextAEventID);
				final EditText txtEventName=(EditText) findViewById(R.id.editTextAEventName);
				final EditText txtEventDate=(EditText) findViewById(R.id.editTextCDate);
				final EditText txtEventTime=(EditText) findViewById(R.id.editTextETime);
			
		// Spinner element
				new Thread() {
	                public void run() {
	                    // TODO Run network requests here.
	                                //  resultSetOutput = (TextView) findViewById(R.id.textViewOutput);
	                    System.out
	                            .println("*********txtId.getText().toString()::"
	                                    + txtEventId.getText().toString()
	                                    + txtEventName.getText().toString()
	                                    + txtEventDate.getText().toString()
	                                    + txtEventTime.getText().toString());

	                    HttpClient httpClient = new DefaultHttpClient();
	                    // HttpPost post = new
	                    // HttpPost("http://samidha.org/restTrials/login/");
	                    HttpPost post = new HttpPost(
	                            "http://115.111.105.152/schoolApp/calenderEntry");
	                    post.setHeader("content-type",
	                            "application/json; charset=UTF-8");

	                    // Construimos el objeto cliente en formato JSON
	                    JSONObject dato = new JSONObject();

	                    try {

	                        // Toast.makeText(getBaseContext(), "trying!!!",
	                        // Toast.LENGTH_SHORT).show();

	                        dato.put("EventId", txtEventId.getText().toString());
	                        dato.put("Ename", txtEventName.getText().toString());
	                        dato.put("Date", txtEventDate.getText().toString());
	                        dato.put("Time", txtEventTime.getText().toString());
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
	                       // Log.e("MYAPP", "exception", exception);
	                        exception.printStackTrace();
	                        
	                    }
	                    
	                    /* just try */
	                                       
	                }

	            }.start();
	System.out.println("OKAY_settext_outer");
            // show the data in toast
            
		//resultSetOutput.setText(OutputData);


		Toast.makeText( getApplicationContext(), "Submited Caldnder Successfully ",Toast.LENGTH_LONG).show();
				
			}
		});
		buttonCUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("onClick!!!");
				// Toast.makeText(getBaseContext(), "onClick!!!",
				// Toast.LENGTH_SHORT).show();

			// Spinner element
				new Thread() {
                public void run() {
                    // TODO Run network requests here.
                    EditText txtId = (EditText) findViewById(R.id.editTextLUsername);
                    EditText txtWord = (EditText) findViewById(R.id.editTextLPwd);
                   
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
            
			//Toast.makeText(getApplicationContext(),
          //          "Selected: " + OutputData, Toast.LENGTH_LONG).show();
//resultSetOutput.setText(OutputData);

				
				Toast.makeText( getApplicationContext(), "Calender Updated Successfully ",Toast.LENGTH_LONG).show();
			}
		});
		buttonCReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText txtEventId=(EditText) findViewById(R.id.editTextAEventID);
				EditText txtEventName=(EditText) findViewById(R.id.editTextAEventName);
				EditText txtEventDate=(EditText) findViewById(R.id.editTextCDate);
				EditText txtEventTime=(EditText) findViewById(R.id.editTextETime);
				txtEventId.setText("");
				txtEventName.setText("");
				txtEventDate.setText("");
				txtEventTime.setText("");
			}
			
		});
		buttonCCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ACalenderActivity.this,AfterAdminMainActivity.class);
				startActivity(intent);
				
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
		getMenuInflater().inflate(R.menu.acalender, menu);
		return true;
	}




	protected void updateTime(int hr2, int mnt2) {
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
		txtEventTime.setText(setTime);
		
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
			setContentView(R.layout.activity_acalender);
			spinnerClass = (Spinner) findViewById(R.id.spinnerCalenderCDID);
		  
			Log.d("onPostExecute",""+result);
			/* try */
			try {
				readFeed = result;
				json = new JSONObject(readFeed);
				JSONArray jsonArray = new JSONArray(
						json.optString("ResultSet"));
				Log.i(ACalenderActivity.class.getName(), "Number of entries "
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
			getMenuInflater().inflate(R.menu.acalender, menu);
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
			setContentView(R.layout.activity_acalender);
			spinnerDivision= (Spinner) findViewById(R.id.spinnerCalenderCDIDDiv);
		  
			Log.d("onPostExecute",""+result);
			/* try */
			try {
				readFeed = result;
				json = new JSONObject(readFeed);
				JSONArray jsonArray = new JSONArray(
						json.optString("ResultSet"));
				Log.i(ACalenderActivity.class.getName(), "Number of entries "
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
			getMenuInflater().inflate(R.menu.acalender, menu);
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


		
	
	
	protected void updateLabel() {
		// TODO Auto-generated method stub
		String myFormat = "MM/dd/yy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		CDOB.setText(sdf.format(myCalendar.getTime()));

		
	}

}

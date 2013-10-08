package tcd.com.t;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class TestReportActivity extends Activity {

	Spinner spinnerClass, spinnerDivision;
	String itemClass, itemDivision;
	List<String> classNames = new ArrayList<String>();
	List<String> DivisionId = new ArrayList<String>();
	TextView classId;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_report);
		testAsynch2 MyTask2 = new testAsynch2();
		MyTask2.execute();
		testAsynch MyTask1 = new testAsynch();
		MyTask1.execute();
				
		
	}

	class testAsynch extends AsyncTask<Void, Integer, String> implements
			OnItemSelectedListener {
		String readFeed;
		JSONObject json;
		StringBuilder builder = new StringBuilder();

		protected void onPreExecute() {
			Log.d("PreExceute", "On pre Exceute......");
		}

		protected String doInBackground(Void... arg0) {

			Log.d("DoINBackGround", "On doInBackground...");
			StringBuilder builder = new StringBuilder();
			HttpClient client = new DefaultHttpClient();

			// domain intentionally obfuscated for security reasons
			HttpGet httpGet = new HttpGet(
					"http://115.111.105.152/schoolApp/allClasses");
			httpGet.setHeader("content-type", "application/json; charset=UTF-8");
			try {
				HttpResponse response = client.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				if (statusCode == 200) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
				} else {
					Log.d("onProgressUpdate",
							"Failed to download file..........");
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return builder.toString();
		}

		protected void onProgressUpdate(Integer... a) {
			Log.d("onProgressUpdate", "U r in Progress Update.........." + a[0]);
		}

		protected void onPostExecute(String result) {
			String spinnerElement;
			List<String> classNames = new ArrayList<String>();
			setContentView(R.layout.activity_test_report);
			spinnerClass = (Spinner) findViewById(R.id.spinnerExamClass);

			Log.d("onPostExecute", "" + result);
			/* try */
			try {
				readFeed = result;
				json = new JSONObject(readFeed);
				JSONArray jsonArray = new JSONArray(json.optString("ResultSet"));
				Log.i(TestReportActivity.class.getName(), "Number of entries "
						+ jsonArray.length());

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					spinnerElement = jsonObject.optString("classId");
					System.out.println("**************************"
							+ spinnerElement);
					classNames.add(spinnerElement);
					System.out.print(" add elements ok");
				}

				spinnerClass.setOnItemSelectedListener(this);
				ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
						getApplicationContext(),
						android.R.layout.simple_spinner_item, classNames);

				// Drop down layout style - list view with radio button
				dataAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				// attaching data adapter to spinner
				spinnerClass.setAdapter(dataAdapter);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is
			// present.
			getMenuInflater().inflate(R.menu.test_report, menu);
			return true;
		}

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {

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

	class testAsynch2 extends AsyncTask<Void, Integer, String> implements
			OnItemSelectedListener {

		String readFeed;
		JSONObject json;
		StringBuilder builder = new StringBuilder();

		protected void onPreExecute() {
			Log.d("PreExceute", "On pre Exceute......");
		}

		protected String doInBackground(Void... arg0) {

			Log.d("DoINBackGround", "On doInBackground...");
			StringBuilder builder = new StringBuilder();
			HttpClient client = new DefaultHttpClient();

			// domain intentionally obfuscated for security reasons
			HttpGet httpGet = new HttpGet(
					"http://115.111.105.152/schoolApp/allDivision");
			httpGet.setHeader("content-type", "application/json; charset=UTF-8");
			try {
				HttpResponse response = client.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				if (statusCode == 200) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
				} else {
					Log.d("onProgressUpdate",
							"Failed to download file..........");
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return builder.toString();
		}

		protected void onProgressUpdate(Integer... a) {
			Log.d("onProgressUpdate", "U r in Progress Update.........." + a[0]);
		}

		protected void onPostExecute(String result) {
			String spinnerElement;
			setContentView(R.layout.activity_test_report);
			spinnerDivision = (Spinner) findViewById(R.id.spinnerExamDivision);

			Log.d("onPostExecute", "" + result);
			/* try */
			try {
				readFeed = result;
				json = new JSONObject(readFeed);
				JSONArray jsonArray = new JSONArray(json.optString("ResultSet"));
				Log.i(TestReportActivity.class.getName(), "Number of entries "
						+ jsonArray.length());

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					spinnerElement = jsonObject.optString("divId");
					System.out.println("**************************"
							+ spinnerElement);
					DivisionId.add(spinnerElement);
					System.out.print(" add elements ok");
				}

				spinnerDivision.setOnItemSelectedListener(this);
				ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
						getApplicationContext(),
						android.R.layout.simple_spinner_item, DivisionId);

				// Drop down layout style - list view with radio button
				dataAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				// attaching data adapter to spinner
				spinnerDivision.setAdapter(dataAdapter);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is
			// present.
			getMenuInflater().inflate(R.menu.test_report, menu);
			return true;
		}

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_report, menu);
		return true;
	}

}

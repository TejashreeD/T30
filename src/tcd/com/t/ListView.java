package tcd.com.t;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ListView extends Activity  {

	JSONObject jsonResponse;
	JSONArray jsonMainNode;
	String email, pwd, item;
	// Spinner Drop down elements
	List<String> Classes = new ArrayList<String>();
	List<String> list = new ArrayList<String>();
ListView lists;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		final Button GetServerData = (Button) findViewById(R.id.buttonTimatableGo);

		final Spinner spinner = (Spinner) findViewById(R.id.spinnerNew);
				// Spinner click listener
		//spinner.setOnItemSelectedListener(this);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				//Object obj=parent.getItemAtPosition(pos);
				//String obj=Classes.get(pos);
				//String obj=spinner.getAdapter().getItem(pos).toString();
				//String obj=(String) spinner.getAdapter().getItem(pos);
				
spinner.setSelection(1);
				//Toast.makeText(parent.getContext(), "Selected: " + obj,Toast.LENGTH_LONG).show();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Classes);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, Classes);
		spinner.setAdapter(adapter);
		GetServerData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// Server Request URL
				String serverURL = "http://115.111.105.152/schoolApp/allClasses";

				// Create Object and call AsyncTask execute Method
				new LongOperation().execute(serverURL);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view, menu);
		return true;
	}

	// Class with extends AsyncTask class
	private class LongOperation extends AsyncTask<String, Void, Void> {

		private final HttpClient Client = new DefaultHttpClient();
		private String Content;
		private String Error = null;
		private ProgressDialog Dialog = new ProgressDialog(ListView.this);

		TextView uiUpdate = (TextView) findViewById(R.id.textView1);

		protected void onPreExecute() {
			// NOTE: You can call UI Element here.

			// UI Element
			uiUpdate.setText("Output : ");
			Dialog.setMessage("Downloading source..");
			Dialog.show();
		}

		// Call after onPreExecute method
		protected Void doInBackground(String... urls) {
			try {

				// Call long running operations here (perform background
				// computation)
				// NOTE: Don't call UI Element here.

				// Server url call by GET method
				HttpGet httpget = new HttpGet(urls[0]);

				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				Content = Client.execute(httpget, responseHandler);
				/* tttttrrrryyyyyyyyyyyyyyyyyyy */

				try {
					jsonResponse = new JSONObject(Content);
					jsonMainNode = jsonResponse.optJSONArray("ResultSet");
					int lengthJsonArr = jsonMainNode.length();

					for (int i = 0; i < lengthJsonArr; i++) {
						System.out.println("OKAY_9!!");
						/****** Get Object for each JSON node. ***********/
						JSONObject jsonChildNode = jsonMainNode
								.getJSONObject(i);

						/******* Fetch node values **********/
						email = jsonChildNode.optString("className").toString();
						pwd = jsonChildNode.optString("classId").toString();
						System.out.println(email + pwd + "\n\n\n");
						//Classes.add(email);
						Classes.add(jsonChildNode.optString("className").toString());
						list.add(email);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/* tttttrrrryyyyyyyyyyyyyyyyyyy end */

			} catch (ClientProtocolException e) {
				Error = e.getMessage();
				cancel(true);
			} catch (IOException e) {
				Error = e.getMessage();
				cancel(true);
			}

			return null;
		}

		protected void onPostExecute(Void unused) {
			// NOTE: You can call UI Element here.

			// Close progress dialog
			Dialog.dismiss();

			if (Error != null) {

				uiUpdate.setText("Output : " + Error);

			} else {

				uiUpdate.setText("Output : " + Content);

			}
		}

	}
//
//	@Override
//	public void onItemSelected(AdapterView<?> parent, View view, int pos,
//			long id) {
//		// TODO Auto-generated method stub
//		Object obj=parent.getItemAtPosition(pos);
//		Toast.makeText(parent.getContext(), "Selected: " + obj,
//				Toast.LENGTH_LONG).show();
//		item = parent.getItemAtPosition(pos).toString();
//		Toast.makeText(parent.getContext(), "Selected: " + item,
//				Toast.LENGTH_LONG).show();
//	}
//
//	@Override
//	public void onNothingSelected(AdapterView<?> arg0) {
//		// TODO Auto-generated method stub
//
//	}
}

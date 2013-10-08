package tcd.com.t;

import java.io.IOException;

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
import android.widget.Button;
import android.widget.TextView;

public class AlarmReportActivity extends Activity {

	Button showAlarm;
	public JSONObject jsonResponse;
	public JSONArray jsonMainNode;
	TextView TextResult;
	String AlarmId,AlarmName,Food,Description,CDId,WholeResult;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_report);
		showAlarm = (Button) findViewById(R.id.buttonShowAllAlarm);
		TextResult=(TextView) findViewById(R.id.textViewResult);
		showAlarm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// call the thread to show the data
				// Server Request URL
				String serverURL = "http://115.111.105.152/schoolApp/allAlarm";

				// Create Object and call AsyncTask execute Method
				new LongOperation().execute(serverURL);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm_report, menu);
		return true;
	}

	// Thread which gives u all alarm ===> start
	private class LongOperation extends AsyncTask<String, Void, Void> {

		private final HttpClient Client = new DefaultHttpClient();
		private String Content;
		private String Error = null;
		private ProgressDialog Dialog = new ProgressDialog(
				AlarmReportActivity.this);

		//TextView uiUpdate = (TextView) findViewById(R.id.textView1);

		protected void onPreExecute() {
			// NOTE: You can call UI Element here.

			// UI Element
			TextResult.setText("Output : ");
			Dialog.setMessage("Downloading source..");
			Dialog.show();
		}

		// Call after onPreExecute method
		protected Void doInBackground(String... urls) {
			try {

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
						/* AlarmId,AlarmName,Food,Description,CDId;
						/******* Fetch node values **********/
						AlarmId = jsonChildNode.optString("AlarmId").toString();
						AlarmName = jsonChildNode.optString("AlarmName").toString();
						Description = jsonChildNode.optString("Description").toString();
						CDId = jsonChildNode.optString("CDId").toString();
						
						System.out.println(AlarmId + AlarmName +Description+CDId+ "\n\n\n");
						WholeResult=AlarmId + AlarmName +Description+CDId+"";
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

				TextResult.setText("Output : " + Error);

			} else {

				TextResult.setText("Output : " + Content + WholeResult);

			}
		}

	}// Thread which gives u all alarm ===> end

}

package tcd.com.t;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ATAlarmActivity extends Activity {
	HttpResponse resp;
	Intent intent1;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private String respStr;
	EditText txtEventId,txtEventName,txtEventDescription;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atalarm);

		Button buttonAlSubmit = (Button) findViewById(R.id.buttonATSubmitAss);
		Button buttonAlReset = (Button) findViewById(R.id.buttonATReset);
		Button buttonAlCancel = (Button) findViewById(R.id.buttonAttendCancel);
		txtEventId=(EditText) findViewById(R.id.editTextEventId);
		txtEventName=(EditText) findViewById(R.id.editTextEventName);
		txtEventDescription=(EditText) findViewById(R.id.editTextEventDescription);
		buttonAlSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("onClick!!!");
				// Spinner element
				new Thread() {
					public void run() {
						/*
						  {
        "AlarmId": "Alarm1213001",
        "AlarmName": "Food",
        "Description": "Masala Dosa",
        "CDId": "1A"
    },*/
						System.out
								.println("*********txtId.getText().toString()::"
										+ txtEventId.getText().toString()
										+ txtEventName.getText().toString()// here we have to add 
										//spinner element of class and division
										+ txtEventDescription.getText().toString());

						HttpClient httpClient = new DefaultHttpClient();
						// HttpPost post = new
						// HttpPost("http://samidha.org/restTrials/login/");
						HttpPost post = new HttpPost(
								"http://115.111.105.152/schoolApp/alarmEntry");
						post.setHeader("content-type",
								"application/json; charset=UTF-8");

						// Construimos el objeto cliente en formato JSON
						JSONObject dato = new JSONObject();

						try {

							dato.put("AlarmId", txtEventId.getText().toString());
							dato.put("AlarmName", txtEventName.getText().toString());
							dato.put("Description",txtEventDescription.getText().toString());
							// CDID SPINNER ELEMENT CLASS AND DIVISION
							
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
			}
		});
		buttonAlReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				txtEventId.setText("");
				txtEventName.setText("");
				txtEventDescription.setText("");

			}
		});
		buttonAlCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intents = new Intent(ATAlarmActivity.this,
						AfterTeacherLoginActivity.class);
				startActivity(intents);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.atalarm, menu);
		return true;
	}

}

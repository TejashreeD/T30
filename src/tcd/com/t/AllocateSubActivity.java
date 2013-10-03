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
import android.widget.Toast;

public class AllocateSubActivity extends Activity {
	HttpResponse resp;
	Intent intent1;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	String respStr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_allocate_sub);
		Button buttonSubjSubmit = (Button) findViewById(R.id.buttonSubjectSubmit);
		Button buttonSubjReset = (Button) findViewById(R.id.buttonSubjectReset);
		Button buttonSubjCancel = (Button) findViewById(R.id.buttonSubjectCancel);

		buttonSubjSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				// Spinner element
				new Thread() {

					
					public void run() {
						// TODO Run network requests here.

						System.out
								.println("*********txtId.getText().toString()::"
										+"" //txtSchId.getText().toString()
										// HERE WE HAVE TO ALL ALL SPINNER ELEMENTS I.E. CLASS DIVISION AND SUBJECT ID.
										);
						HttpClient httpClient = new DefaultHttpClient();
						// HttpPost post = new
						// HttpPost("http://samidha.org/restTrials/login/");
						HttpPost post = new HttpPost(
								"http://115.111.105.152/schoolApp/schoolEntry");
						post.setHeader("content-type",
								"application/json; charset=UTF-8");

						// Construimos el objeto cliente en formato JSON
						JSONObject dato = new JSONObject();

						try {
							//dato.put("schoolId", txtSchId.getText().toString());
							//HERE WE HAVE TO ALL ALL SPINNER ELEMENTS I.E. CLASS DIVISION AND SUBJECT ID.

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
			
				Toast.makeText(getApplicationContext(),
						"Submited Subject Detail Successfully ",
						Toast.LENGTH_LONG).show();

			}
		});
		buttonSubjReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		buttonSubjCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intents = new Intent(AllocateSubActivity.this,
						AfterTeacherLoginActivity.class);
				startActivity(intents);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.allocate_sub, menu);
		return true;
	}

}

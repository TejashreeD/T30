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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDivisionActivity extends Activity {
	HttpResponse resp;
	Intent intent1;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private String respStr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_division);
		Button buttonDSubmit = (Button) findViewById(R.id.buttonDivSubmit);
		Button buttonDReset = (Button) findViewById(R.id.buttonDivReset);
		Button buttonDCancel = (Button) findViewById(R.id.buttonDivCancel);

		buttonDSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("onClick!!!");
				// Spinner element
				new Thread() {
					public void run() {
						// TODO Run network requests here.
						EditText txtDivId = (EditText) findViewById(R.id.editTextDivID);
						EditText txtDivCapacity = (EditText) findViewById(R.id.editTextCapacity);
						// resultSetOutput = (TextView)
						// findViewById(R.id.textViewOutput);
						System.out
								.println("*********txtId.getText().toString()::"
										+ txtDivId.getText().toString()
										+ txtDivCapacity.getText().toString());

						HttpClient httpClient = new DefaultHttpClient();
						// HttpPost post = new
						// HttpPost("http://samidha.org/restTrials/login/");
						HttpPost post = new HttpPost(
								"http://115.111.105.152/schoolApp/divEntry");
						post.setHeader("content-type",
								"application/json; charset=UTF-8");

						// Construimos el objeto cliente en formato JSON
						JSONObject dato = new JSONObject();

						try {

							// Toast.makeText(getBaseContext(), "trying!!!",
							// Toast.LENGTH_SHORT).show();

							dato.put("divId", txtDivId.getText().toString());
							dato.put("cap", txtDivCapacity.getText().toString());
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

				// resultSetOutput.setText(OutputData);

				Toast.makeText(getApplicationContext(),
						"Submited Division Detail Successfully ",
						Toast.LENGTH_LONG).show();

			}
		});
		buttonDReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				EditText txtDId = (EditText) findViewById(R.id.editTextDivID);
				EditText txtDCapacity = (EditText) findViewById(R.id.editTextCapacity);
				txtDId.setText("");
				txtDCapacity.setText("");

			}
		});
		buttonDCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intentd = new Intent(AddDivisionActivity.this,
						AfterAdminMainActivity.class);
				startActivity(intentd);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_division, menu);
		return true;
	}

}

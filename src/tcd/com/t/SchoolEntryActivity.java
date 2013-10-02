package tcd.com.t;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SchoolEntryActivity extends Activity {
	HttpResponse resp;
	Intent intent1;
	private String OutputData;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private String respStr;
	private TextView resultSetOutput;
	protected EditText txtSchId;
	protected EditText txtSchName;
	protected EditText txtSchLoc;
	protected EditText txtSchUni;
	protected EditText txtSchCode;
	protected EditText txtSchContactNo;
	protected EditText txtSchFaxno;
	protected EditText txtSchEmail;
	protected EditText txtSchWebsite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_school_entry);

		txtSchId = (EditText) findViewById(R.id.editTextSchId);
		txtSchName = (EditText) findViewById(R.id.editTextSchName);
		txtSchLoc = (EditText) findViewById(R.id.editTextSchLocation);
		txtSchUni = (EditText) findViewById(R.id.editTextSchUniversity);
		txtSchCode = (EditText) findViewById(R.id.editTextSchCode);
		txtSchContactNo = (EditText) findViewById(R.id.editTextSchContactno);
		txtSchFaxno = (EditText) findViewById(R.id.edittextSchFaxno);
		txtSchEmail = (EditText) findViewById(R.id.editTextSchEmail);
		txtSchWebsite = (EditText) findViewById(R.id.editTextSchWebsite);

		Button btnADDAdmin = (Button) findViewById(R.id.buttonSchAdmin);
		Button buttonSubmit = (Button) findViewById(R.id.buttonSchSubmit);
		Button buttonCancel = (Button) findViewById(R.id.buttonSchoolCancel);
		Button buttonReset = (Button) findViewById(R.id.buttonSchoolReset);
		btnADDAdmin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(SchoolEntryActivity.this,
						TeachersEntryActivity.class);
				startActivity(intent);
			}
		});
		buttonReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				txtSchId.setText("");
				txtSchName.setText("");
				txtSchWebsite.setText("");
				txtSchLoc.setText("");
				txtSchUni.setText("");
				txtSchCode.setText("");
				txtSchContactNo.setText("");
				txtSchFaxno.setText("");
				txtSchEmail.setText("");
				txtSchWebsite.setText("");
			}
		});
		buttonCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SchoolEntryActivity.this,
						AfterSuperAdminLoginActivity.class);
				startActivity(intent);
			}
		});
		buttonSubmit.setOnClickListener(new OnClickListener() {
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
										+ txtSchId.getText().toString()
										+ txtSchName.getText().toString()
										+ txtSchWebsite.getText().toString()
										+ txtSchLoc.getText().toString()
										+ txtSchUni.getText().toString()
										+ txtSchCode.getText().toString()
										+ txtSchContactNo.getText().toString()
										+ txtSchFaxno.getText().toString()
										+ txtSchEmail.getText().toString()
										+ txtSchWebsite.getText().toString());

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
							dato.put("schoolId", txtSchId.getText().toString());
							dato.put("schoolName", txtSchName.getText()
									.toString());
							dato.put("location", txtSchLoc.getText().toString());
							dato.put("university_board", txtSchUni.getText()
									.toString());
							dato.put("schoolCode", txtSchCode.getText()
									.toString());
							dato.put("contactNo", txtSchContactNo.getText()
									.toString());
							dato.put("faxNo", txtSchFaxno.getText().toString());
							dato.put("emailId", txtSchEmail.getText()
									.toString());
							dato.put("website", txtSchWebsite.getText()
									.toString());

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

						/*
						 * JSONObject jsonResponse;
						 * 
						 * try { System.out.println("OKAY_6!!"); /****** Creates
						 * a new JSONObject with name/value mappings from the
						 * JSON string.******* jsonResponse = new
						 * JSONObject(respStr); System.out.println("OKAY_7!!");
						 * /***** Returns the value mapped by name if it exists
						 * and is a JSONArray.** /******* Returns null
						 * otherwise. ******* JSONArray jsonMainNode =
						 * jsonResponse .optJSONArray("ResultSet");
						 * System.out.println("OKAY_8!!"); /*********** Process
						 * each JSON Node ***********
						 * 
						 * int lengthJsonArr = jsonMainNode.length();
						 * 
						 * for (int i = 0; i < lengthJsonArr; i++) {
						 * System.out.println("OKAY_9!!"); /****** Get Object
						 * for each JSON node. *********** JSONObject
						 * jsonChildNode = jsonMainNode .getJSONObject(i);
						 * 
						 * /******* Fetch node values ********** String email =
						 * jsonChildNode.optString( "emailId").toString();
						 * String pwd = jsonChildNode.optString("pwd")
						 * .toString();
						 * 
						 * String firstname = jsonChildNode.optString(
						 * "firstName").toString(); String lastname =
						 * jsonChildNode.optString( "lastName").toString();
						 * 
						 * System.out.println("OKAY_10!!"); OutputData +=
						 * "Node : \n\n     " + email + " | " + pwd + " | " +
						 * firstname + " | " + lastname + " \n\n "; //
						 * Log.i("JSON parse", song_name); }
						 * 
						 * /************ Show Output on screen/activity
						 * ********** System.out.println("OKAY_11!!" +
						 * OutputData);
						 * 
						 * // startActivity(intent1); } catch (JSONException e)
						 * {
						 * 
						 * e.printStackTrace(); }
						 */
						/* just try end */

						// System.out.println("OKAY_settext_inner" +
						// OutputData);

					}

				}.start();
				// System.out.println("OKAY_settext_outer");

				// show the data in toast

				// Toast.makeText(getApplicationContext(),
				// "Selected: " + OutputData, Toast.LENGTH_LONG).show();
				// resultSetOutput.setText(OutputData);

			}
		});
	}

}

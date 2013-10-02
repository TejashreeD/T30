/*package tcd.com.t;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginMainActivity extends Activity implements
		OnItemSelectedListener {

	Intent intent1;
	String Returned;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_main);
		/*
 * try { HttpClient httpclient = new DefaultHttpClient(); HttpPost p=new
 * HttpPost("http://localhost/schoolApp/login/"); HttpPost post = new
 * HttpPost("http://localhost/schoolApp/login/"); StringEntity str = new
 * StringEntity("activity_login_main.xml");
 * str.setContentType("application/xml; charset=utf-8");
 * str.setContentEncoding(new
 * BasicHeader(HTTP.CONTENT_TYPE,"application/xml; charset=utf-8"));
 * post.setEntity(str); HttpResponse response =
 * httpclient.execute(post); HttpEntity entity = response.getEntity();
 * Returned = EntityUtils.toString(entity); Toast.makeText(this,
 * Returned, Toast.LENGTH_LONG).show(); } catch ( IOException ioe ) {
 * ioe.printStackTrace(); }
 *
		Button buttonLogin = (Button) findViewById(R.id.buttonAESubmit);
		buttonLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// intent1=new
				// Intent(LoginMainActivity.this,AfterAdminMainActivity.class );
				EditText txtId = (EditText) findViewById(R.id.editTextLUsername);
				EditText txtWord = (EditText) findViewById(R.id.editTextLPwd);

				/*
 * //json coding System.out.println("onClick!!!"); HttpClient
 * httpClient = new DefaultHttpClient(); // HttpPost post = new
 * HttpPost
 * ("http://10.0.2.2:8080/DictionaryWebService/rest/dictionary/"
 * ); HttpPost post = new
 * HttpPost("http://115.111.105.152/schoolApp/login/");
 * post.setHeader("content-type",
 * "application/json; charset=UTF-8");
 * 
 * //Construimos el objeto cliente en formato JSON JSONObject
 * dato = new JSONObject();
 * 
 * try { dato.put("email", txtId.getText().toString());
 * dato.put("pwd", txtWord.getText().toString()); //
 * dato.put("type", txtDescription.getText().toString());
 * 
 * StringEntity entity = new StringEntity(dato.toString());
 * post.setEntity(entity);
 * 
 * HttpResponse resp = httpClient.execute(post); String
 * respString = EntityUtils.toString(resp.getEntity());
 * 
 * 
 * System.out.println("OKAY!"+ respString);
 * 
 * } catch (Exception e) { System.out.println("Exception OKAY!"+
 * e); }
 * 
 * //json coding end
 *

				startActivity(intent1);
			}
		});

		// Spinner element
		Spinner spinner = (Spinner) findViewById(R.id.spinnerLogin);

		// Spinner click listener
		spinner.setOnItemSelectedListener(this);

		// Spinner Drop down elements
		List<String> categories = new ArrayList<String>();
		categories.add("Super Admin");
		categories.add("Admin");
		categories.add("Teacher");
		categories.add("Parent");
		// categories.add("Student");

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, categories);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// On selecting a spinner item
		String item = parent.getItemAtPosition(position).toString();

		// Showing selected spinner item
		Toast.makeText(parent.getContext(), "Selected: " + item,
				Toast.LENGTH_LONG).show();

		if (item.equals("Super Admin")) {
			intent1 = new Intent(LoginMainActivity.this,
					AfterSuperAdminLoginActivity.class);
		} else if (item.equals("Admin")) {
			intent1 = new Intent(LoginMainActivity.this,
					AfterAdminMainActivity.class);
		} else if (item.equals("Teacher")) {
			intent1 = new Intent(LoginMainActivity.this,
					AfterTeacherLoginActivity.class);

			// intent1=new Intent(LoginMainActivity.this,Demo.class );
		} else if (item.equals("Parent")) {
			intent1 = new Intent(LoginMainActivity.this,
					AfterPArentLoginMainActivity.class);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> a) {
	}
}
 */

package tcd.com.t;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginMainActivity extends Activity implements
		OnItemSelectedListener {
	String OutputData;
	TextView  resultSetOutput ;
	HttpResponse resp;
	Intent intent1;

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login_main);
		final TextView output = (TextView) findViewById(R.id.textViewOutput);
		Button buttonLogin = (Button) findViewById(R.id.buttonAESubmit);
		Button buttonLReset=(Button) findViewById(R.id.buttonAEReset);
		 resultSetOutput=(TextView) findViewById(R.id.textViewLoginPass);
		buttonLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				System.out.println("onClick!!!");
				// Toast.makeText(getBaseContext(), "onClick!!!",
				// Toast.LENGTH_SHORT).show();

			//	StrictMode.ThreadPolicy policy = new
			//	StrictMode.ThreadPolicy.Builder().permitAll().build();
				// StrictMode.setThreadPolicy(policy);
				final EditText txtId = (EditText) findViewById(R.id.editTextLUsername);
				final EditText txtWord = (EditText) findViewById(R.id.editTextLPwd);

				
		// Spinner element
				new Thread() {
                private Context Context;
				private String respStr;
				
                
                
                public void run() {
                    // TODO Run network requests here.
                    EditText txtId = (EditText) findViewById(R.id.editTextLUsername);
                    EditText txtWord = (EditText) findViewById(R.id.editTextLPwd);
                    resultSetOutput = (TextView) findViewById(R.id.textViewOutput);
                    System.out
                            .println("*********txtId.getText().toString()::"
                                    + txtId.getText().toString()
                                    + txtWord.getText().toString());

                    HttpClient httpClient = new DefaultHttpClient();
                     //HttpPost post = new
                    //HttpPost("http://samidha.org/restTrials/login");
                    
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
            
			Toast.makeText(getApplicationContext(),
                    "Selected: " + OutputData, Toast.LENGTH_LONG).show();
			startActivity(intent1);
//resultSetOutput.setText(OutputData);
        }
	});
		
		
		buttonLReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				EditText txtLoginUserName=(EditText) findViewById(R.id.editTextLUsername);
				EditText txtLoginPassword=(EditText) findViewById(R.id.editTextLPwd);
				
				txtLoginUserName.setText("");
				txtLoginPassword.setText("");
								
				
			}
		});

		Spinner spinner = (Spinner) findViewById(R.id.spinnerLogin);

		// Spinner click listener
		spinner.setOnItemSelectedListener(this);

		// Spinner Drop down elements
		List<String> categories = new ArrayList<String>();
		categories.add("Super Admn");
		categories.add("Admn");
		categories.add("Teacher");
		categories.add("Parent");
		// categories.add("Student");

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, categories);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		// On selecting a spinner item
		String item = parent.getItemAtPosition(position).toString();

		// Showing selected spinner item
		Toast.makeText(parent.getContext(), "Selected: " + item,
				Toast.LENGTH_LONG).show();

		if (item.equals("Super Admn")) {
			intent1 = new Intent(LoginMainActivity.this,
					AfterSuperAdminLoginActivity.class);
		} else if (item.equals("Admn")) {
			intent1 = new Intent(LoginMainActivity.this,
					AfterAdminMainActivity.class);
		} else if (item.equals("Teacher")) {
			intent1 = new Intent(LoginMainActivity.this,
					AfterTeacherLoginActivity.class);
		} else if (item.equals("Parent")) {
			intent1 = new Intent(LoginMainActivity.this,
					AfterPArentLoginMainActivity.class);
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}

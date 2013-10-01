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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ATAlarmActivity extends Activity {
	HttpResponse resp;
	Intent intent1;
	private String OutputData;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private String respStr;
	private TextView resultSetOutput;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atalarm);
		
		Button buttonAlSubmit=(Button) findViewById(R.id.buttonATSubmitAss);
		Button buttonAlReset=(Button) findViewById(R.id.buttonATReset);
		Button buttonAlCancel=(Button) findViewById(R.id.buttonAttendCancel);
buttonAlSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("onClick!!!");
				final EditText txtId = (EditText) findViewById(R.id.editTextLUsername);
				final EditText txtWord = (EditText) findViewById(R.id.editTextLPwd);

				
		// Spinner element
				new Thread() {
                private Context Context;
				
                
                
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
            
			Toast.makeText(getApplicationContext(),
                    "Selected: " + OutputData, Toast.LENGTH_LONG).show();
//resultSetOutput.setText(OutputData);

				Toast.makeText( getApplicationContext(), "Submited Event Detail Successfully ",Toast.LENGTH_LONG).show();
				
			}
		});
buttonAlReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				EditText txtEventId=(EditText) findViewById(R.id.editTextEventId);
				EditText txtEventName=(EditText) findViewById(R.id.editTextATEventName);
				EditText txtEventDesc=(EditText) findViewById(R.id.editTextATDescription);
				txtEventId.setText("");
				txtEventName.setText("");
				txtEventDesc.setText("");
				
			}
		});
buttonAlCancel.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intents=new Intent(ATAlarmActivity.this,AfterTeacherLoginActivity.class);
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

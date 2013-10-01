package tcd.com.t;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentEntryActivity extends Activity {
	HttpResponse resp;
	Intent intent1;
	private String OutputData;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private String respStr;
	private TextView resultSetOutput;

	EditText SDOB;
	Calendar myCalendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_entry);
		Button buttonCancel=(Button) findViewById(R.id.buttonASCancel);
		Button buttonReset=(Button) findViewById(R.id.buttonASReset);
		Button buttonSSubmit=(Button) findViewById(R.id.buttonASSubmit);
		SDOB = (EditText) findViewById(R.id.editTextStudBday);
	
		buttonCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(StudentEntryActivity.this, AfterAdminMainActivity.class);
				startActivity(intent);
			}
		});
		
		buttonReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText studPrn=(EditText) findViewById(R.id.editTextStudPRN);
				EditText fName=(EditText) findViewById(R.id.editTextStudFname);
				EditText mName=(EditText) findViewById(R.id.editTextStudMname);
				EditText lName=(EditText) findViewById(R.id.editTextStudLname);
				EditText email=(EditText) findViewById(R.id.editTextStudEmail);
				EditText bDate=(EditText) findViewById(R.id.editTextStudBday);
				studPrn.setText("");
				bDate.setText("");
				fName.setText("");
				mName.setText("");
				lName.setText("");
				email.setText("");
					}
		});
		buttonSSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
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


				
				Toast.makeText( getApplicationContext(), "Submited Student Detail Successfully ",Toast.LENGTH_LONG).show();
			}
		});
		
		myCalendar = Calendar.getInstance();

	
		SDOB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(StudentEntryActivity.this, date,
						myCalendar.get(Calendar.YEAR), myCalendar
								.get(Calendar.MONTH), myCalendar
								.get(Calendar.DAY_OF_MONTH)).show();


				
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

		private void updateLabel() {
			// TODO Auto-generated method stub
			String myFormat = "MM/dd/yy"; // In which you need put here
			SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

			SDOB.setText(sdf.format(myCalendar.getTime()));

			
		}

		
	};


}

package tcd.com.t;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SpinnerJson extends Activity implements OnItemSelectedListener {

	protected static String OutputData = null;
	StringBuilder builder = new StringBuilder();
	String item;
	String readFeed;
	JSONObject json;
	Spinner mySpinner;
	String spinnerElement; String cnames[]=new String[10];
	ResponseHandler<String> responseHandler = new BasicResponseHandler();

	// you can use this array to find the school ID based on name
	List<Classes> Classes = new ArrayList<Classes>();
	// you can use this array to populate your spinner
	List<String> classNames = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner_json);
		mySpinner = (Spinner) findViewById(R.id.spinnerNew);
		
		FeadFeed(cnames);
		for (int i = 0; i < cnames.length; i++) {
			System.out.print(cnames[i]+" add ok");
			classNames.add(cnames[i]);}
		mySpinner.setOnItemSelectedListener(this);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, classNames);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		mySpinner.setAdapter(dataAdapter);

	}

	private void FeadFeed(final String[] cnames) {
		// TODO Auto-generated method stub
		Thread background = new Thread(new Runnable(){
			public void run() {
				// TODO Run network requests here.
				String SetServerString;
				HttpClient httpClient = new DefaultHttpClient();
				
				HttpGet get = new HttpGet(
						"http://115.111.105.152/schoolApp/allClasses");
				get.setHeader("content-type", "application/json; charset=UTF-8");
				

				try {
					

  ResponseHandler<String> responseHandler = new BasicResponseHandler();
                 SetServerString = httpClient.execute(get, responseHandler);
                 threadMsg(SetServerString);
					System.out.println("OKAY_0!!");
					HttpResponse resp = httpClient.execute(get);
					String respStr = httpClient.execute(get,responseHandler);
					System.out.println("OKAY_3!!" + resp);
System.out.println(respStr+"Hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
					StatusLine statusLine = resp.getStatusLine();
					int statusCode = statusLine.getStatusCode();
					if (statusCode == 200) {
						System.out.print("status code" + statusCode + "\n\n");
						HttpEntity entity = resp.getEntity();

						InputStream content = entity.getContent();
						System.out.println("entiry=" + entity + "content="
								+ content);
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(content));
						String line;
						while ((line = reader.readLine()) != null) {
							builder.append(line);
						}
					} else {
						Log.e(SpinnerJson.class.toString(),
								"Failed to download file");
					}
					System.out.println("OKAY_4!!");
					System.out.println("OKAY_5!!" + builder.toString());

				} catch (Exception exception) {
					Log.e("MYAPP", "exception", exception);
				}

				/* try */
				try {
					readFeed = builder.toString();
					json = new JSONObject(readFeed);
					JSONArray jsonArray = new JSONArray(
							json.optString("ResultSet"));
					Log.i(SpinnerJson.class.getName(), "Number of entries "
							+ jsonArray.length());

					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);

						Classes Classes2 = new Classes();
						Classes2.setName(jsonObject.optString("className"));
						Classes2.setId(jsonObject.optString("classId"));
						Classes.add(Classes2);
						spinnerElement = jsonObject.optString("className");
						System.out.println(" " + spinnerElement);
						// classNames.add(jsonObject.optString("className"));
						cnames[i]=spinnerElement;
						
						System.out.print(cnames[i]+" add elements ok");
						
						
					}
										/*for (int i = 0; i < jsonArray.length(); i++) {
						System.out.print(cnames[i]+" add ok");
						classNames.add(cnames[i]);}
					*/
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			private void threadMsg(String msg) {
				// TODO Auto-generated method stub
				if (!msg.equals(null) && !msg.equals("")) {
                    Message msgObj = handler.obtainMessage();
                    Bundle b = new Bundle();
                    b.putString("message", msg);
                    msgObj.setData(b);
                    handler.sendMessage(msgObj);
                }
			
			}

			 private final Handler handler = new Handler() {
				 
                 public void handleMessage(Message msg) {
                      
                     String aResponse = msg.getData().getString("message");

                     if ((null != aResponse)) {

                         // ALERT MESSAGE
                         Toast.makeText(
                                 getBaseContext(),
                                 "Server Response: "+aResponse,
                                 Toast.LENGTH_SHORT).show();
                     }
                     else
                     {

                             // ALERT MESSAGE
                             Toast.makeText(
                                     getBaseContext(),
                                     "Not Got Response From Server.",
                                     Toast.LENGTH_SHORT).show();
                     }   

                 }
             };
		
		});
		background.start();
		
	}

//	public void readFeed(cnames) {
//
//		Thread background = new Thread(new Runnable(){
//			public void run() {
//				// TODO Run network requests here.
//				String SetServerString;
//				HttpClient httpClient = new DefaultHttpClient();
//				
//				HttpGet get = new HttpGet(
//						"http://115.111.105.152/schoolApp/allClasses");
//				get.setHeader("content-type", "application/json; charset=UTF-8");
//				
//
//				try {
//					
//
//  ResponseHandler<String> responseHandler = new BasicResponseHandler();
//                 SetServerString = httpClient.execute(get, responseHandler);
//                 threadMsg(SetServerString);
//					System.out.println("OKAY_0!!");
//					HttpResponse resp = httpClient.execute(get);
//					String respStr = httpClient.execute(get,responseHandler);
//					System.out.println("OKAY_3!!" + resp);
//System.out.println(respStr+"Hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
//					StatusLine statusLine = resp.getStatusLine();
//					int statusCode = statusLine.getStatusCode();
//					if (statusCode == 200) {
//						System.out.print("status code" + statusCode + "\n\n");
//						HttpEntity entity = resp.getEntity();
//
//						InputStream content = entity.getContent();
//						System.out.println("entiry=" + entity + "content="
//								+ content);
//						BufferedReader reader = new BufferedReader(
//								new InputStreamReader(content));
//						String line;
//						while ((line = reader.readLine()) != null) {
//							builder.append(line);
//						}
//					} else {
//						Log.e(SpinnerJson.class.toString(),
//								"Failed to download file");
//					}
//					System.out.println("OKAY_4!!");
//					System.out.println("OKAY_5!!" + builder.toString());
//
//				} catch (Exception exception) {
//					Log.e("MYAPP", "exception", exception);
//				}
//
//				/* try */
//				try {
//					readFeed = builder.toString();
//					json = new JSONObject(readFeed);
//					JSONArray jsonArray = new JSONArray(
//							json.optString("ResultSet"));
//					Log.i(SpinnerJson.class.getName(), "Number of entries "
//							+ jsonArray.length());
//
//					for (int i = 0; i < jsonArray.length(); i++) {
//						JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//						Classes Classes2 = new Classes();
//						Classes2.setName(jsonObject.optString("className"));
//						Classes2.setId(jsonObject.optString("classId"));
//						Classes.add(Classes2);
//						spinnerElement = jsonObject.optString("className");
//						System.out.println(" " + spinnerElement);
//						// classNames.add(jsonObject.optString("className"));
//						cnames[i]=spinnerElement;
//						
//						System.out.print(cnames[i]+" add elements ok");
//						
//						
//					}
//										/*for (int i = 0; i < jsonArray.length(); i++) {
//						System.out.print(cnames[i]+" add ok");
//						classNames.add(cnames[i]);}
//					*/
//					
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//			}
//
//			private void threadMsg(String msg) {
//				// TODO Auto-generated method stub
//				if (!msg.equals(null) && !msg.equals("")) {
//                    Message msgObj = handler.obtainMessage();
//                    Bundle b = new Bundle();
//                    b.putString("message", msg);
//                    msgObj.setData(b);
//                    handler.sendMessage(msgObj);
//                }
//			
//			}
//
//			 private final Handler handler = new Handler() {
//				 
//                 public void handleMessage(Message msg) {
//                      
//                     String aResponse = msg.getData().getString("message");
//
//                     if ((null != aResponse)) {
//
//                         // ALERT MESSAGE
//                         Toast.makeText(
//                                 getBaseContext(),
//                                 "Server Response: "+aResponse,
//                                 Toast.LENGTH_SHORT).show();
//                     }
//                     else
//                     {
//
//                             // ALERT MESSAGE
//                             Toast.makeText(
//                                     getBaseContext(),
//                                     "Not Got Response From Server.",
//                                     Toast.LENGTH_SHORT).show();
//                     }   
//
//                 }
//             };
//		
//		});
//		background.start();
//
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spinner_json, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		item = parent.getItemAtPosition(position).toString();
		Toast.makeText(parent.getContext(), "Selected: " + item,
				Toast.LENGTH_LONG).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		Toast.makeText(parent.getContext(), "Selected: " + item,
				Toast.LENGTH_LONG).show();
	}

}

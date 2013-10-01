package tcd.com.t;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AllocateSubActivity extends Activity {
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
		setContentView(R.layout.activity_allocate_sub);
		Button buttonSubjSubmit=(Button) findViewById(R.id.buttonSubjectSubmit);
		Button buttonSubjReset=(Button) findViewById(R.id.buttonSubjectReset);
		Button buttonSubjCancel=(Button) findViewById(R.id.buttonSubjectCancel);
		
buttonSubjSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText( getApplicationContext(), "Submited Subject Detail Successfully ",Toast.LENGTH_LONG).show();
				
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
		Intent intents=new Intent(AllocateSubActivity.this,AfterTeacherLoginActivity.class);
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

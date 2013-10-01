package tcd.com.t;

import java.sql.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ShowTTActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_tt);
	Button btnBack=(Button) findViewById(R.id.buttonTTBack);
	EditText ctime=(EditText) findViewById(R.id.editTextTTCurrentTime);
/*ctime.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Date d=new Date(0);
		CharSequence s  = DateFormat.format("EEEE, MMMM d, yyyy ", d.getTime());

	}
});*/
//Date d=new Date(0);
	//CharSequence s  = DateFormat.format("EEEE, MMMM d, yyyy ", d.getTime());
	btnBack.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {

			Intent intent=new Intent(ShowTTActivity.this,ClassDivActivity.class);
			startActivity(intent);
		}
	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_tt, menu);
		return true;
	}

}

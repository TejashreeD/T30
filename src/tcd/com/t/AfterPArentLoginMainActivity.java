package tcd.com.t;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AfterPArentLoginMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_after_parent_login_main);
		Button buttonLogout=(Button) findViewById(R.id.buttonAPLogout);
		Button buttonShowAcdProgress=(Button) findViewById(R.id.buttonAPShowAcademics);
		Button buttonShowExtracProgress=(Button) findViewById(R.id.buttonAPShowExtProgress);
	
buttonLogout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(AfterPArentLoginMainActivity.this,LoginMainActivity.class);
				startActivity(intent);
				
				
				
			}
		});
		buttonShowAcdProgress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText( getApplicationContext(), "Showing Academic progress Successfully ",Toast.LENGTH_LONG).show();
							}
		});
		buttonShowExtracProgress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText( getApplicationContext(), "Showing Extracuriculim progress Successfully ",Toast.LENGTH_LONG).show();

					}
			
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.after_parent_login_main, menu);
		return true;
	}

}

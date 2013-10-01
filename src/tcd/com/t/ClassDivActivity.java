package tcd.com.t;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ClassDivActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_div);
		Button buttonGo=(Button) findViewById(R.id.buttonTimatableGo);
		Button buttonBack=(Button) findViewById(R.id.buttonTTBack);

		buttonGo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(ClassDivActivity.this, ShowTTActivity.class);
				startActivity(intent);
			}
		});
		buttonBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(ClassDivActivity.this, AfterTeacherLoginActivity.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.class_div, menu);
		return true;
	}

}

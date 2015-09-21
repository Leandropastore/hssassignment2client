package aut.hss.safeclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	
	EditText Username;
	
	Button SRegister;
	Button Register;
	Button ObtPK;
	Button SRegisterComponent; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SRegister = (Button) findViewById(R.id.SRegister);
		Register = (Button) findViewById(R.id.Register);
		ObtPK = (Button) findViewById(R.id.ObtPK);
		SRegisterComponent = (Button) findViewById(R.id.SRegisterComponent);
		
		
		SRegister.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				if (v == SRegister){
					setContentView(R.layout.sregister);
				}	
			}		
		});
		Register.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				if (v == Register){
					setContentView(R.layout.register);
				}	
			}		
		});
		ObtPK.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				if (v == ObtPK){
					
				}	
			}		
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

package aut.hss.safeclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	EditText username;
	EditText password;
	Button login;
	Button cancel;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		
		login = (Button) findViewById(R.id.loginbutton);
		cancel = (Button) findViewById(R.id.cancelbutton);
		
		login.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				if (v == login){
					// check username and password to server
					// 
				}	
			}		
		});
		cancel.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				if (v == cancel){
					// clear username and password
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

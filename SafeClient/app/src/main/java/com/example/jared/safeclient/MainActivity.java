package com.example.jared.safeclient;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

    // First Contact
    private static String METHOD_NAME_FC = "firstContact";
    private static String SOAP_ACTION_FC1 = "http://webservices.leandro/SafeMessagingWebService/firstContactRequest";
    private static String SOAP_ACTION_FC2 = "http://webservices.leandro/SafeMessagingWebService/firstContactResponse";

    private static String METHOD_NAME_R = "registerUser";
    private static String SOAP_ACTION_R1 = "http://webservices.leandro/SafeMessagingWebService/registerUserRequest";
    private static String SOAP_ACTION_R2 = "http://webservices.leandro/SafeMessagingWebService/registerUserResponse";

    private static String NAMESPACE = "http://localhost:8080/SafeMessagingWebService/SafeMessagingWebService?xsd=1";

    private static String URL = "http://www.w3schools.com/webservices/tempconvert.asmx?WSDL";
    EditText username, aes, phonenum, user2;
    Button firstcontact, register, getPubKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();

        username = (EditText) findViewById(R.id.username);
        aes = (EditText) findViewById(R.id.aes);
        phonenum = (EditText) findViewById(R.id.phone_num);
        user2 = (EditText) findViewById(R.id.user2);

        firstcontact = (Button) findViewById(R.id.First_Contact);
        register = (Button) findViewById(R.id.register);
        getPubKey = (Button) findViewById(R.id.get_public_key);

        firstcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == firstcontact){
                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_FC);
                    request.addProperty("Username",username.getText());
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                    envelope.setOutputSoapObject(request);
                    envelope.dotNet = true;
                    try {
                        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                        //this is the actual part that will call the webservice
                        androidHttpTransport.call(SOAP_ACTION_FC2, envelope);

                        // Get the SoapResult from the envelope body.
                        SoapObject result = (SoapObject)envelope.bodyIn;

                        if(result != null)
                        {
                            //Get the first property and change the label text
                            aes.setText(result.getProperty(0).toString());
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "No Response", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == register){
                    RSA rsaKey = new RSA();
                    editor.putString("",ByteManager.createString(rsaKey.getPrivate()));
                    editor.commit();
                    String contructedText = username.getText()+";"+
                            ByteManager.createString(rsaKey.getPublic())+";"+phonenum.getText();
                    try{
                        AES aEncrypt = new AES(aes.getText().toString());
                        String finalCText = ByteManager.createString(aEncrypt.encrypt(contructedText));

                    } catch (Exception e){
                        System.out.println(e);
                    }
                }
            }
        });
        getPubKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == getPubKey){
                    username.getText();
                    user2.getText();
                    // Decrypt Response with private key
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

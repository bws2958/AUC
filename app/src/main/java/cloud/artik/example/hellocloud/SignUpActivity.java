package cloud.artik.example.hellocloud;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import cloud.artik.example.hellocloud.Util.DBHelper;

/**
 * Created by HMS on 2017-08-25.
 */

public class SignUpActivity extends Activity implements AdapterView.OnItemSelectedListener{
    private static final String TAG = "SignUpActivity";
    private String fName = "myFile";
    private DBHelper dbHelper;
    private String toast;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        try{
            dbHelper = new DBHelper(getApplicationContext(), "AUC.db", null, 1);
        }catch (Exception e){
            Log.e(TAG, "SQLite error");
        }

    }

    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.button_confirm:
                Log.v("confirm", "confirm");
                SharedPreferences sf = getSharedPreferences(fName, 0);
                SharedPreferences.Editor editor = sf.edit();
                EditText id = (EditText)findViewById(R.id.signup_id);
                EditText pass = (EditText)findViewById(R.id.signup_pass);
                EditText email = (EditText)findViewById(R.id.signup_email);
                String str_id = id.getText().toString();
                String str_pass = pass.getText().toString();
                String str_email = email.getText().toString();
                editor.putString("id", str_id);
                editor.putString("pass", str_pass);
                editor.commit();

                if (isValidEmail(str_email) == true){
                    toast = "true email";
                }else{
                    toast = "false email";
                }
                displayToast();
                break;
            case R.id.button_cancel:
                Log.v("confirm", "cancel");
                finish();
                break;
            case R.id.button_device_id:
                new IntentIntegrator(this).initiateScan();
                /*IntentIntegrator integrator = new IntentIntegrator(this);
                integrator.setOrientationLocked(true);
                integrator.initiateScan();*/
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == IntentIntegrator.REQUEST_CODE){
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null){
                toast = "cancelled";
            }else{
                toast = "scanned : " + result.getContents();
                EditText editText = (EditText)findViewById(R.id.device_id);
                editText.setText(result.getContents());

            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
        displayToast();
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){ Log.v(TAG, "item selected"); }

    public void onNothingSelected(AdapterView<?> parent){
        Log.v(TAG, "item not selected");
    }

    public final static boolean isValidEmail(CharSequence target){
        if(TextUtils.isEmpty(target)){
            return false;
        }else
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void displayToast(){
        if(getApplicationContext() != null && toast != null){
            Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
            toast = null;
        }
    }
}

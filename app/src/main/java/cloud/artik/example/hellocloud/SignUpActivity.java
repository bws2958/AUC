package cloud.artik.example.hellocloud;

import android.app.Activity;
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

import cloud.artik.example.hellocloud.Util.Helper.DBHelper;
import cloud.artik.example.hellocloud.Util.Helper.KeyLimitHelper;
import cloud.artik.example.hellocloud.Util.Retrofit.Response.Signup;
import cloud.artik.example.hellocloud.Util.Retrofit.RestfulAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static cloud.artik.example.hellocloud.Util.Config.ACCESS_TOKEN;
import static cloud.artik.example.hellocloud.Util.Config.DEVICE_ID_LIST;
import static cloud.artik.example.hellocloud.Util.Config.REFRESH_TOKEN;
import static cloud.artik.example.hellocloud.Util.Config.USER_DATA;
import static cloud.artik.example.hellocloud.Util.Config.EMAIL;
import static cloud.artik.example.hellocloud.Util.Config.ID;
import static cloud.artik.example.hellocloud.Util.Config.SIGNIN;

/**
 * Created by HMS on 2017-08-25.
 */

public class SignUpActivity extends Activity implements AdapterView.OnItemSelectedListener{
    private static final String TAG = "SignUpActivity";
    private String fName = "myFile";
    private DBHelper dbHelper;
    private String toast;
    private EditText id;
    private EditText pass;
    private EditText email;
    private EditText device_id;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        try{
            dbHelper = new DBHelper(getApplicationContext(), "AUC.db", null, 1);
        }catch (Exception e){
            Log.e(TAG, "SQLite error");
        }

        id = (EditText)findViewById(R.id.signup_id);
        pass = (EditText)findViewById(R.id.signup_pass);
        email = (EditText)findViewById(R.id.signup_email);
        device_id = (EditText)findViewById(R.id.device_id);

        id.setOnKeyListener(new KeyLimitHelper());
        pass.setOnKeyListener(new KeyLimitHelper());
        email.setOnKeyListener(new KeyLimitHelper());
        device_id.setOnKeyListener(new KeyLimitHelper());
    }

    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.button_confirm:
                Log.v("confirm", "confirm");

                final String str_id = id.getText().toString();
                final String str_pass = pass.getText().toString();
                final String str_deviceId = "myBlender";
                final String str_email = email.getText().toString();


                if (isValidEmail(str_email) == true){
                    toast = "true email";

                    Call<Signup> userDataCall = RestfulAdapter.getInstance().userSignup(str_email, str_pass, str_pass);

                    userDataCall.enqueue(new Callback<Signup>() {
                        @Override
                        public void onResponse(Call<Signup> call, Response<Signup> response) {
                            Log.d(TAG, "response : " + response.body());

                            SharedPreferences sf = getSharedPreferences(USER_DATA, 0);
                            SharedPreferences.Editor editor = sf.edit();

                            Boolean signinCheck = response.body().getSignin();

                            if(signinCheck){
                                editor.putBoolean(SIGNIN, true);
                                editor.putString(ID, str_id);
                                editor.putString(EMAIL, str_email);
                                editor.putString(ACCESS_TOKEN, response.body().getAccessToken());
                                editor.putString(REFRESH_TOKEN, response.body().getRefreshToken());
                                editor.putString(DEVICE_ID_LIST, str_deviceId);
                            }else{
                                editor.putBoolean(SIGNIN, false);
                            }
                            Log.d(TAG, "Token : " + response.body().getAccessToken());

                            editor.apply();

                            Toast.makeText(getApplicationContext(), "회원가입 성공.", Toast.LENGTH_LONG).show();

                            finish();
                        }

                        @Override
                        public void onFailure(Call<Signup> call, Throwable t) {
                            Log.d(TAG, "onFailure");
                            Toast.makeText(getApplicationContext(), "잠시 후 다시 시도하세요.", Toast.LENGTH_LONG).show();
                        }
                    });
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
                device_id.setText(result.getContents());

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

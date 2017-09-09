/*
 * Copyright (C) 2017 Samsung Electronics Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cloud.artik.example.hellocloud;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
//import android.widget.Toast;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.TokenResponse;

import cloud.artik.example.hellocloud.Util.AuthHelper;
import cloud.artik.example.hellocloud.Util.AuthStateDAL;
import cloud.artik.example.hellocloud.Util.Retrofit.Response.Signin;
import cloud.artik.example.hellocloud.Util.Retrofit.Response.Signup;
import cloud.artik.example.hellocloud.Util.Retrofit.RestfulAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static cloud.artik.example.hellocloud.Util.AuthHelper.INTENT_ARTIKCLOUD_AUTHORIZATION_RESPONSE;
import static cloud.artik.example.hellocloud.Util.AuthHelper.USED_INTENT;
import static cloud.artik.example.hellocloud.Util.Config.ACCESS_TOKEN;
import static cloud.artik.example.hellocloud.Util.Config.DEVICE_ID_LIST;
import static cloud.artik.example.hellocloud.Util.Config.EMAIL;
import static cloud.artik.example.hellocloud.Util.Config.ID;
import static cloud.artik.example.hellocloud.Util.Config.REFRESH_TOKEN;
import static cloud.artik.example.hellocloud.Util.Config.SIGNIN;
import static cloud.artik.example.hellocloud.Util.Config.USER_DATA;

public class SignInActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "SignInActivity";
    private String fName = "myFile";

    private AuthorizationService mAuthorizationService;
    private AuthStateDAL mAuthStateDAL;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        spinner = (Spinner) findViewById(R.id.signin_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.user_mode, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        mAuthorizationService = new AuthorizationService(this);
        mAuthStateDAL = new AuthStateDAL(this);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        LinearLayout user_layout = (LinearLayout)findViewById(R.id.user_layout);
        LinearLayout admin_layout = (LinearLayout)findViewById(R.id.admin_layout);
        switch(pos){
            case 0:
                try{
                    if(admin_layout.getVisibility() == view.getVisibility()){
                        Log.v(TAG, "admin visible");
                    }else{
                        Log.v(TAG, "make admin visible");
                        admin_layout.setVisibility(view.getVisibility());
                        user_layout.setVisibility(view.GONE);
                    }
                }catch (Exception e){
                    Log.v(TAG, "admin mode error");
                }

                break;
            case 1:
                try{
                    if (user_layout.getVisibility() == view.getVisibility()){
                        Log.v(TAG, "user visible");
                    }else{
                        Log.v(TAG, "make user visible");
                        admin_layout.setVisibility(view.GONE);
                        user_layout.setVisibility(view.getVisibility());
                    }
                }catch(Exception e){
                    Log.v(TAG, "user mode error");
                }
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent){
        Log.v(TAG, "item not selected");
    }

    // File OAuth call with Authorization Code with PKCE method
    // https://developer.artik.cloud/documentation/getting-started/authentication.html#authorization-code-with-pkce-method
    private void doAuth() {
        AuthorizationRequest authorizationRequest = AuthHelper.createAuthorizationRequest();

        PendingIntent authorizationIntent = PendingIntent.getActivity(
                this,
                authorizationRequest.hashCode(),
                new Intent(INTENT_ARTIKCLOUD_AUTHORIZATION_RESPONSE, null, this, AdminActivity.class),
                0);

        /* request sample with custom tabs */
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        mAuthorizationService.performAuthorizationRequest(authorizationRequest, authorizationIntent);
        //mAuthorizationService.performAuthorizationRequest(authorizationRequest, authorizationIntent, customTabsIntent);
        //finish();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "Entering onStart ...");
        super.onStart();
        //checkIntent(getIntent());
    }
    @Override
    protected void onNewIntent(Intent intent) {
        checkIntent(intent);
    }

    private void checkIntent(@Nullable Intent intent) {

        Log.d(TAG, "Entering checkIntent ...");
        if (intent != null) {
            String action = intent.getAction();
            switch (action) {
                case INTENT_ARTIKCLOUD_AUTHORIZATION_RESPONSE:
                    Log.d(TAG, "checkIntent action = " + action
                            + " intent.hasExtra(USED_INTENT) = " + intent.hasExtra(USED_INTENT));
                    if (!intent.hasExtra(USED_INTENT)) {
                        handleAuthorizationResponse(intent);
                        intent.putExtra(USED_INTENT, true);
                    }
                    break;
                default:
                    Log.w(TAG, "checkIntent action = " + action);
                    // do nothing
            }
        } else {
            Log.w(TAG, "checkIntent intent is null!");
        }
    }
    private void handleAuthorizationResponse(@NonNull Intent intent) {
        AuthorizationResponse response = AuthorizationResponse.fromIntent(intent);
        AuthorizationException error = AuthorizationException.fromIntent(intent);
        Log.i(TAG, "Entering handleAuthorizationResponse with response from Intent = " + response.jsonSerialize().toString());

        if (response != null) {

            if (response.authorizationCode != null ) { // Authorization Code method: succeeded to get code

                final AuthState authState = new AuthState(response, error);
                Log.i(TAG, "Received code = " + response.authorizationCode + "\n make another call to get token ...");

                // File 2nd call to get the token
                mAuthorizationService.performTokenRequest(response.createTokenExchangeRequest(), new AuthorizationService.TokenResponseCallback() {
                    @Override
                    public void onTokenRequestCompleted(@Nullable TokenResponse tokenResponse, @Nullable AuthorizationException exception) {
                        if (tokenResponse != null) {
                            authState.update(tokenResponse, exception);
                            mAuthStateDAL.writeAuthState(authState); //store into persistent storage for use later
                            String text = String.format("Received token response [%s]", tokenResponse.jsonSerializeString());
                            Log.i(TAG, text);
                            gotoAdminActivity();
                            finish();
                        } else {
                            Context context = getApplicationContext();
                            Log.w(TAG, "Token Exchange failed", exception);
                            /*
                            CharSequence text = "Token Exchange failed";
                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            //*/
                        }
                    }
                });
            } else { // come here w/o authorization code. For example, signup finish and user clicks "Back to login"
                Log.d(TAG, "additionalParameter = " + response.additionalParameters.toString());

                if (response.additionalParameters.get("status").equalsIgnoreCase("login_request")) {
                    // ARTIK Cloud instructs the app to display a sign-in form
                    doAuth();
                } else {
                    Log.d(TAG, response.jsonSerialize().toString());
                }
            }

        } else {
            Log.w(TAG, "Authorization Response is null ");
            Log.d(TAG, "Authorization Exception = " + error);
        }
    }
    public void onBtnClick(View view){
        switch (view.getId()){
            // admin_cancel
            case R.id.admin_cancel:
                startActivity(new Intent(this, L_Main_Before.class));
                overridePendingTransition(R.anim.move_top_in, R.anim.move_stay);
                finish();
                break;
            // admin_signin
            case R.id.admin_signin:
                try {
                    Log.v(TAG, ": sign in button is clicked.");
                    doAuth();
                } catch (Exception e) {
                    Log.v(TAG, "Run into Exception");
                    e.printStackTrace();
                }
                break;
            // Sign in cancel
            case R.id.user_cancel:
                startActivity(new Intent(this, L_Main_Before.class));
                overridePendingTransition(R.anim.move_top_in, R.anim.move_stay);
                finish();
                break;
            // Sign in confirm
            case R.id.user_confirm:
                SharedPreferences sf = getSharedPreferences(fName, 0);
                EditText id = (EditText)findViewById(R.id.signin_id);
                EditText pass = (EditText)findViewById(R.id.signin_pass);
                String input_id = id.getText().toString();
                String input_pass = pass.getText().toString();

                Call<Signin> userDataCall = RestfulAdapter.getInstance().userSignin(input_id, input_pass);

                userDataCall.enqueue(new Callback<Signin>() {
                    // Sign in success
                    @Override
                    public void onResponse(Call<Signin> call, Response<Signin> response) {

                        SharedPreferences sf = getSharedPreferences(USER_DATA, 0);
                        SharedPreferences.Editor editor = sf.edit();

                        Boolean signinCheck = response.body().getSignin();

                        if(signinCheck){
                            editor.putBoolean(SIGNIN, true);
                            editor.putString(ACCESS_TOKEN, response.body().getAccessToken());
                            editor.putString(REFRESH_TOKEN, response.body().getRefreshToken());

                            gotoMainActivity();
                        }else{
                            editor.putBoolean(SIGNIN, false);
                        }
                        Log.d(TAG, "Token : " + response.body().getAccessToken());

                        editor.apply();

                    }

                    // Sign in fail
                    @Override
                    public void onFailure(Call<Signin> call, Throwable t) {
                        Log.d(TAG, "onFailure");
                        Toast.makeText(getApplicationContext(), "Network error, 다시 시도하세요.", Toast.LENGTH_LONG).show();

                    }
                });
                break;
        }
    }
    private void gotoAdminActivity() {
        Intent msgActivityIntent = new Intent(this, AdminActivity.class);
        startActivity(msgActivityIntent);
        overridePendingTransition(R.anim.move_right_in, R.anim.move_left_out);
        finish();
    }

    private void gotoMainActivity(){
        // Sign in success, go back to main activity
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.move_right_in, R.anim.move_left_out);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, L_Main_Before.class));
        overridePendingTransition(R.anim.move_top_in, R.anim.move_stay);
        finish();
    }
}

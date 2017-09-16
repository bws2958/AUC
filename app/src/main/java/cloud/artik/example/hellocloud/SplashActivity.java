package cloud.artik.example.hellocloud;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

import java.util.List;

import cloud.artik.example.hellocloud.Util.Retrofit.Response.Recipe;
import cloud.artik.example.hellocloud.Util.Retrofit.RestfulAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static cloud.artik.example.hellocloud.Util.Config.ACCESS_TOKEN;
import static cloud.artik.example.hellocloud.Util.Config.DEVICE_ID_LIST;
import static cloud.artik.example.hellocloud.Util.Config.EMAIL;
import static cloud.artik.example.hellocloud.Util.Config.ID;
import static cloud.artik.example.hellocloud.Util.Config.REFRESH_TOKEN;
import static cloud.artik.example.hellocloud.Util.Config.SIGNIN;
import static cloud.artik.example.hellocloud.Util.Config.USER_DATA;

/**
 * Created by HMS on 2017-08-05.
 */

public class SplashActivity extends Activity {
    private static final String TAG = "SplashActivity";
    Handler h;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        h = new Handler();
        h.postDelayed(run1, 2000);
    }

    Runnable run1 = new Runnable() {
        @Override
        public void run() {

            SharedPreferences sf = getSharedPreferences(USER_DATA, 0);
            Boolean signinCheck = sf.getBoolean(SIGNIN, false);
            if(signinCheck){
                Log.d(TAG, "Signin true");

                String id = sf.getString(ID, "");
                String email = sf.getString(EMAIL, "");
                String accessToken = sf.getString(ACCESS_TOKEN, "");
                String refreshToken = sf.getString(REFRESH_TOKEN, "");
                String deviceId = sf.getString(DEVICE_ID_LIST, "");
                Log.d(TAG, "ID : " + id);
                Log.d(TAG, "Email : " + email);
                Log.d(TAG, "AccessToken : " + accessToken);
                Log.d(TAG, "refreshToken : " + refreshToken);
                Log.d(TAG, "deviceId : " + deviceId);
            }else{
                Log.d(TAG, "Signin false");
            }
            Call<Recipe> call = RestfulAdapter.getInstance().getRecipe();
            call.enqueue(new Callback<Recipe>() {
                @Override
                public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                    Recipe recipe = response.body();

                    List<Recipe.Data> data = recipe.getData();
                    for(int i=0; i<data.size(); i++){
                        Log.d(TAG, data.get(i).getContent());

                    }

                }

                @Override
                public void onFailure(Call<Recipe> call, Throwable t) {

                }
            });



            Intent intent1 = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent1);
            finish();
            //overridePendintTransition -> effect on fade in, fade out, sequence is important
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    public void onBackPressed(){
        super.onBackPressed();
        h.removeCallbacks(run1);
    }
}
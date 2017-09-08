package cloud.artik.example.hellocloud;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cloud.artik.example.hellocloud.Util.Retrofit.Response.Signin;
import cloud.artik.example.hellocloud.Util.Retrofit.Response.Signout;
import cloud.artik.example.hellocloud.Util.Retrofit.RestfulAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static cloud.artik.example.hellocloud.Util.Config.ACCESS_TOKEN;
import static cloud.artik.example.hellocloud.Util.Config.REFRESH_TOKEN;
import static cloud.artik.example.hellocloud.Util.Config.REQ_SIGNIN;
import static cloud.artik.example.hellocloud.Util.Config.REQ_SIGNUP;
import static cloud.artik.example.hellocloud.Util.Config.RES_SUCCESS;
import static cloud.artik.example.hellocloud.Util.Config.SIGNIN;
import static cloud.artik.example.hellocloud.Util.Config.USER_DATA;

/**
 * Created by pc on 2017-09-07.
 */

public class L_MainActivity extends Activity{
    private static final String TAG = "L_MainActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_main);
    }

    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(R.anim.move_right_in, R.anim.move_left_out);
                finish();
                break;
            case R.id.btn_signup:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivityForResult(intent, REQ_SIGNUP);
                break;
            case R.id.btn_signin:
                Intent intent1 = new Intent(this, SignInActivity.class);
                startActivityForResult(intent1, REQ_SIGNIN);
                break;
            case R.id.btn_signout:
                SharedPreferences pref = getSharedPreferences(USER_DATA, 0);
                SharedPreferences.Editor editor = pref.edit();
                // 로그인 데이터 초기화
                editor.clear();
                editor.apply();

                Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_LONG).show();

                break;
        }
    }
    // Event when Back Button Pressed
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.move_right_in, R.anim.move_left_out);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RES_SUCCESS){
            if(requestCode == REQ_SIGNUP){

            }else if(requestCode == REQ_SIGNIN){

            }
            finish();
        }
    }
}

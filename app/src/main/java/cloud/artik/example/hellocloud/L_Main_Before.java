package cloud.artik.example.hellocloud;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import static cloud.artik.example.hellocloud.Util.Config.REQ_SIGNIN;
import static cloud.artik.example.hellocloud.Util.Config.REQ_SIGNUP;
import static cloud.artik.example.hellocloud.Util.Config.RES_SUCCESS;
import static cloud.artik.example.hellocloud.Util.Config.USER_DATA;

/**
 * Created by pc on 2017-09-07.
 * L_Main_Before mean Left side activity on MainActivity with not Sign-in.
 */

public class L_Main_Before extends Activity{
    private static final String TAG = "L_Main_Before";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_main_before);
    }

    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(R.anim.move_right_in, R.anim.move_left_out);
                finish();
                break;
            case R.id.btn_signup:
                startActivity(new Intent(this, SignUpActivity.class));
                finish();
                break;
            case R.id.btn_signin:
                startActivity(new Intent(this, SignInActivity.class));
                finish();
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
        }
    }
}

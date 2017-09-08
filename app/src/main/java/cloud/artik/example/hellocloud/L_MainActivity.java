package cloud.artik.example.hellocloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by pc on 2017-09-07.
 */

public class L_MainActivity extends Activity{
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
                startActivity(intent);
                break;
            case R.id.btn_signin:
                Intent intent1 = new Intent(this, SignInActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_signout:
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
}

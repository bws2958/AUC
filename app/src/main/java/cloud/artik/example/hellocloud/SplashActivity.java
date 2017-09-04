package cloud.artik.example.hellocloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * Created by HMS on 2017-08-05.
 */

public class SplashActivity extends Activity {
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
            Intent intent1 = new Intent(SplashActivity.this, SignInActivity.class);
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
package cloud.artik.example.hellocloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by HMS on 2017-08-25.
 */

public class UserActivity extends Activity  {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    public void onBtnClick(View view){
        switch(view.getId()){
            case R.id.button_logout:
                Intent intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}

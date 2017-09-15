package cloud.artik.example.hellocloud;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cloud.artik.example.hellocloud.Util.CustomList;
import cloud.artik.example.hellocloud.Util.ListAdapter;

import static cloud.artik.example.hellocloud.Util.Config.REQ_SIGNIN;
import static cloud.artik.example.hellocloud.Util.Config.REQ_SIGNUP;
import static cloud.artik.example.hellocloud.Util.Config.RES_SUCCESS;
import static cloud.artik.example.hellocloud.Util.Config.USER_DATA;

/**
 * Created by bws29 on 2017-09-08.
 * L_Main_After mean Left side activity on MainActivity with successfully Sign-in.
 */

public class L_Main_After extends Activity {
    private static final String TAG = "L_Main_After";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_main_after);

        ArrayList<CustomList> customLists = new ArrayList<CustomList>();
        customLists.add(new CustomList("123123"));
        customLists.add(new CustomList("321321"));
        ListView listView = (ListView)findViewById(R.id.main_after_left_listView);
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(), R.layout.main_after_left_list_item, customLists);
        listView.setAdapter(listAdapter);
    }

    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(R.anim.move_right_in, R.anim.move_left_out);
                finish();
                break;
            case R.id.btn_device_enroll:
                startActivity(new Intent(this, Device_Enroll_Activity.class));
                finish();
                break;
            case R.id.btn_my_info:
                startActivity(new Intent(this, My_Info_Activity.class));
                finish();
                break;
            case R.id.btn_signout:
                SharedPreferences pref = getSharedPreferences(USER_DATA, 0);
                SharedPreferences.Editor editor = pref.edit();
                // 로그인 데이터 초기화
                editor.clear();
                editor.apply();

                Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(R.anim.move_right_in, R.anim.move_left_out);
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

package cloud.artik.example.hellocloud;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import cloud.artik.example.hellocloud.Util.CustomEditText;

import cloud.artik.example.hellocloud.Util.ViewPager2.view2_pager1;
import cloud.artik.example.hellocloud.Util.ViewPager2.view2_pager2;
import cloud.artik.example.hellocloud.Util.ViewPager2.view2_pager3;
import cloud.artik.example.hellocloud.Util.ViewPager2.view2_pager4;
import cloud.artik.example.hellocloud.Util.ViewPagerAdapter;
import cloud.artik.example.hellocloud.Util.ViewPager1.view1_pager1;
import cloud.artik.example.hellocloud.Util.ViewPager1.view1_pager2;
import cloud.artik.example.hellocloud.Util.ViewPager1.view1_pager3;
import cloud.artik.example.hellocloud.Util.ViewPager1.view1_pager4;
import cloud.artik.example.hellocloud.Util.ViewPager1.view1_pager5;
import cloud.artik.example.hellocloud.Util.ViewPager1.view1_pager6;

import static cloud.artik.example.hellocloud.Util.Config.AUC_Suges_str;
import static cloud.artik.example.hellocloud.Util.Config.SIGNIN;
import static cloud.artik.example.hellocloud.Util.Config.USER_DATA;
import static cloud.artik.example.hellocloud.Util.Config.User_Hot_str;

/**
 * Created by pc on 2017-09-01.
 */

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private static final String TAG = "MainActivity";
    private static final int NUM_PAGES = 3;
    private ViewPager viewPager1;
    private ViewPager viewPager2;
    private TabHost tabHost1;
    private TabHost tabHost2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCustomEditText();
        initViewPager();
        initTabHost();

    }

    private void initTabHost(){
        tabHost1 = (TabHost) findViewById(R.id.main_tab1);
        tabHost1.setup();

        tabHost2 = (TabHost) findViewById(R.id.main_tab2);
        tabHost2.setup();

        TabHost.TabSpec tabSpec1;
        TabHost.TabSpec tabSpec2;

        for(int i=0; i < AUC_Suges_str.length; i++){
            tabSpec1 = tabHost1.newTabSpec(AUC_Suges_str[i]);
            tabSpec1.setIndicator(AUC_Suges_str[i]);
            tabSpec1.setContent(new FakeContent(getApplicationContext()));
            tabHost1.addTab(tabSpec1);
        }
        tabHost1.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int item1 = tabHost1.getCurrentTab();
                viewPager1.setCurrentItem(item1);
                HorizontalScrollView horizontalScrollView1 = (HorizontalScrollView) findViewById(R.id.main_h_scroll1);
                View tabView1 = tabHost1.getCurrentTabView();
                int pos1 = tabView1.getLeft() - (horizontalScrollView1.getWidth() - tabView1.getWidth()) / 2;
                horizontalScrollView1.smoothScrollTo(pos1, 0);
            }
        });

        for(int i=0; i < User_Hot_str.length; i++){
            tabSpec2 = tabHost2.newTabSpec(User_Hot_str[i]);
            tabSpec2.setIndicator(User_Hot_str[i]);
            tabSpec2.setContent(new FakeContent(getApplicationContext()));
            tabHost2.addTab(tabSpec2);
        }

        tabHost2.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int item2 = tabHost2.getCurrentTab();
                viewPager2.setCurrentItem(item2);
                HorizontalScrollView horizontalScrollView2 = (HorizontalScrollView) findViewById(R.id.main_h_scroll2);
                View tabView2 = tabHost2.getCurrentTabView();
                int pos2 = tabView2.getLeft() - (horizontalScrollView2.getWidth() - tabView2.getWidth()) / 2;
                horizontalScrollView2.smoothScrollTo(pos2, 0);
            }
        });
    }

    public class FakeContent implements TabHost.TabContentFactory {
        Context context;

        public FakeContent(Context mcontext){
            context = mcontext;
        }

        @Override
        public View createTabContent(String tag) {
            View fakeView = new View(context);
            fakeView.setMinimumWidth(0);
            fakeView.setMinimumHeight(0);
            return fakeView;
        }
    }

    private void initViewPager(){
        viewPager1 = (ViewPager) findViewById(R.id.main_viewpager1);
        viewPager2 = (ViewPager) findViewById(R.id.main_viewpager2);

        List<Fragment> list1 = new ArrayList<Fragment>();
        list1.add(new view1_pager1());
        list1.add(new view1_pager2());
        list1.add(new view1_pager3());
        list1.add(new view1_pager4());
        list1.add(new view1_pager5());
        list1.add(new view1_pager6());

        ViewPagerAdapter viewPagerAdapter1 = new ViewPagerAdapter(getSupportFragmentManager(), list1);
        viewPager1.setAdapter(viewPagerAdapter1);
        viewPager1.setOnPageChangeListener(this);

        List<Fragment> list2 = new ArrayList<Fragment>();
        list2.add(new view2_pager1());
        list2.add(new view2_pager2());
        list2.add(new view2_pager3());
        list2.add(new view2_pager4());

        ViewPagerAdapter viewPagerAdapter2 = new ViewPagerAdapter(getSupportFragmentManager(), list2);
        viewPager2.setAdapter(viewPagerAdapter2);
        viewPager2.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabHost1.setCurrentTab(position);
        tabHost2.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initCustomEditText(){
        CustomEditText customEditText = (CustomEditText)findViewById(R.id.editText1);
        customEditText.addTextChangedListener(editTextChanged());
    }

    private CustomEditText.TextChangedListener editTextChanged() {
        return new CustomEditText.TextChangedListener() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.btn_list:
                SharedPreferences sf = getSharedPreferences(USER_DATA, 0);
                Boolean stat_signin = sf.getBoolean(SIGNIN, false);
                if(stat_signin == true){ // sign-in success
                    startActivity(new Intent(this, L_Main_After.class));
                    overridePendingTransition(R.anim.move_left_in, R.anim.move_right_out);
                    Log.d(TAG, "Signin true status");
                }else{ // sign-in fail
                      startActivity(new Intent(this, L_Main_Before.class));
                    overridePendingTransition(R.anim.move_left_in, R.anim.move_right_out);
                    Log.d(TAG, "Signin false status");
                }
                finish();
                break;
            case R.id.btn_search:
                Intent intent1 = new Intent(this, SearchActivity.class);
                startActivity(intent1);
                break;
        }
    }
}

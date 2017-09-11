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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import cloud.artik.example.hellocloud.Util.CustomEditText;

import cloud.artik.example.hellocloud.Util.Tab1_Pager1.tab1_pager1_frame1;
import cloud.artik.example.hellocloud.Util.Tab1_Pager1.tab1_pager1_frame2;
import cloud.artik.example.hellocloud.Util.Tab1_Pager1.tab1_pager1_frame3;
import cloud.artik.example.hellocloud.Util.Tab1_Pager1.tab1_pager1_frame4;
import cloud.artik.example.hellocloud.Util.Tab1_Pager1.tab1_pager1_frame5;
import cloud.artik.example.hellocloud.Util.Tab1_Pager1.tab1_pager1_frame6;
import cloud.artik.example.hellocloud.Util.Tab1_Pager2.tab1_pager2_frame1;
import cloud.artik.example.hellocloud.Util.Tab1_Pager2.tab1_pager2_frame2;
import cloud.artik.example.hellocloud.Util.Tab1_Pager2.tab1_pager2_frame3;
import cloud.artik.example.hellocloud.Util.Tab1_Pager2.tab1_pager2_frame4;
import cloud.artik.example.hellocloud.Util.ViewPagerAdapter;

import static cloud.artik.example.hellocloud.Util.Config.SIGNIN;
import static cloud.artik.example.hellocloud.Util.Config.Tab_list;
import static cloud.artik.example.hellocloud.Util.Config.USER_DATA;

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


        TabHost.TabSpec tabSpec1;

        for(int i=0; i < Tab_list.length; i++){
            tabSpec1 = tabHost1.newTabSpec(Tab_list[i]);
            tabSpec1.setIndicator(Tab_list[i]);
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
        viewPager1 = (ViewPager) findViewById(R.id.main_viewpager);
//        viewPager2 = (ViewPager) findViewById(R.id.main_viewpager2);

        List<Fragment> list1 = new ArrayList<Fragment>();
        list1.add(new view_tab1());
        list1.add(new view_tab2());
        list1.add(new view_tab3());
        list1.add(new view_tab4());
        list1.add(new view_tab5());
        list1.add(new view_tab6());

        ViewPagerAdapter viewPagerAdapter1 = new ViewPagerAdapter(getSupportFragmentManager(), list1);
        viewPager1.setAdapter(viewPagerAdapter1);
        viewPager1.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        tabHost1.setCurrentTab(position);
//        tabHost2.setCurrentTab(position);
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
//            case R.id.main_viewpager1:
//                break;
//            case R.id.main_viewpager2:
//                break;
        }
    }

    public static class view_tab1 extends Fragment {
        private ViewPager viewPager1;
        private ViewPager viewPager2;
        public view_tab1(){
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            View view = inflater.inflate(R.layout.main_vpg1, container, false);
            LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.main_vpg1, container, false);

            viewPager1 = (ViewPager) linearLayout.findViewById(R.id.main_viewpager1);

            List<Fragment> list1 = new ArrayList<Fragment>();
            list1.add(new tab1_pager1_frame1());
            list1.add(new tab1_pager1_frame2());
            list1.add(new tab1_pager1_frame3());
            list1.add(new tab1_pager1_frame4());
            list1.add(new tab1_pager1_frame5());
            list1.add(new tab1_pager1_frame6());

            ViewPagerAdapter viewPagerAdapter1 = new ViewPagerAdapter(getChildFragmentManager(), list1);
            viewPager1.setAdapter(viewPagerAdapter1);

            viewPager2 = (ViewPager) linearLayout.findViewById(R.id.main_viewpager2);

            List<Fragment> list2 = new ArrayList<Fragment>();
            list2.add(new tab1_pager2_frame1());
            list2.add(new tab1_pager2_frame2());
            list2.add(new tab1_pager2_frame3());
            list2.add(new tab1_pager2_frame4());

            ViewPagerAdapter viewPagerAdapter2 = new ViewPagerAdapter(getChildFragmentManager(), list2);
            viewPager2.setAdapter(viewPagerAdapter2);

            return linearLayout;
        }
    }

    public static class view_tab1_pager1 extends Fragment{
        private ViewPager viewPager;
        public view_tab1_pager1(){
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.main_vpg1, container, false);

            viewPager = (ViewPager) view.findViewById(R.id.main_viewpager1);

//            List<Fragment> list = new ArrayList<Fragment>();
//            list.add(new tab1_pager1_frame1());
//            list.add(new tab1_pager1_frame2());
//            list.add(new tab1_pager1_frame3());
//            list.add(new tab1_pager1_frame4());
//            list.add(new tab1_pager1_frame5());
//            list.add(new tab1_pager1_frame6());

//            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), list);
//            viewPager.setAdapter(viewPagerAdapter);

            return view;
        }
    }

    public static class view_tab1_pager2 extends Fragment{
        private ViewPager viewPager;
        public view_tab1_pager2(){
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.main_vpg1, container, false);

            viewPager = (ViewPager) view.findViewById(R.id.main_viewpager2);

            List<Fragment> list = new ArrayList<Fragment>();
            list.add(new tab1_pager2_frame1());
            list.add(new tab1_pager2_frame2());
            list.add(new tab1_pager2_frame3());
            list.add(new tab1_pager2_frame4());

            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), list);
            viewPager.setAdapter(viewPagerAdapter);

            return view;
        }
    }

    public static class view_tab2 extends Fragment {
        private ViewPager viewPager;
        public view_tab2(){}

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_main, container, false);

            viewPager = (ViewPager) view.findViewById(R.id.main_viewpager);

//            List<Fragment> list1 = new ArrayList<Fragment>();
//            list1.add(new tab1_pager2_frame1());
//            list1.add(new tab1_pager2_frame2());
//            list1.add(new tab1_pager2_frame3());
//            list1.add(new tab1_pager2_frame4());
//            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), list1);
//            viewPager.setAdapter(viewPagerAdapter);
            return view;
        }
    }

    public static class view_tab3 extends Fragment {
        private ViewPager viewPager;
        public view_tab3(){}

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.main_vpg1, container, false);

            viewPager = (ViewPager) view.findViewById(R.id.main_viewpager1);

//            List<Fragment> list1 = new ArrayList<Fragment>();
//            list1.add(new tab1_pager2_frame1());
//            list1.add(new tab1_pager2_frame2());
//            list1.add(new tab1_pager2_frame3());
//            list1.add(new tab1_pager2_frame4());
//            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), list1);
//            viewPager.setAdapter(viewPagerAdapter);
            return view;
        }
    }

    public static class view_tab4 extends Fragment {
        private ViewPager viewPager;
        public view_tab4(){}

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.main_vpg1, container, false);

            viewPager = (ViewPager) view.findViewById(R.id.main_viewpager1);

//            List<Fragment> list1 = new ArrayList<Fragment>();
//            list1.add(new tab1_pager2_frame1());
//            list1.add(new tab1_pager2_frame2());
//            list1.add(new tab1_pager2_frame3());
//            list1.add(new tab1_pager2_frame4());
//            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), list1);
//            viewPager.setAdapter(viewPagerAdapter);
            return view;
        }
    }

    public static class view_tab5 extends Fragment {
        private ViewPager viewPager;
        public view_tab5(){}

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.main_vpg1, container, false);

            viewPager = (ViewPager) view.findViewById(R.id.main_viewpager1);

//            List<Fragment> list1 = new ArrayList<Fragment>();
//            list1.add(new tab1_pager2_frame1());
//            list1.add(new tab1_pager2_frame2());
//            list1.add(new tab1_pager2_frame3());
//            list1.add(new tab1_pager2_frame4());
//            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), list1);
//            viewPager.setAdapter(viewPagerAdapter);
            return view;
        }
    }

    public static class view_tab6 extends Fragment {
        private ViewPager viewPager;
        public view_tab6(){}

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.main_vpg1, container, false);

            viewPager = (ViewPager) view.findViewById(R.id.main_viewpager1);

//            List<Fragment> list1 = new ArrayList<Fragment>();
//            list1.add(new tab1_pager2_frame1());
//            list1.add(new tab1_pager2_frame2());
//            list1.add(new tab1_pager2_frame3());
//            list1.add(new tab1_pager2_frame4());
//            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), list1);
//            viewPager.setAdapter(viewPagerAdapter);
            return view;
        }
    }
}

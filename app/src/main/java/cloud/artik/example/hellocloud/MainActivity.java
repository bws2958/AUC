package cloud.artik.example.hellocloud;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.text.Editable;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import cloud.artik.example.hellocloud.Util.CustomEditText;
import cloud.artik.example.hellocloud.Util.ViewPager.ViewPagerAdapter;
import cloud.artik.example.hellocloud.Util.ViewPager.main_pager1;
import cloud.artik.example.hellocloud.Util.ViewPager.main_pager2;
import cloud.artik.example.hellocloud.Util.ViewPager.main_pager3;
import cloud.artik.example.hellocloud.Util.ViewPager.main_pager4;
import cloud.artik.example.hellocloud.Util.ViewPager.main_pager5;
import cloud.artik.example.hellocloud.Util.ViewPager.main_pager6;

/**
 * Created by pc on 2017-09-01.
 */

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener{
    private static final int NUM_PAGES = 3;
    private ViewPager viewPager;
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCustomEditText();
        initViewPager();
        initTabHost();
    }

    private void initTabHost(){
        tabHost = (TabHost) findViewById(R.id.main_tab1);
        tabHost.setup();

        String[] tab_content = {"tab1", "tab2", "tab3", "tab4", "tab5", "tab6"};

        for(int i=0; i < tab_content.length; i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec(tab_content[i]);
            tabSpec.setIndicator(tab_content[i]);
            tabSpec.setContent(new FakeContent(getApplicationContext()));
            tabHost.addTab(tabSpec);
        }
        tabHost.setOnTabChangedListener(this);
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
        viewPager = (ViewPager) findViewById(R.id.main_viewpager1);

        List<Fragment> list = new ArrayList<Fragment>();
        list.add(new main_pager1());
        list.add(new main_pager2());
        list.add(new main_pager3());
        list.add(new main_pager4());
        list.add(new main_pager5());
        list.add(new main_pager6());

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    // tabHostListener
    @Override
    public void onTabChanged(String tabId) {
        int item = tabHost.getCurrentTab();
        viewPager.setCurrentItem(item);

        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) findViewById(R.id.main_h_scroll1);
        View tabView = tabHost.getCurrentTabView();
        int pos = tabView.getLeft() - (horizontalScrollView.getWidth() - tabView.getWidth()) / 2;
        horizontalScrollView.smoothScrollTo(pos, 0);
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
                startActivity(new Intent(this, L_MainActivity.class));
                overridePendingTransition(R.anim.move_left_in, R.anim.move_right_out);
                finish();
                break;
            case R.id.btn_search:
                Intent intent1 = new Intent(this, SearchActivity.class);
                startActivity(intent1);
                break;
        }
    }
}

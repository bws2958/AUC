package cloud.artik.example.hellocloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.text.Editable;

import cloud.artik.example.hellocloud.Util.CustomEditText;
import cloud.artik.example.hellocloud.Util.ViewPager.main_pager1;
import cloud.artik.example.hellocloud.Util.ViewPager.main_pager2;
import cloud.artik.example.hellocloud.Util.ViewPager.main_pager3;

/**
 * Created by pc on 2017-09-01.
 */

public class MainActivity extends AppCompatActivity {
    private static final int NUM_PAGES = 3;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomEditText customEditText = (CustomEditText)findViewById(R.id.editText1);
        customEditText.addTextChangedListener(editTextChanged());

        viewPager = (ViewPager) findViewById(R.id.main_viewpager1);
        viewPager.setAdapter(new SlidePagerAdapter(getSupportFragmentManager()));
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

    private class SlidePagerAdapter extends FragmentPagerAdapter{
        public SlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }
        @Override
        public Fragment getItem (int position){
            if (position == 0)
                return new main_pager1();
            else if (position == 1)
                return new main_pager2();
            else // position == 2
                return new main_pager3();

        }
        @Override
        public int getCount(){
            return NUM_PAGES;
        }
    }

}

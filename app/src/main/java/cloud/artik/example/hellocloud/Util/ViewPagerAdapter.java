package cloud.artik.example.hellocloud.Util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by pc on 2017-09-06.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> list;

    public ViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.list = list;
    }
    @Override
    public Fragment getItem (int position){return list.get(position);
    }
    @Override
    public int getCount(){
        return list.size();
    }
}

package cloud.artik.example.hellocloud.Util.ViewPager2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cloud.artik.example.hellocloud.R;

/**
 * Created by bws29 on 2017-09-09.
 */

public class view2_pager1 extends Fragment {
    public view2_pager1(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.main_vpg2_frame1, container, false);
        return linearLayout;
    }
}

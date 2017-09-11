package cloud.artik.example.hellocloud.Util.Tab1_Pager1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cloud.artik.example.hellocloud.R;

/**
 * Created by pc on 2017-09-07.
 */

public class tab1_pager1_frame5 extends Fragment {
    public tab1_pager1_frame5(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.main_vpg1_frame5, container, false);
        return linearLayout;
    }
}

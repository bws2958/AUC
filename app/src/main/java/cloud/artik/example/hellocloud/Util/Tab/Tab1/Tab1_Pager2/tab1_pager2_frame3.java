package cloud.artik.example.hellocloud.Util.Tab.Tab1.Tab1_Pager2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cloud.artik.example.hellocloud.R;

/**
 * Created by bws29 on 2017-09-09.
 */

public class tab1_pager2_frame3 extends Fragment {
    public tab1_pager2_frame3(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.frame_imageview, container, false);

        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.seul_gii);
        imageView.setImageResource(R.drawable.seul_gi);
        return linearLayout;
    }
}

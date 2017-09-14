package cloud.artik.example.hellocloud.Util.Helper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cloud.artik.example.hellocloud.R;

/**
 * Created by bws29 on 2017-09-13.
 */


public class FrameHelper extends Fragment{
    private int layout = R.layout.frame_imageview;
    private int image = 0;

    public FrameHelper(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(layout, container, false);

        Bundle bundle = getArguments();

        if(bundle != null){
            image = bundle.getInt("param1");
            ImageView imageView = (ImageView) linearLayout.findViewById(R.id.seul_gii);
            imageView.setImageResource(image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri;
                    Intent intent;
                    switch (image){
                        case R.drawable.bean_noodle:
                            uri = Uri.parse("https://www.nuc.co.kr/community/bbs/board.php?bo_table=recipe&wr_id=133");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;
                        case R.drawable.solo_kimchi:
                            uri = Uri.parse("https://www.nuc.co.kr/community/bbs/board.php?bo_table=recipe&wr_id=134");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;
                        case R.drawable.green_juice:
                            uri = Uri.parse("https://www.nuc.co.kr/community/bbs/board.php?bo_table=recipe&wr_id=121&page=3");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;
                        case R.drawable.apple_jam:
                            uri = Uri.parse("https://www.nuc.co.kr/community/bbs/board.php?bo_table=recipe&wr_id=117&page=3");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;
                        case R.drawable.blue_blood:
                            uri = Uri.parse("https://www.nuc.co.kr/community/bbs/board.php?bo_table=recipe&wr_id=115&page=3");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;
                        case R.drawable.bit_lemon:
                            uri = Uri.parse("https://www.nuc.co.kr/community/bbs/board.php?bo_table=recipe&wr_id=113&page=3");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;
                    }
                }
            });
        }

        return linearLayout;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

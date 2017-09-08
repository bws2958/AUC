package cloud.artik.example.hellocloud.Util.Helper;

import android.view.KeyEvent;
import android.view.View;

/**
 * Created by bws29 on 2017-09-08.
 */

public class KeyLimitHelper implements View.OnKeyListener{
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_ENTER)
            return true;

        return false;
    }
}

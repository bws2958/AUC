package cloud.artik.example.hellocloud.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

/**
 * Created by bws29 on 2017-09-04.
 */

public class ImageHelper {
    private View view;
    private Bitmap bitmap;
    private int target;
    private int new_width;
    private int new_height;

    public ImageHelper(View view, Bitmap bitmap, int target, int new_width, int new_height) {
        this.bitmap = bitmap;
        this.target = target;
        this.view = view;
        this.new_width = new_width;
        this.new_height = new_height;
        bitInit(view, bitmap, target);
    }

    private BitmapDrawable bitInit(View view, Bitmap bitmap,  int target){
        bitmap = BitmapFactory.decodeResource(view.getResources(), target);

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = ((float)this.new_width / width);
        float scaleHeight = ((float)this.new_height / height);

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap new_bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        BitmapDrawable draw = new BitmapDrawable(new_bitmap);

        return draw;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}

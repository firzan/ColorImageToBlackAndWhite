package com.colortograyscale;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
/**
 * 
 * @author Firzan Ghulam
 *
 */
public class ColorImageToBlackAndWhiteImageActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView orginalImageView = (ImageView) findViewById(R.id.image);
        ImageView blackImageView = (ImageView) findViewById(R.id.blackimage);
        Bitmap sample = BitmapFactory.decodeResource(getResources(),
                R.drawable.globalwarming_l);
        orginalImageView.setBackgroundDrawable(new BitmapDrawable(sample));
        blackImageView.setBackgroundDrawable(new BitmapDrawable(test(sample)));
    }
public static Bitmap test(Bitmap src){
      int width = src.getWidth();
        int height = src.getHeight();
        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
        // color information
        int A, R, G, B;
        int pixel;
    	for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // get pixel color
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                int gray = (int) (0.2989 * R + 0.5870 * G + 0.1140 * B);
                // use 128 as threshold, above -> white, below -> black
                if (gray > 128) {
                    gray = 255;
                }
                else{
                    gray = 0;
                }
                    // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, gray, gray, gray));
            }
        }
		return bmOut;
	}
}

package ledbanner.ledboard.ledscrollingtext.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.textview.MaterialTextView;
import ledbanner.ledboard.ledscrollingtext.R;


public class ImagePagerAdapter extends PagerAdapter {

    int imageArray[];
    String[] stringArray;
    String[] stringarr;


    public ImagePagerAdapter(Activity act, int[] imgArra, String[] stringArra, String[] stringArray1) {
        imageArray = imgArra;
        stringArray = stringArra;
        stringarr = stringArray1;
    }

    public int getCount() {
        return imageArray.length;
    }

    public Object instantiateItem(View collection, int position) {
        LayoutInflater inflater = (LayoutInflater) collection.getContext
                ().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.customitem, null);

        AppCompatImageView im = (AppCompatImageView) layout.findViewById(R.id.myimage);
        im.setImageResource(imageArray[position]);

        MaterialTextView txt = (MaterialTextView) layout.findViewById(R.id.txt1);
        MaterialTextView txt1 = (MaterialTextView) layout.findViewById(R.id.txt2);
        txt.setText(stringArray[position]);
        txt1.setText(stringarr[position]);
        ((ViewPager) collection).addView(layout, 0);
        return layout;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}

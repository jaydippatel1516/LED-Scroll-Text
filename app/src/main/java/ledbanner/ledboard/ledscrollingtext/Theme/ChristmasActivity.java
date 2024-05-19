package ledbanner.ledboard.ledscrollingtext.Theme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.material.textview.MaterialTextView;
import ledbanner.ledboard.ledscrollingtext.Adapter.ThemeAdp;
import ledbanner.ledboard.ledscrollingtext.AppUtils.ThemeData;
import ledbanner.ledboard.ledscrollingtext.R;
import ledbanner.ledboard.ledscrollingtext.admodule.AllAdCommonClass;

import java.util.ArrayList;

public class ChristmasActivity extends AppCompatActivity {
    MaterialTextView themename;
    RecyclerView rcl_themeitem;
    ThemeAdp adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_christmas);
        findViewById(R.id.ivBack).setOnClickListener(v -> onBackPressed());
        themename = findViewById(R.id.themename);
        ShimmerFrameLayout shimmer_view_container = findViewById(R.id.shimmer_view_container);
        AllAdCommonClass.NativeLoad(this, (TemplateView) findViewById(R.id.my_template), findViewById(R.id.fram_fbnative), shimmer_view_container);
        rcl_themeitem = findViewById(R.id.rcl_themeitem);
        ArrayList<ThemeData> data = new ArrayList<>();

        data.add(new ThemeData(R.drawable.thumb_img_christmas_02,R.drawable.img_christmas_02));
        data.add(new ThemeData(R.drawable.thumb_img_christmas_04,R.drawable.img_christmas_04));
        data.add(new ThemeData(R.drawable.thumb_img_christmas_05,R.drawable.img_christmas_05));
        data.add(new ThemeData(R.drawable.thumb_img_christmas_06,R.drawable.img_christmas_06));
        data.add(new ThemeData(R.drawable.thumb_img_christmas_07,R.drawable.img_christmas_07));
        data.add(new ThemeData(R.drawable.thumb_img_christmas_08,R.drawable.img_christmas_08));
        data.add(new ThemeData(R.drawable.thumb_img_christmas_09,R.drawable.img_christmas_09));
        data.add(new ThemeData(R.drawable.thumb_img_christmas_01,R.drawable.img_christmas_01));
        data.add(new ThemeData(R.drawable.thumb_img_christmas_10,R.drawable.img_christmas_10));
        adapter = new ThemeAdp(this, data);
        rcl_themeitem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
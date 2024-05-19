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

public class HappyAnniversaryActivity extends AppCompatActivity {
    MaterialTextView themename;
    RecyclerView rcl_themeitem;
    ThemeAdp adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_anniversary);
        themename = findViewById(R.id.themename);
        ShimmerFrameLayout shimmer_view_container = findViewById(R.id.shimmer_view_container);
        AllAdCommonClass.NativeLoad(this, (TemplateView) findViewById(R.id.my_template), findViewById(R.id.fram_fbnative), shimmer_view_container);
        findViewById(R.id.ivBack).setOnClickListener(v -> onBackPressed());
        rcl_themeitem = findViewById(R.id.rcl_themeitem);
        ArrayList<ThemeData> data = new ArrayList<>();

        data.add(new ThemeData(R.drawable.thumb_img_happy_anniversary_01,R.drawable.img_happy_anniversary_01));
        data.add(new ThemeData(R.drawable.thumb_img_happy_anniversary_02,R.drawable.img_happy_anniversary_02));
        data.add(new ThemeData(R.drawable.thumb_img_happy_anniversary_03,R.drawable.img_happy_anniversary_03));
        data.add(new ThemeData(R.drawable.thumb_img_happy_anniversary_04,R.drawable.img_happy_anniversary_04));
        adapter = new ThemeAdp(this, data);
        rcl_themeitem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
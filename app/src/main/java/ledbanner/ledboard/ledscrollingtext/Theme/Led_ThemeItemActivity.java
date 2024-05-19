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

public class Led_ThemeItemActivity extends AppCompatActivity {
    MaterialTextView themename;
    RecyclerView rcl_themeitem;
    ThemeAdp adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_theme_item);
        themename = findViewById(R.id.themename);
        ShimmerFrameLayout shimmer_view_container = findViewById(R.id.shimmer_view_container);
        AllAdCommonClass.NativeLoad(this, (TemplateView) findViewById(R.id.my_template), findViewById(R.id.fram_fbnative), shimmer_view_container);

        findViewById(R.id.ivBack).setOnClickListener(v -> onBackPressed());
        rcl_themeitem = findViewById(R.id.rcl_themeitem);
        ArrayList<ThemeData> data = new ArrayList<>();

        data.add(new ThemeData(R.drawable.thumb_img_birthday_02,R.drawable.img_birthday_02));
        data.add(new ThemeData(R.drawable.thumb_img_birthday_03,R.drawable.img_birthday_03));
        data.add(new ThemeData(R.drawable.thumb_img_birthday_04,R.drawable.img_birthday_04));
        data.add(new ThemeData(R.drawable.thumb_img_birthday_05,R.drawable.img_birthday_05));
        data.add(new ThemeData(R.drawable.thumb_img_birthday_06,R.drawable.img_birthday_06));
        data.add(new ThemeData(R.drawable.thumb_img_birthday_07,R.drawable.img_birthday_07));
        data.add(new ThemeData(R.drawable.thumb_img_birthday_08,R.drawable.img_birthday_08));
        data.add(new ThemeData(R.drawable.thumb_img_birthday_09,R.drawable.img_birthday_09));
        data.add(new ThemeData(R.drawable.thumb_img_birthday_10,R.drawable.img_birthday_10));
        adapter = new ThemeAdp(this, data);
        rcl_themeitem.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}
package ledbanner.ledboard.ledscrollingtext.Theme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.ads.nativetemplates.TemplateView;

import ledbanner.ledboard.ledscrollingtext.Adapter.ViewHistoryAdapter;
import ledbanner.ledboard.ledscrollingtext.Model.Video;
import ledbanner.ledboard.ledscrollingtext.R;
import ledbanner.ledboard.ledscrollingtext.admodule.AllAdCommonClass;

import java.io.File;
import java.util.ArrayList;

public class BannerViewHistoryActivity extends AppCompatActivity {
    RecyclerView viewhistory;
    ArrayList<Video> arrayList1 = new ArrayList<>();
    String path1 = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/LED TEXT/Banner/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_view_history);
        ShimmerFrameLayout shimmer_view_container = findViewById(R.id.shimmer_view_container);
        AllAdCommonClass.NativeLoad(this, (TemplateView) findViewById(R.id.my_template), findViewById(R.id.fram_fbnative), shimmer_view_container);
        findViewById(R.id.ivBack).setOnClickListener(v -> onBackPressed());
        viewhistory = findViewById(R.id.viewhistory);
        getVideos();

    }


    private void getVideos() {
        arrayList1.clear();
        String path = path1;
        File file = new File(path);
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.getPath().endsWith(".mp4")) {
                    arrayList1.add(new Video(file1.getPath()));
                }
            }
        }
        ViewHistoryAdapter adapter = new ViewHistoryAdapter(BannerViewHistoryActivity.this, arrayList1);
        viewhistory.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getVideos();
    }
}
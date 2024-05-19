package ledbanner.ledboard.ledscrollingtext.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.ads.nativetemplates.TemplateView;

import ledbanner.ledboard.ledscrollingtext.Adapter.BannerAdapter;
import ledbanner.ledboard.ledscrollingtext.Adapter.HistoryAdapter;
import ledbanner.ledboard.ledscrollingtext.AppUtils.App;
import ledbanner.ledboard.ledscrollingtext.Model.Video;
import ledbanner.ledboard.ledscrollingtext.R;
import ledbanner.ledboard.ledscrollingtext.Theme.Led_ThemeActivity;
import ledbanner.ledboard.ledscrollingtext.admodule.AllAdCommonClass;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class Led_HomeActivity extends AppCompatActivity {
    RecyclerView history, banner;
    String videoPath = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/LED TEXT/");
    String path1 = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/LED TEXT/Banner/");


    ArrayList<Video> arrayList;
    ArrayList<Video> arrayList1;

    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources();
        setContentView(R.layout.activity_led_home);
        AllAdCommonClass.showADMFBInterstial(this);
        ShimmerFrameLayout shimmer_view_container = findViewById(R.id.shimmer_view_container);
        AllAdCommonClass.NativeLoad(this, (TemplateView) findViewById(R.id.my_template), findViewById(R.id.fram_fbnative), shimmer_view_container);

        history = findViewById(R.id.history);
        banner = findViewById(R.id.banner);
        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();
        getVideos();
        getBanner();
        findViewById(R.id.btncreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_HomeActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        startActivity(new Intent(Led_HomeActivity.this, Led_EditActivity.class));

                    }
                });
            }
        });
        findViewById(R.id.btntheme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_HomeActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        startActivity(new Intent(Led_HomeActivity.this, Led_ThemeActivity.class));

                    }
                });
            }
        });
        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_HomeActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        startActivity(new Intent(Led_HomeActivity.this, Led_settingActivity.class));

                    }
                });
            }
        });
    }

    public void updateResources() {
        Locale locale = new Locale(App.getInstance().getStoreStringValue("pref_selected_language"));
        Locale.setDefault(locale);
        Configuration configuration = getResources().getConfiguration();
        createConfigurationContext(configuration);
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
    }

    private void getVideos() {
        arrayList.clear();
        String path = videoPath;
        File file = new File(path);
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.getPath().endsWith(".mp4")) {
                    arrayList.add(new Video(file1.getPath()));
                }
            }
        }
        if (arrayList != null && !arrayList.isEmpty()) {
            HistoryAdapter adapter = new HistoryAdapter(Led_HomeActivity.this, arrayList);
            history.setAdapter(adapter);
        }
    }

    private void getBanner() {
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
        if (arrayList1 != null && !arrayList1.isEmpty()) {
            BannerAdapter adapter = new BannerAdapter(Led_HomeActivity.this, arrayList1);
            banner.setAdapter(adapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        getVideos();
        getBanner();

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
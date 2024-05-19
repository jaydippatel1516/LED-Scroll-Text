package ledbanner.ledboard.ledscrollingtext.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.ads.nativetemplates.TemplateView;

import ledbanner.ledboard.ledscrollingtext.AppUtils.App;
import ledbanner.ledboard.ledscrollingtext.R;
import ledbanner.ledboard.ledscrollingtext.admodule.AllAdCommonClass;

import java.util.Locale;

public class Led_settingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources();
        setContentView(R.layout.activity_led_setting);
        AllAdCommonClass.showADMFBInterstial(this);
        ShimmerFrameLayout shimmer_view_container = findViewById(R.id.shimmer_view_container);
        AllAdCommonClass.NativeLoad(this, (TemplateView) findViewById(R.id.my_template), findViewById(R.id.fram_fbnative), shimmer_view_container);
        findViewById(R.id.back).setOnClickListener(v -> onBackPressed());
        findViewById(R.id.language).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_settingActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        startActivity(new Intent(Led_settingActivity.this, Led_LanguageActivity.class));
                    }
                });
            }
        });
        findViewById(R.id.pp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_settingActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        startActivity(new Intent(Led_settingActivity.this, PrivacyActivity.class));

                    }
                });
            }
        });
        findViewById(R.id.rate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                }
            }
        });
        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + getPackageName() + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
}
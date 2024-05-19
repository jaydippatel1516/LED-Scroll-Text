package ledbanner.ledboard.ledscrollingtext.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import ledbanner.ledboard.ledscrollingtext.AppUtils.App;
import ledbanner.ledboard.ledscrollingtext.R;
import ledbanner.ledboard.ledscrollingtext.admodule.AppOpenManager;
import ledbanner.ledboard.ledscrollingtext.admodule.SplashAppOpenAds;

import java.util.Locale;

public class Led_SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources();
        setContentView(R.layout.activity_led_splash);
        getWindow().setFlags(1024, 1024);
        GotoNext();


    }

    public void GotoNext() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppOpenManager.Strcheckad = "StrClosed";
                SplashAppOpenAds.SplashAppOpenShow(Led_SplashActivity.this);
            }
        }, 3000);

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
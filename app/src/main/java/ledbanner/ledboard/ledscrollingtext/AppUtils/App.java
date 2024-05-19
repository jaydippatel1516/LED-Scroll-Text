package ledbanner.ledboard.ledscrollingtext.AppUtils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import ledbanner.ledboard.ledscrollingtext.admodule.AESUTIL;
import ledbanner.ledboard.ledscrollingtext.admodule.AddPrefs;
import ledbanner.ledboard.ledscrollingtext.admodule.AllAdCommonClass;
import ledbanner.ledboard.ledscrollingtext.admodule.AppOpenManager;

import org.json.JSONException;
import org.json.JSONObject;

public class App extends Application {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public static App app;

    public static App getInstance() {
        return app;
    }
    AddPrefs addPrefs;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        addPrefs = new AddPrefs(this);

    }


    public static boolean isConnected(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            return nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
        } catch (Exception e) {
            return false;
        }
    }


    public void setStoreBooleanValue(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getStoreBooleanValue(String key) {
        return preferences.getBoolean(key, false);
    }

    public void setStoreIntValue(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getStoreIntValue(String key) {
        return preferences.getInt(key, 0);
    }

    public void setStoreStringValue(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getStoreStringValue(String key) {
        return preferences.getString(key, "en");
    }

    public String getStoreStringValue1(String key) {
        return preferences.getString(key, "");
    }


}

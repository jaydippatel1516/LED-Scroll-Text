package ledbanner.ledboard.ledscrollingtext.admodule;

import static androidx.lifecycle.Lifecycle.Event.ON_START;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import ledbanner.ledboard.ledscrollingtext.AppUtils.App;


public class AppOpenManager implements LifecycleObserver {

    public static AppOpenAd appOpenAd = null;
    public static Activity currentActivity;
    public static boolean isShowingAd = false;
    public static boolean failappOpen = false;
    public static AppOpenAd.AppOpenAdLoadCallback loadCallback;
    public static String Strcheckad = "StrClosed";
    private final App myApplication;

    public AppOpenManager(App myApplication) {
        this.myApplication = myApplication;
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(ON_START)
    public void onStart() {
        showAdIfAvailable();
    }


    private void fetchAd() {
        AddPrefs appPreference = new AddPrefs(myApplication);
        if (isAdAvailable() || !App.isConnected(myApplication) || appPreference.getAdmAppOpenId().isEmpty()) {
            return;
        }
        failappOpen = false;
        Log.e("@@AppOpenManager2", " - " + appPreference.getAdmAppOpenId());
        loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull AppOpenAd ad) {
                Log.e("@@AppOpenManager", "Loaded");
                appOpenAd = ad;
//                NativeAdsEvent.NativeAdsLoads(myApplication);
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                Log.e("@@AppOpenManager", "Error: " + loadAdError.toString());
                appOpenAd = null;
                failappOpen = true;

            }
        };
        AppOpenAd.load(myApplication, appPreference.getAdmAppOpenId(), new AdRequest.Builder().build(), AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
    }

    public static void fetchAds(Activity activity) {
        AddPrefs appPreference = new AddPrefs(activity);
        if (isAdAvailable() || !App.isConnected(activity) || appPreference.getAdmAppOpenId().isEmpty()) {
            return;
        }
        loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull AppOpenAd ad) {
                Log.e("@@AppOpenManager", "Loaded");
                appOpenAd = ad;
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                Log.e("@@AppOpenManager", "Error: " + loadAdError.toString());
                failappOpen = true;
            }
        };
        AppOpenAd.load(activity, appPreference.getAdmAppOpenId(), new AdRequest.Builder().build(), AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
    }

    private void showAdIfAvailable() {
        if (Strcheckad.equalsIgnoreCase("StrClosed") && !isShowingAd && isAdAvailable()) {
            FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    Log.e("@@AppOpenManager", "AdDismissedFullScreenContent");
                    isShowingAd = false;
                    Strcheckad = "StrClosed";
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    Log.e("@@AppOpenManager", "AdFailedToShowFullScreenContent" + adError.toString());
                    Strcheckad = "StrClosed";
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    appOpenAd = null;
                    fetchAd();
                    Log.e("@@AppOpenManager", "AdShowedFullScreenContent");
                    Strcheckad = "StrOpen";
                    isShowingAd = true;
                }
            };
            appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
            Log.e("currentActivitytttt", "showAdIfAvailable: " + currentActivity);
            appOpenAd.show(currentActivity);
        } else {
            Log.e("@@SplashBeta", " - fetchAd going");
            fetchAd();
        }
    }

    public static void showAdIfAvailableAds(Activity activity, onInterCloseCallBack onInterCloseCallBack) {
        Log.e("gggggggllllllll", "showAdIfAvailableAds: " + activity.getClass().getSimpleName());
        if (Strcheckad.equalsIgnoreCase("StrClosed") && !isShowingAd && isAdAvailable()) {
            FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    Log.e("@@AppOpenManager", "AdDismissedFullScreenContent");
                    appOpenAd = null;
                    isShowingAd = false;
                    fetchAds(activity);
                    Strcheckad = "StrClosed";
                    onInterCloseCallBack.onAdsClose();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    Log.e("@@AppOpenManager", "AdFailedToShowFullScreenContent" + adError.toString());
                    Strcheckad = "StrClosed";
                    onInterCloseCallBack.onAdsClose();
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    Log.e("@@AppOpenManager", "AdShowedFullScreenContent");
                    Strcheckad = "StrOpen";
                    isShowingAd = true;
                }
            };
            appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
            Log.e("currentActivitytttt", "showAdIfAvailableAds:111111 " + currentActivity);
            appOpenAd.show(currentActivity);
        } else {
            onInterCloseCallBack.onAdsClose();
            fetchAds(activity);
        }
    }

    public static boolean isAdAvailable() {
        return appOpenAd != null;
    }

    public static boolean isFailappOpen() {
        return failappOpen;
    }

    public static void clearInstance() {
        currentActivity = null;
        isShowingAd = false;
        appOpenAd = null;
    }
}
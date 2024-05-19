package ledbanner.ledboard.ledscrollingtext.admodule;


import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

import ledbanner.ledboard.ledscrollingtext.Activity.Led_HomeActivity;
import ledbanner.ledboard.ledscrollingtext.Activity.Led_IntroductionActivity;
import ledbanner.ledboard.ledscrollingtext.AppUtils.App;


public class SplashAppOpenAds {
    private static int counter = 0;
    static boolean loaded = false;

    public static void SplashAppOpenShow(Activity SplashActivity) {
        AddPrefs appPreferences = new AddPrefs(SplashActivity);
        if (App.isConnected(SplashActivity) && !appPreferences.getAdmAppOpenId().isEmpty()) {

            new CountDownTimer(3000, 1000) {
                public void onTick(long millisUntilFinished) {
                    counter++;
                    Log.e("@@SplashBeta", " - " + AppOpenManager.isAdAvailable());

                    if (AppOpenManager.isFailappOpen()) {
                        cancel();
                        onFinish();
                    } else if (AppOpenManager.isAdAvailable()) {
                        loaded = true;
                        cancel();
                        onFinish();
                    }
                    Log.e("@@SplashBeta", "- " + counter);
                }

                public void onFinish() {
                    if (!AppOpenManager.isShowingAd && AppOpenManager.isAdAvailable()) {

                        AppOpenManager.showAdIfAvailableAds(SplashActivity, new onInterCloseCallBack() {
                            @Override
                            public void onAdsClose() {

                                goToNextStep(SplashActivity);
                            }
                        });
                    } else if (!loaded) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                goToNextStep(SplashActivity);
                            }
                        }, 2000);
                    } else {
                        goToNextStep(SplashActivity);
                    }
                }
            }.start();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    goToNextStep(SplashActivity);
                }
            }, 2000);
        }
    }

    static void goToNextStep(Activity SplashActivity) {
        Boolean isFirstRun = SplashActivity.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if (isFirstRun) {
            SplashActivity.startActivity(new Intent(SplashActivity, Led_IntroductionActivity.class));
            SplashActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            SplashActivity.finish();
        } else {
            SplashActivity.startActivity(new Intent(SplashActivity, Led_HomeActivity.class));
            SplashActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            SplashActivity.finish();
        }
    }
}

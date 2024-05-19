package ledbanner.ledboard.ledscrollingtext.admodule;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.NativeBannerAdView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import ledbanner.ledboard.ledscrollingtext.AppUtils.App;
import ledbanner.ledboard.ledscrollingtext.R;


public class AllAdCommonClass {

//    public static final String JSON_URL = "https://7seasol-application.s3.amazonaws.com/admin_prod/yrqonaareyrqobneqyrqfpebyyvatgrkg.json";
    public static final String JSON_URL = "7+dH3xwW79YajlGM74j5OKZ6Z6wBx7zUfM+f4xFUnE18qcgC4roZdtTR4a8tVb+sOIJv5+drBHnAXnpSfdWcVcL6ocV/WhjS7WEvZzXCAPXuhglb8BMGbLIL++p33Yxv";

    public static InterstitialAd mInterstitialAd;
    public static MyListener myListener;
    public static NativeAd loadednative;
    public static int count_inters = 0;

    public static int natvcont = 0;
    public static boolean isnativeload = true;
    public static int amfb_native_cnt = 0;
    public static int amfb_native_cnt_alter = 0;

    public static AdView adViewBanner;

    public interface MyListener {
        void callback();
    }

    public static void showAdmobBanner(Activity activity, LinearLayoutCompat layout_adBanner, ShimmerFrameLayout shimmerContainerBanner, boolean isBig) {
        if (App.isConnected(activity)) {
            loadBanner(activity, isBig, shimmerContainerBanner, layout_adBanner);
            layout_adBanner.removeAllViews();
            layout_adBanner.addView(adViewBanner);

        }
        else {
            shimmerContainerBanner.setVisibility(View.GONE);
            layout_adBanner.setVisibility(View.GONE);
        }
    }

    public static void loadBanner(Activity activity, boolean isBig, ShimmerFrameLayout shimmerContainerBanner, LinearLayoutCompat layout_adBanner) {

        adViewBanner = new AdView(activity);
        adViewBanner.setAdUnitId(new AddPrefs(activity).getAdmBannerId());

        if (isBig) {
            AdSize adSize = AdSize.MEDIUM_RECTANGLE;
            adViewBanner.setAdSize(adSize);
        } else {
            AdSize adSize = AdSize.BANNER;
            adViewBanner.setAdSize(adSize);
        }

        AdRequest adRequest = new AdRequest.Builder().build();
        adViewBanner.loadAd(adRequest);
        adViewBanner.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                shimmerContainerBanner.setVisibility(View.GONE);
                layout_adBanner.setVisibility(View.GONE);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                shimmerContainerBanner.setVisibility(View.GONE);
            }
        });
    }

    public static boolean fbInisfailed = true;
    public static com.facebook.ads.InterstitialAd fbinterstial;
    public static boolean adminterisshown = false;

    public static void LoadFb_Interstial(final Context context) {
        if (fbInisfailed) {
            if (fbinterstial == null || !fbinterstial.isAdLoaded()) {
                fbInisfailed = false;

                fbinterstial = new com.facebook.ads.InterstitialAd(context, new AddPrefs(context).getFBinterId());
                InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {
                        // Interstitial ad displayed callback
                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        fbInisfailed = true;
                        LoadFb_Interstial(context);
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        fbInisfailed = true;
                        Log.e("showdialogadmobadsimpAG", "Facebook Inter onError: " + adError.getErrorMessage());

                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        // Interstitial ad is loaded and ready to be displayed
                        // Show the ad
                        Log.e("showdialogadmobadsimpAG", "Facebook Inter FirstonAdLoaded: ");
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                        // Ad clicked callback
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                        // Ad impression logged callback
                    }
                };
                fbinterstial.loadAd(fbinterstial.buildLoadAdConfig().withAdListener(interstitialAdListener).build());
            }
        }
    }

    public static void showADMFBInterstial(Context context) {
        try {
            if (fbinterstial != null && fbinterstial.isAdLoaded()) {
                if (adminterisshown) {
                    fbinterstial.show();
                    adminterisshown = false;
                    fbInisfailed = true;
                }
            } else {
                LoadFb_Interstial(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load_Admob_Interstial(final Context context) {
        if (mInterstitialAd == null) {
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(context, new AddPrefs(context).getAdmInterId(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    mInterstitialAd = interstitialAd;
                    Log.e("Admobintertitial", "Admob Inter FirstonAdLoaded: ");
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    Log.e("Admobintertitial", "Admob Inter onAdFailedToLoad: " + loadAdError.getMessage());
                    mInterstitialAd = null;
                }
            });
        }
    }

    public static Dialog showdialog;

    public static void dialogProgress(Activity context) {
        showdialog = new Dialog(context, R.style.exitdialog_style);
        showdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        showdialog.setContentView(R.layout.showads_dialog);
        showdialog.show();
        showdialog.setCancelable(false);
    }

    public static boolean isFirst = true;
    public static boolean isInterOpen = false;
    public static int count_inters_alter = 0;

    public static void AdShowdialogFirstActivityQue(final Activity context, MyListener myListenerData) {

        if (isFirst && new AddPrefs(context).getCustomInterstial() == 1) {
            isFirst = false;
            myListenerData.callback();
            isInterOpen = false;
        } else {
            myListener = myListenerData;
            if (count_inters % new AddPrefs(context).getAdmShowclick() == 0) {

                if (new AddPrefs(context).getAlternativ() == 1) {
                    if (count_inters_alter % 2 == 0) {
                        AdShowdialogCustomActivityQue(context, myListener);
                    } else {
                        if (fbinterstial != null && fbinterstial.isAdLoaded()) {
                            adminterisshown = true;
                            myListener.callback();
                        } else if (mInterstitialAd != null) {
                            mInterstitialAd.show((Activity) context);
                            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    myListener.callback();
                                    isInterOpen = false;
                                    load_Admob_Interstial(context);
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                                    myListener.callback();
                                    isInterOpen = false;
                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    mInterstitialAd = null;
                                    isInterOpen = true;
                                }
                            });
                        } else {
                            dialogProgress(context);
                            AdRequest adRequest = new AdRequest.Builder().build();
                            InterstitialAd.load(context, new AddPrefs(context).getSecAdmInterId(), adRequest, new InterstitialAdLoadCallback() {
                                @Override
                                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                    Log.e("Admobintertitial", "Admob Inter FirstonAdLoaded: ");
                                    interstitialAd.show((Activity) context);
                                    interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                        @Override
                                        public void onAdDismissedFullScreenContent() {
                                            showdialog.dismiss();
                                            myListener.callback();
                                            isInterOpen = false;
                                        }

                                        @Override
                                        public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                                            showdialog.dismiss();
                                            myListener.callback();
                                            isInterOpen = false;
                                        }

                                        @Override
                                        public void onAdShowedFullScreenContent() {
                                            isInterOpen = true;
                                        }
                                    });
                                }

                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    showdialog.dismiss();
                                    myListener.callback();
                                    isInterOpen = false;
                                }
                            });
                            load_Admob_Interstial(context);
                        }
                    }
                    count_inters_alter++;
                } else {
                    AdShowdialogCustomActivityQue(context, myListener);
                }
            } else {
                isInterOpen = false;
                myListener.callback();
            }
            count_inters++;
        }
    }

    public static void AdShowdialogCustomActivityQue(final Activity context, MyListener myListenerData) {
        myListener = myListenerData;
        if (mInterstitialAd != null) {
            mInterstitialAd.show((Activity) context);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    myListener.callback();
                    isInterOpen = false;
                    load_Admob_Interstial(context);
                }

                @Override
                public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                    myListener.callback();
                    isInterOpen = false;
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    mInterstitialAd = null;
                    isInterOpen = true;
                }
            });
        } else {
            dialogProgress(context);
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(context, new AddPrefs(context).getSecAdmInterId(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    Log.e("Admobintertitial", "Admob Inter FirstonAdLoaded: ");
                    interstitialAd.show((Activity) context);
                    interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            showdialog.dismiss();
                            myListener.callback();
                            isInterOpen = false;
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {

                            if (fbinterstial != null && fbinterstial.isAdLoaded()) {
                                adminterisshown = true;
                                myListener.callback();
                            } else {
                                showdialog.dismiss();
                                myListener.callback();
                                isInterOpen = false;
                            }
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            isInterOpen = true;
                        }
                    });
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    if (fbinterstial != null && fbinterstial.isAdLoaded()) {
                        adminterisshown = true;
                        myListener.callback();
                    } else {
                        showdialog.dismiss();
                        myListener.callback();
                        isInterOpen = false;
                    }
                }
            });
            load_Admob_Interstial(context);
        }
    }

    public static void startNativeLoad(Context context) {
        if (isnativeload) {
            isnativeload = false;
            AdLoader adLoader = new AdLoader.Builder(context, new AddPrefs(context).getAdmNativeId()).forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(@NonNull NativeAd nativeAd) {
                    Log.e("Firstnativeload", "Firston NativeAdLoaded: ");
                    loadednative = nativeAd;
                }
            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    isnativeload = true;
                    loadednative = null;
                    Log.e("Firstnativeload", "onAdFailedToLoad: ");
                }
            }).build();

            natvcont++;
            Log.e("native_kfdfdf", "FirstnativeLoad: " + natvcont);
            adLoader.loadAd(new AdRequest.Builder().build());
        }
    }

    public static NativeBannerAd fbNativeBannerAd;
    public static View fbadnativeBannerview;

    public static boolean fbsmallnativeload = true;

    public static NativeAdLayout smallnativeAdLayout;
    public static LinearLayout adView;

    public static void load_Fb_Native_banner(final Context context) {

        if (fbsmallnativeload) {
            fbsmallnativeload = false;

            fbNativeBannerAd = new NativeBannerAd(context, new AddPrefs(context).getFBnativeBannerId());

            NativeAdListener nativeAdListener = new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    fbsmallnativeload = true;
                    Log.e("fbnativefailed", "Facebook native banner onError: " + adError.getErrorMessage());

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    Log.e("fbnativefailed", "Facebook native banner loaded: ");
                    fbadnativeBannerview = NativeBannerAdView.render(context, fbNativeBannerAd, NativeBannerAdView.Type.HEIGHT_100);

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    fbsmallnativeload = true;

                }
            };

            fbNativeBannerAd.loadAd(fbNativeBannerAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
        }

    }

    public static void NativeLoad(Context context, final TemplateView template, LinearLayoutCompat fbnativebanner, ShimmerFrameLayout shimmer_view_container) {
        Log.e("fbnativefailed", "SmallNativeBannerLoad:1111111111111 " + amfb_native_cnt);
        Log.e("fbnativefailed", "SmallNativeBannerLoad: " + new AddPrefs(context).getNativeAdmShowclick());
        if (amfb_native_cnt % new AddPrefs(context).getNativeAdmShowclick() == 0) {

            if (new AddPrefs(context).getAlternativ() == 1) {
                if (amfb_native_cnt_alter % 2 == 0) {
                    showNativeAds(context, template, shimmer_view_container, fbnativebanner);
                } else {
                    if (fbNativeBannerAd != null && fbNativeBannerAd.isAdLoaded()) {
                        fbnativebanner.setVisibility(View.VISIBLE);
                        template.setVisibility(View.GONE);
                        shimmer_view_container.setVisibility(View.GONE);
                        fbnativebanner.removeAllViews();
                        fbnativebanner.addView(fbadnativeBannerview);
                        fbsmallnativeload = true;
                    } else if (loadednative != null) {
                        template.setVisibility(View.VISIBLE);
                        fbnativebanner.setVisibility(View.GONE);
                        shimmer_view_container.setVisibility(View.GONE);
                        template.setNativeAd(loadednative, new AddPrefs(context).getButtonColor());
                        isnativeload = true;
                    } else {
                        isnativeload = true;
                        AdLoader adLoader = new AdLoader.Builder(context, new AddPrefs(context).getsecAdmNativeId()).forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAd) {
                                Log.e("Firstnativeload", "Firston NativeAdLoaded: ");
                                template.setVisibility(View.VISIBLE);
                                fbnativebanner.setVisibility(View.GONE);
                                shimmer_view_container.setVisibility(View.GONE);
                                template.setNativeAd(nativeAd, new AddPrefs(context).getButtonColor());
                            }
                        }).withAdListener(new AdListener() {
                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                super.onAdFailedToLoad(loadAdError);
                                fbnativebanner.setVisibility(View.GONE);
                                template.setVisibility(View.GONE);
                                isnativeload = false;
                                shimmer_view_container.setVisibility(View.GONE);

                                Log.e("Firstnativeload", "onAdFailedToLoad: ");
                            }
                        }).build();
                        adLoader.loadAd(new AdRequest.Builder().build());
                    }
                }
                amfb_native_cnt_alter++;
            } else {
                showNativeAds(context, template, shimmer_view_container, fbnativebanner);
            }
        } else {
            template.setVisibility(View.GONE);
            fbnativebanner.setVisibility(View.GONE);
            isnativeload = false;
            shimmer_view_container.setVisibility(View.GONE);
        }
        amfb_native_cnt++;

        startNativeLoad(context);
        load_Fb_Native_banner(context);
    }

    public static void ExitNativeLoad(Context context, final TemplateView template, LinearLayoutCompat fbnativebanner, ShimmerFrameLayout shimmer_view_container) {

        if (new AddPrefs(context).getExitNative().equals("0")) {
            showNativeAds(context, template, shimmer_view_container, fbnativebanner);
            startNativeLoad(context);
            load_Fb_Native_banner(context);
        } else {
            fbnativebanner.setVisibility(View.GONE);
            template.setVisibility(View.GONE);
            shimmer_view_container.setVisibility(View.GONE);
        }

    }

    private static void showNativeAds(Context context, TemplateView template, ShimmerFrameLayout shimmer_view_container, LinearLayoutCompat fbnativebanner) {

        if (loadednative != null) {
            template.setVisibility(View.VISIBLE);
            shimmer_view_container.setVisibility(View.GONE);
            template.setNativeAd(loadednative, new AddPrefs(context).getButtonColor());
            isnativeload = true;
        } else {
            isnativeload = true;
            AdLoader adLoader = new AdLoader.Builder(context, new AddPrefs(context).getsecAdmNativeId()).forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(@NonNull NativeAd nativeAd) {
                    Log.e("Firstnativeload", "Firston NativeAdLoaded: ");
                    template.setVisibility(View.VISIBLE);
                    shimmer_view_container.setVisibility(View.GONE);
                    template.setNativeAd(nativeAd, new AddPrefs(context).getButtonColor());
                }
            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    template.setVisibility(View.GONE);
                    isnativeload = false;
                    shimmer_view_container.setVisibility(View.GONE);
                    fbnativebanner.setVisibility(View.GONE);

                    if (fbNativeBannerAd != null && fbNativeBannerAd.isAdLoaded()) {
                        fbnativebanner.setVisibility(View.VISIBLE);
                        template.setVisibility(View.GONE);
                        shimmer_view_container.setVisibility(View.GONE);
                        fbnativebanner.removeAllViews();
                        fbnativebanner.addView(fbadnativeBannerview);
                        fbsmallnativeload = true;
                    }

                    Log.e("Firstnativeload", "onAdFailedToLoad: ");
                }
            }).build();
            adLoader.loadAd(new AdRequest.Builder().build());
        }

    }

}

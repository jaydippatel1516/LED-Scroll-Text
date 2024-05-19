package ledbanner.ledboard.ledscrollingtext.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.material.textview.MaterialTextView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import ledbanner.ledboard.ledscrollingtext.Adapter.ImagePagerAdapter;
import ledbanner.ledboard.ledscrollingtext.AppUtils.App;
import ledbanner.ledboard.ledscrollingtext.R;
import ledbanner.ledboard.ledscrollingtext.admodule.AllAdCommonClass;

import java.util.List;
import java.util.Locale;

public class Led_IntroductionActivity extends AppCompatActivity {
    public int imageArra[] = {R.drawable.intro1, R.drawable.intro2, R.drawable.intro3};
    String[] strings = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};
    String[] per13 = {Manifest.permission.CAMERA, Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VIDEO, Manifest.permission.READ_MEDIA_AUDIO, Manifest.permission.RECORD_AUDIO};

    public String[] stringArray;
    public String[] stringArray1;
    MaterialTextView mtrnext;
    ViewPager myPager;
    ImagePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources();
        setContentView(R.layout.activity_led_introduction);
        getWindow().setFlags(1024, 1024);

        ShimmerFrameLayout shimmer_view_container = findViewById(R.id.shimmer_view_container);
        AllAdCommonClass.NativeLoad(this, (TemplateView) findViewById(R.id.my_template), findViewById(R.id.fram_fbnative), shimmer_view_container);

        mtrnext = findViewById(R.id.btnnext);
        myPager = findViewById(R.id.viewPager);

        stringArray = new String[]{getResources().getString(R.string.title1), getResources().getString(R.string.title1), getResources().getString(R.string.title1)};
        stringArray1 = new String[]{getResources().getString(R.string.sub1), getResources().getString(R.string.sub2), getResources().getString(R.string.sub3)};
        adapter = new ImagePagerAdapter(this, imageArra, stringArray, stringArray1);
        myPager.setAdapter(adapter);
        myPager.setCurrentItem(0);
        myPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        mtrnext.setText(getResources().getString(R.string.next));
                        break;
                    case 1:
                        mtrnext.setText(getResources().getString(R.string.next));
                        break;
                    case 2:
                        mtrnext.setText(getResources().getString(R.string.letsstart));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        findViewById(R.id.btnnext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int current = myPager.getCurrentItem() + 1;
                if (current < myPager.getAdapter().getCount()) {
                    myPager.setCurrentItem(current);
                } else {
                    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).commit();
                    Dexter.withActivity(Led_IntroductionActivity.this)
                            .withPermissions(Build.VERSION.SDK_INT >= 33 ? per13 : strings)
                            .withListener(new MultiplePermissionsListener() {
                                @Override
                                public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                                    if (multiplePermissionsReport.areAllPermissionsGranted()) {
                                        startActivity(new Intent(Led_IntroductionActivity.this, Led_LanguageActivity.class));
                                        finish();
                                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


                                    }
                                    if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                                        startActivity(new Intent(Led_IntroductionActivity.this, Led_LanguageActivity.class));
                                        finish();
                                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


                                    }
                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                                    permissionToken.continuePermissionRequest();
                                }
                            }).withErrorListener(error -> {
                            })
                            .onSameThread().check();


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
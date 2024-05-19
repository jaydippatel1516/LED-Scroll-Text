package ledbanner.ledboard.ledscrollingtext.Theme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.ads.nativetemplates.TemplateView;

import ledbanner.ledboard.ledscrollingtext.Activity.Led_EditActivity;
import ledbanner.ledboard.ledscrollingtext.Activity.Led_LanguageActivity;
import ledbanner.ledboard.ledscrollingtext.Activity.Led_settingActivity;
import ledbanner.ledboard.ledscrollingtext.R;
import ledbanner.ledboard.ledscrollingtext.admodule.AllAdCommonClass;

public class Led_ThemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_theme);
        findViewById(R.id.ivBack).setOnClickListener(v -> onBackPressed());
        AllAdCommonClass.showADMFBInterstial(this);
        ShimmerFrameLayout shimmer_view_container = findViewById(R.id.shimmer_view_container);
        AllAdCommonClass.NativeLoad(this, (TemplateView) findViewById(R.id.my_template), findViewById(R.id.fram_fbnative), shimmer_view_container);
        findViewById(R.id.birthday1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_ThemeActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        startActivity(new Intent(Led_ThemeActivity.this, Led_EditActivity.class).putExtra("img", R.drawable.img_birthday_02));

                    }
                });
            }
        });
        findViewById(R.id.birthdaysell).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_ThemeActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        startActivity(new Intent(Led_ThemeActivity.this, Led_ThemeItemActivity.class));

                    }
                });
            }
        });
        findViewById(R.id.christmas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_ThemeActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        startActivity(new Intent(Led_ThemeActivity.this, Led_EditActivity.class).putExtra("img", R.drawable.img_christmas_01));

                    }
                });

            }
        });
        findViewById(R.id.christmaseeall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_ThemeActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        startActivity(new Intent(Led_ThemeActivity.this, ChristmasActivity.class));

                    }
                });

            }
        });
        findViewById(R.id.hny).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_ThemeActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        startActivity(new Intent(Led_ThemeActivity.this, Led_EditActivity.class).putExtra("img", R.drawable.img_new_year_01));

                    }
                });


            }
        });
        findViewById(R.id.hnyseeall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_ThemeActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        startActivity(new Intent(Led_ThemeActivity.this, NewYearActivity.class));

                    }
                });

            }
        });
        findViewById(R.id.love).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_ThemeActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        startActivity(new Intent(Led_ThemeActivity.this, Led_EditActivity.class).putExtra("img", R.drawable.img_love_02));

                    }
                });


            }
        });
        findViewById(R.id.loveseeall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_ThemeActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        startActivity(new Intent(Led_ThemeActivity.this, LoveActivity.class));

                    }
                });

            }
        });
        findViewById(R.id.happynver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_ThemeActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        startActivity(new Intent(Led_ThemeActivity.this, Led_EditActivity.class).putExtra("img", R.drawable.img_happy_anniversary_01));

                    }
                });

            }
        });
        findViewById(R.id.happynverseeall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_ThemeActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        startActivity(new Intent(Led_ThemeActivity.this, HappyAnniversaryActivity.class));

                    }
                });

            }
        });

    }
}
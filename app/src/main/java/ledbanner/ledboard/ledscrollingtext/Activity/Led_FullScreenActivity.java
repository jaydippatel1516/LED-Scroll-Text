package ledbanner.ledboard.ledscrollingtext.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ledbanner.ledboard.ledscrollingtext.AppUtils.App;
import ledbanner.ledboard.ledscrollingtext.R;

import java.util.Locale;

public class Led_FullScreenActivity extends AppCompatActivity {
    RelativeLayout scrollView_showtext;
    TextView textView;
    long textspeed;
    private Animation anim;
    ValueAnimator animator;
    boolean isBlink;
    String[] data = {"abeezee_italic.ttf", "aguafina_script.ttf", "alfa_slab_one.ttf", "alike_angular.ttf", "almendra_display.ttf", "andada.ttf", "annie_use_your_telescope.ttf", "bangers.ttf", "bungee_shade.ttf", "cinzel_decorative.ttf"};
    float mTextSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources();
        setContentView(R.layout.activity_led_full_screen);
        getWindow().setFlags(1024,104);
        findViewById(R.id.back).setOnClickListener(v -> onBackPressed());
        textView = findViewById(R.id.text_vedio);
        scrollView_showtext = findViewById(R.id.scrollView_vedio);
        savedInstanceState = getIntent().getExtras();
        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean("isBlink")) {
                blink();
            }
            mTextSize = savedInstanceState.getFloat("textsize");
        }

        int arrayB = getIntent().getIntExtra("bg", 0);

        if (arrayB == 0) {
            scrollView_showtext.setBackgroundColor(getIntent().getIntExtra("textbackgroundcolor", 0));
        } else {
            scrollView_showtext.setBackgroundResource(arrayB);
        }


        textView.setTextSize(mTextSize);
        this.textspeed = getIntent().getLongExtra("duration", 0);
        int intExtra3 = getIntent().getIntExtra("textstyle", 0);
        AssetManager assets = getAssets();
        int intExtra4 = getIntent().getIntExtra("check", 0);
        textView.setText(getIntent().getStringExtra("marqueetext"));
        textView.setTextColor(getIntent().getIntExtra("textcolor", 0));

        textView.setTypeface(Typeface.createFromAsset(assets, "font/" + data[intExtra3]));
        if (intExtra4 == 0) {
            right_to_left();
        } else if (intExtra4 == 1) {
            left_to_right();
        }
    }
    public void updateResources() {
        Locale locale = new Locale(App.getInstance().getStoreStringValue("pref_selected_language"));
        Locale.setDefault(locale);
        Configuration configuration = getResources().getConfiguration();
        createConfigurationContext(configuration);
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
    }
    public void left_to_right() {
        animator = ValueAnimator.ofFloat(new float[]{0.0f, 2.0f});
        animator.setRepeatCount(-1);
        animator.setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                int round = Math.round(textView.getPaint().measureText(textView.getText().toString()));
                ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                layoutParams.width = round;
                textView.setLayoutParams(layoutParams);
                getWindowManager().getDefaultDisplay().getRealMetrics(new DisplayMetrics());
                float f = (float) round;
                float f2 = floatValue * f;
                textView.setTranslationX(f2);
                textView.setTranslationX(f2 - f);
            }
        });
        animator.start();
    }

    public void right_to_left() {
        animator = ValueAnimator.ofFloat(new float[]{2.0f, 0.0f});
        animator.setRepeatCount(-1);
        animator.setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                int round = Math.round(textView.getPaint().measureText(textView.getText().toString()));
                ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                layoutParams.width = round;
                textView.setLayoutParams(layoutParams);
                getWindowManager().getDefaultDisplay().getRealMetrics(new DisplayMetrics());
                float f = (float) round;
                float f2 = floatValue * f;
                textView.setTranslationX(f2);
                textView.setTranslationX(f2 - f);
            }
        });
        animator.start();
    }

    private void blink() {
        if (anim == null) {
            isBlink = true;
            anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(50);
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            textView.startAnimation(anim);
        } else {
            isBlink = false;
            textView.clearAnimation();
            anim = null;
        }
    }
}
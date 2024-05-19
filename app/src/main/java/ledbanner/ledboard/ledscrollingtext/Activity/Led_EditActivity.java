package ledbanner.ledboard.ledscrollingtext.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import ledbanner.ledboard.ledscrollingtext.AppUtils.App;
import ledbanner.ledboard.ledscrollingtext.R;
import ledbanner.ledboard.ledscrollingtext.admodule.AllAdCommonClass;

import java.util.Locale;

public class Led_EditActivity extends AppCompatActivity {
    Switch switch1;
    Animation anim;
    boolean isBlink;
    int text_style;
    float text_size;
    AppCompatEditText editText_textt;
    TextView text;
    RelativeLayout relativeLayout;
    LinearLayout lytxtspeed, lytrans, lytextsize, lytextspeed1, lytrans1, lyfont, lycolor, lybgcolor, lytxtfont, lytxtbgcolor, lytxtcolor, lytextsize1;
    MaterialTextView txtspeed, txttrans, txtsize, txtspeed1, txtspeed2, txtspeed3, txtspeed4, txtspeed5, txtnone,
            txtleft, txtright, txtfont, txtColor, txtbgcolor, abeeze, aguafina, annie, alfa, alike, almendra,
            andada, bangers, bungeee, cinzel, size20, size40, size60, size80, size100, iv_done;
    View viewtxtspeed, viewtrans, viewtxtsize, viewtxtfont, viewcolor, viewbgcolor;

    MaterialCardView cc1, cc2, cc3, cc4, cc5, cc6, cc7, cc8, cc9, cc10, cc11, cc12, cc13, cc14, cc15, cc16, cc17;
    MaterialCardView c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17;
    AppCompatImageView fullscreen;
    int check = 0;
    ValueAnimator animator;
    long duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources();
        setContentView(R.layout.activity_led_edit);
        AllAdCommonClass.showADMFBInterstial(this);
        findViewById(R.id.ivBack).setOnClickListener(v -> onBackPressed());
        ShimmerFrameLayout shimmer_view_container = findViewById(R.id.shimmer_view_container);
        AllAdCommonClass.NativeLoad(this, (TemplateView) findViewById(R.id.my_template), findViewById(R.id.fram_fbnative), shimmer_view_container);
        int arrayB = getIntent().getIntExtra("img", 0);
        animator = new ValueAnimator();
        switch1 = findViewById(R.id.switch1);
        text = findViewById(R.id.text);
        text_size = 30;
        text.setTextSize(text_size);
        right_to_left();
        fullscreen = findViewById(R.id.fullscreen);
        relativeLayout = findViewById(R.id.scrollView2);

        if (arrayB == 0) {
            ((ColorDrawable) relativeLayout.getBackground()).getColor();
        } else {
            relativeLayout.setBackgroundResource(arrayB);
        }

        lytxtbgcolor = findViewById(R.id.lytxtbgcolor);
        lytextsize1 = findViewById(R.id.lytextsize1);
        iv_done = findViewById(R.id.iv_done);
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        c5 = findViewById(R.id.c5);
        c6 = findViewById(R.id.c6);
        c7 = findViewById(R.id.c7);
        c8 = findViewById(R.id.c8);
        c9 = findViewById(R.id.c9);
        c10 = findViewById(R.id.c10);
        c11 = findViewById(R.id.c11);
        c12 = findViewById(R.id.c12);
        c13 = findViewById(R.id.c13);
        c14 = findViewById(R.id.c14);
        c15 = findViewById(R.id.c15);
        c16 = findViewById(R.id.c16);
        c17 = findViewById(R.id.c17);
        cc1 = findViewById(R.id.cc1);
        cc2 = findViewById(R.id.cc2);
        cc3 = findViewById(R.id.cc3);
        cc4 = findViewById(R.id.cc4);
        cc5 = findViewById(R.id.cc5);
        cc6 = findViewById(R.id.cc6);
        cc7 = findViewById(R.id.cc7);
        cc8 = findViewById(R.id.cc8);
        cc9 = findViewById(R.id.cc9);
        cc10 = findViewById(R.id.cc10);
        cc11 = findViewById(R.id.cc11);
        cc12 = findViewById(R.id.cc12);
        cc13 = findViewById(R.id.cc13);
        cc14 = findViewById(R.id.cc14);
        cc15 = findViewById(R.id.cc15);
        cc16 = findViewById(R.id.cc16);
        cc17 = findViewById(R.id.cc17);
        size20 = findViewById(R.id.size20);
        size40 = findViewById(R.id.size40);
        size60 = findViewById(R.id.size60);
        size80 = findViewById(R.id.size80);
        size100 = findViewById(R.id.size100);
        editText_textt = findViewById(R.id.editText_textt);
        abeeze = findViewById(R.id.abeeze);
        alfa = findViewById(R.id.alfa);
        annie = findViewById(R.id.annie);
        alike = findViewById(R.id.alike);
        andada = findViewById(R.id.andada);
        bangers = findViewById(R.id.bangers);
        bungeee = findViewById(R.id.bungeee);
        cinzel = findViewById(R.id.cinzel);
        almendra = findViewById(R.id.almendra);
        aguafina = findViewById(R.id.aguafina);
        lytxtspeed = findViewById(R.id.lytxtspeed);
        lytxtfont = findViewById(R.id.lytxtfont);
        lytrans = findViewById(R.id.lytrans);
        lytrans1 = findViewById(R.id.lytrans1);
        lytextsize = findViewById(R.id.lytextsize);
        lytextspeed1 = findViewById(R.id.lytextspeed1);
        lyfont = findViewById(R.id.lyfont);
        lybgcolor = findViewById(R.id.lybgcolor);
        lycolor = findViewById(R.id.lycolor);
        txtspeed = findViewById(R.id.txtspeed);
        txtbgcolor = findViewById(R.id.txtbgcolor);
        txtfont = findViewById(R.id.txtfont);
        txttrans = findViewById(R.id.txttrans);
        txtColor = findViewById(R.id.txtColor);
        txtsize = findViewById(R.id.txtsize);
        txtspeed1 = findViewById(R.id.txtspeed1);
        txtspeed2 = findViewById(R.id.txtspeed2);
        txtspeed3 = findViewById(R.id.txtspeed3);
        txtspeed4 = findViewById(R.id.txtspeed4);
        txtspeed5 = findViewById(R.id.txtspeed5);
        txtnone = findViewById(R.id.txtnone);
        txtleft = findViewById(R.id.txtleft);
        txtright = findViewById(R.id.txtright);
        viewtxtspeed = findViewById(R.id.viewtxtspeed);
        viewtrans = findViewById(R.id.viewtrans);
        viewtxtsize = findViewById(R.id.viewtxtsize);
        viewtxtfont = findViewById(R.id.viewtxtfont);
        viewcolor = findViewById(R.id.viewcolor);
        viewbgcolor = findViewById(R.id.viewbgcolor);
        lytxtcolor = findViewById(R.id.lytxtcolor);

        final Typeface font = ResourcesCompat.getFont(this, R.font.abeezee_italic);
        final Typeface font2 = ResourcesCompat.getFont(this, R.font.aguafina_script);
        final Typeface font3 = ResourcesCompat.getFont(this, R.font.alfa_slab_one);
        final Typeface font4 = ResourcesCompat.getFont(this, R.font.alike_angular);
        final Typeface font5 = ResourcesCompat.getFont(this, R.font.almendra_display);
        final Typeface font6 = ResourcesCompat.getFont(this, R.font.andada);
        final Typeface font7 = ResourcesCompat.getFont(this, R.font.annie_use_your_telescope);
        final Typeface font8 = ResourcesCompat.getFont(this, R.font.bangers);
        final Typeface font9 = ResourcesCompat.getFont(this, R.font.bungee_shade);
        final Typeface font10 = ResourcesCompat.getFont(this, R.font.cinzel_decorative);


        abeeze.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                text_style = 0;
                text.setTypeface(font);
                abeeze.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                aguafina.setTextColor(-1);
                alfa.setTextColor(-1);
                alike.setTextColor(-1);
                almendra.setTextColor(-1);
                andada.setTextColor(-1);
                annie.setTextColor(-1);
                bangers.setTextColor(-1);
                bungeee.setTextColor(-1);
                cinzel.setTextColor(-1);
                aguafina.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alfa.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alike.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                almendra.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                andada.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                annie.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bangers.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bungeee.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                cinzel.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
            }
        });
        aguafina.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                text_style = 1;
                aguafina.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                text.setTypeface(font2);
                abeeze.setTextColor(-1);
                alfa.setTextColor(-1);
                alike.setTextColor(-1);
                almendra.setTextColor(-1);
                andada.setTextColor(-1);
                annie.setTextColor(-1);
                bangers.setTextColor(-1);
                bungeee.setTextColor(-1);
                cinzel.setTextColor(-1);
                abeeze.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alfa.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alike.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                almendra.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                andada.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                annie.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bangers.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bungeee.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                cinzel.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
            }
        });
        alfa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                text_style = 2;
                alfa.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                text.setTypeface(font3);
                aguafina.setTextColor(-1);
                alike.setTextColor(-1);
                almendra.setTextColor(-1);
                andada.setTextColor(-1);
                annie.setTextColor(-1);
                bangers.setTextColor(-1);
                bungeee.setTextColor(-1);
                cinzel.setTextColor(-1);
                aguafina.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alike.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                almendra.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                andada.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                annie.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bangers.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bungeee.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                cinzel.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
            }
        });
        alike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                text_style = 3;
                alike.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                text.setTypeface(font4);
                aguafina.setTextColor(-1);
                alfa.setTextColor(-1);
                almendra.setTextColor(-1);
                andada.setTextColor(-1);
                annie.setTextColor(-1);
                bangers.setTextColor(-1);
                bungeee.setTextColor(-1);
                cinzel.setTextColor(-1);
                aguafina.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alfa.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                almendra.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                andada.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                annie.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bangers.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bungeee.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                cinzel.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
            }
        });
        almendra.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                text_style = 4;
                almendra.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                text.setTypeface(font5);
                aguafina.setTextColor(-1);
                alfa.setTextColor(-1);
                alike.setTextColor(-1);
                andada.setTextColor(-1);
                annie.setTextColor(-1);
                bangers.setTextColor(-1);
                bungeee.setTextColor(-1);
                cinzel.setTextColor(-1);
                aguafina.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alfa.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alike.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                andada.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                annie.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bangers.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bungeee.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                cinzel.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                abeeze.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
            }
        });
        andada.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                text_style = 5;
                andada.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                text.setTypeface(font6);
                aguafina.setTextColor(-1);
                alfa.setTextColor(-1);
                alike.setTextColor(-1);
                almendra.setTextColor(-1);
                annie.setTextColor(-1);
                bangers.setTextColor(-1);
                bungeee.setTextColor(-1);
                cinzel.setTextColor(-1);
                aguafina.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alfa.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alike.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                almendra.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                annie.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bangers.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bungeee.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                cinzel.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
            }
        });
        annie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                text_style = 6;
                annie.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                text.setTypeface(font7);
                aguafina.setTextColor(-1);
                alfa.setTextColor(-1);
                alike.setTextColor(-1);
                almendra.setTextColor(-1);
                andada.setTextColor(-1);
                bangers.setTextColor(-1);
                bungeee.setTextColor(-1);
                cinzel.setTextColor(-1);
                aguafina.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alfa.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alike.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                almendra.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                andada.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bangers.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bungeee.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                cinzel.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
            }
        });
        bangers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                text_style = 7;
                bangers.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                text.setTypeface(font8);
                aguafina.setTextColor(-1);
                alfa.setTextColor(-1);
                alike.setTextColor(-1);
                almendra.setTextColor(-1);
                andada.setTextColor(-1);
                annie.setTextColor(-1);
                bungeee.setTextColor(-1);
                cinzel.setTextColor(-1);
                aguafina.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alfa.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alike.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                almendra.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                andada.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                annie.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bungeee.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                cinzel.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
            }
        });
        bungeee.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                text_style = 8;
                bungeee.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                text.setTypeface(font9);
                aguafina.setTextColor(-1);
                alfa.setTextColor(-1);
                alike.setTextColor(-1);
                almendra.setTextColor(-1);
                andada.setTextColor(-1);
                annie.setTextColor(-1);
                bangers.setTextColor(-1);
                cinzel.setTextColor(-1);
                aguafina.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alfa.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alike.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                almendra.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                andada.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                annie.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bangers.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                cinzel.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
            }
        });
        cinzel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                text_style = 9;
                cinzel.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                text.setTypeface(font10);
                aguafina.setTextColor(-1);
                alfa.setTextColor(-1);
                alike.setTextColor(-1);
                almendra.setTextColor(-1);
                andada.setTextColor(-1);
                annie.setTextColor(-1);
                bangers.setTextColor(-1);
                bungeee.setTextColor(-1);
                aguafina.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alfa.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                alike.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                almendra.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                andada.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                annie.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bangers.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                bungeee.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
            }
        });

        fullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animator != null) {
                    animator.cancel();
                    animator.addUpdateListener(null);
                    animator = null;
                }
                Bundle bundle = new Bundle();
                Intent intent = new Intent(Led_EditActivity.this, Led_FullScreenActivity.class);
                bundle.putBoolean("isBlink", isBlink);
                bundle.putFloat("textsize", text_size);
                String charSequence = text.getText().toString();
                int currentTextColor = text.getCurrentTextColor();
                intent.putExtra("duration", duration);
                intent.putExtra("marqueetext", charSequence);
                intent.putExtra("textcolor", currentTextColor);
                intent.putExtra("textstyle", text_style);

                if (arrayB == 0) {
                    int color = ((ColorDrawable) relativeLayout.getBackground()).getColor();
                    intent.putExtra("textbackgroundcolor", color);
                } else {
                    try {
                        int color = ((ColorDrawable) relativeLayout.getBackground()).getColor();
                        intent.putExtra("textbackgroundcolor", color);
                    } catch (Exception e) {
                        intent.putExtra("bg", arrayB);
                    }
                }
                intent.putExtra("check", check);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        editText_textt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                text.setText(editText_textt.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lytxtspeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtspeed.setTextColor(getResources().getColor(R.color.white));
                txttrans.setTextColor(getResources().getColor(R.color.txtdsel));
                txtsize.setTextColor(getResources().getColor(R.color.txtdsel));
                viewtrans.setVisibility(View.GONE);
                viewtxtsize.setVisibility(View.GONE);
                viewtxtspeed.setVisibility(View.VISIBLE);

                lytextspeed1.setVisibility(View.VISIBLE);
                lytextsize1.setVisibility(View.GONE);
                lytrans1.setVisibility(View.GONE);

            }
        });
        lytrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txttrans.setTextColor(getResources().getColor(R.color.white));
                txtsize.setTextColor(getResources().getColor(R.color.txtdsel));
                txtspeed.setTextColor(getResources().getColor(R.color.txtdsel));
                viewtrans.setVisibility(View.VISIBLE);
                viewtxtspeed.setVisibility(View.GONE);
                viewtxtsize.setVisibility(View.GONE);

                lytextsize1.setVisibility(View.GONE);
                lytextspeed1.setVisibility(View.GONE);
                lytrans1.setVisibility(View.VISIBLE);

            }
        });
        lytextsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtsize.setTextColor(getResources().getColor(R.color.white));
                txtspeed.setTextColor(getResources().getColor(R.color.txtdsel));
                txttrans.setTextColor(getResources().getColor(R.color.txtdsel));
                viewtxtsize.setVisibility(View.VISIBLE);
                viewtrans.setVisibility(View.GONE);
                viewtxtspeed.setVisibility(View.GONE);

                lytextsize1.setVisibility(View.VISIBLE);
                lytextspeed1.setVisibility(View.GONE);
                lytrans1.setVisibility(View.GONE);


            }
        });

        lyfont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtfont.setTextColor(getResources().getColor(R.color.white));
                txtColor.setTextColor(getResources().getColor(R.color.txtdsel));
                txtbgcolor.setTextColor(getResources().getColor(R.color.txtdsel));

                viewtxtfont.setVisibility(View.VISIBLE);
                viewbgcolor.setVisibility(View.GONE);
                viewcolor.setVisibility(View.GONE);

                lytxtfont.setVisibility(View.VISIBLE);
                lytxtcolor.setVisibility(View.GONE);
                lytxtbgcolor.setVisibility(View.GONE);

            }
        });

        lycolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtColor.setTextColor(getResources().getColor(R.color.white));
                txtfont.setTextColor(getResources().getColor(R.color.txtdsel));
                txtbgcolor.setTextColor(getResources().getColor(R.color.txtdsel));

                viewcolor.setVisibility(View.VISIBLE);
                viewbgcolor.setVisibility(View.GONE);
                viewtxtfont.setVisibility(View.GONE);

                lytxtcolor.setVisibility(View.VISIBLE);
                lytxtfont.setVisibility(View.GONE);
                lytxtbgcolor.setVisibility(View.GONE);


            }
        });
        lybgcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtbgcolor.setTextColor(getResources().getColor(R.color.white));
                txtfont.setTextColor(getResources().getColor(R.color.txtdsel));
                txtColor.setTextColor(getResources().getColor(R.color.txtdsel));

                viewbgcolor.setVisibility(View.VISIBLE);
                viewcolor.setVisibility(View.GONE);
                viewtxtfont.setVisibility(View.GONE);

                lytxtbgcolor.setVisibility(View.VISIBLE);
                lytxtcolor.setVisibility(View.GONE);
                lytxtfont.setVisibility(View.GONE);


            }
        });
        txtspeed1.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
        txtnone.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));

        txtspeed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtspeed1.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                txtspeed2.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtspeed3.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtspeed4.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtspeed5.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                animator.setDuration(25000);
                duration = animator.getDuration();

            }
        });
        txtspeed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtspeed1.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtspeed2.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                txtspeed3.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtspeed4.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtspeed5.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));

                animator.setDuration(20000);
                duration = animator.getDuration();

            }
        });
        txtspeed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtspeed3.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                txtspeed1.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtspeed2.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtspeed4.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtspeed5.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));

                animator.setDuration(15000);
                duration = animator.getDuration();


            }
        });
        txtspeed4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtspeed4.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                txtspeed1.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtspeed2.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtspeed3.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtspeed5.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));

                animator.setDuration(10000);
                duration = animator.getDuration();


            }
        });
        txtspeed5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtspeed5.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                txtspeed1.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtspeed2.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtspeed3.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtspeed4.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));

                animator.setDuration(5000);
                duration = animator.getDuration();


            }
        });
        txtnone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                None();
                txtnone.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                txtleft.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtright.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
            }
        });
        txtleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtleft.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                txtnone.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtright.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                check = 0;
                right_to_left();

            }
        });
        txtright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtright.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                txtleft.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                txtnone.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                check = 1;
                left_to_right();
            }
        });
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                blink();
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#000000"));
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#f50057"));
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#01579b"));
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#33691e"));
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#eeff41"));
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#808080"));
            }
        });
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#990000"));
            }
        });
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#4eafc9"));
            }
        });
        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#2c7f84"));
            }
        });
        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#8c8743"));
            }
        });
        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#5ac18e"));
            }
        });
        c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#e5f4bb"));
            }
        });
        c13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#9c59dd"));
            }
        });
        c14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#D068E1"));
            }
        });
        c15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#EB959A"));
            }
        });
        c16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#FFB96B"));
            }
        });
        c17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        });
        cc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#000000"));
            }
        });
        cc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#f50057"));
            }
        });
        cc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#01579b"));
            }
        });
        cc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#33691e"));
            }
        });
        cc5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#eeff41"));
            }
        });
        cc6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#808080"));
            }
        });
        cc7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#990000"));
            }
        });
        cc8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#4eafc9"));
            }
        });
        cc9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#2c7f84"));
            }
        });
        cc10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#8c8743"));
            }
        });
        cc11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#5ac18e"));
            }
        });
        cc12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#e5f4bb"));
            }
        });
        cc13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#9c59dd"));
            }
        });
        cc14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#D068E1"));
            }
        });
        cc15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#EB959A"));
            }
        });
        cc16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#FFB96B"));
            }
        });
        cc17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });

        size20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_size = 20;
                size20.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                size40.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                size60.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                size80.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                size100.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                text.setTextSize(text_size);
            }
        });
        size40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_size = 40;
                size40.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                size60.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                size80.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                size100.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                size20.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                text.setTextSize(text_size);

            }
        });
        size60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_size = 60;
                size60.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                size40.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                size80.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                size100.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                size20.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                text.setTextSize(text_size);
            }
        });
        size80.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_size = 80;
                size80.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                size60.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                size40.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                size100.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                size20.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                text.setTextSize(text_size);
            }
        });
        size100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_size = 100;
                size100.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.round));
                size80.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                size60.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                size40.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                size20.setBackground(ContextCompat.getDrawable(Led_EditActivity.this, R.drawable.numberbg));
                text.setTextSize(text_size);
            }
        });

        iv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_EditActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(Led_EditActivity.this, Led_MakeVideoActrivity.class);
                        bundle.putBoolean("isBlink", isBlink);
                        bundle.putFloat("textsize", text_size);
                        String charSequence = text.getText().toString();
                        int currentTextColor = text.getCurrentTextColor();
                        intent.putExtra("duration", duration);
                        intent.putExtra("marqueetext", charSequence);
                        intent.putExtra("textcolor", currentTextColor);
                        intent.putExtra("textstyle", text_style);
                        if (arrayB == 0) {
                            int color = ((ColorDrawable) relativeLayout.getBackground()).getColor();
                            intent.putExtra("textbackgroundcolor", color);
                        } else {
                            try {
                                int color = ((ColorDrawable) relativeLayout.getBackground()).getColor();
                                intent.putExtra("textbackgroundcolor", color);
                            } catch (Exception e) {
                                intent.putExtra("bg", arrayB);
                            }
                        }
                        intent.putExtra("check", check);
                        intent.putExtras(bundle);
                        startActivity(intent);
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

    public void None() {
    }


    private void blink() {
        if (editText_textt.getText().toString().trim().equals("")) {
            Toast.makeText(Led_EditActivity.this, getResources().getString(R.string.entertext), Toast.LENGTH_SHORT).show();
            switch1.setChecked(false);
        } else if (anim == null) {
            isBlink = true;
            anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(50);
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            text.startAnimation(anim);
        } else {
            isBlink = false;
            text.clearAnimation();
            anim = null;
        }
    }

    public void left_to_right() {
        animator = ValueAnimator.ofFloat(new float[]{0.0f, 2.0f});
        animator.setRepeatCount(-1);
        animator.setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                int round = Math.round(text.getPaint().measureText(text.getText().toString()));
                ViewGroup.LayoutParams layoutParams = text.getLayoutParams();
                layoutParams.width = round;
                text.setLayoutParams(layoutParams);
                getWindowManager().getDefaultDisplay().getRealMetrics(new DisplayMetrics());
                float f = (float) round;
                float f2 = floatValue * f;
                text.setTranslationX(f2);
                text.setTranslationX(f2 - f);
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
                int round = Math.round(text.getPaint().measureText(text.getText().toString()));
                ViewGroup.LayoutParams layoutParams = text.getLayoutParams();
                layoutParams.width = round;
                text.setLayoutParams(layoutParams);
                getWindowManager().getDefaultDisplay().getRealMetrics(new DisplayMetrics());
                float f = (float) round;
                float f2 = floatValue * f;
                text.setTranslationX(f2);
                text.setTranslationX(f2 - f);
            }
        });
        animator.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        right_to_left();
    }
}
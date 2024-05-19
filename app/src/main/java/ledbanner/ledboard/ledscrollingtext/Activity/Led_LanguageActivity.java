package ledbanner.ledboard.ledscrollingtext.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.ads.nativetemplates.TemplateView;
import ledbanner.ledboard.ledscrollingtext.Adapter.LanguageAdapter;
import ledbanner.ledboard.ledscrollingtext.AppUtils.App;
import ledbanner.ledboard.ledscrollingtext.Model.LanguageModel;
import ledbanner.ledboard.ledscrollingtext.R;
import ledbanner.ledboard.ledscrollingtext.admodule.AllAdCommonClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class Led_LanguageActivity extends AppCompatActivity {
    RecyclerView rvLanguage;
    LanguageModel selectedModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_language);
        rvLanguage = findViewById(R.id.rcl_language);

        ShimmerFrameLayout shimmer_view_container = findViewById(R.id.shimmer_view_container);
        AllAdCommonClass.NativeLoad(this, (TemplateView) findViewById(R.id.my_template), findViewById(R.id.fram_fbnative), shimmer_view_container);

        findViewById(R.id.iv_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getInstance().setStoreStringValue("pref_selected_language", selectedModel.getLangCode());
                App.getInstance().setStoreBooleanValue("isLanguageSelected", true);
                updateResources();
                AllAdCommonClass.AdShowdialogFirstActivityQue(Led_LanguageActivity.this, new AllAdCommonClass.MyListener() {
                    @Override
                    public void callback() {
                        Intent i = new Intent(Led_LanguageActivity.this, Led_HomeActivity.class);
                        startActivity(i);
                        finish();
                    }
                });


            }
        });
        String selectedCode = App.getInstance().getStoreStringValue("pref_selected_language");
        String language = Locale.getDefault().getLanguage();

        ArrayList<LanguageModel> languageModels = new ArrayList<>();
        languageModels.add(new LanguageModel("English", "en", R.drawable.us));
        languageModels.add(new LanguageModel("German", "de", R.drawable.german));
        languageModels.add(new LanguageModel("Portugal", "pt", R.drawable.portugal));
        languageModels.add(new LanguageModel("France", "fr", R.drawable.france));
        languageModels.add(new LanguageModel("Spanish", "es", R.drawable.spanish));
        languageModels.add(new LanguageModel("Hindi", "hi", R.drawable.india));

        boolean z10;
        LanguageModel iVar = null;
        if (selectedCode == null || selectedCode.trim().isEmpty()) {
            z10 = false;
        } else {
            Iterator it = languageModels.iterator();
            z10 = false;
            while (it.hasNext()) {
                LanguageModel iVar2 = (LanguageModel) it.next();
                if (selectedCode.equals(iVar2.getLangCode())) {
                    iVar2.setSelected(true);
                    z10 = true;

                    selectedModel = iVar2;

                } else {
                    iVar2.setSelected(false);
                }
                if (language != null && !language.trim().isEmpty() && language.equals(iVar2.getLangCode())) {
                    iVar = iVar2;
                }
            }
        }
        if (!z10) {
            Iterator it2 = languageModels.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                LanguageModel iVar3 = (LanguageModel) it2.next();
                if (iVar3.getLangCode().equals("en")) {
                    iVar3.setSelected(true);
                    iVar = iVar3;
                    break;
                }
            }
        }
        if (iVar != null) {
            languageModels.remove(iVar);
            languageModels.add(0, iVar);
        }

        LanguageAdapter rVar = new LanguageAdapter(Led_LanguageActivity.this, languageModels, new LanguageAdapter.LangClickListener() {
            @Override
            public void onCLick(LanguageModel model) {
                selectedModel = model;
            }
        });
        rvLanguage.setAdapter(rVar);

    }

    private void updateResources() {
        Locale locale = new Locale(App.getInstance().getStoreStringValue("pref_selected_language"));
        Locale.setDefault(locale);
        Configuration configuration = getResources().getConfiguration();
        createConfigurationContext(configuration);
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
    }
}

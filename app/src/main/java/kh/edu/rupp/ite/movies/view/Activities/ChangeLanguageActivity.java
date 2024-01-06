// LanguageChangeActivity.java
package kh.edu.rupp.ite.movies.view.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import kh.edu.rupp.ite.movies.R;
import kh.edu.rupp.ite.movies.databinding.ActivityLanguageChangeBinding;

public class ChangeLanguageActivity extends AppCompatActivity {

    private RadioGroup radioGroupLanguage;
    private RadioButton radioButtonEnglish, radioButtonKhmer;
    private Button buttonApply;
    private ImageView back;
    private ActivityLanguageChangeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_change);

        // Initialize views
        radioGroupLanguage = findViewById(R.id.radioGroupLanguage);
        radioButtonEnglish = findViewById(R.id.radioButtonEnglish);
        radioButtonKhmer = findViewById(R.id.radioButtonKhmer);
        buttonApply = findViewById(R.id.buttonApply);
        back = findViewById(R.id.back);


        back.setOnClickListener(view -> {
            finish();
        });
        // Set click listener for Apply button

        setInitialRadioState();
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected language
                String selectedLanguage = radioButtonEnglish.isChecked() ? "en" : "km";

                // Change the app's locale based on the selected language
                setLocale(selectedLanguage);

                // Restart the app to apply the new language
                restartApp();
            }
        });



    }

    private void setInitialRadioState() {
        // Get the current locale
        Locale currentLocale = getResources().getConfiguration().locale;

        // Set the radio button state based on the current locale
        if (currentLocale.getLanguage().equals("km")) {
            radioButtonKhmer.setChecked(true);
        } else {
            radioButtonEnglish.setChecked(true);
        }
    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
    }

    private void restartApp() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}

package com.stockcrunch.stockbuzzinitial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;

public class SettingActivity extends AppCompatActivity {

    private ImageView backPressed;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        Animation bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        mAuth = FirebaseAuth.getInstance();

        backPressed = findViewById(R.id.backPressed);
        backPressed.setOnClickListener(v -> {
            backPressed.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce));
            finish();
            overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
        });

        ImageView aboutUS = findViewById(R.id.aboutUS);
        aboutUS.setOnClickListener(v -> {
            BottomSheetDialog sheetDialog = new BottomSheetDialog(SettingActivity.this,
                    R.style.Theme_Design_BottomSheetDialog);
            View sheetView = LayoutInflater.from(getApplicationContext()).
                    inflate(R.layout.aboutus_bottomsheet, (LinearLayout) findViewById(R.id.bottomSheet));

            sheetView.findViewById(R.id.closeSheet).setOnClickListener(v12 -> sheetDialog.dismiss());
            sheetView.findViewById(R.id.developerSupport).setOnClickListener(v1 -> {

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@stockcrunch.xyz"}); // recipients
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Support/help");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Namaste,");
                startActivity(Intent.createChooser(emailIntent, "Select your Email app"));

            });
            sheetDialog.setContentView(sheetView);
            sheetDialog.show();

        });

        ImageView shareApp = findViewById(R.id.shareApp);
        shareApp.setOnClickListener(v -> {

            Intent intent = new Intent();
            intent.setType("text/plain");
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "Your million-dollar shortcut!!\n Check out stockCrunch app.\n Made in Nepal🇳🇵\n https://play.google.com/store/apps/details?id=com.stockcrunch.stockbuzzinitial");
            if (getApplicationContext() != null && intent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
                startActivity(intent);
            }
        });

        ImageView rateApp = findViewById(R.id.rateApp);
        rateApp.setOnClickListener(v -> gotoUrl("https://play.google.com/store/apps/details?id=com.stockcrunch.stockbuzzinitial"));

        ImageView feedBack = findViewById(R.id.feedBack);
        feedBack.setOnClickListener(v -> {


            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"feedback@stockcrunch.xyz"}); // recipients
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Message For General Manager");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Namaste,");
            startActivity(Intent.createChooser(emailIntent, "Select your Email app"));
        });

        ImageView contactWapps = findViewById(R.id.contactWapps);
        contactWapps.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + "9779745552867" + "&text=" + "Namaste!!");
            Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(sendIntent);

        });

        ImageView termsAndConditions = findViewById(R.id.termsAndConditions);
        termsAndConditions.setOnClickListener(v -> gotoUrl("https://docs.google.com/document/d/1ZsLzM31kmOu6kENDjfku3-OpxYF1l0vEq2f__s_W3TM/edit?usp=sharing"));

        ImageView privacy = findViewById(R.id.privacy);
        privacy.setOnClickListener(v -> gotoUrl("https://docs.google.com/document/d/1ZsLzM31kmOu6kENDjfku3-OpxYF1l0vEq2f__s_W3TM/edit?usp=sharing"));

        ImageView logOut = findViewById(R.id.logOut);
        logOut.setOnClickListener(v -> {
            mAuth.signOut();
            Intent i = new Intent(SettingActivity.this,GoogleSignInActivity.class);
            i.putExtra("finish", true); // if you are checking for this in your other Activities
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });





        /*Relative layout for social media*/
        RelativeLayout faceBook = findViewById(R.id.faceBook);
        RelativeLayout instaGram = findViewById(R.id.instaGram);
        RelativeLayout twitter = findViewById(R.id.twitter);
        RelativeLayout youTube = findViewById(R.id.youTube);

        faceBook.setOnClickListener(v -> gotoUrl("https://www.facebook.com/stockbuzznepal"));

        instaGram.setOnClickListener(v -> gotoUrl("https://www.instagram.com/stock_buzz_nepal/"));

        twitter.setOnClickListener(v -> gotoUrl("https://twitter.com/stockBuzz_app"));

        youTube.setOnClickListener(v -> gotoUrl("https://www.youtube.com/channel/UCfBB3LYFRMsRzmc4e9e8XXg"));


    }

    /*URL calling for social media starts here*/
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
    /*URL calling for social media ends here*/

}



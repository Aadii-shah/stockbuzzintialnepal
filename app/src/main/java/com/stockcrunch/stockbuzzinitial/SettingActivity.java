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
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class SettingActivity extends AppCompatActivity {

    private ImageView backPressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        Animation bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);

        backPressed = findViewById(R.id.backPressed);
        backPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPressed.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce));
                finish();
                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
            }
        });

        ImageView aboutUS = findViewById(R.id.aboutUS);
        aboutUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog sheetDialog = new BottomSheetDialog(SettingActivity.this,
                        R.style.Theme_Design_BottomSheetDialog);
                View sheetView = LayoutInflater.from(getApplicationContext()).
                        inflate(R.layout.aboutus_bottomsheet, (LinearLayout) findViewById(R.id.bottomSheet));

                sheetView.findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sheetDialog.dismiss();
                    }
                });
                sheetView.findViewById(R.id.developerSupport).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"stockmarketnepalyco@gmail.com"}); // recipients
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Support/help");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Namaste,");
                        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
                        emailIntent.setPackage("com.google.android.gm");
                        startActivity(emailIntent);*/

                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@stockcrunch.xyz"}); // recipients
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Support/help");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Namaste,");
                        startActivity(Intent.createChooser(emailIntent, "Select your Email app"));

                    }
                });
                sheetDialog.setContentView(sheetView);
                sheetDialog.show();
                
            }
        });

        ImageView shareApp = findViewById(R.id.shareApp);
        shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent();
                    intent.setType("text/plain");
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, "Your million-dollar shortcut!!\n Check out stockCrunch app.\n Made in Nepal🇳🇵");
                    String shareApp = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    intent.putExtra(Intent.EXTRA_TEXT, shareApp);
                    startActivity(Intent.createChooser(intent, "SHARE BY"));
                } catch (Exception e) {
                    Toast.makeText(SettingActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            }
        });

        ImageView rateApp = findViewById(R.id.rateApp);
        rateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n");
            }
        });

        ImageView feedBack = findViewById(R.id.feedBack);
        feedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"stockmarketnepalyco@gmail.com"}); // recipients
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Namaste,");
                emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
                emailIntent.setPackage("com.google.android.gm");
                startActivity(emailIntent);*/

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"feedback@stockcrunch.xyz"}); // recipients
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Message For General Manager");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Namaste,");
                startActivity(Intent.createChooser(emailIntent, "Select your Email app"));
            }
        });

        ImageView contactWapps = findViewById(R.id.contactWapps);
        contactWapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + "9779745552867" + "&text=" + "Namaste!!");
                Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(sendIntent);

            }
        });

        ImageView termsAndConditions = findViewById(R.id.termsAndConditions);
        termsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://docs.google.com/document/d/1ZsLzM31kmOu6kENDjfku3-OpxYF1l0vEq2f__s_W3TM/edit?usp=sharing");
            }
        });

        ImageView privacy = findViewById(R.id.privacy);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://docs.google.com/document/d/1ZsLzM31kmOu6kENDjfku3-OpxYF1l0vEq2f__s_W3TM/edit?usp=sharing");
            }
        });




        /*Relative layout for social media*/
        RelativeLayout faceBook = findViewById(R.id.faceBook);
        RelativeLayout instaGram = findViewById(R.id.instaGram);
        RelativeLayout twitter = findViewById(R.id.twitter);
        RelativeLayout youTube = findViewById(R.id.youTube);

        faceBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.facebook.com/stockbuzznepal");

            }

        });

        instaGram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.instagram.com/stock_buzz_nepal/");

            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://twitter.com/stockBuzz_app");
            }
        });

        youTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.youtube.com/channel/UCfBB3LYFRMsRzmc4e9e8XXg");
            }
        });


    }

    /*URL calling for social media starts here*/
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
    /*URL calling for social media ends here*/

}



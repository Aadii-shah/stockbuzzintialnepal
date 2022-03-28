package com.stockcrunch.stockbuzzinitial;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;


public class ContactActivity extends AppCompatActivity {

    private ImageView backPressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        Animation bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        LottieAnimationView animationView = findViewById(R.id.mobile_view);

        backPressed = findViewById(R.id.backPressed);
        backPressed.setOnClickListener(v -> {
            backPressed.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce));
            finish();
            overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
        });

        TextView share_us = findViewById(R.id.share_us);
        share_us.setOnClickListener(v -> {

                Intent intent = new Intent();
                intent.setType("text/plain");
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Your million-dollar shortcut!!\n Check out stockCrunch app.\n Made in Nepal🇳🇵\n https://play.google.com/store/apps/details?id=com.stockcrunch.stockbuzzinitial");
                if (getApplicationContext() != null && intent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
                    startActivity(intent);
                }

        });

        TextView rate_us = findViewById(R.id.rate_us);
        rate_us.setOnClickListener(v -> gotoUrl("https://play.google.com/store/apps/details?id=com.stockcrunch.stockbuzzinitial"));

        TextView termsAndConditions = findViewById(R.id.termsAndConditions);
        termsAndConditions.setOnClickListener(v -> gotoUrl("https://docs.google.com/document/d/1ZsLzM31kmOu6kENDjfku3-OpxYF1l0vEq2f__s_W3TM/edit?usp=sharing"));




        /*Initialize linear layout for contact Info*/
        LinearLayout contactLocation = findViewById(R.id.contactLocation);
        LinearLayout dialNumber = findViewById(R.id.dialNumber);
        LinearLayout emailUs = findViewById(R.id.emailUs);
        LinearLayout contactGm = findViewById(R.id.contactGm);

        contactLocation.setOnClickListener(v -> {
            Uri location = Uri.parse("https://goo.gl/maps/7kdixdLgSc44kS1n9");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
            Intent chooser = Intent.createChooser(mapIntent, "");
            try {
                startActivity(chooser);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(ContactActivity.this, "No specific app found", Toast.LENGTH_SHORT).show();
            }
        });

        dialNumber.setOnClickListener(v -> {
            Uri number = Uri.parse("tel:9745552867");
            Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
            startActivity(callIntent);

        });
        emailUs.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"stockmarketnepalyco@gmail.com"}); // recipients
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Namaste,");
            emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
            emailIntent.setPackage("com.google.android.gm");
            startActivity(emailIntent);
        });

        contactGm.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"manager@stockcrunch.xyz"}); // recipients
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Message For General Manager");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Namaste,");
            startActivity(Intent.createChooser(emailIntent,"Select your Email app"));
        });

    }
    /*URL calling for contact info starts here*/
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
    /*URL calling for contact info ends here*/
}
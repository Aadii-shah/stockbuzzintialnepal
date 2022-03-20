package com.stockcrunch.stockbuzzinitial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<FeedModelClass> feedModelClasses;
    FeedAdapter feedAdapter;
    ArrayList<FeedModelNepaliClass> feedModelNepaliClasses;
    FeedNepaliAdapter feedNepaliAdapter;
    private TextView english, nepali;
    private ImageView setting, contactUs;
    /*Viewpager2 for english feed*/
    private ViewPager2 recyclerFeed;
    /*RECYCLERVIEWFORNEPALIFEED*/
    private ViewPager2 recyclerFeedNe;
    //private ShimmerFrameLayout shimmerFrameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        //DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        //mDatabase.keepSynced(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("notifications", "notifications",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
       /* FirebaseMessaging.getInstance().subscribeToTopic("all")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Success";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });*/

       /* Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        Animation bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);*/

        //shimmerFrameLayout = findViewById(R.id.shimmerLayout);
        /*Declare ImageView*/
        setting = findViewById(R.id.setting);
        contactUs = findViewById(R.id.contactUs);

        setting.setOnClickListener(v -> {
            setting.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce));
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_up, R.anim.slide_down);


        });

        contactUs.setOnClickListener(v -> {
            contactUs.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce));
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

        });


        /*Viewpager2 declaration for Nepali Feed*/
        recyclerFeedNe = findViewById(R.id.recyclerNepali);
        feedModelNepaliClasses = new ArrayList<>();
        feedNepaliAdapter = new FeedNepaliAdapter(feedModelNepaliClasses, MainActivity.this);
        recyclerFeedNe.setAdapter(feedNepaliAdapter);
        recyclerFeedNe.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        recyclerFeedNe.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        recyclerFeedNe.setPageTransformer(new VerticalTransformation());
        fetchNepaliFeedimage();

        /*Viewpager2 declaration for English Feed*/
        recyclerFeed = findViewById(R.id.recyclerFeed);
        feedModelClasses = new ArrayList<>();
        feedAdapter = new FeedAdapter(feedModelClasses, MainActivity.this);
        recyclerFeed.setAdapter(feedAdapter);
        recyclerFeed.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        recyclerFeed.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        recyclerFeed.setPageTransformer(new VerticalTransformation());
        fetchFeedimage();

        english = findViewById(R.id.english);
        nepali = findViewById(R.id.nepali);

        english.setOnClickListener(v -> {
            recyclerFeedNe.setVisibility(View.GONE);
            recyclerFeed.setVisibility(View.VISIBLE);
            nepali.setTextColor(Color.parseColor("#000000"));
            english.setTextColor(Color.parseColor("#FFFFFF"));
            //english.setBackground(getResources().getDrawable(R.drawable.rectangle_background_filled));
            english.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.rectangle_background_filled));
            nepali.setBackground(null);
            Toast.makeText(getApplicationContext(), "English Language", Toast.LENGTH_SHORT).show();

        });

        nepali.setOnClickListener(v -> {
            recyclerFeed.setVisibility(View.GONE);
            recyclerFeedNe.setVisibility(View.VISIBLE);
            english.setTextColor(Color.parseColor("#000000"));
            nepali.setTextColor(Color.parseColor("#FFFFFF"));
           // nepali.setBackground(getResources().getDrawable(R.drawable.rectangle_background_filled));
            nepali.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.rectangle_background_filled));
            english.setBackground(null);
            Toast.makeText(getApplicationContext(), "नेपाली भाषा", Toast.LENGTH_SHORT).show();
        });

    }


    /*Loading Data For Nepali Feed*/
    private void fetchNepaliFeedimage() {
      /* fetchingNep = new ProgressDialog(this);
        //fetchingEng .setMax(100);
        fetchingNep .setMessage("UPLOADING........");
        fetchingNep .setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        fetchingNep.show();
        fetchingNep.setCancelable(false);*/
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        mDatabase.orderByValue().limitToLast(10);
            //mDatabase.child("MainFeedNepali").child("fiNep").addValueEventListener(new ValueEventListener() {
                mDatabase.child("Nep").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    if (dataSnapshot.getValue() != null) {
                        //Log.v("BeautyPage", "ok " + Objects.requireNonNull(dataSnapshot.getValue()).toString());
                        /*fetchingNep.dismiss();
                        recyclerFeedNe.setVisibility(View.VISIBLE);*/
                        List<FeedModelNepaliClass> NepalifeedList = new ArrayList<>();
                        //NepalifeedList.clear();

                        for (DataSnapshot feedSnapshot : dataSnapshot.getChildren()) {
                            FeedModelNepaliClass NepalifeedItem = feedSnapshot.getValue(FeedModelNepaliClass.class);
                            NepalifeedList.add(0, NepalifeedItem);
                        }
                        feedModelNepaliClasses.clear();
                        feedModelNepaliClasses.addAll(NepalifeedList);

                        // refreshing recycler view
                        feedNepaliAdapter.notifyDataSetChanged();

                    }  //todo
                }

            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

                Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    /*Loading Data For English Feed*/
    private void fetchFeedimage() {
        /*fetchingEng = new ProgressDialog(this);
        //fetchingEng .setMax(100);
        fetchingEng .setMessage("UPLOADING........");
        fetchingEng .setProgressStyle(ProgressDialog.STYLE_SPINNER);
        fetchingEng .show();
        fetchingEng .setCancelable(false);*/
        //shimmerFrameLayout.stopShimmer();
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        mDatabase.orderByValue().limitToLast(10);
        //mDatabase.child("MainFeedEnglish").child("fiEn").addValueEventListener(new ValueEventListener() {
             mDatabase.child("Eng").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.getValue() != null) {
                        //fetchingEng.dismiss();
                        //recyclerFeed.setVisibility(View.VISIBLE);
                        // Log.v("BeautyPage", "ok " + Objects.requireNonNull(dataSnapshot.getValue()).toString());
                        List<FeedModelClass> feedList = new ArrayList<>();
                        //feedList.clear();
                        for (DataSnapshot feedSnapshot : dataSnapshot.getChildren()) {
                            FeedModelClass feedItem = feedSnapshot.getValue(FeedModelClass.class);
                            feedList.add(0, feedItem);

                        }
                        feedModelClasses.clear();
                        feedModelClasses.addAll(feedList);
                        // refreshing recycler view
                        feedAdapter.notifyDataSetChanged();
                    }  //todo
                }

            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

                Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
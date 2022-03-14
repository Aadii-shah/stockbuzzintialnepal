package com.example.stockbuzzinitial;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FeedNepaliAdapter extends RecyclerView.Adapter<FeedNepaliAdapter.ViewHolder> {
    ArrayList<FeedModelNepaliClass> feedModelNepaliClasses;
    Context context;

    public FeedNepaliAdapter(ArrayList<FeedModelNepaliClass> feedModelNepaliClasses, Context context) {
        this.feedModelNepaliClasses = feedModelNepaliClasses;
        this.context = context;
    }

    @NonNull
    @Override

    public FeedNepaliAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_items_nepali,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedNepaliAdapter.ViewHolder holder, int position) {
        //  holder.imageView.setImageResource(mainModels.get(position).getLangLogo());
        FeedModelNepaliClass feedModelNepaliClass= feedModelNepaliClasses.get(position);
        //holder.heading.setText(blogMoreForYouModelClass.getBlogHeading());
        /*Shimmer shimmer = new Shimmer.ColorHighlightBuilder()
                .setBaseColor(Color.parseColor("#B3B3B3"))
                .setBaseAlpha(1)
                .setHighlightColor(Color.parseColor("#FFFFFF"))
                .setHighlightAlpha(1)
                .setDropoff(50)
                .build();
        ShimmerDrawable shimmerDrawable = new ShimmerDrawable();
        shimmerDrawable.setShimmer(shimmer);*/
        Glide.with(context)
                .load(feedModelNepaliClass.getNE())
               // .placeholder(shimmerDrawable)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.feedImageViewNe);




    }

    @Override
    public int getItemCount() {
        return feedModelNepaliClasses.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView feedImageViewNe;

        public ViewHolder(@NonNull @NotNull View item) {
            super(item);
            feedImageViewNe=item.findViewById(R.id.feedImageNe);
        }
    }
}

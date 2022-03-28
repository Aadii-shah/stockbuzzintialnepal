package com.stockcrunch.stockbuzzinitial;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    final ArrayList<FeedModelClass> feedModelClasses;
    final Context context;

    public FeedAdapter(ArrayList<FeedModelClass> feedModelClasses, Context context) {
        this.feedModelClasses = feedModelClasses;
        this.context = context;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_items, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //  holder.imageView.setImageResource(mainModels.get(position).getLangLogo());
        FeedModelClass feedModelClass = feedModelClasses.get(position);
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
                .load(feedModelClass.getEN())
                // .placeholder(shimmerDrawable)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.feedImageView);
    }

    @Override
    public int getItemCount() {
        return feedModelClasses.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView feedImageView;

        public ViewHolder(@NonNull @NotNull View item) {
            super(item);
            feedImageView = item.findViewById(R.id.feedImage);
        }
    }
}

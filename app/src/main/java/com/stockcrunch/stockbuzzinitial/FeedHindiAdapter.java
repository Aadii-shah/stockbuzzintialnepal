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

public class FeedHindiAdapter extends RecyclerView.Adapter<FeedHindiAdapter.ViewHolder> {
    final ArrayList<FeedModelHindiClass> feedModelHindiClasses;
    final Context context;

    public FeedHindiAdapter(ArrayList<FeedModelHindiClass> feedModelHindiClasses, Context context) {
        this.feedModelHindiClasses = feedModelHindiClasses;
        this.context = context;
    }

    @NonNull
    @Override

    public FeedHindiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_items_hindi, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedHindiAdapter.ViewHolder holder, int position) {
        //  holder.imageView.setImageResource(mainModels.get(position).getLangLogo());
        FeedModelHindiClass feedModelHindiClass = feedModelHindiClasses.get(position);
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
                .load(feedModelHindiClass.getHI())
                // .placeholder(shimmerDrawable)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.feedImageViewHi);


    }

    @Override
    public int getItemCount() {
        return feedModelHindiClasses.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView feedImageViewHi;

        public ViewHolder(@NonNull @NotNull View item) {
            super(item);
            feedImageViewHi = item.findViewById(R.id.feedImageHi);
        }
    }
}

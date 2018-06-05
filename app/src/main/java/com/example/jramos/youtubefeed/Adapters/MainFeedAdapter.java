package com.example.jramos.youtubefeed.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jramos.youtubefeed.CourseListActivity;
import com.example.jramos.youtubefeed.Models.Video;
import com.example.jramos.youtubefeed.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainFeedAdapter extends RecyclerView.Adapter<MainFeedAdapter.ViewHolder>{

    List<Video> listOfVideos;
    Context context;

    public MainFeedAdapter(Context _context, List<Video> videos){
        context = _context;
        listOfVideos = videos;
    }

    @Override
    public int getItemCount(){
        return listOfVideos.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View currentRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_row, parent, false);
        return new ViewHolder(currentRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Video currentItem = listOfVideos.get(position);

        holder.title.setText( currentItem.getName() );
        holder.channelName.setText( currentItem.getChannel().getName() );

        Picasso.get()
                .load( currentItem.getImgUrl() )
                .into( holder.imgVideoPreview );
        Picasso.get()
                .load(currentItem.getChannel().getImgProfile())
                .into(holder.imgchannel);

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CourseListActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt("courseId", currentItem.getId());
                bundle.putString("courseTitle", currentItem.getName());
                i.putExtras(bundle);

                context.startActivity(i);
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
         TextView title, channelName;
         ImageView imgVideoPreview, imgchannel;
         ConstraintLayout root;

        ViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.txtTitle);
            channelName = view.findViewById(R.id.txtChannel);
            imgVideoPreview = view.findViewById(R.id.imgVideoPreview);
            imgchannel = view.findViewById(R.id.imgChannel);
            root = view.findViewById(R.id.videosFeed_itemRoot);
        }
    }

}



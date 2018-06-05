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

import com.example.jramos.youtubefeed.CourseActivity;
import com.example.jramos.youtubefeed.Models.Course;
import com.example.jramos.youtubefeed.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseViewHolder> {

    Context context;
    List<Course> courses;

    public CourseListAdapter(Context _contex, List<Course> _courses){
        context = _contex;
        courses = _courses;
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }


    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View currentRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_detail_row, parent, false);
        return new CourseListAdapter.CourseViewHolder(currentRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {

        final Course currentCourse = courses.get(position);

        holder.courseTitle.setText( currentCourse.getName() );
        String episodeText = "Episode #" + currentCourse.getNumberOfEpisode();
        holder.episodeNumber.setText( episodeText );
        holder.duration.setText(currentCourse.getDuration());

        Picasso.get().load(currentCourse.getImgUrl()).into(holder.imgCourse);

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CourseActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("link", currentCourse.getLink());
                bundle.putString("title", currentCourse.getName());
                i.putExtras(bundle);

                context.startActivity(i);

            }
        });
    }



    protected class CourseViewHolder extends RecyclerView.ViewHolder{

        ImageView imgCourse;
        TextView courseTitle, episodeNumber, duration;
        ConstraintLayout root;

        public CourseViewHolder(View itemView) {
            super(itemView);

            root = itemView.findViewById(R.id.course_list_item);
            imgCourse = itemView.findViewById(R.id.imgCourseDetailPreview);
            courseTitle = itemView.findViewById(R.id.txtCourseTitle);
            episodeNumber = itemView.findViewById(R.id.txtnumberOfEpisode);
            duration = itemView.findViewById(R.id.txtDuration);
        }
    }
}

package com.zeeshan.roomdatabase;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CourseRVAdapter extends ListAdapter<CourseModal,CourseRVAdapter.ViewHolder>
{
    // creating a variable for on item click listener

    private OnItemClickListener listener;

    // creating a constructor class for our adapter class
    CourseRVAdapter()
    {
        super(DIFF_CALLBACK);
    }

    // creating a call back for item of recycler view
    private static final DiffUtil.ItemCallback<CourseModal> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<CourseModal>() {
                @Override
                public boolean areItemsTheSame(CourseModal oldItem, CourseModal newItem)
                {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(CourseModal oldItem, CourseModal newItem) {
                    return oldItem.getCourseName().equals(newItem.getCourseName()) &&
                            oldItem.getCourseDescription().equals(newItem.getCourseDescription()) &&
                            oldItem.getCourseDuration().equals(newItem.getCourseDuration());
                }
            };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //
        // file for each item of our recycler view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        CourseModal model = getCourseAt(position);
        holder.courseNameTV.setText(model.getCourseName());
        holder.courseDescTV.setText(model.getCourseDescription());
        holder.courseDurationTV.setText(model.getCourseDuration());

    }

    // creating a method to get course modal for a specific position
    public CourseModal getCourseAt(int position)
    {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView courseNameTV, courseDescTV, courseDurationTV;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);
            courseDurationTV = itemView.findViewById(R.id.idTVCourseDuration);


            // adding on click listener for each item of recycler view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    // inside on click listener we are passing
                    // position to our item of recycler view

                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener
    {
        void onItemClick(CourseModal model);
    }
    public void setOnItemClicListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }
}

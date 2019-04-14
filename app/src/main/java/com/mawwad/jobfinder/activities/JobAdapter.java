package com.mawwad.jobfinder.activities;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mawwad.jobfinder.R;
import com.mawwad.jobfinder.model.Job;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JobAdapter extends RecyclerView.Adapter<JobViewHolder> {

    private List<Job> jobList ;

    public JobAdapter(List<Job> jobList){
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JobViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = jobList.get(position);


        if(job.getCompanyLogo() != null && !job.getCompanyLogo().isEmpty())
        Picasso.get().load(job.getCompanyLogo()).resize(400,400).centerInside().into(holder.companyLogoImageView);
        else
            Picasso.get().load(R.drawable.job).resize(400,400).centerInside().into(holder.companyLogoImageView);
        holder.companyLocationTextView.setText(job.getLocation());
        holder.companyNameTextView.setText(job.getCompany());
        holder.jobTitleTextView.setText(job.getTitle());
        holder.postedDateTextView.setText(job.getPostedDate());

    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }
}

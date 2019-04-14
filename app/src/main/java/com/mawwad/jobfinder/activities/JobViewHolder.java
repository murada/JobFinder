package com.mawwad.jobfinder.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mawwad.jobfinder.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class JobViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.company_logo)
    ImageView companyLogoImageView ;

    @BindView(R.id.job_title)
    TextView jobTitleTextView ;

    @BindView(R.id.company_name)
    TextView companyNameTextView ;

    @BindView(R.id.company_location)
    TextView companyLocationTextView ;

    @BindView(R.id.posted_date)
    TextView postedDateTextView ;


    public JobViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.job_recycler_view_item, parent, false));
        ButterKnife.bind(this,itemView);
    }
}

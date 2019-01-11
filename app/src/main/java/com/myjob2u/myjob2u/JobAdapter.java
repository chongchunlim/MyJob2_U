package com.myjob2u.myjob2u;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder>{

    private Context context;
    private List<PostNewJob> jobList;

    public JobAdapter(Context context, List<PostNewJob> jobList) {
        this.context = context;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_job_details,null);

        return new JobViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder jobViewHolder, int i) {

        PostNewJob postNewJob = jobList.get(i);

        jobViewHolder.recylerTitle.setText(postNewJob.getTitle());
        jobViewHolder.recyclerPoster.setText(postNewJob.getPoster());
        jobViewHolder.recyclerDesc.setText(postNewJob.getDesc());
        jobViewHolder.recyclerSalary.setText(postNewJob.getSalary());
        jobViewHolder.recyclerQ.setText(postNewJob.getQualification());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    class JobViewHolder extends RecyclerView.ViewHolder
    {
        TextView recylerTitle,recyclerDesc,recyclerPoster,recyclerSalary,recyclerQ;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);

            recylerTitle = itemView.findViewById(R.id.recyclerTitle);
            recyclerPoster = itemView.findViewById(R.id.recyclerPoster);
            recyclerDesc = itemView.findViewById(R.id.recyclerDesc);
            recyclerQ = itemView.findViewById(R.id.recyclerQ);
            recyclerSalary = itemView.findViewById(R.id.recyclerSalary);
        }
    }

}

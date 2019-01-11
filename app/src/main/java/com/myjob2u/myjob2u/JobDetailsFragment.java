package com.myjob2u.myjob2u;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobDetailsFragment extends Fragment {

    RecyclerView recyclerView;
    JobAdapter jobAdapter;
    List<PostNewJob> postNewJobList;
    DatabaseReference databaseUser;
    View RootView;
    FloatingActionButton btnTakeJob;

    public JobDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        databaseUser = FirebaseDatabase.getInstance().getReference("jobdetails");
         RootView = inflater.inflate(R.layout.fragment_jobdetails, container, false);

       /* btnTakeJob = RootView.findViewById(R.id.btnTakeJob);
        btnTakeJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newComplaint = new Intent(JobDetailsFragment.this.getActivity(), TakeJob.class);
                startActivity(newComplaint);
            }
        });*/


        recyclerView= RootView.findViewById(R.id.recyclerViewDetails);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));


        postNewJobList = new ArrayList<>();

        //postNewJobList.add(new PostNewJob("reatrd","Stsas","Ssasatitle","Strinsasasag title", "String title"));

        JobAdapter jobAdapter =  new JobAdapter(getActivity().getApplicationContext(),postNewJobList);
        recyclerView.setAdapter(jobAdapter);



        return RootView;
    }




    @Override
    public void onStart() {
        super.onStart();

        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                postNewJobList.clear();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                    PostNewJob postNewJob = userSnapshot.getValue(PostNewJob.class);

                    postNewJobList.add(postNewJob);

                }

                JobAdapter jobAdapter =  new JobAdapter(getActivity().getApplicationContext(),postNewJobList);
                recyclerView.setAdapter(jobAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                postNewJobList.clear();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                    PostNewJob postNewJob = userSnapshot.getValue(PostNewJob.class);

                    postNewJobList.add(postNewJob);

                }

                JobAdapter jobAdapter =  new JobAdapter(getActivity().getApplicationContext(),postNewJobList);
                recyclerView.setAdapter(jobAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

package com.myjob2u.myjob2u;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class JobHistoryFragment extends Fragment {

    RecyclerView recyclerView;
    JobAdapter jobAdapter;
    List<PostNewJob> postNewJobList;
    DatabaseReference databaseUser;
    View RootView;
    SharedPreferences loginSession;
    String username;

    public JobHistoryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        databaseUser = FirebaseDatabase.getInstance().getReference("jobdetails");
        RootView = inflater.inflate(R.layout.fragment_jobdetails, container, false);

        loginSession = getActivity().getApplicationContext().getSharedPreferences("loginSession", MODE_PRIVATE);


        username = loginSession.getString("username","Anonymous");


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

                    if(postNewJob.getPoster().equals(username))
                        postNewJobList.add(postNewJob);

                }

                if (loginSession.getString("username","Anonymous").equals("Anonymous"))
                    postNewJobList.clear();

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

                    if(postNewJob.getPoster().equals(username))
                        postNewJobList.add(postNewJob);

                }

                if (loginSession.getString("username","Anonymous").equals("Anonymous"))
                    postNewJobList.clear();

                JobAdapter jobAdapter =  new JobAdapter(getActivity().getApplicationContext(),postNewJobList);
                recyclerView.setAdapter(jobAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

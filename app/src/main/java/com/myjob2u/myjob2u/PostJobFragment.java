package com.myjob2u.myjob2u;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class PostJobFragment extends Fragment {

    List<JobList> jobLists;
    RecyclerView recyclerView;
    JobAdapter jobAdapter;
    List<PostNewJob> postNewJobList;
    DatabaseReference databaseUser;
    View RootView;
    SharedPreferences loginSession;
    String username;
    TextView tv;
    FloatingActionButton fab;



    public PostJobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        RootView = inflater.inflate(R.layout.fragment_postjob, container, false);

        databaseUser = FirebaseDatabase.getInstance().getReference("jobdetails");


        loginSession = getActivity().getApplicationContext().getSharedPreferences("loginSession", MODE_PRIVATE);

        username = loginSession.getString("username","Anonymous");

         fab = RootView.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newComplaint = new Intent(PostJobFragment.this.getActivity(), NewJob.class);
                startActivity(newComplaint);
            }
        });



        // Inflate the layout for this fragment
        tv = RootView.findViewById(R.id.textViewNonMemberNotify);
        recyclerView = RootView.findViewById(R.id.recyclerPostJob);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));


        loginSession = this.getActivity().getSharedPreferences("loginSession", MODE_PRIVATE);

        String username = loginSession.getString("username","Anonymous");

        if(username.equals("Anonymous"))
        {
            fab.hide();
            tv.setVisibility(View.VISIBLE);
        }else {
            fab.show();
            tv.setVisibility(View.INVISIBLE);
        }

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

                if (loginSession.getString("username","Anonymous").equals("Anonymous")) {
                    postNewJobList.clear();

                    if(username.equals("Anonymous"))
                    {
                        fab.hide();
                        tv.setVisibility(View.VISIBLE);
                    }else {
                        fab.show();
                        tv.setVisibility(View.INVISIBLE);
                    }
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

                    if(postNewJob.getPoster().equals(username))
                        postNewJobList.add(postNewJob);

                }

                if (loginSession.getString("username","Anonymous").equals("Anonymous")) {
                    postNewJobList.clear();

                    if(username.equals("Anonymous"))
                    {
                        fab.hide();
                        tv.setVisibility(View.VISIBLE);
                    }else {
                        fab.show();
                        tv.setVisibility(View.INVISIBLE);
                    }
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

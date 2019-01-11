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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class JobHistoryFragment extends Fragment {



    DatabaseReference databaseUser;
    View RootView;
    SharedPreferences loginSession;
    String username;
    TextView bk1,bk2,bk3,bk4,bk5,bk6,bk7,bk8,bk9,bk10;
    List<String> bookmarkJobsList;

    public JobHistoryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        databaseUser = FirebaseDatabase.getInstance().getReference("takenjobs");
        RootView = inflater.inflate(R.layout.fragment_jobhistory, container, false);

        bookmarkJobsList =  new ArrayList<>();

        loginSession = getActivity().getApplicationContext().getSharedPreferences("loginSession", MODE_PRIVATE);


        username = loginSession.getString("username","Anonymous");

        bk1 = RootView.findViewById(R.id.bk1);
        bk2 = RootView.findViewById(R.id.bk2);
        bk3 = RootView.findViewById(R.id.bk3);
        bk4 = RootView.findViewById(R.id.bk4);
        bk5 = RootView.findViewById(R.id.bk5);
        bk6 = RootView.findViewById(R.id.bk6);
        bk7 = RootView.findViewById(R.id.bk7);
        bk8 = RootView.findViewById(R.id.bk8);
        bk9 = RootView.findViewById(R.id.bk9);
        bk10 = RootView.findViewById(R.id.bk10);


        //bk5.setText("fuckfuck");


        return RootView;
    }




     @Override
   public void onStart() {
        super.onStart();

       databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                bookmarkJobsList.clear();


                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                    BookmarkTakeJob bookmarkJob = userSnapshot.getValue(BookmarkTakeJob.class);


                    if (username.equals(bookmarkJob.getUsername()))
                        bookmarkJobsList.add(bookmarkJob.getJobTitle());

                }

                if (username.equals("Anonymous"))
                    bookmarkJobsList.clear();

                if (bookmarkJobsList.size()!=0) {
                    if (bookmarkJobsList.size() >= 1)
                        bk1.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 1));
                    if (bookmarkJobsList.size() >= 2)
                        bk2.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 2));
                    if (bookmarkJobsList.size() >= 3)
                        bk3.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 3));
                    if (bookmarkJobsList.size() >= 4)
                        bk4.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 4));
                    if (bookmarkJobsList.size() >= 5)
                        bk5.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 5));
                    if (bookmarkJobsList.size() >= 6)
                        bk6.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 6));
                    if (bookmarkJobsList.size() >= 7)
                        bk7.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 7));
                    if (bookmarkJobsList.size() >= 8)
                        bk8.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 8));
                    if (bookmarkJobsList.size() >= 9)
                        bk9.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 9));
                    if (bookmarkJobsList.size() >= 10)
                        bk10.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 10));

                }else
                    Toast.makeText(getActivity(),"you have no job history ",Toast.LENGTH_LONG).show();

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

                bookmarkJobsList.clear();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                    BookmarkTakeJob bookmarkJob = userSnapshot.getValue(BookmarkTakeJob.class);


                    if (username.equals(bookmarkJob.getUsername()))
                        bookmarkJobsList.add(bookmarkJob.getJobTitle());

                }

                if (username.equals("Anonymous"))
                    bookmarkJobsList.clear();

                if (bookmarkJobsList.size()!=0) {
                    if (bookmarkJobsList.size() >= 1)
                        bk1.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 1));
                    if (bookmarkJobsList.size() >= 2)
                        bk2.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 2));
                    if (bookmarkJobsList.size() >= 3)
                        bk3.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 3));
                    if (bookmarkJobsList.size() >= 4)
                        bk4.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 4));
                    if (bookmarkJobsList.size() >= 5)
                        bk5.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 5));
                    if (bookmarkJobsList.size() >= 6)
                        bk6.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 6));
                    if (bookmarkJobsList.size() >= 7)
                        bk7.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 7));
                    if (bookmarkJobsList.size() >= 8)
                        bk8.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 8));
                    if (bookmarkJobsList.size() >= 9)
                        bk9.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 9));
                    if (bookmarkJobsList.size() >= 10)
                        bk10.setText(bookmarkJobsList.get(bookmarkJobsList.size() - 10));

                }else
                    Toast.makeText(getActivity(),"you have no job history ",Toast.LENGTH_LONG).show();

            }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


}




}

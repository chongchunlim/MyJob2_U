package com.myjob2u.myjob2u;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {


List<User> userList;
DatabaseReference databaseUser;
    SharedPreferences loginSession;
    String username;
    TextView profUsername,profPassword,profEmail,profContact,profAddress,profAgeRange,profQualification,profBiography;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userList = new ArrayList<>();

        databaseUser = FirebaseDatabase.getInstance().getReference("users");

         loginSession = getSharedPreferences("loginSession", MODE_PRIVATE);

         username = loginSession.getString("username","Anonymous");

         profUsername = findViewById(R.id.profUsername);
        profPassword = findViewById(R.id.profPassword);
        profAddress = findViewById(R.id.profAddress);
        profEmail = findViewById(R.id.profEmail);
        profContact = findViewById(R.id.profContact);
        profQualification = findViewById(R.id.profQualification);
        profAgeRange = findViewById(R.id.profAgeRange);
        profBiography = findViewById(R.id.profBiography);


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                userList.clear();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User check = userSnapshot.getValue(User.class);

                    userList.add(check);

                }

                for(int i = 0;i<userList.size();i++)
                {
                    if(username.equals(userList.get(i).username))
                    {
                        profUsername.setText(userList.get(i).getUsername());
                        profPassword.setText(userList.get(i).getPassword());
                        profAddress.setText(userList.get(i).getAddress());
                        profQualification.setText(userList.get(i).getQualification());
                        profAgeRange.setText(userList.get(i).getAgeRange());
                        profBiography.setText(userList.get(i).getBiography());
                        profEmail.setText(userList.get(i).getEmail());
                        profContact.setText(userList.get(i).getContactno());

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



}

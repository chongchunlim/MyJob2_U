package com.myjob2u.myjob2u;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookmarksActivity extends AppCompatActivity
{

    DatabaseReference databaseUser;
    List<String> bookmarkTakeJobList;
    Spinner spinnerBookmark;
    Button btnBookmark;
    String workRequestType;

    public BookmarksActivity()
    {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bookmarkTakeJobList = new ArrayList<>();

        setContentView(R.layout.activity_bookmarks);

        spinnerBookmark = findViewById(R.id.spinnerBookmarkJob);

        databaseUser = FirebaseDatabase.getInstance().getReference("jobdetails");

        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                    PostNewJob postNewJob = userSnapshot.getValue(PostNewJob.class);

                    bookmarkTakeJobList.add(postNewJob.getTitle());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,bookmarkTakeJobList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBookmark.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        spinnerBookmark.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              /*  workRequestType = bookmarkTakeJobList.get(position);
                spinnerBookmark.setSelection(position);
                spinnerBookmark.setSelected(true);*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnBookmark = findViewById(R.id.buttonBookmarkJob);

        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookmarkJob();
            }
        });

    }

    private void bookmarkJob()
    {
       // Toast.makeText(this,String.valueOf(spinnerBookmark.getSelectedItem()),Toast.LENGTH_LONG).show();

        SharedPreferences loginSession = getSharedPreferences("loginSession", MODE_PRIVATE);

        String username = loginSession.getString("username","Anonymous");

        if(username.equals("Anonymous"))
        {
            Toast.makeText(this,"you havent log in",Toast.LENGTH_LONG).show();
            finish();
        }

        databaseUser = FirebaseDatabase.getInstance().getReference("takenjobs");

        String id = databaseUser.push().getKey();

        BookmarkTakeJob newBookmark = new BookmarkTakeJob(username,"spinner cannot work");

        databaseUser.child(id).setValue(newBookmark);

        Toast.makeText(this,"job successfully bookmarked",Toast.LENGTH_LONG).show();

        finish();
    }
}
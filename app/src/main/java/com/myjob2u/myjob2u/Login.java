package com.myjob2u.myjob2u;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;



public class Login extends AppCompatActivity {

    EditText loginUsername,loginPassword;
    Button buttonLogin;
    DatabaseReference databaseUser;
    List<User> userList;
    TextView signupLink;

    public SharedPreferences loginSession ;
    public SharedPreferences.Editor loginEdit ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseUser = FirebaseDatabase.getInstance().getReference("users");

        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);


        signupLink = findViewById(R.id.signuplink);
        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Registeruser.class));
                //finish();
            }
        });

        buttonLogin = findViewById(R.id.buttonLogin);

        userList = new ArrayList<>();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        loginSession = getApplicationContext().getSharedPreferences("loginSession", MODE_PRIVATE);
        loginEdit = loginSession.edit();

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

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


    }


    private void loginUser()
    {

        for(int i = 0;i<userList.size();i++)
        {
            if(loginUsername.getText().toString().equals(userList.get(i).username)&&loginPassword.getText().toString().equals(userList.get(i).password))
            {
                Toast.makeText(this,"welcome, "+userList.get(i).username,Toast.LENGTH_LONG).show();
                loginEdit.putString("username",loginUsername.getText().toString());
                loginEdit.commit();

                finish();
                break;

                //Intent intent = new Intent(this,MainActivity.class);
               // startActivity(intent);

            }

            if(i==userList.size()-1)
                Toast.makeText(this,"[[[ incorrect username or password. please re-enter ]]]",Toast.LENGTH_LONG).show();
        }
    }
}

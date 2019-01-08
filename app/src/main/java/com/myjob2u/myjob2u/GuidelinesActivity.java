package com.myjob2u.myjob2u;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class GuidelinesActivity extends AppCompatActivity {
    private ConstraintLayout constLayout;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guideline);
    }

    public void backMessage(View v){
        String snackBarMsg;
        if(count < 10){
            snackBarMsg = "Press back button or here to return";
        }else if(count >= 10 && count < 50){
            snackBarMsg = "Are you that bored? -.-";
        }else{
            snackBarMsg = "You";
        }
        count++;
        constLayout = findViewById(R.id.aboutusConstLayout);
        Snackbar snackbar = Snackbar
                .make(constLayout, snackBarMsg, Snackbar.LENGTH_LONG)
                .setAction("BACK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(GuidelinesActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });

        snackbar.show();
    }
}
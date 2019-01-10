package com.myjob2u.myjob2u;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Registeruser extends AppCompatActivity {
    EditText inputUsername,inputPassword,inputContact,inputAddress,inputBiography,inputEmail;
    Spinner inputAgeRange,inputQualification;
    Button buttonRegister;

    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);

        databaseUser = FirebaseDatabase.getInstance().getReference("users");

        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        inputAddress = findViewById(R.id.inputAddress);
        inputContact = findViewById(R.id.inputContactNumber);
        inputBiography = findViewById(R.id.inputBiography);
        inputEmail = findViewById(R.id.inputEmail);
        inputAgeRange = findViewById(R.id.spinnerAge);
        inputQualification = findViewById(R.id.spinnerQualification);

        buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    private boolean isEmptyText(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private void registerUser()
    {
       if(isEmptyText(inputUsername)||isEmptyText(inputPassword)||isEmptyText(inputAddress)||isEmptyText(inputContact)||isEmptyText(inputBiography)||isEmptyText(inputEmail))
        {
            Toast.makeText(this,"please enter everything",Toast.LENGTH_LONG).show();
            return;
        }

        if(inputAgeRange.getSelectedItem().toString().equals("under 10")&&inputQualification.getSelectedItem().toString().equals("Secondary school graduate"))
       {
           Toast.makeText(this,"you can't hold post secondary school qualifications at this age.",Toast.LENGTH_LONG).show();
           return;
       }else if(inputAgeRange.getSelectedItem().toString().equals("under 10")&&inputQualification.getSelectedItem().toString().equals("Diploma"))
        {
            Toast.makeText(this,"you can't hold post secondary school qualifications at this age.",Toast.LENGTH_LONG).show();
            return;
        }else if(inputAgeRange.getSelectedItem().toString().equals("under 10")&&inputQualification.getSelectedItem().toString().equals("Bachelors degree"))
        {
            Toast.makeText(this,"you can't hold post secondary school qualifications at this age.",Toast.LENGTH_LONG).show();
            return;
        }else if(inputAgeRange.getSelectedItem().toString().equals("under 10")&&inputQualification.getSelectedItem().toString().equals("Masters"))
        {
            Toast.makeText(this,"you can't hold post secondary school qualifications at this age.",Toast.LENGTH_LONG).show();
            return;
        }else if(inputAgeRange.getSelectedItem().toString().equals("under 10")&&inputQualification.getSelectedItem().toString().equals("Doctorate"))
        {
            Toast.makeText(this,"you can't hold post secondary school qualifications at this age.",Toast.LENGTH_LONG).show();
            return;
        }else if(inputAgeRange.getSelectedItem().toString().equals("10 to 20")&&inputQualification.getSelectedItem().toString().equals("Bachelors degree"))
        {
            Toast.makeText(this,"you can't hold post secondary school qualifications at this age.",Toast.LENGTH_LONG).show();
            return;
        }else if(inputAgeRange.getSelectedItem().toString().equals("10 to 20")&&inputQualification.getSelectedItem().toString().equals("Masters"))
        {
            Toast.makeText(this,"you can't hold post secondary school qualifications at this age.",Toast.LENGTH_LONG).show();
            return;
        }else if(inputAgeRange.getSelectedItem().toString().equals("10 to 20")&&inputQualification.getSelectedItem().toString().equals("Doctorate"))
        {
            Toast.makeText(this,"you can't hold post secondary school qualifications at this age.",Toast.LENGTH_LONG).show();
            return;
        }

        String id = databaseUser.push().getKey();

        User newUser = new User(inputUsername.getText().toString(),inputPassword.getText().toString(),inputEmail.getText().toString(),inputContact.getText().toString(),inputAddress.getText().toString(),inputAgeRange.getSelectedItem().toString(),inputQualification.getSelectedItem().toString(),inputBiography.getText().toString());

        databaseUser.child(id).setValue(newUser);

        Toast.makeText(this,"user successfully registered",Toast.LENGTH_LONG).show();

        finish();

    }
}

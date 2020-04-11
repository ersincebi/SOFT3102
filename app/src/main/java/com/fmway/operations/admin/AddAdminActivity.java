package com.fmway.operations.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fmway.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import androidx.appcompat.app.AppCompatActivity;

public class AddAdminActivity extends AppCompatActivity {
    EditText nameText, surnameText, emailText , phoneText, usernameText, passwordText;
    Button addAdminButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addadmin);


        nameText = findViewById(R.id.addAdminName);
        surnameText = findViewById(R.id.addAdminSurname);
        emailText = findViewById(R.id.addAdminEmail);
        phoneText = findViewById(R.id.addAdminPhone);
        usernameText = findViewById(R.id.addAdminUsername);
        passwordText = findViewById(R.id.addAdminPassword);
        addAdminButton=findViewById(R.id.addAdminButton);

    }


    public void addAdminAccount (View view) {

        final ParseUser user = new ParseUser();

        user.put("Name", nameText.getText().toString());
        user.put("Surname", surnameText.getText().toString());
        user.put("Email", emailText.getText().toString());
        user.put("Phone", phoneText.getText().toString());
        user.setUsername(usernameText.getText().toString());
        user.setPassword(passwordText.getText().toString());

        user.put("userType","admin");
        user.signUpInBackground(new SignUpCallback() {


            @Override
            public void done(ParseException e) {

                if (e!= null) {
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show(); //geçersiz isim şifre vs.
                } else {
                    Toast.makeText(getApplicationContext(),"New admin account is created!",Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);






                }


            }
        });


    }

}


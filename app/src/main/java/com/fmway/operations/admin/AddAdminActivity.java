package com.fmway.operations.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.user.UserParseDefinitions;
import com.fmway.models.user.UserTypes;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import androidx.appcompat.app.AppCompatActivity;

public class AddAdminActivity extends AppCompatActivity {
    private EditText nameText, surnameText, emailText , phoneText, usernameText, passwordText;
    private Button addAdminButton;
    private ParseUser user;
    private UserParseDefinitions definitions = new UserParseDefinitions();
    private UserTypes roles;

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
        user = new ParseUser();

        addUserToDb(
                user
                ,nameText.getText().toString()
                ,surnameText.getText().toString()
                ,emailText.getText().toString()
                ,phoneText.getText().toString()
                ,usernameText.getText().toString()
                ,passwordText.getText().toString()
        );

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

    public void addUserToDb(
            ParseUser user
            ,String name
            ,String surname
            ,String email
            ,String phone
            ,String username
            ,String password
    ){
        user.put(definitions.getNameKey(),name);
        user.put(definitions.getSurnameKey(),surname);
        user.put(definitions.getEmailKey(),email);
        user.put(definitions.getPhoneKey(),phone);
        user.setUsername(username);
        user.setPassword(password);
        user.put(definitions.getUserTypeKey(),roles.ADMIN.getUserType());
    }
}


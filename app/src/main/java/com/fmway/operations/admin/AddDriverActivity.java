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

public class AddDriverActivity extends AppCompatActivity {

    private EditText nameText, surnameText, emailText , phoneText, usernameText, passwordText;
    private Button addDriverButton;
    private ParseUser user;
    private UserParseDefinitions definitions = new UserParseDefinitions();
    private UserTypes roles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddriver);


        nameText = findViewById(R.id.addDriverName);
        surnameText = findViewById(R.id.addDriverSurname);
        emailText = findViewById(R.id.addDriverEmail);
        phoneText = findViewById(R.id.addDriverPhone);
        usernameText = findViewById(R.id.addDriverUsername);
        passwordText = findViewById(R.id.addDriverPassword);
        addDriverButton=findViewById(R.id.addDriverButton);

    }


    public void addDriverAccount (View view) {
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
                    Toast.makeText(getApplicationContext(),"New driver account is created!",Toast.LENGTH_LONG).show();
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
        user.put(definitions.getUserTypeKey(),roles.DRIVER.getUserType());
    }
}


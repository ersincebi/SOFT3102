package com.example.userOperations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripOperations.ListTripActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpLoginActivity extends AppCompatActivity {

    EditText usernameText, passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        usernameText = findViewById(R.id.signup_login_username);
        passwordText = findViewById(R.id.signup_login_password);

    }

    public void login(View view) {

        ParseUser.logInInBackground(usernameText.getText().toString(), passwordText.getText().toString(), new LogInCallback() {

            @Override
            public void done(ParseUser user, ParseException e) {
                if (e!= null) {
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
        } else {
                    Toast.makeText(getApplicationContext(),"Giriş Yapıldı -> "+ user.getUsername(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), ListTripActivity.class);
                    startActivity(intent);
                }
            }
        });

    }



        public void signUp(View view) {

            ParseUser user = new ParseUser();
            user.setUsername(usernameText.getText().toString());
            user.setPassword(passwordText.getText().toString());

            user.signUpInBackground(new SignUpCallback() {

                @Override
                public void done(ParseException e) {

                    if (e!= null) {
                        Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show(); //geçersiz isim şifre vs.
          } else {
                        Toast.makeText(getApplicationContext(),"Yeni Kullanıcı Oluşturuldu!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),ListTripActivity.class);
                        startActivity(intent);


                    }


                }
            });


        }
    }
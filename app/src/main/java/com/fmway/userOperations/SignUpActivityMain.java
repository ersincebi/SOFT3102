package com.fmway.userOperations;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.fmway.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivityMain extends AppCompatActivity {

    EditText nameText, surnameText, emailText , phoneText, usernameText, passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity_main);


        nameText = findViewById(R.id.user_name);
        surnameText = findViewById(R.id.user_surname);
        emailText = findViewById(R.id.user_email);
        phoneText = findViewById(R.id.user_phone);
        usernameText = findViewById(R.id.user_username);
        passwordText = findViewById(R.id.user_password);

    }


    public void signUp (View view) {

        final ParseUser user = new ParseUser();

        user.put("Name", nameText.getText().toString());
        user.put("Surname", surnameText.getText().toString());
        user.put("Email", emailText.getText().toString());
        user.put("Phone", phoneText.getText().toString());
        user.setUsername(usernameText.getText().toString());
        user.setPassword(passwordText.getText().toString());

        user.put("userType","passenger");
        user.signUpInBackground(new SignUpCallback() {


            @Override
            public void done(ParseException e) {

                if (e!= null) {
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show(); //geçersiz isim şifre vs.
                } else {
                    Toast.makeText(getApplicationContext(),"Yeni Kullanıcı Oluşturuldu!",Toast.LENGTH_LONG).show();
                    String userType=user.getString("userType");
                    if (userType.equals("admin")){

                        //intent for admin Login
                        Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
                        startActivity(intent);

                    }

                    //intent for passenger Login
                    else if (userType.equals("passenger")){
                        //intent
                        Intent intent = new Intent(getApplicationContext(),PassengerActivity.class);
                        startActivity(intent);

                    }


                    //intent for driver Login
                    else if (userType.equals("driver")){
                        //intent
                        Intent intent = new Intent(getApplicationContext(),DriverActivity.class);
                        startActivity(intent);
                    }


                }


            }
        });


    }

}





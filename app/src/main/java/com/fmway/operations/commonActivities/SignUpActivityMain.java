package com.fmway.operations.commonActivities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.fmway.R;
import com.fmway.models.user.UserParseDefinitions;
import com.fmway.models.user.UserTypes;
import com.fmway.operations.admin.AdminActivity;
import com.fmway.operations.driver.DriverActivity;
import com.fmway.operations.passenger.PassengerActivity;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivityMain extends AppCompatActivity {

    private EditText nameText, surnameText, emailText , phoneText, usernameText, passwordText;

    private UserParseDefinitions definitions = new UserParseDefinitions();

    private UserTypes roles;

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

        userSignUp(
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
                    Toast.makeText(getApplicationContext(),"Yeni Kullanıcı Oluşturuldu!",Toast.LENGTH_LONG).show();
                    String userType=user.getString(definitions.getUserTypeKey());
                    if (userType.equals(roles.ADMIN.getUserType())){
                        //intent for admin Login
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);
                    }

                    //intent for passenger Login
                    else if (userType.equals(roles.PASSENGER.getUserType())){
                        //intent
                        Intent intent = new Intent(getApplicationContext(), PassengerActivity.class);
                        startActivity(intent);
                    }

                    //intent for driver Login
                    else if (userType.equals(roles.DRIVER.getUserType())){
                        //intent
                        Intent intent = new Intent(getApplicationContext(), DriverActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public void userSignUp(
            ParseUser user
            ,String name
            ,String surname
            ,String email
            ,String phone
            ,String username
            ,String password
    ){
        user.put(definitions.getNameKey(), name);
        user.put(definitions.getSurnameKey(), surname);
        user.put(definitions.getEmailKey(), email);
        user.put(definitions.getPhoneKey(), phone);
        user.setUsername(username);
        user.setPassword(password);
        user.put(definitions.getUserTypeKey(), roles.PASSENGER.getUserType());
    }
}





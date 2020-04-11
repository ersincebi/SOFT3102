package com.fmway.operations.commonActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fmway.R;
import com.fmway.operations.admin.AdminActivity;
import com.fmway.operations.commonActivities.SignUpActivityMain;
import com.fmway.operations.driver.DriverActivity;
import com.fmway.operations.passenger.PassengerActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

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


                    String userID=user.getObjectId();

                    String userType=user.getString("userType");
                    if (userType.equals("admin")){

                        //intent for admin Login
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        intent.putExtra("userID",userID);
                        startActivity(intent);

                    }

                    //intent for passenger Login
                    else if (userType.equals("passenger")){
                        //intent
                        Intent intent = new Intent(getApplicationContext(), PassengerActivity.class);
                        intent.putExtra("userID",userID);
                        startActivity(intent);

                    }


                    //intent for driver Login
                    else if (userType.equals("driver")){
                        //intent
                        Intent intent = new Intent(getApplicationContext(), DriverActivity.class);
                        intent.putExtra("userID",userID);
                        startActivity(intent);
                    }

                }
            }
        });

    }


    public void signUp_orientation (View view) {

        Toast.makeText(getApplicationContext(),"Lütfen gerekli kullanıcı bilgilerini doldurun!",Toast.LENGTH_LONG).show();

        //intent for new user
        Intent intent = new Intent(getApplicationContext(), SignUpActivityMain.class);
        startActivity(intent);

    }

}






































/*
        public void signUp(View view) {

            final ParseUser user = new ParseUser();
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


        }  */

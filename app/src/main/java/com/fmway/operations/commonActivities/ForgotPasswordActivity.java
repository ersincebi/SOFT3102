package com.fmway.operations.commonActivities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.fmway.R;
import com.fmway.operations.admin.AdminActivity;
import com.fmway.operations.driver.DriverActivity;
import com.fmway.operations.passenger.PassengerActivity;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText emailText ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);


        emailText = findViewById(R.id.user_email);







    }


    public void sendNewPassword(View view) {

        Toast.makeText(getApplicationContext(),"Eğer email adresiniz sisteme kayıtlıysa, sisteme kayıtlı email adresinizi kontrol edin!!",Toast.LENGTH_LONG).show();

        //intent
        setContentView(R.layout.signup_login_activity);
      /*  Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
        startActivity(intent);*/


    };

    }







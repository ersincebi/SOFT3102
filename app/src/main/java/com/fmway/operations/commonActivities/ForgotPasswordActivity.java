package com.fmway.operations.commonActivities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.fmway.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText emailText;

    /**
     * activity constructor
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);
        emailText = findViewById(R.id.user_email);
    }

    /**
     * mail handling for warning the user
     * @param view
     */
    public void sendNewPassword(View view) {
        Toast.makeText(getApplicationContext()
                ,"If the entered e-mail is registered on the system, " +
                        "please check your e-mail!!"
                ,Toast.LENGTH_LONG).show();
        setContentView(R.layout.signup_login_activity);
    };
}







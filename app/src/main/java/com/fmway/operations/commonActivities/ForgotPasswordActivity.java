package com.fmway.operations.commonActivities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.fmway.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText emailText;
    private Button newpass;

    /**
     * activity constructor
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.forgot_password_activity);

        newpass = findViewById(R.id.newpassbutton);
        emailText = findViewById(R.id.forgotpassmail);
        System.out.println(emailText.getText().toString());
        final String email = emailText.getText().toString();
        newpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("" + email + "");
                ParseUser.requestPasswordResetInBackground(emailText.getText().toString(), new RequestPasswordResetCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), "Check your " + emailText.getText().toString() + " to reset your password", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), emailText.getText().toString() + " is not valid email", Toast.LENGTH_LONG).show();
                        }
                        finish();
                    }
                });
            }

            ;


        });
    }
}














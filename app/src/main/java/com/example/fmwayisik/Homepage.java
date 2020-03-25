package com.example.fmwayisik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity {
    private Button profilebutton;
    private Button paymentButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profilebutton = (Button) findViewById(R.id.profileButton);
        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfilePage();
            }
        });
        paymentButton = (Button) findViewById(R.id.payButton);
        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentPage();
            }
        });

    }

    public void openProfilePage(){
        Intent intent = new Intent(this,Profile.class);
        startActivity(intent);
    }
    public void openPaymentPage(){
        Intent intent = new Intent(this,Pay.class);
        startActivity(intent);
    }
}

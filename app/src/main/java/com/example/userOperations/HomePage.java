package com.example.userOperations;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tripOperations.AddTripActivity;
import com.example.tripOperations.ListTripActivity;
import com.example.tripOperations.passengerFeedback;
import com.example.tripOperations.driverFeedback;

public class HomePage extends AppCompatActivity {

    /* Buttons at homepage that opens related pages.
     */
    Button profile;
    Button payment;
    Button triplist;
    Button create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        profile = findViewById(R.id.profilePage);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfilePage();
            }
        });
        payment = findViewById(R.id.paymentPage);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymenPage();
            }
        });
        triplist = findViewById(R.id.tripList);
        triplist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTripList();
            }
        });
        create = findViewById(R.id.createTrip);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddTripPage();
            }
        });
    }
    /*
    Methods that create intents that make buttons open to related pages.
     */
    public void openProfilePage(){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void openPaymenPage(){
        Intent intent = new Intent(this, Payment.class);
        startActivity(intent);
    }

    public void openTripList(){
        Intent intent = new Intent(this, ListTripActivity.class);
        startActivity(intent);
    }

    public void openAddTripPage(){
        Intent intent = new Intent(this, AddTripActivity.class);
        startActivity(intent);
    }

    public void openPassengerFeedback(){
        Intent intent = new Intent(this, passengerFeedback.class);
        intent.putExtra("personName", "Ersin");
        intent.putExtra("personId",123);
        startActivity(intent);
    }


    public void openDriverFeedback(){
        Intent intent = new Intent(this, driverFeedback.class);
        intent.putExtra("personName", "Ersin");
        intent.putExtra("personId",123);
        startActivity(intent);
    }
}

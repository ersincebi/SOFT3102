package com.fmway.userOperations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fmway.tripOperations.AddTripActivity;
import com.fmway.tripOperations.ListTripActivityAdmin;
import com.fmway.tripOperations.passengerFeedback;
import com.fmway.tripOperations.driverFeedback;
import com.fmway.R;
import com.fmway.tripOperations.passengerFeedback;
import com.fmway.tripOperations.tripChat;

public class HomePage extends AppCompatActivity {

    /* Buttons at homepage that opens related pages.
     */
    Button profile;
    Button payment;
    Button triplist;
    Button create;
    Button passengerFeedback;
    Button driverFeedback;
    Button tripChat;
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
        driverFeedback = findViewById(R.id.driverFeedback);
        driverFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDriverFeedback();;
            }
        });
        passengerFeedback = findViewById(R.id.passengerFeedback);
        passengerFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPassengerFeedback();
            }
        });
        tripChat = findViewById(R.id.tripChat);
        tripChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChat();
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
        Intent intent = new Intent(this, ListTripActivityAdmin.class);
        startActivity(intent);
    }

    public void openAddTripPage(){
        Intent intent = new Intent(this, AddTripActivity.class);
        startActivity(intent);
    }

    public void openPassengerFeedback(){
        Intent intent = new Intent(this, passengerFeedback.class);
        intent.putExtra("personName", "Ersin");
        intent.putExtra("personId","123");
        startActivity(intent);
    }

    public void openChat(){
        Intent intent = new Intent(this, tripChat.class);
        intent.putExtra("tripId", "123");
        intent.putExtra("personId","123");
        startActivity(intent);
    }

    public void openDriverFeedback(){
        Intent intent = new Intent(this, driverFeedback.class);
        intent.putExtra("personName", "Ersin");
        intent.putExtra("personId","123");
        startActivity(intent);
    }
}

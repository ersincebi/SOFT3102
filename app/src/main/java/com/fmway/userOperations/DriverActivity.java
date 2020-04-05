package com.fmway.userOperations;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.tripOperations.AddTripActivity;
import com.fmway.tripOperations.ListTripActivityPassenger;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import androidx.appcompat.app.AppCompatActivity;

public class DriverActivity extends AppCompatActivity {

    Button profileButton;
    Button listTripsButton;
    Button listMyTripsButton;
    Button balanceButton;
    Button addTripButton;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.Logout) {
            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null) {
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), SignUpLoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        profileButton=findViewById(R.id.profileButtonDriver);
        listTripsButton=findViewById(R.id.listTripButtonDriver);
        listMyTripsButton=findViewById(R.id.listMyTripsButton);
        addTripButton=findViewById(R.id.addTripButtonDriver);
        balanceButton=findViewById(R.id.balanceButtonDriver);
    }

    public void listTrips(View view){
        Intent intent = new Intent(getApplicationContext(), ListTripActivityPassenger.class);
        startActivity(intent);
    }

    public void balance(View view){
        Intent intent = new Intent(getApplicationContext(),Payment.class);
        startActivity(intent);
    }
    public void addTrip(View view){
        Intent intent = new Intent(getApplicationContext(), AddTripActivity.class);
        startActivity(intent);
    }
}


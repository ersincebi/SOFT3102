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
import com.fmway.tripOperations.ListTripActivityAdmin;
import com.fmway.tripOperations.ListTripActivityPassenger;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import androidx.appcompat.app.AppCompatActivity;

public class PassengerActivity extends AppCompatActivity {

    Button profileButton;
    Button listTripsButton;
    Button applyDriverButton;
    Button balanceButton;

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
        setContentView(R.layout.activity_passenger);

        profileButton=findViewById(R.id.profileButton);
        listTripsButton=findViewById(R.id.listTripButton);
        applyDriverButton=findViewById(R.id.applyDriverButton);
        balanceButton=findViewById(R.id.balanceButton);
    }

    public void listTrips(View view){
        Intent intent = new Intent(getApplicationContext(), ListTripActivityPassenger.class);
        startActivity(intent);
    }

    public void balance(View view){
        Intent intent = new Intent(getApplicationContext(),Payment.class);
        startActivity(intent);
    }
}

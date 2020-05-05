package com.fmway.operations.driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.operations.commonActivities.Profile;
import com.fmway.operations.passenger.ListTripActivityPassenger;
import com.fmway.operations.commonActivities.Payment;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import androidx.appcompat.app.AppCompatActivity;

public class DriverActivity extends AppCompatActivity {

    private Button profileButton;
    private Button listTripsButton;
    private Button listMyTripsButton;
    private Button addTripButton;

    private String userID;

    /**
     * menu option creator handler
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * logout button activity
     * @param item
     * @return
     */
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

    /**
     * driver activity page
     * activity constructor
     * also takes userID by intent
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        profileButton=findViewById(R.id.profileButtonDriver);
        listTripsButton=findViewById(R.id.listTripButtonDriver);
        listMyTripsButton=findViewById(R.id.listMyTripsButton);
        addTripButton=findViewById(R.id.addTripButtonDriver);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
            userID = (String) b.get("userID");
    }

    /**
     * button action for opening list trip activity
     * @param view
     */
    public void listTrips(View view){
        Intent intent = new Intent(getApplicationContext(), ListTripActivityPassenger.class);
        intent.putExtra("userID",userID);
        startActivity(intent);
    }

    /**
     * button action for opening add trip activity
     * @param view
     */
    public void addTrip(View view){
        Intent intent = new Intent(getApplicationContext(), AddTripActivity.class);
        intent.putExtra("userID",userID);
        startActivity(intent);
    }

    /**
     * button action for opening list created by owned trip activity
     * @param view
     */
    public void listMyTrips(View view){
        Intent intent= new Intent(getApplicationContext(), ListMyTripsDriverActivity.class);
        intent.putExtra("userID",userID);
        startActivity(intent);
    }

    /**
     * button action for opening driver profile page
     * @param view
     */
    public void openProfile(View view){
        Intent intent = new Intent(getApplicationContext(), Profile.class);
        intent.putExtra("userID",userID);
        startActivity(intent);
    }
}


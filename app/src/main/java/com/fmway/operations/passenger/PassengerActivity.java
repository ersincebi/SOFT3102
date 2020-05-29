package com.fmway.operations.passenger;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.operations.commonActivities.Profile;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


import java.util.List;


import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

public class PassengerActivity extends AppCompatActivity {
    private Button profileButton;
    private Button uploadYourIssueButton;
    private Button listTripsButton;
    private Button applyDriverButton;
    private String userID;

    private String objectID;
    private String isApproved;

    private Button beDriverButton;

    Context context = this;


    /**
     * menu option creator handler
     *
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
     *
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
                        Toast.makeText(getApplicationContext()
                                , e.getLocalizedMessage()
                                , Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext()
                                , SignUpLoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * passenger add trip page
     * activity constructor
     * also takes userID by intent
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);

        profileButton = findViewById(R.id.profileButton);
        listTripsButton = findViewById(R.id.listTripButton);
        applyDriverButton = findViewById(R.id.applyDriverButton);
        uploadYourIssueButton = findViewById(R.id.upload_your_issue);
        beDriverButton = findViewById(R.id.beDriverButton);


        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null)
            userID = (String) b.get("userID");


        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Licences");
        query.whereEqualTo("userID", userID);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext()
                            , e.getLocalizedMessage()
                            , Toast.LENGTH_LONG).show();

                } else {
                    if (objects.size() > 0) {
                        for (ParseObject object : objects) {


                            objectID = (object.getObjectId());
                            isApproved = (object.getString("isApproved"));

                        }
                    }
                }
            }
        });

        if(objectID!=null) {
            query.getInBackground(objectID, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (isApproved.equals("true")) {
                        beDriverButton.setVisibility(View.VISIBLE);
                    }
                }
            });
        }



        beDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser user=ParseUser.getCurrentUser();
                user.put("userType", "driver");
                user.saveInBackground();

                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Toast.makeText(getApplicationContext()
                                    , e.getLocalizedMessage()
                                    , Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(getApplicationContext()
                                    , SignUpLoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }

    /**
     * button action for opening list trip activity
     *
     * @param view
     */
    public void listTrips(View view) {
        Intent intent = new Intent(getApplicationContext(), ListTripActivityPassenger.class);
        startActivity(intent);
    }

    /**
     * button action for opening support page
     *
     * @param view
     */
    public void uploadYourIssue(View view) {
        Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
        startActivity(intent);
    }

    /**
     * button action for opening driver application page
     *
     * @param view
     */
    public void applyDriver(View view) {
        Intent intent = new Intent(getApplicationContext(), UploadLicenceActivity.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
    }

    /**
     * button action for opening driver profile page
     *
     * @param view
     */
    public void openProfile(View view) {
        Intent intent = new Intent(getApplicationContext(), Profile.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
    }





    public boolean onKeyDown (int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Are you sure you want to end the session?")
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }

                    }).setNegativeButton ("No",new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }

            }).create().show();
        }
        return super.onKeyDown(keyCode, event);
    }




}

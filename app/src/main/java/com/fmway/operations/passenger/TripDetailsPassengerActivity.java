package com.fmway.operations.passenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.trip.Trip;
import com.fmway.models.trip.TripParseDefinitions;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.fmway.operations.driver.DriverActivity;
import com.parse.GetCallback;
import com.parse.LogOutCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class TripDetailsPassengerActivity extends AppCompatActivity {
    private Button joinButton;
    private TextView dateText;
    private TextView timeText;
    private TextView from;
    private TextView destination;
    private TextView capacity;
    private TextView price;

    private Context context=this;

    private String savedExtra;

    private TripParseDefinitions definitions = new TripParseDefinitions();
    Double userBalance;
    String tripId;
    TextView currentBalance;
    String userId;
    int tripPrice;
    boolean checkUser;
    boolean canBejoined;


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
     * passenger add trip page
     * activity constructor
     * also takes objectID by intent
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripdetailspassenger);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
            savedExtra =(String) b.get("objectID");

        dateText = findViewById(R.id.detailsTripDate);
        timeText = findViewById(R.id.detailsTripTime);
        from = findViewById(R.id.detailsTripFrom);
        destination = findViewById(R.id.detailsTripDestination);
        capacity = findViewById(R.id.detailsTripCapacity);
        price = findViewById(R.id.detailsTripPrice);
        joinButton=findViewById(R.id.joinTripButton);
        userId = ParseUser.getCurrentUser().getObjectId();
        download();

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.getInBackground(savedExtra, new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser object, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    userBalance = object.getDouble("balance");

                }

            }
        });

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinTrip();
            }
        });
    }

    /**
     * searches for the trip details on database
     * and fills the fields
     */
    public void download(){
        ParseQuery<ParseObject> query= ParseQuery.getQuery(definitions.getClassName());
        query.getInBackground(savedExtra, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                } else {
                    dateText.setText(object.getString(definitions.getDateKey()));
                    timeText.setText(object.getString(definitions.getTimeKey()));
                    from.setText(object.getString(definitions.getFromKey()));
                    destination.setText(object.getString(definitions.getDestinationKey()));
                    capacity.setText(String.valueOf(object.getInt(definitions.getCapacityKey())));
                    price.setText(String.valueOf(object.getInt(definitions.getPriceKey())));

                }
            }
        });
    }

    public void joinTrip(){

        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Trip");
        query1.getInBackground(savedExtra, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                tripPrice = (int) object.get("Price");
            }
        });
        ParseQuery<ParseUser> userquery = ParseUser.getQuery();
        userquery.getInBackground(userId, new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser user, ParseException e) {
                int balance = user.getInt("balance");
                if(balance < tripPrice){
                    Toast.makeText(getApplicationContext(), "Not Enough Balance", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    System.out.println(userCheck());
                    if(userCheck() != true){
                        System.out.println(userCheck());
                        Toast.makeText(getApplicationContext(), "You already joined this trip", Toast.LENGTH_SHORT).show();
                        canBejoined = false;
                        finish();
                    }
                    if(canBejoined){
                        int finalbal = balance- tripPrice;
                        user.put("balance",finalbal);
                        user.addUnique("Trip",savedExtra);
                        user.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {

                            }
                        });
                        Toast.makeText(getApplicationContext(),"Joined Trip",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
        if(canBejoined){
            query1.getInBackground(savedExtra, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    object.put("Passengers", ParseUser.getCurrentUser().getObjectId());
                    object.addUnique("Passenger",ParseUser.getCurrentUser().getObjectId());
                    object.increment("Capacity",-1);
                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {

                        }
                    });

                    finish();
                }
            });
        }






    }

    public boolean userCheck(){
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Trip");
        query.getInBackground(savedExtra, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                JSONArray a = object.getJSONArray("Passenger");
                try {
                    for(int i = 0; i< a.length();i++){
                        String uid = (String) a.get(i);
                        System.out.println(uid);
                        if(ParseUser.getCurrentUser().getObjectId() == uid){
                            checkUser = false;
                        }

                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }

            }
        });


        return checkUser;
    }

}

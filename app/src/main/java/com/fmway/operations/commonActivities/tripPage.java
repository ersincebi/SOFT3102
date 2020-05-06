package com.fmway.operations.commonActivities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.trip.TripParseDefinitions;
import com.fmway.models.user.UserParseDefinitions;
import com.fmway.operations.passenger.driverFeedback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONArray;

import java.util.List;

public class tripPage extends AppCompatActivity {
    private TextView from;
    private TextView destination;
    private TextView fullDate;

    private JSONArray selectedItem = null;

    private Context context=this;

    private String tripID;
    private String userID;
    private int tripPrice;

    private TripParseDefinitions definitions = new TripParseDefinitions();
    private UserParseDefinitions userParseDefinitions = new UserParseDefinitions();
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
     * @return returns the selected option item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.Logout) {
            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null) {
                        Toast.makeText(getApplicationContext()
                                ,e.getLocalizedMessage()
                                ,Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext()
                                ,SignUpLoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * admin trip detail page
     * activity constructor
     * also makes button handling for
     * opening trip edit activity
     * and trip delete
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_page);

        from = findViewById(R.id.from);
        destination = findViewById(R.id.destination);
        fullDate = findViewById(R.id.fullDate);

        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null) {
            tripID = (String) bundle.get("tripID");
            userID = (String) bundle.get("userID");
        }

        getTrip();
    }

    /**
     * Fills trips info into page
     */
    public void getTrip(){
        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery(definitions.getClassName());

        parseQuery.whereEqualTo(definitions.getObjectIdKey(), tripID);

        parseQuery.findInBackground(
                new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e!=null){
                            Toast.makeText(getApplicationContext()
                                    ,e.getLocalizedMessage()
                                    ,Toast.LENGTH_LONG).show();
                        }else{
                            if(objects.size()>0){
                                from.setText(
                                        objects.get(0).getString(definitions.getFromKey()) + " "
                                );
                                destination.setText(
                                        objects.get(0).getString(definitions.getDestinationKey()) + " "
                                );
                                fullDate.setText(
                                        objects.get(0).getString(definitions.getDateKey()) + " " +
                                                objects.get(0).getString(definitions.getTimeKey())
                                );
                                tripPrice = objects.get(0).getInt(definitions.getPriceKey());
                            }
                        }
                    }
                }
        );
    }

    /**
     * allow people to leave trip
     * if the trips date haven't currently started
     * @param view
     */
    public void leaveTrip(View view){
        deleteFromUserArray();
        deleteFromTripArray();
        Toast.makeText(getApplicationContext(), "You leaved the trip", Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * searches user by userID
     * than deletes the tripID from Trip array
     */
    public void deleteFromUserArray(){
        setJsonArrayToNull();

        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();

        parseQuery.getInBackground(userID, new GetCallback<ParseUser>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void done(ParseUser object, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext()
                            ,e.getLocalizedMessage()
                            ,Toast.LENGTH_LONG).show();
                }else{
                    selectedItem = object.getJSONArray(userParseDefinitions.getTripKey());
                    System.out.println(selectedItem);
                    try {
                        selectedItem.remove(findIndexOf(tripID));
                    } catch (Exception ex) {}

                    System.out.println(selectedItem);
                    int currentBalance = (int) object.get(userParseDefinitions.getBalanceKey());
                    int newBalance = currentBalance + tripPrice;
                    object.put(userParseDefinitions.getBalanceKey(), newBalance);
                    object.put(userParseDefinitions.getTripKey(), selectedItem);
                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {

                        }
                    });
                }
            }
        });
    }


    /**
     * searches trip by tripID
     * than deletes the userID from Passenger array
     */
    public void deleteFromTripArray() {
        setJsonArrayToNull();

        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery(definitions.getClassName());

        parseQuery.getInBackground(tripID, new GetCallback<ParseObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext()
                            ,e.getLocalizedMessage()
                            ,Toast.LENGTH_LONG).show();
                }else{
                    selectedItem = object.getJSONArray(definitions.getPassengerKey());
                    System.out.println(selectedItem);
                    try {
                        selectedItem.remove(findIndexOf(userID));
                    } catch (Exception ex) {}

                    System.out.println(selectedItem);
                    object.increment(definitions.getCapacityKey(),+1);
                    object.put(definitions.getPassengerKey(), selectedItem);
                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {

                        }
                    });
                }
            }
        });
    }

    /**
     * takes the search key
     * returns the index of item
     * @param item
     * @return
     * @throws Exception
     */
    public int findIndexOf(String item) throws Exception{
        for(int i=0;i<selectedItem.length();i++)
            if(selectedItem.get(i).equals(item))
                return i;
        return 0;
    }

    public void setJsonArrayToNull(){
        selectedItem = null;
    }

    /**
     * redirects to chat page of trip
     * @param view
     */
    public void sendMessage(View view){
        Intent myIntent= new Intent(tripPage.this
                                    ,tripChat.class);
        myIntent.putExtra("tripID", tripID);
        myIntent.putExtra("userID", userID);
        startActivity(myIntent);
    }

    /**
     * redirects to chat page of trip
     * @param view
     */
    public void rateTheTrip(View view){
        Intent myIntent= new Intent(tripPage.this
                                    ,driverFeedback.class);
        myIntent.putExtra("tripID", tripID);
        myIntent.putExtra("userID", userID);
        startActivity(myIntent);
    }
}

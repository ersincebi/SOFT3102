package com.fmway.operations.commonActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.trip.TripParseDefinitions;
import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class tripPage extends AppCompatActivity {
    private TextView from;
    private TextView destination;
    private TextView fullDate;

    private Context context=this;

    private String tripID;
    private String userID;

    private TripParseDefinitions definitions = new TripParseDefinitions();

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
        ParseQuery<ParseUser> parseQuery = ParseQuery.getQuery(definitions.getClassName());

        parseQuery.whereEqualTo(definitions.getObjectIdKey(), tripID);

        parseQuery.findInBackground(
                new FindCallback<ParseUser>() {
                    @Override
                    public void done(List<ParseUser> objects, ParseException e) {
                        if(e!=null){
                            Toast.makeText(getApplicationContext()
                                    ,e.getLocalizedMessage()
                                    ,Toast.LENGTH_LONG).show();
                        }else{
                            if(objects.size()>0){
                                from.setText(
                                        objects.get(0).get(definitions.getFromKey()) + ""
                                );
                                destination.setText(
                                        objects.get(0).get(definitions.getDestinationKey()) + ""
                                );
                                fullDate.setText(
                                        objects.get(0).get(definitions.getDateKey()) + " " +
                                                objects.get(0).get(definitions.getTimeKey())
                                );
                            }
                        }
                    }
                }
        );
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
}

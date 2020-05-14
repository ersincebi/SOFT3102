package com.fmway.operations.commonActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.trip.PostActivity;
import com.fmway.models.trip.Trip;
import com.fmway.models.trip.TripParseDefinitions;
import com.fmway.models.user.UserParseDefinitions;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class listMyTrips extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Trip> trip;
    private JSONArray tripList;
    private String tripID=null;
    private String userID;
    private PostActivity postActivity;

    private UserParseDefinitions definitions = new UserParseDefinitions();
    private TripParseDefinitions tripParseDefinitions = new TripParseDefinitions();

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    /**
     * persons trip listing page
     * activity constructor
     * also takes userID by intent
     * and makes button handling for
     * opening required trip
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listmytrips_activity);


        ParseUser user = new ParseUser();
        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null)
            userID = (String) bundle.get("userID");

        listView = findViewById(R.id.listTripsList);

        trip= new ArrayList<>();

        postActivity = new PostActivity(trip,this);

        try {
            getUserTripList();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        listView.setAdapter(postActivity);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tripID = ((TextView) view.findViewById(R.id.customView_objectId)).getText().toString();
                Intent myIntent= new Intent(listMyTrips.this
                                            ,tripPage.class);
                myIntent.putExtra("tripID", tripID);
                myIntent.putExtra("userID",userID);
                startActivity(myIntent);
            }
        });
    }

    /**
     * searches for the trip details on database
     * and fills the fields
     */
    public void getUserTripList() throws java.text.ParseException {
        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();

        parseQuery.whereEqualTo(definitions.getObjectIdKey(), userID);

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
                            tripList = objects.get(0).getJSONArray(definitions.getTripKey());
                            try {
                                for(int i = 0; i< tripList.length();i++)
                                    listUserLists((String) tripList.get(i));
                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        );
    }

    /**
     * takes each trip id that user joined and add int list
     * @param tripId
     */
    public void listUserLists(String tripId){
        ParseQuery<ParseObject> parseQuery= ParseQuery.getQuery(tripParseDefinitions.getClassName());
        parseQuery.getInBackground(tripId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                } else {
                    trip.add(
                        new Trip(
                            object.getObjectId()
                            ,object.getString(tripParseDefinitions.getDateKey())
                            ,object.getString(tripParseDefinitions.getTimeKey())
                            ,object.getString(tripParseDefinitions.getFromKey())
                            ,object.getString(tripParseDefinitions.getDestinationKey())
                            ,object.getString(tripParseDefinitions.getCapacityKey())
                            ,object.getString(tripParseDefinitions.getPriceKey()
                        )
                    ));
                    postActivity.notifyDataSetChanged();
                }
            }
        });
    }
}

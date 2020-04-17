package com.fmway.operations.passenger;

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
import com.fmway.models.trip.Trip;
import com.fmway.models.trip.TripParseDefinitions;
import com.fmway.operations.commonActivities.PostActivity;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ListTripActivityPassenger extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Trip> trip;
    private String selected=null;

    private TripParseDefinitions definitions = new TripParseDefinitions();

    private PostActivity postActivity ;

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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listtrips_activity);

        ParseUser user = new ParseUser();

        listView = findViewById(R.id.listTripsList);

        trip= new ArrayList<>();

        postActivity= new PostActivity(trip,this);

        download();
        listView.setAdapter(postActivity);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selected = ((TextView) view.findViewById(R.id.customView_objectId)).getText().toString();

                Intent myIntent= new Intent(ListTripActivityPassenger.this, TripDetailsPassengerActivity.class);
                myIntent.putExtra("objectID", selected);
                startActivity(myIntent);
            }
        });
    }

    public void download(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(definitions.getClassName());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }else{
                    if(objects.size()>0){
                        for(ParseObject object: objects){
                            trip.add(
                                    new Trip(
                                            object.getObjectId()
                                            ,object.getString(definitions.getDateKey())
                                            ,object.getString(definitions.getTimeKey())
                                            ,object.getString(definitions.getFromKey())
                                            ,object.getString(definitions.getDestinationKey())
                                            ,String.valueOf(object.getInt(definitions.getCapacityKey()))
                                            ,String.valueOf(object.getInt(definitions.getPriceKey()))
                                    )
                            );
                            postActivity.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

}

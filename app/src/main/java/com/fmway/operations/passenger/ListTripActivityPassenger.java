package com.fmway.operations.passenger;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.trip.Trip;
import com.fmway.models.trip.TripParseDefinitions;
import com.fmway.models.trip.PostActivity;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class ListTripActivityPassenger extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Trip> trip;
    private String selected=null;
    private Spinner filterSpinner;
    private Spinner filterSpinner2;
    private Button filterButton;
    private String filterWord;
    private String filterWord2;
    private Button showAllButton;

    private TripParseDefinitions definitions = new TripParseDefinitions();

    private PostActivity postActivity;

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    /**
     * passenger trip listing page
     * activity constructor
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listtrips_activity);

        ParseUser user = new ParseUser();

        listView = findViewById(R.id.listTripsList);
        filterSpinner=findViewById(R.id.filterSpinner);
        filterSpinner2=findViewById(R.id.filterSpinner2);
        filterButton=findViewById(R.id.filterButton);
        showAllButton=findViewById(R.id.shotAllButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterWord= filterSpinner.getSelectedItem().toString();
                filterWord2=filterSpinner2.getSelectedItem().toString();
                try {
                     trip.clear();
                     postActivity.notifyDataSetChanged();
                    download();
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               filterWord=null;
               filterWord2=null;
                try {
                    trip.clear();
                    postActivity.notifyDataSetChanged();
                    download();
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("County");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ArrayList<String> countyList = new ArrayList<>();
                    for(ParseObject object : list) {
                        countyList.add(object.getString("destName"));
                    }
                    ArrayAdapter adapter = new ArrayAdapter(
                            getApplicationContext(),android.R.layout.simple_list_item_1 ,countyList);
                    filterSpinner.setAdapter(adapter);
                    filterSpinner2.setAdapter(adapter);
                } else {

                }
            }
        });
        trip= new ArrayList<>();

        postActivity= new PostActivity(trip,this);

        try {
            download();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        listView.setAdapter(postActivity);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selected = ((TextView) view.findViewById(R.id.customView_objectId)).getText().toString();

                Intent myIntent= new Intent(ListTripActivityPassenger.this
                                            ,TripDetailsPassengerActivity.class);
                myIntent.putExtra("objectID", selected);
                startActivity(myIntent);
            }
        });
    }

    /**
     * searches for the trip details on database
     * and fills the fields
     */
    public void download() throws java.text.ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(definitions.getClassName());

        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final String currentDate = new SimpleDateFormat("dd/MM/yyyy"
                                                        ,Locale.getDefault()).format(new Date());
        final Date currenDate = formatter.parse(currentDate);

            if(filterWord!=null  || filterWord2!=null) {
                query.whereEqualTo("Destination", filterWord);
                query.whereEqualTo("From",filterWord2);

            }
            query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext()
                                    ,e.getLocalizedMessage()
                                    ,Toast.LENGTH_LONG).show();
                }else{
                    if(objects.size()>0){
                        for(ParseObject object: objects){
                            String objDate=object.getString((definitions.getDateKey()));
                            int capac=object.getInt("Capacity");
                            try {
                                Date date1 = formatter.parse(objDate);
                                if (date1.compareTo(currenDate)<0)
                                    continue;
                                else if(capac<1)
                                    continue;
                            }
                                catch (java.text.ParseException ex) {
                                ex.printStackTrace();
                            }
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

package com.fmway.operations.admin;

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

import com.fmway.models.trip.Trip;
import com.fmway.models.trip.TripParseDefinitions;
import com.fmway.models.trip.PostActivity;
import com.fmway.R;
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

public class ListTripActivityAdmin extends AppCompatActivity {
    private String userID;
    private ListView listView;
    private ArrayList<Trip> trip;
    private String selected=null;

    private PostActivity postActivity;

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    /**
     * admin trip listing page
     * activity constructor
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listtrip_activity_admin);


        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
            userID =(String) b.get("userID");

        listView = findViewById(R.id.listTripsList);

        trip = new ArrayList<>();

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

                Intent myIntent= new Intent(ListTripActivityAdmin.this
                                            ,TripDetailsAdminActivity.class);
                myIntent.putExtra(definitions.getObjectIdKey(), selected);
                myIntent.putExtra("userID",userID);
                startActivity(myIntent);
            }
        });
    }

    /**
     * searches for the trip details on database
     * and fills the fields
     */
    public void download() throws java.text.ParseException {
        ParseQuery<ParseObject> query= ParseQuery.getQuery(definitions.getClassName());

        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final String currentDate = new SimpleDateFormat("dd/MM/yyyy"
                                                        ,Locale.getDefault()).format(new Date());
        final Date currenDate=formatter.parse(currentDate);

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

                            try {
                                Date date1 = formatter.parse(objDate);

                                if (date1.compareTo(currenDate)<0) {

                                    continue;
                                }
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

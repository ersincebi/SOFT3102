package com.fmway.operations.driver;

import android.content.Context;
import android.content.DialogInterface;
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
import com.fmway.models.trip.TripParseDefinitions;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.parse.DeleteCallback;
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

public class TripDetailsDriverActivity extends AppCompatActivity {
    private Button editTripButton;
    private Button deleteTripButton;
    private TextView dateText;
    private TextView timeText;
    private TextView from;
    private TextView destination;
    private TextView capacity;
    private TextView price;

    private Context context=this;

    private String userID;
    private String savedExtra;

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
        setContentView(R.layout.activity_tripdetailsadmin);


        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            savedExtra =(String) b.get("objectID");
            userID=(String)b.get("userID");
        }
        savedExtra= getIntent().getStringExtra("objectID");

        editTripButton = findViewById(R.id.detailsEditTripButton);
        deleteTripButton = findViewById(R.id.detailsDeleteTripButton);
        dateText = findViewById(R.id.detailsTripDate);
        timeText = findViewById(R.id.detailsTripTime);
        from = findViewById(R.id.detailsTripFrom);
        destination = findViewById(R.id.detailsTripDestination);
        capacity = findViewById(R.id.detailsTripCapacity);
        price = findViewById(R.id.detailsTripPrice);

        download();
        editTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(TripDetailsDriverActivity.this, EditTripDriverActivity.class);
                myIntent.putExtra("objectID", savedExtra);
                myIntent.putExtra("userID",userID);
                startActivity(myIntent);
            }
        });

        deleteTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(TripDetailsDriverActivity.this);
                builder.setMessage("Do you want to delete this trip?")
                        .setCancelable(false).setPositiveButton("Yes"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ParseQuery<ParseObject> query = ParseQuery.getQuery("Trip");
                                // Query parameters based on the item name
                                query.whereEqualTo("objectId", savedExtra);
                                query.findInBackground(new FindCallback<ParseObject>() {
                                    @Override
                                    public void done(final List<ParseObject> object, ParseException e) {
                                        if (e == null) {
                                            object.get(0).deleteInBackground(new DeleteCallback() {
                                                @Override
                                                public void done(ParseException e) {
                                                    if (e == null) {
                                                        Toast.makeText(getApplicationContext()
                                                                        ,"Trip deleted."
                                                                        ,Toast.LENGTH_LONG).show();
                                                        Intent intent = new Intent(getApplicationContext()
                                                                                    ,DriverActivity.class);
                                                        intent.putExtra("userID",userID);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        Toast.makeText(getApplicationContext()
                                                                ,e.getLocalizedMessage()
                                                                ,Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });
                                        } else {
                                            Toast.makeText(getApplicationContext()
                                                            ,e.getLocalizedMessage()
                                                            ,Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
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
                    Toast.makeText(getApplicationContext()
                                    ,e.getLocalizedMessage()
                                    ,Toast.LENGTH_LONG).show();
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
}





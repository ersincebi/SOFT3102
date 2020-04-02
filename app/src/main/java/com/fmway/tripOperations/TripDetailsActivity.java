package com.fmway.tripOperations;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fmway.R;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class TripDetailsActivity extends AppCompatActivity {
    Button editTripButton;
    Button deleteTripButton;
    TextView dateText;
    TextView timeText;

    TextView from;
    TextView destination;
    TextView capacity;
    TextView price;
    Context context=this;

     String savedExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripdetails);


        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
           savedExtra =(String) b.get("objectID");

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
                Intent myIntent= new Intent(TripDetailsActivity.this, EditTripActivity.class);
                myIntent.putExtra("objectID", savedExtra);
                startActivity(myIntent);
            }
        });

        deleteTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                                        Toast.makeText(getApplicationContext(), "Trip deleted.", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), ListTripActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }




        public void download(){
            ParseQuery<ParseObject> query= ParseQuery.getQuery("Trip");
            query.getInBackground(savedExtra, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e != null) {
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    } else {

                        dateText.setText(object.getString("Date"));
                        timeText.setText(object.getString("Time"));
                        from.setText(object.getString("From"));
                        destination.setText(object.getString("Destination"));
                        capacity.setText(String.valueOf(object.getInt("Capacity")));
                        price.setText(String.valueOf(object.getInt("Price")));
                    }
                }
            });



                }
            }



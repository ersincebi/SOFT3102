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
import com.fmway.models.trip.TripParseDefinitions;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.fmway.operations.driver.DriverActivity;
import com.parse.GetCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import androidx.appcompat.app.AppCompatActivity;

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

        download();

    }

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

}

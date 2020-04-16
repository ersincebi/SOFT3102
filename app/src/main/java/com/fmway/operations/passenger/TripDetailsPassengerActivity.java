package com.fmway.operations.passenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.parse.GetCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import androidx.appcompat.app.AppCompatActivity;

public class TripDetailsPassengerActivity extends AppCompatActivity {


    Button joinButton;

    TextView dateText;
    TextView timeText;

    TextView from;
    TextView destination;
    TextView capacity;
    TextView price;
    Context context=this;

    String savedExtra;

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
        {
            savedExtra =(String) b.get("objectID");

        }
        //savedExtra= getIntent().getStringExtra("objectID");


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

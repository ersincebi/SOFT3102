package com.fmway.operations.driver;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.trip.TripParseDefinitions;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.parse.GetCallback;
import com.parse.LogOutCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditTripDriverActivity extends AppCompatActivity {
    private Button dateButton;
    private Button timeButton;
    private Button editTripButton;
    private TextView dateText;
    private TextView timeText;
    private EditText from;
    private EditText destination;
    private EditText capacity;
    private EditText price;
    private Context context=this;

    private String savedExtra;
    private String userID;

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
        setContentView(R.layout.activity_edittrip);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            savedExtra =(String) b.get("objectID");
            userID=(String) b.get("userID");
        }

        dateButton=findViewById(R.id.editTripDateButton);
        timeButton=findViewById(R.id.editTripTimeButton);
        editTripButton=findViewById(R.id.editTripEditButton);
        dateText=findViewById(R.id.editTripDate);
        timeText=findViewById(R.id.editTripTime);
        from=findViewById(R.id.editTripFrom);
        destination=findViewById(R.id.editTripDestination);
        capacity=findViewById(R.id.editTripCapacity);
        price=findViewById(R.id.editTripPrice);

        download();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar= Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog dpd= new DatePickerDialog(context,new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month += 1;
                        dateText.setText(day + "/" + month + "/" + year);

                    }

                },year,month,day);
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE,"Select",dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE,"Cancel",dpd);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                if(!((Activity) EditTripDriverActivity.this).isFinishing())
                {
                    dpd.show();
                }
            }


        });


        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar callendar = Calendar.getInstance();
                int hour = callendar.get(Calendar.HOUR_OF_DAY);
                int minute = callendar.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // hourOfDay ve minute değerleri seçilen saat değerleridir.
                        // Edittextte bu değerleri gösteriyoruz.
                        timeText.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);


// dialog penceresinin button bilgilerini ayarlıyoruz ve ekranda gösteriyoruz.
                tpd.setButton(TimePickerDialog.BUTTON_POSITIVE, "Select", tpd);
                tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, "Cencel", tpd);
                tpd.show();
            }
        });
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
    public void editTrip(View view){
        if (from.getText().toString().equals(destination.getText().toString()) ){
            Toast.makeText(getApplicationContext(), "From and Destination Cannot be same", Toast.LENGTH_LONG).show();
        }
        else if(dateText.getText().toString().equals("") || timeText.getText().toString().equals("")||
                from.getText().toString().equals("")||destination.getText().toString().equals("")||
                capacity.getText().toString().equals("")|| price.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Fields cannot be empty!", Toast.LENGTH_LONG).show();
        }
        else if(Integer.parseInt(capacity.getText().toString())>6){
            Toast.makeText(getApplicationContext(), "Capacity cannot exceed 6!", Toast.LENGTH_LONG).show();
        }else if(Double.parseDouble(price.getText().toString())>20){
            Toast.makeText(getApplicationContext(), "Price per person cannot exceed 20TL !", Toast.LENGTH_LONG).show();
        }
        else {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Do you want to edit this trip?").setCancelable(false).setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ParseQuery<ParseObject> query= ParseQuery.getQuery("Trip");
                            query.getInBackground(savedExtra, new GetCallback<ParseObject>() {
                                @Override
                                public void done(ParseObject object, ParseException e) {
                                    editTripOnDb(
                                            object
                                            ,dateText.getText().toString()
                                            ,timeText.getText().toString()
                                            ,from.getText().toString()
                                            ,destination.getText().toString()
                                            ,Integer.parseInt(capacity.getText().toString())
                                            ,Integer.parseInt(price.getText().toString())
                                    );
                                    object.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if (e != null) {
                                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                                            } else {
                                                Toast.makeText(getApplicationContext(), "Trip edited.", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(getApplicationContext(), DriverActivity.class);
                                                intent.putExtra("userID",userID);
                                                startActivity(intent);
                                            }

                                        }
                                    });
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
    }

    public void editTripOnDb(
            ParseObject object
            ,String date
            ,String time
            ,String from
            ,String destination
            ,int capacity
            ,int price
    ){
        object.put(definitions.getDateKey(), date);
        object.put(definitions.getTimeKey(), time);
        object.put(definitions.getFromKey(), from);
        object.put(definitions.getDestinationKey(), destination);
        object.put(definitions.getCapacityKey(), capacity);
        object.put(definitions.getPriceKey(), price);
    }
}


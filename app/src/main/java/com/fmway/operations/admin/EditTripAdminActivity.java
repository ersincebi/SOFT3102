package com.fmway.operations.admin;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.trip.TripParseDefinitions;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditTripAdminActivity extends AppCompatActivity {
    private Button dateButton;
    private Button timeButton;
    private Button editTripButton;
    private TextView dateText;
    private TextView timeText;

    private Spinner from;
    private Spinner destination;
    private EditText capacity;
    private EditText price;
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
     * admin trip edit page
     * activity constructor
     * also makes button handling date and time
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittrip);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            savedExtra =(String) b.get(definitions.getObjectIdKey());
            userID=(String) b.get("userID");
        }

        dateButton=findViewById(R.id.editTripDateButton);
        timeButton=findViewById(R.id.editTripTimeButton);
        editTripButton=findViewById(R.id.editTripEditButton);
        dateText=findViewById(R.id.editTripDate);
        timeText=findViewById(R.id.editTripTime);
        from=findViewById(R.id.editFromSpinner);
        destination=findViewById(R.id.editDestSpinner);
        capacity=findViewById(R.id.editTripCapacity);
        price=findViewById(R.id.editTripPrice);

        download();

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
                    from.setAdapter(adapter);
                    destination.setAdapter(adapter);
                } else {

                }
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar= Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog dpd= new DatePickerDialog(context
                        ,new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month += 1;
                        dateText.setText(day + "/" + month + "/" + year);

                    }

                },year,month,day);
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE,"Select",dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE,"Cancel",dpd);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);


                if(!((Activity) EditTripAdminActivity.this).isFinishing())
                    dpd.show();
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
                tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, "Cancel", tpd);
                tpd.show();
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
                    capacity.setText(String.valueOf(object.getInt(definitions.getCapacityKey())));
                    price.setText(String.valueOf(object.getInt(definitions.getPriceKey())));
                }
            }
        });
    }

    /**
     * button handling for submitting
     * the changes on fields for trip
     * @param view
     */
    public void editTrip(View view){
        if (from.getSelectedItem().toString().equals(destination.getSelectedItem().toString()) ){
            Toast.makeText(getApplicationContext()
                            ,"From and Destination Cannot be same"
                            ,Toast.LENGTH_LONG).show();
        }else if(!from.getSelectedItem().toString().equals("Sile")
                && !destination.getSelectedItem().toString().equals("Sile")) {
            Toast.makeText(getApplicationContext()
                    , "One of the choices must be Sile."
                    , Toast.LENGTH_LONG).show();
        }else if(dateText.getText().toString().equals("")
                || timeText.getText().toString().equals("")
                || from.getSelectedItem().toString().equals("")
                || destination.getSelectedItem().toString().equals("")
                || capacity.getText().toString().equals("")
                || price.getText().toString().equals("")){
            Toast.makeText(getApplicationContext()
                            ,"Fields cannot be empty!"
                            ,Toast.LENGTH_LONG).show();
        } else if(Integer.parseInt(capacity.getText().toString())>6){
            Toast.makeText(getApplicationContext()
                            ,"Capacity cannot exceed 6!"
                            ,Toast.LENGTH_LONG).show();
        }else if(Double.parseDouble(price.getText().toString())>20){
            Toast.makeText(getApplicationContext()
                            ,"Price per person cannot exceed 20TL !"
                            ,Toast.LENGTH_LONG).show();
        }
        else {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Do you want to edit this trip?")
                    .setCancelable(false).setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ParseQuery<ParseObject> query= ParseQuery.getQuery("Trip");
                            query.getInBackground(savedExtra, new GetCallback<ParseObject>() {
                                @Override
                                public void done(ParseObject object, ParseException e) {

                                    editTripData(
                                            object
                                            ,dateText.getText().toString()
                                            ,timeText.getText().toString()
                                            ,from.getSelectedItem().toString()
                                            ,destination.getSelectedItem().toString()
                                            ,Integer.parseInt(capacity.getText().toString())
                                            ,Integer.parseInt(price.getText().toString())
                                    );

                                    object.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if (e != null) {
                                                Toast.makeText(getApplicationContext()
                                                                ,e.getLocalizedMessage()
                                                                ,Toast.LENGTH_LONG).show();

                                            } else {
                                                Toast.makeText(getApplicationContext()
                                                                ,"Trip edited."
                                                                ,Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(getApplicationContext()
                                                                            ,AdminActivity.class);
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

    /**
     * this class helps to edit trip editing on database
     * its created separately because for testing
     *
     * @param object variable is inherits the ParseObject class
     * @param date
     * @param time
     * @param from
     * @param destination
     * @param capacity
     * @param price
     */
    public void editTripData(
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


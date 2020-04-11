package com.fmway.operations.admin;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
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
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.parse.GetCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class EditTripAdminActivity extends AppCompatActivity {
    Button dateButton;
    Button timeButton;
    Button editTripButton;
    TextView dateText;
    TextView timeText;

    EditText from;
    EditText destination;
    EditText capacity;
    EditText price;
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
        setContentView(R.layout.activity_edittrip);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            savedExtra =(String) b.get("objectID");

        }
        //savedExtra= getIntent().getStringExtra("objectID");

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


                if(!((Activity) EditTripAdminActivity.this).isFinishing())
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
                tpd.setButton(TimePickerDialog.BUTTON_POSITIVE, "Seç", tpd);
                tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, "İptal", tpd);
                tpd.show();
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
    public void editTrip(View view){
        if (from.getText().toString().equals(destination.getText().toString()) ){
            Toast.makeText(getApplicationContext(), "From and Destination Cannot be same", Toast.LENGTH_LONG).show();
        }
        if(dateText.getText().toString().equals("") || timeText.getText().toString().equals("")||
                from.getText().toString().equals("")||destination.getText().toString().equals("")||
                capacity.getText().toString().equals("")|| price.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Fields cannot be empty!", Toast.LENGTH_LONG).show();
        }
        else {
            ParseQuery<ParseObject> query= ParseQuery.getQuery("Trip");
            query.getInBackground(savedExtra, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    object.put("Date", dateText.getText().toString());
                    object.put("Time", timeText.getText().toString());
                    object.put("From", from.getText().toString());
                    object.put("Destination", destination.getText().toString());
                    object.put("Capacity",Integer.parseInt(capacity.getText().toString()));
                    object.put("Price",Integer.parseInt(price.getText().toString()));
                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(getApplicationContext(), "Trip edited.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), ListTripActivityAdmin.class);
                                startActivity(intent);


                            }

                        }
                    });
                }
            });

        }
    }
}


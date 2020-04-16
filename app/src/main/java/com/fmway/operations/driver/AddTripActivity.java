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
import com.fmway.operations.admin.AdminActivity;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.fmway.operations.passenger.PassengerActivity;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AddTripActivity extends AppCompatActivity {

    Button dateButton;
    Button timeButton;
    Button addTripButton;
    TextView dateText;
    TextView timeText;

    EditText from;
    EditText destination;
    EditText capacity;
    EditText price;
    Context context=this;
    String userID;


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
        setContentView(R.layout.addtrip_activity);

        dateButton=findViewById(R.id.addTripDateButton);
        timeButton=findViewById(R.id.addTripTimeButton);
        addTripButton=findViewById(R.id.addTripButton);
        dateText=findViewById(R.id.addTripDate);
        timeText=findViewById(R.id.addTripTime);
        from=findViewById(R.id.addTripFrom);
        destination=findViewById(R.id.addTripDestination);
        capacity=findViewById(R.id.addTripCapacity);
        price=findViewById(R.id.addTripPrice);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            userID =(String) b.get("userID");

        }



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


                if(!((Activity) AddTripActivity.this).isFinishing())
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
    public void addTrip(View view){
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

            builder.setMessage("Do you want to add this trip?").setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ParseObject object = new ParseObject("Trip");
                            object.put("Date", dateText.getText().toString());
                            object.put("Time", timeText.getText().toString());
                            object.put("From", from.getText().toString());
                            object.put("Destination", destination.getText().toString());
                            object.put("Capacity",Integer.parseInt(capacity.getText().toString()));
                            object.put("Price",Integer.parseInt(price.getText().toString()));
                            object.put("TripCreatedBy",userID);
                            object.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e != null) {
                                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Trip added.", Toast.LENGTH_LONG).show();

                                        ParseUser usr = ParseUser.getCurrentUser();

                                        String usrType = usr.getString("userType");

                                        if (usrType.equals("driver")) {
                                            Intent intent = new Intent(getApplicationContext(), DriverActivity.class);
                                            intent.putExtra("userID", userID);
                                            startActivity(intent);
                                        } else if (usrType.equals("admin")) {
                                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                            intent.putExtra("userID", userID);
                                            startActivity(intent);
                                        }




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
    }
}

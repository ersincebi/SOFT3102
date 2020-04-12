package com.fmway.operations.commonActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.operations.driver.DriverActivity;
import com.fmway.operations.passenger.PassengerActivity;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class Payment extends AppCompatActivity {
    Double currentbalance;
    ListView listView;
    EditText balance;
    EditText cardNo;
    EditText holderName;
    EditText cvc;
    Button pay;
    Double payBalance;
    String userID;
    TextView currentBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        balance = findViewById(R.id.balance);
        cardNo = findViewById(R.id.cardNo);
        holderName = findViewById(R.id.holderName);
        cvc = findViewById(R.id.cvc);
        pay = findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay();
            }
        });
        currentBalance = findViewById(R.id.currentBalance);

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            userID = (String) b.get("userID");


        }

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.getInBackground(userID, new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser object, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    currentbalance = object.getDouble("balance");
                    currentBalance.setText((String.valueOf(currentbalance)));

                }

            }
        });
    }

    public void pay() {
        if (balance.getText().equals("")) {
            Toast.makeText(this, "Balance can not be empty", Toast.LENGTH_SHORT).show();
        } else {
            payBalance = Double.parseDouble(String.valueOf(balance.getText()));
        }
        if (cardNo.getText().equals("")) {
            Toast.makeText(this, "Card Number can not be empty", Toast.LENGTH_SHORT).show();
        }
        if (holderName.getText().equals("")) {
            Toast.makeText(this, "Holder name can not be empty", Toast.LENGTH_SHORT).show();
        }
        if (cvc.getText().equals("")) {
            Toast.makeText(this, "CVC can not be empty", Toast.LENGTH_SHORT).show();
        }

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.getInBackground(userID, new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser object, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    currentbalance = payBalance + currentbalance;
                    object.put("balance", currentbalance);
                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(getApplicationContext(), "Payment successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), PassengerActivity.class);
                                intent.putExtra("userID", userID);
                                startActivity(intent);


                            }
                        }
                    });


                }
            }
        });
    }
}






package com.example.userOperations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Payment extends AppCompatActivity {
    Double currentbalance;
    ListView listView;
    EditText balance;
    EditText cardNo;
    EditText holderName;
    EditText cvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        balance = findViewById(R.id.balance);
        cardNo = findViewById(R.id.cardNo);
        holderName = findViewById(R.id.holderName);
        cvc = findViewById(R.id.cvc);

    }

    public void pay(View view){
        if(balance.getText().equals(" ")){
            Toast.makeText(this, "Balance can not be empty", Toast.LENGTH_SHORT).show();
        }
        if(cardNo.getText().equals(" ")){
            Toast.makeText(this, "Card Number can not be empty", Toast.LENGTH_SHORT).show();
        }
        if(holderName.getText().equals(" ")){
            Toast.makeText(this, "Holder name can not be empty", Toast.LENGTH_SHORT).show();
        }
        if(cvc.getText().equals(" ")){
            Toast.makeText(this, "CVC can not be empty", Toast.LENGTH_SHORT).show();
        }
    }
    public int getBalance() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.getInBackground("YrTwbjwcoY", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    Double objectBalance = object.getDouble("balance");
                    System.out.println(objectBalance);
                }
            }
        });
        return 0;

    }
}


package com.fmway.tripOperations;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.fmway.userOperations.PostActivity;
import com.fmway.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ListTripActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> dateFromParse;
    ArrayList<String> timeFromParse;
    ArrayList<String> fromFromParse;
    ArrayList<String> destinationFromParse;
    ArrayList<String> capacityFromParse;
    ArrayList<String> priceFromParse;

    PostActivity postActivity ;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listtrips_activity);

        listView = findViewById(R.id.listTripsList);

        dateFromParse= new ArrayList<>();
        timeFromParse=new ArrayList<>();
        fromFromParse= new ArrayList<>();
        destinationFromParse=new ArrayList<>();
        capacityFromParse=new ArrayList<>();
        priceFromParse=new ArrayList<>();


        postActivity= new PostActivity(dateFromParse,timeFromParse,fromFromParse,destinationFromParse,capacityFromParse,priceFromParse,this);

        listView.setAdapter(postActivity);

        download();

    }
    public void download(){
        ParseQuery<ParseObject> query= ParseQuery.getQuery("Trip");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                }else{

                    if(objects.size()>0){
                        for(ParseObject object: objects){


                            dateFromParse.add(object.getString("Date"));
                            timeFromParse.add(object.getString("Time"));
                            fromFromParse.add(object.getString("From"));
                            destinationFromParse.add(object.getString("Destination"));
                            capacityFromParse.add(String.valueOf(object.getInt("Capacity")));
                            priceFromParse.add(String.valueOf(object.getInt("Price")));


                            postActivity.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

}

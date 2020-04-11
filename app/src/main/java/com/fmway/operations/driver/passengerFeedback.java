package com.fmway.operations.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fmway.R;
import com.fmway.libs.Commons;

public class passengerFeedback extends AppCompatActivity {

    // Library declarations
    private Commons commons = new Commons();

    private TextView feedbackText;
    private Button up;
    private Button down;
    private String personId;
    /**
     * before call this activity
     * this activity requires two parameter via intent
     * @param personName is passenger name
     * @param personId is passenger unique id
     * function will create a json formatted data like:
     *                 {
     *                      "passengerId": passengerId => "who is been rated"
     *                      ,"driverId": driverId => "who is rated"
     *                      ,"status": boolean => "the rating 0 is bad, 1 is good"
     *                      ,"date": date => "when is rated"
     *                 }
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.passenger_feedback);

        String driverName = getIntent().getStringExtra("personName");
        personId = getIntent().getStringExtra("personId");

        feedbackText = (TextView)findViewById(R.id.feedbackText);
        feedbackText.setText("How was your trip with " + driverName + "?");

        up = (Button)findViewById(R.id.send);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveFeedback(1);
            }
        });

        down = (Button)findViewById(R.id.send);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveFeedback(-1);
            }
        });
    }

    public void giveFeedback(int rating){
        System.out.println("\nCurrent Date: "
                + commons.currentDate("yyyy-MM-dd")
                +"\nid of driver :"
                + personId
                + "\nRating of the driver: "
                + rating);
    }
}
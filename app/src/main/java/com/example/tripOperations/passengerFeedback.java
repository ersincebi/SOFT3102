package com.example.tripOperations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.userOperations.R;

public class passengerFeedback extends AppCompatActivity {

    // Library declarations
    private libs.Commons commons = new libs.Commons();

    private TextView feedbackText;
    private Button up;
    private Button down;

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
        final int personId = getIntent().getIntExtra("personId",-1);

        feedbackText = (TextView)findViewById(R.id.feedbackText);
        feedbackText.setText("How was your trip with " + driverName + "?");

        up = (Button)findViewById(R.id.send);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: will be deleted after implementation
                 * dummy display of ration value
                 */
                if(personId!=-1)
                    System.out.println("\nCurrent Date: "
                            + commons.currentDate("yyyy-MM-dd")
                            +"\nid of driver :"
                            + personId
                            + "\nRating of the driver: "
                            + "1");
                else
                    System.out.println("an error occurred");
            }
        });

        down = (Button)findViewById(R.id.send);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * TODO: will be deleted after implementation
                 * dummy display of ration value
                 */
                if(personId!=-1)
                    System.out.println("\nCurrent Date: "
                            + commons.currentDate("yyyy-MM-dd")
                            +"\nid of driver :"
                            + personId
                            + "\nRating of the driver: "
                            + "-1");
                else
                    System.out.println("an error occurred");
            }
        });
    }
}

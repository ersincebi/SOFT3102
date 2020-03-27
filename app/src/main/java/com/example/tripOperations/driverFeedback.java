package com.example.tripOperations;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.libs.Commons;
import com.example.userOperations.R;

public class driverFeedback extends AppCompatActivity {

    // Library declarations
    private Commons commons = new Commons();

    private TextView feedbackText;
    private RatingBar feedbackStar;
    private Button feedbackSend;
    private EditText commentText;

    /**
     * before call this activity
     * this activity requires two parameter via intent
     * @param personName is drivers name
     * @param personId is drivers unique id
     * function will create a json formatted data like:
     *                 {
     *                      "driverId": driverId => "who is rated"
     *                      ,"passengerId": passengerId => "who is been rated"
     *                      ,"status": float => "the rating can be 0.0 to 5.0"
     *                      ,"comment": text => "this is the passenger thoughts about the driver"
     *                      ,"date": date => "when is rated"
     *                 }
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_feedback);

        String driverName = getIntent().getStringExtra("personName");
        final int personId = getIntent().getIntExtra("personId",-1);

        feedbackText = (TextView)findViewById(R.id.feedbackText);
        feedbackText.setText("How was your trip with " + driverName + "?");

        feedbackStar = (RatingBar)findViewById(R.id.feedbackRating);

        commentText = (EditText)findViewById(R.id.commentText);

        feedbackSend = (Button)findViewById(R.id.send);
        feedbackSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rateValue = feedbackStar.getRating();
                String comment = String.valueOf(commentText.getText());


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
                            + rateValue
                            + "\nPassenger comment to driver: "
                            + comment);
                else
                    System.out.println("an error occurred");
            }
        });
    }
}

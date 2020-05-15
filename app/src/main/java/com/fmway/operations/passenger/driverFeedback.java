package com.fmway.operations.passenger;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fmway.R;
import com.fmway.models.user.passenger.RatingParseDefinitions;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class driverFeedback extends AppCompatActivity {
    private RatingBar qualityRating;
    private EditText commentText;

    private String tripID;
    private String userID;

    private RatingParseDefinitions definitions = new RatingParseDefinitions();

    /**
     * before call this activity
     * this activity requires two parameter via intent
     * @param userID is person who rating
     * @param tripID is trips unique id
     * function will create a json formatted data like:
     *                 {
     *                      "userID": driverId => "who is rated"
     *                      ,"status": float => "the rating can be 0.0 to 5.0"
     *                      ,"comment": text => "this is the passenger thoughts about the driver"
     *                 }
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_feedback);

        tripID = getIntent().getStringExtra("tripID");
        userID = getIntent().getStringExtra("userID");

        qualityRating = (RatingBar)findViewById(R.id.quality);

        commentText = (EditText)findViewById(R.id.commentText);

    }

    /**
     *
     * @param view
     */
    public void sendFeedback(View view){
        float qualityValue = qualityRating.getRating();
        String comment = String.valueOf(commentText.getText());
        if(qualityValue==0
                || comment.equals("")){
            Toast.makeText(getApplicationContext()
                    ,"Fields cannot be empty!"
                    ,Toast.LENGTH_LONG).show();
        } else {
            ParseObject object = new ParseObject(definitions.getClassName());
            addRatingToDb(
                object
                ,qualityValue
                ,comment
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
                                        ,"You have rated the trip."
                                        ,Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        finish();

    }

    /**
     * this class helps to create rating on a trip on database
     * its created separately because for testing
     *
     * @param object variable is for the ParseObject class
     * @param userID
     * @param qualityValue
     * @param comment
     */
    public void addRatingToDb(
            ParseObject object
            ,float qualityValue
            ,String comment
    ){
        object.put(definitions.getUserIdKey(), userID);
        object.put(definitions.getTripIdKey(), tripID);
        object.put(definitions.getRatingValueKey(), qualityValue);
        object.put(definitions.getCommentKey(), comment);
    }
}

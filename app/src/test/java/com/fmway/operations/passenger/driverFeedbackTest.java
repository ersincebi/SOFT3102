package com.fmway.operations.passenger;

import com.fmway.models.user.passenger.RatingParseDefinitions;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class driverFeedbackTest {
    private Boolean actual = true;
    private driverFeedback driverFeedback;
    private RatingParseDefinitions definitions = new RatingParseDefinitions();


    public void setUp(){ driverFeedback = new driverFeedback(); }

    public void tearDown(){ driverFeedback = null; }

    @Test
    public void addTrip_test_expectTrue(){
        setUp();

        final float testRating = 0;
        final String testString = "This a test message";

        ParseObject object = new ParseObject(definitions.getClassName());

        driverFeedback.addRatingToDb(
                object
                ,testRating
                ,testString
        );

        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e!= null) {
                    actual = false;
                } else {
                    actual = true;
                }
            }
        });


        assertTrue(actual);

        tearDown();
    }
}

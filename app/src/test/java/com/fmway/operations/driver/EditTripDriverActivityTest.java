package com.fmway.operations.driver;

import com.fmway.models.trip.TripParseDefinitions;
import com.parse.ParseObject;

import static junit.framework.Assert.assertTrue;
import org.junit.Test;

public class EditTripDriverActivityTest {
    private EditTripDriverActivity editTripDriverActivity;

    private TripParseDefinitions definitions = new TripParseDefinitions();

    public void setUp(){ editTripDriverActivity = new EditTripDriverActivity(); }

    public void tearDown(){ editTripDriverActivity = null; }

    @Test
    public void addTrip_test_expectTrue(){
        setUp();

        ParseObject object = new ParseObject(definitions.getClassName());

        final Boolean[] actual = {true};

        editTripDriverActivity.editTripOnDb(
                object
                ,"12/12/2012"
                ,"21.05"
                ,"fromPlace"
                ,"destinationPlace"
                ,5
                ,23
        );

        assertTrue(actual[0]);

        tearDown();
    }
}

package com.fmway.operations.driver;

import com.fmway.models.trip.TripParseDefinitions;
import com.parse.ParseObject;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class AddTripActivityTest {
    private AddTripActivity addAdminActivity;

    private TripParseDefinitions definitions = new TripParseDefinitions();

    public void setUp(){ addAdminActivity = new AddTripActivity(); }

    public void tearDown(){ addAdminActivity = null; }

    @Test
    public void addTrip_test_expectTrue(){
        setUp();

        ParseObject object = new ParseObject(definitions.getClassName());

        final Boolean[] actual = {true};

        addAdminActivity.addTripToDb(
                object
                ,"12/12/2012"
                ,"21.05"
                ,"fromPlace"
                ,"destinationPlace"
                ,5
                ,23
                ,"1"
        );

        assertTrue(actual[0]);

        tearDown();
    }
}

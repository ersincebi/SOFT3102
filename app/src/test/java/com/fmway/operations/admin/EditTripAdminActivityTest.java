package com.fmway.operations.admin;

import com.fmway.models.trip.TripParseDefinitions;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

public class EditTripAdminActivityTest {
    private EditTripAdminActivity editTripAdminActivity;

    private TripParseDefinitions definitions = new TripParseDefinitions();

    public void setUp(){ editTripAdminActivity = new EditTripAdminActivity(); }

    public void tearDown(){ editTripAdminActivity = null; }

    @Test
    public void addTrip_test_expectTrue(){
        setUp();
        final Boolean[] actual = {true};
        ParseQuery<ParseObject> query= ParseQuery.getQuery(definitions.getClassName());
        query.getInBackground("123",new GetCallback<ParseObject>(){

            @Override
            public void done(ParseObject object, ParseException e) {
                editTripAdminActivity.editTripData(
                        object
                        ,"12/12/2012"
                        ,"21:5"
                        ,"asda"
                        ,"dfgdfg"
                        ,5
                        ,23
                );
                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            actual[0] = false;
                        } else {
                            actual[0] = true;
                        }
                    }
                });
            }
        });

        assertTrue(actual[0]);

        tearDown();
    }
}

package com.fmway.operations.admin;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class UserDetailsAdminActivityTest {
    private Boolean actual = true;

    private UserDetailsAdminActivity userDetailsAdminActivity;

    public void setUp(){ userDetailsAdminActivity = new UserDetailsAdminActivity(); }

    public void tearDown(){ userDetailsAdminActivity = null; }

    @Test
    public void addTrip_test_expectTrue(){
        setUp();

        final String testUser = "To2MCyXMM9";

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.getInBackground(testUser, new GetCallback<ParseUser>(){
            @Override
            public void done(ParseUser object, ParseException e) {
                userDetailsAdminActivity.banUser(
                        object
                );
                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            actual = false;
                        } else {
                            actual = true;
                        }
                    }
                });
            }
        });

        assertTrue(actual);

        tearDown();
    }
}

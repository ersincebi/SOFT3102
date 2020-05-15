package com.fmway.operations.admin;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class AddDriverActivityTest {
    private AddDriverActivity addDriverActivity;

    private ParseUser user = new ParseUser();

    public void setUp(){ addDriverActivity = new AddDriverActivity(); }

    public void tearDown(){ addDriverActivity = null; }

    @Test
    public void addUser_test_expectTrue(){
        setUp();
        final Boolean[] actual = {true};

        addDriverActivity.addUserToDb(
                user
                ,"driver"
                ,"driver"
                ,"driver@email.com"
                ,"12345"
                ,"username"
                ,"password"
        );
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e!= null) {
                    actual[0] = false;
                } else {
                    actual[0] = true;
                }
            }
        });

        assertTrue(actual[0]);

        tearDown();
    }
}

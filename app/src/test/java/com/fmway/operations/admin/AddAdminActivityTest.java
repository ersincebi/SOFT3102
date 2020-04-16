package com.fmway.operations.admin;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class AddAdminActivityTest {
    private AddAdminActivity addAdminActivity;

    private ParseUser user = new ParseUser();

    public void setUp(){ addAdminActivity = new AddAdminActivity(); }

    public void tearDown(){ addAdminActivity = null; }

    @Test
    public void addUser_test_expectTrue(){
        setUp();
        final Boolean[] actual = {true};

        addAdminActivity.addUserToDb(
                user
                ,"admin"
                ,"admin"
                ,"email@email.com"
                ,"123456789"
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

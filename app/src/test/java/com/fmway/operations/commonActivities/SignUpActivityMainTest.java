package com.fmway.operations.commonActivities;

import com.fmway.models.user.UserParseDefinitions;
import com.parse.ParseUser;
import com.parse.ParseException;

import static junit.framework.Assert.assertTrue;

import com.parse.SignUpCallback;

import org.junit.Test;

public class SignUpActivityMainTest {
    private SignUpActivityMain signUpActivityMain;

    private UserParseDefinitions definitions = new UserParseDefinitions();

    public void setUp(){ signUpActivityMain = new SignUpActivityMain(); }

    public void tearDown(){ signUpActivityMain = null; }

    @Test
    public void addTrip_test_expectTrue(){
        setUp();

        ParseUser user = new ParseUser();

        final Boolean[] actual = {true};

        signUpActivityMain.userSignUp(
                user
                ,"name"
                ,"surname"
                ,"email"
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

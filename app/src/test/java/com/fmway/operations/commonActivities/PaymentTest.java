package com.fmway.operations.commonActivities;

import com.fmway.operations.admin.AddDriverActivity;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class PaymentTest {
    private Payment paymentActivity;
    private ParseUser user = new ParseUser();

    public void setUp(){ paymentActivity = new Payment(); }

    public void tearDown(){ paymentActivity = null; }

    @Test
    public void addUser_test_expectTrue(){
        setUp();
        final Boolean[] actual = {true};

        // this pay method should include variables in order to control them.
       //paymentActivity.pay();
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


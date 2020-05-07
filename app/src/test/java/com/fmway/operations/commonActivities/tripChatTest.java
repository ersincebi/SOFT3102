package com.fmway.operations.commonActivities;

import com.fmway.models.chat.ChatParseDefinitions;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class tripChatTest {
    private Boolean actual = true;
    private tripChat tripChatActivity;
    private ChatParseDefinitions definitions = new ChatParseDefinitions();


    public void setUp(){ tripChatActivity = new tripChat(); }

    public void tearDown(){ tripChatActivity = null; }

    @Test
    public void addTrip_test_expectTrue(){
        setUp();

        final String testString = "This a test message";

        ParseObject object = new ParseObject(definitions.getClassName());

        tripChatActivity.saveMessageToDatabase(
                object
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

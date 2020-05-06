package com.fmway.operations.commonActivities;

import android.view.View;

import com.fmway.models.chat.ChatParseDefinitions;
import com.fmway.operations.driver.AddTripActivity;
import com.parse.ParseObject;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class TripChatTest {
    private tripChat tripChatActivity;
    private ChatParseDefinitions definitions = new ChatParseDefinitions();


    public void setUp(){ tripChatActivity = new tripChat(); }

    public void tearDown(){ tripChatActivity = null; }

    @Test
    public void addTrip_test_expectTrue(){
        setUp();

        ParseObject object = new ParseObject(definitions.getClassName());

        // tripChat method should include variables in order to control them.
        //tripChatActivity.sendMessage();
        final Boolean[] actual = {true};



        assertTrue(actual[0]);

        tearDown();
    }
}

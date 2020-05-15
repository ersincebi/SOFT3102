package com.fmway.models.chat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChatParseDefinitionsTest {

    private ChatParseDefinitions chatParseDefinitions;

    public void setUp(){ chatParseDefinitions = new ChatParseDefinitions(); }

    public void tearDown(){ chatParseDefinitions = null; }

    @Test
    public void getClassName_test_expectClassName(){
        setUp();

        final String expected = chatParseDefinitions.className;
        final String actual = chatParseDefinitions.getClassName();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getObjectIdKey_test_expectObjectIdKey(){
        setUp();

        final String expected = chatParseDefinitions.objectIdKey;
        final String actual = chatParseDefinitions.getObjectIdKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getCreatedAtKey_test_expectCreatedAtKey(){
        setUp();

        final String expected = chatParseDefinitions.createdAtKey;
        final String actual = chatParseDefinitions.getCreatedAtKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getTripIdKey_test_expectTripIdKey(){
        setUp();

        final String expected = chatParseDefinitions.tripIdKey;
        final String actual = chatParseDefinitions.getTripIdKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getPersonIdKey_test_expectPersonIdKey(){
        setUp();

        final String expected = chatParseDefinitions.personIdKey;
        final String actual = chatParseDefinitions.getPersonIdKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getMessageKey_test_expectMessageKey(){
        setUp();

        final String expected = chatParseDefinitions.messageKey;
        final String actual = chatParseDefinitions.getMessageKey();

        assertEquals(expected,actual);

        tearDown();
    }
}

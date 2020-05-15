package com.fmway.models.user;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UserParseDefinitionsTest {
    private UserParseDefinitions userParseDefinitions;

    public void setUp(){ userParseDefinitions = new UserParseDefinitions(); }

    public void tearDown(){ userParseDefinitions = null; }

    @Test
    public void getClassName_test_expectClassName(){
        setUp();

        final String expected = userParseDefinitions.className;
        final String actual = userParseDefinitions.getClassName();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getObjectIdKey_test_expectObjectIdKey(){
        setUp();

        final String expected = userParseDefinitions.objectIdKey;
        final String actual = userParseDefinitions.getObjectIdKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getCreatedAtKey_test_expectCreatedAtKey(){
        setUp();

        final String expected = userParseDefinitions.createdAtKey;
        final String actual = userParseDefinitions.getCreatedAtKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getNameKey_test_expectNameKey(){
        setUp();

        final String expected = userParseDefinitions.nameKey;
        final String actual = userParseDefinitions.getNameKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getSurnameKey_test_expectSurnameKey(){
        setUp();

        final String expected = userParseDefinitions.surnameKey;
        final String actual = userParseDefinitions.getSurnameKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getUsernameKey_test_expectUsernameKey(){
        setUp();

        final String expected = userParseDefinitions.usernameKey;
        final String actual = userParseDefinitions.getUsernameKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getEmailKey_test_expectEmailKey(){
        setUp();

        final String expected = userParseDefinitions.emailKey;
        final String actual = userParseDefinitions.getEmailKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getPhoneKey_test_expectPhoneKey(){
        setUp();

        final String expected = userParseDefinitions.phoneKey;
        final String actual = userParseDefinitions.getPhoneKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getUserTypeKey_test_expectUserTypeKey(){
        setUp();

        final String expected = userParseDefinitions.userTypeKey;
        final String actual = userParseDefinitions.getUserTypeKey();

        assertEquals(expected,actual);

        tearDown();
    }

}

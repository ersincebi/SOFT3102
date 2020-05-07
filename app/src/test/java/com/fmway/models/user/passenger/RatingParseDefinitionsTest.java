package com.fmway.models.user.passenger;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class RatingParseDefinitionsTest {
    private RatingParseDefinitions ratingParseDefinitions;

    public void setUp(){ ratingParseDefinitions = new RatingParseDefinitions(); }

    public void tearDown(){ ratingParseDefinitions = null; }

    @Test
    public void getClassName_test_expectClassName(){
        setUp();

        final String expected = ratingParseDefinitions.className;
        final String actual = ratingParseDefinitions.getClassName();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getObjectIdKey_test_expectObjectIdKey(){
        setUp();

        final String expected = ratingParseDefinitions.objectIdKey;
        final String actual = ratingParseDefinitions.getObjectIdKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getCreatedAtKey_test_expectCreatedAtKey(){
        setUp();

        final String expected = ratingParseDefinitions.createdAtKey;
        final String actual = ratingParseDefinitions.getCreatedAtKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getUserIdKey_test_expectCreatedAtKey(){
        setUp();

        final String expected = ratingParseDefinitions.userIdKey;
        final String actual = ratingParseDefinitions.getUserIdKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getTripIdKey_test_expectCreatedAtKey(){
        setUp();

        final String expected = ratingParseDefinitions.tripIdKey;
        final String actual = ratingParseDefinitions.getTripIdKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getRatingValueKey_test_expectCreatedAtKey(){
        setUp();

        final String expected = ratingParseDefinitions.ratingValueKey;
        final String actual = ratingParseDefinitions.getRatingValueKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getCommentKey_test_expectCreatedAtKey(){
        setUp();

        final String expected = ratingParseDefinitions.commentKey;
        final String actual = ratingParseDefinitions.getCommentKey();

        assertEquals(expected,actual);

        tearDown();
    }

}

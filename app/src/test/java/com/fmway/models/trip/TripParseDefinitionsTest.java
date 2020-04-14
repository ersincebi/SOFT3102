package com.fmway.models.trip;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TripParseDefinitionsTest {

    private TripParseDefinitions tripParseDefinitions;

    public void setUp(){ tripParseDefinitions = new TripParseDefinitions(); }

    public void tearDown(){ tripParseDefinitions = null; }

    @Test
    public void getClassName_test_expectClassName(){
        setUp();

        final String expected = tripParseDefinitions.className;
        final String actual = tripParseDefinitions.getClassName();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getObjectIdKey_test_expectObjectIdKey(){
        setUp();

        final String expected = tripParseDefinitions.objectIdKey;
        final String actual = tripParseDefinitions.getObjectIdKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getTripCreatedByKey_test_expectTripCreatedByKey(){
        setUp();

        final String expected = tripParseDefinitions.TripCreatedByKey;
        final String actual = tripParseDefinitions.getTripCreatedByKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getDateKey_test_expectDateKey(){
        setUp();

        final String expected = tripParseDefinitions.DateKey;
        final String actual = tripParseDefinitions.getDateKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getTimeKey_test_expectTimeKey(){
        setUp();

        final String expected = tripParseDefinitions.TimeKey;
        final String actual = tripParseDefinitions.getTimeKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getFromKey_test_expectFromKey(){
        setUp();

        final String expected = tripParseDefinitions.FromKey;
        final String actual = tripParseDefinitions.getFromKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getDestinationKey_test_expectDestinationKey(){
        setUp();

        final String expected = tripParseDefinitions.DestinationKey;
        final String actual = tripParseDefinitions.getDestinationKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getCapacityKey_test_expectCapacityKey(){
        setUp();

        final String expected = tripParseDefinitions.CapacityKey;
        final String actual = tripParseDefinitions.getCapacityKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getPriceKey_test_expectgetPriceKey(){
        setUp();

        final String expected = tripParseDefinitions.PriceKey;
        final String actual = tripParseDefinitions.getPriceKey();

        assertEquals(expected,actual);

        tearDown();
    }
}

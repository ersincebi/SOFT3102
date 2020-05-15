package com.fmway.models.trip;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TripTest {
    private Trip trip;

    private String parameter1 = "123";
    private String parameter2 = "10.04.2020";
    private String parameter3 = "17:50";
    private String parameter4 = "Istanbul";
    private String parameter5 = "Ankara";
    private String parameter6 = "3";
    private String parameter7 = "20";

    public void setUp(){ trip = new Trip(parameter1
                                        ,parameter2
                                        ,parameter3
                                        ,parameter4
                                        ,parameter5
                                        ,parameter6
                                        ,parameter7);
    }

    public void tearDown(){ trip = null; }

    @Test
    public void getObjectId_test_expectObjectId() {
        setUp();
        final String expected = parameter1;
        final String actual = trip.getObjectId();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getDate_test_expectDate() {
        setUp();
        final String expected = parameter2;
        final String actual = trip.getDate();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getTime_test_expectTime() {
        setUp();
        final String expected = parameter3;
        final String actual = trip.getTime();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getFrom_test_expectFrom() {
        setUp();
        final String expected = parameter4;
        final String actual = trip.getFrom();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getDestination_test_expectDestination() {
        setUp();
        final String expected = parameter5;
        final String actual = trip.getDestination();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getCapacity_test_expectCapacity() {
        setUp();
        final String expected = parameter6;
        final String actual = trip.getCapacity();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getPrice_test_expectPrice() {
        setUp();
        final String expected = parameter7;
        final String actual = trip.getPrice();

        assertEquals(expected,actual);

        tearDown();
    }

}

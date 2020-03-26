package com.example.libs;


import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class commonsUnitTest {

    private DateFormat dateFormat;
    private Date date;

    private Commons commons;

    public void setUp(){ commons = new Commons(); }

    public void tearDown(){ commons = null; }

    @Test
    public void currentDate_test_expectCurrentDate() {
        setUp();

        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = new Date();

        final String expected = dateFormat.format(date);
        final String actual = commons.currentDate("dd-MM-yyyy");

        assertEquals(expected,actual);

        tearDown();
    }
}

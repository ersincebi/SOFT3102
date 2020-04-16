package com.fmway.models.user;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UserTypesTest {
    private UserTypes userTypes;

    @Test
    public void getUserType_test_expectAdmin(){
        final String expected = "admin";
        final String actual = userTypes.ADMIN.getUserType();

        assertEquals(expected,actual);
    }

    @Test
    public void getUserType_test_expectPassenger(){
        final String expected = "passenger";
        final String actual = userTypes.PASSENGER.getUserType();

        assertEquals(expected,actual);
    }

    @Test
    public void getUserType_test_expectDriver(){
        final String expected = "driver";
        final String actual = userTypes.DRIVER.getUserType();

        assertEquals(expected,actual);
    }
}

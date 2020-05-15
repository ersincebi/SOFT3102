package com.fmway.models.chat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChatTest {

    private Chat chat;

    private String parameter1 = "123";
    private String parameter2 = "123";
    private String parameter3 = "Message Body";

    public void setUp(){ chat = new Chat(parameter1
                                        ,parameter2
                                        ,parameter3);
    }

    public void tearDown(){ chat = null; }

    @Test
    public void getTripId_test_expectParameter1() {
        setUp();
        final String expected = parameter1;
        final String actual = chat.getTripId();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getPersonId_test_expectParameter2() {
        setUp();
        final String expected = parameter2;
        final String actual = chat.getPersonId();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getMessage_test_expectParameter3() {
        setUp();
        final String expected = parameter3;
        final String actual = chat.getMessage();

        assertEquals(expected,actual);

        tearDown();
    }
}

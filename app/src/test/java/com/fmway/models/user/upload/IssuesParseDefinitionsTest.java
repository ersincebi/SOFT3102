package com.fmway.models.user.upload;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class IssuesParseDefinitionsTest {
    private IssuesParseDefinitions issuesParseDefinitions;

    public void setUp(){ issuesParseDefinitions = new IssuesParseDefinitions(); }

    public void tearDown(){ issuesParseDefinitions = null; }

    @Test
    public void getClassName_test_expectClassName(){
        setUp();

        final String expected = issuesParseDefinitions.className;
        final String actual = issuesParseDefinitions.getClassName();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getImageKey_test_expectClassName(){
        setUp();

        final String expected = issuesParseDefinitions.imageKey;
        final String actual = issuesParseDefinitions.getImageKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getUploadKey_test_expectClassName(){
        setUp();

        final String expected = issuesParseDefinitions.uploadKey;
        final String actual = issuesParseDefinitions.getUploadKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getFileName_test_expectClassName(){
        setUp();

        final String expected = issuesParseDefinitions.fileName;
        final String actual = issuesParseDefinitions.getFileName();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getUsernameKey_test_expectClassName(){
        setUp();

        final String expected = issuesParseDefinitions.usernameKey;
        final String actual = issuesParseDefinitions.getUsernameKey();

        assertEquals(expected,actual);

        tearDown();
    }
}

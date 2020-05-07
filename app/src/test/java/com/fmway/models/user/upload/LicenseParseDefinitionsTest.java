package com.fmway.models.user.upload;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LicenseParseDefinitionsTest {
    private LicenseParseDefinitions licenseParseDefinitions;

    public void setUp(){ licenseParseDefinitions = new LicenseParseDefinitions(); }

    public void tearDown(){ licenseParseDefinitions = null; }

    @Test
    public void getClassName_test_expectClassName(){
        setUp();

        final String expected = licenseParseDefinitions.className;
        final String actual = licenseParseDefinitions.getClassName();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getImageKey_test_expectClassName(){
        setUp();

        final String expected = licenseParseDefinitions.imageKey;
        final String actual = licenseParseDefinitions.getImageKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getUploadKey_test_expectClassName(){
        setUp();

        final String expected = licenseParseDefinitions.uploadKey;
        final String actual = licenseParseDefinitions.getUploadKey();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getFileName_test_expectClassName(){
        setUp();

        final String expected = licenseParseDefinitions.fileName;
        final String actual = licenseParseDefinitions.getFileName();

        assertEquals(expected,actual);

        tearDown();
    }

    @Test
    public void getUsernameKey_test_expectClassName(){
        setUp();

        final String expected = licenseParseDefinitions.usernameKey;
        final String actual = licenseParseDefinitions.getUsernameKey();

        assertEquals(expected,actual);

        tearDown();
    }
}

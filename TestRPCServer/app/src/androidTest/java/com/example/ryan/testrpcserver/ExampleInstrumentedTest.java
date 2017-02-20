package com.example.ryan.testrpcserver;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.ryan.testrpcserver", appContext.getPackageName());
        Uri uri = Uri.parse("content://com.example.ryan.testrpcserver.servercontentprovider/single");
        Cursor cursor = appContext.getContentResolver().query(uri, null, "name=?",
                new String[]{"test1"}, null);
    }
}

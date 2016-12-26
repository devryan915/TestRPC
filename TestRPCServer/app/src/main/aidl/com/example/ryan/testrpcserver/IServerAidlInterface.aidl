// IServerAidlInterface.aidl
package com.example.ryan.testrpcserver;

// Declare any non-default types here with import statements

interface IServerAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
     String sendMsg(String fromClient);
}

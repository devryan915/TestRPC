package com.example.ryan.testrpcserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class ServerAidlService extends Service {
    public class ServerAidlImpl extends IServerAidlInterface.Stub {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String sendMsg(String fromClient) throws RemoteException {
            return fromClient + " reply from server!";
        }
    }

    public ServerAidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ServerAidlImpl();
    }
}

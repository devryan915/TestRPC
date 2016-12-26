package com.example.ryan.testrpcclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ryan.testrpcserver.IServerAidlInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnOpenActivity).setOnClickListener(this);
        findViewById(R.id.btnQueryContentProvider).setOnClickListener(this);
        findViewById(R.id.btnSendBroad).setOnClickListener(this);
        findViewById(R.id.btnInvokeAIDL).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOpenActivity: {
                //打开另一个应用的activity
                Intent otherActivity = new Intent();
                otherActivity.setAction("com.example.ryan.testrpcserver.SERVERACTIVITY_ACTION");
                otherActivity.setData(Uri.parse("ryan://fromclient"));
                otherActivity.putExtra("value", "I am client");
                startActivity(otherActivity);
                break;
            }
            case R.id.btnQueryContentProvider: {
                Uri uri = Uri.parse("content://com.example.ryan.testrpcserver.servercontentprovider/single");
                //  通过ContentProvider查询单词，并返回Cursor对象，然后的操作就和直接从数据中获得
                //  Cursor对象后的操作是一样的了
                Cursor cursor = getContentResolver().query(uri, null, "name=?",
                        new String[]{"test1"}, null);
                String result = "未找到该单词.";
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    result = cursor.getString(cursor.getColumnIndex("value"));
                }
                Toast.makeText(MainActivity.this, "查询到的数据" + result, Toast.LENGTH_SHORT).show();
                if (cursor != null)
                    cursor.close();
                break;
            }
            case R.id.btnSendBroad: {
                Intent broadIntent = new Intent();
                broadIntent.setAction("com.example.ryan.testrpcserver.SERVERRECEIVER_ACTION");
                broadIntent.putExtra("value", "client Send");
                sendBroadcast(broadIntent);
                break;
            }
            case R.id.btnInvokeAIDL: {
                Intent intentAidl = new Intent();
                intentAidl.setAction("com.example.ryan.testrpcserver.SERVERAIDL_ACTION");
                bindService(intentAidl, new ServiceConnection() {

                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        IServerAidlInterface remoteServer = IServerAidlInterface.Stub.asInterface(service);
                        try {
                            String remoteMsg = remoteServer.sendMsg("I am client!");
                            Toast.makeText(MainActivity.this, remoteMsg, Toast.LENGTH_SHORT).show();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                }, Service.BIND_AUTO_CREATE);
                break;
            }
            default: {

            }
        }

    }
}

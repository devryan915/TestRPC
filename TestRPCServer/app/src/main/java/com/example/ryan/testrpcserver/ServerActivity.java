package com.example.ryan.testrpcserver;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class ServerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //  获得其他应用程序传递过来的数据
        if (getIntent().getData() != null) {
            //  获得Host，也就是info://后面的内容
            String host = getIntent().getData().getHost();
            Bundle bundle = getIntent().getExtras();
            //  其他的应用程序会传递过来一个value值，在该应用程序中需要获得这个值
            String value = bundle.getString("value");
            Toast.makeText(this, host + " " + value, Toast.LENGTH_SHORT).show();
        }
    }

}

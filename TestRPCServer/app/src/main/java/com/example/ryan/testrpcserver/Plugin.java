package com.example.ryan.testrpcserver;

import com.example.ryan.plugin.Common;

/**
 * Created by Ryan on 2017/2/17.
 */

public class Plugin implements Common {
    String lastMsg;

    @Override
    public String getMsg(String s) {
        lastMsg = System.currentTimeMillis() + " " + this;
        return s + System.currentTimeMillis();
    }

    @Override
    public String getLastMsg() {
        return lastMsg;
    }
}

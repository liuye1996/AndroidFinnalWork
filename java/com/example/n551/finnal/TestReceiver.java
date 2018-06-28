package com.example.n551.finnal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context,Intent intent){
        String str = intent.getExtras().getString("hello");
        selectActivity.info.setText(str);
    }
}

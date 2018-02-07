package com.flyscale.callsettings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Administrator on 2018/2/2 0002.
 * 正在设置界面
 */

public class OpenActivity extends Activity {

    private String mNumber;
    private String action;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        mNumber = getIntent().getStringExtra("number");
        action = getIntent().getStringExtra("action");

        sp = getSharedPreferences("callsettings", Context
                .MODE_PRIVATE);
        editor = sp.edit();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);

        if (action.equals("all_open")){
            //无条件转移
            intent.setData(Uri
                    .parse("tel:**21*" + mNumber + "%23"));
            editor.putString("forward_all_open", mNumber);
            editor.commit();
            startActivity(intent);

        }else if (action.equals("busy_open")){
            //遇忙转移
            intent.setData(Uri
                    .parse("tel:**67*" + mNumber + "%23"));
            editor.putString("forward_busy_open", mNumber);
            editor.commit();
            startActivity(intent);

        }else if (action.equals("no_reply_open")){
            //无应答转移
            intent.setData(Uri
                    .parse("tel:**61*" + mNumber + "%23"));
            editor.putString("forward_no_reply_open", mNumber);
            editor.commit();
            startActivity(intent);

        }else if (action.equals("reach_open")){
            //无法到达转移
            intent.setData(Uri
                    .parse("tel:**62*" + mNumber + "%23"));
            editor.putString("forward_reach_open", mNumber);
            editor.commit();
            startActivity(intent);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);
    }
}

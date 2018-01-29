package com.flyscale.callsettings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by MrBian on 2018/1/16.
 */

public class StatusActivity extends Activity {

    private static final String TAG = "StatusActivity";
    private TextView textView;
    private boolean callWaiting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_detail);

        initData();
        initView();
    }

    private void initData() {
        callWaiting = getIntent().getBooleanExtra("call_waiting_status", false);

    }

    private void initView() {
        findViewById(R.id.confirm).setVisibility(View.INVISIBLE);
        textView = (TextView)findViewById(R.id.msg_detail);
        TextView title = (TextView)findViewById(R.id.title);
        title.setText(getResources().getString(R.string.quest_status));
        textView.setText(getResources().getString(R.string.call_waiting) + "\n"
            + (callWaiting? getResources().getString(R.string.active):
                getResources().getString(R.string.inactive)));
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:

                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}

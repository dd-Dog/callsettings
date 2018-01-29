package com.flyscale.callsettings;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;


/**
 * Created by MrBian on 2018/1/16.
 */

public class SetCallWaitingActivity extends Activity {

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
        callWaiting = getIntent().getBooleanExtra("call_waiting", false);

    }

    private void initView() {
        findViewById(R.id.confirm).setVisibility(View.INVISIBLE);
        textView = (TextView)findViewById(R.id.msg_detail);
        TextView title = (TextView)findViewById(R.id.title);
        title.setText(getResources().getString(R.string.quest_status));
        textView.setText(getResources().getString(R.string.saving));


        textView.setText(getResources().getString(R.string.save_failed));
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

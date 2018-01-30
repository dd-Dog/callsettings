package com.flyscale.callsettings;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;


/**
 * Created by Administrator on 2018/1/29 0029.
 * 遇忙转移，关闭
 */

public class ForwardBusyClose extends Activity{

    private TextView tvTitel;
    private TextView tvMessage;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forward_cancel_all);
        tvTitel = (TextView)findViewById(R.id.action_title);
        tvMessage = (TextView) findViewById(R.id.forward_cancel_all);
        tvTitel.setText(getResources().getString(R.string.call_forwarding_busy));
        tvMessage.setText(getResources().getString(R.string.yes_no_call_forwarding_busy));

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //Log.e(TAG , "手机号：" + mNumber);
        //遇忙转移取消 ##67#
        switch (keyCode){
            case 82:
            case 23:
                tvMessage.setText(getResources().getString(R.string.setting));
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri
                        .parse("tel:%23%2367%23" ));
                startActivity(intent);
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }
}

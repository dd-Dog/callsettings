package com.flyscale.callsettings;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/1/29 0029.
 * 无条件转移，状态查询
 */

public class ForwardAllStatus extends Activity{

    private TextView tvTitel;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forward_cancel_all);
        tvTitel = (TextView)findViewById(R.id.action_title);
        tvMessage = (TextView) findViewById(R.id.forward_cancel_all);
        tvTitel.setText(getResources().getString(R.string.call_forwarding_all));
        tvMessage.setText(getResources().getString(R.string.under_questuing));

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri
                .parse("tel:*%2321%23" ));
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }
}

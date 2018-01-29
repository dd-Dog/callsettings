package com.flyscale.callsettings;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;


/**
 * Created by Administrator on 2018/1/29 0029.
 * 遇忙转移，关闭
 */

public class ForwardBusyClose extends Activity{

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //Log.e(TAG , "手机号：" + mNumber);
        //遇忙转移取消 ##67#
        switch (keyCode){
            case 82:
            case 23:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri
                        .parse("tel:%23%2367%23" ));
                startActivity(intent);
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}

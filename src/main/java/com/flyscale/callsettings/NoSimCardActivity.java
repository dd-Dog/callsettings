package com.flyscale.callsettings;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * Created by Administrator on 2018/1/30 0030.
 * 没有SIM卡显示的界面
 */

public class NoSimCardActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_sim_card);

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case 4:
                finish();
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}

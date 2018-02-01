package com.flyscale.callsettings.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.flyscale.callsettings.global.Constant;

/**
 * Created by MrBian on 2018/1/30.
 */

public class PhoneStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String number = getResultData();
        SharedPreferences sp = context.getSharedPreferences(Constant.SP, Context
                .MODE_PRIVATE);
        String ip = sp.getString(Constant.IP_DIAL, null);
        boolean enabled = sp.getBoolean(Constant.IP_DIAL_ENABLED, false);
        Log.e("PhoneStateReceiver", "ip=" + ip);
        if (TextUtils.isEmpty(ip) || !enabled) return;
//        setResultData(ip + number);
        setResultData(number);
    }
}

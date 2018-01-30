package com.flyscale.callsettings;

import android.content.Context;
import android.graphics.Color;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class SimCardState {
    public static String TAG = "simcardstate";

    /**
     * 判断是否包含SIM卡
     *
     * @return 状态
     */
    public static boolean ishasSimCard(Context context) {
        TelephonyManager telMgr = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        int simState = telMgr.getSimState();
        boolean result = true;
        switch (simState) {
            case TelephonyManager.SIM_STATE_ABSENT:
                result = false; // 没有SIM卡
                break;
            case TelephonyManager.SIM_STATE_UNKNOWN:
                result = false;
                break;
        }
        Log.e(TAG, result ? "有SIM卡" : "无SIM卡");
        return result;
    }

    public static void showToastString(Context context, String info){
        Toast toast = Toast.makeText( context,info,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.FILL,0,0);
        LinearLayout layout = (LinearLayout) toast.getView();
        layout.setBackgroundColor(Color.BLACK);
        TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,12);
        toast.show();
    }
}

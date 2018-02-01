package com.flyscale.callsettings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by Administrator on 2018/1/29 0029.
 * 无法到达转移，开启
 */

public class ForwardReachOpen extends Activity{

    private String TAG = "ForwardReachOpen";
    private EditText etNumber;
    private String mNumber;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forward_reach_open);

        etNumber = (EditText) findViewById(R.id.et_reach_open_number);
        sp = getSharedPreferences("callsettings", Context
                .MODE_PRIVATE);
        editor = sp.edit();

        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mNumber = etNumber.getText().toString();

            }
        });

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.e(TAG , "手机号：" + mNumber);
        switch (keyCode){
            case 82:
            case 23:
                if (mNumber != null && mNumber.length() > 0){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(Uri
                            .parse("tel:**62*" + mNumber + "%23"));
                    editor.putString("forward_reach_open", mNumber);
                    editor.commit();
                    startActivity(intent);
                    finish();
                }else {
                    SimCardState.showToastString(ForwardReachOpen.this , "号码不能为空");
                }

                return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}

package com.flyscale.callsettings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/1/29 0029.
 * 无条件转移， 开启
 */

public class ForwardAllOpen extends Activity{
    private String TAG = "ForwardAllOpen";
    private EditText etNumber;
    private String mNumber;

    private TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forward_all_open);
        etNumber = (EditText) findViewById(R.id.et_all_open_number);
        back = (TextView) findViewById(R.id.back);

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
                if (mNumber.length() > 0){
                    back.setText(getResources().getString(R.string.clear));
                }else {
                    back.setText(getResources().getString(R.string.back));
                }

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
                    Intent intent = new Intent(this , OpenActivity.class);
                    intent.putExtra("number" , mNumber);
                    intent.putExtra("action" , "all_open");
                    startActivity(intent);
                    finish();
                }else {
                    SimCardState.showToastString(ForwardAllOpen.this , "号码不能为空");
                }

                return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}

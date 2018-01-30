package com.flyscale.callsettings;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import global.Constant;


public class OpenCloseActivity extends Activity {

    private static final String TAG = "MainActivity";
    private ListView mMainTree;
    private String[] mMainData;
    private MainTreeAdapter mMainTreeAdapter;
    private Class[] mActivities;
    private String mGsmType;
    private String mActivate;
    private String mDeactivate;
    private String mInterrogate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mMainData = getResources().getStringArray(R.array.open_close);
        mActivities = new Class[mMainData.length];
        mGsmType = getIntent().getStringExtra(Constant.GSM_TYPE);
        String gsmCode = "";
        if (TextUtils.equals(mGsmType, Constant.GSM_CALL_BLOCK_BA_ALL)) {
            gsmCode = Constant.SC_BA_ALL;
        }else if (TextUtils.equals(mGsmType, Constant.GSM_CALL_BLOCK_BA_MO)) {
            gsmCode = Constant.SC_BA_MO;
        }else if (TextUtils.equals(mGsmType, Constant.GSM_CALL_BLOCK_BA_MT)) {
            gsmCode = Constant.SC_BA_MT;
        }else if (TextUtils.equals(mGsmType, Constant.GSM_CALL_BLOCK_BAIC)) {
            gsmCode = Constant.SC_BAIC;
        }else if (TextUtils.equals(mGsmType, Constant.GSM_CALL_BLOCK_BAICR)) {
            gsmCode = Constant.SC_BAICr;
        }else if (TextUtils.equals(mGsmType, Constant.GSM_CALL_BLOCK_BAOC)) {
            gsmCode = Constant.SC_BAOC;
        }else if (TextUtils.equals(mGsmType, Constant.GSM_CALL_BLOCK_BAOIC)) {
            gsmCode = Constant.SC_BAOIC;
        }else if (TextUtils.equals(mGsmType, Constant.GSM_CALL_BLOCK_BAOICXH)) {
            gsmCode = Constant.SC_BAOICxH;
        }else if (TextUtils.equals(mGsmType, Constant.GSM_CALL_WAITING)) {
            gsmCode = Constant.SC_WAIT;
        }
        mActivate = Constant.ACTION_ACTIVATE + gsmCode + Constant.END_OF_USSD_COMMAND;
        mDeactivate = Constant.ACTION_DEACTIVATE + gsmCode + Constant.END_OF_USSD_COMMAND;
        mInterrogate = Constant.ACTION_INTERROGATE + gsmCode + Constant.END_OF_USSD_COMMAND;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        mMainTree = (ListView) findViewById(R.id.main);
        mMainTreeAdapter = new MainTreeAdapter();
        mMainTree.setAdapter(mMainTreeAdapter);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getResources().getString(R.string.app_name));

        mMainTree.setDivider(null);
        mMainTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handlePosition(position);
            }
        });
    }

    private void handlePosition(int position) {
        switch (position) {
            case 0:
                Intent open = new Intent(Intent.ACTION_CALL);
                open.setData(Uri.parse("tel:" + mActivate));
                startActivity(open);
                break;
            case 1:
                Intent close = new Intent(Intent.ACTION_CALL);
                close.setData(Uri.parse("tel:" + mDeactivate));
                startActivity(close);
                break;
            case 2:
                Intent quest = new Intent(Intent.ACTION_CALL);
                quest.setData(Uri.parse("tel:" + mInterrogate));
                startActivity(quest);
                break;
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                int selectedItemPosition = mMainTree.getSelectedItemPosition();
                handlePosition(selectedItemPosition);
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    class MainTreeAdapter extends BaseAdapter {

        public MainTreeAdapter() {
        }

        @Override
        public int getCount() {
            return mMainData.length;
        }

        @Override
        public String getItem(int position) {
            return mMainData[position % mMainData.length];
        }

        @Override
        public long getItemId(int position) {
            return position % mMainData.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView item = (TextView) getLayoutInflater().inflate(R.layout.item, parent, false);
            item.setText(mMainData[position % mMainData.length]);
            return item;
        }
    }
}

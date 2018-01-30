package com.flyscale.callsettings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import global.Constant;


public class DelayToDialActivity extends Activity {

    private static final String TAG = "DelayToDialActivity";
    private ListView mMainTree;
    private String[] mMainData;
    private MainTreeAdapter mMainTreeAdapter;
    private boolean isOpen;
    private int mDelay;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mMainData = getResources().getStringArray(R.array.delaytodial);
        SharedPreferences sp = getSharedPreferences(Constant.SP,
                Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
        isOpen = sp.getBoolean(Constant.DELAY_TO_DIAL_ENABLED, false);
        mDelay = sp.getInt(Constant.DELAY_SECOND, -1);
        switch (mDelay) {
            case 3:
                mPosition = 1;
                break;
            case 5:
                mPosition = 2;
                break;
            case 7:
                mPosition = 3;
                break;
            case 9:
                mPosition = 4;
                break;
        }
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
        title.setText(getResources().getString(R.string.delay_to_dial));

        mMainTree.setDivider(null);
        mMainTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handlePosition(position);
            }
        });
    }

    private void handlePosition(int position) {
        SharedPreferences sp = getSharedPreferences(Constant.SP,
                Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = sp.edit();
        switch (position) {
            case 0:
                editor.putBoolean(Constant.DELAY_TO_DIAL_ENABLED, false);
                editor.commit();
                finish();
                break;
            case 1:
                editor.putBoolean(Constant.DELAY_TO_DIAL_ENABLED, true);
                editor.putInt(Constant.DELAY_SECOND, 3);
                editor.commit();
                finish();
                break;
            case 2:
                editor.putBoolean(Constant.DELAY_TO_DIAL_ENABLED, true);
                editor.putInt(Constant.DELAY_SECOND, 5);
                editor.commit();
                finish();
                break;
            case 3:
                editor.putBoolean(Constant.DELAY_TO_DIAL_ENABLED, true);
                editor.putInt(Constant.DELAY_SECOND, 7);
                editor.commit();
                finish();
                break;
            case 4:
                editor.putBoolean(Constant.DELAY_TO_DIAL_ENABLED, true);
                editor.putInt(Constant.DELAY_SECOND, 9);
                editor.commit();
                finish();
                break;
        }

    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
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

            View view = null;
            if (convertView != null) {
                view = convertView;
            } else {
                view = getLayoutInflater().inflate(R.layout.item_contacts, parent, false);
            }

            ImageView icon = (ImageView) view.findViewById(R.id.contact_icon);
            icon.setVisibility(View.GONE);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            CheckBox cb = (CheckBox) view.findViewById(R.id.cb);
            cb.setChecked(position == mPosition);
            tv.setText(mMainData[position]);

            return view;
        }
    }
}

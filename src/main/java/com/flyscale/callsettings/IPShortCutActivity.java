package com.flyscale.callsettings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.flyscale.callsettings.global.Constant;


public class IPShortCutActivity extends Activity {

    private static final String TAG = "IPShortCutActivity";
    private ListView mMainTree;
    private String[] mMainData;
    private MainTreeAdapter mMainTreeAdapter;
    private String ip;
    private boolean ipDialEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        SharedPreferences sp = getSharedPreferences(Constant.SP,
                Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
        ip = sp.getString(Constant.IP_DIAL, null);
        ipDialEnabled = sp.getBoolean(Constant.IP_DIAL_ENABLED, false);
        mMainData = getResources().getStringArray(R.array.ipshortcuts);
        for (int i = 0; i < mMainData.length; i++) {
            Log.d(TAG, "mMainData=" + mMainData[i]);
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
        title.setText(getResources().getString(R.string.ip_shortcut));

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
        editor.putString(Constant.IP_DIAL, mMainData[position]);
        editor.commit();
        finish();
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
            if (ipDialEnabled) {
                if (TextUtils.equals(ip, mMainData[position])) {
                    cb.setChecked(true);
                } else {
                    cb.setChecked(false);
                }
            }else {
                if (position == 0) {
                    cb.setChecked(true);
                }else {
                    cb.setChecked(false);
                }
            }
            tv.setText(mMainData[position]);

            return view;
        }
    }
}

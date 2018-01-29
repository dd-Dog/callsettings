package com.flyscale.callsettings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/1/29 0029.
 * 忙时转移
 */

public class ForWardBusy extends Activity {

    private static final String TAG = "CallForwardBusy";
    private ListView mCallForwardBusyTree;
    private String[] mCallForwardBusyData;
    private ForwardBusyAdapter mForwardBusyTreeAdapter;
    private Class[] mActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mCallForwardBusyData = getResources().getStringArray(R.array.open_close);
        mActivities = new Class[mCallForwardBusyData.length];
        mActivities[0] = ForwardBusyOpen.class;
        mActivities[1] = ForwardBusyClose.class;
        mActivities[2] = ForwardBusyStatus.class;

    }

    private void initView() {

        mCallForwardBusyTree = (ListView) findViewById(R.id.main);
        mCallForwardBusyTree.setVerticalScrollBarEnabled(false);
        mForwardBusyTreeAdapter = new ForwardBusyAdapter();
        mCallForwardBusyTree.setAdapter(mForwardBusyTreeAdapter);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getResources().getString(R.string.call_forwarding_busy));

        mCallForwardBusyTree.setDivider(null);
        mCallForwardBusyTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ForWardBusy.this, mActivities[position]);
                startActivityForResult(intent, position);
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                int selectedItemPosition = mCallForwardBusyTree.getSelectedItemPosition();
                Intent intent = new Intent(ForWardBusy.this, mActivities[selectedItemPosition]);
                startActivityForResult(intent, selectedItemPosition);
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }



    class ForwardBusyAdapter extends BaseAdapter {

        public ForwardBusyAdapter() {
        }

        @Override
        public int getCount() {
            return mCallForwardBusyData.length;
        }

        @Override
        public String getItem(int position) {
            return mCallForwardBusyData[position % mCallForwardBusyData.length];
        }

        @Override
        public long getItemId(int position) {
            return position % mCallForwardBusyData.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView item = (TextView) getLayoutInflater().inflate(R.layout.item, parent, false);
            item.setText(mCallForwardBusyData[position % mCallForwardBusyData.length]);
            return item;
        }
    }
}

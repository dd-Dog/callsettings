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
 * 无法到达转移
 */

public class ForwardReach extends Activity {

    private static final String TAG = "ForwardReach";
    private ListView mCallForwardReachTree;
    private String[] mCallForwardReachData;
    private ForwardReachAdapter mForwardReachTreeAdapter;
    private Class[] mActivities;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        mCallForwardReachData = getResources().getStringArray(R.array.open_close);
        mActivities = new Class[mCallForwardReachData.length];
        mActivities[0] = ForwardReachOpen.class;
        mActivities[1] = ForwardReachClose.class;
        mActivities[2] = ForwardReachStatus.class;

    }

    private void initView() {

        mCallForwardReachTree = (ListView) findViewById(R.id.main);
        mCallForwardReachTree.setVerticalScrollBarEnabled(false);
        mForwardReachTreeAdapter = new ForwardReachAdapter();
        mCallForwardReachTree.setAdapter(mForwardReachTreeAdapter);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getResources().getString(R.string.out_of_reach));

        mCallForwardReachTree.setDivider(null);
        mCallForwardReachTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ForwardReach.this, mActivities[position]);
                startActivityForResult(intent, position);
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                int selectedItemPosition = mCallForwardReachTree.getSelectedItemPosition();
                Intent intent = new Intent(ForwardReach.this, mActivities[selectedItemPosition]);
                startActivityForResult(intent, selectedItemPosition);
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }



    class ForwardReachAdapter extends BaseAdapter {

        public ForwardReachAdapter() {
        }

        @Override
        public int getCount() {
            return mCallForwardReachData.length;
        }

        @Override
        public String getItem(int position) {
            return mCallForwardReachData[position % mCallForwardReachData.length];
        }

        @Override
        public long getItemId(int position) {
            return position % mCallForwardReachData.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView item = (TextView) getLayoutInflater().inflate(R.layout.item, parent, false);
            item.setText(mCallForwardReachData[position % mCallForwardReachData.length]);
            return item;
        }
    }
}

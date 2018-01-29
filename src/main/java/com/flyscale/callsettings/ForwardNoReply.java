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
 * 无应答转移
 */

public class ForwardNoReply extends Activity {

    private static final String TAG = "ForwardNoReply";
    private ListView mCallForwardNoReplyTree;
    private String[] mCallForwardNoReplyData;
    private ForwardNoReplyAdapter mForwardNoReplyTreeAdapter;
    private Class[] mActivities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        mCallForwardNoReplyData = getResources().getStringArray(R.array.open_close);
        mActivities = new Class[mCallForwardNoReplyData.length];
        mActivities[0] = ForwardNoReplyOpen.class;
        mActivities[1] = ForwardNoReplyClose.class;
        mActivities[2] = ForwardNoReplyStatus.class;

    }

    private void initView() {

        mCallForwardNoReplyTree = (ListView) findViewById(R.id.main);
        mCallForwardNoReplyTree.setVerticalScrollBarEnabled(false);
        mForwardNoReplyTreeAdapter = new ForwardNoReplyAdapter();
        mCallForwardNoReplyTree.setAdapter(mForwardNoReplyTreeAdapter);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getResources().getString(R.string.call_forwarding_no_reply));

        mCallForwardNoReplyTree.setDivider(null);
        mCallForwardNoReplyTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ForwardNoReply.this, mActivities[position]);
                startActivityForResult(intent, position);
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                int selectedItemPosition = mCallForwardNoReplyTree.getSelectedItemPosition();
                Intent intent = new Intent(ForwardNoReply.this, mActivities[selectedItemPosition]);
                startActivityForResult(intent, selectedItemPosition);
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }



    class ForwardNoReplyAdapter extends BaseAdapter {

        public ForwardNoReplyAdapter() {
        }

        @Override
        public int getCount() {
            return mCallForwardNoReplyData.length;
        }

        @Override
        public String getItem(int position) {
            return mCallForwardNoReplyData[position % mCallForwardNoReplyData.length];
        }

        @Override
        public long getItemId(int position) {
            return position % mCallForwardNoReplyData.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView item = (TextView) getLayoutInflater().inflate(R.layout.item, parent, false);
            item.setText(mCallForwardNoReplyData[position % mCallForwardNoReplyData.length]);
            return item;
        }
    }
}

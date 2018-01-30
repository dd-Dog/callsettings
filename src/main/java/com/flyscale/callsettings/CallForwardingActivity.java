package com.flyscale.callsettings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class CallForwardingActivity extends Activity {

    private static final String TAG = "CallForwardingActivity";
    private Context mContext = this;
    private ListView mCallForwardTree;
    private String[] mCallForwardData;
    private CallForwardingTreeAdapter mCallForwardingTreeAdapter;
    private Class[] mActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mCallForwardData = getResources().getStringArray(R.array.call_forwarding);
        mActivities = new Class[mCallForwardData.length];
        mActivities[0] = ForWardAll.class;
        mActivities[1] = ForWardBusy.class;
        mActivities[2] = ForwardNoReply.class;
        mActivities[3] = ForwardReach.class;
        mActivities[4] = ForwardCancelAll.class;

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        mCallForwardTree = (ListView) findViewById(R.id.main);
        mCallForwardTree.setVerticalScrollBarEnabled(false);
        mCallForwardingTreeAdapter = new CallForwardingTreeAdapter();
        mCallForwardTree.setAdapter(mCallForwardingTreeAdapter);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getResources().getString(R.string.call_forwarding_settings));

        mCallForwardTree.setDivider(null);
        mCallForwardTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (SimCardState.ishasSimCard(mContext)){
                    Intent intent = new Intent(CallForwardingActivity.this, mActivities[position]);
                    startActivityForResult(intent, position);
                }else {
                    Intent intent = new Intent(CallForwardingActivity.this, NoSimCardActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                int selectedItemPosition = mCallForwardTree.getSelectedItemPosition();
                Intent intent = new Intent(CallForwardingActivity.this, mActivities[selectedItemPosition]);
                startActivityForResult(intent, selectedItemPosition);
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    class CallForwardingTreeAdapter extends BaseAdapter {

        public CallForwardingTreeAdapter() {
        }

        @Override
        public int getCount() {
            return mCallForwardData.length;
        }

        @Override
        public String getItem(int position) {
            return mCallForwardData[position % mCallForwardData.length];
        }

        @Override
        public long getItemId(int position) {
            return position % mCallForwardData.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView item = (TextView) getLayoutInflater().inflate(R.layout.item, parent, false);
            item.setText(mCallForwardData[position % mCallForwardData.length]);
            return item;
        }
    }
}

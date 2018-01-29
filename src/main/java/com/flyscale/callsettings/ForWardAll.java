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
 * 无条件转移
 */

public class ForWardAll extends Activity {

    private static final String TAG = "CallForwardingALL";
    private ListView mCallForwardALLTree;
    private String[] mCallForwardALLData;
    private ForwardAllAdapter mForwardAllTreeAdapter;
    private Class[] mActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }

    private void initData() {
        mCallForwardALLData = getResources().getStringArray(R.array.open_close);
        mActivities = new Class[mCallForwardALLData.length];
        mActivities[0] = ForwardAllOpen.class;
        mActivities[1] = ForwardAllClose.class;
        mActivities[2] = ForwardAllStatus.class;

    }

    private void initView() {

        mCallForwardALLTree = (ListView) findViewById(R.id.main);
        mCallForwardALLTree.setVerticalScrollBarEnabled(false);
        mForwardAllTreeAdapter = new ForwardAllAdapter();
        mCallForwardALLTree.setAdapter(mForwardAllTreeAdapter);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getResources().getString(R.string.call_forwarding_all));

        mCallForwardALLTree.setDivider(null);
        mCallForwardALLTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(ForWardAll.this, mActivities[position]);
                    startActivityForResult(intent, position);

            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                int selectedItemPosition = mCallForwardALLTree.getSelectedItemPosition();
                Intent intent = new Intent(ForWardAll.this, mActivities[selectedItemPosition]);
                startActivityForResult(intent, selectedItemPosition);
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }



    class ForwardAllAdapter extends BaseAdapter {

        public ForwardAllAdapter() {
        }

        @Override
        public int getCount() {
            return mCallForwardALLData.length;
        }

        @Override
        public String getItem(int position) {
            return mCallForwardALLData[position % mCallForwardALLData.length];
        }

        @Override
        public long getItemId(int position) {
            return position % mCallForwardALLData.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView item = (TextView) getLayoutInflater().inflate(R.layout.item, parent, false);
            item.setText(mCallForwardALLData[position % mCallForwardALLData.length]);
            return item;
        }
    }
}

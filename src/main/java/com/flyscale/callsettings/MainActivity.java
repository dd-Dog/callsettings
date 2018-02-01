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

import com.flyscale.callsettings.global.Constant;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private static final int SET_CALL_WAITING = 1000;
    private Context mContext = this;
    private ListView mMainTree;
    private String[] mMainData;
    private MainTreeAdapter mMainTreeAdapter;
    private Class[] mActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        mMainData = getResources().getStringArray(R.array.main);
        mActivities = new Class[mMainData.length];
        mActivities[0] = CallForwardingActivity.class;
        mActivities[1] = OpenCloseActivity.class;
        mActivities[2] = DelayToDialActivity.class;
        mActivities[3] = IPShortCutActivity.class;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        mMainTree = (ListView) findViewById(R.id.main);
        mMainTree.setVerticalScrollBarEnabled(false);
        mMainTreeAdapter = new MainTreeAdapter();
        mMainTree.setAdapter(mMainTreeAdapter);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getResources().getString(R.string.app_name));

        mMainTree.setDivider(null);
        mMainTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleOptions(position);
            }
        });
    }

    private void handleOptions(int position) {
//        if (position == 1) {
//            Intent intent = new Intent(Intent.ACTION_CALL);
//            intent.setData(Uri.parse("tel:*%2343%23"));
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
//                    PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//            startActivity(intent);
////            Intent intent = new Intent();
////            ComponentName componentName = new ComponentName("com.android.phone", "com" +
////                    ".android.phone.OpenCloseActivity");
////            intent.setComponent(componentName);
////            startActivityForResult(intent, SET_CALL_WAITING);
//            return;
//        }
        Intent intent = new Intent(MainActivity.this, mActivities[position]);
        if (position == 1) {
            intent.putExtra(Constant.GSM_TYPE, Constant.GSM_CALL_WAITING);
        }
        startActivityForResult(intent, position);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                int position = mMainTree.getSelectedItemPosition();
                handleOptions(position);
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

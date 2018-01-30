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

import com.flyscale.callsettings.global.Constant;


/**
 *      // Call Barring
 93     static final String SC_BAOC         = "33";
 94     static final String SC_BAOIC        = "331";
 95     static final String SC_BAOICxH      = "332";
 96     static final String SC_BAIC         = "35";
 97     static final String SC_BAICr        = "351";
 98
 99     static final String SC_BA_ALL       = "330";
 100     static final String SC_BA_MO        = "333";
 101     static final String SC_BA_MT        = "353";

 */
public class CallBlockingActivity extends Activity {

    private static final String TAG = "MainActivity";
    private ListView mMainTree;
    private String[] mMainData;
    private MainTreeAdapter mMainTreeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mMainData = getResources().getStringArray(R.array.call_block);
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
        Intent intent = new Intent(this, OpenCloseActivity.class);
        switch (position) {
            case 0:
                intent.putExtra(Constant.GSM_TYPE, Constant.GSM_CALL_BLOCK_BA_MO);
                break;
            case 1:
                intent.putExtra(Constant.GSM_TYPE, Constant.GSM_CALL_BLOCK_BAIC);
                break;
            case 2:
                intent.putExtra(Constant.GSM_TYPE, Constant.GSM_CALL_BLOCK_BAICR);
                break;
            case 3:
                intent.putExtra(Constant.GSM_TYPE, Constant.GSM_CALL_BLOCK_BAOIC);
                break;
            case 4:
                intent.putExtra(Constant.GSM_TYPE, Constant.GSM_CALL_BLOCK_BAOICXH);
                break;
            case 5:
                break;
        }
        startActivityForResult(intent, position);
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

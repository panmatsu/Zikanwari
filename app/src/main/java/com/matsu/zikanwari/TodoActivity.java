package com.matsu.zikanwari;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoActivity extends AppCompatActivity {

    private static final String LIST_ITEM_TEXT1 = "Id";
    private static final String LIST_ITEM_TEXT2 = "Name";

    private List<Map<String,String>> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        // ツールバーをアクションバーとしてセット
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView = (ListView)findViewById(R.id.list_view);

        //アダプタ作成
        mList = new ArrayList<>();
        final Simple2Adapter adapter = new Simple2Adapter(this, mList,
                R.layout.list_item_todo,
                new String[]{LIST_ITEM_TEXT1, LIST_ITEM_TEXT2},
                new int[]{R.id.list_item_text1, R.id.list_item_text2},
                R.id.list_item_button
        );

        //クイックイベント処理
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ItemClick", "Position=" + String.valueOf(position));
            }
        });
        listView.setAdapter(adapter);

        // 表示するデータを設定
        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap<>();
            map.put(LIST_ITEM_TEXT1, String.valueOf(i));
            map.put(LIST_ITEM_TEXT2, "名前・・・");
            mList.add(map);
        }
    }
}
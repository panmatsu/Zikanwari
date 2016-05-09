package com.matsu.zikanwari;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoActivity extends AppCompatActivity {

    //ListViewのitemのText
    private static final String LIST_ITEM_TEXT1 = "Id";
    private static final String LIST_ITEM_TEXT2 = "Name";
    /**
     *TODO
     * ToDoSettingActivityをつくる
     * やることと締め切りの日付を選択(DatePicker)させる
     * SharePreferenceで保存する
     * ToDoActivityで表示させる
     */



    private List<Map<String,String>> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        // ツールバーをアクションバーとしてセット
        Toolbar toolbar_todo = (Toolbar) findViewById(R.id.tool_bar_todo);
        setSupportActionBar(toolbar_todo);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
            if(i == 0) {
                map.put(LIST_ITEM_TEXT1, "英語の宿題");
                map.put(LIST_ITEM_TEXT2, "2016年6月１７日まで");

            }else if(i==9){
                map.put(LIST_ITEM_TEXT1, "プログラミングの宿題");
                map.put(LIST_ITEM_TEXT2, "2016年5月20日まで");

            }else {
                map.put(LIST_ITEM_TEXT1, String.valueOf(i));
                map.put(LIST_ITEM_TEXT2, "名前・・・");
            }
            mList.add(map);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.todo_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            //設定ページへの移動
            case R.id.menu_setting_page:
                Intent intent = new Intent(getApplication(), SettingActivity.class);
                startActivity(intent);
                break;
            //ToDoページへの移動
            case R.id.menu_ToDo:
                Intent todo_intent = new Intent(this,TodoActivity.class);
                startActivity(todo_intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
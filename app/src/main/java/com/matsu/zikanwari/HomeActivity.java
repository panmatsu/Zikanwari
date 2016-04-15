package com.matsu.zikanwari;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends ActionBarActivity{

    String[] subject = new String[36];
    String[] teacher = new String[36];
    String[] room = new String[36];
    String[] memo = new String[36];

    TextView[] textView = new TextView[36];

    //Layoutのデータ番号
    int layout_mode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);

        //5限:5 6限:6
        int zigen = data.getInt("Zigen",5);

        //あり:1 なし:0
        int doyou = data.getInt("Doyou",1);

        if(zigen == 5 && doyou == 1){
            //土曜ありの５限
            setContentView(R.layout.activity_home);
            layout_mode = 0;
            //Log.d("Test","土曜ありの５限");
        }else if(zigen==6 && doyou == 1){
            //土曜ありの６限
            setContentView(R.layout.activity_home1);
            layout_mode = 1;
            //Log.d("Test","土曜ありの６限");
        }else if(zigen == 5 && doyou == 0){
            //土曜なしの５限
            setContentView(R.layout.activity_home2);
            layout_mode = 2;
            //Log.d("Test","土曜なしの５限");
        }else if(zigen == 6 && doyou == 0){
            //土曜なしの６限
            setContentView(R.layout.activity_home3);
            layout_mode = 3;
            //Log.d("Test","土曜なしの６限");
        }

        // ツールバーをアクションバーとしてセット
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
//        String Subject = data.getString("SUBJECT", null);
//        String Room = data.getString("ROOM",null);
//        String Teacher = data.getString("TEACHER",null);
//        String Memo = data.getString("MEMO",null);


//        if (Subject!=null||Room!=null|Teacher!=null||Memo!=null){
//            //コンマで区切って配列に代入
//            subject = Subject.split(",",0);
//            teacher = Teacher.split(",",0);
//            room = Room.split(",",0);
//            memo = Memo.split(",",0);
//        }

        //OutputActivityから戻った時の処理
        //Intent intent = getIntent();
        //int position = intent.getIntExtra("POSITION", 0);
        //subject[position] = intent.getStringExtra("SUBJECT");
        //teacher[position] = intent.getStringExtra("TEACHER");
        //room[position] = intent.getStringExtra("ROOM");
        //memo[position] = intent.getStringExtra("MEMO");

//        SharedPreferences.Editor editor = data.edit();
//        for (int i =0;i<35;i++){
//            editor.putString("SUBJECT"+String.valueOf(i), subject[i]);
//        }
//        editor.apply();

        for (int i =0;i<36;i++){
            subject[i] = data.getString("SUBJECT"+String.valueOf(i), null);
            teacher[i] = data.getString("TEACHER"+String.valueOf(i),null);
            room[i] = data.getString("ROOM"+String.valueOf(i),null);
            memo[i] = data.getString("MEMO"+String.valueOf(i),null);

            devideCase(i);
        }
    }

    public void devideCase(int i){
        //Log.d("devideCase",String.valueOf(i));
        switch (layout_mode){
            case 0:
                //土曜ありの５限
                if(i<30){
                    setData(i);
                }
                break;
            case 1:
                //土曜ありの６限
                setData(i);
                break;
            case 2:
                //土曜なしの５限
                if(i%6 != 5){
                    if (i < 30) {
                        //iが6n-1のとき
                        setData(i);
                    }
                }
                break;
            case 3:
                //土曜なしの６限
                if (i%6 != 5){
                    setData(i);
                }
                break;
        }
    }

    public void setData(int i){
        //Log.d("setData",String.valueOf(i));
        int id = getResources().getIdentifier("position_" + i, "id", getPackageName());
        textView[i] = (TextView)findViewById(id);
        if(subject[i]!=null) {
            Log.d("AAA",String.valueOf(i)+subject[i]);
            textView[i].setText(subject[i]);
            textView[i].setTextColor(Color.BLACK);
        }
        /**
         * 追加 onTouch
         */
        final int finalI = i;
        /**
         * 時間割欄を長押しした場合の処理
         */
        textView[i].setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                Intent intent = new Intent(getApplication(), OutputActivity.class);
                intent.putExtra("POSITION", finalI);
                Log.d("TEST",finalI+"intent");
                startActivity(intent);
                Log.d("AAAAAAAAAAAAAAA",String.valueOf(finalI));
                // trueにすると以下のOnClickが呼ばれない
                return true;
            }
        });

        /**
         * 時間割欄を軽くタッチした場合の処理
         */
        textView[i].setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "科目:" + subject[finalI] + "\n" +
                                "教室:" + room[finalI] + "\n" +
                                "教授:" + teacher[finalI] + "\n" +
                                "メモ:" + memo[finalI],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

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
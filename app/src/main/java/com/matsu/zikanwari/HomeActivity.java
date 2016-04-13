package com.matsu.zikanwari;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    int layout_data;

    //配列の番号を認識するよう
    int ren;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        int zigen = data.getInt("Zigen",5);
        int doyou = data.getInt("Doyou",1);

        if(zigen == 5 && doyou == 1){
            //土曜ありの５限
            setContentView(R.layout.activity_home);
            layout_data = 0;
        }else if(zigen==6 && doyou == 1){
            //土曜ありの６限
            setContentView(R.layout.activity_home1);
            layout_data = 1;
        }else if(zigen == 5 && doyou == 0){
            //土曜なしの５限
            setContentView(R.layout.activity_home2);
            layout_data = 2;
        }else if(zigen == 6 && doyou == 0){
            //土曜なしの６限
            setContentView(R.layout.activity_home3);
            layout_data = 3;
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
        String Subject = data.getString("SUBJECT", null);
        String Room = data.getString("ROOM",null);
        String Teacher = data.getString("TEACHER",null);
        String Memo = data.getString("MEMO",null);


//        if (Subject!=null||Room!=null|Teacher!=null||Memo!=null){
//            //コンマで区切って配列に代入
//            subject = Subject.split(",",0);
//            teacher = Teacher.split(",",0);
//            room = Room.split(",",0);
//            memo = Memo.split(",",0);
//        }

        //OutputActivityから戻った時の処理
        Intent intent = getIntent();
        int position = intent.getIntExtra("POSITION", 0);
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
        }

        //TODO Qiita
        for(int i = 0; i < 36; i++){
            int id = getResources().getIdentifier("position_" + i, "id", getPackageName());
            textView[i] = (TextView)findViewById(id);
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
            if(subject[i]!=null) {
                Log.d("AAA",subject[i]);
                textView[i].setText(subject[i]);
            }
        }
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
            case R.id.menu_toppage:
//                NavUtils.navigateUpFromSameTask(this);
                Intent intent = new Intent(getApplication(), SettingActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
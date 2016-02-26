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

    String[] subject = new String[30];
    String[] teacher = new String[30];
    String[] room = new String[30];
    String[] memo = new String[30];

    TextView[] textView = new TextView[30];

    //配列の番号を認識するよう
    int ren;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        int zigen = data.getInt("Zigen",5);
        int doyou = data.getInt("Doyou",1);

        //if(zigen == 5 && doyou == 1){
            //土曜ありの５限
            setContentView(R.layout.activity_home);
//        }else if(zigen==6 && doyou == 1){
//            //土曜ありの６限
//            setContentView(R.layout.activity_home1);
//        }else if(zigen == 5 && doyou == 0){
//            //土曜なしの５限
//            setContentView(R.layout.activity_home2);
//        }else if(zigen == 6 && doyou == 0){
//            //土曜なしの６限
//            setContentView(R.layout.activity_home3);
//        }

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

        for (int i =0;i<30;i++){
            subject[i] = data.getString("SUBJECT"+String.valueOf(i), null);
            teacher[i] = data.getString("TEACHER"+String.valueOf(i),null);
            room[i] = data.getString("ROOM"+String.valueOf(i),null);
            memo[i] = data.getString("MEMO"+String.valueOf(i),null);
        }

        //TODO Qiita
        for(int i = 0; i < 30; i++){
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

//    public void onClick(View v) {
//
//        int x = 0,y = 0,position = 0;
//
//        switch (v.getId()) {
//            case R.id.position_0:
//                x = 0;
//                y = 0;
//                position = 0;
//                break;
//            case R.id.position_1:
//                x = 0;
//                y = 1;
//                position = 1;
//                break;
//            case R.id.position_2:
//                x = 0;
//                y = 2;
//                position = 2;
//                break;
//            case R.id.position_3:
//                x = 0;
//                y = 3;
//                position = 3;
//                break;
//            case R.id.position_4:
//                x = 0;
//                y = 4;
//                position = 4;
//                break;
//            case R.id.position_5:
//                x = 0;
//                y = 5;
//                position = 5;
//                break;
//            case R.id.position_6:
//                x = 1;
//                y = 0;
//                position = 6;
//                break;
//            case R.id.position_7:
//                x = 1;
//                y = 1;
//                position = 7;
//                break;
//            case R.id.position_8:
//                x = 1;
//                y = 2;
//                position = 8;
//                break;
//            case R.id.position_9:
//                x = 1;
//                y = 3;
//                position = 9;
//                break;
//            case R.id.position_10:
//                x = 1;
//                y = 4;
//                position = 10;
//                break;
//            case R.id.position_11:
//                x = 1;
//                y = 5;
//                position = 11;
//                break;
//            case R.id.position_12:
//                x = 2;
//                y = 0;
//                position = 12;
//                break;
//            case R.id.position_13:
//                x = 2;
//                y = 1;
//                position = 13;
//                break;
//            case R.id.position_14:
//                x = 2;
//                y = 2;
//                position = 14;
//                break;
//            case R.id.position_15:
//                x = 2;
//                y = 3;
//                position = 15;
//                break;
//            case R.id.position_16:
//                x = 2;
//                y = 4;
//                position = 16;
//                break;
//            case R.id.position_17:
//                x = 2;
//                y = 5;
//                position = 17;
//                break;
//            case R.id.position_18:
//                x = 3;
//                y = 0;
//                position = 18;
//                break;
//            case R.id.position_19:
//                x = 3;
//                y = 1;
//                position = 19;
//                break;
//            case R.id.position_20:
//                x = 3;
//                y = 2;
//                position = 20;
//                break;
//            case R.id.position_21:
//                x = 3;
//                y = 3;
//                position = 21;
//                break;
//            case R.id.position_22:
//                x = 3;
//                y = 4;
//                position = 22;
//                break;
//            case R.id.position_23:
//                x = 3;
//                y = 5;
//                position = 23;
//                break;
//            case R.id.position_24:
//                x = 4;
//                y = 0;
//                position = 24;
//                break;
//            case R.id.position_25:
//                x = 4;
//                y = 1;
//                position = 25;
//                break;
//            case R.id.position_26:
//                x = 4;
//                y = 2;
//                position = 26;
//                break;
//            case R.id.position_27:
//                x = 4;
//                y = 3;
//                position = 27;
//                break;
//            case R.id.position_28:
//                x = 4;
//                y = 4;
//                position = 28;
//                break;
//            case R.id.position_29:
//                x = 4;
//                y = 5;
//                position = 29;
//                break;
//        }
//
////        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
////        SharedPreferences.Editor editor = data.edit();
////        editor.putInt("POSITION",position);
////
////        editor.apply();
//
//        Intent intent = new Intent(getApplication(), OutputActivity.class);
//        intent.putExtra("position_x",x);
//        intent.putExtra("position_y",y);
//        intent.putExtra("POSITION",position);
//        startActivity(intent);
//    }
}
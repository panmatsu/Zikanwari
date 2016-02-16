package com.matsu.zikanwari;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // ツールバーをアクションバーとしてセット
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

        // いつものUPナビゲーションの処理
        switch (id) {
            case R.id.menu_toppage:
//                NavUtils.navigateUpFromSameTask(this);
                Log.d("aaa", "aaaaaa");
                Intent intent = new Intent(getApplication(), SettingActivity.class);
                startActivity(intent);


                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void settingClick(View v){
        Intent intent = new Intent(getApplication(), SettingActivity.class);
        startActivity(intent);
    }

    public void onClick(View v) {

        //座標
        int x = 0;
        int y = 0;

        switch (v.getId()) {
            case R.id.position_00:
                x = 0;
                y = 0;
                break;
            case R.id.position_01:
                x = 0;
                y = 1;
                break;
            case R.id.position_02:
                x = 0;
                y = 2;
                break;
            case R.id.position_03:
                x = 0;
                y = 3;
                break;
            case R.id.position_04:
                x = 0;
                y = 4;
                break;
            case R.id.position_05:
                x = 0;
                y = 5;
                break;
            case R.id.position_10:
                x = 1;
                y = 0;
                break;
            case R.id.position_11:
                x = 1;
                y = 1;
                break;
            case R.id.position_12:
                x = 1;
                y = 2;
                break;
            case R.id.position_13:
                x = 1;
                y = 3;
                break;
            case R.id.position_14:
                x = 1;
                y = 4;
                break;
            case R.id.position_15:
                x = 1;
                y = 5;
                break;
            case R.id.position_20:
                x = 2;
                y = 0;
                break;
            case R.id.position_21:
                x = 2;
                y = 1;
                break;
            case R.id.position_22:
                x = 2;
                y = 2;
                break;
            case R.id.position_23:
                x = 2;
                y = 3;
                break;
            case R.id.position_24:
                x = 2;
                y = 4;
                break;
            case R.id.position_25:
                x = 2;
                y = 5;
                break;
            case R.id.position_30:
                x = 3;
                y = 0;
                break;
            case R.id.position_31:
                x = 3;
                y = 1;
                break;
            case R.id.position_32:
                x = 3;
                y = 2;
                break;
            case R.id.position_33:
                x = 3;
                y = 3;
                break;
            case R.id.position_34:
                x = 3;
                y = 4;
                break;
            case R.id.position_35:
                x = 3;
                y = 5;
                break;
            case R.id.position_40:
                x = 4;
                y = 0;
                break;
            case R.id.position_41:
                x = 4;
                y = 1;
                break;
            case R.id.position_42:
                x = 4;
                y = 2;
                break;
            case R.id.position_43:
                x = 4;
                y = 3;
                break;
            case R.id.position_44:
                x = 4;
                y = 4;
                break;
            case R.id.position_45:
                x = 4;
                y = 5;
                break;
        }
        Intent intent = new Intent(getApplication(), OutputActivity.class);
        intent.putExtra("position_x", x);
        intent.putExtra("position_y", y);
        startActivity(intent);
    }



}

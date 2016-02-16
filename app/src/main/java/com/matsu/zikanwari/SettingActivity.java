package com.matsu.zikanwari;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingActivity extends AppCompatActivity {

    String str0 ="あり";  //数字で1
    int selected2;  //ありだと1、なし0
    int selected0; //時間数を入れる

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void spinnerOK(View view){       //OKボタンを押したとき実行

// レイアウトからSpinnerを取得
        Spinner item0 = (Spinner) findViewById(R.id.spinnerZ);  //時限数
        Spinner item1 = (Spinner) findViewById(R.id.spinnerD);  //土曜日表示
// 選択したアイテムを取得
        int selected0 = Integer.parseInt((String) item0.getSelectedItem()); //時限数
        String selected1 = (String) item1.getSelectedItem();    //土曜日表示

        if(selected1.equals(str0)){
            selected2 = 1;
        }

        //data保存
        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putInt("Zigen", selected0);      //Zigenにselected0を保存
        editor.putInt("Doyou", selected2);      //Doyouにselected1を保存

        editor.apply();

        Intent inset = new Intent(getApplication(), HomeActivity.class);

        startActivity(inset);




    }

}

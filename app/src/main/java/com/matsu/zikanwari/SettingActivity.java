package com.matsu.zikanwari;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    String str0 ="あり";  //数字で1
    int selected2=1;  //ありだと1、なし0
  //  int selected0; //時間数を入れる
  EditText eT00;
    String nottext; //通知


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        eT00 = (EditText)findViewById(R.id.eT0);
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
    public void notiget(View view){
        nottext = eT00.getText().toString();  //取得
        Toast.makeText(this, nottext, Toast.LENGTH_LONG);
    }
    public void notion(View view){
// PendingIntentの生成
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity( this, 0, i, 0);
// 詳細情報の設定とPendingIntentの設定
        Notification notification = new Notification.Builder(this)

                .setContentTitle("通知")
                .setContentText(nottext)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.minie)
                .setAutoCancel(true)
                .build();
        NotificationManager G = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        G.notify(1000, notification);
    }

}



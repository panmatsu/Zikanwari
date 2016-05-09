package com.matsu.zikanwari;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OutputActivity extends Activity {

    TextView tb;
    EditText subject_edit,room_edit,teacher_edit,memo_edit;
    Intent intent;
    String subject;
    String teacher;
    String room;
    String memo;
    Button saveButton;
    int position;

    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        //保存Button
        saveButton = (Button)findViewById(R.id.button);

        subject_edit = (EditText)findViewById(R.id.subject_editText);
        room_edit = (EditText)findViewById(R.id.room_editText);
        teacher_edit = (EditText)findViewById(R.id.teacher_editText);
        memo_edit = (EditText)findViewById(R.id.memo_editText);

        //左上に選択された欄の曜日と何時間目かを表示させる
        setDate();

        //Hint と Text に表示させる
        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        subject_edit.setHint(data.getString("SUBJECT" + String.valueOf(position), null));
        subject_edit.setText(data.getString("SUBJECT" + String.valueOf(position), null));

        room_edit.setHint(data.getString("ROOM" + String.valueOf(position), null));
        room_edit.setText(data.getString("ROOM" + String.valueOf(position), null));

        teacher_edit.setHint(data.getString("TEACHER" + String.valueOf(position), null));
        teacher_edit.setText(data.getString("TEACHER" + String.valueOf(position), null));

        memo_edit.setHint(data.getString("MEMO"+String.valueOf(position),null));
        memo_edit.setText(data.getString("MEMO" + String.valueOf(position), null));
    }

    /**
     * 曜日と何時間目かをset
     */
    public void setDate(){
        //曜日＋○時間目
        tb = (TextView)findViewById(R.id.textView5);

        intent = getIntent();
        position = intent.getIntExtra("POSITION", 0);

        setDay(position);

        date += "曜日";

        if(0 <= position && position <6){
            date += "1";
        }else if(6 <= position && position < 12){
            date += "2";
        }else if(12 <= position && position < 18){
            date += "3";
        }else if(18 <= position && position < 24){
            date += "4";
        }else if(24 <= position && position < 30){
            date += "5";
        }else if(30 <= position && position < 36){
            date += "6";
        }

        date += "時間目";

        tb.setText(date);
    }

    //曜日を代入する
    public void setDay(int num){
        if(position%6 == 0 ){
            date = "月";
        }else if(position%6 == 1){
            date = "火";
        }else if(position%6 == 2){
            date = "水";
        }else if(position%6 == 3){
            date = "木";
        }else if(position%6 == 4){
            date = "金";
        }else if(position%6 == 5) {
            date = "土";
        }
    }

    public void OK(View view){
        //Textの取得
        subject = subject_edit.getText().toString();
        room = room_edit.getText().toString();
        teacher = teacher_edit.getText().toString();
        memo = memo_edit.getText().toString();


        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = data.edit();
        editor.putString("SUBJECT"+String.valueOf(position), subject);
        editor.putString("ROOM"+String.valueOf(position),room);
        editor.putString("TEACHER"+String.valueOf(position),teacher);
        editor.putString("MEMO"+String.valueOf(position),memo);
        editor.apply();


        intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}

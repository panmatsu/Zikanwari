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
    EditText et,et2,et3,et4;
    Intent intent;
    String DOTW;
    int POT;
    String subject;
    String teacher;
    String room;
    String memo;
    int position_x,position_y;
    Button saveButton;
    int position;

    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        //保存Button
        saveButton = (Button)findViewById(R.id.button);

        et = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText3);
        et4 = (EditText)findViewById(R.id.editText4);

        //左上に選択された欄の曜日と何時間目かを表示させる
        setDate();

        //Hint と Text に表示させる
        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        et.setHint(data.getString("SUBJECT" + String.valueOf(position), null));
        et.setText(data.getString("SUBJECT" + String.valueOf(position), null));

        et3.setHint(data.getString("ROOM" + String.valueOf(position), null));
        et3.setText(data.getString("ROOM" + String.valueOf(position), null));

        et2.setHint(data.getString("TEACHER" + String.valueOf(position), null));
        et2.setText(data.getString("TEACHER" + String.valueOf(position), null));

        et4.setHint(data.getString("MEMO"+String.valueOf(position),null));
        et4.setText(data.getString("MEMO" + String.valueOf(position), null));

        //WeekTime();
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


//    public void WeekTime(){
//
//        if(position_x == 0){
//            POT = 1;
//        } else if(position_x == 1){
//            POT = 2;
//        } else if(position_x == 2){
//            POT = 3;
//        } else if(position_x == 3){
//            POT = 4;
//        } else if(position_x == 4){
//            POT = 5;
//        } else if(position_x == 5){
//            POT = 6;
//        } else if(position_x == 6){
//            POT = 7;
//        }
//
//        if(position_y == 0){
//            DOTW = "月";
//        } else if(position_y == 1){
//            DOTW = "火";
//        } else if(position_y == 2){
//            DOTW = "水";
//        } else if(position_y == 3){
//            DOTW = "木";
//        } else if(position_y == 4){
//            DOTW = "金";
//        } else if(position_y == 5){
//            DOTW = "土";
//        }
//
//        tb.setText(DOTW + POT);
//    }



    public void OK(View view){
        //Textの取得
        subject = et.getText().toString();
        teacher = et2.getText().toString();
        room = et3.getText().toString();
        memo = et4.getText().toString();


        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = data.edit();
        editor.putString("SUBJECT"+String.valueOf(position), subject);
        editor.putString("ROOM"+String.valueOf(position),room);
        editor.putString("TEACHER"+String.valueOf(position),teacher);
        editor.putString("MEMO"+String.valueOf(position),memo);
        editor.apply();


        intent = new Intent(this,HomeActivity.class);
        //intent.putExtra("POSITION",position);
        //intent.putExtra("SUBJECT",subject);
        //intent.putExtra("ROOM",room);
        //intent.putExtra("TEACHER",teacher);
        //intent.putExtra("MEMO",memo);
        startActivity(intent);
    }
}

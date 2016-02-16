package com.matsu.zikanwari;

import android.app.Activity;
import android.content.Intent;
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
    String subject,teacher,room,memo;
    int position_x,position_y;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);


        tb = (TextView)findViewById(R.id.textView5);

        btn = (Button)findViewById(R.id.button);

        et = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText3);
        et4 = (EditText)findViewById(R.id.editText4);

        subject = et.getText().toString();
        teacher = et2.getText().toString();
        room = et3.getText().toString();
        memo = et4.getText().toString();

        intent = getIntent();
        position_x = intent.getIntExtra("position_x", 0);
        position_y = intent.getIntExtra("position_y", 0);

        WeekTime();
       // OK(btn);
    }



    public void WeekTime(){

        if(position_x == 0){
            POT = 1;
        } else if(position_x == 1){
            POT = 2;
        } else if(position_x == 2){
            POT = 3;
        } else if(position_x == 3){
            POT = 4;
        } else if(position_x == 4){
            POT = 5;
        } else if(position_x == 5){
            POT = 6;
        } else if(position_x == 6){
            POT = 7;
        }

        if(position_y == 0){
            DOTW = "月";
        } else if(position_y == 1){
            DOTW = "火";
        } else if(position_y == 2){
            DOTW = "水";
        } else if(position_y == 3){
            DOTW = "木";
        } else if(position_y == 4){
            DOTW = "金";
        } else if(position_y == 5){
            DOTW = "土";
        }

        tb.setText(DOTW + POT);
    }

    public void OK(View view){
        intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}

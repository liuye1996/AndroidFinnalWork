package com.example.n551.finnal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class selectActivity extends AppCompatActivity  {

    static TextView info;
    static TextView TextView1;
    static TextView TextView2;
    static TextView TextView3;
    static TextView TextView4;
    TextView mostAbsent;
    TextView mostLate;
    TextView numberTxt;
    Cursor cursor;
    Button upBtn, downBtn,numberBtn;
    SQLiteDatabase db;
    DBConnection helper;
    public int id_this;
    Bundle savedInstanceState;

    //定义数据库名称及结构
    static String TABLE_NAME = "Student";          //数据表名
    static String ID = "_id";                    //ID
    static String NAME = "name";                    //学生姓名
    static String NUMBER = "number";           //学号
    static String ABSENT = "absent";            //旷课次数
    static String LATE = "late";            //旷课次数
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        TextView1 = (TextView)findViewById(R.id.TextView1);
        TextView2 = (TextView)findViewById(R.id.TextView2);
        TextView3 = (TextView)findViewById(R.id.TextView3);
        TextView4 = (TextView)findViewById(R.id.TextView4);
        info = (TextView)findViewById(R.id.info);
        mostAbsent = (TextView)findViewById(R.id.mostAbsent);
        mostLate = (TextView)findViewById(R.id.mostLate);
        upBtn=(Button)findViewById(R.id.up1);
        upBtn.setOnClickListener(new ClickEvent());
        downBtn=(Button)findViewById(R.id.down1);
        downBtn.setOnClickListener(new ClickEvent());
        numberTxt=(TextView)findViewById(R.id.numberTxt);
        numberBtn=(Button)findViewById(R.id.numberBtn);
        numberBtn.setOnClickListener(new ClickEvent());

        try{
            db = openOrCreateDatabase("Students.db",
                    Context.MODE_PRIVATE, null) ;
            cursor = db.query("Student",
                    null , null, null, null, null, null);
            if(cursor.getCount()>0){
                mostAbsent.setText("旷课次数最多的人："+mostAbsent());
                mostLate.setText("迟到次数最多的人："+mostLate());
    //            cursor.moveToNext();
                datashow();
            }
            else{
                upBtn.setEnabled(false);
                downBtn.setEnabled(false);
                numberBtn.setEnabled(false);
                showToast("数据库为空，请先添加数据");
                board();
//                info.setText("数据库为空，请先添加数据");
            }
        }
        catch(Exception e){
            showToast("数据库为空，请先添加数据");
            board();
        }
    }
    class ClickEvent implements OnClickListener
    {
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case  R.id.up1:
                    if(!cursor.isFirst())
                        cursor.moveToPrevious();
                    datashow();
                    break;
                case R.id.down1:
                    if(!cursor.isLast())
                        cursor.moveToNext();
                    datashow();
                    break; //
                case R.id.numberBtn:
                    try {
                        String number = numberTxt.getText().toString();
                        Intent intent = new Intent(selectActivity.this, numberActivity.class);
                        intent.putExtra("number", number);
                        startActivity(intent);
                    }catch(Exception e) {info.setText(e.toString());}
            }
        }
    }
    /* 显示记录 */
    void datashow()
    {
        id_this = Integer.parseInt(cursor.getString(0));
        String name_this = cursor.getString(1);
        String number_this = cursor.getString(2);
        String absent_this = cursor.getString(3);
        String late_this = cursor.getString(4);
        TextView1.setText(name_this);
        TextView2.setText(number_this);
        TextView3.setText(absent_this);
        TextView4.setText(late_this);
    }
    String mostLate(){
        db = openOrCreateDatabase("Students.db",
                Context.MODE_PRIVATE, null) ;
        cursor = db.query("Student",
                null , null, null, null, null, null);
        int max = 0;
        String name = "";
        while(!cursor.isLast()){
            cursor.moveToNext();
            int late = Integer.parseInt(cursor.getString(4));
            if(late>max) {
                max = late;
                name = cursor.getString(1);
            }
        }
        cursor.moveToFirst();
        return name +"  "+ max+"次";
    }
    String mostAbsent(){
        db = openOrCreateDatabase("Students.db",
                Context.MODE_PRIVATE, null) ;
        cursor = db.query("Student",
                null , null, null, null, null, null);
        int max = 0;
        String name = "";
        while(!cursor.isLast()){
            cursor.moveToNext();
            int absent = Integer.parseInt(cursor.getString(3));
            if(absent>max) {
                max = absent;
                name = cursor.getString(1);
            }
        }
        cursor.moveToFirst();
        return name +"  "+ max+"次";
    }
    void showToast(String str){
        toast = Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
    void board() {
        Intent intent1 = new Intent();
        intent1.setAction("abc");
        Bundle bundle = new Bundle();
        bundle.putString("hello","广播信息：数据库为空，请先添加数据");
        intent1.putExtras(bundle);
        sendBroadcast(intent1);
    }
}

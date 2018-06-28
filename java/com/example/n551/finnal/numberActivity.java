package com.example.n551.finnal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class numberActivity extends AppCompatActivity {

    static TextView TextView1;
    static TextView TextView2;
    static TextView TextView3;
    static TextView TextView4;
    TextView info;

    SQLiteDatabase db;
    Cursor cursor;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        TextView1 = (TextView)findViewById(R.id.TextView1);
        TextView2 = (TextView)findViewById(R.id.TextView2);
        TextView3 = (TextView)findViewById(R.id.TextView3);
        TextView4 = (TextView)findViewById(R.id.TextView4);
        info = (TextView)findViewById(R.id.info);

        Intent intent = getIntent();
        String number = intent.getStringExtra("number");

        db = openOrCreateDatabase("Students.db",
                Context.MODE_PRIVATE, null) ;
        cursor = db.query("Student",
                null , null, null, null, null, null);
        int flag = 0;
        while(!cursor.isLast()){
            cursor.moveToNext();
            String stuNumber = cursor.getString(2);
            if (stuNumber.equals(number)) {
                flag = 1;
                break;
            }
        }
        if(flag == 0) info.setText("没有找到学号为 "+number+" 的学生");
        datashow();
    }
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
}

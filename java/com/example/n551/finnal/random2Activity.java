package com.example.n551.finnal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class random2Activity extends AppCompatActivity {

    Button absentBtn,lateBtn,skipBtn;
    TextView TextView1,TextView2,TextView3,TextView4,info;

    Cursor cursor;
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
        setContentView(R.layout.activity_random2);
        TextView1=(TextView)findViewById(R.id.TextView1);
        TextView2=(TextView)findViewById(R.id.TextView2);
        TextView3=(TextView)findViewById(R.id.TextView3);
        TextView4=(TextView)findViewById(R.id.TextView4);
        info=(TextView)findViewById(R.id.info);
        absentBtn = (Button)findViewById(R.id.absentBtn);
        absentBtn.setOnClickListener(new mClick());
        lateBtn = (Button)findViewById(R.id.lateBtn);
        lateBtn.setOnClickListener(new mClick());
        skipBtn = (Button)findViewById(R.id.skipBtn);
        skipBtn.setOnClickListener(new mClick());
        try {
            db = openOrCreateDatabase("Students.db",
                    android.content.Context.MODE_PRIVATE, null);
            cursor = db.query("Student",
                    null, null, null, null, null, null);
            int i = (int) (Math.random() * cursor.getCount());
            //        info.setText(i+"");
            cursor.moveToPosition(i);
            datashow();
        }catch (Exception e){
            absentBtn.setEnabled(false);
            lateBtn.setEnabled(false);
            skipBtn.setEnabled(false);
            showToast("数据库为空，请先添加学生信息！");
        }
    }
    class mClick implements View.OnClickListener {
        public void onClick(View v){
            switch(v.getId())
            {
                case R.id.absentBtn:{
                    absent();
                    Intent intent = new Intent(random2Activity.this,randomActivity.class);
                    startActivity(intent);
                    random2Activity.this.finish();
                    showToast("操作成功！");
                    break;
                }
                case R.id.lateBtn:{
                    late();
                    Intent intent = new Intent(random2Activity.this,randomActivity.class);
                    startActivity(intent);
                    random2Activity.this.finish();
                    showToast("操作成功！");
                    break;
                }
                case R.id.skipBtn:{
                    Intent intent = new Intent(random2Activity.this,randomActivity.class);
                    startActivity(intent);
                    random2Activity.this.finish();
                    showToast("操作成功！");
                    break; //
                }
            }
        }
    }
    void absent(){
        try {
            int absent =  Integer.parseInt(TextView3.getText().toString()) + 1;
            android.content.ContentValues values = new android.content.ContentValues();
            values.put(NAME, TextView1.getText().toString());
            values.put(NUMBER, TextView2.getText().toString());
            values.put(ABSENT, absent+"");
            values.put(LATE, TextView4.getText().toString());
            String where1 = ID + " = " + id_this;
            db.update(TABLE_NAME, values, where1, null);
            db = helper.getWritableDatabase();
            db.close();
        }
        catch (Exception e){}
//        catch (Exception e){info.setText(e.toString()); }
    }
    void late(){
        try {
            int late =  Integer.parseInt(TextView4.getText().toString()) + 1;
            android.content.ContentValues values = new android.content.ContentValues();
            values.put(NAME, TextView1.getText().toString());
            values.put(NUMBER, TextView2.getText().toString());
            values.put(ABSENT, TextView3.getText().toString());
            values.put(LATE, late+"");
            String where1 = ID + " = " + id_this;
            db.update(TABLE_NAME, values, where1, null);
            db = helper.getWritableDatabase();
            db.close();
        }
        catch (Exception e){}
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
    void showToast(String str){
        toast = Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}

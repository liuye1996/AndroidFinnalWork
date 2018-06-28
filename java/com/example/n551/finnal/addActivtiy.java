package com.example.n551.finnal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class addActivtiy extends AppCompatActivity
{
    static TextView info;
    static EditText mEditText01;
    static EditText mEditText02;
    static EditText mEditText03;
    static EditText mEditText04;
    Cursor cursor;
    Button exBtn;
    Button createBtn, openBtn, upBtn, downBtn;
    Button addBtn, updateBtn, deleteBtn;
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
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mEditText01 = (EditText)findViewById(R.id.EditText01);
        mEditText02 = (EditText)findViewById(R.id.EditText02);
        mEditText03 = (EditText)findViewById(R.id.EditText03);
        mEditText04 = (EditText)findViewById(R.id.EditText04);
//        createBtn = (Button)findViewById(R.id.createDatabase1);
//        createBtn.setOnClickListener(new ClickEvent());
//        openBtn = (Button)findViewById(R.id.openDatabase1);
//        openBtn.setOnClickListener(new ClickEvent());
        upBtn=(Button)findViewById(R.id.up1);
        upBtn.setOnClickListener(new ClickEvent());
        downBtn=(Button)findViewById(R.id.down1);
        downBtn.setOnClickListener(new ClickEvent());
        addBtn = (Button)findViewById(R.id.add1);
        addBtn.setOnClickListener(new ClickEvent());
        updateBtn = (Button)findViewById(R.id.update1);
        updateBtn.setOnClickListener(new ClickEvent());
        deleteBtn = (Button)findViewById(R.id.delete1);
        deleteBtn.setOnClickListener(new ClickEvent());
        exBtn = (Button)findViewById(R.id.exBtn);
        exBtn.setOnClickListener(new ClickEvent());

        try {
            db = openOrCreateDatabase("Students.db",
                    Context.MODE_PRIVATE, null);
            cursor = db.query("Student",
                    null, null, null, null, null, null);
            if (cursor.getCount()>0) {
                cursor.moveToNext();
                datashow();
            }
            else {
                upBtn.setEnabled(false);
                downBtn.setEnabled(false);
                updateBtn.setEnabled(false);
                deleteBtn.setEnabled(false);
                info.setText("数据库为空，先添加学生信息");
            }
        }catch(Exception e){
            helper = new DBConnection(addActivtiy.this);
            SQLiteDatabase db = helper.getWritableDatabase();
//          createData();

        }
    }
    class ClickEvent implements OnClickListener
    {
        public void onClick(View v)
        {
            switch(v.getId())
            {
//                case R.id.createDatabase1:
//                    helper = new DBConnection(addActivtiy.this);
//                    SQLiteDatabase db = helper.getWritableDatabase();
//                    break;
//                case R.id.openDatabase1:
//                    db = openOrCreateDatabase("Students.db",
//                            Context.MODE_PRIVATE, null) ;
//                    cursor = db.query("Student",
//                            null , null, null, null, null, null);
//                    cursor.moveToNext();
////                    upBtn.setClickable(true);
////                    downBtn.setClickable(true);
////                    deleteBtn.setClickable(true);
////                    updateBtn.setClickable(true);
//                    datashow();
//                    break;
                case R.id.exBtn:
                    exAdd();
                    cursor = db.query("Student",
                            null, null, null, null, null, null);
                    cursor.moveToNext();
                    datashow();
                    break;
                case R.id.up1:
                    if(!cursor.isFirst())
                        cursor.moveToPrevious();
                    datashow();
                    break;
                case R.id.down1:
                    if(!cursor.isLast())
                        cursor.moveToNext();
                    datashow();
                    break; //
                case R.id.add1:
                    add();
//                    onCreate(savedInstanceState);
                    cursor = db.query("Student",
                            null, null, null, null, null, null);
                    try{
                        if (cursor.getCount()>0){
                            cursor.moveToNext();
                            datashow();
                        }
                        else{
                            upBtn.setEnabled(false);
                            downBtn.setEnabled(false);
                            updateBtn.setEnabled(false);
                            deleteBtn.setEnabled(false);
                        }
                    }catch (Exception e){
//                        showToast(e.toString());
                    }
                    break;
                case R.id.update1:
                    update();
//                    onCreate(savedInstanceState);
                    cursor = db.query("Student",
                            null, null, null, null, null, null);
                    cursor.moveToNext();
                    datashow();
                    showToast("数据更新成功！");
                    break;
                case  R.id.delete1:
                    delete();
//                    onCreate(savedInstanceState);
                    cursor = db.query("Student",
                            null, null, null, null, null, null);
                    try{
                        if (cursor.getCount()>0){
                            cursor.moveToNext();
                            datashow();
                        }
                        else{
                            mEditText01.setText("");
                            mEditText02.setText("");
                            upBtn.setEnabled(false);
                            downBtn.setEnabled(false);
                            updateBtn.setEnabled(false);
                            deleteBtn.setEnabled(false);
                            info.setText("数据库为空，请添加学生信息");
                        }
                    }catch (Exception e){
//                        showToast(e.toString());
                    }
                    showToast("数据删除成功！");
                    break;
            }
        }
    }
    /* 显示记录 */
    void datashow()
    {
        if (cursor.getCount()>0) {
            id_this = Integer.parseInt(cursor.getString(0));
            String name_this = cursor.getString(1);
            String number_this = cursor.getString(2);
            String absent_this = cursor.getString(3);
            String late_this = cursor.getString(4);
            mEditText01.setText(name_this);
            mEditText02.setText(number_this);
            mEditText03.setText(absent_this);
            mEditText04.setText(late_this);
        }
        else{
            upBtn.setEnabled(false);
            downBtn.setEnabled(false);
            updateBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
            info.setText("数据库为空，先添加学生信息");
        }
    }
    /* 添加记录 */
    void add()
    {
        if(mEditText01.getText().toString().length()>0&&mEditText02.getText().toString().length()>0){
            try{
                db = openOrCreateDatabase("Students.db",
                        Context.MODE_PRIVATE, null) ;
                ContentValues values1 = new ContentValues();
                values1.put(NAME, addActivtiy.mEditText01.getText().toString());
                values1.put(NUMBER, addActivtiy.mEditText02.getText().toString());
                values1.put(ABSENT, addActivtiy.mEditText03.getText().toString());
                values1.put(LATE, addActivtiy.mEditText04.getText().toString());
                db.insert(TABLE_NAME, null, values1);
//                db.close();
                showToast("数据添加成功！");
                upBtn.setEnabled(true);
                downBtn.setEnabled(true);
                updateBtn.setEnabled(true);
                deleteBtn.setEnabled(true);
            }
            catch (Exception e){ }
        }
        else {showToast("名字或学号不能为空！"); }

    }
    /* 修改记录 */
    void update()
    {
        try {
            ContentValues values = new ContentValues();
            values.put(NAME, addActivtiy.mEditText01.getText().toString());
            values.put(NUMBER, addActivtiy.mEditText02.getText().toString());
            values.put(ABSENT, addActivtiy.mEditText03.getText().toString());
            values.put(LATE, addActivtiy.mEditText04.getText().toString());
            String where1 = ID + " = " + id_this;
            db.update(TABLE_NAME, values, where1, null);
            db = helper.getWritableDatabase();
//            db.close();
        }
        catch (Exception e){
//            showToast("uqdata错误"+e.toString());
        }
    }
    /*  删除记录 */
    void delete()
    {
        try{
            String where = ID + " = " + id_this;
            db.delete(TABLE_NAME, where ,null);
            db = helper.getWritableDatabase();
//            db.close();
        }
        catch (Exception e){
//            showToast("delete错误"+e.toString());
        }
    }

    void showToast(String str){
        toast = Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
    void createData(){
        String TABLE_NAME = "Student";          //数据表名
        String ID = "_id";                    //ID
        String NAME = "name";                    //学生姓名
        String NUMBER = "number";           //学号
        String ABSENT = "absent";            //旷课次数
        String LATE = "late";            //旷课次数

        SQLiteDatabase db;
        String db_name = "Students.db";
        String sqlStr = "CREATE TABLE " + TABLE_NAME + " ("
                + ID  + " INTEGER primary key autoincrement, "
                + NAME + " text not null, "
                + NUMBER + " text not null, "
                + ABSENT + " text not null, "
                + LATE + " text not null "+ ");";
        int mode = Context.MODE_PRIVATE;
        db = this.openOrCreateDatabase(db_name,mode,null);
        db.execSQL(sqlStr);
    }
    void exAdd(){
        String str1 = "INSERT INTO Student VALUES(null,'张一','1506410101','0','0');";
        String str2 = "INSERT INTO Student VALUES(null,'张二','1506410102','0','0');";
        String str3 = "INSERT INTO Student VALUES(null,'张三','1506410103','2','0');";
        String str4 = "INSERT INTO Student VALUES(null,'张四','1506410104','1','0');";
        String str5 = "INSERT INTO Student VALUES(null,'张五','1506410105','0','3');";
        String str6 = "INSERT INTO Student VALUES(null,'张六','1506410106','0','0');";

        db = openOrCreateDatabase("Students.db",
                Context.MODE_PRIVATE, null);
        db.execSQL(str1);
        db.execSQL(str2);
        db.execSQL(str3);
        db.execSQL(str4);
        db.execSQL(str5);
        db.execSQL(str6);
//        db.close();
        upBtn.setEnabled(true);
        downBtn.setEnabled(true);
        updateBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
    }
}
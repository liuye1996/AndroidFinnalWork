package com.example.n551.finnal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnection extends SQLiteOpenHelper
{
    static final String Database_name = "Students.db";
    static final int Database_Version = 1;
    SQLiteDatabase db;
    public int id_this;
    Cursor cursor;
    //定义数据库名称及结构
    static String TABLE_NAME = "Student";          //数据表名
    static String ID = "_id";                    //ID
    static String NAME = "name";                    //学生姓名
    static String NUMBER = "number";           //学号
    static String ABSENT = "absent";            //旷课次数
    static String LATE = "late";            //旷课次数

        DBConnection(Context ctx)
        {
        super(ctx, Database_name, null, Database_Version);
        }
        public void onCreate(SQLiteDatabase database)
        {
          	  String sql = "CREATE TABLE " + TABLE_NAME + " ("
          		 + ID  + " INTEGER primary key autoincrement, "
          		 + NAME + " text not null, "
          		 + NUMBER + " text not null, "
          		 + ABSENT + " text not null, "
                 + LATE + " text not null "+ ");";
        database.execSQL(sql);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {	             }
}
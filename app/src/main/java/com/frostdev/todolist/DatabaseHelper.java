package com.frostdev.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Selection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Todo.db";
    public static final String TABLE_NAME = "todo_table";
    public static final int DATABASE_VERSION =1;
    public static final String COL_1="ID";
    public static final String COL_2="NAMA";
    public static final String COL_3="STATUS";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table todo_table(id integer primary key autoincrement," + "nama text," + "status integer)");

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, "Belajar");
        contentValues.put(COL_3, 0);

        db.insert(TABLE_NAME, null, contentValues);
    }
    //coment
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<ToDo> getAllData(){
        List<ToDo> ToDos = new ArrayList<>();
        String SelectQuery = "SELECT * FROM TODO_TABLE" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SelectQuery,null);
        if (cursor.moveToFirst()){
            do {
                ToDo toDo = new ToDo();
                toDo.setmTodo(cursor.getString(0));
                toDo.setNamaToDo(cursor.getString(1));
                toDo.setStatusTodo(cursor.getString(2));

                ToDos.add(toDo);
            } while (cursor.moveToNext());
        }
        db.close();
        return ToDos;
    }

    public boolean InserData(String name,String status){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,status);

        Long reslut =db.insert(TABLE_NAME,null,contentValues);
        if (reslut == -1){
            return false;
        }else {
            return true;
        }
    }

    //Metod Mengupdate Data
    public boolean updateDta(String nama, String Status, String id){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, id);
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, Status);

        long result = db.update(TABLE_NAME,contentValues,"ID-?",new  String[]{id});

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    //Metode menghaps data
    public int Delet(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID-?",new String[]{id});

    }

    //Method mencari data
    public List<ToDo>SearchData(String keyWord){
        List<ToDo>Todos = new ArrayList<>();
        String SelectQuery = "SELECT*FROM TODO_TABLE WHERE NAMA LIKE?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SelectQuery,new  String[]{"%"+keyWord+"%"});
        if (cursor.moveToFirst()){
            do {
                ToDo toDo = new ToDo();
                toDo.setmTodo(cursor.getString(0));
                toDo.setNamaToDo(cursor.getString(1));
                toDo.setStatusTodo(cursor.getString(2));

                Todos.add(toDo);
            }while (cursor.moveToNext());
        }
        db.close();
        return Todos;
    }

}

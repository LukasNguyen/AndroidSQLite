package com.example.student.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.student.model.Book;

import java.util.ArrayList;

/**
 * Created by Student on 10/18/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "BookDB.db";
    private static final String TABLE_NAME = "Books";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME +"  (id integer primary key AUTOINCREMENT,name text,price double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Books");
        onCreate(db);
    }

    public boolean insertBook(Book b){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",b.getName());
        contentValues.put("price",b.getPrice());

        if(db.insert(TABLE_NAME,null,contentValues)!= -1)
            return true;
        return false;
    }

    public boolean updateBook(Book b){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",b.getName());
        contentValues.put("price",b.getPrice());

        db.update(TABLE_NAME,contentValues,"id = ?",new String[]{String.valueOf(b.getId())});
        return true;
    }

    public boolean deleteBook(int id)
    {
        SQLiteDatabase db = getWritableDatabase();

        if(db.delete(TABLE_NAME,"id = ?",new String[]{String.valueOf(id)}) == 1)
            return true;
        return false;
    }

    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> lst = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor res = db.rawQuery("select * from Books",null);

        res.moveToFirst();

        while (!res.isAfterLast()){
            Book b = new Book();
            b.setId(res.getInt(res.getColumnIndex("id")));
            b.setName(res.getString(res.getColumnIndex("name")));
            b.setPrice(res.getDouble(res.getColumnIndex("price")));
            res.moveToNext();
            lst.add(b);
        }
        return lst;
    }

}

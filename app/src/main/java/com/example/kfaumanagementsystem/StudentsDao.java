package com.example.kfaumanagementsystem;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class StudentsDAO {
    public ArrayList<Students> allStudents(Database database){
        ArrayList<Students> studentsArrayList = new ArrayList<>();
        SQLiteDatabase db = database.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM STUDENTS", null);

        while(cursor.moveToNext()){
            Students s = new Students(cursor.getInt(cursor.getColumnIndex("SCHOOL_NUMBER"))
                    , cursor.getString(cursor.getColumnIndex("NAME_SURNAME"))
                    , cursor.getInt(cursor.getColumnIndex("PRICE")));

            studentsArrayList.add(s);
        }

        db.close();

        return studentsArrayList;
    }

    public void studentDelete(Database database, int id){
        SQLiteDatabase db = database.getWritableDatabase();

        db.delete("STUDENTS", "SCHOOL_NUMBER=?", new String[]{String.valueOf(id)});

        db.close();
    }

    public void studentAdd(Database database, String name, int price){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("NAME_SURNAME", name);
        values.put("PRICE", price);

        db.insertOrThrow("STUDENTS", null, values);

        db.close();
    }

    public void studentUpdate(Database database, int id, String name, int price){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("NAME_SURNAME", name);
        values.put("PRICE", price);

        db.update("STUDENTS", values, "SCHOOL_NUMBER=?", new String[]{String.valueOf(id)});

        db.close();
    }
}

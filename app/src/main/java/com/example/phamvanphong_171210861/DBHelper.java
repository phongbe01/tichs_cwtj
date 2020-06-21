package com.example.phamvanphong_171210861;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBName = "mydb.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "Ticket";

    private static final String ID = "_id";
    private static final String GADEN = "GaDen";
    private static final String GADI = "GaDi";
    private static final String GIATIEN = "GiaTien";
    private static final String THELOAI = "TheLoai";

    private SQLiteDatabase myDB;

    public DBHelper (@Nullable Context context){
        super(context, DBName, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTable = "CREATE TABLE " + TABLE_NAME + "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GADEN + " TEXT NOT NULL, " +
                GADI + " TEXT NOT NULL, " +
                GIATIEN + " LONG NOT NULL, " +
                THELOAI + " INTEGER NOT NULL "  + ")";
        db.execSQL(queryTable);

        //add 6 item
        String addItemOne = "INSERT INTO Ticket( GaDen, GaDi, GiaTien, TheLoai) VALUES ( 'Vinh', 'Nam Định', 351500, 1 )";
        db.execSQL(addItemOne);
        String addItemTwo = "INSERT INTO Ticket( GaDen, GaDi, GiaTien, TheLoai) VALUES ( 'Nam Định', 'Thanh Hóa', 237500, 1 )";
        db.execSQL(addItemTwo);
        String addItemThree = "INSERT INTO Ticket( GaDen, GaDi, GiaTien, TheLoai) VALUES ( 'Thanh Hóa', 'Hà Nội', 170000, 0 )";
        db.execSQL(addItemThree);
        String addItemFour = "INSERT INTO Ticket( GaDen, GaDi, GiaTien, TheLoai) VALUES ( 'Hà Nội', 'Thanh Hóa', 170000, 0 )";
        db.execSQL(addItemFour);
        String addItemFive = "INSERT INTO Ticket( GaDen, GaDi, GiaTien, TheLoai) VALUES ( 'Hà Nội', 'Nam Định', 131100, 1 )";
        db.execSQL(addItemFive);
    }

    public void openDB(){
        myDB = getWritableDatabase();
    }

    public void closeDB(){
        if(myDB!=null && myDB.isOpen()){
            myDB.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getTickets()
    {
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + GIATIEN + " DESC ";
        return myDB.rawQuery(query,null);
    }

    public ArrayList<Ticket> getAll()
    {
        ArrayList<Ticket> tickets = new ArrayList<>();
        Cursor cursor = getTickets();
        if (cursor != null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                tickets.add(new Ticket(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getLong(3), cursor.getInt(4)));
                cursor.moveToNext();
            }
        }
        return tickets;
    }

    public long Update(int id, Ticket ticket)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(GADEN, ticket.getGaDen());
        contentValues.put(GADI, ticket.getGaDi());
        contentValues.put(GIATIEN, ticket.getDonGia());
        contentValues.put(THELOAI, ticket.isTheLoai());
        String where = ID + " = " + id;
        return myDB.update(TABLE_NAME, contentValues, where, null);

    }
}

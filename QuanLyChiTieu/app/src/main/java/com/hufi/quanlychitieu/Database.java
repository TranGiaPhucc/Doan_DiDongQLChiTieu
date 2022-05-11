package com.hufi.quanlychitieu;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.util.ArrayList;

public class Database {
    Context context;
    private String dbName = "NguoiDung.db";
    private String dbTable = "NguoiDung";

    public Database(Context context)
    {
        this.context = context;
    }

    public SQLiteDatabase openDB() {
        //return SQLiteDatabase.openOrCreateDatabase(dbName,null);
        return context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }
    /*
    private SQLiteDatabase openExternalDB() {
        String path = Environment.getExternalStorageDirectory().getPath()+"/"+dbName;
        return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
    }*/

    public void closeDB(SQLiteDatabase db) {
        db.close();
    }

    public void createTable() {
        SQLiteDatabase db = openDB();
        String sql = "create table if not exists NguoiDung(" +
                "username TEXT PRIMARY KEY," +
                "password TEXT ) ";
        db.execSQL(sql);
        closeDB(db);
    }

    public ArrayList<NguoiDung> getNguoiDungAll()	{
        SQLiteDatabase db =	openDB();
        ArrayList<NguoiDung>	arr =	new	ArrayList<>();
        String	sql =	"select	*	from	NguoiDung";
        Cursor csr =	db.rawQuery(sql,	null);
        if	(csr !=	null)	{
            if	(csr.moveToFirst())	{
                do	{
                    String	username	=	csr.getString(0);
                    String	password	=	csr.getString(1);
                    arr.add(new	NguoiDung(username, password));
                }	while	(csr.moveToNext());
            } }
        closeDB(db);
        return	arr;
    }


    public	NguoiDung getNguoiDung(String cUsername) {
        String[] fields = {"username", "password"};
        String[] ids = {cUsername};
        SQLiteDatabase db = openDB();
        Cursor cursor = db.query(dbTable, fields, "username	=	?", ids, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        String username = cursor.getString(0);
        String password = cursor.getString(1);
        closeDB(db);
        return new NguoiDung(username, password);
    }

    public boolean insert(NguoiDung nguoidung) {
        boolean flag = false;
        SQLiteDatabase db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("username", nguoidung.getUsername());
        cv.put("password", nguoidung.getPassword());
        flag = db.insert(dbTable, null, cv) > 0;
        closeDB(db);
        return flag;
    }

    public	boolean	update(NguoiDung	nd)	{
        boolean flag = false;
        SQLiteDatabase	db	=	openDB();
        ContentValues	cv	=	new	ContentValues();
        cv.put("username",	 nd.username);
        cv.put("password",	 nd.password);
        String[]	id	=	{nd.username};
        flag = db.update(dbTable, cv, "username = ?", id) > 0;
        closeDB(db);
        return flag;
    }

    public boolean checkUserExist(String username, String password){
        String[] columns = {"username"};

        SQLiteDatabase db = openDB();

        String selection = "username = ? and password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(dbTable, columns, selection, selectionArgs, null, null, null);

        int count = cursor.getCount();

        cursor.close();
        db.close();

        if(count > 0){
            return true;
        } else {
            return false;
        }
    }
}

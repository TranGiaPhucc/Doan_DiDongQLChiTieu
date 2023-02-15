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
    private String dbTableFriend = "BanBe";
    private String dbTableChiTietChiTieu = "ChiTietChiTieu";
    private String dbTableChiTietNguoiChiTieu = "ChiTietNguoiChiTieu";

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
                "password TEXT, " +
                "name TEXT, " +
                "sdt TEXT ) ";
        db.execSQL(sql);
        String sql1 = "create table if not exists BanBe(" +
                "id integer PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT," +
                "usernamefriend TEXT," +
                "checkcustom2 integer," +
                "tongtien integer) ";
        db.execSQL(sql1);
        String sql2 = "create table if not exists ChiTietChiTieu(" +
                "tenhoatdong TEXT PRIMARY KEY," +
                "ngay TEXT," +
                "noidung TEXT," +
                "tongtien integer)";
        db.execSQL(sql2);
        String sql3 = "create table if not exists ChiTietNguoiChiTieu(" +
                "id integer PRIMARY KEY AUTOINCREMENT," +
                "tenhoatdong TEXT," +
                "username TEXT," +
                "tien integer)";
        db.execSQL(sql3);
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
                    String	name	=	csr.getString(2);
                    String	sdt	=	csr.getString(3);
                    arr.add(new	NguoiDung(username, password, name, sdt));
                }	while	(csr.moveToNext());
            } }
        closeDB(db);
        return	arr;
    }

    /*public ArrayList<BanBe> getBanBeAll(String cUsername)	{
        String[] fields = {"id", "username", "usernamefriend"};
        String[] ids = {cUsername};
        ArrayList<BanBe>	arr =	new	ArrayList<>();
        SQLiteDatabase db = openDB();
        Cursor csr = db.query(dbTableFriend, fields, "username	=	?", ids, null, null, null, null);
        if	(csr !=	null)	{
            if	(csr.moveToFirst())	{
                do	{
                    int id = csr.getInt(0);
                    String	username	=	csr.getString(1);
                    String	usernamefriend	=	csr.getString(2);
                    arr.add(new	BanBe(id, username, usernamefriend));
                }	while	(csr.moveToNext());
            } }
        closeDB(db);
        return	arr;
    }*/

    public ArrayList<NguoiDung> getBanBeAll(String cUsername)	{
        String[] fields = {"id", "username", "usernamefriend"};
        String[] ids = {cUsername};
        ArrayList<NguoiDung>	arr =	new	ArrayList<>();
        SQLiteDatabase db = openDB();
        Cursor csr = db.query(dbTableFriend, fields, "username	=	?", ids, null, null, null, null);
        if	(csr !=	null)	{
            if	(csr.moveToFirst())	{
                do	{
                    String	usernamefriend	=	csr.getString(2);
                    NguoiDung nd = getNguoiDung(usernamefriend);
                    arr.add(nd);
                }	while	(csr.moveToNext());
            }
        }
        closeDB(db);
        return	arr;
    }

    public ArrayList<NguoiDung> getBanBeDanhSachAll(String cUsername)	{
        String[] fields = {"id", "username", "usernamefriend"};
        String[] ids = {cUsername};
        ArrayList<NguoiDung>	arr =	new	ArrayList<>();
        SQLiteDatabase db = openDB();
        Cursor csr = db.query(dbTableFriend, fields, "username	=	?", ids, null, null, null, null);
        if	(csr !=	null)	{
            if	(csr.moveToFirst())	{
                do	{
                    String	usernamefriend	=	csr.getString(2);
                    if (usernamefriend.equals(cUsername) == false)
                    {
                        NguoiDung nd = getNguoiDung(usernamefriend);
                        arr.add(nd);
                    }
                }	while	(csr.moveToNext());
            }
        }
        closeDB(db);
        return	arr;
    }

    public ArrayList<NguoiDung> getBanBeCheckCustom2All(String cUsername)	{
        String[] fields = {"id", "username", "usernamefriend", "checkcustom2"};
        String[] ids = {cUsername};
        ArrayList<NguoiDung>	arr =	new	ArrayList<>();
        SQLiteDatabase db = openDB();
        Cursor csr = db.query(dbTableFriend, fields, "username	=	?", ids, null, null, null, null);
        if	(csr !=	null)	{
            if	(csr.moveToFirst())	{
                do	{
                    String	username	=	csr.getString(1);
                    String	usernamefriend	=	csr.getString(2);
                    int checkcustom2 = csr.getInt(3);

                    //NguoiDung nd = getNguoiDung(usernamefriend);
                    //arr.add(nd);
                    //BanBe bb = new BanBe(username, usernamefriend, checkcustom2);

                    if (checkcustom2 == 1)
                    {
                        NguoiDung nd = getNguoiDung(usernamefriend);
                        arr.add(nd);
                    }

                }	while	(csr.moveToNext());
            } }
        closeDB(db);
        return	arr;
    }

    public ArrayList<ChiTietChiTieu> getChiTietChiTieuAll(String cUsername)	{
        String[] fields = {"tenhoatdong", "ngay", "noidung", "tongtien"};
        String[] fieldsNguoiChiTieu = {"id", "tenhoatdong", "username", "tien"};
        String[] ids = {cUsername};
        ArrayList<ChiTietChiTieu>	arr =	new	ArrayList<>();
        SQLiteDatabase db = openDB();
        Cursor csr = db.query(dbTableChiTietNguoiChiTieu, fieldsNguoiChiTieu, "username	=	?", ids, null, null, null, null);
        if	(csr !=	null)	{
            if	(csr.moveToFirst())	{
                do	{
                    String tenhoatdong = csr.getString(1);
                    String username	=	csr.getString(2);

                    if (username.equals(cUsername) == true)
                    {
                        Cursor csr1 = db.query(dbTableChiTietChiTieu, fields, "tenhoatdong	=	?", new String[]{tenhoatdong}, null, null, null, null);
                        if (csr1 != null)
                            csr1.moveToFirst();
                        String thd = csr1.getString(0);
                        String ngay = csr1.getString(1);
                        String noidung = csr1.getString(2);
                        int tongtien = csr1.getInt(3);
                        ChiTietChiTieu ctct = new ChiTietChiTieu(thd, ngay, noidung, tongtien);
                        arr.add(ctct);
                    }
                }	while	(csr.moveToNext());
            }
        }
        closeDB(db);
        return	arr;
    }

    public ArrayList<ChiTietNguoiChiTieu> getChiTietNguoiChiTieuAll(String cTenHoatDong)	{
        String[] fieldsNguoiChiTieu = {"id", "tenhoatdong", "username", "tien"};
        String[] ids = {cTenHoatDong};
        ArrayList<ChiTietNguoiChiTieu>	arr =	new	ArrayList<>();
        SQLiteDatabase db = openDB();
        Cursor csr = db.query(dbTableChiTietNguoiChiTieu, fieldsNguoiChiTieu, "tenhoatdong	=	?", ids, null, null, null, null);
        if	(csr !=	null)	{
            if	(csr.moveToFirst())	{
                do	{
                    String thd = csr.getString(1);
                    String username = csr.getString(2);
                    int tien = csr.getInt(3);
                    ChiTietNguoiChiTieu ctnct = new ChiTietNguoiChiTieu(thd, username, tien);
                    arr.add(ctnct);
                }	while	(csr.moveToNext());
            }
        }
        closeDB(db);
        return	arr;
    }

    public	NguoiDung getNguoiDung(String cUsername) {
        String[] fields = {"username", "password", "name", "sdt"};
        String[] ids = {cUsername};
        SQLiteDatabase db = openDB();
        Cursor cursor = db.query(dbTable, fields, "username	=	?", ids, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        String username = cursor.getString(0);
        String password = cursor.getString(1);
        String name = cursor.getString(2);
        String sdt = cursor.getString(3);
        closeDB(db);
        return new NguoiDung(username, password, name, sdt);
    }

    public	BanBe getBanBe(BanBe bb) {
        String[] fields = {"id", "username", "usernamefriend", "checkcustom2"};
        //String[] ids = {cUsername};
        SQLiteDatabase db = openDB();
        Cursor cursor = db.query(dbTableFriend, fields, "username	=	? and usernamefriend = ?", new String[]{bb.getUsername(), bb.getUsernamefriend()}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        String username = cursor.getString(1);
        String usernamefriend = cursor.getString(2);
        int checkCustom2 = cursor.getInt(3);
        closeDB(db);
        return new BanBe(username, usernamefriend, checkCustom2);
    }

    public	BanBe getBanBeTongTien(BanBe bb) {
        String[] fields = {"id", "username", "usernamefriend", "checkcustom2", "tongtien"};
        //String[] ids = {cUsername};
        SQLiteDatabase db = openDB();
        Cursor cursor = db.query(dbTableFriend, fields, "username	=	? and usernamefriend = ?", new String[]{bb.getUsername(), bb.getUsernamefriend()}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        String username = cursor.getString(1);
        String usernamefriend = cursor.getString(2);
        int checkCustom2 = cursor.getInt(3);
        int tongtien = cursor.getInt(4);
        closeDB(db);
        return new BanBe(username, usernamefriend, checkCustom2, tongtien);
    }

    public boolean insert(NguoiDung nguoidung) {
        boolean flag = false;
        SQLiteDatabase db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("username", nguoidung.getUsername());
        cv.put("password", nguoidung.getPassword());
        cv.put("name", nguoidung.getName());
        cv.put("sdt", nguoidung.getSdt());
        flag = db.insert(dbTable, null, cv) > 0;
        closeDB(db);
        return flag;
    }

    public boolean insertBanBe(BanBe banbe) {
        boolean flag = false;
        SQLiteDatabase db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("username", banbe.getUsername());
        cv.put("usernamefriend", banbe.getUsernamefriend());
        cv.put("checkcustom2", banbe.getCheckChiTietCustom2());
        cv.put("tongtien", banbe.getTongTien());
        flag = db.insert(dbTableFriend, null, cv) > 0;
        closeDB(db);
        return flag;
    }

    public boolean insertChiTietChiTieu(ChiTietChiTieu ctct) {
        boolean flag = false;
        SQLiteDatabase db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("tenhoatdong", ctct.getTenHoatDong());
        cv.put("ngay", ctct.getNgay());
        cv.put("noidung", ctct.getNoiDung());
        cv.put("tongtien", ctct.getTongTien());
        flag = db.insert(dbTableChiTietChiTieu, null, cv) > 0;
        closeDB(db);
        return flag;
    }

    public boolean insertChiTietNguoiChiTieu(ChiTietNguoiChiTieu ctnct) {
        boolean flag = false;
        SQLiteDatabase db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("tenhoatdong", ctnct.getTenHoatDong());
        cv.put("username", ctnct.getUsername());
        cv.put("tien", ctnct.getTien());
        flag = db.insert(dbTableChiTietNguoiChiTieu, null, cv) > 0;
        closeDB(db);
        return flag;
    }

    public	boolean	update(NguoiDung	nd)	{
        boolean flag = false;
        SQLiteDatabase	db	=	openDB();
        ContentValues	cv	=	new	ContentValues();
        cv.put("username",	 nd.username);
        cv.put("password",	 nd.password);
        cv.put("name",	 nd.name);
        cv.put("sdt",	 nd.sdt);
        String[]	id	=	{nd.username};
        flag = db.update(dbTable, cv, "username = ?", id) > 0;
        closeDB(db);
        return flag;
    }

    public boolean deleteBanBe(BanBe bb) {
        boolean flag = false;
        //String[] id = {String.valueOf(bb.id)};
        SQLiteDatabase db = openDB();
        //flag = db.delete(dbTableFriend, "id = ?", id) > 0;
        flag = db.delete(dbTableFriend,"username = ? and usernamefriend = ?", new String[]{bb.getUsername(), bb.getUsernamefriend()}) > 0;
        closeDB(db);
        return flag;
    }

    public	boolean	updateBanBe(BanBe bb)	{
        boolean flag = false;
        SQLiteDatabase	db	=	openDB();
        ContentValues	cv	=	new	ContentValues();
        cv.put("username",	 bb.username);
        cv.put("usernamefriend",	 bb.usernamefriend);
        cv.put("checkcustom2",	 bb.checkChiTietCustom2);
        cv.put("tongtien", bb.tongTien);
        //String[]	id	=	{bb.username};
        flag = db.update(dbTableFriend, cv, "username = ? and usernamefriend = ?", new String[]{bb.getUsername(), bb.getUsernamefriend()}) > 0;
        closeDB(db);
        return flag;
    }

    public boolean checkFriendExist(String username, String usernamefriend){
        String[] columns = {"username"};

        SQLiteDatabase db = openDB();

        String selection = "username = ? and usernamefriend = ?";
        String[] selectionArgs = {username, usernamefriend};

        Cursor cursor = db.query(dbTableFriend, columns, selection, selectionArgs, null, null, null);

        int count = cursor.getCount();

        cursor.close();
        db.close();

        if(count > 0){
            return true;
        } else {
            return false;
        }
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

    public boolean checkUserNameExist(String username){
        String[] columns = {"username"};

        SQLiteDatabase db = openDB();

        String selection = "username = ?";
        String[] selectionArgs = {username};

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

    public boolean checkChiTietChiTieuExist(String tenhoatdong){
        String[] columns = {"tenhoatdong"};

        SQLiteDatabase db = openDB();

        String selection = "tenhoatdong = ?";
        String[] selectionArgs = {tenhoatdong};

        Cursor cursor = db.query(dbTableChiTietChiTieu, columns, selection, selectionArgs, null, null, null);

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

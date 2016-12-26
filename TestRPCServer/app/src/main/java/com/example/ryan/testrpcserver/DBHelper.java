package com.example.ryan.testrpcserver;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    private final static int DB_VERSION = 1;
    private final static String DB_NAME = "appdata";

    public final static String TBL_CONTENT = "content";


    public DBHelper(Context context) {
        this(context, DB_NAME, DB_VERSION);
    }

    private DBHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    private DBHelper(Context context, String name, CursorFactory factory,
                     int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "
                + TBL_CONTENT
                + " (_id integer primary key autoincrement,name text,value text);");
    }

    /**
     * 判断某张表是否存在
     *
     * @param tableName 表名
     * @return
     */
    public boolean tabbleIsExist(String tableName) {
        boolean result = false;
        if (tableName == null) {
            return false;
        }
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getReadableDatabase();
            String sql = "select count(*) as c from Sqlite_master  where type ='table' and name ='"
                    + tableName.trim() + "' ";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		// if (tabbleIsExist(TBL_USER))
//		// db.execSQL("DROP TABLE " + TBL_USER + ";");
//		if (tabbleIsExist(TBL_UPLOAD))
//			db.execSQL("DROP TABLE " + TBL_UPLOAD + ";");
        if (tabbleIsExist(TBL_CONTENT))
            db.execSQL("DROP TABLE " + TBL_CONTENT + ";");
        onCreate(db);
    }

}

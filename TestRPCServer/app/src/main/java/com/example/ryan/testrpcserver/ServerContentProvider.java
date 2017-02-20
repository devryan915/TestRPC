package com.example.ryan.testrpcserver;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ServerContentProvider extends ContentProvider {


    private static final String AUTHORITY = "com.example.ryan.testrpcserver.servercontentprovider";
    private static final int SINGLE_WORD = 1;
    private static final int PREFIX_WORDS = 2;
    public static final String DATABASE_PATH = android.os.Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/";
    public static final String DATABASE_FILENAME = "contentprovider.db";
    private SQLiteDatabase database;
    private static UriMatcher uriMatcher;
    static {
        //  添加访问ContentProvider的Uri
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "single", SINGLE_WORD);
        uriMatcher.addURI(AUTHORITY, "prefix/*", PREFIX_WORDS);
    }

    public ServerContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        database = new DBHelper(getContext()).getWritableDatabase();
        //插入数据SQL语句
        String sql = "insert into content(name,value) values('test1','value1')";
        //执行SQL语句
        database.execSQL(sql);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case SINGLE_WORD: {
                cursor = database.query("content", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
            case PREFIX_WORDS: {
                break;
            }
            default: {
                break;
            }
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

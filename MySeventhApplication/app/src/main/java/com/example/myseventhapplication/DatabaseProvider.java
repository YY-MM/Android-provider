package com.example.myseventhapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/*public class DatabaseProvider extends ContentProvider {
    public static final int TABLE_DIR = 0;
    public static final int TABLE_ITEM = 1;
    private MyDatabaseHelper dbHelper;
    private static UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.myseventhapplication.DatabaseProvider", "p_number", TABLE_DIR);
        uriMatcher.addURI("com.example.myseventhapplication.DatabaseProvider", "p_number/#", TABLE_ITEM);
    }

    public DatabaseProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch(uriMatcher.match(uri)){
            case TABLE_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.myseventhapplication.DatabaseProvider.p_number";
            case TABLE_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.myseventhapplication.DatabaseProvider.p_number";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case TABLE_DIR:
            case TABLE_ITEM:
                long newContentId = db.insert("p_number",null,values);
                uriReturn = Uri.parse("content://com.example.myseventhapplication.DatabaseProvider/p_number/"+newContentId);
                break;
            default:
                break;
        }
        return  uriReturn;
    }

    @Override
    public boolean onCreate() {
        dbHelper =new MyDatabaseHelper(getContext(),"PhoneNumber.db",null,2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=null;
        switch (uriMatcher.match(uri)) {
            case TABLE_DIR:
                cursor=db.query("p_number",new String[]{"name,number,sex"},null,
                        null,null,null,null);
                break;
            case TABLE_ITEM:
                String name=uri.getPathSegments().get(1);
                cursor=db.query("p_number",new String[]{"name,number,sex"},"name=?",
                        new String[] {name},null,null,null);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)){
            case TABLE_DIR:
                updatedRows =db.update("p_number",values,selection,selectionArgs);
                break;
            case TABLE_ITEM:
                String ContentId = uri.getPathSegments().get(1);
                updatedRows = db.update("p_number",values,"id=?",new String[] {ContentId});
                break;
        }
        return updatedRows;
    }
}*/


public class DatabaseProvider extends ContentProvider {
    public static final int CONTENT_DIR = 0;
    public static final int CONTENT_ITEM = 1;
    public static final String AUTHORITY = "com.example.myseventhapplication.provider";
    public static UriMatcher uriMatcher;
    private MyDatabaseHelper dbHelper;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"p_number",CONTENT_DIR);
        uriMatcher.addURI(AUTHORITY,"p_number/#",CONTENT_ITEM);
    }

    public DatabaseProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /*@Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleteRows = 0;//删除了多少行的数据
        switch (uriMatcher.match(uri)){
            case CONTENT_DIR:
                deleteRows = db.delete("p_number",selection,selectionArgs);
                break;
            case CONTENT_ITEM:
                String ContentId = uri.getPathSegments().get(1);
                deleteRows = db.delete("p_number","id=?",new String[]{ContentId});
                break;
            default:
                break;
        }
        return deleteRows;
    }*/

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case CONTENT_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.myseventhapplication.provider.p_number";
            case CONTENT_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.myseventhapplication.provider.p_number";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case CONTENT_DIR:
            case CONTENT_ITEM:
                long newContentId = db.insert("p_number",null,values);
                uriReturn = Uri.parse("content://" +AUTHORITY+ "/p_number/"+newContentId);
                break;
            default:
                break;
        }
        return  uriReturn;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext(),"PhoneNumber.db",null,2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case CONTENT_DIR:
                cursor =db.query("p_number",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CONTENT_ITEM:
                String contentId = uri.getPathSegments().get(1);
                cursor = db.query("p_number",projection,"id=?",new String[]
                        {contentId},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    /*@Override
    public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)){
            case CONTENT_DIR:
                updatedRows =db.update("p_number",values,selection,selectionArgs);
                break;
            case CONTENT_ITEM:
                String ContentId = uri.getPathSegments().get(1);
                updatedRows = db.update("p_number",values,"id=?",new String[] {ContentId});
                break;
        }
        return updatedRows;
    }*/
    @Override
    public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
